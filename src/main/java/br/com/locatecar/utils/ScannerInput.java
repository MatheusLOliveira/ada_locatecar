package br.com.locatecar.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerInput {

    public static int getInt() {
        try {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextInt();
        } catch (InputMismatchException e){
            throw new InputMismatchException("It's not a number");
        }
    }

    public static String getString() {
        try {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        } catch (InputMismatchException e){
            throw new InputMismatchException();
        }
    }

    public static double getDouble() {
        try {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            throw new InputMismatchException();
        }
    }

}
