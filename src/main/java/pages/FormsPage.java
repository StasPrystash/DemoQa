package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import models.User;
import models.user_attributes.Gender;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class FormsPage {

    public SelenideElement firstNameField = element(By.id("firstName"));
    public SelenideElement lastNameField = element(By.id("lastName"));
    public SelenideElement phoneField = element(By.id("userNumber"));
    private SelenideElement practiceForm = element(By.xpath("//*[contains(text(), 'Practice Form')]"));
    private SelenideElement submitButton = element(By.cssSelector("button[id='submit']"));
    private SelenideElement title = element(By.className("modal-title"));
    private SelenideElement resultForm = element(By.className("modal-body"));
    private List<SelenideElement> requiredFields  = elements(By.cssSelector(".form-control:invalid"));

    public FormsPage clickPracticeForm() {
        practiceForm.click();
        return page(FormsPage.class);
    }

    public FormsPage submitEmptyForm() {
        executeJavaScript("arguments[0].click();", submitButton);
        return this;
    }

    public FormsPage fillUserForm(User user, Gender gender) {
        firstNameField.setValue(user.getFirstName().toString());
        lastNameField.setValue(user.getLastName().toString());
        phoneField.setValue(user.getPhoneNumber().toString());
        selectGender(gender);
        executeJavaScript("arguments[0].click();", submitButton);
        return this;
    }

    public void selectGender(Gender gender) {
        $("input[value='" + gender + "']").parent().click();
    }

    public SelenideElement resultFormTitle() {
        return title;
    }

    public SelenideElement resultFormData() {
        return resultForm;
    }

    public boolean isFieldRequired(SelenideElement element){
        return requiredFields.contains(element);
    }

    public boolean isRadioButtonUnchecked(Gender gender){
        return $("input[value='" + gender + "']").parent().is(not(Condition.selected));
    }

}
