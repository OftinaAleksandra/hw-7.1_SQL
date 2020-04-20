package data;

import lombok.Data;
import lombok.Value;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

@Data
public class Users {
    private Users() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        String usersCode;
    }

    public static VerificationCode getVerificationCode() throws SQLException {
        String verificationCode;
        val doSelectCode = "SELECT * FROM auth_codes ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(
                "jdbc:mysql://192.168.99.100:3306/db", "user", "pass")) {
            val code = runner.query(conn, doSelectCode, new BeanHandler<>(data.VerificationCode.class));
            verificationCode = code.getCode();
        }
        return new VerificationCode(verificationCode);
    }

    public static void cleanCodes() throws SQLException {
        val doDeleteCodes = "DELETE FROM auth_codes";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(
                "jdbc:mysql://192.168.99.100:3306/db", "user", "pass")) {
            val cleanCodesUser = runner.execute(conn, doDeleteCodes, new BeanHandler<>(data.VerificationCode.class));
        }
    }
    

}