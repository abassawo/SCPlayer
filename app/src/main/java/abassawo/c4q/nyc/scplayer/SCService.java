package abassawo.c4q.nyc.scplayer;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by c4q-Abass on 12/17/15.
 */
public interface SCService {

    @GET("/tracks?client_id=" + Config.CLIENT_ID)
    public void getRecentTracks(@Query("created_at[from]") String date, Callback<List<Track>> cb );


    @GET("/tracks?client_id=" + Config.CLIENT_ID)
    public void search(@Query("title[from]") String title, Callback<List<Track>> cb );

    //fixme
    @GET("/users/favorites?client_id=" + Config.CLIENT_ID)
    public void searchUsers(@Query("users[from]") String title, Callback<List<User>> cb );
}
