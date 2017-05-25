package ru.itis.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class FileWorker {

    private static PrintWriter pw;

    private static Scanner sc;

    private static String value;

    private static final File file = new File("C:\\Spring\\javafx\\src\\main\\resources\\ru.itis\\token.txt");

    public static void write(String text) throws FileNotFoundException {
        pw = new PrintWriter(file);
        pw.print(text);
        pw.close();
    }
    public static String read() throws FileNotFoundException {
        sc = new Scanner(file);
        value = sc.nextLine();
        sc.close();
        return value;
    }
    public static boolean fileIsEmpty() throws FileNotFoundException {
        sc = new Scanner(file);
        return !sc.hasNextLine();
    }

    public static boolean fileExists(){
        return file.exists();
    }
    public static void clean() throws FileNotFoundException {
        pw = new PrintWriter(file);
        pw.print("");
        pw.close();
    }
}
