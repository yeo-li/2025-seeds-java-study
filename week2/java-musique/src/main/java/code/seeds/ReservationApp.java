package code.seeds;

import global.util.DateGenerator;
import java.util.Date;
import java.util.Scanner;

public class ReservationApp {
    private final Scanner scanner = new Scanner(System.in);
    private final Date randomDateInMarch = DateGenerator.getRandomDateInMarch();
    public void run() {
        System.out.println("(오늘 날짜: " + randomDateInMarch.getMonth() + "월 " + randomDateInMarch.getDate() + "일)");
        System.out.println("[안내] 뮤지컬 '봄맞이 사전 예약' 페이지입니다.");

    }
}
