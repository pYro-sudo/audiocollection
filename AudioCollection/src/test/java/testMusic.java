import by.losik.audioCollection.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class testMusic {
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
    public void testMusic(){
        Assert.assertEquals(music.getName(),"Shawtyslikamelody");
        Assert.assertEquals(music.getAvailableOnLevel(),Subscription.PREMIUM);
        Assert.assertEquals(music.getGenre(),Genre.ELECTRONIC);
        Assert.assertEquals(music.getReleaseYear(),2023);
        Assert.assertEquals(music.getReview(),review);

        music1 = new Music("BinChilin", AudioType.FILE,2020, Subscription.STANDARD,Genre.ELECTRONIC
                ,review);
        music1.setName("Bruh");
        Review review1 = new Review();
        music1.setReview(review1);
        music1.setReleaseYear(1000);
        Assert.assertEquals(music1.getReview(),review1);
        Assert.assertEquals(music1.getReleaseYear(),1000);
        Assert.assertEquals(music1.getName(),"Bruh");
        Assert.assertEquals(music1.getType(),AudioType.FILE);
        Assert.assertEquals(music1.getAvailableOnLevel(),Subscription.STANDARD);
        music1.changeAvailabilityLevel(Subscription.ADMIN);
        Assert.assertEquals(music1.getAvailableOnLevel(),Subscription.ADMIN);
        Assert.assertEquals(music1.getGenre(),Genre.ELECTRONIC);
    }
}
