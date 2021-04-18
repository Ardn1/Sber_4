package com.KudryashevDaniil.Terminal;

import com.KudryashevDaniil.TerminalServer.Request;
import com.KudryashevDaniil.TerminalServer.TerminalServerImp;
import com.KudryashevDaniil.TermoinalExepcions.*;
import com.KudryashevDaniil.Validator.PinValidator;


public class TerminalImp implements Terminal {
    private final TerminalServerImp server;
    {
        server = new TerminalServerImp();
    }

    private String token = "";

    private void parseException(String exception) throws TerminalServerException {
        if (exception.equals("Account Is Locked")) {
            throw new AccountIsLockedException();
        } else if (exception.equals("Wrong Password")) {
            throw new WrongPasswordExeption();
        } else if (exception.equals("Not Authorize")) {
            throw new NoAuthorizeException();
        } else if (exception.equals("Not Enough Money")) {
            throw new NotEnoughMoneyException();
        } else if (exception.equals("Wrong Operation")) {
            throw new WrongAmountOfMoneyException();
        } else if (exception.equals("Wrong Amount Of Money")) {
            throw new WrongAmountOfMoneyException();
        } else {
            throw new UnknownException();
        }
    }

    @Override
    public void tryAuthorize(String pass) throws TerminalServerException {
        Request request = server.authorize(pass);
        if (request.status) {
            token = request.data;
        } else {
            parseException(request.data);
        }
    }

    @Override
    public int checkMoney() throws TerminalServerException {
        Request request = server.checkMoney(token);
        if (request.status) {
            return Integer.parseInt(request.data);
        } else {
            parseException(request.data);
        }
        return 0;
    }

    @Override
    public int getMoney(int Amount) throws TerminalServerException {
        Request request = server.getMoney(token, Amount);
        if (request.status) {
            return Integer.parseInt(request.data);
        } else {
            parseException(request.data);
        }
        return 0;
    }

    @Override
    public void addMoney(int Amount) throws TerminalServerException {
        Request request = server.addMoney(token, Amount);
        if (!request.status) {
            parseException(request.data);
        }
    }
}
