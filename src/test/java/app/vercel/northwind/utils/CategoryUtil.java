package app.vercel.northwind.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategoryUtil {

    public static void acessarTelaCategorias(WebDriver driver) {

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

    public static void salvarCategoria(WebDriver driver) {

        driver.findElement(
                By.cssSelector("[data-testid='save-category-btn']")
        ).click();
    }

    public static void cancelarCadastro(WebDriver driver) {

        driver.findElement(
                By.cssSelector("[data-testid='cancel-category-btn']")
        ).click();
    }

}