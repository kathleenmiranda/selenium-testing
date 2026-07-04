package app.vercel.northwind.page;

import app.vercel.northwind.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public boolean mensagemErroEmailEstaVisivel() {
        return driver.findElement(
                By.cssSelector("[data-testid='email-error']")
        ).isDisplayed();
    }
    public String obterMensagemErroEmail() {
        return driver.findElement(
                By.cssSelector("[data-testid='email-error']")
        ).getText();
    }

    public boolean mensagemErroSenhaEstaVisivel() {
        return driver.findElement(
                By.cssSelector("[data-testid='password-error']")
        ).isDisplayed();
    }

    public String obterMensagemErroSenha() {
        return driver.findElement(
                By.cssSelector("[data-testid='password-error']")
        ).getText();
    }

    public void realizarLogin(String email, String senha) {
        preencherEmail(email);
        preencherSenha(senha);
        clicarEntrar();
    }

    public void abrirCadastro() {
        driver.findElement(
                By.cssSelector("a[href='/register']")
        ).click();

        WaitUtil.esperarUrlContem(driver, "register");

    }


}

