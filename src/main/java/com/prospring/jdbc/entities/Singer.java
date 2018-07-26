package com.prospring.jdbc.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author SiWoo Kim,
 * @version 1.0.0
 * @email sm123tt@gmail.com
 * @github : https://github.com/Siwoo-Kim
 * @since 2018-07-25 오전 11:03
 **/

@Getter @Setter @ToString
public class Singer implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private List<Album> albums;

    public boolean addAlbum(Album album) {
        if(albums == null) {
            albums = new ArrayList<>();
            albums.add(album);
            return true;
        }

        if(albums.contains(album)) {
            return false;
        } else {
            albums.add(album);
            return true;
        }
    }
}
