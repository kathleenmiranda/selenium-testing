package app.vercel.northwind.category;

import app.vercel.northwind.base.BaseTest;
import app.vercel.northwind.page.CategoriaPage;
import app.vercel.northwind.page.LoginPage;
import app.vercel.northwind.utils.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

import static app.vercel.northwind.utils.CategoryUtil.acessarTelaCategorias;
import static app.vercel.northwind.utils.LoginUtil.realizarLogin;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CategoryTest extends BaseTest {

    LoginPage loginPage;
    CategoriaPage  categoriaPage;

    @BeforeEach
    public void iniciarPagina(){

        loginPage = new LoginPage(driver);
        categoriaPage = new CategoriaPage(driver);

        loginPage.realizarLogin(
                TestData.EMAIL_VALIDO, TestData.PASSWORD_VALIDO);
        categoriaPage.acessarTelaCategoriasEEsperaroBotaoFicarVisivelEClicavel(driver);
    }

    @Test
    @DisplayName("Deve exibir erro ao tentar salvar categoria sem nome")
    public void deveExibirMensagemQuandoNomeCategoriaNaoInformado() throws IOException {

        categoriaPage.preencherDescricao("Descrição válida com mais de 10 caracteres");
        categoriaPage.clicarBtnSalvarCategoria();

        assertTrue(categoriaPage.mensagemErroNomeCategoriaVisivel());

        assertEquals(
                TestData.MSG_NOME_CATEGORIA_OBRIGATORIO,
                categoriaPage.obterMensagemErroNomeCategooria());

        ScreenshotUtil.capturar(driver,"nome_categoria_obrigatorio");
    }

    @Test
    @DisplayName("Deve exibir mensagem ao tentar cadastrar categoria sem informar descrição")
    public void deveExibirMensagemAoCadastrarCategoriaSemDescricao() throws Exception {

        categoriaPage.preencherNomeCategoria("Nome Categoria");
        categoriaPage.clicarBtnSalvarCategoria();

        assertTrue(categoriaPage.mensagemErroDescricaoCategoriaVisivel());

        assertEquals(
                TestData.MSG_DESCRICAO_OBRIGATORIA,
                categoriaPage.obterMensagemErroDescricaoCategoria());

        ScreenshotUtil.capturar(driver,"categoria-descricao-obrigatoria");
    }

    @Test
    @DisplayName("Deve exibir menagem ao informar nome com menos de 2 caracteres")
    public void deveExibirMensagemAoInformaNomeComMenosDe2Caracteres() throws Exception {

        categoriaPage.preencherNomeCategoria("A");
        categoriaPage.preencherDescricao("Descrição válida para categoria");
        categoriaPage.clicarBtnSalvarCategoria();

        assertTrue(categoriaPage.mensagemErroNomeCategoriaVisivel());

        assertEquals(
                TestData.MSG_NOME_INVALIDO,
                categoriaPage.obterMensagemErroNomeCategooria());

        ScreenshotUtil.capturar(driver,"categoria-nome-curto");
    }

    @Test
    @DisplayName("Deve exibir mensagem ao informar nome com mais de 50 caracteres")
    public void deveExibirMensagemAoInformarNomeComMaisDe50Caracteres() throws Exception{

        String nomeCategoria = "A".repeat(51);

        categoriaPage.preencherNomeCategoria(nomeCategoria);
        categoriaPage.preencherDescricao("Descrição válida para categoria");
        categoriaPage.clicarBtnSalvarCategoria();

        assertTrue(categoriaPage.mensagemErroNomeCategoriaVisivel());

        assertEquals(
                TestData.MSG_NOME_INVALIDO,
                categoriaPage.obterMensagemErroNomeCategooria());

        ScreenshotUtil.capturar(driver,"categoria-nome-maximo");
    }

    @Test
    @DisplayName("Deve cadastrar categoria com sucesso")
    public void deveCadastrarCategoriaComSucesso() throws Exception {
        String nomeCategoria = "Categoria_" + System.currentTimeMillis();

        categoriaPage.preencherNomeCategoria(nomeCategoria);
        categoriaPage.preencherDescricao("Descrição sucinta de uma nova categoria.");
        categoriaPage.clicarBtnSalvarCategoria();

        assertTrue(categoriaPage.mensagemSucessooCriacaoCategoria());

        assertEquals(
                TestData.MSG_CATEGORIA_CADASTRADA,
                categoriaPage.obterMensagemCategoriaCriadaComSucesso()
        );

        ScreenshotUtil.capturar(driver,"categoria-cadastrada-sucesso");
    }

    @Test
    @DisplayName("Deve fechar modal ao cancelar cadastro")
    public void deveCancelarCadastroCategoria() throws Exception {


        categoriaPage.clicarBtnCancelarCategoria();

        assertTrue(
                driver.findElements(
                                By.cssSelector("[data-testid='category-name-input']"))
                        .isEmpty());

        ScreenshotUtil.capturar(driver,"cancelar-cadastro-categoria");
    }

    @Test
    @DisplayName("Deve editar um item da categoria")
    public void deveEditarCategoria() throws Exception {

        realizarLogin(driver);
        acessarTelaCategorias(driver);

        driver.findElement(By.cssSelector("[data-testid='category-search']")).sendKeys("Categoria_");
        driver.findElement(By.cssSelector("[data-testid='edit-category-517']")).click();

        driver.findElement(By.cssSelector("[data-testid='edit-category-name-input']")).click();
        driver.findElement(By.cssSelector("[data-testid='edit-category-name-input']")).clear();
        driver.findElement(By.cssSelector("[data-testid='edit-category-name-input']")).sendKeys("Calça Jeans");

        driver.findElement(By.cssSelector("[data-testid='edit-category-description-input']")).click();
        driver.findElement(By.cssSelector("[data-testid='edit-category-description-input']")).clear();
        driver.findElement(By.cssSelector("[data-testid='edit-category-description-input']"))
                .sendKeys("Alteração da descrição para teste automatizado edição.");

        driver.findElement(By.cssSelector("[data-testid='update-category-btn']")).click();


        ScreenshotUtil.capturar(driver,"categoria_editada");







    }
}
