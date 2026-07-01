package app.vercel.northwind.category;

import app.vercel.northwind.base.BaseTest;
import app.vercel.northwind.utils.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

import static app.vercel.northwind.utils.LoginUtil.realizarLogin;


public class CategoryTest extends BaseTest {
    @Test
    @DisplayName("Deve exibir erro ao tentar salvar categoria sem nome")
    public void testNomeObrigatorio() throws IOException {

        realizarLogin(driver);
        CategoryUtil.acessarTelaCategorias(driver);

        driver.findElement(By.cssSelector("[data-testid='category-description-input']"))
                .sendKeys("Descrição válida com mais de 10 caracteres");

        CategoryUtil.salvarCategoria(driver);

        WebElement erro = WaitUtil.esperarElementoVisivel(
                driver,
                By.cssSelector("[data-testid='error-category-name']")
        );

        Assertions.assertTrue(erro.isDisplayed());

        Assertions.assertEquals(
                TestData.MSG_NOME_CATEGORIA_OBRIGATORIO,
                erro.getText());

        ScreenshotUtil.capturar(driver,"nome_obrigatorio");
    }

    @Test
    @DisplayName("Deve exibir mensagem ao tentar cadastrar categoria sem informar descrição")
    public void deveExibirMensagemAoCadastrarCategoriaSemDescricao() throws Exception {

        realizarLogin(driver);
        CategoryUtil.acessarTelaCategorias(driver);

        WebElement nome =
                driver.findElement(By.cssSelector("[data-testid='category-name-input']"));

        WebElement salvar =
                driver.findElement(By.cssSelector("[data-testid='save-category-btn']"));

        nome.sendKeys("Categoria Teste");

        salvar.click();

        WebElement erro =
                driver.findElement(By.cssSelector("[data-testid='error-category-description']"));

        Assertions.assertTrue(erro.isDisplayed());

        Assertions.assertEquals(
                TestData.MSG_DESCRICAO_OBRIGATORIA,
                erro.getText());

        ScreenshotUtil.capturar(driver,"categoria-descricao-obrigatoria");
    }

    @Test
    @DisplayName("Deve validar tamanho mínimo do nome da categoria")
    public void deveValidarNomeComUmCaractere() throws Exception {

        realizarLogin(driver);
        CategoryUtil.acessarTelaCategorias(driver);

        driver.findElement(By.cssSelector("[data-testid='category-name-input']"))
                .sendKeys("A");

        driver.findElement(By.cssSelector("[data-testid='category-description-input']"))
                .sendKeys("Descrição válida para categoria");

        driver.findElement(By.cssSelector("[data-testid='save-category-btn']")).click();

        WebElement erro =
                driver.findElement(By.cssSelector("[data-testid='error-category-name']"));

        Assertions.assertTrue(erro.isDisplayed());

        Assertions.assertEquals(
                TestData.MSG_NOME_INVALIDO,
                erro.getText());

        ScreenshotUtil.capturar(driver,"categoria-nome-curto");
    }

    @Test
    @DisplayName("Deve exibir mensagem ao informar nome com mais de 50 caracteres")
    public void deveExibirMensagemAoInformarNomeComMaisDe50Caracteres() throws Exception{

        realizarLogin(driver);
        CategoryUtil.acessarTelaCategorias(driver);
        String nome = "A".repeat(51);

        driver.findElement(By.cssSelector("[data-testid='category-name-input']"))
                .sendKeys(nome);

        driver.findElement(By.cssSelector("[data-testid='category-description-input']"))
                .sendKeys("Descrição válida para categoria");

        driver.findElement(By.cssSelector("[data-testid='save-category-btn']")).click();

        WebElement erro =
                driver.findElement(By.cssSelector("[data-testid='error-category-name']"));

        Assertions.assertTrue(erro.isDisplayed());

        Assertions.assertEquals(
                TestData.MSG_NOME_INVALIDO,
                erro.getText());

        ScreenshotUtil.capturar(driver,"categoria-nome-maximo");
    }

    @Test
    @DisplayName("Deve cadastrar categoria com sucesso")
    public void deveCadastrarCategoriaComSucesso() throws Exception {
        realizarLogin(driver);
        CategoryUtil.acessarTelaCategorias(driver);

        WebElement nome =
                driver.findElement(By.cssSelector("[data-testid='category-name-input']"));

        WebElement descricao =
                driver.findElement(By.cssSelector("[data-testid='category-description-input']"));

        WebElement salvar =
                driver.findElement(By.cssSelector("[data-testid='save-category-btn']"));

        String nomeCategoria = "Categoria_" + System.currentTimeMillis();

        nome.sendKeys(nomeCategoria);

        descricao.sendKeys("Descrição sucinta de uma nova categoria.");

        salvar.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10) );

        WebElement toast = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        (By.className("Toastify__toast"))));

        Assertions.assertTrue(toast.isDisplayed());
        Assertions.assertTrue(toast.getText().contains(TestData.MSG_CATEGORIA_CADASTRADA));

        ScreenshotUtil.capturar(driver,"categoria-cadastrada");
    }

    @Test
    @DisplayName("Deve fechar modal ao cancelar cadastro")
    public void deveCancelarCadastroCategoria() throws Exception {

        realizarLogin(driver);
        CategoryUtil.acessarTelaCategorias(driver);

        driver.findElement(
                        By.cssSelector("[data-testid='cancel-category-btn']"))
                .click();

        Assertions.assertTrue(
                driver.findElements(
                                By.cssSelector("[data-testid='category-name-input']"))
                        .isEmpty());

        ScreenshotUtil.capturar(driver,"cancelar-cadastro-categoria");
    }

}
