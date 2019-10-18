package bmstu.spark.labs;

import java.io.Serializable;

public class DelayStatistic implements Serializable {

        private float delayTime;
        private int countDelayFlights;
        private int countCanceledFlights;
        private int countFlights;

        public DelayStatistic() {};

        public DelayStatistic(float delayTime, boolean countCanceledFlights,
                              boolean countDelayFlights) {
            this.delayTime = delayTime;
            this.countCanceledFlights = countCanceledFlights ? 1 : 0;
            this.countDelayFlights = countDelayFlights ? 1 : 0;
            this.countFlights = 1;
        }

        public void addValue(int statistic, float delayTime, boolean countCanceledFlights,
                             boolean countDelayFlights) {
            return
        }
}
