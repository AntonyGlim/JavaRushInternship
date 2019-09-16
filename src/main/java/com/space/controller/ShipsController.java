package com.space.controller;

import com.space.service.ShipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/ships")
public class ShipsController {

    private ShipsService shipsService;

    @Autowired
    public void setShipsService(ShipsService shipsService) {
        this.shipsService = shipsService;
    }

//    @GetMapping()
//    @ResponseStatus(HttpStatus.OK)
//    public List<Ship> findAll(){
//        return shipsService.findAll();
//    }


}
