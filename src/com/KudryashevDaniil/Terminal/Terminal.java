package com.KudryashevDaniil.Terminal;

public interface Terminal {
    void tryAuthorize(String pass);
    int checkMoney();
    int getMoney(int Amount);
    void addMoney(int Amount);
}
