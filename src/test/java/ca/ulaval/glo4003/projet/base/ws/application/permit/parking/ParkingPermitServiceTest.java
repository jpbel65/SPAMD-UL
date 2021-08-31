package ca.ulaval.glo4003.projet.base.ws.application.permit.parking;

import ca.ulaval.glo4003.projet.base.ws.application.infraction.InfractionService;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.contravention.Contravention;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.Infraction;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.validator.PermitValidator;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.*;
import ca.ulaval.glo4003.projet.base.ws.domain.price.ParkingPriceRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.zone.Zone;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound.ParkingPermitNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ParkingPermitServiceTest {

    private static final String PERMIT_ID = "9659";
    private static final Zone A_ZONE = Zone.ZONE_1;
    private static final ParkingPeriod A_PARKING_PERIOD = ParkingPeriod.ONE_DAY_PER_WEEK_PER_SESSION;
    private static final String CONTRAVENTION_ID = "42";

    private ParkingPermitService parkingPermitService;
    private ParkingPermitDto parkingPermitDto;
    private final ContraventionDto contraventionDto = new ContraventionDto();
    private final ContraventionDto returnedContraventionDto = new ContraventionDto();

    @Mock
    public Price price;
    @Mock
    private ParkingPermit parkingPermit;
    @Mock
    private ParkingPermitRepository parkingPermitRepository;
    @Mock
    private ParkingPermitAssembler parkingPermitAssembler;
    @Mock
    private ParkingPriceRepository parkingPriceRepository;
    @Mock
    private ContraventionAssembler contraventionAssembler;
    @Mock
    private PermitValidator permitValidator;
    @Mock
    private NullParkingPermitFactory nullParkingPermitFactory;
    @Mock
    private InfractionService infractionService;
    @Mock private Contravention contravention;
    @Mock private NullParkingPermit nullParkingPermit;
    @Mock private Infraction invalidCodeInfraction;

    @Before
    public void setUp() {
        parkingPermitService = new ParkingPermitService(parkingPermitRepository, parkingPermitAssembler,
            parkingPriceRepository,
            contraventionAssembler,
            permitValidator,
            nullParkingPermitFactory,
            infractionService
        );

        parkingPermitDto = new ParkingPermitDto();
        parkingPermitDto.period = A_PARKING_PERIOD.toString();
        parkingPermitDto.zone = A_ZONE.toString();

        given(parkingPermitRepository.findById(PERMIT_ID)).willReturn(parkingPermit);
        given(parkingPermitAssembler.create(parkingPermitDto, price)).willReturn(parkingPermit);
        given(parkingPermitAssembler.create(parkingPermit)).willReturn(parkingPermitDto);
        given(parkingPriceRepository.getZonePriceForPeriod(A_ZONE.toString(),
                A_PARKING_PERIOD.toString())).willReturn(price);

        given(contraventionAssembler.create(contraventionDto)).willReturn(contravention);
        given(contraventionAssembler.create(contravention)).willReturn(returnedContraventionDto);
        given(nullParkingPermitFactory.create()).willReturn(nullParkingPermit);
        given(infractionService.createInvalidParkingPermitInfraction()).willReturn(invalidCodeInfraction);

        given(parkingPermit.findContraventionById(CONTRAVENTION_ID)).willReturn(contravention);
    }

    @Test
    public void whenCreate_thenGetParkingPermitPrice() {
        parkingPermitService.createParkingPermit(parkingPermitDto);

        verify(parkingPriceRepository).getZonePriceForPeriod(A_ZONE.toString(), A_PARKING_PERIOD.toString());
    }

    @Test
    public void whenCreate_thenAssembleParkingPermit() {
        parkingPermitService.createParkingPermit(parkingPermitDto);

        verify(parkingPermitAssembler).create(parkingPermitDto, price);
    }

    @Test
    public void whenCreate_thenDeliverParkingPermit() {
        parkingPermitService.createParkingPermit(parkingPermitDto);

        verify(parkingPermit).sendParkingPermitCode();
    }

    @Test
    public void whenCreate_thenSaveParkingPermit() {
        parkingPermitService.createParkingPermit(parkingPermitDto);

        verify(parkingPermitRepository).save(parkingPermit);
    }

    @Test
    public void whenCreate_thenShouldReturnParkingPermit() {
        ParkingPermit parkingPermit = parkingPermitService.createParkingPermit(parkingPermitDto);

        assertNotNull(parkingPermit);
    }

    @Test
    public void whenFindParkingPermit_thenParkingPermitRepositoryFindById() {
        parkingPermitService.findParkingPermit(PERMIT_ID);

        verify(parkingPermitRepository).findById(PERMIT_ID);
    }

    @Test
    public void whenFindParkingPermit_thenParkingPermitAssemblerCreate() {
        parkingPermitService.findParkingPermit(PERMIT_ID);

        verify(parkingPermitAssembler).create(parkingPermit);
    }

    @Test
    public void whenVerifyPermit_thenAssembleContravention() {
        parkingPermitService.verifyPermit(PERMIT_ID, contraventionDto);

        verify(contraventionAssembler).create(contraventionDto);
    }

    @Test
    public void whenVerifyPermit_thenFindPermitById() {
        parkingPermitService.verifyPermit(PERMIT_ID, contraventionDto);

        verify(parkingPermitRepository).findById(PERMIT_ID);
    }

    @Test
    public void whenVerifyPermit_thenValidatePermit() {
        parkingPermitService.verifyPermit(PERMIT_ID, contraventionDto);

        verify(permitValidator).verify(contravention, parkingPermit);
    }

    @Test
    public void whenVerifyPermit_thenKeepOnlyInfractionWithHighestCost() {
        parkingPermitService.verifyPermit(PERMIT_ID, contraventionDto);

        verify(contravention).keepOnlyInfractionWithLargestCost();
    }

    @Test
    public void whenVerifyPermit_thenAddContraventionToPermit() {
        parkingPermitService.verifyPermit(PERMIT_ID, contraventionDto);

        verify(parkingPermit).addContravention(contravention);
    }

    @Test
    public void whenVerifyPermit_thenSaveParkingPermit() {
        parkingPermitService.verifyPermit(PERMIT_ID, contraventionDto);

        verify(parkingPermitRepository).save(parkingPermit);
    }

    @Test
    public void whenVerifyPermit_thenReturnCreatedContravention() {
        ContraventionDto dto = parkingPermitService.verifyPermit(PERMIT_ID, this.contraventionDto);

        assertEquals(returnedContraventionDto, dto);
        assertNotNull(dto);
    }

    private void givenParkingPemitNotFound() {
        given(parkingPermitRepository.findById(PERMIT_ID)).willThrow(ParkingPermitNotFoundException.class);
    }

    @Test
    public void givenParkingPermitNotFound_whenVerifyPermit_thenReturnCreatedContravention() {
        givenParkingPemitNotFound();

        ContraventionDto dto = parkingPermitService.verifyPermit(PERMIT_ID, this.contraventionDto);

        assertEquals(returnedContraventionDto, dto);
    }

    @Test
    public void givenParkingPermitNotFound_whenVerifyPermit_thenCreateNullParkingPermit() {
        givenParkingPemitNotFound();

        parkingPermitService.verifyPermit(PERMIT_ID, contraventionDto);

        verify(nullParkingPermitFactory).create();
    }

    @Test
    public void givenParkingPermitNotFound_whenVerifyPermit_thenCreateInvalidCodeInfraction() {
        givenParkingPemitNotFound();

        parkingPermitService.verifyPermit(PERMIT_ID, contraventionDto);

        verify(infractionService).createInvalidParkingPermitInfraction();
    }

    @Test
    public void givenParkingPermitNotFound_whenVerifyPermit_thenAddInfractionToContravention() {
        givenParkingPemitNotFound();

        parkingPermitService.verifyPermit(PERMIT_ID, contraventionDto);

        verify(contravention).addInfraction(invalidCodeInfraction);
    }

    @Test
    public void whenFindContraventionById_thenFindParkingPermitById() {
        parkingPermitService.findContraventionById(PERMIT_ID, CONTRAVENTION_ID);

        verify(parkingPermitRepository).findById(PERMIT_ID);
    }

    @Test
    public void whenFindContraventionById_thenFindContraventionById() {
        parkingPermitService.findContraventionById(PERMIT_ID, CONTRAVENTION_ID);

        verify(parkingPermit).findContraventionById(CONTRAVENTION_ID);
    }

    @Test
    public void whenFindContraventionById_thenAssembleContravention() {
        parkingPermitService.findContraventionById(PERMIT_ID, CONTRAVENTION_ID);

        verify(contraventionAssembler).create(contravention);
    }

    @Test
    public void whenFindContraventionById_thenReturnContraventionFound() {
        ContraventionDto dto = parkingPermitService.findContraventionById(PERMIT_ID, CONTRAVENTION_ID);

        assertEquals(returnedContraventionDto, dto);
    }

    @Test
    public void whenPayContravention_thenFindParkingPermit() {
        parkingPermitService.payContravention(PERMIT_ID, CONTRAVENTION_ID);

        verify(parkingPermitRepository).findById(PERMIT_ID);
    }

    @Test
    public void whenPayContravention_thenPayContravention() {
        parkingPermitService.payContravention(PERMIT_ID, CONTRAVENTION_ID);

        verify(parkingPermit).payContravention(CONTRAVENTION_ID);
    }

    @Test
    public void whenPayContravention_thenSaveParkingPermit() {
        parkingPermitService.payContravention(PERMIT_ID, CONTRAVENTION_ID);

        verify(parkingPermitRepository).save(parkingPermit);
    }
}
