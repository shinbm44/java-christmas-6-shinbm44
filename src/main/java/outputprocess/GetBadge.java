package outputprocess;

import inputprocess.DateInput;

public class GetBadge {
    public GetBadge(int discountMoney){
        checkBadge(discountMoney);
    }

    private void checkBadge(int discountMoney) {
        if(discountMoney > 20000) {
            System.out.println("산타\uD83C\uDF85");
            return;
        }
        if(discountMoney > 10000) {
            System.out.println("트리\uD83C\uDF84");
            return;
        }
        if(discountMoney > 5000) {
            System.out.println("별⭐");
            return;
        }
        System.out.println("없음");
    }
}
