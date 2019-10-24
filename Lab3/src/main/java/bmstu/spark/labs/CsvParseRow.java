package bmstu.spark.labs;

public class CsvParseRow {

    private String[] columns;
    private static final float FLOAT_ZERO = 0.00f;
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

    private String replaceRegex(String s, String regex) {
        return s.replaceAll(regex, "");
    }

    public int getAirportsID() {
        return Integer.parseInt(replaceRegex(columns[AIRPORT_ID], "\""));
    }

    public String getAirportsName() {
        return replaceRegex(columns[AIRPORT_NAME], "\"");
    }

    public int getOriginAirportId() {
        return Integer.parseInt(columns[ORIGIN_AIRPORT_ID]);
    }

    public int getDestAirportId() {

        return Integer.parseInt(columns[DEST_AIRPORT_ID]);
    }

    public float getDelayTime() {

        float delayTime;
        if (columns[FLIGHT_DELAY_TIME].equals("")) {
            delayTime = FLOAT_ZERO;
        } else {
            delayTime = Float.parseFloat(columns[FLIGHT_DELAY_TIME]);
        }
        return delayTime;
    }

    public boolean getCanceled() {
        return Float.parseFloat(columns[CANCELED]) != FLOAT_ZERO;
    }
}