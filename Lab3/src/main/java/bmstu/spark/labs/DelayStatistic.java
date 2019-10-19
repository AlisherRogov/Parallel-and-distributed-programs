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

//    public float percentDelayed() {
//
//    }

    public static DelayStatistic addStatistics(DelayStatistic statistic, float delayTime, boolean countCanceledFlights, boolean countDelayFlights) {
        return new DelayStatistic(Math.max(delayTime, statistic.getDelayTime()),
                statistic.getCountCanceledFlights() + (countCanceledFlights ? 1 : 0),
                statistic.getCountDelayFlights() + (countDelayFlights ? 1 : 0),
                statistic.getCountFlights() + 1);
    }

    public static DelayStatistic combineTwoStatistics(DelayStatistic a, DelayStatistic b) {
            a.countFlights += b.getCountFlights();
            a.countDelayFlights += b.getCountDelayFlights();
            a.countCanceledFlights += b.getCountCanceledFlights();

            return a;
    }

    float getPencentOfDelayed() {
        return 100.0f * (float) this.getCountDelayFlights() / (float) this.getCountFlights();
    }

     float getPencentOfCanceled() {
        return 100.0f * (float) this.getCountCanceledFlights() / (float) this.getCountFlights();
    }

    public static String resultStatistics(DelayStatistic a) {
            return "max delay:  " + a.getDelayTime() +
                    " percent of Delayed: " + a.getPencentOfDelayed() +
                    " percent of Canceled: " + a.getPencentOfCanceled();
    }

}
