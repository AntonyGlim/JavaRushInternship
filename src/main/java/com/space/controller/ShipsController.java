package com.space.controller;

import com.space.model.Ship;
import com.space.service.ShipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/ships")
public class ShipsController {

    private ShipsService shipsService;

    @Autowired
    public void setShipsService(ShipsService shipsService) {
        this.shipsService = shipsService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Ship> findAll(){
        System.out.println(shipsService.findAll());
        return shipsService.findAll();
    }


}
