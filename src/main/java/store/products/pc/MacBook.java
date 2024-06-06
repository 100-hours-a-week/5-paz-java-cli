package store.products.pc;

public class MacBook extends MacSeries{
    protected int diaplaySize;

    public MacBook(String kind, String product, String processor, int storage, int price, boolean portable, boolean diaplayable, int memory, int diaplaySize) {
        super(kind, product, processor, storage, price, portable, diaplayable, memory);
        this.diaplaySize = diaplaySize;
    }

    @Override
    public void showInfo(){ // 현재 선택한 상품의 정보 출력(product의 메서드 Overide)
        System.out.println("현재 선택하신 제품 군은 "+this.kind+"입니다.");
        System.out.println("선택하신 제품의 제품명은 "+this.product+"입니다.");
        System.out.println("제품에 장착될 프로세서: "+this.processor);
        System.out.println("제품의 저장공간: "+this.storage);
        System.out.println("제품의 메모리: "+this.memory);
        System.out.println("제품의 휴대성: "+this.portable);
        System.out.println("화면 크기: "+this.diaplaySize+"인치");
        System.out.println("결제 예정 가격: "+this.price);
    }
}
