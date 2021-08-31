package ca.ulaval.glo4003.projet.base.ws.domain.user;

public enum Gender {
    MALE("M"),
    FEMALE("F"),
    OTHER("O");

    private final String genderCode;

    Gender(String genderCode) {
        this.genderCode = genderCode;
    }

    @Override
    public String toString() {
        return genderCode;
    }

    public static Gender fromString(String genderCode) throws InvalidGenderException {
        for (Gender gender : Gender.values()) {
            if(genderCode.toUpperCase().equals(gender.genderCode.toUpperCase())) {
                return gender;
            }
        }

        throw new InvalidGenderException(genderCode);
    }
}
