import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    public void checkReturnToDashboardPage() {
        $("[data-test-id=dashboard]").shouldBe(Condition.visible);
        $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button").shouldBe(Condition.visible);
        $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button").shouldBe(Condition.visible);
    }

    public int getBalanceFirstCard() {
        String balanceText = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']").getText();
        int startInd = balanceText.indexOf("баланс: ");
        int finishInd = balanceText.indexOf(" р.");
        String balance = balanceText.substring(startInd + 8, finishInd);
        return Integer.parseInt(balance);
    }

    public int getBalanceSecondCard() {
        String balanceText = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']").getText();
        int startInd = balanceText.indexOf("баланс: ");
        int finishInd = balanceText.indexOf(" р.");
        String balance = balanceText.substring(startInd + 8, finishInd);
        return Integer.parseInt(balance);
    }
    public ReplenishmentPage replenishFirstCard (){
        $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button").click();
        return new ReplenishmentPage();
    }

    public ReplenishmentPage replenishSecondCard (){
        $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button").click();
        return new ReplenishmentPage();
    }

}