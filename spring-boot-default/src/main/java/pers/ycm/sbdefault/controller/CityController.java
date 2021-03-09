package pers.ycm.sbdefault.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.ycm.sbdefault.pojo.entity.City;
import pers.ycm.sbdefault.repository.CityRepository;

/**
 * @author yuanchengman
 * @date 2021-03-09
 */
@RestController
public class CityController {
    @Autowired
    private CityRepository cityRepository;

    @GetMapping("getcity")
    public City getCity(){
        City city = cityRepository.getById(1);
        return city;
    }
}
