package bmstu.spark.labs;

import java.io.Serializable;

public class DelayStatistic implements Serializable {

        private float delayTime;
        private int countDelayFlights;
        private int countCanceledFlights;
        private int countFlights;

        public DelayStatistic() {};

        public DelayStatistic(float delayTime, boolean countCanceledFlights,
                              boolean countDelayFlights, int countFlights) {
            this.delayTime = delayTime;
            this.countCanceledFlights = countCanceledFlights ? 1 : 0;
            this.countDelayFlights = countDelayFlights ? 1 : 0;
            this.countFlights = countFlights;
        }

    public float getDelayTime() {
        return delayTime;
    }

    public int getCountCanceledFlights() {
        return countCanceledFlights;
    }

    public int getCountDelayFlights() {
        return countDelayFlights;
    }

    public int getCountFlights() {
        return countFlights;
    }

    public void addValue(DelayStatistic statistic, float delayTime, boolean countCanceledFlights,
                         boolean countDelayFlights) {
            return new( delayTime > statistic.getDelayTime() ? delayTime, statistic.getDelayTime())
        }
}
