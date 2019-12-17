package ru.geekBrains.lesson3;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
       play();
    }

    private static void play () {
        System.out.println("Ваша задача угадать число");
        for (int i = 10; i <= 30; i += 10)  playLevel(i);
        System.out.println("Победа");
        scanner.close();
    }

    private static void playLevel(int range) {
        int number = (int) (Math.random() * range);
        while (true) {
            System.out.println("Угадайте число от 0 до "+ range);
            int inputNumber = scanner.nextInt();
            if (inputNumber == number) {
                System.out.println("Угадали");
                break;
            } else if (inputNumber > number) {
                System.out.println("Число больше");
            } else {
                System.out.println("Число мменьше");
            }

        }
    }
}
