import org.example.*;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApplicationTest {
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

    @Test
    public void testSettings(){
        settings = new Settings();
        settings.setTheme(ThemeTypes.LIGHT);
        settings.setFont(Fonts.TOYZ);
        Assert.assertEquals(settings.getFont(),Fonts.TOYZ);
        Assert.assertEquals(settings.getTheme(),ThemeTypes.LIGHT);
    }

    @Test
    public void testSubscriptionEntity(){
        Date date = new Date();
        Date date1 = new Date();
        subscription = new SubscriptionEntity(Subscription.PREMIUM.name(),date,date1);
        subscription.setSubscriptionType(Subscription.ADMIN);
        subscription.setBeginningDate(date);
        subscription.setExpirationDate(date1);
        Assert.assertEquals(subscription.getSubscriptionType(),Subscription.ADMIN.name());
        Assert.assertEquals(subscription.getBeginningDate(),date);
        Assert.assertEquals(subscription.getExpirationDate(),date1);
    }

    @Test
    public void testAlbum(){
        Assert.assertEquals(album.removeAUDIO(music1),music1);
        album.setAlbumName("Bababoe");
        Assert.assertTrue(album.getAlbumName().equals("Bababoe"));
        Assert.assertThat(album.getAlbumAudio(), instanceOf(List.class));
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
        Assert.assertThat(user.getFavouriteMusic(),instanceOf(List.class));
        user.setLibrary(new Library());
        Assert.assertThat(user.getLibrary(),instanceOf(Library.class));
        user.setUserDetails(userDetails);
        Assert.assertEquals(user.getUserDetails(),userDetails);
        user.setPlaybackHistory(playbackHistory);
        Assert.assertEquals(user.getPlaybackHistory(), playbackHistory);
        user.setSettings(settings);
        Assert.assertEquals(user.getSettings(),settings);
        user.setOwnedAlbums(new ArrayList<>());
        Assert.assertThat(user.getOwnedAlbums(),instanceOf(List.class));
        user.setOwnedReviews(new ArrayList<>());
        Assert.assertThat(user.getOwnedReviews(),instanceOf(List.class));

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

    @Test
    public void testLibrary(){
        Library library = new Library();
        Assert.assertThat(library.getAlbumLibrary(),instanceOf(List.class));
        Assert.assertEquals(library.addAlbumTOLibrary(album),album);
        List<Album> albumList = new ArrayList<>();
        Assert.assertEquals(library.getAlbumLibrary().isEmpty(),false);
        library.setAlbumLibrary(albumList);
        Assert.assertEquals(albumList,library.getAlbumLibrary());
    }

    @Test
    public void testAdmin(){
        admin.setAcquiringAudioManager(new AcquiringAudioManager());
        Assert.assertThat(admin.getAcquiringAudioManager(),instanceOf(AcquiringAudioManager.class));
        admin.setBufferedUsers(new ArrayList<>());
        Assert.assertThat(admin.getBufferedUsers(),instanceOf(List.class));
        Assert.assertEquals(admin.getUserDetails(),null);
        admin.setUserDetails(userDetails);
        Assert.assertEquals(admin.getUserDetails(),userDetails);
        admin.setSubscriptionEntity(subscription);
        Assert.assertEquals(admin.getSubscriptionEntity(),subscription);
    }

    @Test
    public void testAcquiringAudioManager(){
        acquiringAudioManager.setSearcher(new Searcher());
        Assert.assertThat(acquiringAudioManager.getSearcher(),instanceOf(Searcher.class));
        Assert.assertThat(acquiringAudioManager.getObject(), instanceOf(JSONObject.class));
        Assert.assertEquals(acquiringAudioManager.saveObject("nonExistentMusic").toString(), acquiringAudioManager.getObject().toString());
    }

    @Test
    public void testManager(){
        manager.setSearcher(new Searcher());
        Assert.assertThat(manager.getSearcher(),instanceOf(Searcher.class));
        Assert.assertFalse(manager.LogIn("nonExistentUser","nonExistentPassword"));
    }

    @Test
    public void testPlaybackHistory(){
        Assert.assertEquals(playbackHistory.addAudioToHistory(music1),music1);
        Assert.assertEquals(playbackHistory.removeAudioToHistory(music1),music1);
        playbackHistory.setLastHeardAudios(new ArrayList<>());
        Assert.assertThat(playbackHistory.getLastHeardAudios(),instanceOf(List.class));
    }

    @Test
    public void testPlaylist(){
        playlist.setPlaylist(new ArrayList<>());
        Assert.assertThat(playlist.getPlaylist(),instanceOf(List.class));
        Assert.assertEquals(playlist.addToPlayList(music),music);
        Assert.assertTrue(!playlist.getPlaylist().isEmpty());
        Assert.assertEquals(playlist.removeFromPlayList(music),music);
        Assert.assertTrue(playlist.getPlaylist().isEmpty());
    }

    @Test
    public void testReview(){
        review.setDateOfReview(new Date());
        Assert.assertThat(review.getDateOfReview(),instanceOf(Date.class));
        review.setPersonWhoReviewed("Yarik");
        Assert.assertEquals(review.getPersonWhoReviewed(),"Yarik");
        review.setReview("Nice music, gg");
        Assert.assertEquals(review.getReview(), "Nice music, gg");
        review.setRating(10);
        Assert.assertEquals(review.getRating(),10.0,0);
        review.setNameOfTheMusicReviewed("Shawtyslikeamelody");
        Assert.assertEquals(review.getNameOfTheMusicReviewed(),"Shawtyslikeamelody");
    }

    @Test
    public void testSearcher(){
        Assert.assertEquals(searcher.dropAll(),"All tables are dropped");
        searcher.initAll();
    }
}
