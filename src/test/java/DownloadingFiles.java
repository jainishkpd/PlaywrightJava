import com.microsoft.playwright.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DownloadingFiles {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        List<String> argList = new ArrayList<>();
        argList.add("--start-maximized");
        BrowserContext browserContext = playwright.chromium().launch(new BrowserType.LaunchOptions().setArgs(argList).setHeadless(false)).newContext(new Browser.NewContextOptions().setViewportSize(null).setRecordVideoDir(Paths.get("videos/")));
        Page page = browserContext.newPage();
        page.navigate("https://www.selenium.dev/downloads/");
        System.out.println("Page title: " + page.title());

        Download file = page.waitForDownload(() -> {
            page.locator("(//a[@class='card-link'][normalize-space()='4.40.0 (January 18, 2026)'])[3]").click();
        });

        file.saveAs(Paths.get("./src/main/resources/Files/selenium-java-4.40.0.jar"));

        page.close();
        browserContext.close();
        playwright.close();

    }
}
