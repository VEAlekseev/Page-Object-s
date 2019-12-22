import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Selenide.open;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTransferTest {
    private static String serviceUrl = "http://localhost:9999";

    @ParameterizedTest
    @CsvFileSource(resources = "/TransferAmount.csv", numLinesToSkip = 1)
    void shouldTransferMoneyBetweenOwnCards(int amount, int expected, String message) {
        open(serviceUrl);
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);

        val initialBalanceOfFirstCard = dashboardPage.getBalanceFirstCard();
        val replenishmentPageOfFirstCard = dashboardPage.replenishFirstCard();
        val secondCardInfo = DataHelper.getSecondCardInfo();
        replenishmentPageOfFirstCard.transferAmountFromSecondCard(amount, secondCardInfo);
        assertEquals(expected, initialBalanceOfFirstCard + amount);
        dashboardPage.checkReturnToDashboardPage();
        //возвращаем деньги на вторую карту
        val initialBalanceOfSecondCard = dashboardPage.getBalanceSecondCard();
        val replenishmentPageOfSecondCard = dashboardPage.replenishSecondCard();
        replenishmentPageOfSecondCard.cleanFields();
        val firstCardInfo = DataHelper.getFirstCardInfo();
        replenishmentPageOfSecondCard.transferAmountFromFirstCard(amount, firstCardInfo);
        assertEquals(10000, initialBalanceOfSecondCard + amount);
        dashboardPage.checkReturnToDashboardPage();
    }
}