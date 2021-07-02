package com.csdental.web;

public interface IAlertWrapper {
    void dismiss();
    void acceptAlert();
    String getTextAlert();
    Boolean isPresent();
}
