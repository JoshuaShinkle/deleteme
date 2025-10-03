package com.cyberlink.you.utility;

import com.cyberlink.you.bulletin.AudioItem;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.pages.photoimport.FileItem;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.pages.photoimport.VideoItem;
import com.cyberlink.you.utility.UploadMediaHelper;
import com.cyberlink.you.utility.UploadUtils;
import java.util.ArrayList;
import java.util.List;
import p218v2.C6456d;

/* renamed from: com.cyberlink.you.utility.c */
/* loaded from: classes.dex */
public class C3199c {

    /* renamed from: l */
    public static boolean f14778l = false;

    /* renamed from: a */
    public final ArrayList<UploadMediaHelper> f14779a = new ArrayList<>();

    /* renamed from: b */
    public final Object f14780b = new Object();

    /* renamed from: c */
    public int f14781c = 0;

    /* renamed from: d */
    public b f14782d = null;

    /* renamed from: e */
    public boolean f14783e = false;

    /* renamed from: f */
    public long f14784f = -1;

    /* renamed from: g */
    public boolean f14785g = false;

    /* renamed from: h */
    public boolean f14786h = false;

    /* renamed from: i */
    public int f14787i = 0;

    /* renamed from: j */
    public final C6456d.j f14788j = new C6456d.j() { // from class: k4.m1
        @Override // p218v2.C6456d.j
        public final void onConnected() {
            this.f17701b.m17031z();
        }
    };

    /* renamed from: k */
    public UploadMediaHelper.AbstractC3185v f14789k = new a();

    /* renamed from: com.cyberlink.you.utility.c$a */
    public class a extends UploadMediaHelper.AbstractC3185v {
        public a() {
        }

        @Override // com.cyberlink.you.utility.UploadMediaHelper.AbstractC3185v
        /* renamed from: b */
        public void mo16874b() {
            UploadUtils.m16965l("UploadMultipleMediaHelper", "onReachLimit in");
            if (C3199c.this.f14782d != null) {
                C3199c.this.f14782d.mo9268c();
            }
        }

        @Override // com.cyberlink.you.utility.UploadMediaHelper.AbstractC3185v
        /* renamed from: c */
        public void mo8381c(UploadMediaHelper uploadMediaHelper) {
            UploadUtils.m16965l("UploadMultipleMediaHelper", "[onStep1Complete] in");
            m17046f(uploadMediaHelper);
        }

        @Override // com.cyberlink.you.utility.UploadMediaHelper.AbstractC3185v
        /* renamed from: e */
        public void mo8382e(UploadMediaHelper uploadMediaHelper) {
            UploadUtils.m16965l("UploadMultipleMediaHelper", "[onStep3Complete] in");
            m17046f(uploadMediaHelper);
        }

