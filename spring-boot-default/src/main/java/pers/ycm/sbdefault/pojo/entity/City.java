package pers.ycm.sbdefault.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author yuanchengman
 * @date 2021-03-09
 */
@Data
public class City {
    @TableField("ID")
    private int id;
    @TableField("Name")
    private String name;
    @TableField("District")
    private String district;
    @TableField("CountryCode")
    private String countryCode;
    @TableField("Population")
    private int population;
}
