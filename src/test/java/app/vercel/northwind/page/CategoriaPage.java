package app.vercel.northwind.page;

import app.vercel.northwind.utils.TestData;
import app.vercel.northwind.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class CategoriaPage {
    private final WebDriver driver;
    private final By inputDescricao =
            By.cssSelector("[data-testid='category-description-input']");
    private final By inputCategory =
            By.cssSelector("[data-testid='category-name-input']");

    private final By categoryNameError =
            By.cssSelector("[data-testid='error-category-name']");
    private final By descritionError =
            By.cssSelector("[data-testid='error-category-description']");
    private final By btnCriarCategoria =
            By.cssSelector("[data-testid='add-category-btn']");
    private final By btnSalvarCategoria =
            By.cssSelector("[data-testid='save-category-btn']");
    private final By toast =
            By.cssSelector(".Toastify__toast--success");
    private final By btnCancel =
            By.cssSelector("[data-testid='cancel-category-btn']");


    public CategoriaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void preencherNomeCategoria(String nameCategory){
        driver.findElement(inputCategory)
                .sendKeys(nameCategory);
    }
    public void preencherDescricao(String descricao){
        driver.findElement(inputDescricao)
                .sendKeys(descricao);
    }

    public void clicarBtnSalvarCategoria(){
        driver.findElement(btnSalvarCategoria).click();
    }

    public void clicarBtnCancelarCategoria(){
        driver.findElement(btnCancel).click();
    }
    public String obterMensagemErroNomeCategooria() {
        return driver.findElement(categoryNameError).getText();
    }

    public boolean mensagemErroNomeCategoriaVisivel() {
        return driver.findElement(categoryNameError)
                .isDisplayed();
    }

    public String obterMensagemErroDescricaoCategoria() {
        return driver.findElement(descritionError).getText();
    }

    public boolean mensagemErroDescricaoCategoriaVisivel() {
        return driver.findElement(descritionError).isDisplayed();
    }

    public boolean mensagemSucessooCriacaoCategoria(){
        WaitUtil.esperarElementoVisivel(
                driver,
                toast
        );
      return  driver.findElement(toast).isDisplayed();
    }

    public String obterMensagemCategoriaCriadaComSucesso() {

        WaitUtil.esperarTextoNoElemento(
                driver,
                toast,
                TestData.MSG_CATEGORIA_CADASTRADA
        );

        return driver.findElement(toast).getText().trim();
    }

    public static void acessarTelaCategoriasEEsperaroBotaoFicarVisivelEClicavel(WebDriver driver) {

        // Guarda a aba atual
        String janelaAtual = driver.getWindowHandle();

        // Clica no botão que abre a tela de categorias em uma nova aba
        driver.findElement(By.cssSelector("[data-testid='new-category-button']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Aguarda a abertura da nova aba
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Troca o foco para a nova aba
        for (String janela : driver.getWindowHandles()) {
            if (!janela.equals(janelaAtual)) {
                driver.switchTo().window(janela);
                break;
            }
        }

        // Aguarda o botão "Adicionar Categoria" ficar clicável e clica nele
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-testid='add-category-btn']")
        )).click();
    }


}
