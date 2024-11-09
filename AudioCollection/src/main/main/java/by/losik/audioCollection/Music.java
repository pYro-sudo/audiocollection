package by.losik.audioCollection;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.sql.ResultSet;

public class Music {
    private String id;
    private AudioType type;
    private int releaseYear;
    private Subscription availableOnLevel;

    private Genre genre;

    private Review review;

    public Music(String name, AudioType type, int releaseYear, Subscription availableOnLevel, Genre genre, Review review){
        this.id = name;
        this.availableOnLevel = availableOnLevel;
        this.type = type;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.review = review;
    }

    public String getName() {
        return id;
    }

    public void setName(String name) {
        this.id = name;
    }

    public Subscription changeAvailabilityLevel(Subscription subscription){
        this.availableOnLevel = subscription;
        return subscription;
    }

    public Subscription getAvailableOnLevel(){
        return availableOnLevel;
    }

    public AudioType getType() {
        return this.type;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Review getReview() {
        return review;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public JSONObject saveObject(String nameOfMusic) {
        try{
            JSONObject objectMusic;
            ResultSet resultSet = new Searcher().getMusic(nameOfMusic);
            JSONArray jsonArray = new JSONArray();
            if(!(resultSet == null)){
                while(resultSet.next()){
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id",resultSet.getString("id"));
                    jsonObject.put("audioType",resultSet.getString("audioType"));
                    jsonObject.put("releaseDate",resultSet.getString("releaseDate"));
                    jsonObject.put("availableOnLevel",resultSet.getString("availableOnLevel"));
                    jsonObject.put("genre",resultSet.getString("genre"));
                    jsonArray.put(jsonObject);
                }
            }
            else{
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", "none");
                jsonObject.put("audioType","none");
                jsonObject.put("releaseDate","none");
                jsonObject.put("availableOnLevel","none");
                jsonObject.put("genre","none");
                jsonArray.put(jsonObject);
            }
            objectMusic = new JSONObject().put("music",jsonArray);
            FileWriter fileWriter = new FileWriter("savedAudio.json",true);
            fileWriter.write(objectMusic.toString());
            fileWriter.close();
            return objectMusic;
        }
        catch (Exception e){
            System.out.println("something went wrong");
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject getObjectMusic() {
        return new JSONObject();
    }
}
