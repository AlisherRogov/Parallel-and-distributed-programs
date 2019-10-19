package bmstu.spark.labs;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Map;

public class AirportSparkApp {

    public static void main(String[] args) {
        SparkConf conf  = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> flightData = sc.textFile("664600583_T_ONTIME_sample.csv");
        JavaRDD<String> airportData = sc.textFile("L_AIRPORT_ID.csv");

        JavaPairRDD<Integer, String> airportTable = airportData.mapToPair(
                s -> {
                    CsvParse csvS = new CsvParse(s);
                    return new Tuple2<>(csvS.getAirportsID(),
                            csvS.getAirportsName());
                }
        );

        JavaPairRDD<Tuple2<Integer, Integer>, FLightDataAccum> flightTable = flightData.mapToPair(
                s-> {
                    FLightDataAccum accum = new FLightDataAccum(s);
                    return new Tuple2<>(new Tuple2<>(accum.getOriginAirport(), accum.getDestAirport()), accum);
                }
        );

        JavaPairRDD<Tuple2<Integer, Integer>, DelayStatistic> flightStat = flightTable
                .combineByKey(
                        FlightDataAccumulator  -> {
                            return new DelayStatistic(FlightDataAccumulator.getDelayTime(),
                                    FlightDataAccumulator.getCanceled() ? 1 : 0,
                                    FlightDataAccumulator.getDelayTime() > 0 ? 1 : 0,
                                    1);
                        },
                (statCount, FLightDataAccumulator) -> DelayStatistic.addStatistics(statCount,
                        FLightDataAccumulator.getDelayTime(),
                        FLightDataAccumulator.getCanceled(),
                        FLightDataAccumulator.getDelayTime() > 0),
                DelayStatistic::combineTwoStatistics);

        Map<Integer, String> mapAirportTable = airportTable.collectAsMap();

        final Broadcast<Map<Integer, String>> airportBroadcasted = sc.broadcast(mapAirportTable);

        JavaPairRDD<String, String> joined = flightStat.mapToPair(
              p -> new Tuple2<>( airportBroadcasted.value().get(p._1._1) + " TO" +
                      airportBroadcasted.value().get(p._1._2),
                      DelayStatistic.resultStatistics()
        )
    }
}
