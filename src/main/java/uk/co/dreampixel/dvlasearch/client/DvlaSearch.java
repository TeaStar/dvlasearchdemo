package uk.co.dreampixel.dvlasearch.client;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import uk.co.dreampixel.dvlasearch.domain.Vehicle;

@Component public class DvlaSearch {

    private static final String LICENCE_PLATE = "licencePlate";
    private static final String API_KEY = "apikey";

    private final RestTemplate restTemplate;

    @Value("${dvla.search.endpoint}")
    private String dvlaEndpoint;

    @Value("${dvla.search.api.key}")
    private String dvlaApiKey;

    @Autowired public DvlaSearch(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Vehicle searchVehicle(String licencePlate) {
        return restTemplate.getForObject(createEndpointUri(licencePlate), Vehicle.class);
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
