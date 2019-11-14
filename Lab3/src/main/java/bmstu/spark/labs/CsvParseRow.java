package bmstu.spark.labs;

public class CsvParseRow {

    private String[] columns;
    private static final int ORIGIN_AIRPORT_ID = 11;
    private static final int DEST_AIRPORT_ID = 14;
    private static final int FLIGHT_DELAY_TIME = 18;
    private static final int CANCELED = 19;
    private static final int AIRPORT_ID = 0;
    private static final int AIRPORT_NAME = 1;
    private static final int TYPE_AIRPORT = 0;
    private static final int TYPE_FLIGHT= 1;

    public CsvParseRow(String text, int key) {
        if (key == TYPE_AIRPORT) {
            columns = text.split("\",");
        } else if (key == TYPE_FLIGHT) {
            columns = text.split(",");
        }
    }

    private String replaceRegex(String s) {
        return s.replaceAll("\"", "");
    }

    public int getAirportsID() {
        return Integer.parseInt(replaceRegex(columns[AIRPORT_ID]));
    }

    public String getAirportsName() {
        return replaceRegex(columns[AIRPORT_NAME]);
    }

    public int getOriginAirportId() {
        return Integer.parseInt(columns[ORIGIN_AIRPORT_ID]);
    }

    public int getDestAirportId() {
        return Integer.parseInt(columns[DEST_AIRPORT_ID]);
    }

    private float getDelayTimeFloat(String s) {
        float result;
        if (s.equals("")) {
            result =  0.00f;
        } else {
            result = Float.parseFloat(s);
        }
        return result;
    }

    public float getDelayTime(int indexFlightDelay) {
        return getDelayTimeFloat(columns[indexFlightDelay]);
    }

    public boolean getCanceled() {
        return Float.parseFloat(columns[CANCELED]) != 0.00f;
    }
}