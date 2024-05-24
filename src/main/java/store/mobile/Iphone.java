package store.mobile;
import store.Product;

public class Iphone extends Product {
    protected String material;

    public Iphone(String kind, String product, String processor, int storage, int price, String material) {
        super(kind, product, processor, storage, price);
        this.material = material;
    }

    @Override
    public void setStorage(int storage){
        int n = storage/this.storage - 1;
        System.out.println(storage+":"+this.storage+":"+n);
        if(n>=1){
            this.price += 150000 * n;
        }
        System.out.println("선택한 스토리지 용량: "+storage);
        System.out.println("추가 될 비용: "+ (150000*n));
        System.out.println("현재 기준 결제 예정 가격: "+ this.price);
        this.storage = storage;
    }
    @Override
    public void showInfo(){ // 현재 선택한 상품의 정보 출력(product의 메서드 Overide)
        System.out.println("현재 선택하신 제품 군은 "+this.kind+"입니다.");
        System.out.println("선택하신 제품의 제품명은 "+this.product+"입니다.");
        System.out.println("제품에 장착될 프로세서: "+this.processor);
        System.out.println("제품의 저장공간: "+this.storage);
        System.out.println("재질: "+this.material);
        System.out.println("결제 예정 가격: "+this.price);
    }
}
