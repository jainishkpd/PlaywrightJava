import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.List;

public class HandlingCheckBoxes {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        List<String> argList = new ArrayList<>();
        argList.add("--start-maximized");
        BrowserContext browserContext = playwright.chromium().launch(new BrowserType.LaunchOptions().setArgs(argList).setHeadless(false)).newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page = browserContext.newPage();
        page.navigate("http://tizag.com/htmlT/htmlcheckboxes.php");
        System.out.println("Page title: " + page.title());

        Locator block = page.locator("(//div[@class='display'])[1]");
        Locator checkboxes = block.locator("input[type='checkbox']");

        System.out.println("Total number of checkboxes in the block: " + checkboxes.count());

        for (int i = 0; i < checkboxes.count(); i++) {
            //checkboxes.nth(i).check();
            checkboxes.nth(i).click();
        }

        page.close();
        browserContext.close();
        playwright.close();
    }
}
