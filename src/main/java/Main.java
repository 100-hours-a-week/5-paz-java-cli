import store.Advertisement;
import store.Discount;
import store.Kiosk;

public class Main {
    public static void main(String[] args){

        Discount discount = new Discount();

        Kiosk kiosk = new Kiosk(discount);
        Thread useKiosk = new Thread(kiosk);

        Advertisement advertisement = new Advertisement(discount);
        Thread showAd = new Thread(advertisement);

        useKiosk.start();
        showAd.start();
    }
}
