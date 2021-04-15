import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeSuite;


public class BaseTest {

    @BeforeSuite
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.baseUrl = "https://demoqa.com";
    }

}
