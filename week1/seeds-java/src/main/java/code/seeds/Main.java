package code.seeds;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("단어 맞추기 게임을 시작합니다!.");
        Scanner scanner = new Scanner(System.in);
        new Main().run(scanner);
        scanner.close();
    }
    public void run(Scanner scanner) {
        while (true) {
            System.out.print("단어를 입력해주세요: ");
            String input = scanner.nextLine().trim();
            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("게임 종료");
                break;
            }
            System.out.println("힌트: " + input);
        }
    }
}
