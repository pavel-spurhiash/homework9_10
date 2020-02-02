package com.gmail.pashasimonpure.controller.constant;

public interface RegexConstant {

    String USER_NAME_REGEX = "^[A-Za-z0-9_-]{3,40}$";
    String PASSWORD_REGEX = "^[A-Za-z0-9!@#$%^&*]{5,40}$";
    String AGE_REGEX = "^[0-9]{1,2}$";
    String ADDRESS_REGEX = "^[A-Za-z0-9_-]{5,100}$";
    String PHONE_NUMBER_REGEX = "^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$";

}
