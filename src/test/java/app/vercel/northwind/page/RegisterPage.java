package app.vercel.northwind.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private final WebDriver driver;
    private final By inputPassword =
            By.cssSelector("[data-testid='password-input']");
    private final By inputName =
            By.cssSelector("[data-testid='full-name-input']");
    private final By  inputEmail =
            By.cssSelector("[data-testid='email-input']");
    private final By inputConfirmSenha =
            By.cssSelector("[data-testid='confirm-password-input']");
    private  final By emailError =
            By.cssSelector("[data-testid='email-error']");
    private final By passwordError =
            By.cssSelector("[data-testid='password-error']");
    private final By nameError =
            By.cssSelector("[data-testid='full-name-error']");
    private final By confirmPasswordError =
            By.cssSelector("[data-testid='confirm-password-error']");
    private final By btnRegister =
            By.cssSelector("[data-testid='register-button']");
    private final By msg_sucess =
            By.cssSelector(".Toastify__toast-container");
    private final By iconSucess =
            By.cssSelector(".Toastify__toast-icon");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    private String obterTexto(By by) {
        return driver.findElement(by).getText();
    }

    public void preencherConfirmSenha(String confirmSenha){
        driver.findElement(inputConfirmSenha).sendKeys(confirmSenha);
    }

    public void preencherNomeCompleto(String nome) {
        driver.findElement(inputName).sendKeys(nome);
    }

    public String preencherEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
        return email;
    }

    public void pressionarBackspaceEmail() {
        driver.findElement(inputEmail).sendKeys(Keys.BACK_SPACE);
    }

    public String obterMensagemErroEmail() {
        return obterTexto(emailError);
    }

    public boolean mensagemErroEmailVisivel() {
        return driver.findElement(emailError).isDisplayed();
    }


    public void preencherSenha(String senha) {
        driver.findElement(inputPassword).sendKeys(senha);
    }

    public void pressionarBackspaceSenha() {
        driver.findElement(inputPassword).sendKeys(Keys.BACK_SPACE);
    }

    public String obterMensagemErroSenha() {
        return obterTexto(passwordError);
    }

    public boolean mensagemErroSenhaVisivel() {
        return driver.findElement(passwordError).isDisplayed();
    }

    public void limparSenha(){
        driver.findElement(inputPassword).clear();
    }

    public boolean mensagemErroSenhaNaoExiste() {
        return driver.findElements(passwordError).isEmpty();
    }

    public void limparNome(){
        driver.findElement(inputName).clear();
    }
    public void pressionarBackspaceNome() {
        driver.findElement(inputName).sendKeys(Keys.BACK_SPACE);
    }

    public boolean mensagemErroNomeVisivel() {
        return driver.findElement(nameError).isDisplayed();
    }

    public String obterMensagemErroNome() {
        return obterTexto(nameError);
    }

    public String obterMensagemErroConfirmSenha() {
        return obterTexto(confirmPasswordError);
    }

    public boolean mensagemErroConfirmSenhaVisivel() {
        return driver.findElement(confirmPasswordError).isDisplayed();
    }
    public void pressionarBackspaceConfirmSenha() {
        driver.findElement(inputConfirmSenha).sendKeys(Keys.BACK_SPACE);
    }
    public void clicarBotao() {
        driver.findElement(btnRegister).click();
    }

    public boolean mensagemContaCriadaComSucessoVisivel(){
       return driver.findElement(msg_sucess).isDisplayed();
    }
    public String obterMensagemConfirmacaoContacriada(){
      return obterTexto(msg_sucess);
    }


}
