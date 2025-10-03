package com.cyberlink.you.transcode;

import android.annotation.TargetApi;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.CamcorderProfile;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.cyberlink.media.C1218e;
import com.cyberlink.you.Globals;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import p227w2.C6518a;

/* loaded from: classes.dex */
public final class TranscodeUtility {

    /* renamed from: a */
    public static final String[] f14357a;

    /* renamed from: b */
    public static final List<String> f14358b;

    public enum SUPPORT {
        TRUE,
        FALSE,
        EXCEPTION
    }

    static {
        String[] strArr = {MimeTypes.VIDEO_MP4, "video/x-matroska", MimeTypes.VIDEO_H263, "video/3gpp2", MimeTypes.VIDEO_MPEG};
        f14357a = strArr;
        f14358b = Arrays.asList(strArr);
    }

    /* renamed from: a */
    public static Pair<Integer, Integer> m16303a(String str, Uri uri) {
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            if (uri != null) {
                mediaMetadataRetriever.setDataSource(Globals.m7372O(), uri);
            } else if (!TextUtils.isEmpty(str)) {
                mediaMetadataRetriever.setDataSource(str);
            }
            Pair<Integer, Integer> pairM16304b = m16304b(mediaMetadataRetriever);
            if (((Integer) pairM16304b.first).intValue() <= 0 || ((Integer) pairM16304b.second).intValue() <= 0) {
                pairM16304b = m16305c(mediaMetadataRetriever);
            }
            mediaMetadataRetriever.release();
            return pairM16304b;
        } catch (Exception e9) {
            e9.printStackTrace();
            return Pair.create(0, 0);
        }
    }

    /* renamed from: b */
    public static Pair<Integer, Integer> m16304b(MediaMetadataRetriever mediaMetadataRetriever) {
        int iIntValue;
        int iIntValue2;
        int iIntValue3 = 0;
        try {
            iIntValue = Integer.valueOf(mediaMetadataRetriever.extractMetadata(24)).intValue();
        } catch (Exception e9) {
            Log.d("TranscodeUtility", Log.getStackTraceString(e9));
            iIntValue = 0;
        }
        try {
            iIntValue2 = Integer.valueOf(mediaMetadataRetriever.extractMetadata(18)).intValue();
        } catch (Exception e10) {
            Log.d("TranscodeUtility", Log.getStackTraceString(e10));
            iIntValue2 = 0;
        }
        try {
            iIntValue3 = Integer.valueOf(mediaMetadataRetriever.extractMetadata(19)).intValue();
        } catch (Exception e11) {
            Log.d("TranscodeUtility", Log.getStackTraceString(e11));
        }
        return (iIntValue == 90 || iIntValue == 270) ? Pair.create(Integer.valueOf(iIntValue3), Integer.valueOf(iIntValue2)) : Pair.create(Integer.valueOf(iIntValue2), Integer.valueOf(iIntValue3));
    }

    /* renamed from: c */
    public static Pair<Integer, Integer> m16305c(MediaMetadataRetriever mediaMetadataRetriever) {
        int width;
        int height;
        Bitmap frameAtTime;
        int i9 = 0;
        try {
            frameAtTime = mediaMetadataRetriever.getFrameAtTime();
        } catch (Exception e9) {
            e = e9;
            width = 0;
        }
        if (frameAtTime == null) {
            height = 0;
            return Pair.create(Integer.valueOf(i9), Integer.valueOf(height));
        }
        width = frameAtTime.getWidth();
        try {
            height = frameAtTime.getHeight();
        } catch (Exception e10) {
            e = e10;
            Log.d("TranscodeUtility", Log.getStackTraceString(e));
            height = 0;
            i9 = width;
            return Pair.create(Integer.valueOf(i9), Integer.valueOf(height));
        }
        i9 = width;
        return Pair.create(Integer.valueOf(i9), Integer.valueOf(height));
    }

    /* renamed from: d */
    public static boolean m16306d(Uri uri, String str) throws SecurityException, IllegalArgumentException {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            if (str != null) {
                mediaMetadataRetriever.setDataSource(str);
            } else {
                mediaMetadataRetriever.setDataSource(Globals.m7372O(), uri);
            }
            return mediaMetadataRetriever.extractMetadata(16) != null;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: e */
    public static boolean m16307e(Uri uri, String str) throws SecurityException, IllegalArgumentException {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            if (str != null) {
                mediaMetadataRetriever.setDataSource(str);
            } else {
                mediaMetadataRetriever.setDataSource(Globals.m7372O(), uri);
            }
            return mediaMetadataRetriever.extractMetadata(17) != null;
        } catch (Exception unused) {
            return false;
        }
    }

    @TargetApi(18)
    /* renamed from: f */
    public static SUPPORT m16308f(Uri uri, String str) throws IllegalStateException, IOException, IllegalArgumentException {
        SUPPORT support = SUPPORT.FALSE;
        try {
            MediaExtractor mediaExtractor = new MediaExtractor();
            try {
                if (uri != null) {
                    AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor = Globals.m7372O().getContentResolver().openAssetFileDescriptor(uri, "r");
                    if (assetFileDescriptorOpenAssetFileDescriptor != null) {
                        mediaExtractor.setDataSource(assetFileDescriptorOpenAssetFileDescriptor);
                    }
                } else {
                    mediaExtractor.setDataSource(str);
                }
            } catch (FileNotFoundException e9) {
                Log.d("TranscodeUtility", Log.getStackTraceString(e9));
                mediaExtractor.setDataSource(str);
            }
            int trackCount = mediaExtractor.getTrackCount();
            if (!m16307e(uri, str) || !m16306d(uri, str)) {
                return support;
            }
            boolean z8 = true;
            boolean z9 = true;
            boolean z10 = false;
            boolean z11 = false;
            for (int i9 = 0; i9 < trackCount; i9++) {
                MediaFormat trackFormat = mediaExtractor.getTrackFormat(i9);
                String string = trackFormat.containsKey("mime") ? trackFormat.getString("mime") : "";
                int integer = trackFormat.containsKey("channel-count") ? trackFormat.getInteger("channel-count") : 0;
                if (m16316n(trackFormat)) {
                    if (C1218e.m5382a(string) != null && z8) {
                        z10 = true;
                    }
                    z8 = false;
                } else if (m16309g(trackFormat)) {
                    if (C1218e.m5382a(string) != null && integer <= 2 && z9) {
                        z11 = true;
                    }
                    z9 = false;
                }
            }
            return (z10 && z11) ? SUPPORT.TRUE : support;
        } catch (Exception e10) {
            Log.d("TranscodeUtility", Log.getStackTraceString(e10));
            return SUPPORT.EXCEPTION;
        }
    }

    @TargetApi(18)
    /* renamed from: g */
    public static boolean m16309g(MediaFormat mediaFormat) {
        return mediaFormat.getString("mime").startsWith("audio/");
    }

    /* renamed from: h */
    public static boolean m16310h() {
        String str = Build.MANUFACTURER;
        Locale locale = Locale.US;
        String lowerCase = str.toLowerCase(locale);
        String lowerCase2 = Build.MODEL.toLowerCase(locale);
        Log.d("TranscodeUtility", "------------------- [isServerTranscodeWhiteList] manufacturerName = " + lowerCase);
        Log.d("TranscodeUtility", "------------------- [isServerTranscodeWhiteList] model = " + lowerCase2);
        if ((lowerCase.contains("samsung".toLowerCase(locale)) && (lowerCase2.toLowerCase(locale).contains("SM-G9208".toLowerCase(locale)) || lowerCase2.toLowerCase(locale).contains("SM-G930F".toLowerCase(locale)))) || ((lowerCase.contains("htc".toLowerCase(locale)) && lowerCase2.toLowerCase(locale).contains("HTC_E9".toLowerCase(locale))) || lowerCase2.toLowerCase(locale).contains("One E9+".toLowerCase(locale)) || ((lowerCase.contains("google".toLowerCase(locale)) && lowerCase2.toLowerCase(locale).contains("pixel".toLowerCase(locale))) || (lowerCase.contains("huawei".toLowerCase(locale)) && lowerCase2.toLowerCase(locale).contains("nexus 6p".toLowerCase(locale)))))) {
            Log.d("TranscodeUtility", "--------------- [isServerTranscodeWhiteList]  Is in server transcode white list.");
            return true;
        }
        Log.d("TranscodeUtility", "------------- [isServerTranscodeWhiteList] Isn't in server transcode white list.");
        return false;
    }

    /* renamed from: i */
    public static boolean m16311i() {
        return CamcorderProfile.hasProfile(6) || CamcorderProfile.hasProfile(1006) || CamcorderProfile.hasProfile(2004);
    }

    /* renamed from: j */
    public static boolean m16312j() {
        return CamcorderProfile.hasProfile(8) || CamcorderProfile.hasProfile(1008) || CamcorderProfile.hasProfile(2005);
    }

    /* renamed from: k */
    public static boolean m16313k(int i9, int i10) {
        boolean zM16312j = Math.max(i9, i10) >= 3840 ? m16312j() : Math.max(i9, i10) >= 1920 ? m16311i() : true;
        Log.d("TranscodeUtility", i9 + "x" + i10 + "isSupported:" + zM16312j);
        return zM16312j;
    }

    /* renamed from: l */
    public static SUPPORT m16314l(Uri uri, String str) {
        SUPPORT support = SUPPORT.FALSE;
        if (Globals.m7388i0().getApplicationContext() == null) {
            return support;
        }
        String strM16452J0 = CLUtility.m16452J0(str, uri);
        Log.d("TranscodeUtility", "MIME:" + strM16452J0 + ", " + uri);
        Iterator<String> it = f14358b.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (it.next().equals(strM16452J0)) {
                support = SUPPORT.TRUE;
                break;
            }
        }
        return (support == SUPPORT.TRUE && Globals.m7388i0().m7424F1()) ? m16308f(uri, str) : support;
    }

    /* renamed from: m */
    public static boolean m16315m(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(CLUtility.m16561m1(C6518a.f21932d));
        sb.append(File.separator);
        sb.append("U_");
        return str != null && str.contains(sb.toString());
    }

    @TargetApi(18)
    /* renamed from: n */
    public static boolean m16316n(MediaFormat mediaFormat) {
        return mediaFormat.getString("mime").startsWith("video/");
    }
}
