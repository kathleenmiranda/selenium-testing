package app.vercel.northwind.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginUtil {

    public static void realizarLogin(WebDriver driver) {

        WebElement email = driver.findElement(By.name("email"));
        WebElement senha = driver.findElement(By.name("password"));
        WebElement botao = driver.findElement(By.cssSelector("[data-testid='login-button']"));

        email.sendKeys(TestData.EMAIL_VALIDO);
        senha.sendKeys(TestData.PASSWORD_VALIDO);
        botao.click();

        WaitUtil.esperarUrlContem(driver, "products");
    }

    public static void criarConta(WebDriver driver){

        WebElement cadastro = driver.findElement(
                By.cssSelector("a[href='/register']"));

        cadastro.click();

    }

}