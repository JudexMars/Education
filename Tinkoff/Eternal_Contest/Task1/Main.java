package Tinkoff.Eternal_Contest.Task1;

import java.util.Scanner;


public class Main {
    public static void main(String ... args) {
        try (Scanner in = new Scanner(System.in)) {
            int price = in.nextInt(); // Абонентская плата
            int traffic = in.nextInt(); // Трафик в тарифе
            int overpaymentRate = in.nextInt(); // Цена дополнительного мегабайта
            int plannedTraffic = in.nextInt(); // Сколько будет потрачено мегабайт сверх тарифа

            int overpayment = (plannedTraffic - traffic) * overpaymentRate;
            int sum = price + (overpayment < 0 ? 0 : overpayment);
            System.out.println(sum);
        }
    }
}
