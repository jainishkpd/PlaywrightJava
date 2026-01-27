import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.List;

public class TestEvaluateJs {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        List<String> argList = new ArrayList<>();
        argList.add("--start-maximized");
        BrowserContext browserContext = playwright.chromium().launch(new BrowserType.LaunchOptions().setArgs(argList).setHeadless(false)).newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page = browserContext.newPage();
        page.navigate("http://google.com");
        System.out.println("Page title: " + page.title());

        page.evaluate("document.location.href");

        page.evaluate("() => {"
            + "const textarea = document.createElement('textarea');"
                + "document.body.append(textarea);"
                +"textarea.focus();"
                + "}");
        String text = "Hello from Playwright!";
        page.keyboard().type(text);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        page.close();
        browserContext.close();
        playwright.close();

    }
}
