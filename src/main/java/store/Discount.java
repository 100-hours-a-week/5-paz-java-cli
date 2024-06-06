package store;

public class Discount {
    private boolean isDiscount = false; // discount 변수 초기화

    public synchronized void allowDiscount(){ // discount 적용
        isDiscount = true;
    }

    public synchronized void cancelDiscount(){ // discount 취소
        isDiscount = false;
    }

    public synchronized boolean getDiscountInfo(){ // discount 정보를 반환하기 위한 getter
        return isDiscount;
    }
}
