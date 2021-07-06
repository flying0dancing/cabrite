package com.mainland.web;

public interface IAlertWrapper {
    void dismiss();
    void acceptAlert();
    String getTextAlert();
    Boolean isPresent();
}
