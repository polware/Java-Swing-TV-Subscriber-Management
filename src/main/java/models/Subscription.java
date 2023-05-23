package models;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA
 * User: Paul H. Vargas P.
 * Date: 21/05/2023
 * Time: 2:47 p. m.
 */
public class Subscription implements Serializable {
    private int installPrice;
    private int numberTV;
    private Subscriber subscriber;
    private SubscriptionCycle subscriptionCycle;
    private String dateSubscription;
    private int packagePrice;
    private int totalPrice;

    public Subscription(int numberTV, Subscriber subscriber, SubscriptionCycle subscriptionCycle,
                        String dateSubscription, int packagePrice) {
        this.numberTV = numberTV;
        this.subscriber = subscriber;
        this.subscriptionCycle = subscriptionCycle;
        this.dateSubscription = dateSubscription;
        this.installPrice = 10;
        this.packagePrice = packagePrice;
        this.totalPrice = (installPrice * numberTV) + packagePrice;
    }

    public int getInstallPrice() {
        return installPrice;
    }

    public int getNumberTV() {
        return numberTV;
    }

    public void setNumberTV(int numberTV) {
        this.numberTV = numberTV;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public SubscriptionCycle getSubscriptionCycle() {
        return subscriptionCycle;
    }

    public void setSubscriptionCycle(SubscriptionCycle subscriptionCycle) {
        this.subscriptionCycle = subscriptionCycle;
    }

    public String getDateSubscription() {
        return dateSubscription;
    }

    public void setDateSubscription(String dateSubscription) {
        this.dateSubscription = dateSubscription;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "installPrice=" + installPrice +
                ", numberTV=" + numberTV +
                ", subscriber=" + subscriber +
                ", subscriptionCycle=" + subscriptionCycle +
                ", dateSubscription='" + dateSubscription + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }

}
