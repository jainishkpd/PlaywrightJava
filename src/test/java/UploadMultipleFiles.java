import com.microsoft.playwright.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UploadMultipleFiles {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        List<String> argList = new ArrayList<>();
        argList.add("--start-maximized");
        BrowserContext browserContext = playwright.chromium().launch(new BrowserType.LaunchOptions().setArgs(argList).setHeadless(false)).newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page = browserContext.newPage();
        page.navigate("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_fileupload_multiple");
        System.out.println("Page title: " + page.title());

        page.frameLocator("[name='iframeResult']").locator("#myFile").setInputFiles(new Path[] {
                Paths.get("./src/main/resources/Files/1.jpg"),
                Paths.get("./src/main/resources/Files/2.jpeg")
        });

        page.frameLocator("#iframeResult").locator("[type='submit']").click(new Locator.ClickOptions().setTimeout(2000));
        String uploadedFiles = page.frameLocator("#iframeResult").locator("body > div.w3-container.w3-large.w3-border").innerText();
        System.out.println("Uploaded files: " + uploadedFiles);

        page.close();
        browserContext.close();
        playwright.close();
    }
}
