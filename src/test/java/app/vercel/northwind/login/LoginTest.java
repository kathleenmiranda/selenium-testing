package app.vercel.northwind.login;

import app.vercel.northwind.base.BaseTest;
import app.vercel.northwind.utils.ScreenshotUtil;
import app.vercel.northwind.utils.TestData;

import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

import static app.vercel.northwind.utils.LoginUtil.*;


public class LoginTest extends BaseTest {

        @Test()
        @DisplayName("Deve exibir mensagem ao tentar realizar login sem preencher os campos obrigatórios.")
        public void deveExibirMensagemAoTentarLoginSemPreencherCamposObrigatorios() throws IOException {
            WebElement emailInput = driver.findElement(By.name("email"));
            WebElement inputPassword = driver.findElement(By.name("password"));
            WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));

            emailInput.click();
            inputPassword.click();
            button.click();

            WebElement mensagem =
                    driver.findElement(By.cssSelector("[data-testid='password-error']"));

            Assertions.assertTrue(mensagem.isDisplayed(),
                    "A mensagem de erro deveria estar visível.");
            Assertions.assertEquals(TestData.MSG_CAMPOS_OBRIGATORIOS, mensagem.getText(),
                    "A mensagem exibida está incorreta.");

            ScreenshotUtil.capturar(driver, "campos-obrigatorios");
        }

    @Test()
    @DisplayName("Deve exibir mensagem ao tentar realizar login com e-mail inválido")
    public void deveExibirMensagemAoTentarLoginComEmailInvalido() throws Exception {
        WebElement emailInput = driver.findElement(By.name("email"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

        emailInput.sendKeys(TestData.EMAIL_INVALIDO);
        passwordInput.sendKeys(TestData.PASSWORD_VALIDO);
        loginButton.click();

        WebElement emailErrorMessage  = driver.findElement(
                By.cssSelector("[data-testid='email-error']"));

        Assertions.assertTrue(
                emailErrorMessage .isDisplayed(),
                "A mensagem de erro deveria estar visível.");
        Assertions.assertEquals(
                TestData.MSG_FORMATO_EMAIL_INVALIDO, emailErrorMessage .getText(),
                "A mensagem exibida está incorreta.");

        ScreenshotUtil.capturar(driver, "email-invalido");
    }

    @Test()
    public void testeTentarAcessoComSenhaCurta() throws Exception {
        WebElement emailInput = driver.findElement(By.name("email"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

        emailInput.sendKeys(TestData.EMAIL_VALIDO);
        passwordInput.sendKeys(TestData.PASSWORD_CURTO);
        loginButton.click();

        WebElement mensagem = driver.findElement(By.cssSelector("[data-testid='email-error']"));

        Assertions.assertTrue(mensagem.isDisplayed(), "A mensagem de erro deveria estar visível.");
        Assertions.assertEquals(TestData.MSG_SENHA_CURTA, mensagem.getText(),"A mensagem exibida está incorreta.");

        ScreenshotUtil.capturar(driver, "senha-curta");
    }

    @Test()
    public void testeValidarAcessoComEmailNaoCadastrado() throws Exception {
        WebElement emailInput = driver.findElement(By.name("email"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

        emailInput.sendKeys(TestData.EMAIL_INEXISTENTE);
        passwordInput.sendKeys(TestData.PASSWORD_VALIDO);
        loginButton.click();

        WebElement mensagem = driver.findElement(By.cssSelector("[data-testid='email-error']"));

        Assertions.assertTrue(mensagem.isDisplayed());
        Assertions.assertEquals(TestData.MSG_USUARIO_NAO_ENCONTRADO, mensagem.getText());

        ScreenshotUtil.capturar(driver, "usurio_nao_cadastrado");
    }

    @Test()
    public void testeTentarAcessoComSenhaInexistente() throws Exception {
        WebElement inputEmail = driver.findElement(By.name("email"));
        WebElement inputPassword = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));

        inputEmail.sendKeys(TestData.EMAIL_VALIDO);
        inputPassword.sendKeys(TestData.PASSWORD_INVALIDO);
        button.click();

        WebElement mensagem = driver.findElement(By.cssSelector("[data-testid='password-error']"));
        Assertions.assertTrue(mensagem.isDisplayed());

        Assertions.assertEquals(TestData.MSG_EMAIL_SENHA_INVALIDOS, mensagem.getText() );
        ScreenshotUtil.capturar(driver, "senha-inexistente");
    }


    @Test()
    public void testeValidarAcessoComDadosValidos() throws Exception {
        WebElement inputEmail = driver.findElement(By.name("email"));
        WebElement inputPassword = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));

        inputEmail.sendKeys(TestData.EMAIL_VALIDO);
        inputPassword.sendKeys(TestData.PASSWORD_VALIDO);
        button.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.urlToBe("https://northwind-test-platform.vercel.app/products"));

        Assertions.assertEquals("https://northwind-test-platform.vercel.app/products",
                driver.getCurrentUrl());
        ScreenshotUtil.capturar(driver, "acesso-valido");
    }

    @Test()
    public void testeValidarAcessoComApenasEmailPreenchido() {
        WebElement inputEmail = driver.findElement(By.name("email"));
        WebElement inputPassword = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));

        inputEmail.sendKeys(TestData.EMAIL_VALIDO);
        inputPassword.click();
        button.click();

        WebElement mensagem =  driver.findElement(By.cssSelector("[data-testid='password-error']"));

        Assertions.assertTrue(mensagem.isDisplayed());
        Assertions.assertEquals(TestData.MSG_CAMPOS_OBRIGATORIOS, mensagem.getText());
    }

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

        Assertions.assertTrue(mensagemErroEmail.isDisplayed(),
                "A mensagem de erro do campo e-mail deveria estar visível.");

        Assertions.assertEquals(TestData.MSG_EMAIL_OBRIGATORIO, mensagemErroEmail.getText(),
                "A mensagem apresentada para o campo e-mail está incorreta.");


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

        Assertions.assertTrue(mensagemErroEmail.isDisplayed(),
                "A mensagem de erro do campo e-mail deveria estar visível.");
        Assertions.assertEquals(TestData.MSG_EMAIL_INVALIDO, mensagemErroEmail.getText(),
                "A mensagem apresentada para o campo e-mail está incorreta.");
    }

    @Test()
    public void testeTentarCriarContaComEmailInvalido() throws Exception {

        driver.findElement(By.linkText("Cadastre-se")).click();
        driver.findElement(By.id("full_name")).sendKeys("Kathleen Miranda");
        driver.findElement(By.id("email")).sendKeys("teste.teste");
        driver.findElement(By.id("password")).sendKeys("Teste@123");
        driver.findElement(By.id("confirmPassword")).sendKeys("Teste@123");
        driver.findElement(By.cssSelector("[data-testid='register-button']")).click();

        Assertions.assertTrue(
                driver.findElement(By.cssSelector("[data-testid='email-error']")).isDisplayed()
        );
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("kat@teste.com");
        driver.findElement(By.cssSelector("[data-testid='register-button']")).click();

        Assertions.assertTrue(
                driver.findElements(By.cssSelector("[data-testid='email-error']")).isEmpty());
    }


