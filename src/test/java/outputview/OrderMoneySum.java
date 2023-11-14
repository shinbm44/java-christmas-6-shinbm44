package outputview;

import menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class OrderMoneySum {

    @Test
    void outputMenu() {
        String menuInput = "티본스테이크-2,바비큐립-3,초코케이크-4,제로콜라-1";
        int payMoney = checkEventAble(menuInput);
        assertThat(payMoney).isEqualTo(335000);

    }

    @DisplayName("총 주문금액 계산")
    @Test
    private int checkEventAble(String menuInput) {
        int totalPay = 0;
        String[] firstMenuDivide = menuInput.split(",");
        for(String item : firstMenuDivide) {
            String [] menuInfo = item.split("-");
            String menuName = menuInfo[0].trim();
            Menu menu = Menu.valueOf(menuName);
            if(menu.getName().equals(menuName)){
                totalPay += menu.getPrice() * Integer.parseInt(menuInfo[1].trim());
            }
        }
        return totalPay;
    }
}
