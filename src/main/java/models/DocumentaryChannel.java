package models;

/**
 * Created by IntelliJ IDEA
 * User: Paul H. Vargas P.
 * Date: 21/05/2023
 * Time: 2:36 p. m.
 */
public class DocumentaryChannel extends ChannelPackage {
    int additionalPrice = 15;

    public DocumentaryChannel(String channelName, String language, String category, int price) {
        super(channelName, language, category, price);
    }

    @Override
    public int getPrice() {
        return super.getPrice() + additionalPrice;
    }

}
