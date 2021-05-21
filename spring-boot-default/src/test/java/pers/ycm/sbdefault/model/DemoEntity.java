package pers.ycm.sbdefault.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author yuanchengman
 * @date 2021-05-21
 */
@Data
public class DemoEntity {
    private long id;

    private String name;

    private LocalDateTime createTime;
}