//    @Test()
//    public void testeTentarCriarContaSemPreencherSenha() throws Exception {
//
//        WebElement textCadrastese = driver.findElement(By.linkText("Cadastre-se"));
//        WebElement inputName = driver.findElement(By.id("full_name"));
//        WebElement inputEmail = driver.findElement(By.id("email"));
//        WebElement inputPassword = driver.findElement(By.name("password"));
//        WebElement inputConfirmPassword = driver.findElement(By.name("confirm_password"));
//        WebElement registerButton = driver.findElement(By.cssSelector("[data-testid='register-button']"));
//
//        textCadrastese.click();
//        inputName.sendKeys("Kathleen Miranda");
//        inputEmail.sendKeys("kat@teste.com");
//        inputPassword.click();
//        inputConfirmPassword.sendKeys("Teste@123");
//        registerButton.click();
//
//        WebElement mensagem = driver.findElement(By.cssSelector("[data-testid='email-error']"));
//
//        Assertions.assertTrue(mensagem.isDisplayed());
////        Assertions.assertEquals();
//
//        driver.findElement(By.id("email")).clear();
//        driver.findElement(By.id("email")).sendKeys("kat@teste.com");
//        driver.findElement(By.cssSelector("[data-testid='register-button']")).click();
//
//        Assertions.assertTrue(
//                driver.findElements(By.cssSelector("[data-testid='email-error']")).isEmpty());
//    }
//
//    /** CEN-11 - Campo Senha válido
//     * Dado que existe um erro de validação da Senha
//     * Quando informa uma senha válida
//     * Então a mensagem de erro deve desaparecer.
//     * */
//    @Test()
//    public void testeTentarCriarContaComSSenhaInvalida() throws Exception {
//
//        WebElement textCadrastese = driver.findElement(By.linkText("Cadastre-se"));
//        WebElement inputName = driver.findElement(By.id("full_name"));
//        WebElement inputEmail = driver.findElement(By.id("email"));
//        WebElement inputPassword = driver.findElement(By.name("password"));
//        WebElement inputConfirmPassword = driver.findElement(By.name("confirm_password"));
//        WebElement registerButton = driver.findElement(By.cssSelector("[data-testid='register-button']"));
//
//        textCadrastese.click();
//        inputName.sendKeys("Kathleen Miranda");
//        inputEmail.sendKeys("kat@teste.com");
//        inputPassword.click();
//        inputConfirmPassword.sendKeys("Teste@123");
//        registerButton.click();
//
//        WebElement mensagem = driver.findElement(By.cssSelector("[data-testid='email-error']"));
//
//        Assertions.assertTrue(mensagem.isDisplayed());
////        Assertions.assertEquals();
//
//        driver.findElement(By.id("email")).clear();
//        driver.findElement(By.id("email")).sendKeys("kat@teste.com");
//        driver.findElement(By.cssSelector("[data-testid='register-button']")).click();
//
//        Assertions.assertTrue(
//                driver.findElements(By.cssSelector("[data-testid='email-error']")).isEmpty());
//    }

}
