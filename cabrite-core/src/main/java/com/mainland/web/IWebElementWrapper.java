package com.mainland.web;


import java.util.List;

public interface IWebElementWrapper {
    Boolean isPresent();
    Boolean isEnabled();
    Boolean isDisplayed();
    Boolean isSelected();
    List<IWebElementWrapper> findElements();
    String getAttribute(String var1);
    void sendKeys(CharSequence... keyToSend);
    void click();
    String getText();
    String takeScreenshot(String name);
    String takeScreenshot();
    int getWidth();
    int getHeight();
    int getX();
    int getY();
    void mouse_move(int xStart, int yStart);
    void mouse_move(int xStart, int yStart, int xOffset, int yOffset);
    void mouse_click(int x, int y);
    void selectByVisibleText(String value);
    void selectByValue(String value);
    Boolean elementExists();
}
