package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.elements;
import static com.codeborne.selenide.Selenide.page;


public class MainPage {

    List<SelenideElement> blocks = elements(By.cssSelector(".card.mt-4.top-card"));

    public FormsPage openFormsPage() {
        blocks.get(1).click();
        return page(FormsPage.class);
    }
}
