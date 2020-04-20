package page;

import com.codeborne.selenide.SelenideElement;
import data.Users;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginField = $("[name=login]");
    private final SelenideElement passwordField = $("[name=password]");
    private final SelenideElement loginButton = $("[data-test-id=action-login]");
    private final SelenideElement errorMessage = $("[data-test-id=error-notification]");

    public VerificationPage validLogin (Users.AuthInfo info) {

        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }


    public SelenideElement invalidLogin (Users.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue("12345");
        loginButton.click();
        return errorMessage;
    }
}
