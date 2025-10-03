package com.cyberlink.you.transcode;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaMetadataRetriever;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.share.ShareMediaActivity;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.pages.photoimport.VideoItem;
import com.cyberlink.you.transcode.TranscodeUtility;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.common.net.HttpHeaders;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.p159io.FilenameUtils;
import p025c4.C0726d;
import p106j4.C5095a;
import p106j4.InterfaceC5096b;
import p106j4.InterfaceC5101g;
import p116k4.C5154j;
import p116k4.C5187v0;
import p218v2.C6462j;
import p218v2.DialogC6459g;
import p236x2.C6566a;

/* renamed from: com.cyberlink.you.transcode.a */
/* loaded from: classes.dex */
public class C3114a {

    /* renamed from: e */
    public static final String f14363e = "a";

    /* renamed from: a */
    public Activity f14364a;

    /* renamed from: b */
    public InterfaceC5096b f14365b = null;

    /* renamed from: c */
    public d f14366c;

    /* renamed from: d */
    public Runnable f14367d;

    /* renamed from: com.cyberlink.you.transcode.a$a */
    public class a implements InterfaceC5101g {

        /* renamed from: a */
        public final /* synthetic */ DialogC6459g f14368a;

        /* renamed from: b */
        public final /* synthetic */ File f14369b;

        /* renamed from: c */
        public final /* synthetic */ String f14370c;

        /* renamed from: d */
        public final /* synthetic */ Uri f14371d;

        /* renamed from: e */
        public final /* synthetic */ String f14372e;

        public a(DialogC6459g dialogC6459g, File file, String str, Uri uri, String str2) {
            this.f14368a = dialogC6459g;
            this.f14369b = file;
            this.f14370c = str;
            this.f14371d = uri;
            this.f14372e = str2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m16341d(int i9, DialogC6459g dialogC6459g) {
            dialogC6459g.m24776s(C3114a.this.f14364a.getString(R.string.processing) + " (" + i9 + "%)");
            dialogC6459g.m24772o(i9);
        }

        @Override // p106j4.InterfaceC5101g
        /* renamed from: a */
        public void mo16342a(final int i9) {
            Activity activity = C3114a.this.f14364a;
            final DialogC6459g dialogC6459g = this.f14368a;
            activity.runOnUiThread(new Runnable() { // from class: j4.f
                @Override // java.lang.Runnable
                public final void run() {
                    this.f17560b.m16341d(i9, dialogC6459g);
                }
            });
        }

        @Override // p106j4.InterfaceC5101g
        /* renamed from: b */
        public void mo16343b() {
            if (C3114a.this.f14365b != null) {
                C3114a.this.f14365b.release();
                C3114a.this.f14365b = null;
            }
            C3114a.this.f14365b = null;
            if (this.f14369b.exists()) {
                CLUtility.m16447I(this.f14369b);
            }
            if (!C3114a.this.f14364a.isFinishing() && !C3114a.this.f14364a.isDestroyed() && this.f14368a.isShowing()) {
                this.f14368a.dismiss();
            }
            CLUtility.m16455K(this.f14370c, this.f14371d);
            C3114a.this.m16335s(this.f14372e, null, false);
        }

        @Override // p106j4.InterfaceC5101g
        public void onError(String str) {
            Log.d(C3114a.f14363e, "onError() called with: errorDetail = [" + str + "]");
            if (C3114a.this.f14365b != null) {
                C3114a.this.f14365b.release();
                C3114a.this.f14365b = null;
            }
            if (this.f14369b.exists()) {
                CLUtility.m16447I(this.f14369b);
            }
            if (C3114a.this.f14364a != null) {
                C5187v0.m20267c(R.string.error_transcode_fail);
                if (C3114a.this.f14364a.isFinishing() || C3114a.this.f14364a.isDestroyed() || !this.f14368a.isShowing()) {
                    return;
                }
                this.f14368a.dismiss();
            }
        }
    }

    /* renamed from: com.cyberlink.you.transcode.a$b */
    public class b extends AsyncTask<Void, Void, Uri> {

        /* renamed from: a */
        public final /* synthetic */ String f14374a;

        /* renamed from: b */
        public final /* synthetic */ boolean f14375b;

