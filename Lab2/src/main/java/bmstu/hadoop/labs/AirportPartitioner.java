package bmstu.hadoop.labs;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.io.Text;

public class AirportPartitioner extends Partitioner<AirportJoinCompositeKey, Text> {
    @Override
    public int getPartition(AirportJoinCompositeKey k, Text v, int numReduceTasks) {
        return k.getAirportId() % numReduceTasks;
    }
}
