package by.losik.audioCollection;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Manager {
    private Searcher searcher = new Searcher();

    public void setSearcher(Searcher searcher) {
        this.searcher = searcher;
    }

    public boolean LogIn(String username, String password){
        return getSearcher().authorised(username, password);
    }

    public Searcher getSearcher(){
        return this.searcher;
    }

    public void UserOperations(User user){
        try{
            System.out.println("Enter the number of operation\n1 - set Settings\n" +
                    "2 - find music\n" + "3 - find album\n4 - add music to favourite\n5 - add album to favourite\n" +
                    "6 - find user\n7 - get playback history\n8 - add to playlist\n9 - set subscription\n" +
                    "10 - write review\n11 - add to library\n12 - save music\n13 - find owned music\n" +
                    "14 - add music\n15 - add album\n16 - get review\n17 - find favourite\n" +
                    "18 - add owned music\n19 - get settings\n20 - get library");
            int operation = new Scanner(System.in).nextInt();
            switch (operation){
                case 1:
                    Settings settings = new Settings();
                    settings.setFont(Fonts.TOYZ);
                    settings.setTheme(ThemeTypes.LIGHT);
                    user.setSettings(settings);
                    getSearcher().insertIntoUserSettings(user);
                    System.out.println("Inserted");
                    break;
                case 2:
                    System.out.println("Enter the name of music");
                    ResultSet resultSet = getSearcher().getMusic(new Scanner(System.in).nextLine());
                    System.out.println("The result:");
                    if(resultSet != null){
                        while(resultSet.next()){
                            System.out.println("Name:"+resultSet.getString("id")+"\tAudio Type:"+resultSet.getString("audioType")+"\t" +
                                    "Release Year:"+resultSet.getString("releaseYear")+"\tGenre:"+resultSet.getString("genre"));
                        }
                    }
                    break;
                case 3:
                    System.out.println("Enter the name of album");
                    ResultSet resultSetAlbum = getSearcher().getAlbums(new Scanner(System.in).nextLine());
                    System.out.println("The result:");
                    if(resultSetAlbum != null){
                        while(resultSetAlbum.next()){
                            System.out.println("Name:"+resultSetAlbum.getString("id")+"\tMusic:"+resultSetAlbum.getString("musicId"));
                        }
                    }
                    break;
                case 4:
                case 5:
                    searcher.insertIntoFavourite(user);
                    break;
                case 6:
                    System.out.println("Enter username");
                    String username = new Scanner(System.in).nextLine();
                    System.out.println("Enter password");
                    String password = new Scanner(System.in).nextLine();
                    ResultSet resultSet1 = searcher.getUserDetails(username,password);
                    System.out.println("The result:");
                    if(resultSet1 != null){
                        while (resultSet1.next()){
                            System.out.println("Name:"+resultSet1.getString("id")+"\tPassword:"+resultSet1.getString("password")+"\tRegistrationDate" +
                                    resultSet1.getDate("registrationDate")+"User Type:"+resultSet1.getObject("userType"));
                        }
                    }
                    break;
                case 7:
                    ResultSet resultSet6 = searcher.getPlaybackHistory(user);
                    System.out.println("The result:");
                    if(resultSet6 != null){
                        while(resultSet6.next()){
                            System.out.println("User:"+resultSet6.getString("userId")+"\tMusic:"+resultSet6.getString("musicId"));
                        }
                    }
                    break;
                case 8:
                    System.out.println("Enter the name of music");
                    String musicName3 = new Scanner(System.in).nextLine();
                    System.out.println("Enter the year of release");
                    int musicYear3 = new Scanner(System.in).nextInt();
                    user.getPlaylist().getPlaylist().add(new Music(musicName3,AudioType.FILE,musicYear3,Subscription.NONE,Genre.WESTERN,new Review()));
                    searcher.insertIntoPlaybackHistory(user);
                    break;
                case 9:
                    searcher.insertIntoSubscription(user);
                    break;
                case 10:
                    if(user.getOwnedReviews() == null){
                        user.setOwnedReviews(new ArrayList<>());
                    }
                    Review review = new Review();
                    System.out.println("Enter the name of music reviewed");
                    String music = new Scanner(System.in).nextLine();
                    review.setNameOfTheMusicReviewed(music);
                    System.out.println("Enter the review");
                    review.setReview(new Scanner(System.in).nextLine());
                    review.setPersonWhoReviewed(user.getId());
                    review.setDateOfReview(new Date());
                    user.getOwnedReviews().add(review);
                    searcher.insertIntoReview(user);
                    System.out.println("Inserted");
                    break;
                case 11:
                    if(user.getLibrary() == null){
                        user.setLibrary(new Library());
                    }
                    System.out.println("Enter the name of album");
                    String albumName2 = new Scanner(System.in).nextLine();
                    System.out.println("Enter the name of music");
                    String musicName4 = new Scanner(System.in).nextLine();
                    System.out.println("Enter the year of release");
                    int musicYear4 = new Scanner(System.in).nextInt();
                    Album album2 = new Album();
                    album2.setAlbumAudio(new ArrayList<>());
                    album2.setAlbumName(albumName2);
                    album2.getAlbumAudio().add(new Music(musicName4,AudioType.FILE,musicYear4,Subscription.NONE,Genre.METAL,new Review()));
                    user.getLibrary().getAlbumLibrary().add(album2);
                    searcher.insertIntoLibrary(user);
                    break;
                case 12:
                    AcquiringAudioManager acquiringAudioManager = new AcquiringAudioManager();
                    acquiringAudioManager.setSearcher(searcher);
                    System.out.println("Enter the name of music");
                    acquiringAudioManager.saveObject(new Scanner(System.in).nextLine());
                    break;
                case 13:
                    ResultSet resultSet5 = searcher.getUserOwned(user);
                    System.out.println("The result:");
                    if(resultSet5 != null){
                        while(resultSet5.next()){
                            System.out.println("User:"+resultSet5.getString("id")+"\tMusic:"+resultSet5.getString("musicId")+"\t" +
                                    "Album:"+resultSet5.getString("albumId"));
                        }
                    }
                    break;
                case 14:
                    System.out.println("Enter the name of music");
                    String musicName = new Scanner(System.in).nextLine();
                    System.out.println("Enter the year of release");
                    int musicYear = new Scanner(System.in).nextInt();
                    searcher.insertIntoMusic(new Music(musicName,AudioType.FILE,musicYear,Subscription.PREMIUM,Genre.JAZZ,new Review()));
                    System.out.println("Inserted");
                    break;
                case 15:
                    System.out.println("Enter the name of album");
                    String albumName = new Scanner(System.in).nextLine();
                    System.out.println("Enter the name of music");
                    String musicName1 = new Scanner(System.in).nextLine();
                    System.out.println("Enter the year of release");
                    int musicYear1 = new Scanner(System.in).nextInt();
                    Album album = new Album();
                    album.setAlbumName(albumName);
                    album.setAlbumAudio(new ArrayList<>());
                    album.getAlbumAudio().add(new Music(musicName1,AudioType.FILE,musicYear1,Subscription.PREMIUM,Genre.BREAKCORE,new Review()));
                    searcher.insertIntoAlbum(album);
                    System.out.println("Inserted");
                    break;
                case 16:
                    ResultSet resultSet3 = searcher.getReview(user);
                    System.out.println("The result:");
                    if(resultSet3 != null){
                        while(resultSet3.next()){
                            System.out.println("MusicReviewed:"+resultSet3.getString("reviewOnMusicId")+"\tUser:"+resultSet3.getString("reviewByUserId")+"\t" +
                                    "Rating:"+resultSet3.getInt("rating")+"\tDescription:"+resultSet3.getString("description")+"\t" +
                                    "Date:"+resultSet3.getDate("dateOfReview"));
                        }
                    }
                    break;
                case 17:
                    ResultSet resultSet2 = searcher.getFavourite(user);
                    System.out.println("The result:");
                    if(resultSet2 != null){
                        while(resultSet2.next()){
                            System.out.println("User:"+resultSet2.getString("userId")+"\tmusicId:"+resultSet2.getString("musicId")+"\t" +
                                    "albumId:"+resultSet2.getString("albumId"));
                        }
                    }
                    break;
                case 18:
                    System.out.println("Enter the name of album");
                    String albumName1 = new Scanner(System.in).nextLine();
                    System.out.println("Enter the name of music");
                    String musicName2 = new Scanner(System.in).nextLine();
                    System.out.println("Enter the year of release");
                    int musicYear2 = new Scanner(System.in).nextInt();
                    Album album1 = new Album();
                    album1.setAlbumName(albumName1);
                    album1.setAlbumAudio(new ArrayList<>());
                    album1.getAlbumAudio().add(new Music(musicName2,AudioType.FILE,musicYear2,Subscription.PREMIUM,Genre.BREAKCORE,new Review()));
                    user.setOwnedAlbums(new ArrayList<>());
                    user.getOwnedAlbums().add(album1);
                    searcher.insertIntoUserOwned(user);
                    break;
                case 19:
                    ResultSet resultSet4 = searcher.getUserSettings(user.getId());
                    System.out.println("The result:");
                    if(resultSet4 != null){
                        while(resultSet4.next()){
                            System.out.println("User:"+resultSet4.getString("userId")+"\tFont:"+resultSet4.getString("font")+"\t" +
                                    "Theme:"+resultSet4.getString("theme"));
                        }
                    }
                    break;
                case 20:
                    ResultSet resultSet7 = searcher.getLibrary(user);
                    System.out.println("The result:");
                    if(resultSet7 != null){
                        while(resultSet7.next()){
                            System.out.println("User:"+resultSet7.getString("userId")+"\tMusic:"+resultSet7.getString("musicId")+"\t" +
                                    "Album:"+resultSet7.getString("albumId"));
                        }
                    }
                default:
                    break;
            }
        }
        catch (Exception e){
            System.out.println("Error!");
            e.printStackTrace();
            return;
        }
    }
}
