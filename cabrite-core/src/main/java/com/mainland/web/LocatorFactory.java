package com.mainland.web;

import com.mainland.util.Helper;
import com.mainland.util.IProp;
import com.mainland.util.Strs;
import com.mainland.web.pojo.Locator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;

public class LocatorFactory {
    private static final Logger logger = LoggerFactory.getLogger(LocatorFactory.class);
    private List<Locator> locators;
    private String replacement_symbol="?";

    /*private LocatorFactory(List<Locator> locators) {
        this.locators = locators;
    }*/
    private LocatorFactory(InputStream inputStream){setLocators(inputStream);}
    private final static LocatorFactory locatorFactory_Meshviewer=new LocatorFactory(LocatorFactory.class.getClassLoader().getResourceAsStream(IProp.LOCATORS_MESHVIEWER));
    public static LocatorFactory Meshviewer(){
        return locatorFactory_Meshviewer;
    }

    public Locator getLocatorById(String id, String... replacements) {
        Locator locator=null;
        if(!Strs.isEmpty(id)){
            locator=(Locator) Helper.filterListById(locators,id);
            if(!Strs.isEmptyStrings(replacements)){
                Locator newLocator=new Locator(locator.getId(),locator.getType(),locator.getBy(),locator.getExpr(),locator.getDescription());
                String expr=locator.getExpr();
                StringBuffer buffer= new StringBuffer();
                for(int i=0;i< replacements.length;i++){
                    int index=expr.indexOf(replacement_symbol);
                    buffer.append(expr.substring(0,index)+replacements[i]);
                    expr=expr.substring(index+1);
                }
                buffer.append(expr);
                newLocator.setExpr(buffer.toString());
                logger.debug(newLocator.toString());
                return newLocator;
            }
        }
        return locator;
    }

    /*private List<Locator> filterLocators(List<Locator> locators, String id){
        if(id.contains(".")){
            String prefix=id.substring(0,id.indexOf("."));
            Helper.filterListById(locators,prefix)
        }
        return null;
    }*/
    public List<Locator> getLocators() {
        return locators;
    }

    public void setLocators(List<Locator> locators) {
        this.locators = locators;
    }

    public void setLocators(InputStream inputStream){
        List<Locator> locators=Helper.loadJson(inputStream,Locator.class);
        setLocators(locators);
    }

}
