package by.losik.audioCollection;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.sql.ResultSet;

public class AcquiringAudioManager {
    private Searcher searcher;
    private JSONObject objectMusic = new JSONObject();

    public Searcher getSearcher() {
        return searcher;
    }

    public void setSearcher(Searcher searcher) {
        this.searcher = searcher;
    }

    public JSONObject saveObject(String nameOfMusic) {
        try{
            ResultSet resultSet = searcher.getMusic(nameOfMusic);
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
            this.objectMusic = new JSONObject().put("music",jsonArray);
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
        return objectMusic;
    }
}
