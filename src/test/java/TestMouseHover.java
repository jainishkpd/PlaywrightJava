import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.List;

public class TestMouseHover {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        List<String> argList = new ArrayList<>();
        argList.add("--start-maximized");
        BrowserContext browserContext = playwright.chromium().launch(new BrowserType.LaunchOptions().setArgs(argList).setHeadless(false)).newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page = browserContext.newPage();
        page.navigate("http://way2automation.com");
        System.out.println("Page title: " + page.title());

        page.locator("//*[@id=\"menu-item-27580\"]/a/span[2]").hover();
        page.locator("//*[@id=\"menu-item-27592\"]/a/span[2]").click();

        System.out.println("Navigated to: " + page.title());

        page.close();
        browserContext.close();
        playwright.close();

    }
}
