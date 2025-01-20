package code.seeds;

import java.util.Scanner;

public class Main {

    private String computerWord;

    public Main(){
        computerWord = RandomWordGenerator.getRandomWord();
    }

    public Main(String fixedWord){  // 테스트용 생성자. 삭제 금지.
        this.computerWord = fixedWord;
    }

    public static void main(String[] args) {
        System.out.println("단어 맞추기 게임을 시작합니다!.");
        Scanner scanner = new Scanner(System.in);
        new Main().run(scanner);
        scanner.close();
    }

    public void run(Scanner scanner) {
        System.out.print("단어를 입력해주세요: ");
        String input = scanner.nextLine().trim();
        // 컴퓨터 랜덤 단어 생성은 RandomWordGenerator.getRandomWord(); 를 이용한다.
    }
}
