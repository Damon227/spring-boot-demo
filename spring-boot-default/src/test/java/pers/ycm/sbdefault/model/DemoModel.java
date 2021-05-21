package pers.ycm.sbdefault.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author yuanchengman
 * @date 2021-05-21
 */
@Data
public class DemoModel {
    private long id;

    private String name;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;

    public DemoModel() {
        this.createTime = LocalDateTime.MIN;
    }
}
