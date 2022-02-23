package com.sapient.util;

import java.util.InputMismatchException;
import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InputUtil {

    public static int getIntegerInput(String msg, String errorString) {
        while (true) {
            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);
            System.out.println(msg);
            try {
                int n = sc.nextInt();
                return n;
            } catch (InputMismatchException e) {
            	log.debug(e.getMessage());
                System.out.println(errorString);
            }
        }

    }

    public static double getDoubleInput(String msg) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.println(msg);
        double n = sc.nextDouble();
        return n;
    }

    public static String getStringInput(String msg) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.print(msg);
        String str = sc.nextLine().trim();
        return str;

    }

    public static void clearSceen() {
        System.out.print("\n\n\n");
    }

}