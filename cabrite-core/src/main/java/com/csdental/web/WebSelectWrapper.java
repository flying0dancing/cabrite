package com.csdental.web;

import com.csdental.web.pojo.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Quotes;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class WebSelectWrapper implements IWebSelectWrapper{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private WebDriverWrapper webDriverWrapper;
    private WebDriver driver;
    private Wait<WebDriver> wait;
    private Locator locator;
    private WebElement element;
    private By by;
    private String tagName;
    private boolean isMulti;
    private Map map=new HashMap<>();

    public WebSelectWrapper(WebDriverWrapper webDriverWrapper, Locator locator) {
        this.webDriverWrapper=webDriverWrapper;
        this.driver=webDriverWrapper.getDriver();
        this.wait=webDriverWrapper.getWait();
        this.locator=locator;
        this.by=locator.getBy(locator.getId());
        this.element=element();
        map.put("select","option");
        map.put("ul","li");
        select();
    }

    private WebElement element(){
        logger.debug("locator {}",locator);
        List<WebElement> elts=driver.findElements(by);
        if(elts==null || elts.size()==0){
            return null;
        }
        return wait.until(driver->{
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        });
    }
    private void select() {
        this.element = element();
        tagName = element.getTagName();
        if (null == tagName ) {
            throw new UnexpectedTagNameException("select or ul", tagName);
        }
        if (!map.containsKey(tagName.toLowerCase())) {
            throw new UnexpectedTagNameException(tagName.toLowerCase(), tagName);
        }
        String value = element.getAttribute("multiple");

        // The atoms normalize the returned value, but check for "false"
        isMulti = (value != null && !"false".equals(value));
    }

    private List<WebElement> getOptions1(){
        return this.element.findElements(By.tagName(tagName));
    }
    public List<IWebElementWrapper> getOptions(){
        List<WebElement> elts=this.element.findElements(By.tagName(tagName));
        List<IWebElementWrapper> iWebElementWrappers=new ArrayList<>();
        for(WebElement elt:elts){
            iWebElementWrappers.add(webDriverWrapper.element(elt));
        }
        return iWebElementWrappers;
    }

    public List<IWebElementWrapper> getAllSelectedOptions(){
        return this.getOptions().stream().filter(IWebElementWrapper::isSelected).collect(Collectors.toList());
    }

    public IWebElementWrapper getFirstSelectedOption(){
        return this.getOptions().stream().filter(IWebElementWrapper::isSelected).findFirst().orElseThrow(() -> new NoSuchElementException("No options are selected"));
    }

    public void selectByIndex(int index){
        setSelectedByIndex(index,true);
    }
    private void setSelectedByIndex(int index, boolean select) {
        String match = String.valueOf(index);

        for (IWebElementWrapper option : getOptions()) {
            if (match.equals(option.getAttribute("index"))) {
                setSelected(option, select);
                return;
            }
        }
        throw new NoSuchElementException("Cannot locate option with index: " + index);
    }
    private void setSelected(IWebElementWrapper option, boolean select) {
        if (option.isSelected() != select) {
            option.click();
        }
    }
    public void selectByValue(String value){
        for (IWebElementWrapper option : findOptionsByValue(value)) {
            setSelected(option, true);
            if (!isMultiple()) {
                return;
            }
        }
    }
    private List<IWebElementWrapper> findOptionsByValue(String value) {
        List<WebElement> options = element.findElements(By.xpath(
                ".//"+map.getOrDefault(tagName,"option")+"[@value = " + Quotes.escape(value) + "]"));
        if (options.isEmpty()) {
            throw new NoSuchElementException("Cannot locate option with value: " + value);
        }
        List<IWebElementWrapper> elts=new ArrayList<>();
        for(WebElement elt:options){
            elts.add(webDriverWrapper.element(elt));
        }
        return elts;
    }
    public void selectByVisibleText(String text){
        // try to find the option via XPATH ...
        List<WebElement> options =
                element.findElements(By.xpath(".//"+map.getOrDefault(tagName,"option")+"[normalize-space(.) = " + Quotes.escape(text) + "]"));
        for (WebElement option : options) {
            setSelected(webDriverWrapper.element(option), true);
            if (!isMultiple()) {
                return;
            }
        }

        boolean matched = !options.isEmpty();
        if (!matched && text.contains(" ")) {
            String subStringWithoutSpace = getLongestSubstringWithoutSpace(text);
            List<WebElement> candidates;
            if ("".equals(subStringWithoutSpace)) {
                // hmm, text is either empty or contains only spaces - get all options ...
                candidates = element.findElements(By.tagName(map.getOrDefault(tagName,"option").toString()));
            } else {
                // get candidates via XPATH ...
                candidates =
                        element.findElements(By.xpath(".//"+map.getOrDefault(tagName,"option")+"[contains(., " +
                                Quotes.escape(subStringWithoutSpace) + ")]"));
            }
            for (WebElement option : candidates) {
                if (text.equals(option.getText())) {
                    setSelected(webDriverWrapper.element(option), true);
                    if (!isMultiple()) {
                        return;
                    }
                    matched = true;
                }
            }
        }

        if (!matched) {
            throw new NoSuchElementException("Cannot locate element with text: " + text);
        }
    }
    private String getLongestSubstringWithoutSpace(String s) {
        String result = "";
        StringTokenizer st = new StringTokenizer(s, " ");
        while (st.hasMoreTokens()) {
            String t = st.nextToken();
            if (t.length() > result.length()) {
                result = t;
            }
        }
        return result;
    }

    @Override
    public Boolean isMultiple(){
        return this.isMulti;
    }
}
