package ca.ulaval.glo4003.projet.base.ws.domain.user;

import java.time.LocalDate;

public class User {

    private final String id;
    private final String name;
    private final LocalDate birthDate;
    private final Gender gender;
    private final Integer age;

    public User(String id, String name, LocalDate birthDate, Gender gender, Integer age) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }
}
