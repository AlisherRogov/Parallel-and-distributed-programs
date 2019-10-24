package bmstu.hadoop.labs;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlightMapper extends Mapper<LongWritable, Text, AirportJoinCompositeKey, Text> {

    private static final int TYPE_FLIGHT = 1;
    private static final int AIRPORT_ID = 14;
    private static final int FLIGHT_DELAY_TIME = 18;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        CsvParseRow csv = new CsvParseRow(value.toString(), TYPE_FLIGHT);
        if (key.get() > 0) {
            float delayTime = csv.getDelayTime(FLIGHT_DELAY_TIME);
            if (delayTime > 0) {
                int airportID = csv.getAirportsID(AIRPORT_ID, TYPE_FLIGHT);
                context.write(new AirportJoinCompositeKey(airportID, TYPE_FLIGHT), csv.getDelayText(FLIGHT_DELAY_TIME));
            }
        }
    }
}
