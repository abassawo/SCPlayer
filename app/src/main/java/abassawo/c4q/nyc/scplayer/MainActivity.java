package abassawo.c4q.nyc.scplayer;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private List<Track > mTracks;
    private TrackRVAdapter mTrackAdapter;
    private RecyclerView mRecyclerView;
    private TextView mSelectedTrackTitle;
    private ImageView mSelectedTrackImage;
    private MediaPlayer mMediaPlayer;
    private ImageView mPlayerControl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                togglePlayPause();
            }
        });
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mPlayerControl.setImageResource(android.R.drawable.ic_media_play);
            }
        });
        mPlayerControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePlayPause();
            }
        });
        mRecyclerView = (RecyclerView)findViewById(R.id.track_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTracks = new ArrayList<Track>();
        mTrackAdapter = new TrackRVAdapter(mTracks);

        setupRetrofit();
    }

    private void togglePlayPause() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mPlayerControl.setImageResource(android.R.drawable.ic_media_play);
        } else {
            mMediaPlayer.start();
            mPlayerControl.setImageResource(android.R.drawable.ic_media_pause);
        }
    }

    public void playTrack(Track track){
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();
        }
        try {
            mMediaPlayer.setDataSource(track.getStreamURL() + "?client_id=" + Config.CLIENT_ID);
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateUI(){
        mTrackAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mTrackAdapter);
    }

    public void initViews(){
        mPlayerControl = (ImageView)findViewById(R.id.player_control);
        mSelectedTrackTitle = (TextView)findViewById(R.id.selected_track_title);
        mSelectedTrackImage = (ImageView)findViewById(R.id.selected_track_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void setupRetrofit(){
        final SCService scService = SoundCloud.getService();
        scService.getRecentTracks(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()), new Callback<List<Track>>() {
            @Override
            public void success(List<Track> tracks, Response response) {
                Log.d(TAG, "First track title: " + tracks.get(0).getTitle());
                mTracks.addAll(tracks);
                updateUI();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Error: " + error);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class TrackHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView trackImageView;
        TextView titleTextView;
        private Context mContext;
        private Track mItem;

        public TrackHolder(View itemView) {
            super(itemView);
            mContext = getApplicationContext();
            trackImageView = (ImageView) itemView.findViewById(R.id.track_image);
            titleTextView = (TextView) itemView.findViewById(R.id.track_title);
            itemView.setOnClickListener(this);
        }

        public void bindTrack(Track track){
            mItem = track;
            titleTextView.setTextColor(getResources().getColor(R.color.colorText));
            titleTextView.setText(track.getTitle());
            Picasso.with(mContext).load(track.getArtworkURL()).into(this.trackImageView);
        }

        @Override
        public void onClick(View v) {
            mSelectedTrackTitle.setText(mItem.getTitle());
            Picasso.with(MainActivity.this).load(mItem.getArtworkURL()).into(mSelectedTrackImage);
            playTrack(mItem);

        }
    }

    public class TrackRVAdapter extends RecyclerView.Adapter<TrackHolder>{
        private List<Track> mTracks;

        public TrackRVAdapter(List<Track> items) {
            mTracks = items;
        }

        @Override
        public TrackHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            View view = inflater.inflate(R.layout.track_list_row, parent, false);
            return new TrackHolder(view);
        }

        @Override
        public void onBindViewHolder(TrackHolder holder, int position) {
            Track item = mTracks.get(position);
            holder.bindTrack(item);

        }

        @Override
        public int getItemCount() {
            return mTracks.size();
        }
    }



}
