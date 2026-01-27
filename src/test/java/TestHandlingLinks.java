import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.List;

public class TestHandlingLinks {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        List<String> argList = new ArrayList<>();
        argList.add("--start-maximized");
        BrowserContext browserContext = playwright.chromium().launch(new BrowserType.LaunchOptions().setArgs(argList).setHeadless(false)).newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page = browserContext.newPage();
        page.navigate("https://wikipedia.org");
        System.out.println("Page title: " + page.title());

        Locator links = page.locator("a");
        links.count();

        System.out.println("Total number of links in the page: " + links.count());

        for(int i =0; i<links.count(); i++) {
            System.out.println(links.nth(i).getAttribute("href") + "->" + links.nth(i).innerText());
        }

        System.out.println("---------");

        Locator blockLinks = page.locator("nav > div.other-project > a");
        blockLinks.count();

        System.out.println("Number of links in the block: " + blockLinks.count());

        for(int i = 0; i <blockLinks.count(); i++) {
            System.out.println(blockLinks.nth(i).getAttribute("href") + "->" + blockLinks.nth(i).innerText());
        }

        page.close();
        browserContext.close();
        playwright.close();

    }
}
