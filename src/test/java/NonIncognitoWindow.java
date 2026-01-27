import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class NonIncognitoWindow {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        List<String> argList = new ArrayList<>();
        argList.add("--start-maximized");
        BrowserContext browserContext = playwright.chromium().launchPersistentContext(Paths.get(""), new BrowserType.LaunchPersistentContextOptions()
                .setChannel("chrome").setHeadless(false).setArgs(argList).setViewportSize(null));

        Page page = browserContext.newPage();
        page.navigate("https://way2automation.com");
        System.out.println("page title: " + page.title());

        page.close();
        browserContext.close();
        playwright.close();
    }
}
