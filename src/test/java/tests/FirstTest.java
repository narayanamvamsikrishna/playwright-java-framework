package tests;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.BaseTest;

@Listeners(listeners.TestListener.class)
public class FirstTest extends BaseTest {

    @Test(dataProvider = "browsers",
      dataProviderClass = utils.DataProviders.class)

    public void googleTest( String browser) {
        page.navigate("https://google.com");
        System.out.println("Browser: " + browser);
        System.out.println(page.title());
    }

    @Test(dataProvider = "browsers",
      dataProviderClass = utils.DataProviders.class)
    public void amazonTest( String browser) {
        page.navigate("https://amazon.in");
        System.out.println("Browser: " + browser);
        System.out.println(page.title());
    }
}