package com.soucod.jfx2.samples.learn.fxmllogindemo.security;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:  此类描述
 * Author: LuDaShi
 * Date: 2021-01-13-16:20
 * UpdateDate: 2021-01-13-16:20
 * FileName: Authenticator
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */
public class Authenticator {

    private static final Map<String, String> USERS = new HashMap<String, String>();

    static {
        USERS.put("demo", "demo");
    }

    public static boolean validate(String user, String password) {
        String validUserPassword = USERS.get(user);
        return validUserPassword != null && validUserPassword.equals(password);
    }
}