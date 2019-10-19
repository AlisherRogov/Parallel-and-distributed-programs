package bmstu.spark.labs;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

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

        JavaPairRDD<Tuple2<Integer, Integer>, String> flightStat = flightTable
                .combineByKey(
                        FlightDataAccumulator  -> {
                            int countCanceledFlights = FlightDataAccumulator.getCanceled() ? 1 : 0;
                            int countDelayFlights = FlightDataAccumulator.getDelayTime() > 0.0f ? 1 : 0;
                            return new DelayStatistic(
                                    FlightDataAccumulator.getDelayTime(),
                                    countCanceledFlights,
                                    countDelayFlights,
                                    1);
                        },
                (statCount, FLightDataAccumulator) -> DelayStatistic.addStatistics(statCount, FLightDataAccumulator.getDelayTime(),
                        FLightDataAccumulator.getCanceled(), FLightDataAccumulator.getDelayTime() > 0),
                DelayStatistic::addStatistics);

    }
}
