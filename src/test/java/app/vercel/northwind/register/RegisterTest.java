package app.vercel.northwind.register;

import app.vercel.northwind.base.BaseTest;
import app.vercel.northwind.page.LoginPage;
import app.vercel.northwind.page.RegisterPage;
import app.vercel.northwind.utils.DataGenerator;
import app.vercel.northwind.utils.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static app.vercel.northwind.utils.ScreenshotUtil.capturar;
import static app.vercel.northwind.utils.WaitUtil.esperarUrlContem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterTest extends BaseTest {

    private LoginPage loginPage;
    private RegisterPage registerPage;

    @BeforeEach
    void iniciarPagina() {

        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);

        loginPage.abrirCadastro();
    }

    @Test()
    @DisplayName("Deve criar conta com sucesso")
    public void deveCriarContaComSucesso() throws IOException {
        registerPage.preencherNomeCompleto("Quality Assurance");
        registerPage.preencherEmail(DataGenerator.gerarEmail());
        registerPage.preencherSenha(TestData.PASSWORD_VALIDO);
        registerPage.preencherConfirmSenha(TestData.PASSWORD_VALIDO);
        registerPage.clicarBotao();

        assertTrue(registerPage.mensagemContaCriadaComSucessoVisivel());

        capturar(driver, "cadastro-criado-com-sucesso");

    }

    @Test()
    @DisplayName("Deve exibir mensagem de preenchimento obrigatório para o campo de e-mail")
    public void deveExibirMensagemQuandoEmailNaoForInformado() throws Exception {
        registerPage.preencherNomeCompleto("Quality Assurance");
        registerPage.preencherEmail("a");
        registerPage.pressionarBackspaceEmail();

        assertTrue(
                registerPage.mensagemErroEmailVisivel());

        Assertions.assertEquals(
                TestData.MSG_EMAIL_OBRIGATORIO, registerPage.obterMensagemErroEmail());

        capturar(driver, "form-email-obrigatorio");

    }

    @Test()
    @DisplayName("Deve exibir mensagem de e-mail inválido quando o formato informado estiver incorreto")
    public void deveExibirMensagemDeEmailInvalido() throws Exception {
        registerPage.preencherNomeCompleto("Quality Assurance");
        registerPage.preencherEmail("a");

        assertTrue(registerPage.mensagemErroEmailVisivel());

        assertEquals(TestData.MSG_EMAIL_INVALIDO, registerPage.obterMensagemErroEmail());

        capturar(driver, "form-email-invalido");
    }

    @Test
    @DisplayName("Deve exibir mensagem de senha Obrigatória")
    public void deveExibirMensagemDeSenhaObrigatorio() throws IOException {
        registerPage.preencherNomeCompleto("Quality Assurance");
        registerPage.preencherEmail("qa@qa.com.br");

        registerPage.preencherSenha("a");
        registerPage.pressionarBackspaceSenha();

        assertTrue(registerPage.mensagemErroSenhaVisivel());

        assertEquals(TestData.MSG_SENHA_OBRIGATORIA, registerPage.obterMensagemErroSenha());

        capturar(driver, "form-senha-obrigatorio");
    }

    @Test
    @DisplayName("Deve ocultar a mensagem de erro ao informar uma senha válida")
    public void deveOcultarMensagemDeErroAoInformarSenhaValida() {
        // Dado: existe um erro
        registerPage.preencherSenha("a");

        Assertions.assertTrue(
                registerPage.mensagemErroSenhaVisivel());

        // Quando: informo uma senha válida
        registerPage.limparSenha();
        registerPage.preencherSenha("Senha!123");

        // Então: o erro desaparece
      assertTrue(
                registerPage.mensagemErroSenhaNaoExiste()
        );
    }

    @Test
    @DisplayName("Deve exibir mensagem quando o campo nome exceder limite máximo")
    public void deveExibirMensagemNomeExcedidoMaximo() throws IOException {
        String nome = "A".repeat(101);
        registerPage.preencherNomeCompleto(nome);

       assertTrue(
                registerPage.mensagemErroNomeVisivel());

        assertEquals(
                TestData.MSG_NOME_TAMANHO_MAXIMO, registerPage.obterMensagemErroNome());

        capturar(driver, "form-nome-limite-maximo");
    }

    @Test
    @DisplayName("Deve exibir mensagem quando o campo nome não obtiver limite mínimo")
    public void deveExibirMensagemNomeTamanhoMinimo() throws IOException {
        String nome = "A".repeat(2);
        registerPage.preencherNomeCompleto(nome);

        assertTrue(
                registerPage.mensagemErroNomeVisivel());

        assertEquals(
                TestData.MSG_NOME_TAMANHO_MINIMO, registerPage.obterMensagemErroNome());

        capturar(driver, "form-nome-minimo");
    }

    @Test
    @DisplayName("Deve exibir mensagem de nome brigatório")
    public void deveExibirMensagemNomeObrigatorio() throws IOException {
        registerPage.preencherNomeCompleto("a");
        registerPage.pressionarBackspaceNome();

        assertTrue(
                registerPage.mensagemErroNomeVisivel());

        assertEquals(
                TestData.MSG_NOME_OBRIGATORIO, registerPage.obterMensagemErroNome());

        capturar(driver, "form-nome-inexistente");
    }

    @Test
    @DisplayName("Deve exibir mensagem quando o nome informado possuir caracteres especiais ou números")
    public void deveExibirMensagemQuandoNomePossuirCaracteresEspeciaisOUNumeros() throws IOException {
        registerPage.preencherNomeCompleto("N0m& 1nv4l1D0");

        assertTrue(
                registerPage.mensagemErroNomeVisivel());

        assertEquals(
                TestData.MSG_NOME_APENAS_LETRAS_ESPACOS, registerPage.obterMensagemErroNome());

        capturar(driver, "form-nome-caracteres-especiais");
    }

    @Test
    @DisplayName("Deve exibir mensagem quando o campo email exceder tamanho máximo")
    public void deveExibirMensagemQuandoEmailExcederTamanhoMaximo() throws IOException {
        String email = "a".repeat(256);
        registerPage.preencherEmail("teste@teste.com" + email);

        assertTrue(
                registerPage.mensagemErroEmailVisivel());

        assertEquals(
                TestData.MSG_EMAIL_TAMANHO_MAXIMO, registerPage.obterMensagemErroEmail());

        capturar(driver, "form-email-maximo");
    }

    @Test
    @DisplayName("Deve exibir mensagem senha tamanho minimo")
    public void deveExibirMensagemQuandoSenhaExcederTamanhoMinimo() throws IOException {
        registerPage.preencherSenha("a");

        assertTrue(
                registerPage.mensagemErroSenhaVisivel());

        assertEquals(
                TestData.MSG_SENHA_TAMANHO_MINIMO, registerPage.obterMensagemErroSenha());

        capturar(driver, "form-senha-minimo");
    }

    @Test
    @DisplayName("Deve exibir mensagem quando o campo senha não for informado Letra Maiúscula")
    public void deveExibirMensagemQuandoSenhaNaoPossuirLetraMaiuscula() throws IOException {
        registerPage.preencherSenha(TestData.PASSWORD_SEM_LETRA_MAIUSCULA);

        assertTrue(
                registerPage.mensagemErroSenhaVisivel());

        assertEquals(
                TestData.MSG_SENHA_CONTER_LETRA_MAIUSCULA, registerPage.obterMensagemErroSenha());

        capturar(driver, "form-senha-sem-letra-maiuscula");
    }

    @Test
    @DisplayName("Deve exibir mensagem quando o campo senha não for informado Letra Minúscula")
    public void deveExibirMensagemQuandoSenhaNaoPossuirLetraMinuscula() throws IOException {
        registerPage.preencherSenha(TestData.PASSWORD_SEM_LETRA_MINUSCULA);

        assertTrue(
                registerPage.mensagemErroSenhaVisivel());

        assertEquals(
                TestData.MSG_SENHA_CONTER_LETRA_MINUSCULA, registerPage.obterMensagemErroSenha());

        capturar(driver, "form-senha-sem-letra-minuscula");
    }

    @Test
    @DisplayName("Deve exibir mensagem quando o campo senha não for informado caracter especial")
    public void deveExibirMensagemQuandoSenhaNaoPossuirCaracterEspecial() throws IOException {
        registerPage.preencherSenha(TestData.PASSWORD_SEM_CARACTER_ESPECIAL);

        assertTrue(
                registerPage.mensagemErroSenhaVisivel());

       assertEquals(
                TestData.MSG_SENHA_CONTER_CARACTERE_ESPECIAL, registerPage.obterMensagemErroSenha());

        capturar(driver, "form-senha-sem-letra-maiuscula");
    }

    @Test
    @DisplayName("Deve exibir mensagem quando senha exceder tamanho máximo")
    public void deveExibirMensagemQuandoSenhaExcederTamanhoMaximo() throws IOException {
        String senha = "a".repeat(129);
        registerPage.preencherSenha(TestData.PASSWORD_VALIDO + senha);

        assertTrue(
                registerPage.mensagemErroSenhaVisivel());

        assertEquals(
                TestData.MSG_SENHA_TAMANHO_MAXIMO, registerPage.obterMensagemErroSenha());

        capturar(driver, "form-senha-tamanho-maximoo");
    }

    @Test
    @DisplayName("Deve exibir mensagem quando senha possuir caracter for repetido")
    public void deveExibirMensagemQuandoSenhaPossuirCaracterRepetido() throws IOException {
        registerPage.preencherSenha("123@123abcAAAAA");

        assertTrue(
                registerPage.mensagemErroSenhaVisivel());

        assertEquals(
                TestData.MSG_SENHA_CARACTER_REPETIDO, registerPage.obterMensagemErroSenha());

        capturar(driver, "form-senha-caracter-repetido");
    }

    @Test
    @DisplayName("Deve exibir mensagem quando a confirmação de senha for divergente")
    public void deveExibirMensagemQuandoConfirmSenhaForDivergente() throws IOException {
        registerPage.preencherSenha(TestData.PASSWORD_VALIDO);
        registerPage.preencherConfirmSenha("123@Teste");

        assertTrue(
                registerPage.mensagemErroConfirmSenhaVisivel());

        assertEquals(
                TestData.MSG_CONFIRM_SENHA_DIVERGENTE, registerPage.obterMensagemErroConfirmSenha());

        capturar(driver, "form-confirm-senha-divergente");
    }

    @Test
    @DisplayName("Deve exibir mensagem de confirmação de senha obrigatória")
    public void deveExibirMensagemConfirmSenhaObrigatoria() throws IOException {
        registerPage.preencherConfirmSenha("a");
        registerPage.pressionarBackspaceConfirmSenha();

        assertTrue(
                registerPage.mensagemErroConfirmSenhaVisivel());

        assertEquals(
                TestData.MSG_CONFIRM_SENHA_OBRIGATORIA, registerPage.obterMensagemErroConfirmSenha());

        capturar(driver, "form-confirm-senha-obrigatória");
    }


}
