package bmstu.hadoop.labs;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class AirportGroupingComparator extends WritableComparator {

    protected AirportGroupingComparator() {
        super(AirportJoinCompositeKey.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        AirportJoinCompositeKey first = (AirportJoinCompositeKey) a;
        AirportJoinCompositeKey second = (AirportJoinCompositeKey) b;

        return Integer.compare(first.getAirportId(), second.getAirportId());
    }
}
