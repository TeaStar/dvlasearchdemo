package uk.co.dreampixel.dvlasearch.client;

import java.net.URI;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import uk.co.dreampixel.dvlasearch.domain.Vehicle;

@Slf4j
@Component
public class DvlaSearch {

    private static final String LICENCE_PLATE = "licencePlate";
    private static final String API_KEY = "apikey";

    private final RestTemplate restTemplate;
    private final String dvlaEndpoint;
    private final String dvlaApiKey;

    @Autowired
    public DvlaSearch(RestTemplate restTemplate,
                      final @Value("${dvla.search.endpoint}") String dvlaEndpoint,
                      final @Value("${dvla.search.api.key}") String dvlaApiKey) {
        this.restTemplate = restTemplate;
        this.dvlaEndpoint = dvlaEndpoint;
        this.dvlaApiKey = dvlaApiKey;
    }

    public Vehicle searchVehicle(String licencePlate) {

        // Find bugs example
        String findBugsString = "findbugs";
        findBugsString.replace('f', 'k');

        URI endpointUri = createEndpointUri(licencePlate);
        log.info("**** Endpoint: " + endpointUri + " ****");

        return restTemplate.getForObject(endpointUri, Vehicle.class);
    }

    private URI createEndpointUri(String licencePlate) {
        return UriComponentsBuilder.fromHttpUrl(dvlaEndpoint)
                                   .queryParam(LICENCE_PLATE, licencePlate)
                                   .queryParam(API_KEY, dvlaApiKey)
                                   .build()
                                   .encode()
                                   .toUri();
    }


}
