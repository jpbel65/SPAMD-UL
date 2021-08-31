package ca.ulaval.glo4003.projet.base.ws.domain.permit.access;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.ResourceNotFoundException;

public class PeriodNotFoundException extends ResourceNotFoundException {
  private final static String ERROR_CODE = "PERIOD_NOT_FOUND";
  private final static String ERROR_MESSAGE = "period %s not found";

  public PeriodNotFoundException(String period) {
    super(ERROR_CODE, String.format(ERROR_MESSAGE, period));
  }
}
