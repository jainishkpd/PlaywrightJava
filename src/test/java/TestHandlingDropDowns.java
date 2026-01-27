import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

import java.util.ArrayList;
import java.util.List;

public class TestHandlingDropDowns {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        List<String> argList = new ArrayList<>();
        argList.add("--start-maximized");
        BrowserContext browserContext = playwright.chromium().launch(new BrowserType.LaunchOptions().setArgs(argList).setHeadless(false)).newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page = browserContext.newPage();
        page.navigate("http://www.wikipedia.org");
        System.out.println("Page title: " + page.title());

        // Select by value
        page.selectOption("select", "hi");

        // Select by text
        page.selectOption("select", new SelectOption().setLabel("Eesti"));

        // Select by index
        page.selectOption("select", new SelectOption().setIndex(10));

        Locator values = page.locator("select > option");
        System.out.println("Total number of values in the dropdown: " + values.count());

        for (int i = 0; i < values.count(); i++) {
            System.out.println(values.nth(i).innerText() + "  ---> " + values.nth(i).getAttribute("value"));
        }

        System.out.println("-------------------");

        page.querySelectorAll("select > option").forEach(option -> {
            System.out.println(option.innerText() + " ==> " + option.getAttribute("value"));
        });

        System.out.println("-------------------");

        List<ElementHandle> listValues = page.querySelectorAll("select > option");
        System.out.println("Total number of values in the dropdown: " + listValues.size());

        for (ElementHandle e: listValues){
            System.out.println(e.innerText() + " ==> " + e.getAttribute("value"));
        }

        page.close();
        browserContext.close();
        playwright.close();

    }
}
