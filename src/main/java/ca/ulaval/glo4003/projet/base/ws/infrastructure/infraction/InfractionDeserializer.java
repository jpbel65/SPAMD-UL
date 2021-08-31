package ca.ulaval.glo4003.projet.base.ws.infrastructure.infraction;

import ca.ulaval.glo4003.projet.base.ws.domain.infraction.Infraction;
import ca.ulaval.glo4003.projet.base.ws.domain.infraction.InfractionCode;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound.InfractionNotFoundException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class InfractionDeserializer extends StdDeserializer <Infraction> {

    public InfractionDeserializer() {
        this(null);
    }

    public InfractionDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Infraction deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, InfractionNotFoundException {
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode node = objectCodec.readTree(jsonParser);

        final double cost = node.get("montant").asDouble();
        final String offense = node.get("infraction").asText();
        final InfractionCode code = InfractionCode.fromString(node.get("code").asText());

        return new Infraction(code, offense, new Price((float)cost));
    }
}
