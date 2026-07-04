package app.vercel.northwind.login;

import app.vercel.northwind.base.BaseTest;

import app.vercel.northwind.page.LoginPage;
import app.vercel.northwind.utils.TestData;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static app.vercel.northwind.utils.ScreenshotUtil.*;
import static app.vercel.northwind.utils.WaitUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginTest extends BaseTest {

    @Test()
    @DisplayName("Deve exibir mensagem ao tentar realizar login sem preencher os campos obrigatórios.")
    public void deveExibirMensagemAoTentarLoginSemPreencherCamposObrigatorios() throws IOException {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.realizarLogin(
                TestData.EMAIL_VAZIO, TestData.PASSWORD_VAZIO
        );

        assertTrue(
                loginPage.mensagemErroSenhaEstaVisivel()
        );

        assertEquals(
                TestData.MSG_CAMPOS_OBRIGATORIOS,
                loginPage.obterMensagemErroSenha()
        );

        capturar(driver, "campos-obrigatorios");
    }

    @Test()
    @DisplayName("Deve exibir mensagem ao tentar realizar login com e-mail inválido")
    public void deveExibirMensagemAoTentarLoginComEmailInvalido() throws Exception {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.realizarLogin(
                TestData.EMAIL_INVALIDO, TestData.PASSWORD_VALIDO
        );

        assertTrue(
                loginPage.mensagemErroEmailEstaVisivel()
        );

        Assertions.assertEquals(
                TestData.MSG_FORMATO_EMAIL_INVALIDO,
                loginPage.obterMensagemErroEmail()
        );

        capturar(driver, "email-invalido");
    }

    @Test()
    @DisplayName("Deve exibir mensagem ao tentar realizar login com senha curta")
    public void deveExibirMensagemInformandoSenhaCurta() throws Exception {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.realizarLogin(
                TestData.EMAIL_VALIDO, TestData.PASSWORD_CURTO
        );

       assertTrue(
               loginPage.mensagemErroEmailEstaVisivel()
       );

        Assertions.assertEquals(
                TestData.MSG_SENHA_CURTA,
                loginPage.obterMensagemErroEmail()
        );

        capturar(driver, "senha-curta");
    }

    @Test()
    @DisplayName("Deve exibir mensagem ao tentar realizar login com email não cadastrado")
    public void DeveExibirMensagemDeEmailNaoCadastrado() throws Exception {
        LoginPage  loginPage = new LoginPage(driver);

        loginPage.realizarLogin(
                TestData.EMAIL_INEXISTENTE, TestData.PASSWORD_VALIDO
        );

        assertTrue(
                loginPage.mensagemErroEmailEstaVisivel()
        );

        Assertions.assertEquals(
                TestData.MSG_USUARIO_NAO_ENCONTRADO,
                loginPage.obterMensagemErroEmail()
        );

        capturar(
                driver, "usurio_nao_cadastrado");
    }

    @Test()
    @DisplayName("Deve exibir mensagem ao tentar realizar login com e senha invalida")
    public void deveExibirMensagemDeSenhaInvalida() throws Exception {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.realizarLogin(
                TestData.EMAIL_VALIDO, TestData.PASSWORD_INVALIDO
        );

        assertTrue(
                loginPage.mensagemErroSenhaEstaVisivel()
        );

        assertEquals(
                TestData.MSG_EMAIL_SENHA_INVALIDOS,
                loginPage.obterMensagemErroSenha()
        );

        capturar(driver, "senha-inexistente");
    }


    @Test()
    @DisplayName("Deve realizar Login com sucesso")
    public void deveRealizarLoginComSucesso() throws Exception {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.realizarLogin(
                TestData.EMAIL_VALIDO, TestData.PASSWORD_VALIDO
        );

        esperarUrlContem(
                driver,"https://northwind-test-platform.vercel.app/products");

        assertEquals("https://northwind-test-platform.vercel.app/products",
                driver.getCurrentUrl(),
                "Não ocorreu redirecionamento para tela de produtos");

        capturar(driver, "acesso-valido");
    }

    @Test()
    public void deveExibirMensagemInformandoCamposObrigatorios() throws IOException {
        LoginPage  loginPage = new LoginPage(driver);

        loginPage.realizarLogin(
                TestData.EMAIL_VALIDO, ""
        );

        assertTrue(
                loginPage.mensagemErroSenhaEstaVisivel()
        );

        Assertions.assertEquals(
                TestData.MSG_CAMPOS_OBRIGATORIOS,
                loginPage.obterMensagemErroSenha()
        );

        capturar(driver, "campos-obrigatorios");
    }

}
