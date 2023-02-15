package com.farzin.aparat.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.VolumeShaper;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.farzin.aparat.ORM.AppDataBase;
import com.farzin.aparat.R;
import com.farzin.aparat.Utils.AppConfig;
import com.farzin.aparat.databinding.ActivityPlayBinding;
import com.farzin.aparat.databinding.CustomControlLayoutBinding;
import com.farzin.aparat.models.Creator;
import com.farzin.aparat.models.VideoVO;
import com.farzin.aparat.models.Videos;
import com.farzin.aparat.webService.ApiCaller;
import com.farzin.aparat.webService.MassageListener;
import com.google.android.exoplayer2.DeviceInfo;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.PlayerMessage;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Tracks;
import com.google.android.exoplayer2.analytics.AnalyticsCollector;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.audio.AuxEffectInfo;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.ShuffleOrder;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.CueGroup;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionParameters;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoFrameMetadataListener;
import com.google.android.exoplayer2.video.VideoSize;
import com.google.android.exoplayer2.video.spherical.CameraMotionListener;

import java.util.List;

public class PlayActivity extends AppCompatActivity {

    private ActivityPlayBinding binding;
    private Bundle bundle;
    private Videos videos;
    private ExoPlayer exoPlayer;
    private AppDataBase appDataBase;
    private ApiCaller apiCaller;
    private AppConfig appConfig;
    private boolean isButtonVisible = false;
    private boolean isFullscreen = false;
    private boolean isLock = false;
    private AppCompatImageView btFullscreen;
    private AppCompatImageView btLock;
    private LinearLayout sec_controlvid1, sec_controlvid2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startBinding();
        getExtras();
        appConfig = new AppConfig(getApplicationContext());
        apiCaller = new ApiCaller();
        binding.viewTxt.setText(videos.getView()+"");
        binding.descTxt.setText(videos.getDescription());
        binding.txtTitle.setText(videos.getTitle());


        btFullscreen = findViewById(R.id.bt_fullscreen);
        //تمام صفحه کردن ویدیو پلیر
        btFullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFullscreen){
                    btFullscreen.setImageDrawable(ContextCompat.getDrawable(getApplication(),R.drawable.exit_fullscreen));
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    binding.parentall.setVisibility(View.GONE);
                }else {
                    btFullscreen.setImageDrawable(ContextCompat.getDrawable(getApplication(),R.drawable.fullscreen));
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    binding.parentall.setVisibility(View.VISIBLE);
                }
                isFullscreen = !isFullscreen;
            }
        });

        //قفل کردن صفحه
        btLock = findViewById(R.id.exo_lock);
        btLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLock){
                    btLock.setImageDrawable(ContextCompat.getDrawable(getApplication(),R.drawable.lock));
                }else {
                    btLock.setImageDrawable(ContextCompat.getDrawable(getApplication(),R.drawable.unlock));
                }
                isLock = !isLock;
                lockScreen(isLock);
            }
        });




//        if (appConfig.getBookmark() == true){
//            binding.bookmarkFilled.setVisibility(View.VISIBLE);
//            binding.bookmarkBorder.setVisibility(View.GONE);
//        }else {
//            binding.bookmarkFilled.setVisibility(View.GONE);
//            binding.bookmarkBorder.setVisibility(View.VISIBLE);
//        }

        binding.linBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //ستاپ کردن exoplayer
        exoPlayer = new ExoPlayer.Builder(getApplicationContext())
                .setSeekBackIncrementMs(5000)
                .setSeekForwardIncrementMs(5000)
                .build();
        //

        //مشخص کردن منبع داده
        DataSource.Factory dataSource = new DefaultHttpDataSource.Factory().setUserAgent(Util.getUserAgent(getApplicationContext(),"Aparat"));
        //


