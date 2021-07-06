package com.mainland.web;

import java.util.List;

public interface IWebSelectWrapper {
    List<IWebElementWrapper> getOptions();
    List<IWebElementWrapper> getAllSelectedOptions();
    IWebElementWrapper getFirstSelectedOption();
    void selectByIndex(int index);
    void selectByValue(String value);
    void selectByVisibleText(String value);
    /**
     * Returns:
     * Whether this select element support selecting multiple options at the same time? This is done by checking the value of the "multiple" attribute.
     * @return
     */
    Boolean isMultiple();
}
