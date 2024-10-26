import by.losik.audioCollection.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class testAdmin {
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
        music = new Music("Shawtyslikamelody", AudioType.FILE,2023, Subscription.PREMIUM, Genre.ELECTRONIC
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
    public void testAdmin(){
        admin.setAcquiringAudioManager(new AcquiringAudioManager());
        Assert.assertTrue(admin.getAcquiringAudioManager() instanceof AcquiringAudioManager);
        admin.setBufferedUsers(new ArrayList<>());
        Assert.assertTrue(admin.getBufferedUsers() instanceof List);
        Assert.assertEquals(admin.getUserDetails(),null);
        admin.setUserDetails(userDetails);
        Assert.assertEquals(admin.getUserDetails(),userDetails);
        admin.setSubscriptionEntity(subscription);
        Assert.assertEquals(admin.getSubscriptionEntity(),subscription);
    }
}
