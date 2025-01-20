package code.seeds;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Main 통합 테스트")
class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final java.io.InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
        outContent.reset();
    }

    private Stream<String> getOutputLines() {
        return Stream.of(outContent.toString().split(System.lineSeparator()));
    }

    @Test
    @DisplayName("정상 동작: 고정 단어 'paper'에서 올바른 힌트 생성 및 정답 처리")
    void 정상동작_테스트() {
        Main mainApp = new Main("paper");
        String input = String.join("\n",
                "apple",
                "paper",
                "2"
        ) + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        mainApp.run(new Scanner(System.in));
        var lines = getOutputLines().map(String::trim).toList();


        assertThat(lines.get(0)).isEqualTo("단어 맞추기 게임을 시작합니다.");
        assertThat(lines).filteredOn(line -> line.contains("단어를 입력해주세요:")).isNotEmpty();
        assertThat(lines).anySatisfy(line ->
                assertThat(line).contains("1매치 3틀림")
        );
        assertThat(lines).anySatisfy(line ->
                assertThat(line).contains("정답입니다! 게임 종료")
        );
        assertThat(lines).anySatisfy(line ->
                assertThat(line).contains("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.")
        );
    }

    @Test
    @DisplayName("경계 조건: 입력값의 앞뒤 공백이 제거됨")
    void 입력공백_테스트() {
        String input = "   paper   \n2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main mainApp = new Main("paper");
        mainApp.run(new Scanner(System.in));
        var lines = getOutputLines().map(String::trim);
        assertThat(lines).anyMatch(line -> line.contains("정답입니다! 게임 종료"));
    }

    @Test
    @DisplayName("예외 상황: 5글자 미만 입력 시 예외 발생")
    void 단어길이_예외테스트() {
        String input = "app\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main mainApp = new Main("paper");
        assertThatThrownBy(() -> mainApp.run(new Scanner(System.in)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("5글자 알파벳");
    }

    @Test
    @DisplayName("예외 상황: 알파벳 외 문자 입력 시 예외 발생")
    void 알파벳외문자_예외테스트() {
        String input = "pa@er\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main mainApp = new Main("paper");
        assertThatThrownBy(() -> mainApp.run(new Scanner(System.in)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("5글자 알파벳");
    }

    @Test
    @DisplayName("예외 상황: 재시작/종료 입력이 1 또는 2가 아닐 경우 예외 발생")
    void 재시작입력_예외테스트() {
        String input = String.join("\n",
                "paper",
                "3"
        ) + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main mainApp = new Main("paper");
        assertThatThrownBy(() -> mainApp.run(new Scanner(System.in)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1 또는 2");
    }

    @Test
    @DisplayName("비즈니스 로직: 힌트 형식이 정규 표현식과 일치함")
    void 힌트형식_정규표현식테스트() {
        String input = String.join("\n",
                "place",
                "paper",
                "2"
        ) + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main mainApp = new Main("paper");
        mainApp.run(new Scanner(System.in));
        var lines = getOutputLines().map(String::trim);
        Pattern pattern = Pattern.compile("[0-9]+매치( [0-9]+틀림)?");
        assertThat(lines)
                .anySatisfy(line -> assertThat(pattern.matcher(line).find()).isTrue());
    }
}
