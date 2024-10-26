package org.example;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Album> albumLibrary = new ArrayList<>();

    public Album addAlbumTOLibrary(Album album){
        this.albumLibrary.add(album);
        return album;
    }

    public void setAlbumLibrary(List<Album> albumLibrary) {
        this.albumLibrary = albumLibrary;
    }

    public List<Album> getAlbumLibrary() {
        return albumLibrary;
    }
}
