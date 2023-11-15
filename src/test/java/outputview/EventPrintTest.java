package outputview;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EventPrintTest {

    @DisplayName("할인내역 총 금액 확인")
    @Test
    void name() {
        ChrismasEvent.EventInfo test = new ChrismasEvent().getEventInfo(3,"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        assertThat(test.discountAmount).isEqualTo(6346);
    }
}
