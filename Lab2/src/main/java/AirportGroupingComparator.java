import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class AirportComparator implements WritableComparator {

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        AirportWritableComparable first = (AirportWritableComparable) a;
        AirportWritableComparable second = (AirportWritableComparable) b;

        return Integer.compare(first.getAirportId(), second.getAirportId());
    }
}
