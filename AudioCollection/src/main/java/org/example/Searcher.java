package org.example;

import org.postgresql.Driver;

import java.util.HashMap;
import java.sql.*;
import java.util.Map;

public class Searcher { //query execution
    private String password = "postgres";
    private String username = String.valueOf(password);
    public boolean authorised(String username, String password){
        try {
            //Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                String selectQuery = "SELECT * FROM userDetails WHERE password ="+password+" AND id = " + username;
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(selectQuery);
                resultSet.close();
                statement.close();
                con.close();
                return resultSet.next();
            } else {
                System.out.println("Not Connected...");
            }
            return false;
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return false;
        }
    }
    public ResultSet getMusic(String nameOfMusic){
        try {
            //Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                String selectQuery = "SELECT * FROM music WHERE id ="+nameOfMusic;
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(selectQuery);
                resultSet.close();
                statement.close();
                con.close();
                return resultSet;
            } else {
                System.out.println("Not Connected...");
            }
            return null;
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return null;
        }
    }

    public ResultSet getAlbums(String nameOfMusic){
        try {
            //Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                String selectQuery = "SELECT * FROM album WHERE musicId ="+nameOfMusic;
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(selectQuery);
                resultSet.close();
                statement.close();
                con.close();
                return resultSet;
            } else {
                System.out.println("Not Connected...");
            }
            return null;
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return null;
        }
    }

    public Music insertIntoMusic(Music music){
        try {
            //Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                String sql = "INSERT INTO music (id, audiotype, releaseyear, availableonlevel, genre)" +
                        " Values (?, "+music.getType().name()+", ?, "+music.getAvailableOnLevel().name()+
                        ", "+music.getGenre().name()+")";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, music.getName());
                preparedStatement.setInt(3, music.getReleaseYear());
                return music;
            } else {
                System.out.println("Not Connected...");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return null;
        }
    }

    public Album insertIntoAlbum(Album album){
        try {
            //Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                for(Music music : album.getAlbumAudio()){
                    String sql = "INSERT INTO album (id, musicId) Values (?, ?)";
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, album.getAlbumName());
                    preparedStatement.setString(2, music.getName());
                }
                return album;
            } else {
                System.out.println("Not Connected...");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return null;
        }
    }

    public UserDetails insertIntoUserDetails(UserDetails userDetails){
        try {
            //Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                String sql = "INSERT INTO userdetails (id, password, registrationdate, usertype)" +
                    "VALUES (?, ?, ?, "+userDetails.getType()+")";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, userDetails.getId());
                preparedStatement.setString(2, userDetails.getPassword());
                preparedStatement.setDate(3, (Date) userDetails.getRegistrationDate());
                return userDetails;
            } else {
                System.out.println("Not Connected...");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return null;
        }
    }

    public ResultSet getUserDetails(String username, String password){
        try {
            //Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                String selectQuery = "SELECT * FROM userDetails WHERE password ="+password+" AND id = " + username;
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(selectQuery);
                resultSet.close();
                statement.close();
                con.close();
                return resultSet;
            } else {
                System.out.println("Not Connected...");
            }
            return null;
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return null;
        }
    }

    public ResultSet getUserSettings(String username){
        try {
            //Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                String selectQuery = "SELECT * FROM usersetting WHERE id ="+username;
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(selectQuery);
                resultSet.close();
                statement.close();
                con.close();
                return resultSet;
            } else {
                System.out.println("Not Connected...");
            }
            return null;
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return null;
        }
    }

    public User insertIntoUserSettings(User user){
        try {
            //Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                String sql = "INSERT INTO usersetting (id, font, themetype)" +
                        "VALUES (?, "+user.getSettings().getFont().name()+", "+ user.getSettings().getFont().name()+")";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, user.getUserDetails().getId());
                return user;
            } else {
                System.out.println("Not Connected...");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return null;
        }
    }

    public ResultSet getUserOwned(User user){
        try {
            //Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                String selectQuery = "SELECT * FROM userOwned WHERE id ="+user.getUserDetails().getId();
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(selectQuery);
                resultSet.close();
                statement.close();
                con.close();
                return resultSet;
            } else {
                System.out.println("Not Connected...");
            }
            return null;
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return null;
        }
    }

    public User insertIntoUserOwned(User user){
        try {
            //Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                for(Album album : user.getOwnedAlbums()){
                    for(Music music : album.getAlbumAudio()){
                        String sql = "INSERT INTO userowned (id, musicid, albumid)" +
                                "VALUES (?, ?, ?)";
                        PreparedStatement preparedStatement = con.prepareStatement(sql);
                        preparedStatement.setString(1, user.getUserDetails().getId());
                        preparedStatement.setString(2, album.getAlbumName());
                        preparedStatement.setString(3, music.getName());
                    }
                }
                return user;
            } else {
                System.out.println("Not Connected...");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return null;
        }
    }

    public ResultSet getReview(User user){
        try {
            //Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                String selectQuery = "SELECT * FROM review WHERE reviewByUserId ="+user.getUserDetails().getId();
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(selectQuery);
                resultSet.close();
                statement.close();
                con.close();
                return resultSet;
            } else {
                System.out.println("Not Connected...");
            }
            return null;
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return null;
        }
    }

    public User insertIntoReview(User user){
        try {
            //Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                for(Review review : user.getOwnedReviews()){
                    String sql = "INSERT INTO review (reviewOnMusicId, reviewByUserId, rating, description, dateOfReview)" +
                            "VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, review.getNameOfTheMusicReviewed());
                    preparedStatement.setString(2, user.getUserDetails().getId());
                    preparedStatement.setDouble(3, review.getRating());
                    preparedStatement.setString(4, review.getReview());
                    preparedStatement.setDate(5, (Date) review.getDateOfReview());
                }
                return user;
            } else {
                System.out.println("Not Connected...");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return null;
        }
    }

    public ResultSet getPlaybackHistory(User user){
        try {
            //Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                String selectQuery = "SELECT * FROM playbackhistory WHERE userid ="+user.getUserDetails().getId();
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(selectQuery);
                resultSet.close();
                statement.close();
                con.close();
                return resultSet;
            } else {
                System.out.println("Not Connected...");
            }
            return null;
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return null;
        }
    }

    public User insertIntoPlaybackHistory(User user){
        try {
            //Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                for(Music music : user.getPlaybackHistory().getLastHeardAudios()){
                    String sql = "INSERT INTO playbackhistory (userid, musicid)" +
                            "VALUES (?, ?)";
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, user.getUserDetails().getId());
                    preparedStatement.setString(2, music.getName());
                }
                return user;
            } else {
                System.out.println("Not Connected...");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return null;
        }
    }

    public ResultSet getLibrary(User user){
        try {
            //Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                String selectQuery = "SELECT * FROM library WHERE userid ="+user.getUserDetails().getId();
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(selectQuery);
                resultSet.close();
                statement.close();
                con.close();
                return resultSet;
            } else {
                System.out.println("Not Connected...");
            }
            return null;
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return null;
        }
    }

    public User insertIntoLibrary(User user){
        try {
            //Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                for(Album album : user.getLibrary().getAlbumLibrary()){
                    for(Music music : album.getAlbumAudio()){
                        String sql = "INSERT INTO library (userid, musicid, albumid)" +
                                "VALUES (?, ?, ?)";
                        PreparedStatement preparedStatement = con.prepareStatement(sql);
                        preparedStatement.setString(1, user.getUserDetails().getId());
                        preparedStatement.setString(2, music.getName());
                        preparedStatement.setString(3, album.getAlbumName());
                    }
                }
                return user;
            } else {
                System.out.println("Not Connected...");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return null;
        }
    }

    public ResultSet getFavourite(User user){
        try {
            //Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                String selectQuery = "SELECT * FROM favourite WHERE userid ="+user.getUserDetails().getId();
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(selectQuery);
                resultSet.close();
                statement.close();
                con.close();
                return resultSet;
            } else {
                System.out.println("Not Connected...");
            }
            return null;
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return null;
        }
    }

    public User insertIntoFavourite(User user){
        try {
            //Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                HashMap<String, String> albumsResult = new HashMap<>();
                for(Music music: user.getFavouriteMusic()){
                    String selectQuery = "SELECT * FROM album WHERE musicid ="+music.getName();
                    Statement statement = con.createStatement();
                    ResultSet resultSet = statement.executeQuery(selectQuery);
                    while(resultSet.next()){
                        albumsResult.put(resultSet.getString("id"),resultSet.getString("musicId"));
                    }
                    resultSet.close();
                    statement.close();
                }
                for(Map.Entry<String, String> entry : albumsResult.entrySet()){
                    String sql = "INSERT INTO favourite (userid, musicid, albumid)" +
                            "VALUES (?, ?, ?)";
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, user.getUserDetails().getId());
                    preparedStatement.setString(2, entry.getValue());
                    preparedStatement.setString(3, entry.getKey());
                }
                return user;
            } else {
                System.out.println("Not Connected...");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return null;
        }
    }

    public ResultSet getSubscription(String name){
        try {
            //Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                String selectQuery = "SELECT * FROM subscription WHERE id ="+name;
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(selectQuery);
                resultSet.close();
                statement.close();
                con.close();
                return resultSet;
            } else {
                System.out.println("Not Connected...");
            }
            return null;
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return null;
        }
    }

    public User insertIntoSubscription(User user){
        try {
            //Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                String sql = "INSERT INTO subscription (id, subscriptiontype, beginning, expiration)" +
                        "VALUES (?, "+user.getUserDetails().getSubscription().getSubscriptionType()+", ?, ?)";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, user.getUserDetails().getId());
                preparedStatement.setDate(3,(Date) user.getUserDetails().getSubscription().getBeginningDate());
                preparedStatement.setDate(4,(Date) user.getUserDetails().getSubscription().getExpirationDate());
                return user;
            } else {
                System.out.println("Not Connected...");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return null;
        }
    }

    public String dropAll(){
        try {
            Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                String dropQuery = "drop table userSetting cascade;\n"+
                        "drop table userOwned cascade;\n" +
                        "drop table favourite cascade;\n" +
                        "drop table library cascade;\n" +
                        "drop table settings cascade;\n" +
                        "drop table playbackHistory cascade;\n" +
                        "drop table review cascade;\n" +
                        "drop table album cascade;\n" +
                        "drop table userDetails cascade;\n" +
                        "drop table music cascade;\n" +
                        "drop table subscription cascade;\n";
                Statement statement = (Statement) con.createStatement().executeQuery(dropQuery);
                statement.close();
                con.close();
                return "All tables are dropped";
            } else {
                System.out.println("Not Connected...");
            }
            return "All tables are dropped";
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return "All tables are dropped";
        }
    }

    public String initAll(){
        try {
            Class<Driver> driverClass = Driver.class;
            Connection con=DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",this.password,this.username);
            if (con != null) {
                String createQuery = "create table userDetails( id varchar(20) primary key unique not null, password varchar(20) not null, registrationDate date not null, userType varchar(20) not null);\n"+
                "create table userSetting( id varchar(20) references userDetails(id) not null unique, font varchar(20) not null, themeType varchar(20) not null);\n"+
                "create table subscription( id varchar(20) references userDetails(id) unique, subscriptionType varchar(20) primary key not null, beginning date, expiration date);\n" +
                "create table music( id varchar(20) not null primary key, audioType varchar(20) not null, releaseYear int not null, availableOnLevel varchar(20) references subscription(subscriptionType) not null, genre varchar(20) not null);\n" +
                "create table album( id varchar(20) not null primary key, musicId varchar(20) references music(id) not null unique);\n"+
                "create table userOwned( id varchar(20) references userDetails(id) not null, musicId varchar(20) references music(id) not null, albumId varchar(20) references album(id) not null);\n"+
                "create table review( reviewOnMusicId varchar(20) references music(id) not null, reviewByUserId varchar(20) references userDetails(id) not null, rating decimal not null check ( rating >= 0 or rating <= 10 ), description varchar(256), dateOfReview date not null);\n"+
                "create table playbackHistory( userId varchar(20) references userDetails(id) not null, musicId varchar(20) references music(id) not null);\n"+
                "create table settings( userId varchar(20) references userDetails(id) not null unique, font varchar(20) not null, theme varchar(20) not null);\n"+
                "create table Library( userId varchar(20) references userDetails(id) not null, musicId varchar(20) references music(id) not null unique, albumId varchar(20) references album(id) not null);\n"+
                "create table favourite( userId varchar(20) references userDetails(id) not null, musicId varchar(20) references music(id) not null, albumId varchar(20) references album(id) not null);";
                Statement statement = (Statement) con.createStatement().executeQuery(createQuery);
                statement.close();
                con.close();
                return "All tables are created";
            } else {
                System.out.println("Not Connected...");
            }
            return "All tables are created";
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
            return "All tables are created";
        }
    }
}
