package com.google.zxing.client.android;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes2.dex */
final class BeepManager implements MediaPlayer.OnErrorListener, Closeable {
    private static final float BEEP_VOLUME = 0.1f;
    private static final String TAG = "BeepManager";
    private static final long VIBRATE_DURATION = 200;
    private final Activity activity;
    private MediaPlayer mediaPlayer = null;
    private boolean playBeep;
    private boolean vibrate;

    public BeepManager(Activity activity) {
        this.activity = activity;
        updatePrefs();
    }

    private MediaPlayer buildMediaPlayer(Context context) throws IllegalStateException, Resources.NotFoundException, IOException {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor assetFileDescriptorOpenRawResourceFd = context.getResources().openRawResourceFd(C4453R.raw.beep);
            try {
                mediaPlayer.setDataSource(assetFileDescriptorOpenRawResourceFd.getFileDescriptor(), assetFileDescriptorOpenRawResourceFd.getStartOffset(), assetFileDescriptorOpenRawResourceFd.getLength());
                assetFileDescriptorOpenRawResourceFd.close();
                mediaPlayer.setOnErrorListener(this);
                mediaPlayer.setAudioStreamType(3);
                mediaPlayer.setLooping(false);
                mediaPlayer.setVolume(0.1f, 0.1f);
                mediaPlayer.prepare();
                return mediaPlayer;
            } catch (Throwable th) {
                assetFileDescriptorOpenRawResourceFd.close();
                throw th;
            }
        } catch (IOException e9) {
            Log.w(TAG, e9);
            mediaPlayer.release();
            return null;
        }
    }

    private static boolean shouldBeep(SharedPreferences sharedPreferences, Context context) {
        boolean z8 = sharedPreferences.getBoolean(PreferencesActivity.KEY_PLAY_BEEP, true);
        if (!z8 || ((AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO)).getRingerMode() == 2) {
            return z8;
        }
        return false;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mediaPlayer = null;
        }
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public synchronized boolean onError(MediaPlayer mediaPlayer, int i9, int i10) {
        if (i9 == 100) {
            this.activity.finish();
        } else {
            close();
            updatePrefs();
        }
        return true;
    }

    public synchronized void playBeepSoundAndVibrate() {
        MediaPlayer mediaPlayer;
        if (this.playBeep && (mediaPlayer = this.mediaPlayer) != null) {
            mediaPlayer.start();
        }
        if (this.vibrate) {
            ((Vibrator) this.activity.getSystemService("vibrator")).vibrate(VIBRATE_DURATION);
        }
    }

    public synchronized void updatePrefs() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.activity);
        this.playBeep = shouldBeep(defaultSharedPreferences, this.activity);
        this.vibrate = defaultSharedPreferences.getBoolean(PreferencesActivity.KEY_VIBRATE, false);
        if (this.playBeep && this.mediaPlayer == null) {
            this.activity.setVolumeControlStream(3);
            this.mediaPlayer = buildMediaPlayer(this.activity);
        }
    }
}
