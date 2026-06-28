package app.vercel.northwind.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.function.BooleanSupplier;


public class LoginTest {
        private WebDriver driver;

        @BeforeEach
        public void setUp() {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        }

        /** CEN-01 - Login sem credenciais
        * Dado que o usuário está na página de login
        * Quando deixa os campos Email e Senha vazios e clica em "Entrar"
        * Então o sistema deve impedir o login
        * E exibir "Email e senha são obrigatórios"
        * E permanecer na tela de login
        */
        @Test()
        public void testeValidarTentativaDeAcessoSemPreenchimentoDosCamposEmailESenha() throws Exception {
            driver.get("https://northwind-test-platform.vercel.app/");
            driver.findElement(By.name("email")).click();
            driver.findElement(By.name("password")).click();
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            Assertions.assertTrue(
                    driver.findElement(By.cssSelector("[data-testid='password-error']")).isDisplayed(),"Email e senha são obrigatórios");
        }
    /** CEN-02 - Email inválido
     * Dado que o usuário está na página de login
     * Quando informa um email inválido
     * E informa uma senha válida
     * Então o sistema deve exibir
     * "Formato de email inválido. Use: nome@dominio.com".
     */

    @Test()
    public void testeValidarAcessoComEmailInvalido() throws Exception {
        driver.get("https://northwind-test-platform.vercel.app/");
        driver.findElement(By.name("email")).sendKeys("email.invalido");
        driver.findElement(By.name("password")).sendKeys("Teste@123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assertions.assertTrue(
                driver.findElement(By.cssSelector("[data-testid='email-error']")).isDisplayed(),"Formato de email inválido. Use: nome@dominio.com");
    }

    /** CEN-03 - Senha curta
    * Dado que o usuário está na página de login
    * Quando digita uma senha com menos de 6 caracteres
    * Então deve ser exibida
    * "Senha deve ter pelo menos 6 caracteres".
    */
    @Test()
    public void testeValidarAcessoComSenhaCurta() throws Exception {
        driver.get("https://northwind-test-platform.vercel.app/");
        driver.findElement(By.name("email")).sendKeys("admin@qatest.com");
        driver.findElement(By.name("password")).sendKeys("12345");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assertions.assertTrue(
                driver.findElement(By.cssSelector("[data-testid='email-error']")).isDisplayed(),"Senha deve ter pelo menos 6 caracteres");
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
        driver.get("https://northwind-test-platform.vercel.app/");
        driver.findElement(By.name("email")).sendKeys("teste@teste.com");
        driver.findElement(By.name("password")).sendKeys("Teste@123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assertions.assertTrue(
                driver.findElement(By.cssSelector("[data-testid='email-error']")).isDisplayed(),"Usuário não encontrado. Verifique o email ou cadastre-se.");
    }

    /** CEN-05 - Senha incorreta
     * Dado que o usuário possui cadastro
     * Quando informa uma senha incorreta
     * Então o sistema deve exibir
     * "Email ou senha inválidos"
     * E permanecer na tela.
     * */

    @Test()
    public void testeValidarAcessoComSenhaIncorreta() throws Exception {
        driver.get("https://northwind-test-platform.vercel.app/");
        driver.findElement(By.name("email")).sendKeys("admin@qatest.com");
        driver.findElement(By.name("password")).sendKeys("senhaIncorreta");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assertions.assertTrue(
                driver.findElement(By.cssSelector("[data-testid='password-error']")).isDisplayed(),"Email ou senha inválidos");
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
        driver.get("https://northwind-test-platform.vercel.app/");
        driver.findElement(By.name("email")).sendKeys("admin@qatest.com");
        driver.findElement(By.name("password")).sendKeys("Teste@123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    /** CEN-07 - Email sem senha
     * Dado que o usuário informa apenas o email
     * Quando pressiona Entrar
     * Então o sistema deve impedir o acesso
     * E apresentar
     * "Email e senha são obrigatórios".
     * */
    @Test()
    public void testeValidarAcessoComApenasEmailPreenchido() throws Exception {
        driver.get("https://northwind-test-platform.vercel.app/");
        driver.findElement(By.name("email")).sendKeys("admin@qatest.com");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assertions.assertTrue(
                driver.findElement(By.cssSelector("[data-testid='password-error']")).isDisplayed(),"Email e senha são obrigatórios");
    }

    /** CEN-08 - Campo Email vazio
     * Dado que o usuário está preenchendo o formulário
     * Quando deixa o campo Email vazio
     * Então deve ser exibido
     * "Email é obrigatório".
     * */
    @Test()
    public void testeValidarCriarContaSemEmailPreenchido() throws Exception {
        driver.get("https://northwind-test-platform.vercel.app/");
        driver.findElement(By.linkText("Cadastre-se")).click();
        driver.findElement(By.id("full_name")).sendKeys("Kathleen Miranda");
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("password")).sendKeys("Teste@123");
        driver.findElement(By.id("confirmPassword")).sendKeys("Teste@123");
        driver.findElement(By.cssSelector("[data-testid='register-button']")).click();

        Assertions.assertTrue(driver.findElement(By.id("email")).isDisplayed(),"E-mail é obrigatório");
    }

    /** CEN-09 - Campo Email válido
     * Dado que existe um erro de validação no Email
     * Quando o usuário informa um email válido
     * Então o erro deve ser removido.
     * */
    @Test()
    public void testeValidarCriarContaAjustandoEmail() throws Exception {
        driver.get("https://northwind-test-platform.vercel.app/");
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

        @AfterEach
        public void tearDown() throws Exception {
            driver.quit();
        }
}
