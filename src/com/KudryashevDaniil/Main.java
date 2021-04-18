package com.KudryashevDaniil;

import com.KudryashevDaniil.TerminalConsole.TerminalConsole;
import com.KudryashevDaniil.TerminalConsole.TerminalConsoleImp;

import javax.swing.text.Document;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TerminalConsole console = new TerminalConsoleImp();
        console.authorize();
        console.checkMoney();
        console.addMoney();
        console.getMoney();
        console.checkMoney();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите url: ");
        boolean b = false;
        URL urlOb;
        while (!b) {
            try {
                String url = scanner.nextLine();
                urlOb = new URL(url);
                HttpURLConnection myUrlCon = (HttpURLConnection) urlOb.openConnection();
                System.out.println("--- Содержимое ---");
                InputStream input = myUrlCon.getInputStream();
                int c;
                while(((c = input.read()) != -1)) {
                    System.out.print((char) c);
                }
                input.close();
                b = true;
            } catch (Exception e) {
                System.out.println("Не удалось установить соединение");
            }
        }

    }
}
