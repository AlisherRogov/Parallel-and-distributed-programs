package bmstu.spark.labs;

import java.io.Serializable;

public class DelayStatistic implements Serializable {

        private float delayTime;
        private int countDelayFlights;
        private int countCanceledFlights;
        private int countFlights;

        public DelayStatistic() {};

        public DelayStatistic(float delayTime, int countCanceledFlights,
                              int countDelayFlights) {
            this.delayTime = delayTime;
            this.countCanceledFlights = countCanceledFlights;
            this.countDelayFlights = countDelayFlights;
            this.countFlights = 1;
        }
}