        /* renamed from: f */
        public final void m17046f(UploadMediaHelper uploadMediaHelper) {
            try {
                UploadUtils.UploadResultType uploadResultTypeM16828Y0 = uploadMediaHelper.m16828Y0();
                if (uploadMediaHelper.m16860o1()) {
                    uploadResultTypeM16828Y0 = uploadMediaHelper.m16853k1();
                    if (uploadMediaHelper.m16857m1() && uploadMediaHelper.m16853k1().equals(UploadUtils.UploadResultType.STEP_2_SUCCESS)) {
                        uploadResultTypeM16828Y0 = uploadMediaHelper.m16828Y0();
                    }
                } else if (uploadMediaHelper.m16859n1()) {
                    uploadResultTypeM16828Y0 = uploadMediaHelper.m16848i1();
                } else if (uploadMediaHelper.m16855l1()) {
                    uploadResultTypeM16828Y0 = uploadMediaHelper.m16810O0();
                }
                synchronized (this) {
                    if (uploadResultTypeM16828Y0.equals(UploadUtils.UploadResultType.STEP_1_FAIL) || uploadResultTypeM16828Y0.equals(UploadUtils.UploadResultType.STEP_2_VOICE_FAIL) || uploadResultTypeM16828Y0.equals(UploadUtils.UploadResultType.STEP_2_SMALL_FAIL) || uploadResultTypeM16828Y0.equals(UploadUtils.UploadResultType.STEP_3_VOICE_FAIL) || uploadResultTypeM16828Y0.equals(UploadUtils.UploadResultType.STEP_3_SMALL_FAIL) || uploadResultTypeM16828Y0.equals(UploadUtils.UploadResultType.STEP_3_BIG_FAIL) || uploadResultTypeM16828Y0.equals(UploadUtils.UploadResultType.STEP_3_SUCCESS)) {
                        UploadUtils.m16965l("UploadMultipleMediaHelper", "[onHandleStepComplete] result type = " + uploadResultTypeM16828Y0);
                        UploadUtils.UploadResultType uploadResultType = UploadUtils.UploadResultType.STEP_3_SUCCESS;
                        if (uploadResultTypeM16828Y0.equals(uploadResultType)) {
                            C3199c.m17020f(C3199c.this);
                            UploadUtils.m16965l("UploadMultipleMediaHelper", "[onHandleStepComplete] Upload success. Go to next media = " + C3199c.this.f14781c);
                        } else if (C3199c.this.f14787i < 3) {
                            C3199c.m17023i(C3199c.this);
                            UploadUtils.m16965l("UploadMultipleMediaHelper", "[onHandleStepComplete] Upload failed and need to retry");
                        } else {
                            C3199c.m17020f(C3199c.this);
                            C3199c.this.f14787i = 0;
                            UploadUtils.m16965l("UploadMultipleMediaHelper", "[onHandleStepComplete] Upload failed, but can't retry anymore. Go to next media =" + C3199c.this.f14781c);
                        }
                        C3199c.this.f14783e = false;
                        synchronized (C3199c.this.f14779a) {
                            if (C3199c.this.f14781c >= C3199c.this.f14779a.size()) {
                                UploadUtils.m16965l("UploadMultipleMediaHelper", "[onHandleStepComplete] All task is finish.");
                                if (C3199c.this.f14782d != null) {
                                    C3199c.this.f14782d.mo7917b(C3199c.this.m17043u());
                                }
                            } else if (!C6456d.m24714D().m24748G()) {
                                UploadUtils.m16965l("UploadMultipleMediaHelper", "[onHandleStepComplete] The network is not connected.");
                                if (C3199c.this.f14782d != null) {
                                    C3199c.this.f14782d.onPause();
                                }
                            } else if (uploadResultTypeM16828Y0.equals(uploadResultType)) {
                                UploadUtils.m16965l("UploadMultipleMediaHelper", "[onHandleStepComplete] The upload task is finished. Continue to upload next media immediately.");
                                C3199c.this.m17035F();
                            } else {
                                UploadUtils.m16965l("UploadMultipleMediaHelper", "[onHandleStepComplete] The upload task is failed. Wait 3000 millisecond to run next.");
                                C3199c.this.f14785g = true;
                                C3199c.this.m17035F();
                            }
                        }
                    } else {
                        UploadUtils.m16965l("UploadMultipleMediaHelper", "[onHandleStepComplete] Upload is not fail case and reset retry count to 0.");
                        C3199c.this.f14787i = 0;
                    }
                }
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        }
    }

    /* renamed from: com.cyberlink.you.utility.c$b */
    public interface b {
        /* renamed from: a */
        void mo7916a(int i9, int i10, UploadMediaHelper uploadMediaHelper);

        /* renamed from: b */
        void mo7917b(C3199c c3199c);

        /* renamed from: c */
        default void mo9268c() {
        }

        void onCancel();

        default void onPause() {
        }
    }

