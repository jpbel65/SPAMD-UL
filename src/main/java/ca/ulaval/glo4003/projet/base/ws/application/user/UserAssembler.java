package ca.ulaval.glo4003.projet.base.ws.application.user;

import ca.ulaval.glo4003.projet.base.ws.domain.Id.IdGenerator;
import ca.ulaval.glo4003.projet.base.ws.domain.user.Gender;
import ca.ulaval.glo4003.projet.base.ws.domain.user.User;

import java.time.LocalDate;
import java.util.UUID;

public class UserAssembler {
    private final IdGenerator idGenerator;

    public UserAssembler(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public User create(UserDto userDto) {
        String id = idGenerator.generateId();
        LocalDate birthDate = LocalDate.parse(userDto.birthDate);
        Gender gender = Gender.fromString(userDto.gender);
        return new User(id, userDto.name, birthDate, gender, userDto.age);
    }

    public UserDto create(User user) {
        UserDto userDto = new UserDto();
        userDto.id = user.getId();
        userDto.name = user.getName();
        userDto.birthDate = user.getBirthDate().toString();
        userDto.gender = user.getGender().toString();
        userDto.age = user.getAge();
        return userDto;
    }
}
