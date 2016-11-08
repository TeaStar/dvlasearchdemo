package uk.co.dreampixel.dvlasearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uk.co.dreampixel.dvlasearch.client.DvlaSearch;
import uk.co.dreampixel.dvlasearch.domain.Vehicle;

@RestController
public class VehicleSearchController {

    private final DvlaSearch dvlaSearch;

    @Autowired 
    public VehicleSearchController(DvlaSearch dvlaSearch) {
        this.dvlaSearch = dvlaSearch;
    }

    @RequestMapping("/vehicle")
    @ResponseBody
    public Vehicle searchVehicle(@RequestParam String licencePlate) {
        return dvlaSearch.searchVehicle(licencePlate);
    }

}
