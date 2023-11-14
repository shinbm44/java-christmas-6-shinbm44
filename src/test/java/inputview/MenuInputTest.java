package inputview;

import menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MenuInputTest {

    @Test
    void readMenu() {
        // 주어진 형식으로 메뉴를 입력했나?
        String menuInput = "티본스테이크^1,바비큐립-1,초코케이크-2,제로콜라-1";
        checkMenuFormat(menuInput);
        // 메뉴의 개수를 정수로 입력했나?
        String menuInput2 = "티본스테이크-2,바비큐립-15,초코케이크-4개,제로콜라-1";
        checkMenuInputInt(menuInput2);
        // 메뉴의 개수를 1 이상의 정수로 입력했나?
        String menuInput3 = "티본스테이크-0,바비큐립-15,초코케이크-4,제로콜라-1";
        checkMenuInputInt(menuInput3);
        // 최대 20개를 넘지 않는 범주로 주문했나?
        String menuInput4 = "티본스테이크-2,바비큐립-15,초코케이크-4,제로콜라-1";
        checkMenuInputRange(menuInput4);
        // 중복 메뉴를 입력하지 않았는가?
        String menuInput5 = "티본스테이크-2,티본스테이크-4,초코케이크-4,제로콜라-1";
        checkMenuDuplication(menuInput5);
        // 존재하지 않는 메뉴가 입력되지 않았는가?
        String menuInput6 = "제로콜라-1,레드와인-4,사이다-1";
        checkNotInMenu(menuInput6);
        // 음료만 주문하지 않았는가?
        String menuInput7 = "제로콜라-1,레드와인-4";
        checkOnlyDrink(menuInput7);
    }
    
    @DisplayName("음료만 주문하지 않았는가?")
    @Test
    private void checkOnlyDrink(String menuInput7) {
        assertThatThrownBy(()->{
            if(!isOnlyDrink(menuInput7)) {
                throw new IllegalArgumentException("존재하지 않는 메뉴가 입력되었습니다.");
            }
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 메뉴가 입력되었습니다.");

    }

    private boolean isOnlyDrink(String menuInput7) {
        String[] firstMenuDivide = menuInput7.split(",");
        int beverageCount = 0;
        for (String menuOrder : firstMenuDivide) {
            String[] menuInfo = menuOrder.split("-");
            String menuName = menuInfo[0].trim();
            Menu menu = Menu.valueOf(menuName);
            if(menu.getCategory().equals("음료")) {
                beverageCount++;
            }
        }
        return beverageCount != firstMenuDivide.length;
    }

    @DisplayName("존재하지 않는 메뉴가 입력되지 않았는가?")
    @Test
    private void checkNotInMenu(String menuInput6) {
        assertThatThrownBy(()->{
            if(!isExistMenu(menuInput6)) {
                throw new IllegalArgumentException("존재하지 않는 메뉴가 입력되었습니다.");
            }
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 메뉴가 입력되었습니다.");

    }

    private boolean isExistMenu(String menuInput6) {
        String [] firstMenuDivide = menuInput6.split(",");
        for (String item : firstMenuDivide){
            String[] menuInfo = item.split("-");
            String menuName = menuInfo[0].trim();
            try {
                Menu menu = Menu.valueOf(menuName);
            } catch (IllegalArgumentException e){
                return false;
            }
        }
        return true;
    }

    @DisplayName("중복메뉴를 선택하지 않았는가?")
    @Test
    private void checkMenuDuplication(String menuInput5) {
        String [] firstMenuDivide = menuInput5.split(",");
        assertThatThrownBy(() -> {
            if(!isRepeat(firstMenuDivide)){
                throw new IllegalArgumentException("메뉴는 중복 입력이 불가합니다.");
            }
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("메뉴는 중복 입력이 불가합니다.");
    }

    private boolean isRepeat(String[] firstMenuDivide) {
        List<String> menuNames = new ArrayList<String>();
        for(String item : firstMenuDivide ) {
            String[] parts = item.split("-");
            String menuName = parts[0].trim();
            menuNames.add(menuName);
        }
        if (!menuRepeatCheck(menuNames)){
            return false;
        }
        return true;
    }

    private boolean menuRepeatCheck(List<String> menuNames) {
        Set<String> numSet = new HashSet<>(menuNames);
        if(numSet.size()!= menuNames.size()){
            return false;
        }
        return true;
    }


    @DisplayName("메뉴의 갯수를 1이상의 정수로 입력했는가?")
    @Test
    private void checkMenuInputInt(String menuInput) {
        String [] firstMenuDivide = menuInput.split(",");
        assertThatThrownBy(() -> {
            if(!isPlusInt(firstMenuDivide)){
                throw new IllegalArgumentException("메뉴의 개수는 1이상의 정수로 입력해주세요.");
            }
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("메뉴의 개수는 1이상의 정수로 입력해주세요.");
    }

    private boolean isPlusInt(String[] firstMenuDivide) {
        for(String item : firstMenuDivide ) {
            String[] parts = item.split("-");

            String quantity = parts[1].trim();
            if (!isInteger(quantity) || !isPlus(quantity)) {
                return false;
            }
        }
        return true;
    }

    private boolean isPlus(String quantity) {
        int number = Integer.parseInt(quantity);
        if (number < 1) {
            return false;
        }
        return true;
    }

    private boolean isInteger(String quantity) {
        try {
            Integer.parseInt(quantity);
            return true;
        } catch (NumberFormatException e){
            return false;
        }

    }


    @DisplayName("최대 20개를 넘지 않도록 주문했나?")
    @Test
    private void checkMenuInputRange(String menuInput4) {
        String [] firstMenuDivide = menuInput4.split(",");
        assertThatThrownBy(() -> {
            if(!checkMenuRange(firstMenuDivide)){
                throw new IllegalArgumentException("메뉴는 최대 20개까지 주문가능합니다.");
            }
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("메뉴는 최대 20개까지 주문가능합니다.");
    }

    @DisplayName("20개를 넘게 주문했나 확인")
    @Test
    private boolean checkMenuRange(String[] firstMenuDivide) {
        int total = 0;
        for(String item : firstMenuDivide ) {
            String[] parts = item.split("-");
            String quantity = parts[1].trim();

            int MenuQuantity = Integer.parseInt(quantity);
            total += MenuQuantity;
        }
        if (total > 20) {
            return false;
        }
        return true;
    }

    @DisplayName("형식에 맞춰서 입력되었는지 확인")
    @Test
    private void checkMenuFormat(String menuInput) {
        String [] firstMenuDivide = menuInput.split(",");
        assertThatThrownBy(() -> {
            if(!checkMenuInput(firstMenuDivide)){
                throw new IllegalArgumentException("정해진 형식에 맞춰서 메뉴를 입력해주세요.");
            }
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("정해진 형식에 맞춰서 메뉴를 입력해주세요.");
    }

    private boolean checkMenuInput(String[] firstMenuDivide) {
        for(String item : firstMenuDivide ) {
            String[] parts = item.split("-");

            if(parts.length != 2){
                return false;
            }
            String menuItem = parts[0].trim();
            String quantity = parts[1].trim();

            if (menuItem.isEmpty() || quantity.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
