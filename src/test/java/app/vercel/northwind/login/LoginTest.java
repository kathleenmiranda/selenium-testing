package app.vercel.northwind.login;

import app.vercel.northwind.base.BaseTest;
import app.vercel.northwind.utils.ScreenshotUtil;
import app.vercel.northwind.utils.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;


public class LoginTest extends BaseTest {

        /** CEN-01 - Login sem credenciais
        * Dado que o usuário está na página de login
        * Quando deixa os campos Email e Senha vazios e clica em "Entrar"
        * Então o sistema deve impedir o login
        * E exibir "Email e senha são obrigatórios"
        * E permanecer na tela de login
        */
        @Test()
        public void testeTentarAcessoSemPreenchimentoDosCamposEmailESenha() throws IOException {            WebElement inputEmail = driver.findElement(By.name("email"));
            WebElement inputPassword = driver.findElement(By.name("password"));
            WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));

            inputEmail.click();
            inputPassword.click();
            button.click();

            WebElement mensagem =  driver.findElement(By.cssSelector("[data-testid='password-error']"));

            Assertions.assertTrue(mensagem.isDisplayed());
            Assertions.assertEquals(TestData.MSG_CAMPOS_OBRIGATORIOS, mensagem.getText());

            ScreenshotUtil.capturar(driver, "senha-incorreta");
        }

        /** CEN-02 - Email inválido
         * Dado que o usuário está na página de login
         * Quando informa um email inválido
         * E informa uma senha válida
         * Então o sistema deve exibir
         * "Formato de email inválido. Use: nome@dominio.com".
         * */
    @Test()
    public void testeTentarAcessoComEmailInvalido() throws Exception {
        WebElement inputEmail = driver.findElement(By.name("email"));
        WebElement inputPassword = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));

        inputEmail.sendKeys(TestData.EMAIL_INVALIDO);
        inputPassword.sendKeys(TestData.PASSWORD_VALIDO);
        button.click();

        WebElement mensagem = driver.findElement(By.cssSelector("[data-testid='email-error']"));

        Assertions.assertTrue(mensagem.isDisplayed());
        Assertions.assertEquals(
                TestData.MSG_FORMATO_EMAIL_INVALIDO, mensagem.getText());

        ScreenshotUtil.capturar(driver, "email-invalido");
    }

    /** CEN-03 - Senha curta
    * Dado que o usuário está na página de login
    * Quando digita uma senha com menos de 6 caracteres
    * Então deve ser exibida
    * "Senha deve ter pelo menos 6 caracteres".
    */
    @Test()
    public void testeTentarAcessoComSenhaCurta() throws Exception {
        WebElement inputEmail = driver.findElement(By.name("email"));
        WebElement inputPassword = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));

        inputEmail.sendKeys(TestData.EMAIL_VALIDO);
        inputPassword.sendKeys(TestData.PASSWORD_INVALIDO);
        button.click();

        WebElement mensagem = driver.findElement(By.cssSelector("[data-testid='email-error']"));

        Assertions.assertTrue(mensagem.isDisplayed());
        Assertions.assertEquals(TestData.MSG_SENHA_CURTA, mensagem.getText());

        ScreenshotUtil.capturar(driver, "senha-curta");
    }

    /** CEN-04 - Usuário inexistente
     * Dado que o usuário está na página de login
     * Quando informa um email não cadastrado
     * E realiza o login
     * Então o sistema deve retornar
     * "Usuário não encontrado. Verifique o email ou cadastre-se.
     * "E não redirecionar.
     * */

    @Test()
    public void testeValidarAcessoComEmailNaoCadastrado() throws Exception {
        WebElement inputEmail = driver.findElement(By.name("email"));
        WebElement inputPassword = driver.findElement(By.name("password"));
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));

        inputEmail.sendKeys(TestData.EMAIL_INEXISTENTE);
        inputPassword.sendKeys(TestData.PASSWORD_VALIDO);
        button.click();

        WebElement mensagem = driver.findElement(By.cssSelector("[data-testid='email-error']"));

        Assertions.assertTrue(mensagem.isDisplayed());
        Assertions.assertEquals(TestData.MSG_USUARIO_NAO_ENCONTRADO, mensagem.getText());

        ScreenshotUtil.capturar(driver, "usurio_nao_cadastrado");
    }

    /** CEN-05 - Senha incorreta
     * Dado que o usuário possui cadastro
     * Quando informa uma senha incorreta
     * Então o sistema deve exibir
     * "Email ou senha inválidos"
     * E permanecer na tela.
     * */
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

    /** CEN-06 - Login válido
     * Dado que existe o usuário admin@qatest.com
     * Quando informa a senha Teste@123
     * Então o sistema deve autenticar
     * E retornar sucesso
     * E redirecionar para /products
     * E apresentar "Login realizado com sucesso!".
     * */

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

    /** CEN-07 - Email sem senha
     * Dado que o usuário informa apenas o email
     * Quando pressiona Entrar
     * Então o sistema deve impedir o acesso
     * E apresentar
     * "Email e senha são obrigatórios".
     * */
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

    /** CEN-08 - Campo Email vazio
     * Dado que o usuário está preenchendo o formulário
     * Quando deixa o campo Email vazio
     * Então deve ser exibido
     * "Email é obrigatório".
     * */
    @Test()
    public void testeValidarCriarContaSemEmailPreenchido() throws Exception {
        WebElement textCadrastese = driver.findElement(By.linkText("Cadastre-se"));
        WebElement inputName = driver.findElement(By.id("full_name"));
        WebElement inputEmail = driver.findElement(By.id("email"));
        WebElement inputPassword = driver.findElement(By.name("password"));
        WebElement inputConfirmPassword = driver.findElement(By.name("confirm_password"));
        WebElement registerButton = driver.findElement(By.cssSelector("[data-testid='register-button']"));

        textCadrastese.click();
        inputName.sendKeys("Kathleen Miranda");
        inputEmail.click();
        inputPassword.sendKeys("Teste@123");
        inputConfirmPassword.sendKeys("Teste@123");
        registerButton.click();

        WebElement mensagem = driver.findElement(By.id("email"));

        Assertions.assertTrue(mensagem.isDisplayed());
        Assertions.assertEquals("E-mail é obrigatório", mensagem.getText());
    }

    /** CEN-09 - Campo Email válido
     * Dado que existe um erro de validação no Email
     * Quando o usuário informa um email válido
     * Então o erro deve ser removido.
     * */
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

    /** CEN-10 - Campo Senha vazio
     * Dado que o usuário está preenchendo o formulário
     * Quando deixa o campo Senha vazio
     * Então deve ser exibido
     * "Senha é obrigatória".
     * */
    @Test()
    public void testeTentarCriarContaSemPreencherSenha() throws Exception {

        WebElement textCadrastese = driver.findElement(By.linkText("Cadastre-se"));
        WebElement inputName = driver.findElement(By.id("full_name"));
        WebElement inputEmail = driver.findElement(By.id("email"));
        WebElement inputPassword = driver.findElement(By.name("password"));
        WebElement inputConfirmPassword = driver.findElement(By.name("confirm_password"));
        WebElement registerButton = driver.findElement(By.cssSelector("[data-testid='register-button']"));

        textCadrastese.click();
        inputName.sendKeys("Kathleen Miranda");
        inputEmail.sendKeys("kat@teste.com");
        inputPassword.click();
        inputConfirmPassword.sendKeys("Teste@123");
        registerButton.click();

        WebElement mensagem = driver.findElement(By.cssSelector("[data-testid='email-error']"));

        Assertions.assertTrue(mensagem.isDisplayed());
//        Assertions.assertEquals();

        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("kat@teste.com");
        driver.findElement(By.cssSelector("[data-testid='register-button']")).click();

        Assertions.assertTrue(
                driver.findElements(By.cssSelector("[data-testid='email-error']")).isEmpty());
    }

    /** CEN-11 - Campo Senha válido
     * Dado que existe um erro de validação da Senha
     * Quando informa uma senha válida
     * Então a mensagem de erro deve desaparecer.
     * */
    @Test()
    public void testeTentarCriarContaComSSenhaInvalida() throws Exception {

        WebElement textCadrastese = driver.findElement(By.linkText("Cadastre-se"));
        WebElement inputName = driver.findElement(By.id("full_name"));
        WebElement inputEmail = driver.findElement(By.id("email"));
        WebElement inputPassword = driver.findElement(By.name("password"));
        WebElement inputConfirmPassword = driver.findElement(By.name("confirm_password"));
        WebElement registerButton = driver.findElement(By.cssSelector("[data-testid='register-button']"));

        textCadrastese.click();
        inputName.sendKeys("Kathleen Miranda");
        inputEmail.sendKeys("kat@teste.com");
        inputPassword.click();
        inputConfirmPassword.sendKeys("Teste@123");
        registerButton.click();

        WebElement mensagem = driver.findElement(By.cssSelector("[data-testid='email-error']"));

        Assertions.assertTrue(mensagem.isDisplayed());
//        Assertions.assertEquals();

        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("kat@teste.com");
        driver.findElement(By.cssSelector("[data-testid='register-button']")).click();

        Assertions.assertTrue(
                driver.findElements(By.cssSelector("[data-testid='email-error']")).isEmpty());
    }

}
