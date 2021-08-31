package ca.ulaval.glo4003.projet.base.ws.application.accessRequest;

public class AccessRequestDto {
    public String accessCode;
    public String accessType;

    @Override
    public String toString() {
        return "AccessRequestDto{" +
               "accessCode='" + accessCode + '\'' +
               ", accessType='" + accessType + '\'' +
               '}';
    }
}
