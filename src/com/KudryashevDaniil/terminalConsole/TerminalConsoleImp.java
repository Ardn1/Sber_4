package com.KudryashevDaniil.terminalConsole;

import com.KudryashevDaniil.terminal.TerminalImp;
import com.KudryashevDaniil.termoinalExepcions.*;
import com.KudryashevDaniil.validator.PinValidator;
import com.KudryashevDaniil.validatorExeptions.NotCharException;
import com.KudryashevDaniil.validatorExeptions.NotNumberException;

import java.util.Scanner;

public class TerminalConsoleImp implements TerminalConsole {
    private final TerminalImp terminal = new TerminalImp();
    private final PinValidator pinValidator = new PinValidator();

    private void parseException(TerminalServerException exception) {
        if (exception.getClass() == AccountIsLockedException.class) {
            System.out.println("Слишком много ошибок, попробуйте позже");
        } else if (exception.getClass() == WrongPasswordExeption.class) {
            System.out.println("Неправильный пароль");
        } else if (exception.getClass() == NoAuthorizeException.class) {
            System.out.println("Вы не авторизованны");
        } else if (exception.getClass() == NotEnoughMoneyException.class) {
            System.out.println("Недостаточное количество денег");
        } else if (exception.getClass() == WrongAmountOfMoneyException.class) {
            System.out.println("Некоректное количество денег, должно быть положительное число кратное 100");
        } else {
            System.out.println("Неизвестная ошибка");
        }
    }

    private void parseExceptionValidator(RuntimeException exception) {
        if (exception.getClass() == NotCharException.class) {
            System.out.println("Введите только один символ и нажмите enter");
        } else if (exception.getClass() == NotNumberException.class) {
            System.out.println("Вводите только числа");
        } else {
            System.out.println("Неизвестная ошибка");
        }
    }


    @Override
    public void authorize() {
        Scanner scanner = new Scanner(System.in);
        String pass = "";
        System.out.println("Вводите пароль (по одному символу, пароль: 1234):");
        while (pass.length() < 4) {
            System.out.println(pass);
            String symb = scanner.next();
            try {
                if (pinValidator.validate(symb)) {
                    pass += symb;
                }
            } catch (RuntimeException e) {
                parseExceptionValidator(e);
            }
        }
        try {
            terminal.tryAuthorize(pass);
        } catch (TerminalServerException e) {
            parseException(e);
        }
    }

    @Override
    public void checkMoney() {
        try {
            int m = terminal.checkMoney();
            System.out.println("У вас на счету: " + m);
        } catch (TerminalServerException e) {
            parseException(e);
        }
    }

    @Override
    public void getMoney() {
        try {
            System.out.println("Введите сумму для снятия: ");
            Scanner scanner = new Scanner(System.in);
            int amount = scanner.nextInt();
            int m = terminal.getMoney(amount);
            System.out.println("Вам выданно: " + m);
        } catch (TerminalServerException e) {
            parseException(e);
        }
    }

    @Override
    public void addMoney() {
        try {
            System.out.println("Введите сумму для зачисления: ");
            Scanner scanner = new Scanner(System.in);
            int amount = scanner.nextInt();
            terminal.addMoney(amount);
            System.out.println("Вам на счет записанна сумма");
        } catch (TerminalServerException e) {
            parseException(e);
        }
    }
}
