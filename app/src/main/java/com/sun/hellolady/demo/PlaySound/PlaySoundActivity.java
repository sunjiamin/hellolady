package com.sun.hellolady.demo.PlaySound;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.sun.hellolady.BuildConfig;
import com.sun.hellolady.R;
import com.support.util.common.LogUtil;

public class PlaySoundActivity extends AppCompatActivity implements View.OnClickListener {

    private SoundPool.Builder soundPoolBuilder;
    private SoundPool soundPool;
    private MediaPlayer player;
    private int soundId;
    private Button btn_play;
    private RelativeLayout activity_play_sound;
    private Button button12;
    int successFlag;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_sound);
//        soundPoolBuilder = new SoundPool.Builder();
//        soundPoolBuilder.setMaxStreams(10);
//        //soundPoolBuilder.setAudioAttributes(null);
//        soundPool = soundPoolBuilder.build();
//        soundId = soundPool.load(this, R.raw.videophone_come, 1);
//        soundPool.setPriority(soundId,100);

        audioManager = (AudioManager)
                this.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMicrophoneMute(false);
        audioManager.setSpeakerphoneOn(true);// 使用扬声器播放，即使已经插入耳机
        audioManager.setMode(AudioManager.STREAM_MUSIC);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);//控制声音的大小

        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    void palySound() {
        try {


           //soundPool.play(soundId, 1, 1, 100, -1, 1);

//
            player =  MediaPlayer.create(PlaySoundActivity.this,R.raw.videophone_come);
            player.setLooping(true);//循环播放

            player.start();
            count = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void stopSound(){
//        soundPool.stop(soundId);
//        soundPool.release();
        player.stop();
        player.release();
    }

    private void initView() {
        btn_play = (Button) findViewById(R.id.btn_play);
        activity_play_sound = (RelativeLayout) findViewById(R.id.activity_play_sound);

        btn_play.setOnClickListener(this);
        button12 = (Button) findViewById(R.id.button12);
        button12.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                palySound();
                break;
            case R.id.button12:
                stopSound();
                break;
        }
    }

    private int count ;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {

            case KeyEvent.KEYCODE_VOLUME_DOWN:
               // audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                LogUtil.e("sjm","-----------------"+count+" 实际音量大小:"+audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
                count--;

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, count,
                        AudioManager.FLAG_PLAY_SOUND);

                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                LogUtil.e("sjm","++++++++++++++++"+ count+" 实际音量大小:"+audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
                count++;
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, count,
                        AudioManager.FLAG_PLAY_SOUND);
                return true;
            case KeyEvent.KEYCODE_VOLUME_MUTE:
                LogUtil.e("sjm","MUTE");
                return true;

        }
        return super.onKeyDown(keyCode, event);
    }
}
