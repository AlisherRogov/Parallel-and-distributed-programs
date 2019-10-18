package bmstu.spark.labs;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class AirportSparkApp {

    private static final int TYPE_AIRPORT = 0;
    private static final int AIRPORT_ID = 0;
    private static final int AIRPORT_NAME = 1;

    
    public static void main(String[] args) {
        SparkConf conf  = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> flightData = sc.textFile("664600583_T_ONTIME_sample.csv");
        JavaRDD<String> airportData = sc.textFile("L_AIRPORT_ID.csv");

        JavaPairRDD<Integer, String> airportTable = airportData.mapToPair(
                s -> {
                    CsvParse csvS = new CsvParse(s);
                }
        );
    }
}
