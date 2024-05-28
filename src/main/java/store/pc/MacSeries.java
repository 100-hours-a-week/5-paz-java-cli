package store.pc;

import store.Product;

public class MacSeries extends Product {
    protected boolean portable;
    protected boolean displayable;
    protected  int memory;

    public MacSeries(String kind, String product, String processor, int storage, int price, boolean portable, boolean displayable, int memory) {
        super(kind, product, processor, storage, price);
        this.portable = portable;
        this.displayable = displayable;
        this.memory = memory;
    }

    public void setMemory(int memory){ // 메모리 용량 업그레이드 시에 메모리 스펙 & 가격 반영.
        int n = memory/this.memory - 1;
        int additionalCost = 270000*n;
        if(n>=1){
            this.price += additionalCost;
        }
        System.out.println("선택한 메모리: "+memory);
        System.out.println("추가 될 비용: "+ additionalCost);
        System.out.println("현재 기준 결제 예정 가격: "+ this.price);
        this.memory = memory;
    }
    @Override
    public void showInfo(){ // 현재 선택한 상품의 정보 출력(product의 메서드 Overide)
        System.out.println("현재 선택하신 제품 군은 "+this.kind+"입니다.");
        System.out.println("선택하신 제품의 제품명은 "+this.product+"입니다.");
        System.out.println("제품에 장착될 프로세서: "+this.processor);
        System.out.println("제품의 저장공간: "+this.storage);
        System.out.println("제품의 메모리: "+this.memory);
        System.out.println("제품의 휴대성: "+this.portable);
        System.out.println("제품의 화면 탑재 여부: "+this.displayable);
        System.out.println("결제 예정 가격: "+this.price);
    }
}
