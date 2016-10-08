package uk.co.dreampixel.dvlasearch.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uk.co.dreampixel.dvlasearch.domain.Vehicle;

@Component
public class DvlaSearch {

    private final RestTemplate restTemplate;

    @Value("${dvla.search.endpoint}")
    private String dvlaEndpoint;

    @Value("${dvla.search.api.key}")
    private String apiKey;

    @Autowired public DvlaSearch(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Vehicle searchVehicle(String licencePlate) {
        return restTemplate.getForObject(createUrl(licencePlate), Vehicle.class);
    }

    private String createUrl(String licencePlate) {
        return dvlaEndpoint + "?licencePlate=" + licencePlate.toLowerCase() + "&apikey=" + apiKey;
    }

}