//        //پخش کردن اهنگ از فایل اندروید
//        String path = RawResourceDataSource.buildRawResourceUri(R.raw.myvideo).toString();
//        Uri uri1 = Uri.parse(path);
//        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSource).createMediaSource(MediaItem.fromUri(uri1));
//        //پخش کردن اهنگ از فایل اندروید


        //ست کردن لینک آدرس به mediaSource
        Uri uri = Uri.parse(videos.getLink());
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSource).createMediaSource(MediaItem.fromUri(uri));
        exoPlayer.setMediaSource(mediaSource);
        //



        binding.exoPlayer.setKeepScreenOn(true);
        exoPlayer.prepare();
        binding.exoPlayer.setPlayer(exoPlayer);
        exoPlayer.setPlayWhenReady(true);


        binding.bookmarkBorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                VideoVO videoVO = new VideoVO(videos.getId(),videos.getCatId(),videos.getTitle(),videos.getIcon(),
                        videos.getCreator(), videos.getDescription(), videos.getLink(), videos.getView(),
                        videos.getTime(), videos.getSpecial());

                appDataBase = AppDataBase.getInstance(getApplicationContext());
                long result = appDataBase.videoDAO().insertVideo(videoVO);

                if (result > 0){
                    Toast.makeText(PlayActivity.this, getResources().getString(R.string.added_to_favs), Toast.LENGTH_SHORT).show();
//                    binding.bookmarkFilled.setVisibility(View.VISIBLE);
//                    binding.bookmarkBorder.setVisibility(View.GONE);
//                    isButtonVisible = true;
//                    appConfig.setBookmark(isButtonVisible);


                }else {
                    Toast.makeText(PlayActivity.this, getResources().getString(R.string.sth_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }
        });

        binding.bookmarkFilled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VideoVO videoVO = new VideoVO(videos.getId(),videos.getCatId(),videos.getTitle(),videos.getIcon(),
                        videos.getCreator(), videos.getDescription(), videos.getLink(), videos.getView(),
                        videos.getTime(), videos.getSpecial());

                appDataBase = AppDataBase.getInstance(getApplicationContext());
                int result = appDataBase.videoDAO().deleteFavorites(videoVO);

                if (result > 0){
                    Toast.makeText(PlayActivity.this, getResources().getString(R.string.remove_fom_favs), Toast.LENGTH_SHORT).show();
//                    binding.bookmarkFilled.setVisibility(View.GONE);
//                    binding.bookmarkBorder.setVisibility(View.VISIBLE);
//                    isButtonVisible = false;
//                    appConfig.setBookmark(isButtonVisible);

                }else {
                    Toast.makeText(PlayActivity.this, getResources().getString(R.string.sth_went_wrong), Toast.LENGTH_SHORT).show();
                }
            }
        });


        apiCaller.increaseViewNumber(videos.getId(), new MassageListener() {
            @Override
            public void onSuccess(Object successMassage) {
                Log.e("", "" );
            }

            @Override
            public void onFailure(String errorMassage) {
                Log.e("", "" );
            }
        });

        apiCaller.getCreator(videos.getCreator(), new MassageListener() {
            @Override
            public void onSuccess(Object successMassage) {
                Creator creator = (Creator) successMassage;
                binding.creatorTxt.setText(creator.getTitle());
            }

            @Override
            public void onFailure(String errorMassage) {

            }
        });





    }

    ////قفل کردن صفحه
    private void lockScreen(boolean isLock) {
        sec_controlvid1 = findViewById(R.id.sec_controlvid1);
        sec_controlvid2 = findViewById(R.id.sec_controlvid2);

        if (isLock){
            sec_controlvid1.setVisibility(View.INVISIBLE);
            sec_controlvid2.setVisibility(View.INVISIBLE);
        }else {
            sec_controlvid1.setVisibility(View.VISIBLE);
            sec_controlvid2.setVisibility(View.VISIBLE);
        }
    }

    //استاپ کردن پلیر وقتی که اکتیویتی بسته شد
    @Override
    protected void onStop() {
        super.onStop();
        exoPlayer.stop();
    }

    //بعد از زدن دکمه برگشت به حالت portrait درمی اید
    @Override
    public void onBackPressed() {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                binding.parentall.setVisibility(View.VISIBLE);
                btFullscreen.setImageDrawable(ContextCompat.getDrawable(getApplication(),R.drawable.fullscreen));
                isFullscreen = false;
            }
            else{
                super.onBackPressed();
            }

    }

    //گرفتن ابجکت video از ویدیو اداپتر
    private void getExtras() {
        bundle = getIntent().getExtras();
        videos = new Videos();
        if (bundle != null){
            videos = bundle.getParcelable("video");
        }

    }


    //starting to bind
    private void startBinding() {
        binding = ActivityPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}