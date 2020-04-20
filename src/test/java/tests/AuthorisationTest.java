package tests;

import com.codeborne.selenide.Condition;
import data.Users;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import java.sql.SQLException;
import static com.codeborne.selenide.Selenide.open;

public class AuthorisationTest {

    @BeforeEach
    void openPage() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void clean() throws SQLException {
        Users.cleanCodes();
    }

    @Test
    @DisplayName("Авторизация с корректными данными")
    void correctAuthorisation() throws SQLException {
        val loginPage = new LoginPage();
        val authInfo = Users.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = Users.getVerificationCode();
        verificationPage.validVerify(verificationCode);
    }

    @Test
    @DisplayName("Авторизация с некорректным паролем")
    void incorrectAuthorisation() throws SQLException {
        val loginPage = new LoginPage();
        val authInfo = Users.getAuthInfo();
        val errorMessage = loginPage.invalidLogin(authInfo);
        errorMessage.shouldBe(Condition.visible);
    }
}