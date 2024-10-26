package by.losik.audioCollection;

import java.util.List;

public class Album {
    private String albumName;
    private List<Music> albumAudio;

    public List<Music> getAlbumAudio() {
        return albumAudio;
    }

    public void setAlbumAudio(List<Music> albumAudio) {
        this.albumAudio = albumAudio;
    }

    public Music addAUDIO(Music entity){
        this.albumAudio.add(entity);
        return entity;
    }

    public Music removeAUDIO(Music entity){
        this.albumAudio.remove(entity);
        return entity;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
}
