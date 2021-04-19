package tests;

import com.codeborne.selenide.Condition;
import models.User;
import models.user_attributes.Gender;
import org.testng.annotations.Test;
import pages.FormsPage;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertTrue;


public class TestCases extends BaseTest {

    @Test
    public void testCase1() {
        Gender gender = Gender.Other;
        User user = User.newUser(gender);

        FormsPage formsPage = open("/", MainPage.class)
                .openFormsPage()
                .clickPracticeForm()
                .fillUserForm(user, gender);

        formsPage.resultFormTitle().shouldHave(Condition.text("Thanks for submitting the form"));

        formsPage.resultFormData().shouldHave(Condition.text(String.format("%s %s", user.getFirstName().toString(), user.getLastName().toString())));
        formsPage.resultFormData().shouldHave(Condition.text(user.getPhoneNumber().toString()));
        formsPage.resultFormData().shouldHave(Condition.text(user.getGender().toString()));
    }

    @Test
    public void testCase2(){

        FormsPage formsPage = open("/", MainPage.class).
                openFormsPage().
                clickPracticeForm().
                submitEmptyForm();

        assertTrue(formsPage.isFieldRequired(formsPage.firstNameField), "First name is not required field");
        assertTrue(formsPage.isFieldRequired(formsPage.lastNameField), "Last name is not required field");
        assertTrue(formsPage.isFieldRequired(formsPage.phoneField ), "Phone number is not required field");
        assertTrue(formsPage.isRadioButtonUnchecked(Gender.Male), "Male radio button is checked");
        assertTrue(formsPage.isRadioButtonUnchecked(Gender.Female), "Female radio button is checked");
        assertTrue(formsPage.isRadioButtonUnchecked(Gender.Other), "Other radio button is checked");

    }
}
