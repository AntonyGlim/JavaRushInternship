package com.space.service;

import com.space.exceptions.BadRequestException;
import com.space.exceptions.PageNotFoundException;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public List<Ship> findAll(Specification<Ship> specification) {
        return shipsRepository.findAll(specification);
    }

    public Ship createShip(Ship ship) {
        if (!shipCorrect(ship)) throw new BadRequestException("Cant create ship");
        if (ship.getUsed() == null) ship.setUsed(false); //TODO ship.getUsed().equals("")
        ship.setRating(ship.rating());
        return shipsRepository.saveAndFlush(ship);
    }

    public Ship findById(Long id) {
        if (id > Long.MAX_VALUE || id <= 0) throw new BadRequestException("Wrong ID!");
        Optional<Ship> optionalShip = shipsRepository.findById(id);
        return optionalShip.orElseThrow(() -> new PageNotFoundException("Ship not found"));
    }

    public void deleteById(Long id) {
        findById(id);
        shipsRepository.deleteById(id);
    }


    /*-------------------------------ShipCheck-block-------------------------------*/
    private boolean shipCorrect(Ship ship) {
        return (
                ship != null &&
                shipNameCorrect(ship.getName()) &&
                shipPlanetCorrect(ship.getPlanet()) &&
                shipTypeCorrect(ship.getShipType()) &&
                shipProdDateCorrect(ship.getProdDate()) &&
                shipSpeedCorrect(ship.getSpeed()) &&
                shipCrewSizeCorrect(ship.getCrewSize())
        );
    }

    private boolean shipNameCorrect(String name) {
        return name != null && !name.equals("") && name.length() <= 50;
    }

    private boolean shipPlanetCorrect(String planet) {
        return planet != null && !planet.equals("") && planet.length() <= 50;
    }

    private boolean shipTypeCorrect(ShipType shipType) {
        return shipType != null &&
                (shipType == ShipType.TRANSPORT || shipType == ShipType.MILITARY || shipType == ShipType.MERCHANT);
    }

    private boolean shipProdDateCorrect(Date prodDate) {
        return prodDate != null && prodDate.getTime() >= 26192246400000L && prodDate.getTime() >= 33103209600000L;
    }

    private boolean shipSpeedCorrect(Double speed) {
        return speed != null && speed >= 0.01d && speed <= 0.99d;
    }

    private boolean shipCrewSizeCorrect(Integer crewSize) {
        return crewSize != null && crewSize >= 1 && crewSize <= 9999;
    }
    /*-------------------------------ShipCheck-block-end-------------------------------*/


    /*-------------------------------filters-block-------------------------------*/
    public Specification<Ship> filterByName(String name) {
        return (Specification<Ship>) (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public Specification<Ship> filterByPlanet(String planet) {
        return (Specification<Ship>) (root, query, criteriaBuilder) ->
                planet == null ? null : criteriaBuilder.like(root.get("planet"), "%" + planet + "%");
    }

    public Specification<Ship> filterByProdDate(Long minDate, Long maxDate) {
        return (Specification<Ship>) (root, query, criteriaBuilder) -> {
            if (minDate == null && maxDate == null) {
                return null;
            } else if (minDate == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("prodDate"), new Date(maxDate));
            } else if (maxDate == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("prodDate"), new Date(minDate));
            }
            return criteriaBuilder.between(root.get("prodDate"), new Date(minDate), new Date(maxDate));
        };
    }

    public Specification<Ship> filterByCrewSize(Integer minCrewSize, Integer maxCrewSize) {
        return (Specification<Ship>) (root, query, criteriaBuilder) -> {
            if (minCrewSize == null && maxCrewSize == null) {
                return null;
            } else if (minCrewSize == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("crewSize"), maxCrewSize);
            } else if (maxCrewSize == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("crewSize"), minCrewSize);
            }
            return criteriaBuilder.between(root.get("crewSize"), minCrewSize, maxCrewSize);
        };
    }

    public Specification<Ship> filterBySpeed(Double minSpeed, Double maxSpeed) {
        return (Specification<Ship>) (root, query, criteriaBuilder) -> {
            if (minSpeed == null && maxSpeed == null) {
                return null;
            } else if (minSpeed == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("speed"), maxSpeed);
            } else if (maxSpeed == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("speed"), minSpeed);
            }
            return criteriaBuilder.between(root.get("speed"), minSpeed, maxSpeed);
        };
    }

    public Specification<Ship> filterByRating(Double minRating, Double maxRating) {
        return (Specification<Ship>) (root, query, criteriaBuilder) -> {
            if (minRating == null && maxRating == null) {
                return null;
            } else if (minRating == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("rating"), maxRating);
            } else if (maxRating == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), minRating);
            }
            return criteriaBuilder.between(root.get("rating"), minRating, maxRating);
        };
    }

    public Specification<Ship> filterByShipType(ShipType shipType) {
        return (Specification<Ship>) (root, query, criteriaBuilder) ->
                shipType == null ? null : criteriaBuilder.equal(root.get("shipType"), shipType);
    }

    public Specification<Ship> filterByIsUsed(Boolean isUsed) {
        return (Specification<Ship>) (root, query, criteriaBuilder) -> {
            if  (isUsed == null){
                return null;
            } else if (isUsed) {
                return criteriaBuilder.isTrue(root.get("isUsed"));
            }
            return criteriaBuilder.isFalse(root.get("isUsed"));
        };
    }
    /*-------------------------------filters-block-end-------------------------------*/



}
