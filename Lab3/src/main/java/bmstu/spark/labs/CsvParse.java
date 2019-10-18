package bmstu.spark.labs;

public class CsvParse {

    private String text;
    private static final int TYPE_AIRPORT = 0;
    private static final int TYPE_FLIGHT = 1;
    private static final float FLOAT_ZERO = 0.00f;
    private static final int TYPE_FLIGHT = 1;
    private static final int ORIGIN_AIRPORT_ID = 11;
    private static final int DEST_AIRPORT_ID = 14;
    private static final int FLIGHT_DELAY_TIME = 18;
    private static final int CANCELED = 19;
    private static final int AIRPORT_ID_TYPE_AIRPORT = 0;
    private static final int AIRPORT_NAME_TYPE_AIRPORT = 1;

    public CsvParse(String text) {
        this.text = text;
    }

    public int getAirportsID() {
        String[] columns = text.split("\",");
        return Integer.parseInt(columns[AIRPORT_ID_TYPE_AIRPORT].replaceAll("\"", ""));
    }

    public String getAirportsName(int indexAirportName) {
        String[] columns = text.split("\",");
        return columns[indexAirportName].replaceAll("\"", "");
    }

    public int getOriginAirportId() {
        String[] columns = text.split(",");
        return Integer.parseInt(columns[ORIGIN_AIRPORT_ID]);
    }

    public int getDestAirportId() {
        String[] columns = text.split(",");
        return Integer.parseInt(columns[DEST_AIRPORT_ID]);
    }

    public float getDelayTime(int indexFlightDelay) {
        String[] columns = text.split(",");
        float delayTime;
        if (columns[indexFlightDelay].equals("")) {
            delayTime = FLOAT_ZERO;
        } else {
            delayTime = Float.parseFloat(columns[indexFlightDelay]);
        }
        return delayTime;
    }

    public boolean getCanceled(int indexCanceled) {
        String[] columns = text.split(",");
        return Float.parseFloat(columns[indexCanceled]) != FLOAT_ZERO;
    }
}