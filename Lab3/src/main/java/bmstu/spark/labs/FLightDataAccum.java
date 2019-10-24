package bmstu.spark.labs;

import java.io.Serializable;

public class FLightDataAccum implements Serializable {

    private static final int TYPE_FLIGHT = 1;
    private CsvParseRow csvRow;
    private int destAirport;
    private int originAirport;
    private boolean isCanceled;
    private float delayTime;

    public FLightDataAccum(String s) {
        csvRow = new CsvParseRow(s, TYPE_FLIGHT);
        this.destAirport = csvRow.getDestAirportId();
        this.originAirport = csvRow.getOriginAirportId();
        this.delayTime = csvRow.getDelayTime();
        this.isCanceled = csvRow.getCanceled();
    }

    public float getDelayTime() {
        return delayTime;
    }

    public int getDestAirport() {
        return destAirport;
    }

    public int getOriginAirport() {
        return originAirport;
    }

    public boolean getCanceled() {
        return isCanceled;
    }

}
