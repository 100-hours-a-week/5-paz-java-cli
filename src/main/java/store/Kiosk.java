package store;

import store.products.mobile.Iphone;
import store.products.pc.MacBook;
import store.products.pc.MacMini;
import store.products.pc.MacSeries;

import java.util.Scanner;

public class Kiosk implements Runnable {
    Scanner input = new Scanner(System.in);
    private Discount discount;

    public Kiosk(Discount discount){
        this.discount = discount;
    }

    @Override
    public void run() {
        useKiosk();
    }

    // 질문
    public void line() { // 각 단락의 구분을 짓기 위한 라인 출력
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.println(" ");
    }

    public int choose(int limit) { // 사용자로부터 선택지에 대한 답안 입력 반환
        int userChosen = input.nextInt();
        line();
        if (userChosen > limit || userChosen < 0) {
            System.out.println("선택지 안에서 골라주세요!");
            userChosen = choose(limit);
        } else if (userChosen == 0) { // 키오스크 종료 선택지를 택했을 경우
            close();
        }
        return userChosen;

    }

    public void close() { // 키오스크 작동 종료
        line();
        System.out.println("루팡의 키오스크 이용을 종료합니다.");
        System.out.println("다음에 또 방문해주세요.");
        line();
    }

    public void useKiosk() { // 키오스크 작동의 메인
        int userChosen;
        line();
        System.out.println("안녕하세요, 루팡입니다.");
        System.out.println("원하시는 PC나 휴대전화를 선택하시면 주문에 맞춰서");
        System.out.println("당일 밤이면 집앞으로 배송되는 상상 정도는 하게 해드립니다.");
        System.out.println("원하시는 서비스를 골라주세요!");
        line();
        System.out.println("1. PC 구매");
        System.out.println("2. 휴대전화 구매");
        line();
        userChosen = choose(2);
        if (userChosen == 1) { // 선택지에 따라 다음 질문으로 연결
            choosePC();
        } else if (userChosen == 2) {
            chooseMobile();
        }
    }

    public void choosePC() { // 맥미니인지 맥북인지 분류
        String kind;
        // kind에 PC 할당
        System.out.println("PC를 선택하셨습니다.");
        System.out.println("원하시는 용도에 맞게 PC의 유형을 골라주세요!");
        System.out.println("*tip: 데스크탑은 집에서, 노트북은 휴대하는 경우에 적합해요!");
        line();
        System.out.println("1. 데스크탑 - AKA.MacMini (휴대 불가)");
        System.out.println("2. 노트북 - AKA.MacBook (휴대 가능)");
        line();
        int userChosen = choose(2);
        if (userChosen == 1) {
            kind = "PC-MacMini";
            chooseMacMini(kind);
        } else if (userChosen == 2) {
            kind = "PC-MacBook";
            chooseMacBook(kind);
        }
    }

    public void chooseMacMini(String kind) { // 기본형과 고급형 모델의 비교
        MacSeries mypc = null;

        System.out.println("현재 선택하신 제품의 유형은 " + kind + "입니다.");
        line();
        System.out.println("맥미니는 기본형과 고급형 라인업이 준비되어 있습니다.");
        System.out.println("기본형과 고급형의 차이는 프로세서의 성능과 연결 가능한 모니터의 갯수입니다.");
        System.out.println("맥미니의 기본형과 고급형 중 어떤 모델을 원하시는지 골라주세요.");
        line();
        System.out.println("1. 기본형 - 프로세서: M2/ 연결 가능한 모니터: 2대 => 가격: 850,000원");
        System.out.println("2. 고급형 - 프로세서: M2 PRO/ 연결 가능한 모니터: 3대 => 가격: 1,250,000원");
        line();
        int userChosen = choose(2);

        // 프로세서 정보 저장
        if (userChosen == 1) {
            // 제품: kind, 가격 그대로, 프로세서 그대로, 휴대성 false, 모니터 연결 가능개수 2, 화면의 유무 false
            mypc = new MacMini(kind, "맥미니 기본형", "M2", 256, 850000, false, false, 8, 2);
        } else if (userChosen == 2) {
            // 제품: kind, 가격 +40, 프로세서 m2 pro, 휴대성 false, 모니터 연결 가능 개수 3, 화면 유무 false
            mypc = new MacMini(kind, "맥미니 고급형", "M2 PRO", 256, 1250000, false, false, 8, 3);
        }

        if (mypc != null) {
            upgradeStorage(mypc);
            upgradeMemory(mypc);
            line();
            mypc.showInfo();
            purchase(mypc.price, discount.getDiscountInfo());
        } else {
            System.out.println("오류가 발생했습니다.");
        }
    }

