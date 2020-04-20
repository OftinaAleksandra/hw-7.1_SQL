package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement HEADING = $("[data-test-id=dashboard]");

    public DashboardPage() {
        HEADING.shouldBe(Condition.visible);
    }
}
