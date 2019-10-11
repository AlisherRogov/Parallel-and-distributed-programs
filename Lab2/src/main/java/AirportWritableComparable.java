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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return  false;

        AirportWritableComparable tmp = (AirportWritableComparable) obj;
        return (tmp.getAirportId() == airportId && tmp.getKey() == key);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int compareTo(Object obj) {
        AirportWritableComparable tmp = (AirportWritableComparable) obj;
        if (airportId > tmp.getAirportId()) {
            return 1;
        }else if (airportId < tmp.getAirportId()) {
            return -1;
        } else if (key > tmp.getKey()) {
            return 1;
        } else if (key < tmp.getKey()) {
            return -1;
        }
        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.write(airportId);
        dataOutput.write(key);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        airportId = dataInput.readInt();
        key = dataInput.readInt();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
