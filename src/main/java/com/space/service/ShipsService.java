package com.space.service;

//import com.space.repository.ShipsRepository;
import com.space.model.Ship;
import com.space.repository.ShipsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<Ship> findAll(Specification<Ship> specification, Pageable pageable) {
        return shipsRepository.findAll(specification, pageable);
    }
}
