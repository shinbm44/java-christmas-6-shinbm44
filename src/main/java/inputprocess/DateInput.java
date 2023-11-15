package inputprocess;

import camp.nextstep.edu.missionutils.Console;

import java.util.InputMismatchException;

public class DateInput {
    private static int date;

    public static int getDate() {
        return date;
    }

    public DateInput() {
        readDate(date);
    }

    private void readDate(int date) {
        // 정수로 날짜가 입력되었는가?
        checkInt(date);
        // 1~31일의 범주 숫자로 날짜가 입력되었는가?
        checkDateRange(date);
    }

    private void checkDateRange(int date) {
        int dateRange = DateInput.date;
        while (true){
            try{
                rangeValidation(dateRange);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
                dateRange = changeInt();
            }
        }
    }

    private void rangeValidation(int dateRange) throws IllegalArgumentException {
        if(dateRange > 31 || dateRange < 1) {
            throw new IllegalArgumentException();
        }
    }

    private void checkInt(int date) {
        while (true) {
            try {
                DateInput.date = changeInt();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        }
    }

    private int changeInt() {
        try{
            int inputDate = Integer.parseInt(Console.readLine());
            return inputDate;
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException();
        }
    }
}



