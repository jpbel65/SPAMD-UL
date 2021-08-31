package ca.ulaval.glo4003.projet.base.ws.application.user;

public class UserDto {

    public String id;
    public String name;
    public String birthDate;
    public String gender;
    public Integer age;

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }
}
