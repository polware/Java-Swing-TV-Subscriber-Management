package models;

/**
 * Created by IntelliJ IDEA
 * User: Paul H. Vargas P.
 * Date: 21/05/2023
 * Time: 2:37 p. m.
 */
public class MovieChannel extends ChannelPackage {
    int additionalPrice = 20;

    public MovieChannel(String channelName, String language, String category, int price) {
        super(channelName, language, category, price);
    }

    @Override
    public int getPrice() {
        return super.getPrice() + additionalPrice;
    }

}
