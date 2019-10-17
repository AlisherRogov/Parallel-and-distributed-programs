package bmstu.hadoop.labs;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportMapper extends Mapper<LongWritable, Text, AirportJoinCompositeKey, Text> {

    private static final int TYPE_AIRPORT = 0;
    private static final int AIRPORT_ID = 0;
    private static final int AIRPORT_NAME = 1;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        CsvParse csv = new CsvParse(value);
        if (key.get() > 0) {
            context.write(new AirportJoinCompositeKey(csv.getAirportsID(AIRPORT_ID, TYPE_AIRPORT), TYPE_AIRPORT),
                    csv.getAirportsName(AIRPORT_NAME));
        }
    }
}
