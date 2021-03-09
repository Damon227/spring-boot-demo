package pers.ycm.sbdefault.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.ycm.sbdefault.pojo.entity.City;
import pers.ycm.sbdefault.repository.mapper.CityMapper;

/**
 * @author yuanchengman
 * @date 2021-03-09
 */
@Service
public class CityRepository extends ServiceImpl<CityMapper, City> {
}
