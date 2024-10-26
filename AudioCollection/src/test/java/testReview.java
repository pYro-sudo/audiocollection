import by.losik.audioCollection.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class testReview {
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
    public void testReview(){
        review.setDateOfReview(new Date());
        Assert.assertTrue(review.getDateOfReview() instanceof Date);
        review.setPersonWhoReviewed("Yarik");
        Assert.assertEquals(review.getPersonWhoReviewed(),"Yarik");
        review.setReview("Nice music, gg");
        Assert.assertEquals(review.getReview(), "Nice music, gg");
        review.setRating(10);
        Assert.assertEquals(review.getRating(),10.0,0);
        review.setNameOfTheMusicReviewed("Shawtyslikeamelody");
        Assert.assertEquals(review.getNameOfTheMusicReviewed(),"Shawtyslikeamelody");
    }
}
