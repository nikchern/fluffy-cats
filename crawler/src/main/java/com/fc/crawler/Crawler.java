package com.fc.crawler;

import com.fc.browser.Browser;
import com.fc.util.UtilConst;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Crawler {

    static final WebDriver driver = Browser.CreateDriver();

    public static void main(String [] args) {


        driver.get(UtilConst.SEARCH_STR);
        driver.findElement(UtilConst.IMAGES_MODE_LINK).click();

        for (WebElement element : driver.findElements(By.tagName("a"))) {
            getParamValueByParamName(element.getAttribute("href"), UtilConst.IMG_URL);
        }

    }

    public static String getParamValueByParamName(String url, String paramName)
    {
        if (url == null) return null;
        String[] params = url.split("&");
        for (String param : params)
        {
            if (paramName.equals(param.split("=")[0]) && (param.split("=")[1] != null)) {
                System.out.println(param.split("=")[1]);
                return param.split("=")[1];
            }
        }
        return null;
    }
}
