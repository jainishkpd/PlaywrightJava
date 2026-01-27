import com.microsoft.playwright.*;
import com.microsoft.playwright.Playwright;

import java.awt.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LaunchBrowsers {

    public static void main(String[] args) {

        System.out.println("Approach 1");

        Playwright playwright = Playwright.create();
        List<String> argList = new ArrayList<>();
        argList.add("--start-maximized");
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setArgs(argList).setHeadless(false));
        BrowserContext browserContext =  browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page = browserContext.newPage();

        page.navigate("https://way2automation.com");
        System.out.println("page title: " + page.title());

        page.close();
        browserContext.close();
        browser.close();
        playwright.close();

        System.out.println("Approach 2");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width =  screenSize.getWidth();
        double height = screenSize.getHeight();

        System.out.println("Width: " + width + "and Height: " + height);

        Playwright playwright1 = Playwright.create();
        BrowserContext browserContext1 = playwright1.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false).setExecutablePath(Paths.get("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe")))
                .newContext(new Browser.NewContextOptions()
                        .setViewportSize((int) width, (int) height));
        Page page1 = browserContext1.newPage();
        page1.navigate("https://way2automation.com");
        System.out.println("page title: " + page1.title());

        page1.close();
        browserContext1.close();
        playwright1.close();
    }
}
