package ca.ulaval.glo4003.projet.base.ws.application.user;

import ca.ulaval.glo4003.projet.base.ws.domain.Id.IdGenerator;
import ca.ulaval.glo4003.projet.base.ws.domain.user.Gender;
import ca.ulaval.glo4003.projet.base.ws.domain.user.InvalidGenderException;
import ca.ulaval.glo4003.projet.base.ws.domain.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserAssemblerTest {

    private final static String ID = "ID";
    private final static String NAME = "Name";
    private final static String BIRTHDATE = "2020-09-21";
    private final static LocalDate LOCAL_BIRTHDATE = LocalDate.of(2020, Month.SEPTEMBER, 21);
    private final static String GENDER = "M";
    private final static Gender ENUM_GENDER = Gender.MALE;
    private final static int AGE = 21;
    private final static String ERROR = "ERROR";

    private UserAssembler userAssembler;
    private UserDto userDto;
    private User user;

    @Mock
    private IdGenerator idGenerator;

    @Before
    public void setUp() {
        userAssembler = new UserAssembler(idGenerator);
        userDto = new UserDto();
        userDto.name = NAME;
        userDto.birthDate = BIRTHDATE;
        userDto.gender = GENDER;
        userDto.age = AGE;
        user = new User(ID, NAME, LOCAL_BIRTHDATE, ENUM_GENDER, AGE);
    }

    @Test
    public void givenValidUserDto_whenAssemblingUser_thenShouldReturnUser() {
        User actualUser = userAssembler.create(userDto);

        assertEquals(NAME, actualUser.getName());
        assertEquals(BIRTHDATE, actualUser.getBirthDate().toString());
        assertEquals(GENDER, actualUser.getGender().toString());
        assertEquals(AGE, actualUser.getAge().intValue());
    }

    @Test(expected = InvalidGenderException.class)
    public void givenInvalidGender_whenAssemblingUser_thenShouldThrowInvalidGenderException() {
        userDto.gender = ERROR;

        userAssembler.create(userDto);
    }

    @Test(expected = DateTimeParseException.class)
    public void givenInvalidBirthDate_whenAssemblingUser_thenShouldThrowDateTimeParseException() {
        userDto.birthDate = ERROR;

        userAssembler.create(userDto);
    }

    @Test
    public void givenValidUser_whenAssemblingUserDto_thenShouldReturnUserDto() {
        UserDto actualUserDto = userAssembler.create(user);

        assertEquals(NAME, actualUserDto.name);
        assertEquals(BIRTHDATE, actualUserDto.birthDate);
        assertEquals(GENDER, actualUserDto.gender);
        assertEquals(AGE, actualUserDto.age.intValue());
    }

    @Test
    public void whenAssemblingUser_thenCallIdGenerator(){
        userAssembler.create(userDto);

        BDDMockito.verify(idGenerator).generateId();
    }
}
