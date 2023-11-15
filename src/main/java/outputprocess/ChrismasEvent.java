package outputprocess;
import inputprocess.MenuInput;
import menu.Menu;

import java.time.LocalDate;

public class ChrismasEvent {
    String [] menuList = MenuInput.menuDivide;

    private final LocalDate startDate = LocalDate.of(2023, 12, 1);
    private final LocalDate endDate = LocalDate.of(2023, 12, 31);



    public static class EventInfo {
        public final int discountAmount;

        EventInfo( int discountAmount){
            this.discountAmount = discountAmount;
        }
    }

    public EventInfo getEventInfo(int date) {
        LocalDate currentDate = LocalDate.of(2023, 12, date);
        int dayGap = (int)startDate.until(currentDate).getDays() + 1;

        if(dayGap >= 1 && dayGap <= 31) {
            return calculateEventDiscount(dayGap, menuList);
        }
        System.out.println("없음");
        return new EventInfo(0);
    }

    private EventInfo calculateEventDiscount(int dayGap, String [] menuList) {
        if(!discountBeforeMoney.checkEventAble){
            System.out.println("없음");
            return null;
        }
        int DdayDiscount = checkDdayDiscount(dayGap);
        int DayEventDiscount = checkDayEventDiscount(dayGap, menuList);
        int specialDiscount = checkSpecialDiscount(dayGap);
        PrintGiftStatus(discountBeforeMoney.checkGift);
        if(discountBeforeMoney.checkEventAble == true) {
            return new EventInfo(DdayDiscount + DayEventDiscount + specialDiscount + 25000);
        }
        return new EventInfo(DdayDiscount + DayEventDiscount + specialDiscount);
    }

    private void PrintGiftStatus(boolean checkGift) {
        if (checkGift == true) {
            System.out.printf("증정 이벤트: -%d원", Menu.valueOf("샴페인").getPrice());
            return;
        }
    }

    private int checkDayEventDiscount(int dayGap, String [] menuList) {
        LocalDate reserveDate = LocalDate.of(2023, 12, dayGap);
        String dayOfWeek = String.valueOf((reserveDate.getDayOfWeek().getValue()));
        if(Integer.parseInt(dayOfWeek) <= 4 || Integer.parseInt(dayOfWeek) == 7) {
            return checkDesert(menuList);
        }
        return checkMainMenu(menuList);
    }

    private int checkMainMenu(String [] menuList) {
        int discountSum = 0;
        for (String item : menuList) {
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

    private int checkDesert(String [] menuList) {
        int discountSum = 0;
        for (String item : menuList) {
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
            System.out.printf("크리스마스 디데이 할인: -%d원", (dayGap * 100)+900);
            System.out.println();
        }
        return (dayGap * 100)+900;
    }
}