    public void chooseMacBook(String kind) {
        MacSeries mypc = null;

        System.out.println("현재 선택하신 제품의 유형은 " + kind + "입니다.");
        line();
        System.out.println("원하시는 맥북의 모델을 선택해주세요.");
        line();
        System.out.println("1. 13인치 - 화면 크기: 13인치/ 프로세서: M3/ => 가격: 1,390,000원");
        System.out.println("2. 13인치 - 화면 크기: 13인치/ 프로세서: M3/ => 가격: 1,590,000원");
        System.out.println("3. 15인치 - 화면 크기: 15인치/ 프로세서: M3 pro/ => 가격: 1,890,000원");
        line();
        int userChosen = choose(3);

        if (userChosen == 1) { // 프로세서 업그레이드 가능 모델
            int upgradeCpu = upgradeProcessor("M2");
            if (upgradeCpu == 1) {
                mypc = new MacBook(kind, "맥북 M2 13", "M2 PRO", 256, 1525000, true, true, 8, 13);
            } else if (upgradeCpu == 2) {
                mypc = new MacBook(kind, "맥북 M2 13", "M2", 256, 1390000, true, true, 8, 13);
            }
        } else if (userChosen == 2) { // 프로세서 업그레이드 가능 모델
            int upgradeCpu = upgradeProcessor("M3");
            if (upgradeCpu == 1) {
                mypc = new MacBook(kind, "맥북 M3 13", "M3 PRO", 256, 1725000, true, true, 8, 13);
            } else if (upgradeCpu == 2) {
                mypc = new MacBook(kind, "맥북 M3 13", "M3", 256, 1590000, true, true, 8, 13);
            }
        } else if (userChosen == 3) {
            mypc = new MacBook(kind, "맥북 M3 15", "M3 PRO", 256, 1890000, true, true, 8, 15);
        }

        if (mypc != null) {
            upgradeStorage(mypc);
            upgradeMemory(mypc);
            line();
            mypc.showInfo();
            purchase(mypc.price, discount.getDiscountInfo());
        } else {
            System.out.println("오류가 발생했습니다.");
        }
    }

    public void chooseMobile() {
        String kind = "휴대폰";
        Iphone myPhone = null;

        System.out.println("현재 선택하신 제품의 유형은 " + kind + "입니다.");
        line();
        System.out.println("아이폰은 기본형과 고급형 라인업이 준비되어 있습니다.");
        System.out.println("기본형과 고급형의 차이는 프로세서의 성능과 프레임의 재질입니다.");
        System.out.println("아이폰의 기본형과 고급형 중 어떤 모델을 원하시는지 골라주세요.");
        line();
        System.out.println("1. 기본형 - 프로세서: a16/ 프레임 재질: 알루미늄 => 가격: 1,250,000원");
        System.out.println("2. 고급형 - 프로세서: a17/ 프레임 재질: 티타늄 => 가격: 1,550,000원");
        line();
        int userChosen = choose(2);

        if (userChosen == 1) {
            myPhone = new Iphone(kind, "아이폰 기본형", "a16", 128, 1250000, "알루미늄");
        } else if (userChosen == 2) {
            myPhone = new Iphone(kind, "아이폰 고급형", "a17", 128, 1550000, "티타늄");
        }
        if (myPhone != null) {
            upgradeStorage(myPhone);
            line();
            myPhone.showInfo();
            purchase(myPhone.price, discount.getDiscountInfo());
        } else {
            System.out.println("오류가 발생했습니다.");
        }
    }

    public int upgradeProcessor(String processor) {
        line();
        System.out.println("프로세서 업그레이드를 진행하시겠습니까? " + processor + " => " + processor + " PRO");
        System.out.println("업그레이드 비용: 135000");
        line();
        System.out.println("1. 네");
        System.out.println("2. 아니오");
        line();
        return choose(2);
    }

