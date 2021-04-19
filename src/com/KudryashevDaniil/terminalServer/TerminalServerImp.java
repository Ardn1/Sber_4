package com.KudryashevDaniil.terminalServer;

import java.sql.Timestamp;
import java.time.Instant;

public class TerminalServerImp implements TerminalServer {
    private String token = java.util.UUID.randomUUID().toString();
    private String pass = "1234";
    private int wrongPassCount = 0;
    private Timestamp wrongPassTime = Timestamp.from(Instant.now());
    private Integer money = 0;

    private String generateToken() {
        token = java.util.UUID.randomUUID().toString();
        return token;
    }

    @Override
    public Request authorize(String pass) {
        if (wrongPassCount >= 3) {
            if ((Timestamp.from(Instant.now()).getTime() - wrongPassTime.getTime()) >= 10 * 1000) {
                wrongPassCount = 0;
            } else {
                return new Request(false, "Account Is Locked");
            }
        }
        if (this.pass.equals(pass)) {
            token = generateToken();
            return new Request(true, token);
        } else {
            wrongPassCount++;
            if (wrongPassCount >= 3) {
                wrongPassTime = Timestamp.from(Instant.now());
            }
            return new Request(false, "Wrong Password");
        }
    }

    @Override
    public Request checkMoney(String token) {
        if (this.token.equals(token)) {
            return new Request(true, money.toString());
        } else {
            return new Request(false, "Not Authorize");
        }
    }

    @Override
    public Request getMoney(String token, Integer amount) {
        if (this.token.equals(token)) {
            if (amount % 100 != 0) {
                return new Request(false, "Wrong Amount Of Money");
            } else if (amount > money) {
                return new Request(false, "Not Enough Money");
            } else {
                money -= amount;
                return new Request(true, amount.toString());
            }
        } else {
            return new Request(false, "Not Authorize");
        }
    }

    @Override
    public Request addMoney(String token, Integer amount) {
        if (this.token.equals(token)) {
            if (amount < 0) {
                return new Request(false, "Wrong Operation");
            } else if (amount % 100 != 0) {
                return new Request(false, "Wrong Amount Of Money");
            } else {
                money += amount;
                return new Request(true, money.toString());
            }
        } else {
            return new Request(false, "Not Authorize");
        }
    }
}
