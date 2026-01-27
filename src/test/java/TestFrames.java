import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.List;

public class TestFrames {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        List<String> argList = new ArrayList<>();
        argList.add("--start-maximized");
        BrowserContext browserContext = playwright.chromium().launch(new BrowserType.LaunchOptions().setArgs(argList).setHeadless(false)).newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page = browserContext.newPage();
        page.navigate("http://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_submit_get");
        System.out.println("Page title: " + page.title());

        page.frameLocator("[name='iframeResult']").locator("body > button").click(new Locator.ClickOptions().setTimeout(3000));
        String getText = page.frameLocator("[name='iframeResult']").locator("#demo").innerText();
        System.out.println("Text after clicking the button inside Iframe: " + getText);

        Locator frames = page.locator("iframe");
        System.out.println("Total number of frames in the page: " + frames.count());

        for (int i = 0; i < frames.count(); i++) {
            System.out.println(frames.nth(i).getAttribute("id") + " ---> " + frames.nth(i).getAttribute("name"));
        }

        page.close();
        browserContext.close();
        playwright.close();
    }
}
