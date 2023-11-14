package inputview;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class DateInputTest {

    @DisplayName("입력받은 날짜에 대한 확인 메소드 호출")
    @Test
    void readDate() {
        // 정수로 날짜가 입력되었는가?
        String Date1 = "5일";
        checkInt(Date1);
        // 1~31일의 범주 숫자로 날짜가 입력되었는가?
        String Date2 = "32";
        checkDateRange(Date2);

    }

    @DisplayName("1~31일의 범주 숫자로 날짜 입력이 들어왔는가?")
    @Test
    private void checkDateRange(String date2) {
        int date = Integer.parseInt(date2);
        assertThatThrownBy(() -> {
            if (date > 31 || date < 1) {
                throw new IllegalArgumentException("1~31일 기간에 포함되는 날짜를 입력해주세요");
            }
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1~31일 기간에 포함되는 날짜를 입력해주세요");
    }


    @DisplayName("입력받은 날짜가 정수인지 확인")
    @Test
    private void checkInt(String date1) {
        assertThatThrownBy(()->{
            if( !isInteger(date1)) {
                throw new IllegalArgumentException("날짜는 정수만 입력해주세요");
            }
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("날짜는 정수만 입력해주세요");
    }

    private boolean isInteger(String date1) {
        try {
            Integer.parseInt(date1);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

}
