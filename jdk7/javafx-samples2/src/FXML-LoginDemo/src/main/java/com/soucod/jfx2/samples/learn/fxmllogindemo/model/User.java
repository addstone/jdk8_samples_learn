package com.soucod.jfx2.samples.learn.fxmllogindemo.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:  此类描述
 * Author: LuDaShi
 * Date: 2021-01-13-16:20
 * UpdateDate: 2021-01-13-16:20
 * FileName: User
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */

public class User {

    private static final Map<String, User> USERS = new HashMap<String, User>();

    public static User of(String id) {
        User user = USERS.get(id);
        if (user == null) {
            user = new User(id);
            USERS.put(id, user);
        }
        return user;
    }

    private User(String id) {
        this.id = id;
    }

    private String id;

    public String getId() {
        return id;
    }

    private String email = "";
    private String phone = "";
    private boolean subscribed;
    private String address = "";

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the subscribed
     */
    public boolean isSubscribed() {
        return subscribed;
    }

    /**
     * @param subscribed the subscribed to set
     */
    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
