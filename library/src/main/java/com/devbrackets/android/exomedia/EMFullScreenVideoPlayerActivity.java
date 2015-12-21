package com.devbrackets.android.exomedia;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.devbrackets.android.exomedia.listener.EMVideoViewControlsCallback;
import com.devbrackets.android.exomedia.util.MediaUtil;
import com.devbrackets.android.exomedia.widget.DefaultControls;

/**
 * A simple example of making a fullscreen video player activity.
 * <p>
 * <b><em>NOTE:</em></b> the EMVideoView setup is done in the {@link}
 */
public class EMFullScreenVideoPlayerActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener {

    static String VideoUrlToPlay = "";
    static MediaUtil.MediaType VideoMediaType;

    public static  void startFullScreenPlay(Activity activity,String url, MediaUtil.MediaType mediaType)
    {
        if (url == null || url.isEmpty())return;
        VideoUrlToPlay = url;
        VideoMediaType = mediaType;
        activity.startActivity(new Intent(activity,EMFullScreenVideoPlayerActivity.class));
    }

    protected ViewGroup containerView;
    protected EMVideoView emVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreen_video_player_activity);
        containerView = (ViewGroup)findViewById(R.id.video_view_container);

        emVideoView = (EMVideoView)findViewById(R.id.video_play_activity_video_view);
        emVideoView.setOnPreparedListener(this);
        emVideoView.setVideoViewControlsCallback(new DefaultControlsCallback());
        emVideoView.setVideoURI(Uri.parse(VideoUrlToPlay), VideoMediaType);
        emVideoView.setIsFullScreen(true);
        emVideoView.setReleaseOnDetachFromWindow(false);
    }


    @Override
    protected void onStop() {
        releaseVideoView();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        releaseVideoView();
        super.onDestroy();
    }

    void  releaseVideoView()
    {
        if (emVideoView != null) {
            containerView.removeAllViews();
            emVideoView.release();
            emVideoView = null;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        emVideoView.start(true);
    }


    /**
     * A Listener for the {@link DefaultControls}
     * so that we can re-enter fullscreen mode when the controls are hidden.
     */
    private class DefaultControlsCallback implements EMVideoViewControlsCallback {
        @Override
        public boolean onFullScreenClicked() {
            onBackPressed();
            return true;
        }

        @Override
        public boolean onPlayPauseClicked() {
            return false; // No additional functionality performed
        }

        @Override
        public boolean onPreviousClicked() {
            return false; // No additional functionality performed
        }

        @Override
        public boolean onNextClicked() {
            return false; // No additional functionality performed
        }

        @Override
        public boolean onControlsShown() {
            return false; // No additional functionality performed
        }

        @Override
        public boolean onControlsHidden() {
            return false;
        }
    }
}
