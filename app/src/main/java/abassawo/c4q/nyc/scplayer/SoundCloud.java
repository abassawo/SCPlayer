package abassawo.c4q.nyc.scplayer;

import retrofit.RestAdapter;

/**
 * Created by c4q-Abass on 12/17/15.
 */

public class SoundCloud {

    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder().setEndpoint(Config.API_URL).build();
    private static final SCService SERVICE = REST_ADAPTER.create(SCService.class);

    public static SCService getService() {
        return SERVICE;
    }
}
