import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

class AirportComparator extends WritableComparator {

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        AirportWritableComparable first = (AirportWritableComparable) a;
        AirportWritableComparable second = (AirportWritableComparable) b;

        return Integer.compare(first.getAirportId(), second.getAirportId());
    }
}
