package store;

public class Product {
    protected String kind; // 제품의 종류 => pc || phone
    protected  String product; // 제품명
    protected String processor; // 제품에 들어가는 프로세서
    protected int storage; // 제품의 저장공간 용량
    protected int price; // 제품가

    public Product(String kind, String product, String processor, int storage, int price) {
        this.kind = kind;
        this.product = product;
        this.processor = processor;
        this.storage = storage;
        this.price = price;
    }

    public void setStorage(int storage){ // 스토리지 용량 업그레이드 시에 용량과 가격 변경 반영.
        int n = storage/this.storage - 1;
        if(n>=1){
            this.price += 270000 * n;
        }
        System.out.println("선택한 스토리지 용량: "+storage);
        System.out.println("추가 될 비용: "+ (270000*n));
        System.out.println("현재 기준 결제 예정 가격: "+ this.price);
        this.storage = storage;
    }
    public void showInfo(){ //현재 선택한 상품의 정보 출력
        System.out.println("현재 선택하신 제품 군은 "+this.kind+"입니다.");
        System.out.println("선택하신 제품의 제품명은 "+this.product+"입니다.");
        System.out.println("제품에 장착될 프로세서: "+this.processor);
        System.out.println("제품의 저장공간: "+this.storage);
        System.out.println("결제 예정 가격: "+this.price);
    }
    public void showPrice() { //현재까지의 가격 출력
        System.out.println(this.product+"의 가격은 "+this.price+"원 입니다.");
    }
}
