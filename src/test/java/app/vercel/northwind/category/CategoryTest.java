package app.vercel.northwind.category;

import app.vercel.northwind.base.BaseTest;
import app.vercel.northwind.page.CategoriaPage;
import app.vercel.northwind.page.LoginPage;
import app.vercel.northwind.utils.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CategoryTest extends BaseTest {

    LoginPage loginPage;
    CategoriaPage  categoriaPage;

    @BeforeEach
    public void iniciarPagina(){

        loginPage = new LoginPage(driver);
        loginPage.realizarLogin(
                TestData.EMAIL_VALIDO, TestData.PASSWORD_VALIDO);
        categoriaPage = new CategoriaPage(driver);
        categoriaPage.abrirTelaCategorias(driver);
    }

    @Test
    @DisplayName("Deve cadastrar uma categoria com sucesso")
    public void deveCadastrarCategoriaComSucesso() throws Exception {
        categoriaPage.abrirModalNovaCategoria();

        String nomeCategoria = "Categoria_" + System.currentTimeMillis();

        categoriaPage.preencherNomeCategoria(nomeCategoria);
        categoriaPage.preencherDescricaoCategoria("Descrição sucinta de uma nova categoria.");
        categoriaPage.clicarBtnSalvarCategoria();

        assertTrue(categoriaPage.mensagemSucessoCriacaoCategoria());

        assertEquals(
                TestData.MSG_CATEGORIA_CADASTRADA,
                categoriaPage.obterMensagemCategoriaCriadaComSucesso()
        );

        ScreenshotUtil.capturar(driver,"categoria-cadastrada-sucesso");
    }

    @Test
    @DisplayName("Deve exibir mensagem quando nome da Categoria não for informado")
    public void deveExibirMensagemQuandoNomeCategoriaNaoInformado() throws IOException {

        categoriaPage.abrirModalNovaCategoria();
        categoriaPage.preencherDescricaoCategoria("Descrição válida com mais de 10 caracteres");
        categoriaPage.clicarBtnSalvarCategoria();

        assertTrue(categoriaPage.mensagemErroNomeCategoriaVisivel());

        assertEquals(
                TestData.MSG_NOME_CATEGORIA_OBRIGATORIO,
                categoriaPage.obterMensagemErroNomeCategooria());

        ScreenshotUtil.capturar(driver,"nome_categoria_obrigatorio");
    }

    @Test
    @DisplayName("Deve exibir mensagem quando a descrição da categoria não for informada")
    public void deveExibirMensagemQuandoDescricaoNaoInformada() throws Exception {

        categoriaPage.abrirModalNovaCategoria();
        categoriaPage.preencherNomeCategoria("Nome Categoria");
        categoriaPage.clicarBtnSalvarCategoria();

        assertTrue(categoriaPage.mensagemErroDescricaoCategoriaVisivel());

        assertEquals(
                TestData.MSG_DESCRICAO_OBRIGATORIA,
                categoriaPage.obterMensagemErroDescricaoCategoria());

        ScreenshotUtil.capturar(driver,"categoria-descricao-obrigatoria");
    }

    @Test
    @DisplayName("Deve exibir menagem quando o nome possuir menos de 2 caracteres")
    public void deveExibirMensagemAoInformaNomeComMenosDe2Caracteres() throws Exception {
        categoriaPage.abrirModalNovaCategoria();

        categoriaPage.preencherNomeCategoria("A");
        categoriaPage.preencherDescricaoCategoria("Descrição válida para categoria");
        categoriaPage.clicarBtnSalvarCategoria();

        assertTrue(categoriaPage.mensagemErroNomeCategoriaVisivel());

        assertEquals(
                TestData.MSG_NOME_INVALIDO,
                categoriaPage.obterMensagemErroNomeCategooria());

        ScreenshotUtil.capturar(driver,"categoria-nome-curto");
    }

    @Test
    @DisplayName("Deve exibir mensagem quando o nome possuir mais de 50 caracteres")
    public void deveExibirMensagemAoInformarNomeComMaisDe50Caracteres() throws Exception{
        categoriaPage.abrirModalNovaCategoria();

        String nomeCategoria = "A".repeat(51);

        categoriaPage.preencherNomeCategoria(nomeCategoria);
        categoriaPage.preencherDescricaoCategoria("Descrição válida para categoria");
        categoriaPage.clicarBtnSalvarCategoria();

        assertTrue(categoriaPage.mensagemErroNomeCategoriaVisivel());

        assertEquals(
                TestData.MSG_NOME_INVALIDO,
                categoriaPage.obterMensagemErroNomeCategooria());

        ScreenshotUtil.capturar(driver,"categoria-nome-maximo");
    }

    @Test
    @DisplayName("Deve exibir mensagem quando a descriçao possuir menos de 10 caracteres")
    public void deveExibirMensagemQuandoADescricaoPossuirMenosDe10Caracteres() throws Exception{
        categoriaPage.abrirModalNovaCategoria();

        String descricaoCategoria = "Categoria";

        categoriaPage.preencherNomeCategoria("Categoria Teste");
        categoriaPage.preencherDescricaoCategoria(descricaoCategoria);
        categoriaPage.clicarBtnSalvarCategoria();

        assertTrue(categoriaPage.mensagemErroDescricaoCategoriaVisivel());

        assertEquals(TestData.MSG_DESCRICAO_LIMITE, categoriaPage.obterMensagemErroDescricaoCategoria());
    }

    @Test
    @DisplayName("Deve exibir mensagem quando a descriçao possuir menos de 200 caracteres")
    public void deveExibirMensagemQuandoADescricaoPossuirMenosDe200Caracteres() throws Exception{
        categoriaPage.abrirModalNovaCategoria();

        String descricaoCategoria = "A".repeat(201);

        categoriaPage.preencherNomeCategoria("Categoria Teste");
        categoriaPage.preencherDescricaoCategoria(descricaoCategoria);
        categoriaPage.clicarBtnSalvarCategoria();

        assertTrue(categoriaPage.mensagemErroDescricaoCategoriaVisivel());

        assertEquals(TestData.MSG_DESCRICAO_LIMITE, categoriaPage.obterMensagemErroDescricaoCategoria());
    }

    @Test
    @DisplayName("Deve editar um item da categoria")
    public void deveEditarCategoria() throws Exception {
        categoriaPage.searchCategory("Categoria_");
        categoriaPage.openModalEditCategory();
        categoriaPage.clearNameCategory();
        categoriaPage.editNameCategory("Calça Jeans Levis Bootcup");
        categoriaPage.clickBtnUpdateCategory();


//        driver.findElement(By.cssSelector("[data-testid='edit-category-description-input']")).click();
//        driver.findElement(By.cssSelector("[data-testid='edit-category-description-input']")).clear();
//        driver.findElement(By.cssSelector("[data-testid='edit-category-description-input']"))
//                .sendKeys("Alteração da descrição para teste automatizado edição.");
//
//        driver.findElement(By.cssSelector("[data-testid='update-category-btn']")).click();


        ScreenshotUtil.capturar(driver,"update-categoria");







    }
}
