package com.KudryashevDaniil.Validator;

import com.KudryashevDaniil.ValidatorExeptions.NotCharException;
import com.KudryashevDaniil.ValidatorExeptions.NotNumberException;

public class PinValidator {
    public boolean validate(String c) throws NotCharException, NotNumberException {
        if (c.length() != 1)
            throw new NotCharException();
        if (!Character.isDigit(c.charAt(0)))
            throw new NotNumberException();
        return true;
    }
}
