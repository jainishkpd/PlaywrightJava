import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.List;

public class HandlingAlerts {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        List<String> argList = new ArrayList<>();
        argList.add("--start-maximized");
        BrowserContext browserContext = playwright.chromium().launch(new BrowserType.LaunchOptions().setArgs(argList).setHeadless(false)).newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page = browserContext.newPage();
        page.navigate("http://mail.rediff.com/cgi-bin/login.cgi");
        System.out.println("Page title: " + page.title());

        page.onDialog(dialog -> {
            dialog.accept();
            System.out.println("Alert message: " + dialog.message());
        });

        page.locator("[type='submit']").click();
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
