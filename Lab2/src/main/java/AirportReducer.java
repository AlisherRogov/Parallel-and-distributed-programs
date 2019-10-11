import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class AirportReducer extends Reducer<AirportWritableComparable, Text, Text, Text> {

    @Override
    protected void reduce(AirportWritableComparable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator<Text> iter = values.iterator();
        Text airportId = iter.next();

        if (iter.hasNext()) {
            float current = Float.parseFloat(iter.next().toString());
            float min = current;
            float average = current;
            float max = current;
            int counter = 1;
            while (iter.hasNext()) {
                current = Float.parseFloat(iter.next().toString());
                average += current;
                if (current > max) {
                    max = current;
                }
                if(current < min) {
                    min = current;
                }
                counter++;
            }
            average /= counter;

        }
    }
}
