import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.List;

public class HandlingTabsAndPopups {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        List<String> argList = new ArrayList<>();
        argList.add("--start-maximized");
        BrowserContext browserContext = playwright.chromium().launch(new BrowserType.LaunchOptions().setArgs(argList).setHeadless(false)).newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page = browserContext.newPage();
        page.navigate("http://sso.teachable.com/secure/673/identity/sign_up/email");
        System.out.println("Page title: " + page.title());

        Page popupPage = page.waitForPopup(() -> {
            page.locator("text=Privacy Policy").nth(0).click();
        });

        popupPage.locator("//*[@id='header-sign-up-btn']").click();
        popupPage.locator("#name").fill("John Doe");
        popupPage.locator("#email").fill("johndoe@email.com");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        popupPage.close();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        page.close();
        browserContext.close();
        playwright.close();
    }
}
