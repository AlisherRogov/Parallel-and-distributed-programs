package bmstu.hadoop.labs;
import org.apache.hadoop.io.Text;

public class CsvParseRow {

    private static final float FLOAT_ZERO = 0.00f;
    private static final int TYPE_AIRPORT = 0;
    private static final int TYPE_FLIGHT = 1;

    private String[] columns;

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

    public int getAirportsID(int indexAirportID, int key) {
        int airportID = 0;
        if (key == TYPE_AIRPORT) {
            airportID = Integer.parseInt(replaceRegex(columns[indexAirportID], "\""));
        } else if (key == TYPE_FLIGHT) {
            airportID = Integer.parseInt(columns[indexAirportID]);
        }
        return airportID;
    }

    public Text getAirportsName(int indexAirportName) {
        return new Text(replaceRegex(columns[indexAirportName], "\""));
    }

    public float getDelayTime(int indexFlightDelay) {
        float delayTime;
        if (columns[indexFlightDelay].equals("")) {
            delayTime = FLOAT_ZERO;
        } else {
            delayTime = Float.parseFloat(columns[indexFlightDelay]);
        }
        return delayTime;
    }

    public Text getDelayText(int indexFlightDelay) {
        return new Text(columns[indexFlightDelay]);
    }
}
