import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.List;

public class HandlingShadowRootElement {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        List<String> argList = new ArrayList<>();
        argList.add("--start-maximized");
        BrowserContext browserContext = playwright.chromium().launch(new BrowserType.LaunchOptions().setArgs(argList).setHeadless(false)).newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page = browserContext.newPage();
        page.navigate("chrome://downloads/");
        System.out.println("Page title: " + page.title());

        page.locator("#searchInput").type("Java");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        page.close();
        browserContext.close();
        playwright.close();
    }
}
