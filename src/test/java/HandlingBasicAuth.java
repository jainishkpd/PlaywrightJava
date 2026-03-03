import com.microsoft.playwright.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HandlingBasicAuth {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        List<String> argList = new ArrayList<>();
        argList.add("--start-maximized");
        BrowserContext browserContext = playwright.chromium().launch(new BrowserType.LaunchOptions().setArgs(argList).setHeadless(false)).newContext(new Browser.NewContextOptions().setViewportSize(null).setHttpCredentials("admin", "admin"));
        Page page = browserContext.newPage();
        page.navigate("http://the-internet.herokuapp.com/basic_auth");
        System.out.println("Page title: " + page.title());

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./screenshots/BasicAuth.png")));

        page.close();
        browserContext.close();
        playwright.close();
    }
}
