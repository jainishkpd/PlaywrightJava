import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.List;

public class TestDroppable {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        List<String> argList = new ArrayList<>();
        argList.add("--start-maximized");
        BrowserContext browserContext = playwright.chromium().launch(new BrowserType.LaunchOptions().setArgs(argList).setHeadless(false)).newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page = browserContext.newPage();
        page.navigate("https://jqueryui.com/resources/demos/droppable/default.html");
        System.out.println("Page title: " + page.title());

        Locator draggable = page.locator("#draggable");
        Locator droppable = page.locator("#droppable");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        page.mouse().move(draggable.boundingBox().x + draggable.boundingBox().width/2, draggable.boundingBox().y + draggable.boundingBox().height/2);
        page.mouse().down();
        page.mouse().move(droppable.boundingBox().x + droppable.boundingBox().width/2, droppable.boundingBox().y+ droppable.boundingBox().height/2);
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
