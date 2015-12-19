package com.devbrackets.android.exomediademo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.devbrackets.android.exomedia.EMFullScreenVideoPlayerActivity;
import com.devbrackets.android.exomedia.util.MediaUtil;
import com.devbrackets.android.exomediademo.R;
import com.devbrackets.android.exomediademo.adapter.StartupListAdapter;

public class StartupActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup_activity);

        ListView exampleList = (ListView) findViewById(R.id.startup_activity_list);
        exampleList.setAdapter(new StartupListAdapter(this));
        exampleList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case StartupListAdapter.INDEX_VIDEO_PLAYBACK:
                startVideoPlayerActivity();
                break;

            case StartupListAdapter.INDEX_VIDEO_PLAYBACK_FULLSCREEN:
                startFullscreenVideoPlayerActivity();
                break;

            case StartupListAdapter.INDEX_AUDIO_PLAYBACK:
                startAudioPlayerActivity();
                break;

            default:
        }
    }

    private void startVideoPlayerActivity() {
        Intent intent = new Intent(this, VideoPlayerActivity.class);
        startActivity(intent);
    }

    private void startFullscreenVideoPlayerActivity() {
        EMFullScreenVideoPlayerActivity.startFullScreenPlay(this,"http://us.sinaimg.cn/0017W0DNjx06XTEl1ZfN050d010000i60k01.m3u8", MediaUtil.MediaType.HLS);
    }

    private void startAudioPlayerActivity() {
        Intent intent = new Intent(this, AudioSelectionActivity.class);
        startActivity(intent);
    }
}
