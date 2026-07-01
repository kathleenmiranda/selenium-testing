package app.vercel.northwind.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtil {

    private static final int TEMPO = 10;

    public static WebElement esperarElementoVisivel(WebDriver driver, By by) {

        return new WebDriverWait(driver, Duration.ofSeconds(TEMPO))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static WebElement esperarElementoClicavel(WebDriver driver, By by) {

        return new WebDriverWait(driver, Duration.ofSeconds(TEMPO))
                .until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void esperarUrlContem(WebDriver driver, String texto) {

        new WebDriverWait(driver, Duration.ofSeconds(TEMPO))
                .until(ExpectedConditions.urlContains(texto));
    }

    public static boolean esperarElementoDesaparecer(WebDriver driver, By by) {

        return new WebDriverWait(driver, Duration.ofSeconds(TEMPO))
                .until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

}