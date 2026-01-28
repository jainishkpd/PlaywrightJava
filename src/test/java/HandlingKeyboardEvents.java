import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.List;

public class HandlingKeyboardEvents {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        List<String> argList = new ArrayList<>();
        argList.add("--start-maximized");
        BrowserContext browserContext = playwright.chromium().launch(new BrowserType.LaunchOptions().setArgs(argList).setHeadless(false)).newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page = browserContext.newPage();
        page.navigate("http://login.yahoo.com");
        System.out.println("Page title: " + page.title());

        //page.locator("#login-username").type("abcd@gmail.com");
        page.locator("#login-username").type("abcd@gmail.com", new Locator.TypeOptions().setDelay(100));
        page.keyboard().press("Enter");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        page.goBack();
        page.keyboard().press("Control+A");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        page.keyboard().press("Control+X");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        page.keyboard().press("Control+V");
        page.keyboard().down("Shift");

        for (int i = 0; i < 3; i++) {
            page.keyboard().press("ArrowLeft");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        page.close();
        browserContext.close();
        playwright.close();

    }
}
