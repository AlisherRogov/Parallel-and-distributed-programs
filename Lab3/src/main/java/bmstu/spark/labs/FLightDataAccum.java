package bmstu.spark.labs;

import java.io.Serializable;

public class FLightDataAccum implements Serializable {

    private static final int TYPE_FLIGHT = 1;
    private static final int ORIGIN_AIRPORT_ID = 11;
    private static final int DEST_AIRPORT_ID = 14;
    private static final int FLIGHT_DELAY_TIME = 18;
    private static final int CANCELED = 19;

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
