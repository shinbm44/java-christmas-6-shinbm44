package outputprocess;

import inputprocess.MenuInput;
import menu.Menu;

public class BeforeDiscountMoney {
    public static boolean checkGift;
    public static boolean checkEventAble;
    public static int totalPay;
    String [] menuList = MenuInput.menuDivide;

    public BeforeDiscountMoney() {
        // 할인 전 지불의 총합
        moneySum(menuList);
        // 만원 이상 구매했나?
        checkEventAble(totalPay);
        // 지불 금액 출력
        printMoney(totalPay);
        // 12만원 이상 구매했나?
        checkGiftAble(totalPay);
    }

    private void checkGiftAble(int totalPay) {
        if (totalPay >= 120000 ){
            this.checkGift = true;
            System.out.println("<증정 메뉴>");
            System.out.println("샴페인 1개");
            return;
        }
        this.checkGift = false;
        System.out.println("<증정 메뉴>");
        System.out.println("없음");
    }

    private void printMoney(int totalPay) {
        System.out.printf("%d원", totalPay);
        System.out.println();
    }

    private void checkEventAble(int totalPay) {
        if (totalPay < 10000){
            this.checkEventAble = false;
            return;
        }
        this.checkEventAble = true;
    }

    private void moneySum(String[] menuList) {
        System.out.println("<할인 전 총주문 금액>");
        int totalPay = 0;
        for(String item : menuList) {
            String [] menuInfo = item.split("-");
            String menuName = menuInfo[0].trim();
            Menu menu = Menu.valueOf(menuName);
            if(menu.getName().equals(menuName)){
                totalPay += menu.getPrice() * Integer.parseInt(menuInfo[1].trim());
            }
        }
        this.totalPay = totalPay;
    }
}
