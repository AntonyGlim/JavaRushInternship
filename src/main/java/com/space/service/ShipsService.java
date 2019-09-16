package com.space.service;

//import com.space.repository.ShipsRepository;
import com.space.model.Ship;
import com.space.repository.ShipsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipsService {


    private ShipsRepository shipsRepository;

    @Autowired
    public void setShipsRepository(ShipsRepository shipsRepository) {
        this.shipsRepository = shipsRepository;
    }

    public List<Ship> findAll() {
        List<Ship> list = new ArrayList<>();
        shipsRepository.findAll().forEach(list::add);
        return list;
    }
}
