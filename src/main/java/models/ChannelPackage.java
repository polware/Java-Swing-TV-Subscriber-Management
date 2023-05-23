package models;

/**
 * Created by IntelliJ IDEA
 * User: Paul H. Vargas P.
 * Date: 21/05/2023
 * Time: 2:28 p. m.
 */
public abstract class ChannelPackage {
    String channelName;
    String language;
    String category;
    int price;

    public ChannelPackage(String channelName, String language, String category, int price) {
        this.channelName = channelName;
        this.language = language;
        this.category = category;
        this.price = price;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
