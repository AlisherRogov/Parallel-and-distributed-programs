package bmstu.hadoop.labs;
import org.apache.hadoop.io.Text;

public class CsvParseRow {

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

    private String replaceRegex(String s) {
        return s.replaceAll("\"", "");
    }

    public int getAirportsID(int indexAirportID, int key) {
        int airportID = 0;
        if (key == TYPE_AIRPORT) {
            airportID = Integer.parseInt(replaceRegex(columns[indexAirportID]));
        } else if (key == TYPE_FLIGHT) {
            airportID = Integer.parseInt(columns[indexAirportID]);
        }
        return airportID;
    }

    public Text getAirportsName(int indexAirportName) {
        return new Text(replaceRegex(columns[indexAirportName]));
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

    public Text getDelayText(int indexFlightDelay) {
        return new Text(columns[indexFlightDelay]);
    }
}
