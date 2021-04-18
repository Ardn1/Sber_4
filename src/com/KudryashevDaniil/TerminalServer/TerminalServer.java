package com.KudryashevDaniil.TerminalServer;

public interface TerminalServer {
    Request authorize(String pass);
    Request checkMoney(String token);
    Request getMoney(String token, Integer amount);
    Request addMoney(String token, Integer amount);
}
