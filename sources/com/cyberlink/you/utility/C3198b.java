package com.cyberlink.you.utility;

import android.net.Uri;
import com.cyberlink.clrtc.NileNetwork;
import com.cyberlink.meeting.clrtc.MeetingManager;
import com.cyberlink.p033u.glide.MediaLoader;
import com.cyberlink.you.Globals;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.pages.photoimport.FileItem;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.pages.photoimport.VideoItem;
import com.cyberlink.you.utility.UploadMediaHelper;
import com.cyberlink.you.utility.UploadUtils;
import com.google.android.gms.plus.PlusShare;
import java.io.IOException;

/* renamed from: com.cyberlink.you.utility.b */
/* loaded from: classes.dex */
public class C3198b extends UploadMediaHelper {

    /* renamed from: d1 */
    public String f14763d1;

    /* renamed from: e1 */
    public NileNetwork f14764e1;

    /* renamed from: f1 */
    public final Object f14765f1;

    /* renamed from: g1 */
    public UploadUtils.UploadResultType f14766g1;

    /* renamed from: h1 */
    public String f14767h1;

    /* renamed from: i1 */
    public Uri f14768i1;

    /* renamed from: j1 */
    public UploadMediaHelper.MediaType f14769j1;

    /* renamed from: k1 */
    public UploadMultipleChatMediaHelperQueue f14770k1;

    /* renamed from: l1 */
    public long f14771l1;

    /* renamed from: com.cyberlink.you.utility.b$a */
    public class a extends Thread {
        public a() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws InterruptedException, IOException {
            ULogUtility.m16670f("Upload Performance", "U.2 uploadPhoto_step1 ==> buildConnection(); small image ======== start");
            C3198b c3198b = C3198b.this;
            boolean zM16863x0 = c3198b.m16863x0(c3198b.f14573g, UploadMediaHelper.MediaType.SMALL_IMG);
            StringBuilder sb = new StringBuilder();
            sb.append("U.2 uploadPhoto_step1 ==> buildConnection(); small image ======== end == ");
            sb.append(zM16863x0 ? "OK" : "NG");
            ULogUtility.m16670f("Upload Performance", sb.toString());
        }
    }

    /* renamed from: com.cyberlink.you.utility.b$b */
    public class b extends Thread {
        public b() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws InterruptedException, IOException {
            ULogUtility.m16670f("Upload Performance", "U.2 uploadPhoto_step1 ==> buildConnection(); big image ======== start");
            C3198b c3198b = C3198b.this;
            boolean zM16863x0 = c3198b.m16863x0(c3198b.f14575h, UploadMediaHelper.MediaType.BIG_IMG);
            StringBuilder sb = new StringBuilder();
            sb.append("U.2 uploadPhoto_step1 ==> buildConnection(); big image ======== end == ");
            sb.append(zM16863x0 ? "OK" : "NG");
            ULogUtility.m16670f("Upload Performance", sb.toString());
        }
    }

    /* renamed from: com.cyberlink.you.utility.b$c */
    public class c extends Thread {
        public c() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            ULogUtility.m16670f("Upload Performance", "U.2 uploadFile_step1 ==> buildConnection(); file ======== start");
            UploadUtils.C3196b c3196b = C3198b.this.f14581k;
            boolean zM16863x0 = true;
            if (!c3196b.f14733a) {
                UploadUtils.C3196b.a aVarM16968c = c3196b.m16968c(1);
                zM16863x0 = aVarM16968c != null ? C3198b.this.m16863x0(aVarM16968c.f14739c, UploadMediaHelper.MediaType.FILE) : false;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("U.2 uploadFile_step1 ==> buildConnection(); file ======== end == ");
            sb.append(zM16863x0 ? "OK" : "NG");
            ULogUtility.m16670f("Upload Performance", sb.toString());
        }
    }

    /* renamed from: com.cyberlink.you.utility.b$d */
    public class d extends Thread {
        public d() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws InterruptedException, IOException {
            ULogUtility.m16670f("Upload Performance", "U.2 uploadVideo_step1 ==> buildConnection(); video thumbnail ======== start");
            C3198b c3198b = C3198b.this;
            boolean zM16863x0 = c3198b.m16863x0(c3198b.f14573g, UploadMediaHelper.MediaType.VIDEO_IMG);
            StringBuilder sb = new StringBuilder();
            sb.append("U.2 uploadVideo_step1 ==> buildConnection(); video thumbnail ======== end == ");
            sb.append(zM16863x0 ? "OK" : "NG");
            ULogUtility.m16670f("Upload Performance", sb.toString());
        }
    }

