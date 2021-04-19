package models;

import com.github.javafaker.Faker;
import models.user_attributes.FirstName;
import models.user_attributes.Gender;
import models.user_attributes.LastName;
import models.user_attributes.PhoneNumber;

import java.util.Objects;

public class User {
    private final FirstName firstName;
    private final LastName lastName;
    private final PhoneNumber phoneNumber;
    private final Gender gender;

    private User(FirstName firstName, LastName lastName, PhoneNumber phoneNumber, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public static User newUser(Gender gender) {
        Faker faker = new Faker();
        User user = new User(
                new FirstName(faker.name().firstName()),
                new LastName(faker.name().lastName()),
                new PhoneNumber(faker.number().digits(10)),
                gender);
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                gender == user.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumber, gender);
    }

    public FirstName getFirstName() {
        return firstName;
    }

    public LastName getLastName() {
        return lastName;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

}
