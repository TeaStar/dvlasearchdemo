package uk.co.dreampixel.dvlasearch.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import uk.co.dreampixel.dvlasearch.client.DvlaSearch;
import uk.co.dreampixel.dvlasearch.domain.Vehicle;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class VehicleSearchControllerTest {

    private DvlaSearch dvlaSearch;
    private VehicleSearchController controller;

    private static final String LICENCE_PLATE = "WN66 ABC";

    @Before
    public void setup() {
        dvlaSearch = mock(DvlaSearch.class);
        controller = new VehicleSearchController(dvlaSearch);
    }

    @Test
    public void vehicleSearch() {

        // given
        given(dvlaSearch.searchVehicle(LICENCE_PLATE)).willReturn(new Vehicle());

        // when
        Vehicle vehicle = controller.searchVehicle(LICENCE_PLATE);

        // then
        verify(dvlaSearch, Mockito.times(1)).searchVehicle(LICENCE_PLATE);
        assertNotNull(vehicle);
    }

}
