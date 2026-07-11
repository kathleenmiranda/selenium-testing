package app.vercel.northwind.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {

    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void preencherNomeCompleto(String nome) {
        driver.findElement(
                By.cssSelector("[data-testid='full-name-input']")
        ).sendKeys(nome);
    }

    public void clicarNomeCompleto() {
        driver.findElement(By.cssSelector("[data-testid='full-name-input']"))
                .click();
    }
    public void preencherEmail(String email) {
        driver.findElement(
                By.cssSelector("[data-testid='email-input']")
        ).sendKeys(email);
    }

    public void apagarEmail() {
        driver.findElement(By.cssSelector("[data-testid='email-input']"))
                .sendKeys(Keys.BACK_SPACE);
    }

    public String obterMensagemErroEmail() {
        return driver.findElement(
                By.cssSelector("[data-testid='email-error']")
        ).getText();
    }

    public boolean mensagemErroEmailVisivel() {
        return driver.findElement(
                By.cssSelector("[data-testid='email-error']")
        ).isDisplayed();
    }


    public void preencherSenha(String senha) {
        driver.findElement(By.cssSelector("[data-testid='password-input']"))
                .sendKeys(senha);
    }

    public void apagarSenha() {
        driver.findElement(By.cssSelector("[data-testid='password-input']"))
                .sendKeys(Keys.BACK_SPACE);
    }

    public String obterMensagemErroSenha() {
        return driver.findElement(
                By.cssSelector("[data-testid='password-error']")
        ).getText();
    }

    public boolean mensagemErroSenhaVisivel() {
        return driver.findElement(
                By.cssSelector("[data-testid='password-error']")
        ).isDisplayed();
    }

    public void limparSenha(){
        driver.findElement(
                By.cssSelector("[data-testid='password-input']")
        ).clear();
    }

    public boolean mensagemErroSenhaNaoExiste() {
        return driver.findElements(
                By.cssSelector("[data-testid='password-error']")
        ).isEmpty();
    }

}
