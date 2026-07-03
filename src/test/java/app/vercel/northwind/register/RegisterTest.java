package app.vercel.northwind.register;

import app.vercel.northwind.base.BaseTest;
import app.vercel.northwind.utils.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static app.vercel.northwind.utils.LoginUtil.criarConta;
import static app.vercel.northwind.utils.ScreenshotUtil.capturar;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RegisterTest extends BaseTest {

    @Test()
    @DisplayName("Deve exibir mensagem de campo obrigatório para o e-mail")
    public void deveExibirMensagemQuandoEmailNaoForInformado() throws Exception {


        criarConta(driver);

        WebElement inputName = driver.findElement(By.cssSelector("[data-testid='full-name-input']"));

        inputName.sendKeys("Quality Assurance");

        WebElement inputEmail = driver.findElement(By.cssSelector("[data-testid='email-input']"));

        inputEmail.sendKeys("a");
        inputEmail.sendKeys(Keys.BACK_SPACE);
        inputName.click();

        WebElement mensagemErroEmail = driver.findElement(By.cssSelector("[data-testid='email-error']"));

        assertTrue(mensagemErroEmail.isDisplayed(),
                "A mensagem de erro do campo e-mail deveria estar visível.");

        Assertions.assertEquals(TestData.MSG_EMAIL_OBRIGATORIO, mensagemErroEmail.getText(),
                "A mensagem apresentada para o campo e-mail está incorreta.");
        capturar(driver, "form-email-obrigatorio");

    }

    @Test()
    @DisplayName("Deve exibir mensagem de e-mail inválido quando o formato for incorreto")
    public void deveExibirMensagemDeEmailInvalido() throws Exception {

        criarConta(driver);

        WebElement inputName = driver.findElement(By.cssSelector("[data-testid='full-name-input']"));

        inputName.sendKeys("Quality Assurance");

        WebElement inputEmail = driver.findElement(By.cssSelector("[data-testid='email-input']"));

        inputEmail.sendKeys("a");
        inputName.click();

        WebElement mensagemErroEmail = driver.findElement(By.cssSelector("[data-testid='email-error']"));

        assertTrue(mensagemErroEmail.isDisplayed(),
                "A mensagem de erro do campo e-mail deveria estar visível.");
        Assertions.assertEquals(TestData.MSG_EMAIL_INVALIDO, mensagemErroEmail.getText(),
                "A mensagem apresentada para o campo e-mail está incorreta.");

        capturar(driver, "form-email-invalido");
    }

    @Test
    @DisplayName("Deve exibir mensagem de campo obrigatorio para a senha")
    public void deveExibirMensagemDeSenhaObrigatorio() throws IOException {
        criarConta(driver);
        WebElement inputName = driver.findElement(By.cssSelector("[data-testid='full-name-input']"));

        inputName.sendKeys("Quality Assurance");

        WebElement inputSenha = driver.findElement(By.cssSelector("[data-testid='password-input']"));

        inputSenha.sendKeys("a");
        inputSenha.sendKeys(Keys.BACK_SPACE);
        inputName.click();

        WebElement mensagemErroPassword = driver.findElement(By.cssSelector("[data-testid='password-error']"));

        assertTrue(mensagemErroPassword.isDisplayed(),
                "A mensagem de erro do campo senha deveria estar visível.");

        assertEquals(TestData.MSG_SENHA_OBRIGATORIA, mensagemErroPassword.getText(),
                "A mensagem apresentada para o campo senha está incorreta.");

        capturar(driver, "form-senha-obrigatorio");
    }

    @Test
    @DisplayName("Deve ocultar a mensagem de erro ao informar uma senha válida")
    public void deveOcultarMensagemDeErroAoInformarSenhaValida() {

        criarConta(driver);

        WebElement inputSenha = driver.findElement(
                By.cssSelector("[data-testid='password-input']")
        );

        // Dado: existe um erro
        inputSenha.sendKeys("a");

        WebElement mensagemErro = driver.findElement(
                By.cssSelector("[data-testid='password-error']")
        );

        Assertions.assertTrue(
                mensagemErro.isDisplayed(),
                "A mensagem de erro deveria estar visível."
        );

        // Quando: informo uma senha válida
        inputSenha.clear();
        inputSenha.sendKeys("Senha!123");

        // Então: o erro desaparece
        Assertions.assertTrue(
                driver.findElements(By.cssSelector("[data-testid='password-error']")).isEmpty(),
                "A mensagem de erro deveria desaparecer após informar uma senha válida."
        );
    }
}
