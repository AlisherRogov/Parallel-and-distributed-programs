package bmstu.spark.labs;

public class CsvParse {

    private String text;
    private static final float FLOAT_ZERO = 0.00f;
    private static final int ORIGIN_AIRPORT_ID = 11;
    private static final int DEST_AIRPORT_ID = 14;
    private static final int FLIGHT_DELAY_TIME = 18;
    private static final int CANCELED = 19;
    private static final int AIRPORT_ID_TYPE_AIRPORT = 0;
    private static final int AIRPORT_NAME_TYPE_AIRPORT = 1;
    private String[] columnsTypeAirport;
    private String[] columnsTypeFlight;

    public CsvParse(String text) {
        this.text = text;
        this.columnsTypeAirport = text.split("\",");
        this.columnsTypeFlight = text.split(",");
    }

    public int getAirportsID() {
        return Integer.parseInt(columnsTypeAirport[AIRPORT_ID_TYPE_AIRPORT].replaceAll("\"", ""));
    }

    public String getAirportsName() {
        return columnsTypeAirport[AIRPORT_NAME_TYPE_AIRPORT].replaceAll("\"", "");
    }

    public int getOriginAirportId() {

        return Integer.parseInt(columnsTypeFlight[ORIGIN_AIRPORT_ID]);
    }

    public int getDestAirportId() {

        return Integer.parseInt(columnsTypeFlight[DEST_AIRPORT_ID]);
    }

    public float getDelayTime() {

        float delayTime;
        if (columnsTypeFlight[FLIGHT_DELAY_TIME].equals("")) {
            delayTime = FLOAT_ZERO;
        } else {
            delayTime = Float.parseFloat(columnsTypeFlight[FLIGHT_DELAY_TIME]);
        }
        return delayTime;
    }

    public boolean getCanceled() {

        return Float.parseFloat(columnsTypeFlight[CANCELED]) != FLOAT_ZERO;
    }
}