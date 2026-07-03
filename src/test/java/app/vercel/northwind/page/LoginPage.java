package app.vercel.northwind.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {


    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void preencherEmail(String email) {
        driver.findElement(By.name("email"))
                .sendKeys(email);
    }

    public void preencherSenha(String senha) {
        driver.findElement(By.name("password"))
                .sendKeys(senha);
    }

    public void clicarEntrar() {
        driver.findElement(By.cssSelector("[data-testid='login-button']"))
                .click();
    }

    public WebElement mensagemErroSenha() {
        return driver.findElement(
                By.cssSelector("[data-testid='password-error']")
        );
    }

    public WebElement mensagemErroEmail() {
        return driver.findElement(
                By.cssSelector("[data-testid='email-error']")
        );
    }

    public void realizarLogin(String email, String senha) {
        preencherEmail(email);
        preencherSenha(senha);
        clicarEntrar();
    }

    public void abrirCadastro() {
        driver.findElement(By.cssSelector("a[href='/register']")).click();
    }
}

