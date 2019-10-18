package bmstu.spark.labs;

import java.io.Serializable;

public class FLightDataAccum implements Serializable {


    private CsvParse csv;
    private int destAirport;
    private int originAirport;
    private boolean isCanceled;
    private float delayTime;

    public FLightDataAccum(String s) {
        csv = new CsvParse(s);
        this.destAirport = csv.getDestAirportId();
        this.originAirport = csv.getOriginAirportId();
        this.delayTime = csv.getDelayTime();
        this.isCanceled = csv.getCanceled();
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

    public CsvParse getCsv() {
        return csv;
    }
}
