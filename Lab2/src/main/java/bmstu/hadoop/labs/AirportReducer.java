package bmstu.hadoop.labs;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class AirportReducer extends Reducer<AirportJoinCompositeKey, Text, Text, Text> {

    @Override
    protected void reduce(AirportJoinCompositeKey key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator<Text> iter = values.iterator();
        Text airportId = new Text(iter.next().toString());

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
                }else if (current < min) {
                    min = current;
                }
                counter++;
            }
            average /= counter;
            Text Info = new Text("  Average: " + Float.toString(average) + ", Max: " +
                    Float.toString(max) + ", Min: " + Float.toString(min));
            context.write(airportId, Info);
        }
    }
}