        public b(String str, boolean z8) {
            this.f14374a = str;
            this.f14375b = z8;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Uri doInBackground(Void... voidArr) {
            if (TextUtils.isEmpty(this.f14374a)) {
                return null;
            }
            return ShareMediaActivity.m12674g1(this.f14374a);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Uri uri) {
            C3114a.this.m16331l(this.f14374a, uri, this.f14375b);
        }
    }

    /* renamed from: com.cyberlink.you.transcode.a$c */
    public class c extends AsyncTask<Void, Void, VideoItem> {

        /* renamed from: a */
        public final /* synthetic */ Uri f14377a;

        /* renamed from: b */
        public final /* synthetic */ String f14378b;

        /* renamed from: c */
        public final /* synthetic */ boolean f14379c;

        public c(Uri uri, String str, boolean z8) {
            this.f14377a = uri;
            this.f14378b = str;
            this.f14379c = z8;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public VideoItem doInBackground(Void... voidArr) throws SecurityException, IOException, IllegalArgumentException {
            C0726d c0726d = new C0726d(C3114a.this.f14364a);
            VideoItem videoItemM3573l = c0726d.m3573l(this.f14377a);
            if (videoItemM3573l == null && !TextUtils.isEmpty(this.f14378b)) {
                File file = new File(this.f14378b);
                if (file.exists()) {
                    videoItemM3573l = new VideoItem("", -1L, this.f14378b, null, "", file.getName(), CLUtility.m16440G0(this.f14378b, null), this.f14379c, 0, 0);
                }
            }
            if (TextUtils.isEmpty(videoItemM3573l.m16218g())) {
                videoItemM3573l.m16225n(this.f14378b);
            }
            String strM3568g = c0726d.m3568g(videoItemM3573l.m16217f());
            if (!CLUtility.m16613z1(strM3568g, null)) {
                strM3568g = CLUtility.m16427D(videoItemM3573l.m16218g(), CLUtility.m16510Z1(videoItemM3573l.m16219h()));
            }
            if (!CLUtility.m16613z1(strM3568g, null)) {
                ULogUtility.m16670f(C3114a.f14363e, "[getVideoItem] thumbnail not exist or can not read at:" + strM3568g);
            }
            videoItemM3573l.m16224m(strM3568g);
            Pair<Integer, Integer> pairM16303a = TranscodeUtility.m16303a(this.f14378b, this.f14377a);
            if (((Integer) pairM16303a.first).intValue() != 0 && ((Integer) pairM16303a.second).intValue() != 0) {
                videoItemM3573l.m16226o(((Integer) pairM16303a.first).intValue());
                videoItemM3573l.m16222k(((Integer) pairM16303a.second).intValue());
            }
            videoItemM3573l.m16223l(this.f14379c);
            if (TextUtils.isEmpty(videoItemM3573l.m16212a())) {
                videoItemM3573l.m16221j(CLUtility.m16552k0(this.f14378b, this.f14377a));
            }
            return videoItemM3573l;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(VideoItem videoItem) {
            HashMap map = new HashMap();
            map.put("videoItem", videoItem);
            C3114a.this.f14366c.mo12439a(map);
        }
    }

    /* renamed from: com.cyberlink.you.transcode.a$d */
    public interface d {
        /* renamed from: a */
        void mo12439a(Map<String, Object> map);
    }

    public C3114a(Activity activity, d dVar) {
        this.f14364a = activity;
        this.f14366c = dVar;
    }

    /* renamed from: j */
    public static Pair<Integer, Integer> m16326j(int i9, int i10, int i11) {
        if (i9 > i10) {
            return Pair.create(Integer.valueOf(((i9 * i11) / i10) & (-16)), Integer.valueOf(i11));
        }
        return Pair.create(Integer.valueOf(i11), Integer.valueOf(((i10 * i11) / i9) & (-16)));
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x007a -> B:49:0x007d). Please report as a decompilation issue!!! */
    /* renamed from: k */
    public static Pair<Integer, Integer> m16327k(String str, Uri uri) throws Throwable {
        MediaMetadataRetriever mediaMetadataRetriever;
        int iIntValue;
        int iIntValue2;
        MediaMetadataRetriever mediaMetadataRetriever2 = null;
        try {
            try {
                try {
                    mediaMetadataRetriever = new MediaMetadataRetriever();
                } catch (IOException e9) {
                    e9.printStackTrace();
                }
            } catch (Exception e10) {
                e = e10;
            }
            try {
                try {
                    if (uri != null) {
                        mediaMetadataRetriever.setDataSource(Globals.m7372O(), uri);
                    } else {
                        mediaMetadataRetriever.setDataSource(str);
                    }
                    try {
                        iIntValue = Integer.valueOf(mediaMetadataRetriever.extractMetadata(18)).intValue();
                    } catch (Exception e11) {
                        Log.d(f14363e, Log.getStackTraceString(e11));
                        iIntValue = 0;
                    }
                    try {
                        iIntValue2 = Integer.valueOf(mediaMetadataRetriever.extractMetadata(19)).intValue();
                    } catch (Exception e12) {
                        Log.d(f14363e, Log.getStackTraceString(e12));
                        iIntValue2 = 0;
                    }
                } catch (Exception e13) {
                    e = e13;
                    mediaMetadataRetriever2 = mediaMetadataRetriever;
                    C5154j.m20076j(e);
                    if (mediaMetadataRetriever2 != null) {
                        mediaMetadataRetriever2.release();
                    }
                    return Pair.create(0, 0);
                }
                if (iIntValue == 0 || iIntValue2 == 0) {
                    mediaMetadataRetriever.release();
                    return Pair.create(0, 0);
                }
                Pair<Integer, Integer> pairM16326j = m16326j(iIntValue, iIntValue2, Math.min(Math.min(iIntValue, iIntValue2), 360));
                try {
                    mediaMetadataRetriever.release();
                } catch (IOException e14) {
                    e14.printStackTrace();
                }
                return pairM16326j;
            } catch (Throwable th) {
                th = th;
                mediaMetadataRetriever2 = mediaMetadataRetriever;
                if (mediaMetadataRetriever2 != null) {
                    try {
                        mediaMetadataRetriever2.release();
                    } catch (IOException e15) {
                        e15.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m */
    public /* synthetic */ void m16328m(String str, boolean z8, String str2, Uri uri) {
        Log.d(f14363e, "[onScanCompleted]:" + str2);
        new b(str, z8).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n */
    public /* synthetic */ void m16329n(File file, DialogInterface dialogInterface) {
        InterfaceC5096b interfaceC5096b = this.f14365b;
        if (interfaceC5096b != null) {
            interfaceC5096b.cancel();
            this.f14365b.release();
            this.f14365b = null;
        }
        if (file.exists()) {
            CLUtility.m16447I(file);
        }
        C5187v0.m20267c(R.string.error_transcode_fail);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o */
    public /* synthetic */ void m16330o(String str, Uri uri, String str2, DialogC6459g dialogC6459g, File file) throws Throwable {
        Thread.currentThread().setName("TranscodeVideo");
        Pair<Integer, Integer> pairM16327k = m16327k(str, uri);
        C5095a c5095a = new C5095a(((Integer) pairM16327k.first).intValue(), ((Integer) pairM16327k.second).intValue());
        this.f14365b = c5095a;
        c5095a.mo19949b(str, uri, Globals.m7372O(), str2, new a(dialogC6459g, file, str, uri, str2));
    }

    /* renamed from: l */
    public final void m16331l(String str, Uri uri, boolean z8) {
        new c(uri, str, z8).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* renamed from: p */
    public void m16332p() {
        InterfaceC5096b interfaceC5096b = this.f14365b;
        if (interfaceC5096b != null) {
            interfaceC5096b.cancel();
            this.f14365b.release();
        }
    }

    /* renamed from: q */
    public void m16333q() {
        InterfaceC5096b interfaceC5096b = this.f14365b;
        if (interfaceC5096b != null) {
            interfaceC5096b.pause();
        }
    }

    /* renamed from: r */
    public void m16334r() {
        InterfaceC5096b interfaceC5096b = this.f14365b;
        if (interfaceC5096b != null) {
            interfaceC5096b.mo19948a();
        }
    }

    /* renamed from: s */
    public void m16335s(final String str, Uri uri, final boolean z8) {
        if (z8) {
            C6566a.m25161t(Build.MANUFACTURER + "," + Build.MODEL);
            C6566a.m25163v(HttpHeaders.SERVER);
        }
        if (CLUtility.m16597v1(uri)) {
            m16331l(str, uri, z8);
        } else {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            MediaScannerConnection.scanFile(this.f14364a, new String[]{str}, null, new MediaScannerConnection.OnScanCompletedListener() { // from class: j4.c
                @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                public final void onScanCompleted(String str2, Uri uri2) {
                    this.f17549a.m16328m(str, z8, str2, uri2);
                }
            });
        }
    }

    /* renamed from: t */
    public void m16336t(Uri uri) {
        m16339w(uri, false);
    }

    /* renamed from: u */
    public void m16337u(Uri uri, String str) {
        m16338v(uri, str, false);
    }

    /* renamed from: v */
    public void m16338v(final Uri uri, final String str, boolean z8) throws SecurityException, IOException, IllegalArgumentException {
        TranscodeUtility.SUPPORT supportM16314l = TranscodeUtility.m16314l(uri, str);
        if (supportM16314l == TranscodeUtility.SUPPORT.EXCEPTION) {
            C5187v0.m20267c(R.string.error_media_type_unsupported);
            return;
        }
        long jM16440G0 = CLUtility.m16440G0(str, uri);
        if (jM16440G0 > 600000) {
            String str2 = String.format(this.f14364a.getResources().getString(R.string.select_videos_too_long), String.valueOf(10L));
            AlertDialog.Builder builderM16382a = C3123g.m16382a(this.f14364a);
            builderM16382a.setMessage(str2);
            builderM16382a.setPositiveButton(this.f14364a.getString(R.string.ok), (DialogInterface.OnClickListener) null);
            builderM16382a.create().show();
            return;
        }
        if (jM16440G0 < 1000) {
            C5187v0.m20267c(R.string.videos_too_short);
            return;
        }
        boolean zM16315m = TranscodeUtility.m16315m(str);
        TranscodeUtility.m16310h();
        String str3 = f14363e;
        ULogUtility.m16680p(str3, "isEnableServerTransCode() : " + Globals.m7388i0().m7419E1());
        ULogUtility.m16680p(str3, "isFromForumOrPoll : " + z8);
        ULogUtility.m16680p(str3, "isSupportedVideo : " + supportM16314l);
        ULogUtility.m16680p(str3, "isURecordedVideo : " + zM16315m);
        ULogUtility.m16680p(str3, "isEnabledAPTranscode : " + Globals.m7388i0().m7424F1());
        ULogUtility.m16680p(str3, "isNeedAPTranscode : true");
        Pair<Integer, Integer> pairM16303a = TranscodeUtility.m16303a(str, uri);
        if (((Integer) pairM16303a.first).intValue() == 0 || ((Integer) pairM16303a.second).intValue() == 0) {
            C5187v0.m20267c(R.string.error_media_type_unsupported);
            return;
        }
        if (!TranscodeUtility.m16313k(((Integer) pairM16303a.first).intValue(), ((Integer) pairM16303a.second).intValue())) {
            Log.d(str3, "Not AP transcode");
            if (zM16315m) {
                C6566a.m25163v("None");
            }
            m16335s(str, uri, !zM16315m);
            return;
        }
        Log.d(str3, "AP transcode");
        C6566a.m25163v("AP");
        final DialogC6459g dialogC6459g = new DialogC6459g(this.f14364a, R.style.FriendSelectorDialog);
        String strM16435F = CLUtility.m16435F(str, uri, CLUtility.m16443H() + "_" + FilenameUtils.getBaseName(CLUtility.m16552k0(str, uri)), 1);
        final File file = new File(strM16435F);
        dialogC6459g.m24765g(strM16435F);
        dialogC6459g.getWindow().addFlags(128);
        dialogC6459g.m24766h(true);
        dialogC6459g.m24772o(0);
        dialogC6459g.setCancelable(true);
        dialogC6459g.setCanceledOnTouchOutside(false);
        dialogC6459g.m24770m(new C6462j(dialogC6459g));
        dialogC6459g.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: j4.d
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                this.f17552b.m16329n(file, dialogInterface);
            }
        });
        dialogC6459g.show();
        final String strM16557l1 = CLUtility.m16557l1(new File(strM16435F).getName());
        this.f14367d = new Runnable() { // from class: j4.e
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                this.f17554b.m16330o(str, uri, strM16557l1, dialogC6459g, file);
            }
        };
        new Thread(this.f14367d).start();
    }

    /* renamed from: w */
    public void m16339w(Uri uri, boolean z8) throws SecurityException, IOException, IllegalArgumentException {
        m16338v(uri, CLUtility.m16576q0(this.f14364a, uri), z8);
    }
}
