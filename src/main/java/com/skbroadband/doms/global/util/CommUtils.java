package com.skbroadband.doms.global.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.global.util
 * @File : CommUtils
 * @Program :
 * @Date : 2022-12-26
 * @Comment :
 */
@Slf4j
public class CommUtils {
    public static boolean continueCharactersCheck(String password) {
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String number = "0123456789";

        String reversUpperCase = "ZYXWVUTSRQPONMLKJIHGFEDCBA";
        String reversLowerCase = "zyxwvutsrqponmlkjihgfedcba";
        String reversNumber = "9876543210";

        boolean continueCharactersCheck = false;

        for (int i = 0; i < password.length() - 2; i += 1) {
            if (upperCase.contains(password.substring(i, i + 3))) {
                continueCharactersCheck = true;
                break;
            }
            if (lowerCase.contains(password.substring(i, i + 3))) {
                continueCharactersCheck = true;
                break;
            }
            if (number.contains(password.substring(i, i + 3))) {
                continueCharactersCheck = true;
                break;
            }

            if (reversUpperCase.contains(password.substring(i, i + 3))) {
                continueCharactersCheck = true;
                break;
            }
            if (reversLowerCase.contains(password.substring(i, i + 3))) {
                continueCharactersCheck = true;
                break;
            }
            if (reversNumber.contains(password.substring(i, i + 3))) {
                continueCharactersCheck = true;
                break;
            }
        }
        return continueCharactersCheck;
    }
}
