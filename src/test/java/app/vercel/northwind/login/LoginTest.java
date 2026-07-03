package app.vercel.northwind.login;

import app.vercel.northwind.base.BaseTest;

import app.vercel.northwind.page.LoginPage;
import app.vercel.northwind.utils.TestData;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

import static app.vercel.northwind.utils.ScreenshotUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginTest extends BaseTest {

    @Test()
    @DisplayName("Deve exibir mensagem ao tentar realizar login sem preencher os campos obrigatórios.")
    public void deveExibirMensagemAoTentarLoginSemPreencherCamposObrigatorios() throws IOException {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.realizarLogin(TestData.EMAIL_VAZIO, TestData.PASSWORD_VAZIO);

        assertTrue(
                loginPage.mensagemErroSenha().isDisplayed(),
                "A mensagem de erro deveria estar visível."
        );

        assertEquals(
                TestData.MSG_CAMPOS_OBRIGATORIOS,
                loginPage.mensagemErroSenha().getText(),
                "A mensagem exibida está incorreta."
        );

        capturar(driver, "campos-obrigatorios");
    }

    @Test()
    @DisplayName("Deve exibir mensagem ao tentar realizar login com e-mail inválido")
    public void deveExibirMensagemAoTentarLoginComEmailInvalido() throws Exception {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.realizarLogin(TestData.EMAIL_INVALIDO, TestData.PASSWORD_VALIDO);

        assertTrue(
                loginPage.mensagemErroEmail().isDisplayed(),
                "A mensagem de erro deveria estar visível.");

        Assertions.assertEquals(
                TestData.MSG_FORMATO_EMAIL_INVALIDO, loginPage.mensagemErroEmail().getText(),
                "A mensagem exibida está incorreta.");

        capturar(driver, "email-invalido");
    }

    @Test()
    public void testeTentarAcessoComSenhaCurta() throws Exception {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.realizarLogin(
                TestData.EMAIL_VALIDO, TestData.PASSWORD_CURTO);

       assertTrue(
               loginPage.mensagemErroEmail().isDisplayed(),
                "A mensagem de erro deveria estar visível.");

        Assertions.assertEquals(
                TestData.MSG_SENHA_CURTA,
                loginPage.mensagemErroEmail().getText(),
                "A mensagem exibida está incorreta.");

        capturar(driver, "senha-curta");
    }

    @Test()
    public void testeValidarAcessoComEmailNaoCadastrado() throws Exception {
        LoginPage  loginPage = new LoginPage(driver);

        loginPage.realizarLogin(
                TestData.EMAIL_INEXISTENTE, TestData.PASSWORD_VALIDO);

        assertTrue(
                loginPage.mensagemErroEmail().isDisplayed());

        Assertions.assertEquals(
                TestData.MSG_USUARIO_NAO_ENCONTRADO,
                loginPage.mensagemErroEmail().getText());

        capturar(
                driver, "usurio_nao_cadastrado");
    }

    @Test()
    public void testeTentarAcessoComSenhaInexistente() throws Exception {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.realizarLogin(TestData.EMAIL_VALIDO, TestData.PASSWORD_INVALIDO);

        assertTrue(
                loginPage.mensagemErroSenha().isDisplayed());

        assertEquals(
                TestData.MSG_EMAIL_SENHA_INVALIDOS,
                loginPage.mensagemErroSenha().getText());

        capturar(driver, "senha-inexistente");
    }


    @Test()
    @DisplayName("Deve realizar Login com sucesso")
    public void deveRealizarLoginComSucesso() throws Exception {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.realizarLogin(TestData.EMAIL_VALIDO, TestData.PASSWORD_VALIDO);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("https://northwind-test-platform.vercel.app/products"));

        assertEquals("https://northwind-test-platform.vercel.app/products",
                driver.getCurrentUrl());

        capturar(driver, "acesso-valido");
    }

    @Test()
    public void testeValidarAcessoComApenasEmailPreenchido() throws IOException {
        WebElement inputEmail = driver.findElement(By.name("email"));
        WebElement inputPassword = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));

        inputEmail.sendKeys(TestData.EMAIL_VALIDO);
        inputPassword.click();
        button.click();

        WebElement mensagem = driver.findElement(By.cssSelector("[data-testid='password-error']"));

        assertTrue(mensagem.isDisplayed());
        Assertions.assertEquals(TestData.MSG_CAMPOS_OBRIGATORIOS, mensagem.getText());

        capturar(driver, "campos-obrigatorios");
    }


}
