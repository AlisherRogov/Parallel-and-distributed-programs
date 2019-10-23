package bmstu.hadoop.labs;
import org.apache.hadoop.io.Text;

public class CsvParseRow {

    private String[] columns;

    public CsvParseRow(String text, int key) {
        if (key == 0) {
            columns = text.split("\",");
        } else if (key == 1) {
            columns = text.split(",");
        }
    }

    private String replaceRegex(String s, String regex) {
        return s.replaceAll(regex, "");

    }

    public int getAirportsID(int indexAirportID, int key) {
        int airportID = 0;
        if (key == 0) {
            airportID = Integer.parseInt(replaceRegex(columns[indexAirportID], "\""));
        } else if (key == 1) {
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
            delayTime = 0;
        } else {
            delayTime = Float.parseFloat(columns[indexFlightDelay]);
        }
        return delayTime;
    }

    public Text getDelayText(int indexFlightDelay) {
        return new Text(columns[indexFlightDelay]);
    }
}