    public C3199c() {
        UploadUtils.m16965l("UploadMultipleMediaHelper", "[constructor]");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: A */
    public /* synthetic */ void m17014A() {
        synchronized (this.f14780b) {
            if (this.f14785g) {
                try {
                    UploadUtils.m16965l("UploadMultipleMediaHelper", "[startUpload] Before wait.");
                    this.f14780b.wait(3000L);
                    UploadUtils.m16965l("UploadMultipleMediaHelper", "[startUpload] After wait.");
                } catch (InterruptedException e9) {
                    e9.printStackTrace();
                }
                this.f14785g = false;
            } else {
                UploadUtils.m16965l("UploadMultipleMediaHelper", "[startUpload] don't wait.");
            }
        }
        if (this.f14786h) {
            UploadUtils.m16965l("UploadMultipleMediaHelper", "[startUpload] Is shutdown.");
            b bVar = this.f14782d;
            if (bVar != null) {
                bVar.onCancel();
                return;
            }
            return;
        }
        UploadUtils.m16965l("UploadMultipleMediaHelper", "[startUpload] in");
        synchronized (this.f14780b) {
            if (this.f14779a.isEmpty()) {
                UploadUtils.m16965l("UploadMultipleMediaHelper", "[startUpload] size is 0");
                return;
            }
            if (this.f14783e) {
                UploadUtils.m16965l("UploadMultipleMediaHelper", "[startUpload] Uploading media now");
                return;
            }
            if (m17045w()) {
                UploadUtils.m16965l("UploadMultipleMediaHelper", "[startUpload] The upload task is expired.");
                b bVar2 = this.f14782d;
                if (bVar2 != null) {
                    bVar2.onCancel();
                }
                return;
            }
            if (this.f14781c >= this.f14779a.size()) {
                return;
            }
            UploadMediaHelper uploadMediaHelper = this.f14779a.get(this.f14781c);
            if (C6456d.m24714D().m24748G()) {
                this.f14783e = true;
                UploadUtils.m16965l("UploadMultipleMediaHelper", "[startUpload] startUpload");
                uploadMediaHelper.m16819T1();
                b bVar3 = this.f14782d;
                if (bVar3 != null) {
                    bVar3.mo7916a(this.f14781c, this.f14779a.size(), uploadMediaHelper);
                }
                return;
            }
            UploadUtils.m16965l("UploadMultipleMediaHelper", "[startUpload] The network is disconnected.");
            b bVar4 = this.f14782d;
            if (bVar4 != null) {
                bVar4.mo7916a(this.f14781c, this.f14779a.size(), uploadMediaHelper);
            }
            b bVar5 = this.f14782d;
            if (bVar5 != null) {
                bVar5.onPause();
            }
        }
    }

    /* renamed from: D */
    public static void m17015D(boolean z8) {
        f14778l = z8;
    }

    /* renamed from: f */
    public static /* synthetic */ int m17020f(C3199c c3199c) {
        int i9 = c3199c.f14781c;
        c3199c.f14781c = i9 + 1;
        return i9;
    }

    /* renamed from: i */
    public static /* synthetic */ int m17023i(C3199c c3199c) {
        int i9 = c3199c.f14787i;
        c3199c.f14787i = i9 + 1;
        return i9;
    }

    /* renamed from: x */
    public static boolean m17029x() {
        return f14778l;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y */
    public /* synthetic */ void m17030y() {
        UploadMediaHelper uploadMediaHelper;
        UploadUtils.m16965l("UploadMultipleMediaHelper", "[abort] Abort current uploading task.");
        synchronized (this.f14779a) {
            uploadMediaHelper = !this.f14779a.isEmpty() ? this.f14779a.get(this.f14781c) : null;
        }
        m17034E();
        if (uploadMediaHelper != null) {
            uploadMediaHelper.m16850j();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z */
    public /* synthetic */ void m17031z() {
        UploadUtils.m16965l("UploadMultipleMediaHelper", "[onConnected] in");
        m17035F();
    }

    /* renamed from: B */
    public final void m17032B() {
        UploadUtils.m16965l("UploadMultipleMediaHelper", "[onNotifyWait] in.");
        synchronized (this.f14780b) {
            UploadUtils.m16965l("UploadMultipleMediaHelper", "[onNotifyWait] Before notify wait.");
            this.f14780b.notifyAll();
            this.f14785g = false;
            UploadUtils.m16965l("UploadMultipleMediaHelper", "[onNotifyWait] After notify wait.");
        }
    }

    /* renamed from: C */
    public void m17033C(b bVar) {
        this.f14782d = bVar;
    }

    /* renamed from: E */
    public void m17034E() {
        UploadUtils.m16965l("UploadMultipleMediaHelper", "[shutdown] in");
        UploadUtils.m16965l("UploadMultipleMediaHelper", "[shutdown] Remove connection listener");
        C6456d.m24714D().m24753M(this.f14788j);
        synchronized (this.f14780b) {
            UploadUtils.m16965l("UploadMultipleMediaHelper", "[shutdown] Clear upload media list.");
            this.f14779a.clear();
        }
        this.f14786h = true;
        this.f14783e = false;
        this.f14781c = 0;
        this.f14787i = 0;
        this.f14784f = -1L;
        m17032B();
    }

    /* renamed from: F */
    public void m17035F() {
        new Thread(new Runnable() { // from class: k4.k1
            @Override // java.lang.Runnable
            public final void run() {
                this.f17687b.m17014A();
            }
        }).start();
    }

    /* renamed from: d */
    public void m17036d() {
        new Thread(new Runnable() { // from class: k4.l1
            @Override // java.lang.Runnable
            public final void run() {
                this.f17690b.m17030y();
            }
        }).start();
    }

    /* renamed from: o */
    public void m17037o(String str, AudioItem audioItem) {
        UploadMediaHelper uploadMediaHelper = new UploadMediaHelper(str, audioItem);
        uploadMediaHelper.m16815R1(this.f14789k);
        this.f14779a.add(uploadMediaHelper);
    }

    /* renamed from: p */
    public void m17038p(String str, FileItem fileItem, boolean z8) {
        UploadMediaHelper uploadMediaHelper = new UploadMediaHelper(str, fileItem);
        uploadMediaHelper.m16815R1(this.f14789k);
        if (z8) {
            uploadMediaHelper.m16799H0();
        }
        this.f14779a.add(uploadMediaHelper);
    }

    /* renamed from: q */
    public void m17039q(String str, ImageItem imageItem, String str2, String str3, String str4, String str5) {
        m17040r(str, imageItem, str2, str3, str4, str5, false);
    }

    /* renamed from: r */
    public void m17040r(String str, ImageItem imageItem, String str2, String str3, String str4, String str5, boolean z8) {
        synchronized (this.f14779a) {
            UploadMediaHelper uploadMediaHelper = new UploadMediaHelper(str, imageItem, str2, str3, str4, str5);
            uploadMediaHelper.m16815R1(this.f14789k);
            if (z8) {
                uploadMediaHelper.m16799H0();
            }
            this.f14779a.add(uploadMediaHelper);
        }
    }

    /* renamed from: s */
    public void m17041s(String str, VideoItem videoItem) {
        UploadMediaHelper uploadMediaHelper = new UploadMediaHelper(str, videoItem);
        uploadMediaHelper.m16815R1(this.f14789k);
        this.f14779a.add(uploadMediaHelper);
    }

    /* renamed from: t */
    public List<UploadMediaHelper> m17042t() {
        return this.f14779a;
    }

    /* renamed from: u */
    public final C3199c m17043u() {
        return this;
    }

    /* renamed from: v */
    public void m17044v() {
        UploadUtils.m16965l("UploadMultipleMediaHelper", "[initialize] in");
        this.f14786h = false;
        this.f14783e = false;
        this.f14785g = false;
        this.f14781c = 0;
        this.f14787i = 0;
        this.f14784f = FriendsClient.m15646K();
        UploadUtils.m16965l("UploadMultipleMediaHelper", "[initialize] Add connection listener");
        C6456d.m24714D().m24744B(this.f14788j);
    }

    /* renamed from: w */
    public final boolean m17045w() {
        return (FriendsClient.m15646K() - this.f14784f) / 1000 >= 3600;
    }
}
