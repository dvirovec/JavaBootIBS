package inforbis.erp.controller.travel_order;

import inforbis.erp.model.travel_order.TravelOrderConfig;
import inforbis.erp.service.travel_order.TravelOrderConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dvirovec on 22.12.2016..
 */
@RestController
public class TravelOrderConfigController {

@Autowired
private TravelOrderConfigService travel_order_configService;
    
    @RequestMapping(value= "/erp/travel_order_config/{company_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TravelOrderConfig> getTravelOrderConfig(@PathVariable("company_id") Long id) {

        TravelOrderConfig travel_order_config = travel_order_configService.findOne(id);

        if(travel_order_config == null){
            return new ResponseEntity<TravelOrderConfig>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<TravelOrderConfig>(travel_order_config, HttpStatus.OK);

    }

    @RequestMapping(value= "/erp/travel_order_config", method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TravelOrderConfig> createTravelOrderConfig(@RequestBody TravelOrderConfig travel_order_config) {

        TravelOrderConfig savedTravelOrderConfig =  travel_order_configService.create(travel_order_config);

        return new ResponseEntity<TravelOrderConfig>(savedTravelOrderConfig, HttpStatus.CREATED);

    }

    @RequestMapping(value= "/erp/travel_order_config/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TravelOrderConfig> updateTravelOrderConfig(@RequestBody TravelOrderConfig travel_order_config) {

        TravelOrderConfig updatedTravelOrderConfig = travel_order_configService.update(travel_order_config);

        if(updatedTravelOrderConfig==null){
            return new ResponseEntity<TravelOrderConfig>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<TravelOrderConfig>(updatedTravelOrderConfig, HttpStatus.OK);
    }

    @RequestMapping(value= "/erp/travel_order_config/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TravelOrderConfig> deleteTravelOrderConfig(@PathVariable("id") Long id, @RequestBody TravelOrderConfig travel_order_config){

        travel_order_configService.delete(id);

        return new ResponseEntity<TravelOrderConfig>(HttpStatus.NO_CONTENT);

    }

}
