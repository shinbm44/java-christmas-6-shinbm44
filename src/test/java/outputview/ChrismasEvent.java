package outputview;

import menu.Menu;

import java.time.LocalDate;

public class ChrismasEvent {

    private final LocalDate startDate = LocalDate.of(2023, 12, 1);
    private final LocalDate endDate = LocalDate.of(2023, 12, 31);



    public static class EventInfo {
        public final int discountAmount;

        EventInfo( int discountAmount){
            this.discountAmount = discountAmount;
        }
        public int getDiscountAmount() {
            return discountAmount;
        }
    }

    public EventInfo getEventInfo(int date , String menuInput) {
        LocalDate currentDate = LocalDate.of(2023, 12, date);
        int dayGap = (int)startDate.until(currentDate.plusDays(1)).getDays();

        if(currentDate.isAfter(startDate) && currentDate.isBefore(endDate.plusDays(1))) {
            return calculateEventDiscount(dayGap, menuInput);
        }
        return new EventInfo(0);
    }

    private EventInfo calculateEventDiscount(int dayGap, String menuInput) {
        int DdayDiscount = checkDdayDiscount(dayGap);
        int DayEventDiscount = checkDayEventDiscount(dayGap, menuInput);
        int specialDiscount = checkSpecialDiscount(dayGap);

        return new EventInfo(DdayDiscount + DayEventDiscount + specialDiscount);
    }

    private int checkDayEventDiscount(int dayGap, String menuInput) {
        LocalDate reserveDate = LocalDate.of(2023, 12, dayGap);
        String dayOfWeek = String.valueOf((reserveDate.getDayOfWeek().getValue()));
        if(Integer.parseInt(dayOfWeek) <= 4 || Integer.parseInt(dayOfWeek) == 7) {
            return checkDesert(menuInput);
        }
        return checkMainMenu(menuInput);
    }

    private int checkMainMenu(String menuInput) {
        String[] firstMenuDivide = menuInput.split(",");
        int discountSum = 0;
        for (String item : firstMenuDivide) {
            String[] menuInfo = item.split("-");
            String menuName = menuInfo[0].trim();
            Menu menu = Menu.valueOf(menuName);
            if (menu.getCategory().equals("메인")) {
                discountSum += Integer.parseInt(menuInfo[1].trim()) * 2023;
            }
        }
        System.out.printf("주말 할인: -%d원",discountSum);
        System.out.println();
        return discountSum;
    }

    private int checkDesert(String menuInput) {
        String[] firstMenuDivide = menuInput.split(",");
        int discountSum = 0;
        for (String item : firstMenuDivide) {
            String[] menuInfo = item.split("-");
            String menuName = menuInfo[0].trim();
            Menu menu = Menu.valueOf(menuName);
            if (menu.getCategory().equals("디저트")) {
                discountSum += Integer.parseInt(menuInfo[1].trim()) * 2023;
            }
        }
        System.out.printf("평일 할인: -%d원",discountSum);
        System.out.println();
        return discountSum;
    }

    private int checkSpecialDiscount(int dayGap) {
        LocalDate reserveDate = LocalDate.of(2023, 12, dayGap);
        String dayOfWeek = String.valueOf((reserveDate.getDayOfWeek().getValue()));
        if(Integer.parseInt(dayOfWeek) == 7 || dayGap == 25){
            System.out.printf("특별 할인: -%d원",1000);
            System.out.println();
        }
        return 1000;
    }

    private int checkDdayDiscount(int dayGap) {
        if (dayGap <=25 ) {
            System.out.printf("크리스마스 디데이 할인: -%d원", (dayGap * 100)+1000);
            System.out.println();
        }
        return (dayGap * 100)+1000;
    }

}
