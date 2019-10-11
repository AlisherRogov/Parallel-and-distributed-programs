import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportMapper extends Mapper<LongWritable, Text, AirportWritableComparable, Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if(key.get() > 0) {
            String[] columns = value.toString().split("\",");

            int airportID = Integer.parseInt(columns[0].replaceAll("\"", ""));
            Text airportName = new Text(columns[1].replaceAll("\"", ""));
            context.write();

        }
    }
}
