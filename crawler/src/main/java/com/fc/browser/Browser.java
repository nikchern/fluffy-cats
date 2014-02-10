package com.fc.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum Browser {

    FIREFOX,

    NULL;

    static Browser getDefault() {
        return FIREFOX;
    }

    public static WebDriver CreateDriver() {

        switch (getDefault()) {
            case FIREFOX:
                return new FirefoxDriver();
            case NULL:
                throwBrowserException();
        }
        return null;
    }

    private static WebDriver throwBrowserException() {
        throw new RuntimeException("Browser parameter(even default) is not found or is not correct!");
    }
}

