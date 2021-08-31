package ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.ResourceNotFoundException;

public class ReportNotFoundException extends ResourceNotFoundException {

    private final static String ERROR_CODE = "RAPPORT_NOT_FOUND";
    private final static String ERROR_MESSAGE = "Rapport with date %s not found";

    public ReportNotFoundException(String yearMonth) {
        super(ERROR_CODE, String.format(ERROR_MESSAGE, yearMonth));
    }
}
