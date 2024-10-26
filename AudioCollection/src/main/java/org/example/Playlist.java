package org.example;

import java.util.List;

public class Playlist {
    private List<Music> playlist;

    public void setPlaylist(List<Music> playlist) {
        this.playlist = playlist;
    }

    public List<Music> getPlaylist() {
        return playlist;
    }

    public Music addToPlayList(Music audio) {
        this.playlist.add(audio);
        return audio;
    }

    public Music removeFromPlayList(Music audio){
        this.playlist.remove(audio);
        return audio;
    }
}
