package bmstu.spark.labs;

import java.io.Serializable;

public class DelayStatistic implements Serializable {

        private float delayTime;
        private int countDelayFlights;
        private int countCanceledFlights;
        private int countFlights;

        public DelayStatistic() {};

        public DelayStatistic(float delayTime, int countCanceledFlights, int countDelayFlights, int countFlights) {
            this.delayTime = delayTime;
            this.countCanceledFlights = countCanceledFlights;
            this.countDelayFlights = countDelayFlights;
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

    public DelayStatistic addValue(DelayStatistic statistic, float delayTime, boolean countCanceledFlights, boolean countDelayFlights) {
        return new DelayStatistic(Math.max(delayTime, statistic.getDelayTime()),
                statistic.getCountCanceledFlights() + (countCanceledFlights ? 1 : 0),
                statistic.getCountDelayFlights() + (countDelayFlights ? 1 : 0),
                statistic.getCountFlights() + 1);
    }


}
