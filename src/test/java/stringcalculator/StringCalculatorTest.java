package stringcalculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class StringCalculatorTest {

    @Test
    void splitAndSum_null_또는_빈문자() throws Exception {
        int result = StringCalculator.splitAndSum(null);
        assertThat(result).isEqualTo(0);

        result = StringCalculator.splitAndSum("");
        assertThat(result).isEqualTo(0);
    }

    @Test
    void splitAndSum_숫자하나() throws Exception {
        int result = StringCalculator.splitAndSum("1");
        assertThat(result).isEqualTo(1);
    }

    @Test
    void splitAndSum_쉼표구분자() throws Exception {
        int result = StringCalculator.splitAndSum("1,2");
        assertThat(result).isEqualTo(3);
    }

    @Test
    void splitAndSum_쉼표_또는_콜론_구분자() throws Exception {
        int result = StringCalculator.splitAndSum("1,2:3");
        assertThat(result).isEqualTo(6);
    }

    @Test
    void splitAndSum_custom_구분자() throws Exception {
        int result = StringCalculator.splitAndSum("//;\n1;2;3");
        assertThat(result).isEqualTo(6);
    }

    @Test
    void splitAndSum_negative() throws Exception {
        assertThatThrownBy(() -> StringCalculator.splitAndSum("-1,2,3"))
            .isInstanceOf(RuntimeException.class);
    }

    @Test
    void splitAndSum_숫자_이외의_값() {
        assertThatThrownBy(() -> StringCalculator.splitAndSum("a,2,3"))
            .isInstanceOf(RuntimeException.class).hasMessage("숫자가 아닙니다!");
    }

    @Test
    void 커스텀_구분자를_입력하지_않은_경우() {
        assertThatThrownBy(() -> StringCalculator.splitAndSum("//\n1;2;3"))
            .isInstanceOf(RuntimeException.class).hasMessage("양식이 올바르지 않습니다.");
    }

    @Test
    @DisplayName("기본 구분자 제거")
    void removeDefaultDelim() {
        String input = "1,2,3";

        assertThat(StringCalculator.getNumbersWithDelim(input)).containsExactly("1", "2", "3");
    }

    @Test
    @DisplayName("커스텀 구분자 제거")
    void removeCustomDelim() {
        String input = "//;\n1;2;3";

        assertThat(StringCalculator.getNumbersWithDelim(input)).containsExactly("1", "2", "3");
    }
}
