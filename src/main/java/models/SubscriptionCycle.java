package models;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA
 * User: Paul H. Vargas P.
 * Date: 21/05/2023
 * Time: 2:42 p. m.
 */
public class SubscriptionCycle implements Serializable {
    private String startDate;
    private String endDate;

    public SubscriptionCycle(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "SubscriptionCycle{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

}
