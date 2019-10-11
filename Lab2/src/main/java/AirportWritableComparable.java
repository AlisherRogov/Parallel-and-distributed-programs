import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class AirportWritableComparable implements WritableComparable {

    private int airportId;
    private int  key;

    public AirportWritableComparable(int airportId, int key) {
        this.airportId = airportId;
        this.key = key;
    };

    public int getAirportId() {
        return airportId;
    }

    public int getKey() {
        return key;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {

    }
}
