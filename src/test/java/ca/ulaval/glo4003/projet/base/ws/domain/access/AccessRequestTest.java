package ca.ulaval.glo4003.projet.base.ws.domain.access;

import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessPermit;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccessRequestTest {
  private static final String ANY_CODE = "42";
  private static final LocalDateTime ANY_DATETIME = LocalDateTime.now();
  private static final AccessType ANY_TYPE = AccessType.AUTOMOBILE;

  private final AccessRequest accessRequest = new AccessRequest(ANY_CODE, ANY_TYPE, ANY_DATETIME);

  @Mock AccessPermit accessPermit;

  @Test
  public void whenVerify_thenVerifyDateTime() {
    accessRequest.verify(accessPermit);

    verify(accessPermit).verifyDateTime(ANY_DATETIME);
  }

  @Test
  public void whenVerify_thenVerifyAccessType() {
    accessRequest.verify(accessPermit);

    verify(accessPermit).verifyType(ANY_TYPE);
  }
}
