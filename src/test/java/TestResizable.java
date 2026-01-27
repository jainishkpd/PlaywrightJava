import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.List;

public class TestResizable {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        List<String> argList = new ArrayList<>();
        argList.add("--start-maximized");
        BrowserContext browserContext = playwright.chromium().launch(new BrowserType.LaunchOptions().setArgs(argList).setHeadless(false)).newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page = browserContext.newPage();
        page.navigate("https://jqueryui.com/resources/demos/resizable/default.html");
        System.out.println("Page title: " + page.title());

        Locator resizable = page.locator("//*[@id=\"resizable\"]/div[3]");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        page.mouse().move(resizable.boundingBox().x + resizable.boundingBox().width/2, resizable.boundingBox().y + resizable.boundingBox().height/2);
        page.mouse().down();
        page.mouse().move(resizable.boundingBox().x + 400, resizable.boundingBox().y+ 400);
        page.mouse().up();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        page.close();
        browserContext.close();
        playwright.close();
    }
}
