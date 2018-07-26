package com.prospring.jdbc.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-25 오전 11:04
 **/

@Getter @Setter @ToString
public class Album {
    private Long id;
    private Long singerId;
    private String title;
    private Date releaseDate;

}
