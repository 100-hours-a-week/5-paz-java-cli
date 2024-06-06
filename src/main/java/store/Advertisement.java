package store;
public class Advertisement implements Runnable{

    private Discount discount;
    public Advertisement(Discount discount){
        this.discount = discount;
    }
    public void exitAd(){ // 광고 스레드의 종료
        System.out.println("광고 시청을 종료합니다.");
        Thread.currentThread().interrupt(); // 현재 스레드 종료
    }
    public void showAd() {
        try{
            Thread.sleep(7000);
        }catch(InterruptedException e){
            System.out.println("광고 재생 중 오류가 발생했습니다.");
            exitAd(); // 오류 발생 후 광고창 종료
        }
        System.out.println("키오스크 이용을 시작하신지 7초가 지났습니다!");
        System.out.println("광고를 시청해주세요.");
        System.out.println("-----------------------------------");
        try{
            System.out.println("광고가 나가는 중입니다.");
            Thread.sleep(5000); // 광고 재생 5초
            System.out.println("광고 시청을 완료하여 할인 쿠폰이 발급되었습니다.");
            System.out.println("발급된 할인쿠폰은 결제시 자동 적용됩니다, 감사합니다.");
            discount.allowDiscount();
        }catch(InterruptedException e){
            System.out.println("광고 재생 중 오류가 발생했습니다.");
            discount.cancelDiscount();
            exitAd(); // 오류 발생 후 광고창 종료
        }
        System.out.println("-----------------------------------");
    }
    @Override
    public void run() {
        showAd();
    }
}
