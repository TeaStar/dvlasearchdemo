package uk.co.dreampixel.dvlasearch.client;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import uk.co.dreampixel.dvlasearch.domain.Vehicle;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class DvlaSearchTest {

    private static final String ENDPOINT = "http://dvla.test.endpoint/DvlaSearch";
    private static final String LICENCE_PLATE = "MT09NKS";
    private static final String API_KEY = "ANY_API_KEY";

    RestTemplate restTemplate;
    MockRestServiceServer mockServer;
    DvlaSearch dvlaSearch;

    @Before
    public void setup() {
        restTemplate = new RestTemplate();
        mockServer = MockRestServiceServer.createServer(restTemplate);
        dvlaSearch = new DvlaSearch(restTemplate, ENDPOINT, API_KEY);
    }

    @Test
    public void search() throws IOException {

        // given
        File file = FileUtils.getFile("src", "test", "resources", "example_response.json");
        String responseExample = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

        String requestURI = String.format("?licencePlate=%s&apikey=%s", LICENCE_PLATE, API_KEY);

        mockServer.expect(requestTo(requestURI))
                  .andExpect(method(HttpMethod.GET))
                  .andRespond(withSuccess(responseExample, MediaType.APPLICATION_JSON)
                  );

        // when
        Vehicle vehicle = restTemplate.getForObject("?licencePlate={licencePlate}&apikey={apikey}", Vehicle.class, LICENCE_PLATE, API_KEY);

        // then
        mockServer.verify();

        assertNotNull(vehicle);
        assertThat(vehicle.getMake(), is("VOLKSWAGEN"));
        assertThat(vehicle.getModel(), is("TIGUAN SE TDI 4MOTION 140"));
        assertThat(vehicle.getVin(), is("WVGZZZ5NZAW007903"));
        assertThat(vehicle.getDateOfFirstRegistration(), is("23 July 2009"));
        assertThat(vehicle.getCo2Emissions(), is("167"));
        assertTrue(vehicle.getTaxed());
        assertTrue(vehicle.getMot());
    }

}
