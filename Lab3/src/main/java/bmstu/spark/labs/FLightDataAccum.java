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
        this destAirport = csv.getAirportsID();
    }
}
