package ca.ulaval.glo4003.projet.base.ws.infrastructure.infraction;

import ca.ulaval.glo4003.projet.base.ws.domain.infraction.Infraction;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.InfractionCode;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.InfractionStorage;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound.InfractionNotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InfractionStorageInMemory implements InfractionStorage {

    private String infractionFilePath = "src/main/java/ca/ulaval/glo4003/projet/base/ws/externResource/infraction.json";
    private Map<InfractionCode, Infraction> infractions = new HashMap<>();

    public InfractionStorageInMemory() {
        initialiseStorage();
    }

    @Override
    public Infraction findByCode(InfractionCode code) {
        verifyInfractionIsInStorage(code);
        return infractions.get(code);
    }

    private void verifyInfractionIsInStorage(InfractionCode code) {
        if (!infractions.containsKey(code)) {
            throw new InfractionNotFoundException(code.toString());
        }
    }

    private void initialiseStorage() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Infraction.class, new InfractionDeserializer());
        mapper.registerModule(module);

        File file = new File(infractionFilePath);
        try {
            List<Infraction> infractions = mapper.readValue(file, new TypeReference<List<Infraction>>() {
            });
            this.infractions = infractions.stream().collect(Collectors.toMap(Infraction::getCode, infraction -> infraction));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
