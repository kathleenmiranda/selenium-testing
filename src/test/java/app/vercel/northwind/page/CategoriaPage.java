package app.vercel.northwind.page;

import app.vercel.northwind.utils.TestData;
import app.vercel.northwind.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class CategoriaPage {
    private final WebDriver driver;
    private static final By btnNewCategory =
            By.cssSelector("[data-testid='new-category-button']");
    private static final By addNewCategory =
            By.cssSelector("[data-testid='add-category-btn']");

    private final By inputDescription =
            By.cssSelector("[data-testid='category-description-input']");
    private final By inputCategory =
            By.cssSelector("[data-testid='category-name-input']");

    private final By errorNameCategory =
            By.cssSelector("[data-testid='error-category-name']");
    private final By errorDescription =
            By.cssSelector("[data-testid='error-category-description']");
    private final By btnSaveCategory =
            By.cssSelector("[data-testid='save-category-btn']");
    private final By toast =
            By.cssSelector(".Toastify__toast--success");

    private final By toastCategoryDeleteSuccess =
            By.cssSelector(".Toastify__toast");

    private final By searchCategory =
            By.cssSelector("[data-testid='category-search']");
    private final By inputEditCategoryName =
            By.cssSelector("[data-testid='edit-category-name-input']");
    private final By btnUpdateCategory =
            By.cssSelector("[data-testid='update-category-btn']");
    private final By btnConfirmDeleteCategory =
            By.cssSelector("[data-testid='confirm-delete-category-btn']");


    public CategoriaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void preencherNomeCategoria(String nameCategory){
        driver.findElement(inputCategory
        ).sendKeys(nameCategory);
    }
    public void preencherDescricaoCategoria(String descricao){
        driver.findElement(inputDescription
        ).sendKeys(descricao);
    }

    public void clicarBtnSalvarCategoria(){
        driver.findElement(btnSaveCategory).click();
    }

    public void pesquisarCategoria(String category){
        driver.findElement(searchCategory
        ).sendKeys(category);
    }

    public void abrirModalEditarCategoria() {
        obterPrimeiraCategoria().click();
    }

    public void openModalDeleteCategory(){
        obterPrimeiroItemDeCategoriaParaExclusao(

        ).click();
    }

    public void clickConfirmDeleteCategory(){
        driver.findElement(btnConfirmDeleteCategory).click();
    }
    public void limparNomeCategoria(){
        driver.findElement(
                inputEditCategoryName
        ).clear();
    }

    public void editarNomeCategoria(String novoNomeCategoria){
        driver.findElement(inputEditCategoryName
        ).sendKeys(novoNomeCategoria);
    }
    public void clicarBotaoAtualizarCategoria(){
        driver.findElement(
                btnUpdateCategory
        ).click();

    }


    public String obterMensagemErroNomeCategoria() {
        return driver.findElement(errorNameCategory
        ).getText();
    }
    public boolean mensagemErroNomeCategoriaVisivel() {
        return driver.findElement(errorNameCategory
        ).isDisplayed();
    }

    public String obterMensagemDeleteCategoria() {
        WaitUtil.esperarElementoVisivel(
                driver,
                toastCategoryDeleteSuccess
        );
        return driver.findElement(
                toastCategoryDeleteSuccess
        ).getText();
    }
    public boolean mensagemCategoriaExcluidaVisivel() {
        WaitUtil.esperarElementoVisivel(
                driver,
                toastCategoryDeleteSuccess
        );

        return driver.findElement(toastCategoryDeleteSuccess
        ).isDisplayed();
    }
    public String obterMensagemErroDescricaoCategoria() {
        return driver.findElement(errorDescription
        ).getText();
    }

    public boolean mensagemErroDescricaoCategoriaVisivel() {
        return driver.findElement(errorDescription
        ).isDisplayed();
    }

    public boolean mensagemSucessoCriacaoCategoria(){
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

    public void abrirModalNovaCategoria() {
        WaitUtil.esperarElementoClicavel(
                driver,
                addNewCategory
        ).click();
    }

    public void abrirTelaCategorias(WebDriver driver) {

        String janelaAtual = driver.getWindowHandle();

        driver.findElement(btnNewCategory).click();

        WaitUtil.esperarQuantidadeDeJanelas(driver, 2);

        for (String janela : driver.getWindowHandles()) {
            if (!janela.equals(janelaAtual)) {
                driver.switchTo().window(janela);
                break;
            }
        }

        WaitUtil.esperarElementoVisivel(driver, addNewCategory);
    }

    public WebElement obterPrimeiraCategoria() {
        List<WebElement> categorias = driver.findElements(
                By.cssSelector("[data-testid^='edit-category-']")
        );

        if (categorias.isEmpty()) {
            throw new IllegalStateException(
                    "Nenhuma categoria encontrada."
            );
        }
        return categorias.get(0);
    }

    public WebElement obterPrimeiroItemDeCategoriaParaExclusao() {
        List<WebElement> excluirCategoria =
                driver.findElements(
                        By.cssSelector("[data-testid^='delete-category-']")
        );
        if (excluirCategoria.isEmpty()) {
            throw new IllegalStateException(
                    "Nenhuma categoria encontrada para exclusão."
            );
        }

        return excluirCategoria.get(0);
    }

}
