package com.csdental.web.pojo;

import com.csdental.util.BuildStatus;
import com.google.gson.GsonBuilder;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Locator {
    private static final Logger logger = LoggerFactory.getLogger(Locator.class);
    private String id;
    private String type;
    private String by;
    private String expr;
    private String description;

    public Locator(String id, String type, String by, String expression) {
        this.id = id;
        this.type = type;
        this.by = by;
        this.expr = expression;
        this.description = "null description";
    }

    public Locator(String id, String type, String by, String expression, String description) {
        this.id = id;
        this.type = type;
        this.by = by;
        this.expr = expression;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getExpr() {
        return expr;
    }

    public void setExpr(String expr) {
        this.expr = expr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        try {
            return new GsonBuilder().disableHtmlEscaping().create().toJson(this);
        } catch (Exception e) {
            BuildStatus.getInstance().recordError();
            logger.error("Unable to display details of locator", e);
            return "";
        }
    }

    public By getBy(String id){
        By by=null;
        switch (getBy()){
            case "id":
                by=By.id(expr);
                break;
            case "name":
                by=By.name(expr);
                break;
            case "className":
                by=By.className(expr);
            case "cssSelector":
                by=By.cssSelector(expr);
            default:
                by=By.xpath(expr);
        };
        return by;
    }
}
