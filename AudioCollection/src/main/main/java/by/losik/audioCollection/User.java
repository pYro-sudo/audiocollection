package by.losik.audioCollection;

import java.util.Date;
import java.util.List;

public class User extends UserDetails {
    private List<Music> favouriteMusic;
    private List<Album> ownedAlbums;
    private UserDetails userDetails;
    private Library library;
    private PlaybackHistory playbackHistory;
    private Settings settings;
    private List<Review> ownedReviews;

    private Playlist playlist;

    public User(String id, String password, Date registrationDate, String type, SubscriptionEntity subscription, Settings settings) {
        super(id, password, registrationDate, type, subscription, settings);
    }

    public List<Music> getFavouriteMusic() {
        return favouriteMusic;
    }

    public void setFavouriteMusic(List<Music> favouriteMusic) {
        this.favouriteMusic = favouriteMusic;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public PlaybackHistory getPlaybackHistory() {
        return playbackHistory;
    }

    public void setPlaybackHistory(PlaybackHistory playbackHistory) {
        this.playbackHistory = playbackHistory;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public List<Album> getOwnedAlbums() {
        return ownedAlbums;
    }

    public void setOwnedAlbums(List<Album> ownedAlbums) {
        this.ownedAlbums = ownedAlbums;
    }

    public List<Review> getOwnedReviews() {
        return ownedReviews;
    }

    public void setOwnedReviews(List<Review> ownedReviews) {
        this.ownedReviews = ownedReviews;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}
