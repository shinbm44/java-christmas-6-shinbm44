package inputprocess;

import camp.nextstep.edu.missionutils.Console;
import menu.Menu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenuInput {

    public static String menu;
    public static String [] menuDivide;

    public MenuInput() {
        checkError();
    }

    private void checkError() {
        while(true){
            try{
                menu = changeMenu();
                menuDivide = menu.split(",");
                // 주어진 형식으로 입력 받았는가?
                checkMenuFormat();
                // 메뉴의 개수를 양의 정수로 입력했나?
                checkMenuInputInt();
                // 최대 20개를 넘지 않는 범주로 주문했나?
                checkMenuInputRange(menuDivide);
                // 중복 메뉴를 입력하지 않았는가?
                checkMenuDuplication();
                // 존재하지 않는 메뉴가 입력되지 않았는가?
                checkNotInMenu();
                // 음료만 주문하지 않았는가?
                checkOnlyDrink();
                break;
            } catch (IllegalArgumentException e){
                System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    private void checkOnlyDrink()  {
        isOnlyDrink(menuDivide);
    }

    private void isOnlyDrink(String[] menuDivide) {
        int beverageCount = 0;
        for (String menuOrder : menuDivide) {
            String[] menuInfo = menuOrder.split("-");
            String menuName = menuInfo[0].trim();
            Menu menu = Menu.valueOf(menuName);
            if(menu.getCategory().equals("음료")) {
                beverageCount++;
            }
        }
        if (beverageCount == menuDivide.length){
            throw new IllegalArgumentException();
        }
    }

    private void checkNotInMenu() {
        isExistMenu(menuDivide);
    }

    private void isExistMenu(String[] menuDivide) {
        for (String item : menuDivide){
            String[] menuInfo = item.split("-");
            String menuName = menuInfo[0].trim();
            try {
                Menu menu = Menu.valueOf(menuName);
            } catch (IllegalArgumentException e){
                throw new IllegalArgumentException();
            }
        }
    }

    private void checkMenuDuplication() {
        isRepeat(menuDivide);
    }

    private void isRepeat(String[] menuDivide) throws IllegalArgumentException  {
        List<String> menuNames = new ArrayList<String>();
        for(String item : menuDivide ) {
            String[] parts = item.split("-");
            String menuName = parts[0].trim();
            menuNames.add(menuName);
        }
        if (!menuRepeatCheck(menuNames)){
            throw new IllegalArgumentException();
        }
    }

    private boolean menuRepeatCheck(List<String> menuNames) {
        Set<String> numSet = new HashSet<>(menuNames);
        if(numSet.size()!= menuNames.size()){
            return false;
        }
        return true;
    }

    private void checkMenuInputRange(String[] menuDivide) {
        ValidRange(menuDivide);
    }

    private void ValidRange(String[] menuDivide) throws IllegalArgumentException {
        int total = 0;
        for(String item : menuDivide ) {
            String[] parts = item.split("-");
            String quantity = parts[1].trim();
            int menuQuantity = Integer.parseInt(quantity);
            total += menuQuantity;
        }
        if (total > 20) {
            throw new IllegalArgumentException();
        }
    }

    private void checkMenuInputInt() {
        isPlusInt(menuDivide);

    }

    private void isPlusInt(String[] menuDivide) {
        for(String item : menuDivide) {
            String[] parts = item.split("-");

            String quantity = parts[1].trim();
            if (!isInteger(quantity) || !isPlus(quantity)) {
                throw new IllegalArgumentException();
            }
        }
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

    private void checkMenuFormat() {
        for (String item : menuDivide) {
            String[] parts = item.split("-");
            if (parts.length != 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new IllegalArgumentException();
            }
        }
    }

    private String changeMenu()  {
        while (true) {
            String menuInput = Console.readLine();
            String[] firstMenuDivide = menuInput.split(",");
            if (checkMenuInput(firstMenuDivide)) {
                return menuInput;
            }
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
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
