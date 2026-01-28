import com.microsoft.playwright.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UploadingFiles {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        List<String> argList = new ArrayList<>();
        argList.add("--start-maximized");
        BrowserContext browserContext = playwright.chromium().launch(new BrowserType.LaunchOptions().setArgs(argList).setHeadless(false)).newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page = browserContext.newPage();
        page.navigate("https://www.way2automation.com/way2auto_jquery/registration.php#load_box");
        System.out.println("Page title: " + page.title());

        page.locator("input[type='file']").setInputFiles(Paths.get("./src/main/resources/Files/1.jpg"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        page.close();
        browserContext.close();
        playwright.close();

    }
}
