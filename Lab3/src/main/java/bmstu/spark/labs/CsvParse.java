package bmstu.spark.labs;

public class CsvParse {

    private String text;
    private static final int TYPE_AIRPORT = 0;
    private static final int TYPE_FLIGHT = 1;
    private static final float FLOAT_ZERO = 0.00f;

    public CsvParse(String text) {
        this.text = text;
    }

    public int getAirportsID(int indexAirportID, int key) {
        int airportID = 0;
        String[] columns;
        if (key == TYPE_AIRPORT) {
            columns = text.split("\",");
            airportID = Integer.parseInt(columns[indexAirportID].replaceAll("\"", ""));
        } else if (key == TYPE_FLIGHT) {
            columns = text.split(",");
            airportID = Integer.parseInt(columns[indexAirportID]);
        }
        return airportID;
    }

    public String getAirportsName(int indexAirportName) {
        String[] columns = text.split("\",");
        return columns[indexAirportName].replaceAll("\"", "");
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