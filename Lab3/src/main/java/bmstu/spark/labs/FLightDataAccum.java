package bmstu.spark.labs;

import java.io.Serializable;

public class FLightDataAccum implements Serializable {

    private static final int TYPE_FLIGHT = 1;
    private static final int AIRPORT_ID = 14;
    private static final int FLIGHT_DELAY_TIME = 18;

    private CsvParse csv;
    private int countFlightts;
    private int countDelayFlights;
    private int countCanceledFlights;
    private int maxDelayTime;


    public FLightDataAccum(String s) {
        csv = new CsvParse(s);
        
    }
}
