package outputprocess;

import inputprocess.MenuInput;

public class OrderMenuPrint {
    String [] menuList = MenuInput.menuDivide;
    public OrderMenuPrint() {
        printMenu();
    }

    private void printMenu() {
        System.out.println("<주문 메뉴>");
        for(String item : menuList) {
            String [] eachItem = item.split("-");
            String menu = eachItem[0];
            String number = eachItem[1];
            System.out.printf("%s %s개",menu, number);
            System.out.println();
        }
    }
}