    /* renamed from: com.cyberlink.you.utility.b$e */
    public class e extends Thread {
        public e() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            ULogUtility.m16670f("Upload Performance", "U.2 uploadVideo_step1 ==> buildConnection(); video ======== start");
            UploadUtils.C3196b.a aVarM16968c = C3198b.this.f14579j.m16968c(1);
            boolean zM16863x0 = aVarM16968c != null ? C3198b.this.m16863x0(aVarM16968c.f14739c, UploadMediaHelper.MediaType.VIDEO) : false;
            StringBuilder sb = new StringBuilder();
            sb.append("U.2 uploadVideo_step1 ==> buildConnection(); video ======== end == ");
            sb.append(zM16863x0 ? "OK" : "NG");
            ULogUtility.m16670f("Upload Performance", sb.toString());
        }
    }

    /* renamed from: com.cyberlink.you.utility.b$f */
    public static /* synthetic */ class f {

        /* renamed from: a */
        public static final /* synthetic */ int[] f14777a;

        static {
            int[] iArr = new int[UploadMediaHelper.MediaType.values().length];
            f14777a = iArr;
            try {
                iArr[UploadMediaHelper.MediaType.BIG_IMG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f14777a[UploadMediaHelper.MediaType.FILE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f14777a[UploadMediaHelper.MediaType.VIDEO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public C3198b(String str, ImageItem imageItem, String str2) {
        this.f14763d1 = "";
        this.f14765f1 = new Object();
        this.f14766g1 = UploadUtils.UploadResultType.STEP_1_FAIL;
        this.f14767h1 = null;
        this.f14768i1 = null;
        this.f14769j1 = null;
        this.f14771l1 = -1L;
        this.f14531M = str;
        this.f14611z = imageItem;
        if (imageItem != null) {
            this.f14588n0 = true;
            this.f14609y = imageItem.m16141n();
            String strM16145r = this.f14611z.m16145r();
            this.f14767h1 = strM16145r;
            this.f14768i1 = CLUtility.m16510Z1(strM16145r);
        }
        this.f14763d1 = str2;
        this.f14764e1 = MeetingManager.m5616i(str2);
    }

    @Override // com.cyberlink.you.utility.UploadMediaHelper
    /* renamed from: A0 */
    public long mo16785A0(UploadMediaHelper.MediaType mediaType, UploadUtils.C3195a c3195a) {
        return 1L;
    }

    @Override // com.cyberlink.you.utility.UploadMediaHelper
    /* renamed from: D0 */
    public void mo16791D0(String str) {
        m17012r2();
        this.f14560a0 = UploadUtils.UploadResultType.STEP_2_SUCCESS;
        this.f14602u0 = UploadMediaHelper.FailReason.FAIL_NONE;
    }

    @Override // com.cyberlink.you.utility.UploadMediaHelper
    /* renamed from: E0 */
    public void mo16793E0(String str, String str2) {
        this.f14553X = UploadUtils.UploadResultType.STEP_2_SUCCESS;
    }

    @Override // com.cyberlink.you.utility.UploadMediaHelper
    /* renamed from: F0 */
    public void mo16795F0(String str, String str2) {
        m17012r2();
        this.f14557Z = this.f14766g1;
        this.f14602u0 = UploadMediaHelper.FailReason.FAIL_NONE;
    }

    @Override // com.cyberlink.you.utility.UploadMediaHelper
    /* renamed from: b2 */
    public void mo16835b2() {
        m17012r2();
        this.f14553X = this.f14766g1;
        this.f14602u0 = UploadMediaHelper.FailReason.FAIL_NONE;
    }

    @Override // com.cyberlink.you.utility.UploadMediaHelper
    /* renamed from: d2 */
    public void mo16839d2() {
        this.f14560a0 = UploadUtils.UploadResultType.STEP_1_FAIL;
        m17013s2();
        this.f14560a0 = this.f14766g1;
    }

    @Override // com.cyberlink.you.utility.UploadMediaHelper
    /* renamed from: e2 */
    public void mo16841e2() {
        this.f14553X = UploadUtils.UploadResultType.STEP_1_FAIL;
        m17013s2();
        this.f14553X = this.f14766g1;
    }

    @Override // com.cyberlink.you.utility.UploadMediaHelper
    /* renamed from: h2 */
    public void mo16847h2() {
        this.f14557Z = UploadUtils.UploadResultType.STEP_1_FAIL;
        m17013s2();
        this.f14557Z = this.f14766g1;
    }

    /* renamed from: n2 */
    public long m17008n2() {
        if (this.f14771l1 == -1) {
            this.f14771l1 = System.currentTimeMillis();
        }
        return this.f14771l1;
    }

    /* renamed from: o2 */
    public void m17009o2(int i9) {
        ULogUtility.m16670f("UploadMeetingMedia", "onFileUploadCanceled! resultCode: " + i9);
    }

    /* renamed from: p2 */
    public void m17010p2(int i9, int i10, String str, int i11, String str2) {
        ULogUtility.m16670f("UploadMeetingMedia", "onFileUploadCompleted! resultCode: " + i9);
        if (i9 == 0) {
            this.f14766g1 = UploadUtils.UploadResultType.STEP_3_SUCCESS;
            int i12 = f.f14777a[this.f14769j1.ordinal()];
            if (i12 != 1) {
                if (i12 != 2) {
                    if (i12 == 3 && this.f14569e != null) {
                        C2973l0.a aVar = new C2973l0.a();
                        aVar.f13200d = str;
                        String str3 = this.f14572f0;
                        if (str3 == null) {
                            str3 = "0";
                        }
                        aVar.f13203g = str3;
                        C2973l0.a aVar2 = new C2973l0.a();
                        aVar2.f13200d = str2;
                        this.f14543S = new C2973l0(-1L, "Meeting:" + m16806M0(), m17008n2(), this.f14569e.f14723b, "", "Video", -1L, "", aVar2, aVar, this.f14515E.m16220i(), this.f14515E.m16214c(), 0, 0, 0, 0, 0L);
                        C2950b0.m14914m().m14712i(this.f14543S);
                    }
                } else if (this.f14571f != null) {
                    C2973l0.a aVar3 = new C2973l0.a();
                    aVar3.f13200d = str;
                    aVar3.f13197a = this.f14571f.f14730i;
                    C2973l0.a aVar4 = new C2973l0.a();
                    aVar4.f13200d = str2;
                    String str4 = "Meeting:" + m16806M0();
                    long jM17008n2 = m17008n2();
                    UploadUtils.C3195a c3195a = this.f14571f;
                    this.f14545T = new C2973l0(-1L, str4, jM17008n2, c3195a.f14723b, "", "File", -1L, "", aVar4, aVar3, c3195a.f14724c, c3195a.f14725d, 0, 0, 0, 0, 0L);
                    C2950b0.m14914m().m14712i(this.f14545T);
                }
            } else if (this.f14565c != null) {
                C2973l0.a aVar5 = new C2973l0.a();
                aVar5.f13200d = str;
                C2973l0.a aVar6 = new C2973l0.a();
                aVar6.f13200d = str2;
                String str5 = "Meeting:" + m16806M0();
                long jM17008n22 = m17008n2();
                UploadUtils.C3195a c3195a2 = this.f14565c;
                this.f14539Q = new C2973l0(-1L, str5, jM17008n22, c3195a2.f14723b, "", "Photo", -1L, "", aVar6, aVar5, c3195a2.f14724c, c3195a2.f14725d, 0, 0, 0, 0, 0L);
                MediaLoader.m7150i(Globals.m7372O(), this.f14539Q, false);
                C2950b0.m14914m().m14712i(this.f14539Q);
            }
        }
        synchronized (this.f14765f1) {
            this.f14765f1.notify();
        }
    }

    /* renamed from: q2 */
    public void m17011q2(int i9, String[] strArr, String[] strArr2, String str) throws NumberFormatException {
        if (i9 == 0) {
            ULogUtility.m16670f("UploadMeetingMedia", "[onFileUploadPrepared]: OK!!!");
            this.f14766g1 = UploadUtils.UploadResultType.STEP_1_SUCCESS;
            int i10 = f.f14777a[this.f14769j1.ordinal()];
            if (i10 == 1) {
                this.f14575h = strArr[0];
                this.f14573g = strArr2[0];
                new a().start();
                new b().start();
            } else if (i10 == 2) {
                try {
                    this.f14581k.m16966a(UploadMediaHelper.MediaType.FILE, Integer.parseInt("1"), strArr[0], this.f14571f.f14730i, 0L, false);
                } catch (Exception e9) {
                    UploadUtils.m16964k("UploadMeetingMedia", "[parseMediaUploadFile]", e9);
                }
                new c().start();
            } else if (i10 == 3) {
                this.f14573g = strArr2[0];
                try {
                    this.f14579j.m16966a(UploadMediaHelper.MediaType.VIDEO, Integer.parseInt("1"), strArr[0], this.f14569e.f14730i, 0L, false);
                } catch (Exception e10) {
                    UploadUtils.m16964k("UploadMeetingMedia", "[parseMediaUploadVideo]", e10);
                }
                new d().start();
                new e().start();
            }
        } else if (i9 != 4) {
            ULogUtility.m16670f("UploadMeetingMedia", "onFileUploadPrepared resultCode = " + i9);
        } else {
            this.f14764e1.m4956o3();
        }
        synchronized (this.f14765f1) {
            this.f14765f1.notify();
        }
        ULogUtility.m16670f("Upload Performance", "P3 SendRequest(photo) ==== end");
        ULogUtility.m16670f("UploadMeetingMedia", "[uploadPhoto_step1]: ==== (Photo) STEP-1 done");
        ULogUtility.m16670f("Upload Performance", "P1.1 UploadMediaHelper.uploadPhoto_step1(); ======== end");
    }

    /* renamed from: r2 */
    public final void m17012r2() {
        ULogUtility.m16670f("UploadMeetingMedia", "[uploadComplete]");
        this.f14766g1 = UploadUtils.UploadResultType.STEP_2_SMALL_FAIL;
        this.f14764e1.m4958p3();
        try {
            synchronized (this.f14765f1) {
                this.f14765f1.wait();
            }
        } catch (InterruptedException e9) {
            Thread.currentThread().interrupt();
            UploadUtils.m16964k("UploadMeetingMedia", "[uploadComplete]", e9);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00be A[PHI: r3
      0x00be: PHI (r3v3 long) = (r3v2 long), (r3v5 long), (r3v7 long) binds: [B:52:0x00d7, B:49:0x00ca, B:46:0x00bc] A[DONT_GENERATE, DONT_INLINE]] */
    /* renamed from: s2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m17013s2() {
        String strM16147t;
        long jM16572p0;
        ULogUtility.m16670f("UploadMeetingMedia", "upload initialize");
        ULogUtility.m16670f("UploadMeetingMedia", "[uploadPhoto_step1]: Reason:" + this.f14602u0 + " upload status:" + this.f14766g1);
        this.f14770k1.m16936b0(this);
        this.f14766g1 = UploadUtils.UploadResultType.STEP_1_FAIL;
        if (this.f14604v0) {
            return;
        }
        String str = null;
        UploadMediaHelper.MediaType mediaType = this.f14588n0 ? UploadMediaHelper.MediaType.BIG_IMG : this.f14592p0 ? UploadMediaHelper.MediaType.VIDEO : this.f14600t0 ? UploadMediaHelper.MediaType.FILE : null;
        this.f14769j1 = mediaType;
        if (mediaType == null) {
            ULogUtility.m16670f("UploadMeetingMedia", "[upload initialize]: ==== STEP-1 done ===== no valid media type to upload");
            return;
        }
        int[] iArr = f.f14777a;
        int i9 = iArr[mediaType.ordinal()];
        if (i9 == 1) {
            ImageItem imageItem = this.f14611z;
            strM16147t = imageItem != null ? imageItem.m16147t() : "";
        } else {
            if (i9 != 2) {
                if (i9 == 3) {
                    VideoItem videoItem = this.f14515E;
                    strM16147t = videoItem != null ? videoItem.m16212a() : "";
                }
                if (str != null) {
                    ULogUtility.m16670f("UploadMeetingMedia", "[upload initialize]: ==== STEP-1 done ===== cannot get media file name");
                    return;
                }
                int i10 = iArr[this.f14769j1.ordinal()];
                int i11 = Integer.MAX_VALUE;
                if (i10 == 1) {
                    jM16572p0 = CLUtility.m16572p0(this.f14767h1, this.f14768i1);
                    if (jM16572p0 <= 2147483647L) {
                    }
                } else if (i10 == 2) {
                    jM16572p0 = CLUtility.m16572p0(this.f14527K, this.f14529L);
                    if (jM16572p0 <= 2147483647L) {
                    }
                } else if (i10 != 3) {
                    i11 = -1;
                } else {
                    jM16572p0 = CLUtility.m16572p0(this.f14523I, this.f14525J);
                    if (jM16572p0 <= 2147483647L) {
                        i11 = (int) jM16572p0;
                    }
                }
                if (i11 == -1) {
                    ULogUtility.m16670f("UploadMeetingMedia", "[upload initialize]: ==== STEP-1 done ===== cannot get file size");
                    return;
                }
                ULogUtility.m16670f("UploadMeetingMedia", "Prepare file upload ==== start");
                this.f14764e1.m4908L7(str, "JPG", i11, 1);
                try {
                    synchronized (this.f14765f1) {
                        this.f14765f1.wait();
                    }
                    return;
                } catch (InterruptedException e9) {
                    UploadUtils.m16964k("UploadMeetingMedia", "[upload initialize]", e9);
                    return;
                }
            }
            FileItem fileItem = this.f14517F;
            strM16147t = fileItem != null ? fileItem.m16114a() : "";
        }
        str = strM16147t;
        if (str != null) {
        }
    }

    public C3198b(String str, ImageItem imageItem, String str2, String str3, String str4, VideoItem videoItem, String str5, FileItem fileItem, MessageObj messageObj, String str6, UploadMultipleChatMediaHelperQueue uploadMultipleChatMediaHelperQueue) {
        String strM14747I;
        this(str, imageItem, str6);
        this.f14533N = str2;
        this.f14535O = str4;
        this.f14511C = str3;
        this.f14515E = videoItem;
        this.f14537P = str5;
        this.f14517F = fileItem;
        this.f14563b0 = messageObj;
        this.f14584l0 = str6;
        this.f14770k1 = uploadMultipleChatMediaHelperQueue;
        if (CLUtility.m16613z1(str3, null)) {
            this.f14590o0 = true;
        }
        VideoItem videoItem2 = this.f14515E;
        if (videoItem2 != null) {
            this.f14523I = videoItem2.m16218g();
            Uri uriM16510Z1 = CLUtility.m16510Z1(this.f14515E.m16219h());
            this.f14525J = uriM16510Z1;
            if (CLUtility.m16613z1(this.f14523I, uriM16510Z1)) {
                this.f14592p0 = true;
                this.f14579j = new UploadUtils.C3196b();
            }
            this.f14572f0 = String.valueOf(this.f14515E.m16213b());
        }
        FileItem fileItem2 = this.f14517F;
        if (fileItem2 != null) {
            this.f14527K = fileItem2.m16115b();
            Uri uriM16510Z12 = CLUtility.m16510Z1(this.f14517F.m16117d());
            this.f14529L = uriM16510Z12;
            if (CLUtility.m16613z1(this.f14527K, uriM16510Z12)) {
                this.f14600t0 = true;
                this.f14581k = new UploadUtils.C3196b();
            }
        }
        MessageObj messageObj2 = this.f14563b0;
        this.f14594q0 = messageObj2 != null;
        if (messageObj2 != null) {
            try {
                this.f14568d0 = messageObj2.m14747I(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION);
                this.f14566c0 = this.f14563b0.m14777o();
                if (this.f14563b0.m14778p().equals(MessageObj.MessageType.PhotoNote)) {
                    strM14747I = this.f14563b0.m14747I("noteMediaDescription");
                } else {
                    strM14747I = this.f14563b0.m14747I("duration");
                }
                this.f14570e0 = strM14747I;
            } catch (Exception e9) {
                UploadUtils.m16964k("UploadMeetingMedia", "[UploadMediaHelper]", e9);
            }
        }
    }
}
