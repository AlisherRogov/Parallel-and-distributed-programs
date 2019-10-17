package bmstu.hadoop.labs;
import org.apache.hadoop.io.Text;

public class CsvParse {

    private Text text;

    public CsvParse(Text text) {
        this.text = text;
    }

    public int getAirportsID(int indexAirportID, int key) {
        int airportID = 0;
        String[] columns;
        if (key == 0) {
            columns = text.toString().split("\",");
            airportID = Integer.parseInt(columns[indexAirportID].replaceAll("\"", ""));
        } else if (key == 1) {
            columns = text.toString().split(",");
            airportID = Integer.parseInt(columns[indexAirportID]);
        }
        return airportID;
    }

    public Text getAirportsName(int indexAirportName) {
        String[] columns = text.toString().split("\",");
        return new Text(columns[indexAirportName].replaceAll("\"", ""));
    }

    public float getDelayTime(int indexFlightDelay) {
        String[] columns = text.toString().split(",");
        float delayTime;
        if (columns[indexFlightDelay].equals("")) {
            delayTime = 0;
        } else {
            delayTime = Float.parseFloat(columns[indexFlightDelay]);
        }
        return delayTime;
    }

    public Text getDelayText(int indexFlightDelay) {
        String[] columns = text.toString().split(",");
        return new Text(columns[indexFlightDelay]);
    }
}
