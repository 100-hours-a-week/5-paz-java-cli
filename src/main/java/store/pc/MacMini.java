package store.pc;

public class MacMini extends  MacSeries{
    protected int connectable;

    public MacMini(String kind, String product, String processor, int storage, int price, boolean portable, boolean diaplayable, int memory, int connectable) {
        super(kind, product, processor, storage, price, portable, diaplayable, memory);
        this.connectable = connectable;
    }

    @Override
    public void showInfo(){ // 현재 선택한 상품의 정보 출력(product의 메서드 Overide)
        System.out.println("현재 선택하신 제품 군은 "+this.kind+"입니다.");
        System.out.println("선택하신 제품의 제품명은 "+this.product+"입니다.");
        System.out.println("제품에 장착될 프로세서: "+this.processor);
        System.out.println("제품의 저장공간: "+this.storage+"gb");
        System.out.println("제품의 메모리: "+this.memory+"g");
        System.out.println("제품의 휴대성: "+this.portable);
        System.out.println("제품의 화면 탑재 여부: "+this.displayable);
        System.out.println("제품에 연결 가능한 모니터 갯수: "+this.connectable);
        System.out.println("결제 예정 가격: "+this.price);
    }
}
