package app.vercel.northwind.register;

import app.vercel.northwind.base.BaseTest;
import app.vercel.northwind.page.LoginPage;
import app.vercel.northwind.page.RegisterPage;
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

        LoginPage loginPage = new LoginPage(driver);
        loginPage.abrirCadastro();

        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.preencherNomeCompleto("Quality Assurance");

        registerPage.preencherEmail("a");
        registerPage.apagarEmail();
        registerPage.clicarNomeCompleto();


        assertTrue(registerPage.mensagemErroEmailVisivel());

        Assertions.assertEquals(TestData.MSG_EMAIL_OBRIGATORIO, registerPage.obterMensagemErroEmail());

        capturar(driver, "form-email-obrigatorio");

    }

    @Test()
    @DisplayName("Deve exibir mensagem de e-mail inválido quando o formato for incorreto")
    public void deveExibirMensagemDeEmailInvalido() throws Exception {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.abrirCadastro();

        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.preencherNomeCompleto("Quality Assurance");
        registerPage.preencherEmail("a");
        registerPage.clicarNomeCompleto();


        assertTrue(registerPage.mensagemErroEmailVisivel());

        Assertions.assertEquals(TestData.MSG_EMAIL_INVALIDO, registerPage.obterMensagemErroEmail());

        capturar(driver, "form-email-invalido");
    }

    @Test
    @DisplayName("Deve exibir mensagem de campo obrigatorio para a senha")
    public void deveExibirMensagemDeSenhaObrigatorio() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.abrirCadastro();
        RegisterPage registerPage = new RegisterPage(driver);


        registerPage.preencherNomeCompleto("Quality Assurance");
        registerPage.preencherEmail("qa@qa.com.br");

        registerPage.preencherSenha("a");
        registerPage.apagarSenha();
        registerPage.clicarNomeCompleto();

        assertTrue(registerPage.mensagemErroSenhaVisivel());

        assertEquals(TestData.MSG_SENHA_OBRIGATORIA, registerPage.obterMensagemErroSenha());

        capturar(driver, "form-senha-obrigatorio");
    }

    @Test
    @DisplayName("Deve ocultar a mensagem de erro ao informar uma senha válida")
    public void deveOcultarMensagemDeErroAoInformarSenhaValida() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.abrirCadastro();
        RegisterPage registerPage = new RegisterPage(driver);

        // Dado: existe um erro
        registerPage.preencherSenha("a");

        Assertions.assertTrue(
                registerPage.mensagemErroSenhaVisivel());

        // Quando: informo uma senha válida
        registerPage.limparSenha();
        registerPage.preencherSenha("Senha!123");

        // Então: o erro desaparece
        Assertions.assertTrue(
                registerPage.mensagemErroSenhaNaoExiste()
        );
    }
}
