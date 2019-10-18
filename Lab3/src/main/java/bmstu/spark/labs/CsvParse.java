package bmstu.spark.labs;

public class CsvParse {

    private String text;

    public CsvParse(String text) {
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

    public String getAirportsName(int indexAirportName) {
        String[] columns = text.toString().split("\",");
        return columns[indexAirportName].replaceAll("\"", "");
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

    public boolen getCanceled(int indexCanceled) {
        String[] columns = text.toString().split(",");
        return columns[indexCanceled].to;
    }
}