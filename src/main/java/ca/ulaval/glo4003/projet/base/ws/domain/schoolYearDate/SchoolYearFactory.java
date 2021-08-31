package ca.ulaval.glo4003.projet.base.ws.domain.schoolYearDate;

import java.time.LocalDate;

public class SchoolYearFactory {
    public SchoolYearDate create(){
        return new SchoolYearDate(LocalDate.now());
    }

}
