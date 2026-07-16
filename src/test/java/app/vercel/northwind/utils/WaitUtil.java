package app.vercel.northwind.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtil {

    private static final Duration TEMPO_ESPERA = Duration.ofSeconds(10);


    private static WebDriverWait getWait(WebDriver driver) {
        return new WebDriverWait(driver, TEMPO_ESPERA);
    }

    public static WebElement esperarElementoVisivel(WebDriver driver, By locator) {
        return getWait(driver).until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public static WebElement esperarElementoClicavel(WebDriver driver, By locator) {
        return getWait(driver).until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    public static void esperarUrlContem(WebDriver driver, String url) {
        getWait(driver).until(
                ExpectedConditions.urlContains(url)
        );
    }

    public static boolean esperarElementoDesaparecer(WebDriver driver, By by) {

        return getWait(driver).until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public static boolean esperarTextoNoElemento(WebDriver driver, By by, String texto) {

        return getWait(driver).until(
                ExpectedConditions.textToBePresentInElementLocated(by, texto)
        );
    }

    public static void esperarQuantidadeDeJanelas(WebDriver driver, int quantidade) {
        getWait(driver).until(
                ExpectedConditions.numberOfWindowsToBe(quantidade)
        );
    }

}