    public void upgradeStorage(Iphone myPhone) {
        // 스토리지 용량 업그레이드 for Iphone
        line();
        System.out.println("스토리지 용량을 선택해주세요! 기본 - 256gb");
        System.out.println("1. 128gb - 비용추가 x");
        System.out.println("2. 256gb - 150,000원 추가");
        System.out.println("3. 512gb - 450,000원 추가");
        line();
        int userChosen = choose(4);
        if (userChosen == 1) {
            // 용량 그대로
            myPhone.setStorage(128);
        } else if (userChosen == 2) {
            // 용량 저장, 가격 추가
            myPhone.setStorage(256);
        } else if (userChosen == 3) {
            // 용량 저장, 가격 추가
            myPhone.setStorage(512);
        }
    }

    public void upgradeStorage(MacSeries myMac) {
        // 스토리지 용량 업그레이드 for mac
        line();
        System.out.println("스토리지 용량을 선택해주세요! 기본 - 256gb");
        System.out.println("1. 256gb - 비용추가 x");
        System.out.println("2. 512gb - 270,000원 추가");
        System.out.println("3. 1tb - 810,000원 추가");
        System.out.println("4. 2tb - 1,890,000원 추가");
        line();
        int userChosen = choose(4);
        if (userChosen == 1) {
            // 용량 그대로
            myMac.setStorage(256);
        } else if (userChosen == 2) {
            // 용량 저장, 가격 추가
            myMac.setStorage(512);
        } else if (userChosen == 3) {
            // 용량 저장, 가격 추가
            myMac.setStorage(1024);
        } else if (userChosen == 4) {
            // 용량 저장, 가격 추가
            myMac.setStorage(2048);
        }
    }

    public void upgradeMemory(MacSeries myMac) {
        // 메모리 용량 업그레이드 for mac
        line();
        System.out.println("메모리 용량을 선택해주세요! 기본 - 256gb");
        System.out.println("1. 8g - 비용추가 x");
        System.out.println("2. 16g - 270,000원 추가");
        System.out.println("3. 24g - 560,000원 추가");
        line();
        int userChosen = choose(3);
        if (userChosen == 1) {
            // 용량 그대로
            myMac.setMemory(8);
        } else if (userChosen == 2) {
            // 용량 저장, 가격 추가
            myMac.setMemory(16);
        } else if (userChosen == 3) {
            // 용량 저장, 가격 추가
            myMac.setMemory(24);
        }
    }

    public void purchase(int price, boolean discount) {
        line();
        if(discount){
            System.out.println("할인이 적용되었습니다.");
            price = price/5*4;
            System.out.println("할인 적용 가격: "+price);
        }
        else{
            System.out.println("지불해야하는 금액: " + price);
        }
        System.out.println("✔︎ -1을 입력하면 구매가 취소되고 키오스크가 사용종료 됩니다.");
        line();
        System.out.print("지불할 금액을 입력해주세요: ");

        int userPayed = input.nextInt();
        line();
        if (userPayed == -1) {
            System.out.println("결제가 취소되었습니다. 구매 프로세스를 종료합니다.");
            close();
        } else if (userPayed < price) {
            int lack = price - userPayed;
            System.out.println("지불해야 할 금액: " + price);
            System.out.println("현재 지불 완료한 금액: " + userPayed);
            System.out.println("부족한 금액: " + lack);
            System.out.println("금액을 추가 지불하거나 -1을 입력하여 구매를 취소해주세요.");
            purchase(lack, false);
        } else if (userPayed == price) {
            System.out.println("지불해야 할 금액: " + price);
            System.out.println("현재 지불 완료한 금액: " + userPayed);
            System.out.println("결제가 완료되었습니다. 주문하신 상품은 문앞으로 배송될 예정입니다. 아마도...");
            close();
        } else if (userPayed > price) {
            int change = userPayed - price;
            System.out.println("지불해야 할 금액: " + price);
            System.out.println("현재 지불 완료한 금액: " + userPayed);
            System.out.println("거스름돈: " + change);
            System.out.println("결제가 완료되었습니다. 주문하신 상품은 문앞으로 배송될 예정입니다. 아마도...");
            close();
        }
    }
}
