package com.panda.auth.user.services.validation;

import com.panda.auth.exceptions.InvalidUserDataException;
import com.google.common.base.Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Validates international phone numbers based on industry-standard notation specified by ITU-T E.123
// https://howtodoinjava.com/regex/java-regex-validate-international-phone-numbers/
public class PhoneValidator {

    private static int MAX_PHONE_LENGTH = 50;

    private static final String PHONE_REGEX = "^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$";

    private Pattern pattern;

    public PhoneValidator() {
        pattern = Pattern.compile(PHONE_REGEX);
    }

    public void checkPhone(String phone) {
        if (Strings.isNullOrEmpty(phone)) {
            throw new InvalidUserDataException("The phone cannot be null or empty");
        }

        // check max phone length
        if (phone.length() > MAX_PHONE_LENGTH) {
            throw new InvalidUserDataException(String.format("The phone is too long: max number of chars is %s",
                    MAX_PHONE_LENGTH));
        }

        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            throw new InvalidUserDataException(String.format("The phone provided %s is not formal valid", phone));
        }
    }

}
