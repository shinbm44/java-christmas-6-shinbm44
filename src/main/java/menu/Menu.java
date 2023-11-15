package menu;

public enum Menu {
    양송이수프("애피타이저", "양송이수프", 6000),
    타파스("애피타이저", "타파스", 5500),
    시저샐러드("애피타이저", "시저샐러드", 8000),
    티본스테이크("메인", "티본스테이크", 55000),
    바비큐립("메인", "바비큐립", 54000),
    해산물파스타("메인", "해산물파스타", 35000),
    크리스마스파스타("메인", "크리스마스파스타", 25000),
    초코케이크("디저트", "초코케이크", 15000),
    아이스크림("디저트", "아이스크림", 5000),
    제로콜라("음료", "제로콜라", 3000),
    레드와인("음료", "레드와인", 60000),
    샴페인("음료", "샴페인", 25000);

    private final String category;
    private final String name;
    private final int price;

    Menu(String category, String name, int price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}