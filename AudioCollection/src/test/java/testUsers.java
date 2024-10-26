import by.losik.audioCollection.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;

public class testUsers {
    Album album;
    Music music;
    Music music1;
    Settings settings;
    Review review = new Review();
    SubscriptionEntity subscription;
    User user;
    UserDetails userDetails;
    playbackHistory playbackHistory;
    Admin admin;
    AcquiringAudioManager acquiringAudioManager;
    Manager manager;
    Playlist playlist;
    Searcher searcher;

    @Before
    public void mockData(){
        album = new Album();
        playbackHistory = new playbackHistory();
        album.setAlbumAudio(new ArrayList<>());
        music = new Music("Shawtyslikamelody", AudioType.FILE,2023, Subscription.PREMIUM,Genre.ELECTRONIC
                ,review);
        album.addAUDIO(music);
        album.addAUDIO(music1);
        user = new User("Name","Password",new Date(65626),UserType.USER.name(),subscription,settings);
        userDetails = new UserDetails("Name1","pswrd",new Date(1000),UserType.ADMIN.name(),subscription,settings);
        admin=new Admin("AdminName","admin",new Date(),UserType.ADMIN,subscription,settings);
        acquiringAudioManager = new AcquiringAudioManager();
        manager = new Manager();
        playlist = new Playlist();
        searcher = new Searcher();
    }

    @Test
    public void testUsers(){
        Guest guest = new Guest("Guest"+this.hashCode(),"",new Date(),UserType.GUEST,
                subscription, settings);

        Assert.assertEquals(guest.getUserDetails(),null);
        Assert.assertEquals(guest.getSettings(),settings);
        Assert.assertThat(guest.getId(),instanceOf(String.class));
        Assert.assertEquals(guest.getPassword(),"");
        Assert.assertThat(guest.getRegistrationDate(),instanceOf(Date.class));
        Assert.assertEquals(guest.getType(),UserType.GUEST.name());
        Assert.assertEquals(guest.getSettings(),settings);
        Assert.assertEquals(guest.getSubscription(),subscription);

        List<Music> musicList = new ArrayList<>();
        user.setFavouriteMusic(musicList);
        Assert.assertTrue(user.getFavouriteMusic().isEmpty());
        Assert.assertEquals(user.getFavouriteMusic(),musicList);
        Assert.assertTrue(user.getFavouriteMusic() instanceof List);
        user.setLibrary(new Library());
        Assert.assertTrue(user.getLibrary() instanceof Library);
        user.setUserDetails(userDetails);
        Assert.assertEquals(user.getUserDetails(),userDetails);
        user.setPlaybackHistory(playbackHistory);
        Assert.assertEquals(user.getPlaybackHistory(), playbackHistory);
        user.setSettings(settings);
        Assert.assertEquals(user.getSettings(),settings);
        user.setOwnedAlbums(new ArrayList<>());
        Assert.assertTrue(user.getOwnedAlbums() instanceof List);
        user.setOwnedReviews(new ArrayList<>());
        Assert.assertTrue(user.getOwnedReviews() instanceof List);

        userDetails.setPassword("pswd1");
        Assert.assertEquals(userDetails.getSubscription(),subscription);
        Assert.assertEquals(userDetails.getId(),"Name1");
        Assert.assertEquals(userDetails.getPassword(),"pswd1");
        userDetails.setType(UserType.GUEST);
        Assert.assertEquals(userDetails.getType(),UserType.GUEST.name());
        userDetails.setSubscription(subscription);
        Assert.assertEquals(subscription,userDetails.getSubscription());
        userDetails.setSettings(settings);
        Assert.assertEquals(userDetails.getSettings(),settings);
    }
}
