package christmas;

import inputprocess.DateInput;
import inputprocess.MenuInput;
import outputprocess.BeforeDiscountMoney;
import outputprocess.OrderMenuPrint;

public class Run {
    public static void main(String[] args) {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");

        DateInput date = new DateInput();
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        MenuInput menu = new MenuInput();
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", DateInput.getDate());
        System.out.println();
        System.out.println();
        OrderMenuPrint menuPrint = new OrderMenuPrint();
        System.out.println();
        BeforeDiscountMoney beforeMoney = new BeforeDiscountMoney();

    }
}
