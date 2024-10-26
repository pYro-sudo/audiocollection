package org.example;

import java.util.ArrayList;
import java.util.List;

public class playbackHistory {
    private List<Music> lastHeardAudios = new ArrayList<>();

    public List<Music> getLastHeardAudios() {
        return lastHeardAudios;
    }

    public void setLastHeardAudios(List<Music> lastHeardAudios) {
        this.lastHeardAudios = lastHeardAudios;
    }

    public Music addAudioToHistory(Music audio){
        this.lastHeardAudios.add(audio);
        return audio;
    }

    public Music removeAudioToHistory(Music audio){
        this.lastHeardAudios.remove(audio);
        return audio;
    }
}
