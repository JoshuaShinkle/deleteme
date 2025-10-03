package com.cyberlink.you.utility;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.cyberlink.clsm.C1199a;
import com.cyberlink.p033u.glide.MediaLoader;
import com.cyberlink.you.Globals;
import com.cyberlink.you.bulletin.AudioItem;
import com.cyberlink.you.chat.p034e.C2889b;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2971k0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.friends.C3061a;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.pages.photoimport.FileItem;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.pages.photoimport.VideoItem;
import com.cyberlink.you.utility.UploadUtils;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.appinvite.PreviewActivity;
import com.google.android.gms.plus.PlusShare;
import com.google.common.net.HttpHeaders;
import com.google.common.primitives.UnsignedBytes;
import com.google.firebase.messaging.Constants;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5170o0;
import p116k4.C5172p;
import p201t3.C6301o;
import p209u2.AbstractC6381r;
import p209u2.C6370g;
import p209u2.C6383t;
import p209u2.ThreadFactoryC6373j;

/* loaded from: classes.dex */
public class UploadMediaHelper {

    /* renamed from: c1 */
    public static int f14506c1 = 2;

    /* renamed from: A */
    public String f14507A;

    /* renamed from: A0 */
    public DataOutputStream f14508A0;

    /* renamed from: B */
    public String f14509B;

    /* renamed from: B0 */
    public HttpURLConnection f14510B0;

    /* renamed from: C */
    public String f14511C;

    /* renamed from: C0 */
    public DataOutputStream f14512C0;

    /* renamed from: D */
    public AudioItem f14513D;

    /* renamed from: D0 */
    public HttpURLConnection f14514D0;

    /* renamed from: E */
    public VideoItem f14515E;

    /* renamed from: E0 */
    public DataOutputStream f14516E0;

    /* renamed from: F */
    public FileItem f14517F;

    /* renamed from: F0 */
    public HttpURLConnection f14518F0;

    /* renamed from: G */
    public C3184u f14519G;

    /* renamed from: G0 */
    public DataOutputStream f14520G0;

    /* renamed from: H */
    public String f14521H;

    /* renamed from: H0 */
    public final Semaphore f14522H0;

    /* renamed from: I */
    public String f14523I;

    /* renamed from: I0 */
    public final Semaphore f14524I0;

    /* renamed from: J */
    public Uri f14525J;

    /* renamed from: J0 */
    public final Semaphore f14526J0;

    /* renamed from: K */
    public String f14527K;

    /* renamed from: K0 */
    public final Semaphore f14528K0;

    /* renamed from: L */
    public Uri f14529L;

    /* renamed from: L0 */
    public Semaphore f14530L0;

    /* renamed from: M */
    public String f14531M;

    /* renamed from: M0 */
    public Semaphore f14532M0;

    /* renamed from: N */
    public String f14533N;

    /* renamed from: N0 */
    public Semaphore f14534N0;

    /* renamed from: O */
    public String f14535O;

    /* renamed from: O0 */
    public Semaphore f14536O0;

    /* renamed from: P */
    public String f14537P;

    /* renamed from: P0 */
    public Semaphore f14538P0;

    /* renamed from: Q */
    public C2973l0 f14539Q;

    /* renamed from: Q0 */
    public Semaphore f14540Q0;

    /* renamed from: R */
    public C2973l0 f14541R;

    /* renamed from: R0 */
    public int f14542R0;

    /* renamed from: S */
    public C2973l0 f14543S;

    /* renamed from: S0 */
    public AsyncTaskC3186w f14544S0;

    /* renamed from: T */
    public C2973l0 f14545T;

    /* renamed from: T0 */
    public final Object f14546T0;

    /* renamed from: U */
    public final FriendsClient f14547U;

    /* renamed from: U0 */
    public final Object f14548U0;

    /* renamed from: V */
    public AbstractC3185v f14549V;

    /* renamed from: V0 */
    public boolean f14550V0;

    /* renamed from: W */
    public InterfaceC3183t f14551W;

    /* renamed from: W0 */
    public boolean f14552W0;

    /* renamed from: X */
    public UploadUtils.UploadResultType f14553X;

    /* renamed from: X0 */
    public boolean f14554X0;

    /* renamed from: Y */
    public UploadUtils.UploadResultType f14555Y;

    /* renamed from: Y0 */
    public boolean f14556Y0;

    /* renamed from: Z */
    public UploadUtils.UploadResultType f14557Z;

    /* renamed from: Z0 */
    public C1199a f14558Z0;

    /* renamed from: a */
    public final ExecutorService f14559a;

    /* renamed from: a0 */
    public UploadUtils.UploadResultType f14560a0;

    /* renamed from: a1 */
    public C1199a f14561a1;

    /* renamed from: b */
    public UploadUtils.C3195a f14562b;

    /* renamed from: b0 */
    public MessageObj f14563b0;

    /* renamed from: b1 */
    public InterfaceC3187x f14564b1;

    /* renamed from: c */
    public UploadUtils.C3195a f14565c;

    /* renamed from: c0 */
    public String f14566c0;

    /* renamed from: d */
    public UploadUtils.C3195a f14567d;

    /* renamed from: d0 */
    public String f14568d0;

    /* renamed from: e */
    public UploadUtils.C3195a f14569e;

    /* renamed from: e0 */
    public String f14570e0;

    /* renamed from: f */
    public UploadUtils.C3195a f14571f;

    /* renamed from: f0 */
    public String f14572f0;

    /* renamed from: g */
    public String f14573g;

    /* renamed from: g0 */
    public int f14574g0;

    /* renamed from: h */
    public String f14575h;

    /* renamed from: h0 */
    public int f14576h0;

    /* renamed from: i */
    public String f14577i;

    /* renamed from: i0 */
    public int f14578i0;

    /* renamed from: j */
    public UploadUtils.C3196b f14579j;

    /* renamed from: j0 */
    public int f14580j0;

    /* renamed from: k */
    public UploadUtils.C3196b f14581k;

    /* renamed from: k0 */
    public int f14582k0;

    /* renamed from: l */
    public String f14583l;

    /* renamed from: l0 */
    public String f14584l0;

    /* renamed from: m */
    public String f14585m;

    /* renamed from: m0 */
    public String f14586m0;

    /* renamed from: n */
    public String f14587n;

    /* renamed from: n0 */
    public boolean f14588n0;

    /* renamed from: o */
    public String f14589o;

    /* renamed from: o0 */
    public boolean f14590o0;

    /* renamed from: p */
    public Date f14591p;

    /* renamed from: p0 */
    public boolean f14592p0;

    /* renamed from: q */
    public Date f14593q;

    /* renamed from: q0 */
    public boolean f14594q0;

    /* renamed from: r */
    public Date f14595r;

    /* renamed from: r0 */
    public boolean f14596r0;

    /* renamed from: s */
    public Date f14597s;

    /* renamed from: s0 */
    public boolean f14598s0;

    /* renamed from: t */
    public String f14599t;

    /* renamed from: t0 */
    public boolean f14600t0;

    /* renamed from: u */
    public String f14601u;

    /* renamed from: u0 */
    public FailReason f14602u0;

    /* renamed from: v */
    public String f14603v;

    /* renamed from: v0 */
    public volatile boolean f14604v0;

    /* renamed from: w */
    public String f14605w;

    /* renamed from: w0 */
    public AsyncTaskC3186w f14606w0;

    /* renamed from: x */
    public String f14607x;

    /* renamed from: x0 */
    public HttpURLConnection f14608x0;

    /* renamed from: y */
    public String f14609y;

    /* renamed from: y0 */
    public DataOutputStream f14610y0;

    /* renamed from: z */
    public ImageItem f14611z;

    /* renamed from: z0 */
    public HttpURLConnection f14612z0;

    public enum FailReason {
        FAIL_NONE,
        FAIL_VOICE_UPLOAD,
        FAIL_VOICE_COMPLETE,
        FAIL_VIDEO_UPLOAD,
        FAIL_VIDEO_COMPLETE,
        FAIL_SMALL_UPLOAD,
        FAIL_SMALL_COMPLETE,
        FAIL_BIG_UPLOAD,
        FAIL_BIG_UPDATE,
        FAIL_BIG_COMPLETE,
        FAIL_FILE_UPLOAD,
        FAIL_FILE_COMPLETE
    }

    public enum MediaType {
        VOICE,
        VIDEO,
        BIG_IMG,
        SMALL_IMG,
        VIDEO_IMG,
        FILE
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$a */
    public class C3164a extends Thread {
        public C3164a() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws InterruptedException, IOException {
            UploadUtils.m16965l("Upload Performance", "U.2 uploadVoice_step1 ==> buildConnection(); voice ======== start");
            UploadMediaHelper uploadMediaHelper = UploadMediaHelper.this;
            boolean zM16863x0 = uploadMediaHelper.m16863x0(uploadMediaHelper.f14577i, MediaType.VOICE);
            StringBuilder sb = new StringBuilder();
            sb.append("U.2 uploadVoice_step1 ==> buildConnection(); voice ======== end == ");
            sb.append(zM16863x0 ? "OK" : "NG");
            UploadUtils.m16965l("Upload Performance", sb.toString());
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$b */
    public class C3165b extends Thread {
        public C3165b() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws InterruptedException, IOException {
            UploadUtils.m16965l("Upload Performance", "U.2 uploadVideo_step1 ==> buildConnection(); video thumbnail ======== start");
            UploadMediaHelper uploadMediaHelper = UploadMediaHelper.this;
            boolean zM16863x0 = uploadMediaHelper.m16863x0(uploadMediaHelper.f14573g, MediaType.VIDEO_IMG);
            StringBuilder sb = new StringBuilder();
            sb.append("U.2 uploadVideo_step1 ==> buildConnection(); video thumbnail ======== end == ");
            sb.append(zM16863x0 ? "OK" : "NG");
            UploadUtils.m16965l("Upload Performance", sb.toString());
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$c */
    public class C3166c extends Thread {
        public C3166c() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            UploadUtils.m16965l("Upload Performance", "U.2 uploadVideo_step1 ==> buildConnection(); video ======== start");
            UploadUtils.C3196b c3196b = UploadMediaHelper.this.f14579j;
            boolean zM16863x0 = true;
            if (!c3196b.f14733a) {
                UploadUtils.C3196b.a aVarM16968c = c3196b.m16968c(1);
                zM16863x0 = aVarM16968c != null ? UploadMediaHelper.this.m16863x0(aVarM16968c.f14739c, MediaType.VIDEO) : false;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("U.2 uploadVideo_step1 ==> buildConnection(); video ======== end == ");
            sb.append(zM16863x0 ? "OK" : "NG");
            UploadUtils.m16965l("Upload Performance", sb.toString());
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$d */
    public class C3167d extends Thread {
        public C3167d() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            UploadUtils.m16965l("Upload Performance", "U.2 uploadFile_step1 ==> buildConnection(); file ======== start");
            UploadUtils.C3196b c3196b = UploadMediaHelper.this.f14581k;
            boolean zM16863x0 = true;
            if (!c3196b.f14733a) {
                UploadUtils.C3196b.a aVarM16968c = c3196b.m16968c(1);
                zM16863x0 = aVarM16968c != null ? UploadMediaHelper.this.m16863x0(aVarM16968c.f14739c, MediaType.FILE) : false;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("U.2 uploadFile_step1 ==> buildConnection(); file ======== end == ");
            sb.append(zM16863x0 ? "OK" : "NG");
            UploadUtils.m16965l("Upload Performance", sb.toString());
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$e */
    public class C3168e extends Thread {
        public C3168e() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws InterruptedException, IOException {
            UploadUtils.m16965l("Upload Performance", "U.2 uploadPhoto_step1 ==> buildConnection(); small image ======== start");
            UploadMediaHelper uploadMediaHelper = UploadMediaHelper.this;
            boolean zM16863x0 = uploadMediaHelper.m16863x0(uploadMediaHelper.f14573g, MediaType.SMALL_IMG);
            StringBuilder sb = new StringBuilder();
            sb.append("U.2 uploadPhoto_step1 ==> buildConnection(); small image ======== end == ");
            sb.append(zM16863x0 ? "OK" : "NG");
            UploadUtils.m16965l("Upload Performance", sb.toString());
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$f */
    public class C3169f extends Thread {
        public C3169f() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws InterruptedException, IOException {
            UploadUtils.m16965l("Upload Performance", "U.2 uploadPhoto_step1 ==> buildConnection(); big image ======== start");
            UploadMediaHelper uploadMediaHelper = UploadMediaHelper.this;
            boolean zM16863x0 = uploadMediaHelper.m16863x0(uploadMediaHelper.f14575h, MediaType.BIG_IMG);
            StringBuilder sb = new StringBuilder();
            sb.append("U.2 uploadPhoto_step1 ==> buildConnection(); big image ======== end == ");
            sb.append(zM16863x0 ? "OK" : "NG");
            UploadUtils.m16965l("Upload Performance", sb.toString());
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$g */
    public class C3170g implements InterfaceC3187x {
        public C3170g() {
        }

        @Override // com.cyberlink.you.utility.UploadMediaHelper.InterfaceC3187x
        /* renamed from: a */
        public void mo16866a(MediaType mediaType, int i9) {
            if (UploadMediaHelper.this.f14549V == null) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[onUploadTaskCallback.onProgress]: ==== done ==== mCallback == null");
                return;
            }
            if (mediaType == MediaType.VOICE) {
                UploadMediaHelper.this.f14578i0 = i9;
            } else if (mediaType == MediaType.SMALL_IMG || mediaType == MediaType.VIDEO_IMG) {
                UploadMediaHelper.this.f14574g0 = i9;
            } else if (mediaType == MediaType.BIG_IMG) {
                UploadMediaHelper.this.f14576h0 = i9;
            } else if (mediaType == MediaType.FILE) {
                UploadMediaHelper.this.f14582k0 = i9;
            } else {
                UploadMediaHelper.this.f14580j0 = i9;
            }
            UploadMediaHelper.this.f14549V.mo16873a(UploadMediaHelper.this.m16811P0());
        }

        @Override // com.cyberlink.you.utility.UploadMediaHelper.InterfaceC3187x
        /* renamed from: b */
        public void mo16867b(AsyncTaskC3186w asyncTaskC3186w, boolean z8) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[onUploadTaskCallback.onComplete]: ==== start");
            if (asyncTaskC3186w.f14656a == MediaType.VOICE) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[onUploadTaskCallback.onComplete] -- Upload Voice, input result=" + z8);
                if (z8) {
                    UploadMediaHelper.this.f14603v = asyncTaskC3186w.f14663h;
                    UploadMediaHelper.this.m16849i2();
                } else {
                    UploadMediaHelper uploadMediaHelper = UploadMediaHelper.this;
                    uploadMediaHelper.f14555Y = uploadMediaHelper.f14594q0 ? UploadUtils.UploadResultType.STEP_2_VOICE_FAIL : UploadUtils.UploadResultType.STEP_3_VOICE_FAIL;
                    uploadMediaHelper.f14602u0 = FailReason.FAIL_VOICE_UPLOAD;
                }
                UploadUtils.m16965l("UploadMediaHelperV2", "[onUploadTaskCallback.onComplete] -- Upload Voice, done");
            } else if (asyncTaskC3186w.f14656a == MediaType.VIDEO) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[onUploadTaskCallback.onComplete] -- Upload Video, input result=" + z8);
                if (z8) {
                    UploadMediaHelper.this.f14605w = asyncTaskC3186w.f14663h;
                    UploadMediaHelper.this.m16845g2();
                } else {
                    UploadMediaHelper uploadMediaHelper2 = UploadMediaHelper.this;
                    uploadMediaHelper2.f14557Z = uploadMediaHelper2.f14594q0 ? UploadUtils.UploadResultType.STEP_2_VIDEO_FAIL : UploadUtils.UploadResultType.STEP_3_VIDEO_FAIL;
                    uploadMediaHelper2.f14602u0 = FailReason.FAIL_VIDEO_UPLOAD;
                }
                UploadUtils.m16965l("UploadMediaHelperV2", "[onUploadTaskCallback.onComplete] -- Upload Video, done");
            } else if (asyncTaskC3186w.f14656a == MediaType.FILE) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[onUploadTaskCallback.onComplete] -- Upload File, input result=" + z8);
                if (z8) {
                    UploadMediaHelper.this.f14607x = asyncTaskC3186w.f14663h;
                    UploadMediaHelper.this.m16837c2();
                } else {
                    UploadMediaHelper uploadMediaHelper3 = UploadMediaHelper.this;
                    uploadMediaHelper3.f14560a0 = uploadMediaHelper3.f14594q0 ? UploadUtils.UploadResultType.STEP_2_FILE_FAIL : UploadUtils.UploadResultType.STEP_3_FILE_FAIL;
                    uploadMediaHelper3.f14602u0 = FailReason.FAIL_FILE_UPLOAD;
                }
                UploadUtils.m16965l("UploadMediaHelperV2", "[onUploadTaskCallback.onComplete] -- Upload File, done");
            } else if (asyncTaskC3186w.f14656a == MediaType.VIDEO_IMG) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[onUploadTaskCallback.onComplete] -- Upload Video Image, input result=" + z8);
                if (z8) {
                    UploadMediaHelper.this.f14599t = asyncTaskC3186w.f14663h;
                    UploadMediaHelper.this.f14557Z = UploadUtils.UploadResultType.STEP_2_SUCCESS;
                } else {
                    UploadMediaHelper uploadMediaHelper4 = UploadMediaHelper.this;
                    uploadMediaHelper4.f14557Z = uploadMediaHelper4.f14594q0 ? UploadUtils.UploadResultType.STEP_2_SMALL_FAIL : UploadUtils.UploadResultType.STEP_3_SMALL_FAIL;
                    uploadMediaHelper4.f14602u0 = FailReason.FAIL_SMALL_UPLOAD;
                }
                UploadUtils.m16965l("UploadMediaHelperV2", "[onUploadTaskCallback.onComplete] -- Upload Video Image, done");
            } else if (asyncTaskC3186w.f14656a == MediaType.SMALL_IMG) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[onUploadTaskCallback.onComplete] -- Upload Small Photo, input result=" + z8);
                if (z8) {
                    UploadMediaHelper.this.f14599t = asyncTaskC3186w.f14663h;
                    UploadMediaHelper.this.m16843f2();
                } else {
                    UploadMediaHelper uploadMediaHelper5 = UploadMediaHelper.this;
                    uploadMediaHelper5.f14553X = uploadMediaHelper5.f14594q0 ? UploadUtils.UploadResultType.STEP_2_SMALL_FAIL : UploadUtils.UploadResultType.STEP_3_SMALL_FAIL;
                    uploadMediaHelper5.f14602u0 = FailReason.FAIL_SMALL_UPLOAD;
                }
                UploadUtils.m16965l("UploadMediaHelperV2", "[onUploadTaskCallback.onComplete] -- Upload Small Photo, done");
            } else {
                UploadUtils.m16965l("UploadMediaHelperV2", "[onUploadTaskCallback.onComplete] -- Upload Big Photo, input result=" + z8);
                if (z8) {
                    UploadMediaHelper.this.f14601u = asyncTaskC3186w.f14663h;
                    UploadMediaHelper.this.mo16835b2();
                } else {
                    UploadMediaHelper uploadMediaHelper6 = UploadMediaHelper.this;
                    if (uploadMediaHelper6.f14594q0) {
                        synchronized (uploadMediaHelper6.f14546T0) {
                            if (!UploadMediaHelper.this.f14550V0) {
                                try {
                                    UploadMediaHelper.this.f14546T0.wait();
                                } catch (InterruptedException unused) {
                                    Thread.currentThread().interrupt();
                                }
                            }
                        }
                    } else {
                        synchronized (uploadMediaHelper6.f14548U0) {
                            if (!UploadMediaHelper.this.f14552W0) {
                                try {
                                    UploadMediaHelper.this.f14548U0.wait();
                                } catch (InterruptedException unused2) {
                                    Thread.currentThread().interrupt();
                                }
                            }
                        }
                    }
                    UploadMediaHelper uploadMediaHelper7 = UploadMediaHelper.this;
                    uploadMediaHelper7.f14553X = UploadUtils.UploadResultType.STEP_3_BIG_FAIL;
                    uploadMediaHelper7.f14602u0 = FailReason.FAIL_BIG_UPLOAD;
                }
                UploadUtils.m16965l("UploadMediaHelperV2", "[onUploadTaskCallback.onComplete] -- Upload Big Photo, done");
            }
            UploadUtils.m16965l("UploadMediaHelperV2", "[onUploadTaskCallback.onComplete]: ==== done !!!");
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$h */
    public class C3171h extends AbstractC6381r<C1199a, Void> {
        public C3171h() {
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(C1199a c1199a) {
            UploadMediaHelper.this.f14558Z0 = c1199a;
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$i */
    public class C3172i extends AbstractC6381r<C1199a, Void> {

        /* renamed from: c */
        public final /* synthetic */ UploadUtils.C3195a f14641c;

        public C3172i(UploadUtils.C3195a c3195a) {
            this.f14641c = c3195a;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(C1199a c1199a) {
            UploadUtils.C3195a c3195a = this.f14641c;
            c3195a.f14731j = c1199a.f5810a;
            c3195a.f14732k = c1199a.f5811b;
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$j */
    public static /* synthetic */ class C3173j {

        /* renamed from: a */
        public static final /* synthetic */ int[] f14642a;

        static {
            int[] iArr = new int[MediaType.values().length];
            f14642a = iArr;
            try {
                iArr[MediaType.VIDEO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f14642a[MediaType.FILE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f14642a[MediaType.BIG_IMG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f14642a[MediaType.SMALL_IMG.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f14642a[MediaType.VOICE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f14642a[MediaType.VIDEO_IMG.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$k */
    public class AsyncTaskC3174k extends AsyncTask<Void, Void, Boolean> {

        /* renamed from: a */
        public final /* synthetic */ Uri f14643a;

        /* renamed from: b */
        public final /* synthetic */ Runnable f14644b;

        public AsyncTaskC3174k(Uri uri, Runnable runnable) {
            this.f14643a = uri;
            this.f14644b = runnable;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Void... voidArr) throws IOException {
            Bitmap bitmapM16493U;
            boolean zCompress = false;
            try {
                bitmapM16493U = CLUtility.m16493U(null, this.f14643a, null);
            } catch (Exception e9) {
                e9.printStackTrace();
            } catch (OutOfMemoryError e10) {
                e10.printStackTrace();
            }
            if (bitmapM16493U == null) {
                return Boolean.FALSE;
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapM16493U);
            OutputStream outputStreamOpenOutputStream = Globals.m7372O().getContentResolver().openOutputStream(this.f14643a);
            try {
                zCompress = bitmapCreateBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStreamOpenOutputStream);
                if (outputStreamOpenOutputStream != null) {
                    outputStreamOpenOutputStream.close();
                }
                bitmapM16493U.recycle();
                bitmapCreateBitmap.recycle();
                return Boolean.valueOf(zCompress);
            } finally {
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            if (bool.booleanValue()) {
                this.f14644b.run();
            }
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$l */
    public class C3175l extends Thread {
        public C3175l() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            UploadUtils.m16965l("Upload Performance", "U.1 startUpload ==> prepareUploadMedia(); ======== start");
            boolean zM16800H1 = UploadMediaHelper.this.m16800H1();
            StringBuilder sb = new StringBuilder();
            sb.append("U.1 startUpload ==> prepareUploadMedia(); ======== end == ");
            sb.append(zM16800H1 ? "OK" : "NG");
            UploadUtils.m16965l("Upload Performance", sb.toString());
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$m */
    public class C3176m extends Thread {
        public C3176m() {
        }

        /* JADX WARN: Removed duplicated region for block: B:103:0x0180  */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() throws InterruptedException {
            UploadUtils.UploadResultType uploadResultType;
            UploadUtils.UploadResultType uploadResultType2;
            UploadUtils.UploadResultType uploadResultType3;
            UploadUtils.UploadResultType uploadResultType4;
            UploadUtils.UploadResultType uploadResultType5;
            UploadUtils.UploadResultType uploadResultType6;
            UploadUtils.m16965l("UploadMediaHelperV2", "[startUpload]: run()");
            UploadMediaHelper uploadMediaHelper = UploadMediaHelper.this;
            boolean z8 = false;
            boolean z9 = ((uploadMediaHelper.f14590o0 && uploadMediaHelper.f14555Y == UploadUtils.UploadResultType.STEP_1_FAIL) || (uploadMediaHelper.f14592p0 && uploadMediaHelper.f14557Z == UploadUtils.UploadResultType.STEP_1_FAIL) || ((uploadMediaHelper.f14600t0 && uploadMediaHelper.f14560a0 == UploadUtils.UploadResultType.STEP_1_FAIL) || ((uploadMediaHelper.f14596r0 && uploadMediaHelper.f14553X == UploadUtils.UploadResultType.STEP_1_FAIL) || ((uploadMediaHelper.f14598s0 && uploadMediaHelper.f14553X == UploadUtils.UploadResultType.STEP_1_FAIL) || (uploadMediaHelper.f14588n0 && uploadMediaHelper.f14553X == UploadUtils.UploadResultType.STEP_1_FAIL))))) ? false : true;
            Date date = new Date(new Date().getTime() + 600000);
            if ((UploadMediaHelper.this.f14593q == null || !UploadMediaHelper.this.f14593q.before(date)) && ((UploadMediaHelper.this.f14595r == null || !UploadMediaHelper.this.f14595r.before(date)) && ((UploadMediaHelper.this.f14597s == null || !UploadMediaHelper.this.f14597s.before(date)) && (UploadMediaHelper.this.f14591p == null || !UploadMediaHelper.this.f14591p.before(date))))) {
                z8 = z9;
            } else {
                UploadUtils.m16965l("UploadMediaHelperV2", "[startUpload]: upload URL expired ### enforce step-1 to re-run");
            }
            if (z8) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[startUpload]: upload_step1() ### skipped !!!");
            } else {
                UploadUtils.m16965l("UploadMediaHelperV2", "[startUpload]: upload_step1()");
                UploadUtils.UploadResultType uploadResultTypeM16858m2 = UploadMediaHelper.this.m16858m2();
                if (UploadMediaHelper.this.f14549V != null) {
                    UploadUtils.m16965l("Upload Performance", "1.3 mCallback.onStep1Complete(); ======== start");
                    UploadMediaHelper.this.f14549V.mo8381c(UploadMediaHelper.this.m16811P0());
                    UploadUtils.m16965l("Upload Performance", "1.3 mCallback.onStep1Complete(); ======== end");
                }
                if (UploadMediaHelper.this.f14604v0) {
                    return;
                }
                if (uploadResultTypeM16858m2 != UploadUtils.UploadResultType.STEP_1_SUCCESS) {
                    UploadMediaHelper.this.m16787B0();
                    UploadUtils.m16965l("UploadMediaHelperV2", "[startUpload]: ==== done #### fail result = " + uploadResultTypeM16858m2);
                    if (uploadResultTypeM16858m2 == UploadUtils.UploadResultType.STEP_1_REACH_LIMIT) {
                        UploadMediaHelper.this.f14549V.mo16874b();
                        return;
                    }
                    return;
                }
            }
            if (UploadMediaHelper.this.f14604v0) {
                return;
            }
            UploadMediaHelper uploadMediaHelper2 = UploadMediaHelper.this;
            if (!uploadMediaHelper2.f14594q0) {
                uploadMediaHelper2.m16856l2();
            } else if ((!uploadMediaHelper2.f14590o0 || ((uploadResultType6 = uploadMediaHelper2.f14555Y) != UploadUtils.UploadResultType.STEP_1_SUCCESS && uploadResultType6 != UploadUtils.UploadResultType.STEP_2_VOICE_FAIL)) && (!uploadMediaHelper2.f14592p0 || ((uploadResultType5 = uploadMediaHelper2.f14557Z) != UploadUtils.UploadResultType.STEP_1_SUCCESS && uploadResultType5 != UploadUtils.UploadResultType.STEP_2_VIDEO_FAIL))) {
                UploadUtils.UploadResultType uploadResultType7 = uploadMediaHelper2.f14557Z;
                UploadUtils.UploadResultType uploadResultType8 = UploadUtils.UploadResultType.STEP_2_SMALL_FAIL;
                if (uploadResultType7 == uploadResultType8 || ((uploadMediaHelper2.f14600t0 && ((uploadResultType4 = uploadMediaHelper2.f14560a0) == UploadUtils.UploadResultType.STEP_1_SUCCESS || uploadResultType4 == UploadUtils.UploadResultType.STEP_2_FILE_FAIL)) || ((uploadMediaHelper2.f14596r0 && ((uploadResultType3 = uploadMediaHelper2.f14553X) == UploadUtils.UploadResultType.STEP_1_SUCCESS || uploadResultType3 == uploadResultType8)) || ((uploadMediaHelper2.f14598s0 && ((uploadResultType2 = uploadMediaHelper2.f14553X) == UploadUtils.UploadResultType.STEP_1_SUCCESS || uploadResultType2 == uploadResultType8)) || (uploadMediaHelper2.f14588n0 && ((uploadResultType = uploadMediaHelper2.f14553X) == UploadUtils.UploadResultType.STEP_1_SUCCESS || uploadResultType == uploadResultType8)))))) {
                    uploadMediaHelper2.m16854k2();
                } else {
                    uploadMediaHelper2.m16856l2();
                }
            }
            UploadUtils.m16965l("UploadMediaHelperV2", "[startUpload]: ==== done");
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$n */
    public class C3177n extends Thread {
        public C3177n() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws ExecutionException, InterruptedException {
            if (UploadMediaHelper.this.f14604v0) {
                return;
            }
            UploadMediaHelper uploadMediaHelper = UploadMediaHelper.this;
            if (uploadMediaHelper.f14602u0 == FailReason.FAIL_VOICE_COMPLETE) {
                uploadMediaHelper.f14578i0 = 100;
                UploadMediaHelper.this.f14549V.mo16873a(UploadMediaHelper.this.m16811P0());
                UploadMediaHelper.this.m16849i2();
            } else {
                UploadUtils.m16965l("Upload Performance", "P2.1 startUploadVoice()==> UploadTask(); ======== start");
                UploadMediaHelper.this.f14606w0 = UploadMediaHelper.this.new AsyncTaskC3186w(MediaType.VOICE);
                UploadMediaHelper.this.f14606w0.m16891p(UploadMediaHelper.this.f14564b1);
                try {
                    UploadMediaHelper.this.f14606w0.executeOnExecutor(UploadMediaHelper.this.f14559a, "", UploadMediaHelper.this.f14577i).get();
                } catch (Exception e9) {
                    UploadUtils.m16964k("UploadMediaHelperV2", "[startUploadVoice]", e9);
                }
                UploadMediaHelper.this.f14606w0 = null;
                UploadUtils.m16965l("Upload Performance", "P2.1 startUploadVoice()==> UploadTask(); ======== end");
            }
            if (UploadMediaHelper.this.f14604v0) {
                return;
            }
            if (UploadMediaHelper.this.f14549V != null) {
                UploadMediaHelper uploadMediaHelper2 = UploadMediaHelper.this;
                if (uploadMediaHelper2.f14594q0) {
                    if (uploadMediaHelper2.f14555Y != UploadUtils.UploadResultType.STEP_2_SUCCESS) {
                        UploadUtils.m16965l("Upload Performance", "2.3 mCallback.onStep2Complete(); ======== start");
                        UploadMediaHelper.this.f14549V.mo16875d(UploadMediaHelper.this.m16811P0());
                        UploadUtils.m16965l("Upload Performance", "2.3 mCallback.onStep2Complete(); ======== end");
                    } else if (!uploadMediaHelper2.f14588n0 && !uploadMediaHelper2.f14596r0) {
                        UploadUtils.m16965l("Upload Performance", "2.3 mCallback.onStep2Complete(); ======== start");
                        UploadMediaHelper.this.f14549V.mo16875d(UploadMediaHelper.this.m16811P0());
                        UploadUtils.m16965l("Upload Performance", "2.3 mCallback.onStep2Complete(); ======== end");
                        UploadMediaHelper.this.f14555Y = UploadUtils.UploadResultType.STEP_3_SUCCESS;
                        UploadUtils.m16965l("Upload Performance", "3.2 mCallback.onStep3Complete(); ======== start");
                        UploadMediaHelper.this.f14549V.mo8382e(UploadMediaHelper.this.m16811P0());
                        UploadUtils.m16965l("Upload Performance", "3.2 mCallback.onStep3Complete(); ======== end");
                        UploadUtils.m16965l("Upload Performance", "Trace upload process <<<<<<<<<<<<<<<<<<<<<<<<<<< End");
                    }
                } else if (uploadMediaHelper2.f14555Y != UploadUtils.UploadResultType.STEP_3_SUCCESS) {
                    UploadUtils.m16965l("Upload Performance", "3.2 mCallback.onStep3Complete(); ======== start");
                    UploadMediaHelper.this.f14549V.mo8382e(UploadMediaHelper.this.m16811P0());
                    UploadUtils.m16965l("Upload Performance", "3.2 mCallback.onStep3Complete(); ======== end");
                    UploadUtils.m16965l("Upload Performance", "Trace upload process <<<<<<<<<<<<<<<<<<<<<<<<<<< End");
                } else if (!uploadMediaHelper2.f14588n0 && !uploadMediaHelper2.f14596r0) {
                    UploadUtils.m16965l("Upload Performance", "3.2 mCallback.onStep3Complete(); ======== start");
                    UploadMediaHelper.this.f14549V.mo8382e(UploadMediaHelper.this.m16811P0());
                    UploadUtils.m16965l("Upload Performance", "3.2 mCallback.onStep3Complete(); ======== end");
                    UploadUtils.m16965l("Upload Performance", "Trace upload process <<<<<<<<<<<<<<<<<<<<<<<<<<< End");
                }
            }
            UploadMediaHelper uploadMediaHelper3 = UploadMediaHelper.this;
            UploadUtils.UploadResultType uploadResultType = uploadMediaHelper3.f14555Y;
            if (uploadResultType == UploadUtils.UploadResultType.STEP_2_VOICE_FAIL || uploadResultType == UploadUtils.UploadResultType.STEP_3_VOICE_FAIL || uploadMediaHelper3.f14604v0) {
                return;
            }
            UploadMediaHelper uploadMediaHelper4 = UploadMediaHelper.this;
            if (uploadMediaHelper4.f14588n0 || uploadMediaHelper4.f14596r0) {
                uploadMediaHelper4.m16825W1();
            }
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$o */
    public class C3178o extends Thread {
        public C3178o() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws ExecutionException, InterruptedException {
            if (UploadMediaHelper.this.f14604v0) {
                return;
            }
            UploadMediaHelper uploadMediaHelper = UploadMediaHelper.this;
            if (uploadMediaHelper.f14602u0 == FailReason.FAIL_VIDEO_COMPLETE) {
                uploadMediaHelper.f14580j0 = 100;
                UploadMediaHelper.this.f14574g0 = 100;
                UploadMediaHelper.this.f14549V.mo16873a(UploadMediaHelper.this.m16811P0());
                UploadMediaHelper.this.m16845g2();
            } else {
                UploadUtils.m16965l("Upload Performance", "P2.1 startUploadVideoThumb()==> UploadTask(); ======== start");
                UploadMediaHelper.this.f14606w0 = UploadMediaHelper.this.new AsyncTaskC3186w(MediaType.VIDEO_IMG);
                UploadMediaHelper.this.f14606w0.m16891p(UploadMediaHelper.this.f14564b1);
                try {
                    UploadMediaHelper.this.f14606w0.executeOnExecutor(UploadMediaHelper.this.f14559a, UploadMediaHelper.this.f14573g, "").get();
                } catch (Exception e9) {
                    UploadUtils.m16964k("UploadMediaHelperV2", "[startUploadVideoThumb]", e9);
                }
                UploadMediaHelper.this.f14606w0 = null;
                UploadUtils.m16965l("Upload Performance", "P2.1 startUploadVideoThumb()==> UploadTask(); ======== end");
            }
            if (UploadMediaHelper.this.f14604v0) {
                return;
            }
            if (UploadMediaHelper.this.f14549V != null) {
                UploadMediaHelper uploadMediaHelper2 = UploadMediaHelper.this;
                if (uploadMediaHelper2.f14594q0) {
                    if (uploadMediaHelper2.f14557Z != UploadUtils.UploadResultType.STEP_2_SUCCESS) {
                        UploadUtils.m16965l("Upload Performance", "2.3 mCallback.onStep2Complete(); ======== start");
                        UploadMediaHelper.this.f14549V.mo16875d(UploadMediaHelper.this.m16811P0());
                        UploadUtils.m16965l("Upload Performance", "2.3 mCallback.onStep2Complete(); ======== end");
                    }
                } else if (uploadMediaHelper2.f14557Z != UploadUtils.UploadResultType.STEP_3_SUCCESS) {
                    UploadUtils.m16965l("Upload Performance", "3.2 mCallback.onStep3Complete(); ======== start");
                    UploadMediaHelper.this.f14549V.mo8382e(UploadMediaHelper.this.m16811P0());
                    UploadUtils.m16965l("Upload Performance", "3.2 mCallback.onStep3Complete(); ======== end");
                    UploadUtils.m16965l("Upload Performance", "Trace upload process <<<<<<<<<<<<<<<<<<<<<<<<<<< End");
                }
            }
            UploadMediaHelper uploadMediaHelper3 = UploadMediaHelper.this;
            UploadUtils.UploadResultType uploadResultType = uploadMediaHelper3.f14557Z;
            if (uploadResultType == UploadUtils.UploadResultType.STEP_2_SMALL_FAIL || uploadResultType == UploadUtils.UploadResultType.STEP_3_SMALL_FAIL || uploadMediaHelper3.f14604v0) {
                return;
            }
            UploadMediaHelper.this.m16827X1();
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$p */
    public class C3179p extends Thread {
        public C3179p() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws ExecutionException, InterruptedException {
            if (UploadMediaHelper.this.f14604v0) {
                return;
            }
            UploadMediaHelper uploadMediaHelper = UploadMediaHelper.this;
            if (uploadMediaHelper.f14602u0 == FailReason.FAIL_VIDEO_COMPLETE) {
                uploadMediaHelper.f14580j0 = 100;
                UploadMediaHelper.this.f14574g0 = 100;
                if (UploadMediaHelper.this.f14549V != null) {
                    UploadMediaHelper.this.f14549V.mo16873a(UploadMediaHelper.this.m16811P0());
                }
                UploadMediaHelper.this.m16845g2();
            } else {
                uploadMediaHelper.f14574g0 = 100;
                if (UploadMediaHelper.this.f14549V != null) {
                    UploadMediaHelper.this.f14549V.mo16873a(UploadMediaHelper.this.m16811P0());
                }
                UploadUtils.m16965l("Upload Performance", "P2.1 startUploadVideo()==> UploadTask(); ======== start");
                UploadMediaHelper.this.f14606w0 = UploadMediaHelper.this.new AsyncTaskC3186w(MediaType.VIDEO);
                UploadMediaHelper.this.f14606w0.m16891p(UploadMediaHelper.this.f14564b1);
                try {
                    UploadMediaHelper.this.f14606w0.executeOnExecutor(UploadMediaHelper.this.f14559a, "", "").get();
                } catch (Exception e9) {
                    UploadUtils.m16964k("UploadMediaHelperV2", "[startUploadVideo]", e9);
                }
                UploadMediaHelper.this.f14606w0 = null;
                UploadUtils.m16965l("Upload Performance", "P2.1 startUploadVideo()==> UploadTask(); ======== end");
            }
            if (UploadMediaHelper.this.f14604v0) {
                return;
            }
            if (UploadMediaHelper.this.f14549V != null) {
                UploadMediaHelper uploadMediaHelper2 = UploadMediaHelper.this;
                if (!uploadMediaHelper2.f14594q0) {
                    UploadUtils.m16965l("Upload Performance", "3.2 mCallback.onStep3Complete(); ======== start");
                    UploadMediaHelper.this.f14549V.mo8382e(UploadMediaHelper.this.m16811P0());
                    UploadUtils.m16965l("Upload Performance", "3.2 mCallback.onStep3Complete(); ======== end");
                    UploadUtils.m16965l("Upload Performance", "Trace upload process <<<<<<<<<<<<<<<<<<<<<<<<<<< End");
                } else if (uploadMediaHelper2.f14557Z != UploadUtils.UploadResultType.STEP_2_SUCCESS) {
                    UploadUtils.m16965l("Upload Performance", "2.3 mCallback.onStep2Complete(); ======== start");
                    UploadMediaHelper.this.f14549V.mo16875d(UploadMediaHelper.this.m16811P0());
                    UploadUtils.m16965l("Upload Performance", "2.3 mCallback.onStep2Complete(); ======== end");
                } else {
                    UploadUtils.m16965l("Upload Performance", "2.3 mCallback.onStep2Complete(); ======== start");
                    UploadMediaHelper.this.f14549V.mo16875d(UploadMediaHelper.this.m16811P0());
                    UploadUtils.m16965l("Upload Performance", "2.3 mCallback.onStep2Complete(); ======== end");
                    UploadMediaHelper.this.f14557Z = UploadUtils.UploadResultType.STEP_3_SUCCESS;
                    UploadUtils.m16965l("Upload Performance", "3.2 mCallback.onStep3Complete(); ======== start");
                    UploadMediaHelper.this.f14549V.mo8382e(UploadMediaHelper.this.m16811P0());
                    UploadUtils.m16965l("Upload Performance", "3.2 mCallback.onStep3Complete(); ======== end");
                    UploadUtils.m16965l("Upload Performance", "Trace upload process <<<<<<<<<<<<<<<<<<<<<<<<<<< End");
                }
            }
            if (UploadMediaHelper.this.f14557Z != UploadUtils.UploadResultType.STEP_2_VIDEO_FAIL) {
                UploadUtils.UploadResultType uploadResultType = UploadUtils.UploadResultType.STEP_1_SUCCESS;
            }
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$q */
    public class C3180q extends Thread {
        public C3180q() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws ExecutionException, InterruptedException {
            if (UploadMediaHelper.this.f14604v0) {
                return;
            }
            UploadMediaHelper uploadMediaHelper = UploadMediaHelper.this;
            if (uploadMediaHelper.f14602u0 == FailReason.FAIL_FILE_COMPLETE) {
                uploadMediaHelper.f14582k0 = 100;
                if (UploadMediaHelper.this.f14549V != null) {
                    UploadMediaHelper.this.f14549V.mo16873a(UploadMediaHelper.this.m16811P0());
                }
                UploadMediaHelper.this.m16837c2();
            } else {
                if (uploadMediaHelper.f14549V != null) {
                    UploadMediaHelper.this.f14549V.mo16873a(UploadMediaHelper.this.m16811P0());
                }
                UploadUtils.m16965l("Upload Performance", "P2.1 startUploadFile()==> UploadTask(); ======== start");
                UploadMediaHelper.this.f14606w0 = UploadMediaHelper.this.new AsyncTaskC3186w(MediaType.FILE);
                UploadMediaHelper.this.f14606w0.m16891p(UploadMediaHelper.this.f14564b1);
                try {
                    UploadMediaHelper.this.f14606w0.executeOnExecutor(UploadMediaHelper.this.f14559a, "", "").get();
                } catch (Exception e9) {
                    UploadUtils.m16964k("UploadMediaHelperV2", "[startUploadFile]", e9);
                }
                UploadMediaHelper.this.f14606w0 = null;
                UploadUtils.m16965l("Upload Performance", "P2.1 startUploadFile()==> UploadTask(); ======== end");
            }
            if (UploadMediaHelper.this.f14604v0) {
                return;
            }
            if (UploadMediaHelper.this.f14549V != null) {
                UploadMediaHelper uploadMediaHelper2 = UploadMediaHelper.this;
                if (!uploadMediaHelper2.f14594q0) {
                    UploadUtils.m16965l("Upload Performance", "3.2 mCallback.onStep3Complete(); ======== start");
                    UploadMediaHelper.this.f14549V.mo8382e(UploadMediaHelper.this.m16811P0());
                    UploadUtils.m16965l("Upload Performance", "3.2 mCallback.onStep3Complete(); ======== end");
                    UploadUtils.m16965l("Upload Performance", "Trace upload process <<<<<<<<<<<<<<<<<<<<<<<<<<< End");
                } else if (uploadMediaHelper2.f14560a0 != UploadUtils.UploadResultType.STEP_2_SUCCESS) {
                    UploadUtils.m16965l("Upload Performance", "2.3 mCallback.onStep2Complete(); ======== start");
                    UploadMediaHelper.this.f14549V.mo16875d(UploadMediaHelper.this.m16811P0());
                    UploadUtils.m16965l("Upload Performance", "2.3 mCallback.onStep2Complete(); ======== end");
                } else {
                    UploadUtils.m16965l("Upload Performance", "2.3 mCallback.onStep2Complete(); ======== start");
                    UploadMediaHelper.this.f14549V.mo16875d(UploadMediaHelper.this.m16811P0());
                    UploadUtils.m16965l("Upload Performance", "2.3 mCallback.onStep2Complete(); ======== end");
                    UploadMediaHelper.this.f14560a0 = UploadUtils.UploadResultType.STEP_3_SUCCESS;
                    UploadUtils.m16965l("Upload Performance", "3.2 mCallback.onStep3Complete(); ======== start");
                    UploadMediaHelper.this.f14549V.mo8382e(UploadMediaHelper.this.m16811P0());
                    UploadUtils.m16965l("Upload Performance", "3.2 mCallback.onStep3Complete(); ======== end");
                    UploadUtils.m16965l("Upload Performance", "Trace upload process <<<<<<<<<<<<<<<<<<<<<<<<<<< End");
                }
            }
            if (UploadMediaHelper.this.f14560a0 != UploadUtils.UploadResultType.STEP_2_FILE_FAIL) {
                UploadUtils.UploadResultType uploadResultType = UploadUtils.UploadResultType.STEP_1_SUCCESS;
            }
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$r */
    public class C3181r extends Thread {
        public C3181r() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws ExecutionException, InterruptedException {
            UploadUtils.UploadResultType uploadResultType;
            Thread.currentThread().setName("startUploadSmall thread");
            if (UploadMediaHelper.this.f14604v0) {
                return;
            }
            UploadMediaHelper uploadMediaHelper = UploadMediaHelper.this;
            if (uploadMediaHelper.f14602u0 == FailReason.FAIL_SMALL_COMPLETE) {
                if (uploadMediaHelper.f14590o0) {
                    uploadMediaHelper.f14578i0 = 100;
                }
                UploadMediaHelper.this.f14574g0 = 100;
                UploadMediaHelper.this.f14549V.mo16873a(UploadMediaHelper.this.m16811P0());
                UploadMediaHelper.this.m16843f2();
            } else {
                if (uploadMediaHelper.f14590o0) {
                    uploadMediaHelper.f14578i0 = 100;
                    UploadMediaHelper.this.f14549V.mo16873a(UploadMediaHelper.this.m16811P0());
                }
                UploadUtils.m16965l("Upload Performance", "P2.2 startUploadSmall()==> UploadTask(); ======== start");
                UploadMediaHelper.this.f14606w0 = UploadMediaHelper.this.new AsyncTaskC3186w(MediaType.SMALL_IMG);
                UploadMediaHelper.this.f14606w0.m16891p(UploadMediaHelper.this.f14564b1);
                try {
                    if (UploadMediaHelper.this.f14606w0 != null) {
                        AsyncTaskC3186w asyncTaskC3186w = UploadMediaHelper.this.f14606w0;
                        ExecutorService executorService = UploadMediaHelper.this.f14559a;
                        UploadMediaHelper uploadMediaHelper2 = UploadMediaHelper.this;
                        asyncTaskC3186w.executeOnExecutor(executorService, uploadMediaHelper2.f14573g, uploadMediaHelper2.f14575h).get();
                    }
                } catch (Exception e9) {
                    UploadUtils.m16964k("UploadMediaHelperV2", "[startUploadSmall]", e9);
                }
                UploadMediaHelper.this.f14606w0 = null;
                UploadUtils.m16965l("Upload Performance", "P2.2 startUploadSmall()==> UploadTask(); ======== end");
            }
            if (UploadMediaHelper.this.f14604v0) {
                return;
            }
            if (UploadMediaHelper.this.f14549V != null) {
                UploadMediaHelper uploadMediaHelper3 = UploadMediaHelper.this;
                if (uploadMediaHelper3.f14594q0) {
                    UploadUtils.m16965l("Upload Performance", "2.3 mCallback.onStep2Complete(); ======== start");
                    UploadUtils.UploadResultType uploadResultType2 = UploadMediaHelper.this.f14553X;
                    UploadUtils.UploadResultType uploadResultType3 = UploadUtils.UploadResultType.STEP_2_SUCCESS;
                    if (uploadResultType2 != uploadResultType3 && uploadResultType2 != (uploadResultType = UploadUtils.UploadResultType.STEP_2_SMALL_FAIL)) {
                        UploadUtils.m16965l("Upload Performance", "  > Incorrect status: " + UploadMediaHelper.this.f14553X);
                        UploadMediaHelper.this.f14553X = uploadResultType;
                    }
                    UploadMediaHelper.this.f14549V.mo16875d(UploadMediaHelper.this.m16811P0());
                    synchronized (UploadMediaHelper.this.f14546T0) {
                        UploadMediaHelper uploadMediaHelper4 = UploadMediaHelper.this;
                        if (uploadMediaHelper4.f14553X == uploadResultType3) {
                            uploadMediaHelper4.f14550V0 = true;
                            UploadMediaHelper.this.f14546T0.notifyAll();
                        }
                    }
                    UploadUtils.m16965l("Upload Performance", "2.3 mCallback.onStep2Complete(); ======== end");
                } else if (uploadMediaHelper3.f14553X != UploadUtils.UploadResultType.STEP_3_SUCCESS) {
                    UploadUtils.m16965l("Upload Performance", "3.2 mCallback.onStep3Complete(); ======== start");
                    UploadMediaHelper.this.m16787B0();
                    UploadMediaHelper.this.f14549V.mo8382e(UploadMediaHelper.this.m16811P0());
                    UploadUtils.m16965l("Upload Performance", "3.2 mCallback.onStep3Complete(); ======== end");
                    UploadUtils.m16965l("Upload Performance", "Trace upload process <<<<<<<<<<<<<<<<<<<<<<<<<<< End");
                }
            }
            UploadMediaHelper uploadMediaHelper5 = UploadMediaHelper.this;
            UploadUtils.UploadResultType uploadResultType4 = uploadMediaHelper5.f14553X;
            if (uploadResultType4 == UploadUtils.UploadResultType.STEP_2_SMALL_FAIL || uploadResultType4 == UploadUtils.UploadResultType.STEP_3_SMALL_FAIL) {
                return;
            }
            boolean z8 = uploadMediaHelper5.f14604v0;
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$s */
    public class C3182s extends Thread {
        public C3182s() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws ExecutionException, InterruptedException {
            Thread.currentThread().setName("startUploadBig thread");
            if (UploadMediaHelper.this.f14604v0) {
                return;
            }
            UploadMediaHelper uploadMediaHelper = UploadMediaHelper.this;
            FailReason failReason = uploadMediaHelper.f14602u0;
            if (((failReason == FailReason.FAIL_BIG_COMPLETE) || (failReason == FailReason.FAIL_BIG_UPDATE)) || uploadMediaHelper.f14598s0) {
                if (uploadMediaHelper.f14590o0) {
                    uploadMediaHelper.f14578i0 = 100;
                }
                UploadMediaHelper.this.f14576h0 = 100;
                UploadMediaHelper.this.f14549V.mo16873a(UploadMediaHelper.this.m16811P0());
                UploadMediaHelper.this.mo16835b2();
            } else {
                if (uploadMediaHelper.f14590o0) {
                    uploadMediaHelper.f14578i0 = 100;
                }
                UploadMediaHelper.this.f14549V.mo16873a(UploadMediaHelper.this.m16811P0());
                UploadUtils.m16965l("Upload Performance", "P2.3 startUploadBig()==> UploadTask(); ======== start");
                UploadMediaHelper.this.f14544S0 = UploadMediaHelper.this.new AsyncTaskC3186w(MediaType.BIG_IMG);
                UploadMediaHelper.this.f14544S0.m16891p(UploadMediaHelper.this.f14564b1);
                try {
                    AsyncTaskC3186w asyncTaskC3186w = UploadMediaHelper.this.f14544S0;
                    ExecutorService executorService = UploadMediaHelper.this.f14559a;
                    UploadMediaHelper uploadMediaHelper2 = UploadMediaHelper.this;
                    asyncTaskC3186w.executeOnExecutor(executorService, uploadMediaHelper2.f14573g, uploadMediaHelper2.f14575h).get();
                } catch (Exception e9) {
                    UploadUtils.m16964k("UploadMediaHelperV2", "[startUploadBig]", e9);
                }
                UploadMediaHelper.this.f14544S0 = null;
                UploadUtils.m16965l("Upload Performance", "P2.3 startUploadBig()==> UploadTask(); ======== end");
            }
            if (UploadMediaHelper.this.f14604v0 || UploadMediaHelper.this.f14549V == null) {
                return;
            }
            UploadUtils.m16965l("Upload Performance", "3.2 mCallback.onStep3Complete(); ======== start");
            UploadMediaHelper.this.m16787B0();
            UploadMediaHelper.this.f14549V.mo8382e(UploadMediaHelper.this.m16811P0());
            UploadUtils.m16965l("Upload Performance", "3.2 mCallback.onStep3Complete(); ======== end");
            UploadUtils.m16965l("Upload Performance", "Trace upload process <<<<<<<<<<<<<<<<<<<<<<<<<<< End");
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$t */
    public interface InterfaceC3183t {
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$v */
    public static abstract class AbstractC3185v {
        /* renamed from: a */
        public void mo16873a(UploadMediaHelper uploadMediaHelper) {
        }

        /* renamed from: b */
        public void mo16874b() {
        }

        /* renamed from: c */
        public abstract void mo8381c(UploadMediaHelper uploadMediaHelper);

        /* renamed from: d */
        public void mo16875d(UploadMediaHelper uploadMediaHelper) {
        }

        /* renamed from: e */
        public abstract void mo8382e(UploadMediaHelper uploadMediaHelper);
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$w */
    public class AsyncTaskC3186w extends AsyncTask<String, Integer, Boolean> {

        /* renamed from: a */
        public MediaType f14656a;

        /* renamed from: b */
        public byte[] f14657b = null;

        /* renamed from: c */
        public String f14658c = null;

        /* renamed from: d */
        public Uri f14659d = null;

        /* renamed from: e */
        public long f14660e = 0;

        /* renamed from: f */
        public HttpURLConnection f14661f = null;

        /* renamed from: g */
        public DataOutputStream f14662g = null;

        /* renamed from: h */
        public String f14663h = null;

        /* renamed from: i */
        public InterfaceC3187x f14664i = null;

        /* renamed from: j */
        public String f14665j = ULogUtility.m16677m();

        /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$w$a */
        public class a implements Runnable {

            /* renamed from: b */
            public UploadUtils.C3196b.a f14667b;

            public a(UploadUtils.C3196b.a aVar) {
                this.f14667b = aVar;
            }

            /* JADX WARN: Removed duplicated region for block: B:58:0x01b5  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void run() throws Throwable {
                InputStream inputStreamOpenInputStream;
                boolean z8;
                boolean z9;
                boolean z10;
                UploadUtils.C3196b.a aVar;
                UploadUtils.m16965l("Upload Performance", "U.2 upload data thread ======== start");
                if (this.f14667b == null) {
                    UploadUtils.m16965l("Upload Performance", "U.2 mTaskStatus = null, return immediate.");
                    return;
                }
                UploadUtils.m16965l("Upload Performance", "Running task index = " + this.f14667b.f14738b);
                UploadMediaHelper uploadMediaHelper = UploadMediaHelper.this;
                UploadUtils.C3196b.a aVar2 = this.f14667b;
                boolean zM16864y0 = uploadMediaHelper.m16864y0(aVar2.f14737a, aVar2.f14738b);
                if (this.f14667b.f14743g == null || !zM16864y0) {
                    UploadUtils.m16965l("Upload Performance", "Error!! buildSinglePartConnection failed");
                    return;
                }
                try {
                    UploadUtils.m16965l("Upload Performance", "U.2 upload data thread ======== start uploading");
                    inputStreamOpenInputStream = AsyncTaskC3186w.this.f14659d != null ? Globals.m7372O().getContentResolver().openInputStream(AsyncTaskC3186w.this.f14659d) : new FileInputStream(AsyncTaskC3186w.this.f14658c);
                    try {
                        try {
                            inputStreamOpenInputStream.skip(this.f14667b.f14741e);
                            byte[] bArr = new byte[(int) 10240];
                            while (true) {
                                long jM16974a = this.f14667b.m16974a();
                                UploadUtils.C3196b.a aVar3 = this.f14667b;
                                if (jM16974a >= aVar3.f14740d) {
                                    break;
                                }
                                long jM16974a2 = this.f14667b.f14740d - aVar3.m16974a();
                                if (jM16974a2 > 10240) {
                                    jM16974a2 = 10240;
                                }
                                if (UploadMediaHelper.this.f14604v0) {
                                    break;
                                }
                                int i9 = inputStreamOpenInputStream.read(bArr);
                                if (i9 == -1) {
                                    Log.e("UploadMediaHelperV2", "inputStream.read() read fail");
                                    break;
                                }
                                if (i9 != jM16974a2) {
                                    Log.w("UploadMediaHelperV2", "write: " + i9 + " vs " + jM16974a2);
                                }
                                if (UploadMediaHelper.this.f14604v0) {
                                    break;
                                }
                                z8 = false;
                                try {
                                    this.f14667b.f14743g.write(bArr, 0, (int) jM16974a2);
                                    UploadUtils.C3196b.a aVar4 = this.f14667b;
                                    aVar4.m16976c(aVar4.m16974a() + jM16974a2);
                                } catch (Exception e9) {
                                    e = e9;
                                    UploadUtils.m16964k("UploadMediaHelperV2", "[UploadDataRunnable]", e);
                                    UploadUtils.m16965l("Upload Performance", "U.2 upload data ======== finally: task(" + this.f14667b.f14738b + ")");
                                    C6370g.m24480b(inputStreamOpenInputStream);
                                    C6370g.m24480b(this.f14667b.f14743g);
                                    UploadUtils.C3196b.a aVar5 = this.f14667b;
                                    aVar5.f14743g = null;
                                    if (aVar5.f14742f != null) {
                                        UploadUtils.m16965l("Upload Performance", "U.2 upload data ======== connection.disconnect()");
                                        this.f14667b.f14742f.disconnect();
                                        this.f14667b.f14742f = null;
                                    }
                                    UploadUtils.m16965l("Upload Performance", "U.2 upload data ======== end: task(" + this.f14667b.f14738b + ")");
                                    z9 = z8;
                                    UploadUtils.C3196b.a aVar6 = this.f14667b;
                                    aVar6.f14744h = z9;
                                    aVar6.f14745i = true;
                                }
                            }
                        } catch (Exception e10) {
                            e = e10;
                            z8 = false;
                        }
                    } catch (Throwable th) {
                        th = th;
                        UploadUtils.m16965l("Upload Performance", "U.2 upload data ======== finally: task(" + this.f14667b.f14738b + ")");
                        C6370g.m24480b(inputStreamOpenInputStream);
                        C6370g.m24480b(this.f14667b.f14743g);
                        UploadUtils.C3196b.a aVar7 = this.f14667b;
                        aVar7.f14743g = null;
                        if (aVar7.f14742f != null) {
                            UploadUtils.m16965l("Upload Performance", "U.2 upload data ======== connection.disconnect()");
                            this.f14667b.f14742f.disconnect();
                            this.f14667b.f14742f = null;
                        }
                        UploadUtils.m16965l("Upload Performance", "U.2 upload data ======== end: task(" + this.f14667b.f14738b + ")");
                        throw th;
                    }
                } catch (Exception e11) {
                    e = e11;
                    z8 = false;
                    inputStreamOpenInputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    inputStreamOpenInputStream = null;
                }
                if (UploadMediaHelper.this.f14604v0) {
                    z10 = false;
                    UploadUtils.m16965l("UploadMediaHelperV2", "[UploadDataRunnable]: ==== abort !!!! task(" + this.f14667b.f14738b + ")");
                } else {
                    this.f14667b.f14743g.flush();
                    HttpURLConnection httpURLConnection = this.f14667b.f14742f;
                    if (httpURLConnection != null && httpURLConnection.getResponseCode() == 200) {
                        List<String> list = this.f14667b.f14742f.getHeaderFields().get(HttpHeaders.ETAG);
                        if (!list.isEmpty()) {
                            if (Globals.m7388i0().m7550g2()) {
                                AsyncTaskC3186w.this.f14663h = "";
                            } else {
                                AsyncTaskC3186w.this.f14663h = list.get(0).replaceAll("\"", "");
                            }
                            UploadUtils.m16965l("UploadMediaHelperV2", "[UploadDataRunnable]: ==== done !!!! success: task(" + this.f14667b.f14738b + ")");
                            z9 = true;
                            UploadUtils.m16965l("Upload Performance", "U.2 upload data ======== finally: task(" + this.f14667b.f14738b + ")");
                            C6370g.m24480b(inputStreamOpenInputStream);
                            C6370g.m24480b(this.f14667b.f14743g);
                            aVar = this.f14667b;
                            aVar.f14743g = null;
                            if (aVar.f14742f != null) {
                                UploadUtils.m16965l("Upload Performance", "U.2 upload data ======== connection.disconnect()");
                                this.f14667b.f14742f.disconnect();
                                this.f14667b.f14742f = null;
                            }
                            UploadUtils.m16965l("Upload Performance", "U.2 upload data ======== end: task(" + this.f14667b.f14738b + ")");
                            UploadUtils.C3196b.a aVar62 = this.f14667b;
                            aVar62.f14744h = z9;
                            aVar62.f14745i = true;
                        }
                    }
                    z10 = false;
                }
                z9 = z10;
                UploadUtils.m16965l("Upload Performance", "U.2 upload data ======== finally: task(" + this.f14667b.f14738b + ")");
                C6370g.m24480b(inputStreamOpenInputStream);
                C6370g.m24480b(this.f14667b.f14743g);
                aVar = this.f14667b;
                aVar.f14743g = null;
                if (aVar.f14742f != null) {
                }
                UploadUtils.m16965l("Upload Performance", "U.2 upload data ======== end: task(" + this.f14667b.f14738b + ")");
                UploadUtils.C3196b.a aVar622 = this.f14667b;
                aVar622.f14744h = z9;
                aVar622.f14745i = true;
            }
        }

        public AsyncTaskC3186w(MediaType mediaType) {
            this.f14656a = mediaType;
        }

        /* renamed from: a */
        public void m16882a() {
            try {
                HttpURLConnection httpURLConnection = this.f14661f;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                    this.f14661f = null;
                }
                int i9 = C3173j.f14642a[this.f14656a.ordinal()];
                if (i9 == 1) {
                    UploadMediaHelper.this.f14579j.m16972g();
                } else {
                    if (i9 != 2) {
                        return;
                    }
                    UploadMediaHelper.this.f14581k.m16972g();
                }
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        }

        /* renamed from: h */
        public final int m16883h() {
            long j9;
            int i9 = C3173j.f14642a[this.f14656a.ordinal()];
            int i10 = 1;
            if (i9 == 1) {
                long j10 = 0;
                long jM16974a = 0;
                while (i10 <= UploadMediaHelper.this.f14579j.m16971f()) {
                    UploadUtils.C3196b.a aVarM16968c = UploadMediaHelper.this.f14579j.m16968c(i10);
                    if (aVarM16968c != null) {
                        jM16974a += aVarM16968c.m16974a();
                        j10 += aVarM16968c.f14740d;
                    }
                    i10++;
                }
                if (j10 > 0) {
                    j9 = (jM16974a * 100) / j10;
                    return (int) j9;
                }
                return 0;
            }
            if (i9 == 2) {
                long j11 = 0;
                long jM16974a2 = 0;
                while (i10 <= UploadMediaHelper.this.f14581k.m16971f()) {
                    UploadUtils.C3196b.a aVarM16968c2 = UploadMediaHelper.this.f14581k.m16968c(i10);
                    if (aVarM16968c2 != null) {
                        jM16974a2 += aVarM16968c2.m16974a();
                        j11 += aVarM16968c2.f14740d;
                    }
                    i10++;
                }
                if (j11 > 0) {
                    j9 = (jM16974a2 * 100) / j11;
                    return (int) j9;
                }
            }
            return 0;
        }

        /* renamed from: i */
        public final boolean m16884i() {
            UploadUtils.C3196b c3196b;
            MediaType mediaType = this.f14656a;
            if (mediaType == MediaType.VIDEO) {
                UploadUtils.C3196b c3196b2 = UploadMediaHelper.this.f14579j;
                if (c3196b2 != null) {
                    return c3196b2.f14733a;
                }
            } else if (mediaType == MediaType.FILE && (c3196b = UploadMediaHelper.this.f14581k) != null) {
                return c3196b.f14733a;
            }
            return false;
        }

        @Override // android.os.AsyncTask
        /* renamed from: j, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(String... strArr) throws Throwable {
            byte[] bArr;
            boolean zM16863x0;
            boolean zM16863x02;
            UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== start mediaType = " + this.f14656a);
            boolean zM16886k = false;
            switch (C3173j.f14642a[this.f14656a.ordinal()]) {
                case 1:
                    try {
                        UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfPrepareVideoSync.acquire(1) + release(1) ==== enter");
                        UploadMediaHelper.this.f14524I0.acquire(1);
                        UploadMediaHelper.this.f14524I0.release(1);
                        UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfPrepareVideoSync.acquire(1) + release(1) ==== leave");
                        if (UploadMediaHelper.this.f14532M0 == null || UploadMediaHelper.this.f14542R0 > 0) {
                            UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== build connection (Video) !!!");
                            if (UploadMediaHelper.this.f14542R0 > 0) {
                                UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== fail count =" + UploadMediaHelper.this.f14542R0);
                            }
                            UploadUtils.C3196b c3196b = UploadMediaHelper.this.f14579j;
                            if (c3196b.f14733a) {
                                zM16863x02 = true;
                            } else {
                                UploadUtils.C3196b.a aVarM16968c = c3196b.m16968c(1);
                                zM16863x02 = aVarM16968c != null ? UploadMediaHelper.this.m16863x0(aVarM16968c.f14739c, MediaType.VIDEO) : false;
                            }
                            if (!zM16863x02) {
                                UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== build connection (video) !!! === Fail ###");
                                return Boolean.valueOf(m16889n(false));
                            }
                        }
                        UploadMediaHelper uploadMediaHelper = UploadMediaHelper.this;
                        if (uploadMediaHelper.f14579j.f14733a) {
                            this.f14657b = uploadMediaHelper.f14569e.f14726e;
                        } else {
                            UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfConnVideoSync.acquire(1) + release(1) ==== enter");
                            if (UploadMediaHelper.this.f14532M0 != null) {
                                UploadMediaHelper.this.f14532M0.acquire(1);
                                UploadMediaHelper.this.f14532M0.release(1);
                            }
                            UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfConnVideoSync.acquire(1) + release(1) ==== leave");
                            this.f14661f = UploadMediaHelper.this.f14612z0;
                            this.f14662g = UploadMediaHelper.this.f14508A0;
                            this.f14657b = UploadMediaHelper.this.f14569e.f14726e;
                        }
                        UploadUtils.C3195a c3195a = UploadMediaHelper.this.f14569e;
                        this.f14658c = c3195a.f14728g;
                        this.f14659d = c3195a.f14729h;
                        this.f14660e = c3195a.f14730i;
                        break;
                    } catch (Exception e9) {
                        UploadUtils.m16964k("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfPrepareVideoSync or smfConnVideoSync .acquire(1) + release(1) fail", e9);
                        return Boolean.valueOf(m16889n(false));
                    }
                case 2:
                    try {
                        UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfPrepareFileSync.acquire(1) + release(1) ==== enter");
                        UploadMediaHelper.this.f14528K0.acquire(1);
                        UploadMediaHelper.this.f14528K0.release(1);
                        UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfPrepareFileSync.acquire(1) + release(1) ==== leave");
                        if (UploadMediaHelper.this.f14540Q0 == null || UploadMediaHelper.this.f14542R0 > 0) {
                            UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== build connection (File) !!!");
                            if (UploadMediaHelper.this.f14542R0 > 0) {
                                UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== fail count =" + UploadMediaHelper.this.f14542R0);
                            }
                            UploadUtils.C3196b c3196b2 = UploadMediaHelper.this.f14581k;
                            if (c3196b2.f14733a) {
                                zM16863x0 = true;
                            } else {
                                UploadUtils.C3196b.a aVarM16968c2 = c3196b2.m16968c(1);
                                zM16863x0 = aVarM16968c2 != null ? UploadMediaHelper.this.m16863x0(aVarM16968c2.f14739c, MediaType.FILE) : false;
                            }
                            if (!zM16863x0) {
                                UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== build connection (file) !!! === Fail ###");
                                return Boolean.valueOf(m16889n(false));
                            }
                        }
                        UploadMediaHelper uploadMediaHelper2 = UploadMediaHelper.this;
                        if (uploadMediaHelper2.f14581k.f14733a) {
                            this.f14657b = uploadMediaHelper2.f14571f.f14726e;
                        } else {
                            UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfConnFileSync.acquire(1) + release(1) ==== enter");
                            if (UploadMediaHelper.this.f14540Q0 != null) {
                                UploadMediaHelper.this.f14540Q0.acquire(1);
                                UploadMediaHelper.this.f14540Q0.release(1);
                            }
                            UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfConnFileSync.acquire(1) + release(1) ==== leave");
                            this.f14661f = UploadMediaHelper.this.f14518F0;
                            this.f14662g = UploadMediaHelper.this.f14520G0;
                            this.f14657b = UploadMediaHelper.this.f14571f.f14726e;
                        }
                        UploadUtils.C3195a c3195a2 = UploadMediaHelper.this.f14571f;
                        this.f14658c = c3195a2.f14728g;
                        this.f14659d = c3195a2.f14729h;
                        this.f14660e = c3195a2.f14730i;
                        break;
                    } catch (Exception e10) {
                        UploadUtils.m16964k("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfPrepareFileSync or smfConnFileSync .acquire(1) + release(1) fail", e10);
                        return Boolean.valueOf(m16889n(false));
                    }
                    break;
                case 3:
                    try {
                        UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfPreparePhotoSync.acquire(1) + release(1) (big) ==== enter");
                        UploadMediaHelper.this.f14526J0.acquire(1);
                        UploadMediaHelper.this.f14526J0.release(1);
                        UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfPreparePhotoSync.acquire(1) + release(1) (big) ==== leave");
                        if (UploadMediaHelper.this.f14536O0 == null || UploadMediaHelper.this.f14542R0 > 0) {
                            UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== build connection (big) !!!");
                            if (UploadMediaHelper.this.f14542R0 > 0) {
                                UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.UploadTask()][" + this.f14665j + "]: ==== fail count =" + UploadMediaHelper.this.f14542R0);
                            }
                            UploadMediaHelper uploadMediaHelper3 = UploadMediaHelper.this;
                            if (!uploadMediaHelper3.m16863x0(uploadMediaHelper3.f14575h, MediaType.BIG_IMG)) {
                                UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== build connection (big) !!! === Fail ###");
                                return Boolean.valueOf(m16889n(false));
                            }
                        }
                        UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfConnBigSync.acquire(1) + release(1) ==== enter");
                        UploadMediaHelper.this.f14536O0.acquire(1);
                        UploadMediaHelper.this.f14536O0.release(1);
                        UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfConnBigSync.acquire(1) + release(1) ==== leave");
                        this.f14661f = UploadMediaHelper.this.f14514D0;
                        this.f14662g = UploadMediaHelper.this.f14516E0;
                        UploadUtils.C3195a c3195a3 = UploadMediaHelper.this.f14565c;
                        if (c3195a3 != null && (bArr = c3195a3.f14726e) != null) {
                            this.f14657b = bArr;
                            break;
                        }
                    } catch (InterruptedException e11) {
                        UploadUtils.m16964k("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfPreparePhotoSync or smfConnBigSync .acquire(1) + release(1) (big) ==== fail", e11);
                        return Boolean.valueOf(m16889n(false));
                    }
                    break;
                case 4:
                    try {
                        UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfPreparePhotoSync.acquire(1) + release(1) (small) ==== enter");
                        UploadMediaHelper.this.f14526J0.acquire(1);
                        UploadMediaHelper.this.f14526J0.release(1);
                        UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfPreparePhotoSync.acquire(1) + release(1) (small) ==== leave");
                        if (UploadMediaHelper.this.f14534N0 == null || UploadMediaHelper.this.f14542R0 > 0) {
                            UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== build connection (small) !!!");
                            if (UploadMediaHelper.this.f14542R0 > 0) {
                                UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== fail count =" + UploadMediaHelper.this.f14542R0);
                            }
                            UploadMediaHelper uploadMediaHelper4 = UploadMediaHelper.this;
                            if (!uploadMediaHelper4.m16863x0(uploadMediaHelper4.f14573g, MediaType.SMALL_IMG)) {
                                UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== build connection (small) !!! === Fail ###");
                                return Boolean.valueOf(m16889n(false));
                            }
                        }
                        UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfConnSmallSync.acquire(1) + release(1) ==== enter");
                        UploadMediaHelper.this.f14534N0.acquire(1);
                        UploadMediaHelper.this.f14534N0.release(1);
                        UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfConnSmallSync.acquire(1) + release(1) ==== leave");
                        this.f14661f = UploadMediaHelper.this.f14510B0;
                        this.f14662g = UploadMediaHelper.this.f14512C0;
                        if (UploadMediaHelper.this.f14562b != null && UploadMediaHelper.this.f14562b.f14726e != null) {
                            this.f14657b = UploadMediaHelper.this.f14562b.f14726e;
                            break;
                        }
                    } catch (InterruptedException e12) {
                        UploadUtils.m16964k("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfPreparePhotoSync or smfConnSmallSync .acquire(1) + release(1) (small) ==== fail", e12);
                        return Boolean.valueOf(m16889n(false));
                    }
                    break;
                case 5:
                    try {
                        UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfPrepareVoiceSync.acquire(1) + release(1) ==== enter");
                        UploadMediaHelper.this.f14522H0.acquire(1);
                        UploadMediaHelper.this.f14522H0.release(1);
                        UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfPrepareVoiceSync.acquire(1) + release(1) ==== leave");
                        if (UploadMediaHelper.this.f14530L0 == null || UploadMediaHelper.this.f14542R0 > 0) {
                            UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== build connection (voice) !!!");
                            if (UploadMediaHelper.this.f14542R0 > 0) {
                                UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground]: ==== fail count =" + UploadMediaHelper.this.f14542R0);
                            }
                            UploadMediaHelper uploadMediaHelper5 = UploadMediaHelper.this;
                            if (!uploadMediaHelper5.m16863x0(uploadMediaHelper5.f14577i, MediaType.VOICE)) {
                                UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== build connection (voice) !!! === Fail ###");
                                return Boolean.valueOf(m16889n(false));
                            }
                        }
                        if (UploadMediaHelper.this.f14567d != null && UploadMediaHelper.this.f14567d.f14726e != null) {
                            UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfConnVoiceSync.acquire(1) + release(1) ==== enter");
                            UploadMediaHelper.this.f14530L0.acquire(1);
                            UploadMediaHelper.this.f14530L0.release(1);
                            UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfConnVoiceSync.acquire(1) + release(1) ==== leave");
                            this.f14661f = UploadMediaHelper.this.f14608x0;
                            this.f14662g = UploadMediaHelper.this.f14610y0;
                            this.f14657b = UploadMediaHelper.this.f14567d.f14726e;
                            break;
                        }
                        UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== byteArr in VoiceUploadMedia is null");
                        return Boolean.valueOf(m16889n(false));
                    } catch (InterruptedException e13) {
                        UploadUtils.m16964k("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfPrepareVoiceSync or smfConnVoiceSync .acquire(1) + release(1) ==== fail", e13);
                        return Boolean.valueOf(m16889n(false));
                    }
                case 6:
                    try {
                        UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfPrepareVideoSync.acquire(1) + release(1) ==== enter");
                        UploadMediaHelper.this.f14524I0.acquire(1);
                        UploadMediaHelper.this.f14524I0.release(1);
                        UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfPrepareVideoSync.acquire(1) + release(1) ==== leave");
                        if (UploadMediaHelper.this.f14538P0 == null || UploadMediaHelper.this.f14542R0 > 0) {
                            UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== build connection (small) !!!");
                            if (UploadMediaHelper.this.f14542R0 > 0) {
                                UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== fail count =" + UploadMediaHelper.this.f14542R0);
                            }
                            UploadMediaHelper uploadMediaHelper6 = UploadMediaHelper.this;
                            if (!uploadMediaHelper6.m16863x0(uploadMediaHelper6.f14573g, MediaType.VIDEO_IMG)) {
                                UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== build connection (small) !!! === Fail ###");
                                return Boolean.valueOf(m16889n(false));
                            }
                        }
                        UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfConnVideoSync.acquire(1) + release(1) ==== enter");
                        UploadMediaHelper.this.f14538P0.acquire(1);
                        UploadMediaHelper.this.f14538P0.release(1);
                        UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfConnVideoSync.acquire(1) + release(1) ==== leave");
                        this.f14661f = UploadMediaHelper.this.f14510B0;
                        this.f14662g = UploadMediaHelper.this.f14512C0;
                        this.f14657b = UploadMediaHelper.this.f14562b.f14726e;
                        break;
                    } catch (Exception e14) {
                        UploadUtils.m16964k("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== smfPrepareVideoSync or smfConnVideoSync .acquire(1) + release(1) ==== fail", e14);
                        return Boolean.valueOf(m16889n(false));
                    }
                    break;
                default:
                    UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: MediaType unknown ==== return fail");
                    return Boolean.valueOf(m16889n(false));
            }
            UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== start upload");
            if (m16884i()) {
                zM16886k = m16887l();
            } else if (this.f14662g == null) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: mOut == null, leave loop");
            } else {
                zM16886k = m16886k();
            }
            m16889n(zM16886k);
            return Boolean.valueOf(zM16886k);
        }

        /* JADX WARN: Code restructure failed: missing block: B:77:0x0229, code lost:
        
            com.cyberlink.you.utility.ULogUtility.m16676l("UploadMediaHelperV2", r15 + r35.f14665j + "]inputStream.read() read fail");
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:100:0x02e3 A[Catch: Exception -> 0x0321, TryCatch #33 {Exception -> 0x0321, blocks: (B:98:0x02df, B:100:0x02e3, B:101:0x02e6, B:103:0x0301), top: B:373:0x02df }] */
        /* JADX WARN: Removed duplicated region for block: B:103:0x0301 A[Catch: Exception -> 0x0321, TRY_LEAVE, TryCatch #33 {Exception -> 0x0321, blocks: (B:98:0x02df, B:100:0x02e3, B:101:0x02e6, B:103:0x0301), top: B:373:0x02df }] */
        /* JADX WARN: Removed duplicated region for block: B:105:0x0306  */
        /* JADX WARN: Removed duplicated region for block: B:115:0x033e  */
        /* JADX WARN: Removed duplicated region for block: B:118:0x037a  */
        /* JADX WARN: Removed duplicated region for block: B:197:0x0584 A[Catch: Exception -> 0x05c2, TryCatch #47 {Exception -> 0x05c2, blocks: (B:195:0x0580, B:197:0x0584, B:198:0x0587, B:200:0x05a2), top: B:384:0x0580 }] */
        /* JADX WARN: Removed duplicated region for block: B:200:0x05a2 A[Catch: Exception -> 0x05c2, TRY_LEAVE, TryCatch #47 {Exception -> 0x05c2, blocks: (B:195:0x0580, B:197:0x0584, B:198:0x0587, B:200:0x05a2), top: B:384:0x0580 }] */
        /* JADX WARN: Removed duplicated region for block: B:202:0x05a7  */
        /* JADX WARN: Removed duplicated region for block: B:212:0x05df  */
        /* JADX WARN: Removed duplicated region for block: B:215:0x0617  */
        /* JADX WARN: Removed duplicated region for block: B:265:0x0740 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:280:0x0780 A[Catch: Exception -> 0x07c1, TryCatch #48 {Exception -> 0x07c1, blocks: (B:278:0x077c, B:280:0x0780, B:281:0x0783, B:283:0x079e), top: B:386:0x077c }] */
        /* JADX WARN: Removed duplicated region for block: B:283:0x079e A[Catch: Exception -> 0x07c1, TRY_LEAVE, TryCatch #48 {Exception -> 0x07c1, blocks: (B:278:0x077c, B:280:0x0780, B:281:0x0783, B:283:0x079e), top: B:386:0x077c }] */
        /* JADX WARN: Removed duplicated region for block: B:285:0x07a5  */
        /* JADX WARN: Removed duplicated region for block: B:295:0x07e1  */
        /* JADX WARN: Removed duplicated region for block: B:298:0x0819  */
        /* JADX WARN: Removed duplicated region for block: B:390:0x0867 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:395:0x066a A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:401:0x03d3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r0v63, types: [java.lang.StringBuilder] */
        /* JADX WARN: Type inference failed for: r2v41 */
        /* JADX WARN: Type inference failed for: r2v42, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v43 */
        /* JADX WARN: Type inference failed for: r2v44 */
        /* JADX WARN: Type inference failed for: r2v45 */
        /* JADX WARN: Type inference failed for: r2v46 */
        /* JADX WARN: Type inference failed for: r2v47 */
        /* JADX WARN: Type inference failed for: r2v48, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v49 */
        /* JADX WARN: Type inference failed for: r2v50 */
        /* JADX WARN: Type inference failed for: r2v51, types: [java.io.InputStream] */
        /* JADX WARN: Type inference failed for: r2v52 */
        /* JADX WARN: Type inference failed for: r2v53, types: [java.io.DataOutputStream, java.net.HttpURLConnection] */
        /* JADX WARN: Type inference failed for: r2v55 */
        /* JADX WARN: Type inference failed for: r5v28, types: [int] */
        /* JADX WARN: Type inference failed for: r5v29, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r5v30 */
        /* JADX WARN: Type inference failed for: r5v94 */
        /* JADX WARN: Type inference failed for: r5v95 */
        /* renamed from: k */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final boolean m16886k() throws Throwable {
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            String str8;
            String str9;
            String str10;
            String str11;
            String str12;
            String str13;
            String str14;
            boolean z8;
            String str15;
            Throwable th;
            int responseCode;
            int i9;
            HttpURLConnection httpURLConnection;
            InputStream errorStream;
            BufferedReader bufferedReader;
            InputStream inputStream;
            BufferedReader bufferedReader2;
            List<String> list;
            DataOutputStream dataOutputStream;
            boolean z9;
            String str16;
            String str17;
            boolean z10;
            int responseCode2;
            ?? r52;
            HttpURLConnection httpURLConnection2;
            BufferedReader bufferedReader3;
            ?? r22;
            boolean z11;
            boolean z12;
            List<String> list2;
            DataOutputStream dataOutputStream2;
            long j9;
            String str18;
            String str19;
            String str20;
            String str21;
            String str22;
            InputStream inputStream2;
            InputStream inputStreamOpenInputStream;
            String str23;
            Integer[] numArr;
            String str24;
            int responseCode3;
            HttpURLConnection httpURLConnection3;
            InputStream errorStream2;
            BufferedReader bufferedReader4;
            List<String> list3;
            DataOutputStream dataOutputStream3;
            String str25 = HttpHeaders.ETAG;
            String str26 = "]: ==== done #### responseCode = ";
            String str27 = "] ======== mConn.disconnect()";
            String str28 = "";
            StringBuilder sb = new StringBuilder();
            String str29 = "[";
            sb.append("[");
            sb.append(this.f14665j);
            sb.append("]U.2 do_upload(); ======== start");
            String str30 = "Upload Performance";
            UploadUtils.m16965l("Upload Performance", sb.toString());
            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "]: ==== start");
            if (this.f14662g == null) {
                return false;
            }
            try {
                UploadUtils.m16965l("Upload Performance", "[do_upload][" + this.f14665j + "]U.2 ======== start");
                j9 = 0;
            } catch (Exception e9) {
                e = e9;
                str11 = "Upload Performance";
                str12 = "]U.2 upload data ======== end";
                str13 = "]: ==== exception: ";
                str14 = "]: ==== errorStream is null";
                z8 = true;
                str15 = "[";
                str = "\"";
                str2 = "] ======== mConn.disconnect()";
                str3 = "]: ==== done !!!! success";
            } catch (Throwable th2) {
                th = th2;
                str = "\"";
                str2 = "] ======== mConn.disconnect()";
                str3 = "]: ==== done !!!! success";
                str4 = HttpHeaders.ETAG;
                str5 = "]: ==== done #### responseCode = ";
                str6 = "]: ==== errorStream is null";
                str7 = "]U.2 upload data ======== end";
                str8 = "]: ==== exception: ";
                str9 = "[";
                str10 = "Upload Performance";
            }
            if (this.f14657b != null) {
                long j10 = 0;
                while (true) {
                    try {
                        byte[] bArr = this.f14657b;
                        str18 = str30;
                        try {
                            str19 = str25;
                            if (j10 >= bArr.length) {
                                break;
                            }
                            try {
                                long length = bArr.length - j10;
                                if (length > 10240) {
                                    length = 10240;
                                }
                                if (UploadMediaHelper.this.f14604v0) {
                                    break;
                                }
                                String str31 = str28;
                                try {
                                    String str32 = str26;
                                    String str33 = str27;
                                    try {
                                        try {
                                            this.f14662g.write(this.f14657b, (int) j10, (int) length);
                                            j10 += length;
                                        } catch (Exception e10) {
                                            e = e10;
                                            str12 = "]U.2 upload data ======== end";
                                            str13 = "]: ==== exception: ";
                                            str14 = "]: ==== errorStream is null";
                                            str3 = "]: ==== done !!!! success";
                                            str11 = str18;
                                            str4 = str19;
                                            str28 = str31;
                                            str5 = str32;
                                            str2 = str33;
                                            z8 = true;
                                            str15 = "[";
                                            str = "\"";
                                            String str34 = str13;
                                            try {
                                                StringBuilder sb2 = new StringBuilder();
                                                sb2.append("[do_upload][");
                                                String str35 = str14;
                                                try {
                                                    sb2.append(this.f14665j);
                                                    sb2.append("]");
                                                    UploadUtils.m16964k("UploadMediaHelperV2", sb2.toString(), e);
                                                    UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== finally");
                                                    try {
                                                        dataOutputStream2 = this.f14662g;
                                                        if (dataOutputStream2 != null) {
                                                            dataOutputStream2.close();
                                                        }
                                                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mOut.close()");
                                                        HttpURLConnection httpURLConnection4 = this.f14661f;
                                                        responseCode2 = httpURLConnection4 != null ? httpURLConnection4.getResponseCode() : -1;
                                                        try {
                                                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mConn.getResponseCode()");
                                                            r52 = responseCode2;
                                                        } catch (Exception e11) {
                                                            e = e11;
                                                            UploadUtils.m16964k("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "]", e);
                                                            r52 = responseCode2;
                                                            httpURLConnection2 = this.f14661f;
                                                            if (httpURLConnection2 != null) {
                                                            }
                                                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str2);
                                                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str5 + r52);
                                                            ?? errorStream3 = 200;
                                                            if (r52 != 200) {
                                                            }
                                                            this.f14661f = r22;
                                                            this.f14662g = r22;
                                                            StringBuilder sb3 = new StringBuilder();
                                                            str17 = str15;
                                                            sb3.append(str17);
                                                            sb3.append(this.f14665j);
                                                            sb3.append(str12);
                                                            str16 = str11;
                                                            UploadUtils.m16965l(str16, sb3.toString());
                                                            z10 = z11;
                                                            z9 = false;
                                                            UploadUtils.m16965l(str16, str17 + this.f14665j + "]U.2 do_upload(); ======== end");
                                                            if (z10) {
                                                            }
                                                            return false;
                                                        }
                                                    } catch (Exception e12) {
                                                        e = e12;
                                                        responseCode2 = -1;
                                                    }
                                                    httpURLConnection2 = this.f14661f;
                                                    if (httpURLConnection2 != null) {
                                                        httpURLConnection2.disconnect();
                                                    }
                                                    UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str2);
                                                    UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str5 + r52);
                                                    ?? errorStream32 = 200;
                                                    try {
                                                        if (r52 != 200) {
                                                            HttpURLConnection httpURLConnection5 = this.f14661f;
                                                            Map<String, List<String>> headerFields = httpURLConnection5 != null ? httpURLConnection5.getHeaderFields() : null;
                                                            if (headerFields == null || (list2 = headerFields.get(str4)) == null || list2.isEmpty()) {
                                                                z12 = false;
                                                            } else {
                                                                if (Globals.m7388i0().m7550g2()) {
                                                                    this.f14663h = str28;
                                                                } else {
                                                                    this.f14663h = list2.get(0).replaceAll(str, str28);
                                                                }
                                                                UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str3);
                                                                z12 = z8;
                                                            }
                                                            z11 = z12;
                                                            r22 = 0;
                                                        } else {
                                                            try {
                                                                HttpURLConnection httpURLConnection6 = this.f14661f;
                                                                errorStream32 = httpURLConnection6 != null ? httpURLConnection6.getErrorStream() : 0;
                                                                try {
                                                                    if (errorStream32 != 0) {
                                                                        bufferedReader3 = new BufferedReader(new InputStreamReader(errorStream32));
                                                                        try {
                                                                            StringBuilder sb4 = new StringBuilder();
                                                                            while (true) {
                                                                                String line = bufferedReader3.readLine();
                                                                                if (line == null) {
                                                                                    break;
                                                                                }
                                                                                sb4.append(line);
                                                                            }
                                                                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "]: ==== error, errorMsg = " + sb4.toString());
                                                                        } catch (Exception e13) {
                                                                            e = e13;
                                                                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str34 + e.getMessage());
                                                                            C6370g.m24480b(bufferedReader3);
                                                                            C6370g.m24480b(errorStream32);
                                                                            r22 = 0;
                                                                            z11 = false;
                                                                            this.f14661f = r22;
                                                                            this.f14662g = r22;
                                                                            StringBuilder sb32 = new StringBuilder();
                                                                            str17 = str15;
                                                                            sb32.append(str17);
                                                                            sb32.append(this.f14665j);
                                                                            sb32.append(str12);
                                                                            str16 = str11;
                                                                            UploadUtils.m16965l(str16, sb32.toString());
                                                                            z10 = z11;
                                                                            z9 = false;
                                                                            UploadUtils.m16965l(str16, str17 + this.f14665j + "]U.2 do_upload(); ======== end");
                                                                            if (z10) {
                                                                            }
                                                                            return false;
                                                                        }
                                                                    } else {
                                                                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str35);
                                                                        bufferedReader3 = null;
                                                                    }
                                                                } catch (Exception e14) {
                                                                    e = e14;
                                                                    bufferedReader3 = null;
                                                                    UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str34 + e.getMessage());
                                                                    C6370g.m24480b(bufferedReader3);
                                                                    C6370g.m24480b(errorStream32);
                                                                    r22 = 0;
                                                                    z11 = false;
                                                                    this.f14661f = r22;
                                                                    this.f14662g = r22;
                                                                    StringBuilder sb322 = new StringBuilder();
                                                                    str17 = str15;
                                                                    sb322.append(str17);
                                                                    sb322.append(this.f14665j);
                                                                    sb322.append(str12);
                                                                    str16 = str11;
                                                                    UploadUtils.m16965l(str16, sb322.toString());
                                                                    z10 = z11;
                                                                    z9 = false;
                                                                    UploadUtils.m16965l(str16, str17 + this.f14665j + "]U.2 do_upload(); ======== end");
                                                                    if (z10) {
                                                                    }
                                                                    return false;
                                                                } catch (Throwable th3) {
                                                                    th = th3;
                                                                    r52 = 0;
                                                                    C6370g.m24480b(r52);
                                                                    C6370g.m24480b(errorStream32);
                                                                    throw th;
                                                                }
                                                            } catch (Exception e15) {
                                                                e = e15;
                                                                errorStream32 = 0;
                                                            } catch (Throwable th4) {
                                                                th = th4;
                                                                errorStream32 = 0;
                                                            }
                                                            C6370g.m24480b(bufferedReader3);
                                                            C6370g.m24480b(errorStream32);
                                                            r22 = 0;
                                                            z11 = false;
                                                        }
                                                        this.f14661f = r22;
                                                        this.f14662g = r22;
                                                        StringBuilder sb3222 = new StringBuilder();
                                                        str17 = str15;
                                                        sb3222.append(str17);
                                                        sb3222.append(this.f14665j);
                                                        sb3222.append(str12);
                                                        str16 = str11;
                                                        UploadUtils.m16965l(str16, sb3222.toString());
                                                        z10 = z11;
                                                        z9 = false;
                                                        UploadUtils.m16965l(str16, str17 + this.f14665j + "]U.2 do_upload(); ======== end");
                                                        if (z10) {
                                                        }
                                                        return false;
                                                    } catch (Throwable th5) {
                                                        th = th5;
                                                    }
                                                } catch (Throwable th6) {
                                                    th = th6;
                                                    str8 = str34;
                                                    str9 = str15;
                                                    str6 = str35;
                                                    str10 = str11;
                                                    str7 = str12;
                                                    th = th;
                                                    StringBuilder sb5 = new StringBuilder();
                                                    sb5.append("[do_upload][");
                                                    String str36 = str8;
                                                    sb5.append(this.f14665j);
                                                    sb5.append("] ======== finally");
                                                    UploadUtils.m16965l("UploadMediaHelperV2", sb5.toString());
                                                    dataOutputStream = this.f14662g;
                                                    if (dataOutputStream != null) {
                                                    }
                                                    UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mOut.close()");
                                                    HttpURLConnection httpURLConnection7 = this.f14661f;
                                                    if (httpURLConnection7 != null) {
                                                    }
                                                    try {
                                                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mConn.getResponseCode()");
                                                    } catch (Exception e16) {
                                                        e = e16;
                                                        UploadUtils.m16964k("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "]", e);
                                                        i9 = responseCode;
                                                        httpURLConnection = this.f14661f;
                                                        if (httpURLConnection != null) {
                                                        }
                                                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str2);
                                                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str5 + i9);
                                                        if (i9 != 200) {
                                                        }
                                                        this.f14661f = null;
                                                        this.f14662g = null;
                                                        UploadUtils.m16965l(str10, str9 + this.f14665j + str7);
                                                        throw th;
                                                    }
                                                    i9 = responseCode;
                                                    httpURLConnection = this.f14661f;
                                                    if (httpURLConnection != null) {
                                                    }
                                                    UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str2);
                                                    UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str5 + i9);
                                                    if (i9 != 200) {
                                                    }
                                                    this.f14661f = null;
                                                    this.f14662g = null;
                                                    UploadUtils.m16965l(str10, str9 + this.f14665j + str7);
                                                    throw th;
                                                }
                                            } catch (Throwable th7) {
                                                th = th7;
                                                str6 = str14;
                                                str8 = str34;
                                                str9 = str15;
                                            }
                                        }
                                        try {
                                            Integer[] numArr2 = new Integer[1];
                                            numArr2[0] = Integer.valueOf((int) ((100 * j10) / this.f14657b.length));
                                            publishProgress(numArr2);
                                            str30 = str18;
                                            str25 = str19;
                                            str28 = str31;
                                            str26 = str32;
                                            str27 = str33;
                                        } catch (Exception e17) {
                                            e = e17;
                                            str12 = "]U.2 upload data ======== end";
                                            str13 = "]: ==== exception: ";
                                            str3 = "]: ==== done !!!! success";
                                            str11 = str18;
                                            str4 = str19;
                                            str28 = str31;
                                            str5 = str32;
                                            str2 = str33;
                                            z8 = true;
                                            str14 = "]: ==== errorStream is null";
                                            str15 = "[";
                                            str = "\"";
                                            String str342 = str13;
                                            StringBuilder sb22 = new StringBuilder();
                                            sb22.append("[do_upload][");
                                            String str352 = str14;
                                            sb22.append(this.f14665j);
                                            sb22.append("]");
                                            UploadUtils.m16964k("UploadMediaHelperV2", sb22.toString(), e);
                                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== finally");
                                            dataOutputStream2 = this.f14662g;
                                            if (dataOutputStream2 != null) {
                                            }
                                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mOut.close()");
                                            HttpURLConnection httpURLConnection42 = this.f14661f;
                                            if (httpURLConnection42 != null) {
                                            }
                                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mConn.getResponseCode()");
                                            r52 = responseCode2;
                                            httpURLConnection2 = this.f14661f;
                                            if (httpURLConnection2 != null) {
                                            }
                                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str2);
                                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str5 + r52);
                                            ?? errorStream322 = 200;
                                            if (r52 != 200) {
                                            }
                                            this.f14661f = r22;
                                            this.f14662g = r22;
                                            StringBuilder sb32222 = new StringBuilder();
                                            str17 = str15;
                                            sb32222.append(str17);
                                            sb32222.append(this.f14665j);
                                            sb32222.append(str12);
                                            str16 = str11;
                                            UploadUtils.m16965l(str16, sb32222.toString());
                                            z10 = z11;
                                            z9 = false;
                                            UploadUtils.m16965l(str16, str17 + this.f14665j + "]U.2 do_upload(); ======== end");
                                            if (z10) {
                                            }
                                            return false;
                                        }
                                    } catch (Throwable th8) {
                                        th = th8;
                                        str9 = "[";
                                        str7 = "]U.2 upload data ======== end";
                                        str8 = "]: ==== exception: ";
                                        str6 = "]: ==== errorStream is null";
                                        str3 = "]: ==== done !!!! success";
                                        str = "\"";
                                        str10 = str18;
                                        str4 = str19;
                                        str28 = str31;
                                        str5 = str32;
                                        str2 = str33;
                                        th = th;
                                        StringBuilder sb52 = new StringBuilder();
                                        sb52.append("[do_upload][");
                                        String str362 = str8;
                                        sb52.append(this.f14665j);
                                        sb52.append("] ======== finally");
                                        UploadUtils.m16965l("UploadMediaHelperV2", sb52.toString());
                                        dataOutputStream = this.f14662g;
                                        if (dataOutputStream != null) {
                                        }
                                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mOut.close()");
                                        HttpURLConnection httpURLConnection72 = this.f14661f;
                                        if (httpURLConnection72 != null) {
                                        }
                                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mConn.getResponseCode()");
                                        i9 = responseCode;
                                        httpURLConnection = this.f14661f;
                                        if (httpURLConnection != null) {
                                        }
                                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str2);
                                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str5 + i9);
                                        if (i9 != 200) {
                                        }
                                        this.f14661f = null;
                                        this.f14662g = null;
                                        UploadUtils.m16965l(str10, str9 + this.f14665j + str7);
                                        throw th;
                                    }
                                } catch (Exception e18) {
                                    e = e18;
                                    str5 = str26;
                                    str2 = str27;
                                    str12 = "]U.2 upload data ======== end";
                                    str13 = "]: ==== exception: ";
                                    str14 = "]: ==== errorStream is null";
                                    str3 = "]: ==== done !!!! success";
                                    str11 = str18;
                                    str4 = str19;
                                    str28 = str31;
                                } catch (Throwable th9) {
                                    th = th9;
                                    str5 = str26;
                                    str2 = str27;
                                    str9 = "[";
                                    str7 = "]U.2 upload data ======== end";
                                    str8 = "]: ==== exception: ";
                                    str6 = "]: ==== errorStream is null";
                                    str3 = "]: ==== done !!!! success";
                                    str = "\"";
                                    str10 = str18;
                                    str4 = str19;
                                    str28 = str31;
                                }
                            } catch (Exception e19) {
                                e = e19;
                                str5 = str26;
                                str2 = str27;
                                str12 = "]U.2 upload data ======== end";
                                str13 = "]: ==== exception: ";
                                str14 = "]: ==== errorStream is null";
                                str3 = "]: ==== done !!!! success";
                                str11 = str18;
                                str4 = str19;
                            } catch (Throwable th10) {
                                th = th10;
                                str5 = str26;
                                str2 = str27;
                                str9 = "[";
                                str7 = "]U.2 upload data ======== end";
                                str8 = "]: ==== exception: ";
                                str6 = "]: ==== errorStream is null";
                                str3 = "]: ==== done !!!! success";
                                str = "\"";
                                str10 = str18;
                                str4 = str19;
                            }
                        } catch (Exception e20) {
                            e = e20;
                            str2 = str27;
                            str12 = "]U.2 upload data ======== end";
                            str13 = "]: ==== exception: ";
                            str14 = "]: ==== errorStream is null";
                            str3 = "]: ==== done !!!! success";
                            str11 = str18;
                            z8 = true;
                            str15 = "[";
                            str = "\"";
                            String str37 = str26;
                            str4 = str25;
                            str5 = str37;
                            String str3422 = str13;
                            StringBuilder sb222 = new StringBuilder();
                            sb222.append("[do_upload][");
                            String str3522 = str14;
                            sb222.append(this.f14665j);
                            sb222.append("]");
                            UploadUtils.m16964k("UploadMediaHelperV2", sb222.toString(), e);
                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== finally");
                            dataOutputStream2 = this.f14662g;
                            if (dataOutputStream2 != null) {
                            }
                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mOut.close()");
                            HttpURLConnection httpURLConnection422 = this.f14661f;
                            if (httpURLConnection422 != null) {
                            }
                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mConn.getResponseCode()");
                            r52 = responseCode2;
                            httpURLConnection2 = this.f14661f;
                            if (httpURLConnection2 != null) {
                            }
                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str2);
                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str5 + r52);
                            ?? errorStream3222 = 200;
                            if (r52 != 200) {
                            }
                            this.f14661f = r22;
                            this.f14662g = r22;
                            StringBuilder sb322222 = new StringBuilder();
                            str17 = str15;
                            sb322222.append(str17);
                            sb322222.append(this.f14665j);
                            sb322222.append(str12);
                            str16 = str11;
                            UploadUtils.m16965l(str16, sb322222.toString());
                            z10 = z11;
                            z9 = false;
                            UploadUtils.m16965l(str16, str17 + this.f14665j + "]U.2 do_upload(); ======== end");
                            if (z10) {
                            }
                            return false;
                        } catch (Throwable th11) {
                            th = th11;
                            str2 = str27;
                            str9 = "[";
                            str7 = "]U.2 upload data ======== end";
                            str8 = "]: ==== exception: ";
                            str6 = "]: ==== errorStream is null";
                            str3 = "]: ==== done !!!! success";
                            str = "\"";
                            str10 = str18;
                            th = th;
                            String str38 = str26;
                            str4 = str25;
                            str5 = str38;
                            StringBuilder sb522 = new StringBuilder();
                            sb522.append("[do_upload][");
                            String str3622 = str8;
                            sb522.append(this.f14665j);
                            sb522.append("] ======== finally");
                            UploadUtils.m16965l("UploadMediaHelperV2", sb522.toString());
                            dataOutputStream = this.f14662g;
                            if (dataOutputStream != null) {
                            }
                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mOut.close()");
                            HttpURLConnection httpURLConnection722 = this.f14661f;
                            if (httpURLConnection722 != null) {
                            }
                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mConn.getResponseCode()");
                            i9 = responseCode;
                            httpURLConnection = this.f14661f;
                            if (httpURLConnection != null) {
                            }
                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str2);
                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str5 + i9);
                            if (i9 != 200) {
                            }
                            this.f14661f = null;
                            this.f14662g = null;
                            UploadUtils.m16965l(str10, str9 + this.f14665j + str7);
                            throw th;
                        }
                    } catch (Exception e21) {
                        e = e21;
                        str11 = str30;
                        str2 = str27;
                        str12 = "]U.2 upload data ======== end";
                        str13 = "]: ==== exception: ";
                        str14 = "]: ==== errorStream is null";
                        str3 = "]: ==== done !!!! success";
                    } catch (Throwable th12) {
                        th = th12;
                        str10 = str30;
                        str2 = str27;
                        str9 = "[";
                        str7 = "]U.2 upload data ======== end";
                        str8 = "]: ==== exception: ";
                        str6 = "]: ==== errorStream is null";
                        str3 = "]: ==== done !!!! success";
                        str = "\"";
                    }
                }
                str20 = str26;
                str21 = str27;
                str22 = str28;
                str23 = "[";
                z9 = true;
                this.f14662g.flush();
                UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== finally");
                try {
                    dataOutputStream3 = this.f14662g;
                    if (dataOutputStream3 != null) {
                        dataOutputStream3.close();
                    }
                    UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mOut.close()");
                    HttpURLConnection httpURLConnection8 = this.f14661f;
                    responseCode3 = httpURLConnection8 == null ? httpURLConnection8.getResponseCode() : -1;
                    try {
                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mConn.getResponseCode()");
                    } catch (Exception e22) {
                        e = e22;
                        UploadUtils.m16964k("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "]", e);
                        httpURLConnection3 = this.f14661f;
                        if (httpURLConnection3 != null) {
                        }
                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str21);
                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str20 + responseCode3);
                        if (responseCode3 == 200) {
                        }
                        z10 = false;
                        this.f14661f = null;
                        this.f14662g = null;
                        str16 = str18;
                        UploadUtils.m16965l(str16, str23 + this.f14665j + "]U.2 upload data ======== end");
                        z8 = z9;
                        str17 = str23;
                        UploadUtils.m16965l(str16, str17 + this.f14665j + "]U.2 do_upload(); ======== end");
                        if (z10) {
                        }
                        return false;
                    }
                } catch (Exception e23) {
                    e = e23;
                    responseCode3 = -1;
                }
                httpURLConnection3 = this.f14661f;
                if (httpURLConnection3 != null) {
                    httpURLConnection3.disconnect();
                }
                UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str21);
                UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str20 + responseCode3);
                if (responseCode3 == 200) {
                    HttpURLConnection httpURLConnection9 = this.f14661f;
                    Map<String, List<String>> headerFields2 = httpURLConnection9 != null ? httpURLConnection9.getHeaderFields() : null;
                    if (headerFields2 != null && (list3 = headerFields2.get(str19)) != null && !list3.isEmpty()) {
                        if (Globals.m7388i0().m7550g2()) {
                            this.f14663h = str22;
                        } else {
                            this.f14663h = list3.get(0).replaceAll("\"", str22);
                        }
                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "]: ==== done !!!! success");
                        z10 = z9;
                    }
                    this.f14661f = null;
                    this.f14662g = null;
                    str16 = str18;
                    UploadUtils.m16965l(str16, str23 + this.f14665j + "]U.2 upload data ======== end");
                    z8 = z9;
                    str17 = str23;
                    UploadUtils.m16965l(str16, str17 + this.f14665j + "]U.2 do_upload(); ======== end");
                    if (z10 || !z9) {
                        return false;
                    }
                    return z8;
                }
                try {
                    HttpURLConnection httpURLConnection10 = this.f14661f;
                    errorStream2 = httpURLConnection10 != null ? httpURLConnection10.getErrorStream() : null;
                } catch (Exception e24) {
                    e = e24;
                    errorStream2 = null;
                } catch (Throwable th13) {
                    th = th13;
                    errorStream2 = null;
                }
                try {
                    if (errorStream2 != null) {
                        bufferedReader4 = new BufferedReader(new InputStreamReader(errorStream2));
                        try {
                            try {
                                StringBuilder sb6 = new StringBuilder();
                                while (true) {
                                    String line2 = bufferedReader4.readLine();
                                    if (line2 == null) {
                                        break;
                                    }
                                    sb6.append(line2);
                                }
                                UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "]: ==== error, errorMsg = " + sb6.toString());
                            } catch (Exception e25) {
                                e = e25;
                                UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "]: ==== exception: " + e.getMessage());
                                C6370g.m24480b(bufferedReader4);
                                C6370g.m24480b(errorStream2);
                                z10 = false;
                                this.f14661f = null;
                                this.f14662g = null;
                                str16 = str18;
                                UploadUtils.m16965l(str16, str23 + this.f14665j + "]U.2 upload data ======== end");
                                z8 = z9;
                                str17 = str23;
                                UploadUtils.m16965l(str16, str17 + this.f14665j + "]U.2 do_upload(); ======== end");
                                if (z10) {
                                }
                                return false;
                            }
                        } catch (Throwable th14) {
                            th = th14;
                            C6370g.m24480b(bufferedReader4);
                            C6370g.m24480b(errorStream2);
                            throw th;
                        }
                    } else {
                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "]: ==== errorStream is null");
                        bufferedReader4 = null;
                    }
                } catch (Exception e26) {
                    e = e26;
                    bufferedReader4 = null;
                    UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "]: ==== exception: " + e.getMessage());
                    C6370g.m24480b(bufferedReader4);
                    C6370g.m24480b(errorStream2);
                    z10 = false;
                    this.f14661f = null;
                    this.f14662g = null;
                    str16 = str18;
                    UploadUtils.m16965l(str16, str23 + this.f14665j + "]U.2 upload data ======== end");
                    z8 = z9;
                    str17 = str23;
                    UploadUtils.m16965l(str16, str17 + this.f14665j + "]U.2 do_upload(); ======== end");
                    if (z10) {
                    }
                    return false;
                } catch (Throwable th15) {
                    th = th15;
                    bufferedReader4 = null;
                    C6370g.m24480b(bufferedReader4);
                    C6370g.m24480b(errorStream2);
                    throw th;
                }
                C6370g.m24480b(bufferedReader4);
                C6370g.m24480b(errorStream2);
                z10 = false;
                this.f14661f = null;
                this.f14662g = null;
                str16 = str18;
                UploadUtils.m16965l(str16, str23 + this.f14665j + "]U.2 upload data ======== end");
                z8 = z9;
                str17 = str23;
                UploadUtils.m16965l(str16, str17 + this.f14665j + "]U.2 do_upload(); ======== end");
                if (z10) {
                }
                return false;
            }
            str18 = "Upload Performance";
            str19 = HttpHeaders.ETAG;
            str20 = "]: ==== done #### responseCode = ";
            str21 = "] ======== mConn.disconnect()";
            str22 = "";
            try {
                if (this.f14659d != null) {
                    try {
                        inputStreamOpenInputStream = Globals.m7372O().getContentResolver().openInputStream(this.f14659d);
                    } catch (Throwable th16) {
                        th = th16;
                        str12 = "]U.2 upload data ======== end";
                        str13 = "]: ==== exception: ";
                        str14 = "]: ==== errorStream is null";
                        str3 = "]: ==== done !!!! success";
                        str11 = str18;
                        str4 = str19;
                        str28 = str22;
                        str5 = str20;
                        str2 = str21;
                        z8 = true;
                        inputStream2 = null;
                        str15 = "[";
                        str = "\"";
                        try {
                            C6370g.m24480b(inputStream2);
                            throw th;
                        } catch (Exception e27) {
                            e = e27;
                            String str34222 = str13;
                            StringBuilder sb2222 = new StringBuilder();
                            sb2222.append("[do_upload][");
                            String str35222 = str14;
                            sb2222.append(this.f14665j);
                            sb2222.append("]");
                            UploadUtils.m16964k("UploadMediaHelperV2", sb2222.toString(), e);
                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== finally");
                            dataOutputStream2 = this.f14662g;
                            if (dataOutputStream2 != null) {
                            }
                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mOut.close()");
                            HttpURLConnection httpURLConnection4222 = this.f14661f;
                            if (httpURLConnection4222 != null) {
                            }
                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mConn.getResponseCode()");
                            r52 = responseCode2;
                            httpURLConnection2 = this.f14661f;
                            if (httpURLConnection2 != null) {
                            }
                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str2);
                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str5 + r52);
                            ?? errorStream32222 = 200;
                            if (r52 != 200) {
                            }
                            this.f14661f = r22;
                            this.f14662g = r22;
                            StringBuilder sb3222222 = new StringBuilder();
                            str17 = str15;
                            sb3222222.append(str17);
                            sb3222222.append(this.f14665j);
                            sb3222222.append(str12);
                            str16 = str11;
                            UploadUtils.m16965l(str16, sb3222222.toString());
                            z10 = z11;
                            z9 = false;
                            UploadUtils.m16965l(str16, str17 + this.f14665j + "]U.2 do_upload(); ======== end");
                            if (z10) {
                            }
                            return false;
                        } catch (Throwable th17) {
                            th = th17;
                            str9 = str15;
                            str10 = str11;
                            str7 = str12;
                            String str39 = str14;
                            str8 = str13;
                            str6 = str39;
                        }
                    }
                } else {
                    inputStreamOpenInputStream = new FileInputStream(this.f14658c);
                }
                InputStream inputStream3 = inputStreamOpenInputStream;
                long j11 = 10240;
                try {
                    byte[] bArr2 = new byte[(int) 10240];
                    while (true) {
                        long j12 = this.f14660e;
                        if (j9 >= j12) {
                            break;
                        }
                        long j13 = j12 - j9;
                        if (j13 > j11) {
                            j13 = j11;
                        }
                        if (UploadMediaHelper.this.f14604v0) {
                            break;
                        }
                        int i10 = inputStream3.read(bArr2);
                        if (i10 == -1) {
                            break;
                        }
                        if (i10 != j13) {
                            ULogUtility.m16684t("UploadMediaHelperV2", "check :  write byte not match : " + i10 + " vs " + j13);
                        }
                        if (UploadMediaHelper.this.f14604v0) {
                            break;
                        }
                        this.f14662g.write(bArr2, 0, (int) j13);
                        j9 += j13;
                        try {
                            numArr = new Integer[1];
                            str24 = str29;
                        } catch (Throwable th18) {
                            th = th18;
                            str12 = "]U.2 upload data ======== end";
                            str3 = "]: ==== done !!!! success";
                            str11 = str18;
                            str4 = str19;
                            str28 = str22;
                            str5 = str20;
                            str2 = str21;
                            z8 = true;
                            str14 = "]: ==== errorStream is null";
                            str15 = str29;
                            str = "\"";
                            inputStream2 = inputStream3;
                            str13 = "]: ==== exception: ";
                            C6370g.m24480b(inputStream2);
                            throw th;
                        }
                        try {
                            numArr[0] = Integer.valueOf((int) ((100 * j9) / this.f14660e));
                            publishProgress(numArr);
                            str29 = str24;
                            j11 = 10240;
                        } catch (Throwable th19) {
                            th = th19;
                            str12 = "]U.2 upload data ======== end";
                            str = "\"";
                            str11 = str18;
                            str4 = str19;
                            str28 = str22;
                            str5 = str20;
                            str2 = str21;
                            z8 = true;
                            str14 = "]: ==== errorStream is null";
                            str15 = str24;
                            str3 = "]: ==== done !!!! success";
                            inputStream2 = inputStream3;
                            str13 = "]: ==== exception: ";
                            C6370g.m24480b(inputStream2);
                            throw th;
                        }
                    }
                    str23 = str29;
                    z9 = true;
                    try {
                        C6370g.m24480b(inputStream3);
                        this.f14662g.flush();
                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== finally");
                        dataOutputStream3 = this.f14662g;
                        if (dataOutputStream3 != null) {
                        }
                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mOut.close()");
                        HttpURLConnection httpURLConnection82 = this.f14661f;
                        if (httpURLConnection82 == null) {
                        }
                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mConn.getResponseCode()");
                        httpURLConnection3 = this.f14661f;
                        if (httpURLConnection3 != null) {
                        }
                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str21);
                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str20 + responseCode3);
                        if (responseCode3 == 200) {
                        }
                        z10 = false;
                        this.f14661f = null;
                        this.f14662g = null;
                        str16 = str18;
                        UploadUtils.m16965l(str16, str23 + this.f14665j + "]U.2 upload data ======== end");
                        z8 = z9;
                        str17 = str23;
                    } catch (Exception e28) {
                        e = e28;
                        str12 = "]U.2 upload data ======== end";
                        str13 = "]: ==== exception: ";
                        str = "\"";
                        str11 = str18;
                        str4 = str19;
                        str28 = str22;
                        str5 = str20;
                        str2 = str21;
                        z8 = z9;
                        str14 = "]: ==== errorStream is null";
                        str15 = str23;
                        str3 = "]: ==== done !!!! success";
                        String str342222 = str13;
                        StringBuilder sb22222 = new StringBuilder();
                        sb22222.append("[do_upload][");
                        String str352222 = str14;
                        sb22222.append(this.f14665j);
                        sb22222.append("]");
                        UploadUtils.m16964k("UploadMediaHelperV2", sb22222.toString(), e);
                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== finally");
                        dataOutputStream2 = this.f14662g;
                        if (dataOutputStream2 != null) {
                        }
                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mOut.close()");
                        HttpURLConnection httpURLConnection42222 = this.f14661f;
                        if (httpURLConnection42222 != null) {
                        }
                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mConn.getResponseCode()");
                        r52 = responseCode2;
                        httpURLConnection2 = this.f14661f;
                        if (httpURLConnection2 != null) {
                        }
                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str2);
                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str5 + r52);
                        ?? errorStream322222 = 200;
                        if (r52 != 200) {
                        }
                        this.f14661f = r22;
                        this.f14662g = r22;
                        StringBuilder sb32222222 = new StringBuilder();
                        str17 = str15;
                        sb32222222.append(str17);
                        sb32222222.append(this.f14665j);
                        sb32222222.append(str12);
                        str16 = str11;
                        UploadUtils.m16965l(str16, sb32222222.toString());
                        z10 = z11;
                        z9 = false;
                        UploadUtils.m16965l(str16, str17 + this.f14665j + "]U.2 do_upload(); ======== end");
                        if (z10) {
                        }
                        return false;
                    } catch (Throwable th20) {
                        th = th20;
                        str = "\"";
                        str4 = str19;
                        str28 = str22;
                        str5 = str20;
                        str2 = str21;
                        str9 = str23;
                        str7 = "]U.2 upload data ======== end";
                        str8 = "]: ==== exception: ";
                        str6 = "]: ==== errorStream is null";
                        str3 = "]: ==== done !!!! success";
                        str10 = str18;
                        th = th;
                        StringBuilder sb5222 = new StringBuilder();
                        sb5222.append("[do_upload][");
                        String str36222 = str8;
                        sb5222.append(this.f14665j);
                        sb5222.append("] ======== finally");
                        UploadUtils.m16965l("UploadMediaHelperV2", sb5222.toString());
                        dataOutputStream = this.f14662g;
                        if (dataOutputStream != null) {
                        }
                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mOut.close()");
                        HttpURLConnection httpURLConnection7222 = this.f14661f;
                        if (httpURLConnection7222 != null) {
                        }
                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mConn.getResponseCode()");
                        i9 = responseCode;
                        httpURLConnection = this.f14661f;
                        if (httpURLConnection != null) {
                        }
                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str2);
                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str5 + i9);
                        if (i9 != 200) {
                        }
                        this.f14661f = null;
                        this.f14662g = null;
                        UploadUtils.m16965l(str10, str9 + this.f14665j + str7);
                        throw th;
                    }
                    UploadUtils.m16965l(str16, str17 + this.f14665j + "]U.2 do_upload(); ======== end");
                    if (z10) {
                    }
                    return false;
                } catch (Throwable th21) {
                    th = th21;
                    str12 = "]U.2 upload data ======== end";
                    str14 = "]: ==== errorStream is null";
                    str3 = "]: ==== done !!!! success";
                    str11 = str18;
                    str4 = str19;
                    str28 = str22;
                    str5 = str20;
                    str2 = str21;
                    z8 = true;
                }
            } catch (Throwable th22) {
                th = th22;
                str12 = "]U.2 upload data ======== end";
                str13 = "]: ==== exception: ";
                str14 = "]: ==== errorStream is null";
                str3 = "]: ==== done !!!! success";
                str11 = str18;
                str4 = str19;
                str28 = str22;
                str5 = str20;
                str2 = str21;
                z8 = true;
                str15 = "[";
                str = "\"";
                inputStream2 = null;
            }
            StringBuilder sb52222 = new StringBuilder();
            sb52222.append("[do_upload][");
            String str362222 = str8;
            sb52222.append(this.f14665j);
            sb52222.append("] ======== finally");
            UploadUtils.m16965l("UploadMediaHelperV2", sb52222.toString());
            try {
                dataOutputStream = this.f14662g;
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mOut.close()");
                HttpURLConnection httpURLConnection72222 = this.f14661f;
                responseCode = httpURLConnection72222 != null ? httpURLConnection72222.getResponseCode() : -1;
                UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "] ======== mConn.getResponseCode()");
            } catch (Exception e29) {
                e = e29;
                responseCode = -1;
            }
            i9 = responseCode;
            httpURLConnection = this.f14661f;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str2);
            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str5 + i9);
            if (i9 != 200) {
                HttpURLConnection httpURLConnection11 = this.f14661f;
                Map<String, List<String>> headerFields3 = httpURLConnection11 != null ? httpURLConnection11.getHeaderFields() : null;
                if (headerFields3 != null && (list = headerFields3.get(str4)) != null && !list.isEmpty()) {
                    if (Globals.m7388i0().m7550g2()) {
                        this.f14663h = str28;
                    } else {
                        this.f14663h = list.get(0).replaceAll(str, str28);
                    }
                    UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str3);
                }
            } else {
                try {
                    HttpURLConnection httpURLConnection12 = this.f14661f;
                    errorStream = httpURLConnection12 != null ? httpURLConnection12.getErrorStream() : null;
                } catch (Exception e30) {
                    e = e30;
                    bufferedReader = null;
                    inputStream = null;
                } catch (Throwable th23) {
                    th = th23;
                    errorStream = null;
                }
                try {
                    if (errorStream != null) {
                        bufferedReader2 = new BufferedReader(new InputStreamReader(errorStream));
                        try {
                            StringBuilder sb7 = new StringBuilder();
                            while (true) {
                                String line3 = bufferedReader2.readLine();
                                if (line3 == null) {
                                    break;
                                }
                                sb7.append(line3);
                            }
                            UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + "]: ==== error, errorMsg = " + sb7.toString());
                        } catch (Exception e31) {
                            e = e31;
                            inputStream = errorStream;
                            bufferedReader = bufferedReader2;
                            try {
                                UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str362222 + e.getMessage());
                                C6370g.m24480b(bufferedReader);
                                C6370g.m24480b(inputStream);
                                this.f14661f = null;
                                this.f14662g = null;
                                UploadUtils.m16965l(str10, str9 + this.f14665j + str7);
                                throw th;
                            } catch (Throwable th24) {
                                th = th24;
                                InputStream inputStream4 = inputStream;
                                bufferedReader2 = bufferedReader;
                                errorStream = inputStream4;
                                C6370g.m24480b(bufferedReader2);
                                C6370g.m24480b(errorStream);
                                throw th;
                            }
                        } catch (Throwable th25) {
                            th = th25;
                            C6370g.m24480b(bufferedReader2);
                            C6370g.m24480b(errorStream);
                            throw th;
                        }
                    } else {
                        UploadUtils.m16965l("UploadMediaHelperV2", "[do_upload][" + this.f14665j + str6);
                        bufferedReader2 = null;
                    }
                    C6370g.m24480b(bufferedReader2);
                    C6370g.m24480b(errorStream);
                } catch (Exception e32) {
                    e = e32;
                    inputStream = errorStream;
                    bufferedReader = null;
                } catch (Throwable th26) {
                    th = th26;
                    bufferedReader2 = null;
                    C6370g.m24480b(bufferedReader2);
                    C6370g.m24480b(errorStream);
                    throw th;
                }
            }
            this.f14661f = null;
            this.f14662g = null;
            UploadUtils.m16965l(str10, str9 + this.f14665j + str7);
            throw th;
        }

        /* renamed from: l */
        public final boolean m16887l() {
            MediaType mediaType = this.f14656a;
            if (mediaType == MediaType.VIDEO) {
                return m16888m(UploadMediaHelper.this.f14579j);
            }
            if (mediaType == MediaType.FILE) {
                return m16888m(UploadMediaHelper.this.f14581k);
            }
            return false;
        }

        /* renamed from: m */
        public final boolean m16888m(UploadUtils.C3196b c3196b) {
            UploadUtils.m16965l("Upload Performance", "U.2 do_uploadMultiPart(); ======== start");
            UploadUtils.m16965l("UploadMediaHelperV2", "[do_uploadMultiPart]: ==== start");
            for (int i9 = 1; i9 <= c3196b.m16971f(); i9++) {
                UploadUtils.C3196b.a aVarM16968c = c3196b.m16968c(i9);
                if (aVarM16968c != null) {
                    if (UploadMediaHelper.this.f14604v0 || aVarM16968c.f14744h) {
                        UploadUtils.m16965l("UploadMediaHelperV2", String.format(Locale.US, "[do_uploadMultiPart]: task(%d) is already successfully", Integer.valueOf(i9)));
                    } else {
                        Log.v("UploadMediaHelperV2", "[do_uploadMultiPart] start task(" + i9 + ") for upload");
                        aVarM16968c.f14745i = false;
                        aVarM16968c.m16976c(0L);
                        new Thread(new a(aVarM16968c)).start();
                    }
                }
            }
            int iM16883h = m16883h();
            publishProgress(Integer.valueOf(iM16883h));
            while (!c3196b.m16969d() && !UploadMediaHelper.this.f14604v0) {
                SystemClock.sleep(250L);
                int iM16883h2 = m16883h();
                if (iM16883h2 > iM16883h) {
                    publishProgress(Integer.valueOf(iM16883h2));
                    iM16883h = iM16883h2;
                }
            }
            boolean zM16970e = c3196b.m16970e();
            UploadUtils.m16965l("Upload Performance", "U.2 do_uploadMultiPart(); result = " + zM16970e);
            UploadUtils.m16965l("Upload Performance", "U.2 do_uploadMultiPart(); ======== end");
            return zM16970e;
        }

        /* renamed from: n */
        public final boolean m16889n(boolean z8) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[" + this.f14665j + "]U.3 UploadTask.doInBackground()==> callback.onComplete(); ======== start: " + z8);
            this.f14664i.mo16867b(this, z8);
            UploadUtils.m16965l("UploadMediaHelperV2", "[" + this.f14665j + "]U.3 UploadTask.doInBackground()==> callback.onComplete(); ======== end");
            UploadUtils.m16965l("UploadMediaHelperV2", "[UploadTask.doInBackground][" + this.f14665j + "]: ==== done !!! success = " + z8);
            return z8;
        }

        @Override // android.os.AsyncTask
        /* renamed from: o, reason: merged with bridge method [inline-methods] */
        public void onProgressUpdate(Integer... numArr) {
            InterfaceC3187x interfaceC3187x = this.f14664i;
            if (interfaceC3187x != null) {
                interfaceC3187x.mo16866a(this.f14656a, numArr[0].intValue());
            }
        }

        /* renamed from: p */
        public final void m16891p(InterfaceC3187x interfaceC3187x) {
            this.f14664i = interfaceC3187x;
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$x */
    public interface InterfaceC3187x {
        /* renamed from: a */
        void mo16866a(MediaType mediaType, int i9);

        /* renamed from: b */
        void mo16867b(AsyncTaskC3186w asyncTaskC3186w, boolean z8);
    }

    public UploadMediaHelper(String str, ImageItem imageItem) {
        this.f14559a = Executors.newFixedThreadPool(2, new ThreadFactoryC6373j("UploadMediaHelper"));
        this.f14562b = null;
        this.f14565c = null;
        this.f14567d = null;
        this.f14569e = null;
        this.f14571f = null;
        this.f14573g = null;
        this.f14575h = null;
        this.f14577i = null;
        this.f14579j = null;
        this.f14581k = null;
        this.f14583l = null;
        this.f14585m = null;
        this.f14587n = null;
        this.f14589o = null;
        this.f14591p = null;
        this.f14593q = null;
        this.f14595r = null;
        this.f14597s = null;
        this.f14599t = null;
        this.f14601u = null;
        this.f14603v = null;
        this.f14605w = null;
        this.f14607x = null;
        this.f14609y = null;
        this.f14611z = null;
        this.f14507A = null;
        this.f14509B = null;
        this.f14511C = null;
        this.f14513D = null;
        this.f14515E = null;
        this.f14517F = null;
        this.f14519G = null;
        this.f14521H = null;
        this.f14523I = null;
        this.f14525J = null;
        this.f14527K = null;
        this.f14529L = null;
        this.f14531M = null;
        this.f14533N = null;
        this.f14535O = null;
        this.f14537P = null;
        this.f14539Q = null;
        this.f14541R = null;
        this.f14543S = null;
        this.f14545T = null;
        this.f14547U = new FriendsClient(1);
        this.f14549V = null;
        this.f14551W = null;
        UploadUtils.UploadResultType uploadResultType = UploadUtils.UploadResultType.STEP_1_FAIL;
        this.f14553X = uploadResultType;
        this.f14555Y = uploadResultType;
        this.f14557Z = uploadResultType;
        this.f14560a0 = uploadResultType;
        this.f14563b0 = null;
        this.f14566c0 = null;
        this.f14568d0 = null;
        this.f14570e0 = null;
        this.f14572f0 = null;
        this.f14574g0 = 0;
        this.f14576h0 = 0;
        this.f14578i0 = 0;
        this.f14580j0 = 0;
        this.f14582k0 = 0;
        this.f14584l0 = null;
        this.f14586m0 = null;
        this.f14588n0 = false;
        this.f14590o0 = false;
        this.f14592p0 = false;
        this.f14594q0 = false;
        this.f14596r0 = false;
        this.f14598s0 = false;
        this.f14600t0 = false;
        this.f14602u0 = FailReason.FAIL_NONE;
        this.f14604v0 = false;
        this.f14606w0 = null;
        this.f14608x0 = null;
        this.f14610y0 = null;
        this.f14612z0 = null;
        this.f14508A0 = null;
        this.f14510B0 = null;
        this.f14512C0 = null;
        this.f14514D0 = null;
        this.f14516E0 = null;
        this.f14518F0 = null;
        this.f14520G0 = null;
        this.f14522H0 = new Semaphore(0, true);
        this.f14524I0 = new Semaphore(0, true);
        this.f14526J0 = new Semaphore(0, true);
        this.f14528K0 = new Semaphore(0, true);
        this.f14530L0 = null;
        this.f14532M0 = null;
        this.f14534N0 = null;
        this.f14536O0 = null;
        this.f14538P0 = null;
        this.f14540Q0 = null;
        this.f14542R0 = 0;
        this.f14544S0 = null;
        this.f14546T0 = new Object();
        this.f14548U0 = new Object();
        this.f14550V0 = false;
        this.f14552W0 = false;
        this.f14554X0 = true;
        this.f14556Y0 = false;
        this.f14558Z0 = null;
        this.f14561a1 = null;
        this.f14564b1 = new C3170g();
        this.f14531M = str;
        this.f14611z = imageItem;
        if (imageItem != null) {
            this.f14588n0 = true;
            this.f14609y = imageItem.m16141n();
        }
    }

    /* renamed from: I0 */
    public static File m16706I0(File file, AbstractC6381r<C1199a, Void> abstractC6381r) throws IOException {
        File fileCreateTempFile = File.createTempFile(UUID.randomUUID().toString(), "", null);
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
            try {
                abstractC6381r.m24506d(C2889b.m14298h().m14304f(fileInputStream, fileOutputStream));
                fileOutputStream.close();
                fileInputStream.close();
                return fileCreateTempFile;
            } finally {
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    /* renamed from: J0 */
    public static File m16708J0(String str, Uri uri, AbstractC6381r<C1199a, Void> abstractC6381r) throws IOException {
        FileOutputStream fileOutputStream;
        File fileCreateTempFile = File.createTempFile(UUID.randomUUID().toString(), "", null);
        if (uri != null) {
            InputStream inputStreamOpenInputStream = Globals.m7372O().getContentResolver().openInputStream(uri);
            try {
                fileOutputStream = new FileOutputStream(fileCreateTempFile);
                try {
                    abstractC6381r.m24506d(C2889b.m14298h().m14304f(inputStreamOpenInputStream, fileOutputStream));
                    fileOutputStream.close();
                    if (inputStreamOpenInputStream != null) {
                        inputStreamOpenInputStream.close();
                    }
                    return fileCreateTempFile;
                } finally {
                }
            } catch (Throwable th) {
                if (inputStreamOpenInputStream != null) {
                    try {
                        inputStreamOpenInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        FileInputStream fileInputStream = new FileInputStream(new File(str));
        try {
            fileOutputStream = new FileOutputStream(fileCreateTempFile);
            try {
                abstractC6381r.m24506d(C2889b.m14298h().m14304f(fileInputStream, fileOutputStream));
                fileOutputStream.close();
                fileInputStream.close();
                return fileCreateTempFile;
            } finally {
                try {
                    fileOutputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
            }
        } catch (Throwable th4) {
            try {
                fileInputStream.close();
            } catch (Throwable th5) {
                th4.addSuppressed(th5);
            }
            throw th4;
        }
    }

    /* renamed from: K0 */
    public static void m16710K0(boolean z8, UploadUtils.C3195a c3195a, File file) {
        c3195a.f14722a = file.getName();
        if (z8) {
            byte[] bArrM16954a = UploadUtils.m16954a(m16706I0(file, new C3172i(c3195a)).getAbsolutePath());
            c3195a.f14726e = bArrM16954a;
            c3195a.f14727f = m16719R0(bArrM16954a);
        } else {
            byte[] bArrM16954a2 = UploadUtils.m16954a(file.getAbsolutePath());
            c3195a.f14726e = bArrM16954a2;
            c3195a.f14727f = m16719R0(bArrM16954a2);
            c3195a.f14731j = null;
            c3195a.f14732k = null;
        }
    }

    /* renamed from: O1 */
    public static void m16715O1(Uri uri, Runnable runnable) {
        new AsyncTaskC3174k(uri, runnable).execute(new Void[0]);
    }

    /* renamed from: R0 */
    public static String m16719R0(byte[] bArr) throws NoSuchAlgorithmException {
        String strM16784z0 = null;
        if (bArr == null) {
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bArr);
            strM16784z0 = m16784z0(messageDigest.digest());
        } catch (NoSuchAlgorithmException e9) {
            e9.printStackTrace();
        }
        UploadUtils.m16965l("Upload Performance", "******* getMD5(len=" + bArr.length + ") = " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms");
        return strM16784z0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q1 */
    public /* synthetic */ void m16761q1(String str, String str2, String str3, String str4) {
        this.f14553X = UploadUtils.UploadResultType.STEP_3_BIG_FAIL;
        this.f14602u0 = FailReason.FAIL_BIG_COMPLETE;
        if (str3 == null) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_big]: statusCode == null");
        } else if (str3.equals("200")) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_big]: statusCode = 200 OK!!");
            if (m16790C1(str4, false)) {
                this.f14553X = UploadUtils.UploadResultType.STEP_3_SUCCESS;
                this.f14602u0 = FailReason.FAIL_NONE;
            }
        } else {
            UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_big] statusCode=" + str3);
        }
        synchronized (this.f14547U) {
            this.f14547U.notify();
        }
        UploadUtils.m16965l("Upload Performance", "P3 completeUploadMedia() ==== end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r1 */
    public /* synthetic */ void m16764r1(String str, String str2, String str3, String str4) {
        this.f14560a0 = this.f14594q0 ? UploadUtils.UploadResultType.STEP_2_FILE_FAIL : UploadUtils.UploadResultType.STEP_3_FILE_FAIL;
        this.f14602u0 = FailReason.FAIL_FILE_COMPLETE;
        if (str3 == null) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_file]: statusCode == null");
        } else if (str3.equals("200")) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_file]: statusCode = 200 OK!!");
            if (m16786A1(str4)) {
                this.f14560a0 = this.f14594q0 ? UploadUtils.UploadResultType.STEP_2_SUCCESS : UploadUtils.UploadResultType.STEP_3_SUCCESS;
                this.f14602u0 = FailReason.FAIL_NONE;
                CLUtility.m16455K(this.f14527K, this.f14529L);
            }
        } else {
            UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_file]: statusCode = " + str3);
        }
        synchronized (this.f14547U) {
            this.f14547U.notify();
        }
        UploadUtils.m16965l("Upload Performance", "P3 completeUploadMedia(); ==== end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s1 */
    public /* synthetic */ void m16767s1(String str, String str2, String str3, String str4) {
        this.f14553X = this.f14594q0 ? UploadUtils.UploadResultType.STEP_2_SMALL_FAIL : UploadUtils.UploadResultType.STEP_3_SMALL_FAIL;
        this.f14602u0 = FailReason.FAIL_SMALL_COMPLETE;
        if (str3 == null) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_small]: statusCode == null");
        } else {
            UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_small] statusCode=" + str3);
            if (str3.equals("200")) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_small]: statusCode = 200 OK!!");
                if (m16790C1(str4, true)) {
                    this.f14553X = this.f14594q0 ? UploadUtils.UploadResultType.STEP_2_SUCCESS : UploadUtils.UploadResultType.STEP_3_SUCCESS;
                    this.f14602u0 = FailReason.FAIL_NONE;
                }
            } else {
                UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_small]: statusCode = " + str3);
            }
        }
        synchronized (this.f14547U) {
            this.f14547U.notify();
        }
        UploadUtils.m16965l("Upload Performance", "P3 completeUploadMedia() ==== end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: t1 */
    public /* synthetic */ void m16770t1(String str, String str2, String str3, String str4) {
        this.f14557Z = this.f14594q0 ? UploadUtils.UploadResultType.STEP_2_VIDEO_FAIL : UploadUtils.UploadResultType.STEP_3_VIDEO_FAIL;
        this.f14602u0 = FailReason.FAIL_VIDEO_COMPLETE;
        if (str3 == null) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_video]: statusCode == null");
        } else if (str3.equals("200")) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_video]: statusCode = 200 OK!!");
            if (m16794E1(str4)) {
                this.f14557Z = this.f14594q0 ? UploadUtils.UploadResultType.STEP_2_SUCCESS : UploadUtils.UploadResultType.STEP_3_SUCCESS;
                this.f14602u0 = FailReason.FAIL_NONE;
                CLUtility.m16455K(this.f14523I, this.f14525J);
            }
        } else {
            UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_video]: statusCode = " + str3);
        }
        synchronized (this.f14547U) {
            this.f14547U.notify();
        }
        UploadUtils.m16965l("Upload Performance", "P3 completeUploadMedia(); ==== end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u1 */
    public /* synthetic */ void m16773u1(String str, String str2, String str3, String str4) {
        this.f14555Y = (this.f14594q0 || this.f14588n0) ? UploadUtils.UploadResultType.STEP_2_VOICE_FAIL : UploadUtils.UploadResultType.STEP_3_VOICE_FAIL;
        this.f14602u0 = FailReason.FAIL_VOICE_COMPLETE;
        if (str3 == null) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_voice]: statusCode == null");
        } else if (str3.equals("200")) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_voice]: statusCode = 200 OK!!");
            if (m16798G1(str4)) {
                this.f14555Y = (this.f14594q0 || this.f14588n0) ? UploadUtils.UploadResultType.STEP_2_SUCCESS : UploadUtils.UploadResultType.STEP_3_SUCCESS;
                this.f14602u0 = FailReason.FAIL_NONE;
            }
        } else {
            UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_voice]: statusCode = " + str3);
        }
        synchronized (this.f14547U) {
            this.f14547U.notify();
        }
        UploadUtils.m16965l("Upload Performance", "P3 completeUploadMedia(); ==== end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v1 */
    public /* synthetic */ void m16776v1(String str, String str2, String str3, String str4) {
        if (str3 == null) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadFile_step1]: mFriendsClient.SendRequest() statusCode == null");
        } else if (str3.equals("200")) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadFile_step1]: mFriendsClient.SendRequest() statusCode = 200 !!! OK");
            if (m16865z1(str4)) {
                this.f14560a0 = UploadUtils.UploadResultType.STEP_1_SUCCESS;
                new C3167d().start();
            }
        } else {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadFile_step1]: mFriendsClient.SendRequest() statusCode = " + str3);
        }
        synchronized (this.f14547U) {
            this.f14547U.notify();
        }
        UploadUtils.m16965l("Upload Performance", "P3 SendRequest(file) ==== end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w1 */
    public /* synthetic */ void m16778w1(String str, String str2, String str3, String str4) {
        if (str3 == null) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadPhoto_step1]: mFriendsClient.SendRequest() statusCode == null");
        } else if (str3.equals("200")) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadPhoto_step1]: mFriendsClient.SendRequest() statusCode = 200 !!! OK");
            if (m16788B1(str4)) {
                this.f14553X = UploadUtils.UploadResultType.STEP_1_SUCCESS;
                new C3168e().start();
                if (this.f14588n0 || this.f14596r0) {
                    new C3169f().start();
                }
            }
        } else {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadPhoto_step1]: mFriendsClient.SendRequest() statusCode = " + str3);
            if (str3.equals("403") && str4.contains("Max media limit exceeded")) {
                this.f14553X = UploadUtils.UploadResultType.STEP_1_REACH_LIMIT;
            }
        }
        synchronized (this.f14547U) {
            this.f14547U.notify();
        }
        UploadUtils.m16965l("Upload Performance", "P3 SendRequest(photo) ==== end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x1 */
    public /* synthetic */ void m16780x1(String str, String str2, String str3, String str4) {
        if (str3 == null) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVideo_step1]: mFriendsClient.SendRequest() statusCode == null");
        } else if (str3.equals("200")) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVideo_step1]: mFriendsClient.SendRequest() statusCode = 200 !!! OK");
            if (m16792D1(str4)) {
                this.f14557Z = UploadUtils.UploadResultType.STEP_1_SUCCESS;
                new C3165b().start();
                new C3166c().start();
            }
        } else {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVideo_step1]: mFriendsClient.SendRequest() statusCode = " + str3);
        }
        synchronized (this.f14547U) {
            this.f14547U.notify();
        }
        UploadUtils.m16965l("Upload Performance", "P3 SendRequest(voice) ==== end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y1 */
    public /* synthetic */ void m16782y1(String str, String str2, String str3, String str4) {
        if (str3 == null) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVoice_step1]: mFriendsClient.SendRequest() statusCode == null");
        } else if (str3.equals("200")) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVoice_step1]: mFriendsClient.SendRequest() statusCode = 200 !!! OK");
            if (m16796F1(str4)) {
                this.f14555Y = UploadUtils.UploadResultType.STEP_1_SUCCESS;
                new C3164a().start();
            }
        } else {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVoice_step1]: mFriendsClient.SendRequest() statusCode = " + str3);
        }
        synchronized (this.f14547U) {
            this.f14547U.notify();
        }
        UploadUtils.m16965l("Upload Performance", "P3 SendRequest(voice) ==== end");
    }

    /* renamed from: z0 */
    public static String m16784z0(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b9 : bArr) {
            String hexString = Integer.toHexString(b9 & UnsignedBytes.MAX_VALUE);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    /* renamed from: A0 */
    public long mo16785A0(MediaType mediaType, UploadUtils.C3195a c3195a) {
        if (!Globals.m7388i0().m7419E1() || c3195a == null) {
            return 1L;
        }
        if (c3195a.f14726e == null && TextUtils.isEmpty(c3195a.f14728g) && c3195a.f14729h == null) {
            return 1L;
        }
        long j9 = c3195a.f14730i;
        if (mediaType == MediaType.VIDEO || mediaType == MediaType.FILE) {
            return (long) Math.ceil(j9 / 5242880.0d);
        }
        return 1L;
    }

    /* renamed from: A1 */
    public final boolean m16786A1(String str) throws JSONException {
        UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadFileComplete]: ==== start");
        boolean z8 = false;
        if (this.f14604v0) {
            return false;
        }
        try {
            try {
                try {
                    C2973l0 c2973l0M20188j = C5172p.m20188j(this.f14537P, new JSONObject(str).getJSONObject("result"));
                    this.f14545T = c2973l0M20188j;
                    if (c2973l0M20188j != null) {
                        c2973l0M20188j.m15148t().f13201e = this.f14527K;
                        if (this.f14558Z0 != null) {
                            this.f14545T.m15148t().f13204h = this.f14558Z0.f5810a;
                            this.f14545T.m15148t().f13205i = this.f14558Z0.f5811b;
                        }
                        C2950b0.m14914m().m14712i(this.f14545T);
                        z8 = true;
                    }
                } catch (Exception e9) {
                    UploadUtils.m16964k("UploadMediaHelperV2", "[parseMediaUploadFileComplete]", e9);
                }
                UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadFileComplete]: ==== done !!! =========== result =" + z8);
                return z8;
            } catch (JSONException unused) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadFileComplete]: ==== done ==== 'result' missing. JSONstr=" + str);
                return false;
            }
        } catch (JSONException unused2) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadFileComplete]: ==== done ==== new JSONObject(jsonStr) fail...");
            return false;
        }
    }

    /* renamed from: B0 */
    public final void m16787B0() {
        ImageItem imageItem;
        Uri uriM16510Z1;
        if (!this.f14588n0 || (imageItem = this.f14611z) == null || !imageItem.m16148u() || (uriM16510Z1 = CLUtility.m16510Z1(this.f14611z.m16145r())) == null) {
            return;
        }
        try {
            Cursor cursorQuery = Globals.m7372O().getContentResolver().query(uriM16510Z1, null, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.getCount() > 0) {
                        Globals.m7372O().getContentResolver().delete(uriM16510Z1, null, null);
                    }
                } finally {
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: B1 */
    public boolean m16788B1(String str) {
        UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadPhoto]: ==== start");
        if (this.f14604v0) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            try {
                this.f14573g = jSONObject.getJSONObject("thumbnail").getString("1");
                UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadPhoto]: ==== done ==== 'small url' =" + this.f14573g);
                try {
                    this.f14575h = jSONObject.getJSONObject("original").getString("1");
                    UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadPhoto]: ==== done ==== 'original url' =" + this.f14575h);
                    try {
                        this.f14583l = jSONObject.getString("complete");
                        try {
                            this.f14591p = new Date(jSONObject.getLong("timeout") * 1000);
                            UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadPhoto]: ==== done !!!");
                            return true;
                        } catch (JSONException unused) {
                            UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadPhoto]: ==== done ==== 'complete' missing. JSONstr=" + str);
                            return false;
                        }
                    } catch (JSONException unused2) {
                        UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadPhoto]: ==== done ==== 'complete' missing. JSONstr=" + str);
                        return false;
                    }
                } catch (JSONException unused3) {
                    UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadPhoto]: ==== done ==== 'original' missing. JSONstr=" + str);
                    return false;
                }
            } catch (JSONException unused4) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadPhoto]: ==== done ==== 'small' missing. JSONstr=" + str);
                return false;
            }
        } catch (JSONException unused5) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadPhoto]: ==== done ==== new JSONObject(jsonStr) fail...");
            return false;
        }
    }

    /* renamed from: C0 */
    public final void m16789C0(String str, String str2) {
        UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_big]: ==== start");
        if (this.f14604v0) {
            return;
        }
        if (this.f14594q0) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_big]: ==== should not run into this path !!!!");
        } else {
            String strValueOf = this.f14590o0 ? String.valueOf(this.f14541R.m15144p()) : null;
            String str3 = this.f14568d0;
            String strM7449L = Globals.m7388i0().m7449L();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", strM7449L));
            arrayList.add(new C6301o("thumbnail", str));
            arrayList.add(new C6301o("original", str2));
            if (str3 != null) {
                arrayList.add(new C6301o(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, str3));
            }
            if (strValueOf != null) {
                arrayList.add(new C6301o("mediaNotes", strValueOf));
            }
            if (this.f14604v0) {
                return;
            }
            UploadUtils.m16965l("Upload Performance", "P3 completeUploadMedia() ==== start");
            this.f14547U.m15698B(this.f14583l, arrayList, new FriendsClient.InterfaceC3051i() { // from class: k4.e1
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str4, String str5, String str6, String str7) {
                    this.f17654a.m16761q1(str4, str5, str6, str7);
                }
            });
            try {
                synchronized (this.f14547U) {
                    this.f14547U.wait();
                }
            } catch (InterruptedException e9) {
                Thread.currentThread().interrupt();
                UploadUtils.m16964k("UploadMediaHelperV2", "[do_phase3_big]", e9);
            }
        }
        UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_big]: ==== done !!!");
    }

    /* renamed from: C1 */
    public final boolean m16790C1(String str, boolean z8) throws JSONException {
        UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadPhotoComplete]: ==== start");
        boolean z9 = false;
        if (this.f14604v0) {
            return false;
        }
        try {
            try {
                JSONObject jSONObject = new JSONObject(str).getJSONObject("result");
                try {
                    C2973l0 c2973l0M20188j = C5172p.m20188j(this.f14531M, jSONObject);
                    this.f14539Q = c2973l0M20188j;
                    if (C5170o0.m20170e(c2973l0M20188j.m15148t().f13200d)) {
                        this.f14539Q = (C2973l0) FriendsClient.m15657X(this.f14539Q.m15131c(), this.f14539Q.m15144p()).first;
                    }
                    C2973l0 c2973l0 = this.f14539Q;
                    if (c2973l0 != null) {
                        UploadUtils.C3195a c3195a = this.f14565c;
                        if (c3195a != null) {
                            c2973l0.m15124K(c3195a.f14724c);
                            this.f14539Q.m15121H(this.f14565c.f14725d);
                        } else {
                            UploadUtils.C3195a c3195a2 = this.f14562b;
                            if (c3195a2 != null) {
                                int i9 = c3195a2.f14724c;
                                int i10 = c3195a2.f14725d;
                                float f9 = 1280.0f / (i9 > i10 ? i9 : i10);
                                if (i9 > i10) {
                                    c2973l0.m15124K(1280);
                                    this.f14539Q.m15121H(Math.round(this.f14562b.f14725d * f9));
                                } else {
                                    c2973l0.m15124K(Math.round(i9 * f9));
                                    this.f14539Q.m15121H(1280);
                                }
                            }
                        }
                        if (z8) {
                            UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadPhotoComplete]: ==== small = downloadURL = " + this.f14539Q.m15149u().f13200d);
                            CLUtility.m16451J(this.f14507A);
                            this.f14507A = null;
                            this.f14539Q.m15149u().f13201e = this.f14596r0 ? this.f14519G.f14654b : this.f14598s0 ? this.f14521H : this.f14611z.m16144q();
                            if (this.f14561a1 != null) {
                                this.f14539Q.m15149u().f13204h = this.f14561a1.f5810a;
                                this.f14539Q.m15149u().f13205i = this.f14561a1.f5811b;
                            }
                            if (this.f14558Z0 != null) {
                                this.f14539Q.m15148t().f13204h = this.f14558Z0.f5810a;
                                this.f14539Q.m15148t().f13205i = this.f14558Z0.f5811b;
                            }
                            MediaLoader.m7150i(Globals.m7372O(), this.f14539Q, true);
                        } else {
                            UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadPhotoComplete]: ==== big = downloadURL = " + this.f14539Q.m15148t().f13200d);
                            CLUtility.m16451J(this.f14509B);
                            this.f14509B = null;
                            this.f14539Q.m15148t().f13201e = this.f14596r0 ? this.f14519G.f14653a : this.f14611z.m16144q();
                            if (this.f14561a1 != null) {
                                this.f14539Q.m15149u().f13204h = this.f14561a1.f5810a;
                                this.f14539Q.m15149u().f13205i = this.f14561a1.f5811b;
                            }
                            if (this.f14558Z0 != null) {
                                this.f14539Q.m15148t().f13204h = this.f14558Z0.f5810a;
                                this.f14539Q.m15148t().f13205i = this.f14558Z0.f5811b;
                            }
                            MediaLoader.m7150i(Globals.m7372O(), this.f14539Q, false);
                        }
                        C2950b0.m14914m().m14712i(this.f14539Q);
                        C3061a.a aVarM20192n = C5172p.m20192n(jSONObject);
                        if (aVarM20192n != null && !aVarM20192n.m15800a()) {
                            C2950b0.m14915n().m15107f(new C2971k0(String.valueOf(this.f14539Q.m15144p()), aVarM20192n));
                        }
                        z9 = true;
                    }
                } catch (Exception e9) {
                    UploadUtils.m16964k("UploadMediaHelperV2", "[parseMediaUploadPhotoComplete]", e9);
                }
                UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadPhotoComplete]: ==== done !!! =========== result =" + z9);
                return z9;
            } catch (JSONException unused) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadPhotoComplete]: ==== done ==== 'result' missing. JSONstr=" + str);
                return false;
            }
        } catch (JSONException unused2) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadPhotoComplete]: ==== done ==== new JSONObject(jsonStr) fail...");
            return false;
        }
    }

    /* renamed from: D0 */
    public void mo16791D0(String str) {
        UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_file]: ==== start");
        if (this.f14604v0) {
            return;
        }
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("original", str));
        arrayList.add(new C6301o("partAmount", String.valueOf(this.f14581k.f14734b)));
        UploadUtils.m16965l("Upload Performance", "P3 completeUploadMedia() ==== start");
        this.f14547U.m15698B(this.f14589o, arrayList, new FriendsClient.InterfaceC3051i() { // from class: k4.c1
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str2, String str3, String str4, String str5) {
                this.f17644a.m16764r1(str2, str3, str4, str5);
            }
        });
        try {
            synchronized (this.f14547U) {
                this.f14547U.wait();
            }
        } catch (InterruptedException e9) {
            UploadUtils.m16964k("UploadMediaHelperV2", "[do_phase3_file]", e9);
        }
        UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_file]: ==== done !!!");
    }

    /* renamed from: D1 */
    public final boolean m16792D1(String str) throws JSONException, NumberFormatException {
        JSONObject jSONObject;
        long j9;
        UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadVideo]: ==== start");
        if (this.f14604v0) {
            return false;
        }
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            try {
                this.f14573g = jSONObject2.getJSONObject("thumbnail").getString("1");
                try {
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("original");
                    if (this.f14579j.f14733a) {
                        long length = jSONObject3.length();
                        UploadUtils.C3196b c3196b = this.f14579j;
                        if (length != c3196b.f14734b) {
                            UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadVideo]: ==== done ==== Url size is no expected: " + jSONObject3.length());
                            return false;
                        }
                        c3196b.m16967b();
                        int i9 = 1;
                        while (true) {
                            long j10 = i9;
                            if (j10 <= this.f14579j.f14734b) {
                                try {
                                    int i10 = Integer.parseInt(String.valueOf(i9));
                                    if (j10 == this.f14579j.f14734b) {
                                        jSONObject = jSONObject3;
                                        long j11 = this.f14569e.f14730i;
                                        if (j11 % 5242880 != 0) {
                                            j9 = j11 % 5242880;
                                        }
                                        jSONObject3 = jSONObject;
                                        this.f14579j.m16966a(MediaType.VIDEO, i10, jSONObject3.getString(String.valueOf(i9)), j9, Math.max(0L, (i9 - 1) * 5242880), false);
                                        i9++;
                                    } else {
                                        jSONObject = jSONObject3;
                                    }
                                    j9 = 5242880;
                                    jSONObject3 = jSONObject;
                                    this.f14579j.m16966a(MediaType.VIDEO, i10, jSONObject3.getString(String.valueOf(i9)), j9, Math.max(0L, (i9 - 1) * 5242880), false);
                                    i9++;
                                } catch (NumberFormatException e9) {
                                    UploadUtils.m16964k("UploadMediaHelperV2", "[parseMediaUploadVideo]: ==== done ==== Cannot parse url index to int", e9);
                                    return false;
                                }
                            }
                        }
                    } else {
                        try {
                            try {
                                this.f14579j.m16966a(MediaType.VIDEO, Integer.parseInt("1"), jSONObject3.getString("1"), this.f14569e.f14730i, 0L, false);
                            } catch (Exception e10) {
                                UploadUtils.m16964k("UploadMediaHelperV2", "[parseMediaUploadVideo]", e10);
                                return false;
                            }
                        } catch (NumberFormatException e11) {
                            UploadUtils.m16964k("UploadMediaHelperV2", "[parseMediaUploadVideo]: ==== done ==== Cannot parse url index to int", e11);
                            return false;
                        }
                    }
                    try {
                        this.f14587n = jSONObject2.getString("complete");
                        try {
                            this.f14595r = new Date(jSONObject2.getLong("timeout") * 1000);
                            UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadVideo]: ==== done !!!");
                            return true;
                        } catch (JSONException unused) {
                            UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadVideo]: ==== done ==== 'complete' missing. JSONstr=" + str);
                            return false;
                        }
                    } catch (JSONException unused2) {
                        UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadVideo]: ==== done ==== 'complete' missing. JSONstr=" + str);
                        return false;
                    }
                } catch (JSONException unused3) {
                    UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadVideo]: ==== done ==== 'original' missing. JSONstr=" + str);
                    return false;
                }
            } catch (JSONException unused4) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadVideo]: ==== done ==== 'small' missing. JSONstr=" + str);
                return false;
            }
        } catch (JSONException unused5) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadVideo]: ==== done ==== new JSONObject(jsonStr) fail...");
            return false;
        }
    }

    /* renamed from: E0 */
    public void mo16793E0(String str, String str2) {
        UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_small]: ==== start");
        if (this.f14604v0) {
            return;
        }
        if (!this.f14594q0) {
            this.f14553X = UploadUtils.UploadResultType.STEP_3_SMALL_FAIL;
            this.f14602u0 = FailReason.FAIL_SMALL_COMPLETE;
            UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_small] out === none message case");
            return;
        }
        String strValueOf = this.f14590o0 ? String.valueOf(this.f14541R.m15144p()) : null;
        String str3 = this.f14568d0;
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("thumbnail", str));
        arrayList.add(new C6301o("original", str2));
        if (str3 != null) {
            arrayList.add(new C6301o(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, str3));
        }
        if (strValueOf != null) {
            arrayList.add(new C6301o("mediaNotes", strValueOf));
        }
        UploadUtils.m16965l("Upload Performance", "P3 completeUploadMedia() ==== start");
        this.f14547U.m15698B(this.f14583l, arrayList, new FriendsClient.InterfaceC3051i() { // from class: k4.x0
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str4, String str5, String str6, String str7) {
                this.f17773a.m16767s1(str4, str5, str6, str7);
            }
        });
        try {
            synchronized (this.f14547U) {
                this.f14547U.wait();
            }
        } catch (InterruptedException e9) {
            Thread.currentThread().interrupt();
            UploadUtils.m16964k("UploadMediaHelperV2", "[do_phase3_small]", e9);
        }
        UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_small]: ==== done !!!");
    }

    /* renamed from: E1 */
    public final boolean m16794E1(String str) throws JSONException {
        UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadVideoComplete]: ==== start");
        boolean z8 = false;
        if (this.f14604v0) {
            return false;
        }
        try {
            try {
                try {
                    C2973l0 c2973l0M20188j = C5172p.m20188j(this.f14535O, new JSONObject(str).getJSONObject("result"));
                    this.f14543S = c2973l0M20188j;
                    if (c2973l0M20188j != null) {
                        c2973l0M20188j.m15148t().f13201e = this.f14523I;
                        C2950b0.m14914m().m14712i(this.f14543S);
                        z8 = true;
                    }
                } catch (Exception e9) {
                    UploadUtils.m16964k("UploadMediaHelperV2", "[parseMediaUploadVideoComplete]", e9);
                }
                UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadVideoComplete]: ==== done !!! =========== result =" + z8);
                return z8;
            } catch (JSONException unused) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadVideoComplete]: ==== done ==== 'result' missing. JSONstr=" + str);
                return false;
            }
        } catch (JSONException unused2) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadVideoComplete]: ==== done ==== new JSONObject(jsonStr) fail...");
            return false;
        }
    }

    /* renamed from: F0 */
    public void mo16795F0(String str, String str2) {
        UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_video]: ==== start");
        if (this.f14604v0) {
            return;
        }
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("thumbnail", str));
        arrayList.add(new C6301o("original", str2));
        arrayList.add(new C6301o("partAmount", String.valueOf(this.f14579j.f14734b)));
        VideoItem videoItem = this.f14515E;
        if (videoItem != null && videoItem.m16215d()) {
            arrayList.add(new C6301o("transcode", "1"));
        }
        UploadUtils.m16965l("Upload Performance", "P3 completeUploadMedia() ==== start");
        this.f14547U.m15698B(this.f14587n, arrayList, new FriendsClient.InterfaceC3051i() { // from class: k4.a1
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str3, String str4, String str5, String str6) {
                this.f17630a.m16770t1(str3, str4, str5, str6);
            }
        });
        try {
            synchronized (this.f14547U) {
                this.f14547U.wait();
            }
        } catch (InterruptedException e9) {
            UploadUtils.m16964k("UploadMediaHelperV2", "[do_phase3_video]", e9);
        }
        UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_video]: ==== done !!!");
    }

    /* renamed from: F1 */
    public final boolean m16796F1(String str) {
        UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadVoice]: ==== start");
        if (this.f14604v0) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            try {
                this.f14577i = jSONObject.getJSONObject("original").getString("1");
                try {
                    this.f14585m = jSONObject.getString("complete");
                    try {
                        this.f14593q = new Date(jSONObject.getLong("timeout") * 1000);
                        UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadVoice]: ==== done !!!");
                        return true;
                    } catch (JSONException unused) {
                        UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadPhoto]: ==== done ==== 'complete' missing. JSONstr=" + str);
                        return false;
                    }
                } catch (JSONException unused2) {
                    UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadVoice]: ==== done ==== 'complete' missing. JSONstr=" + str);
                    return false;
                }
            } catch (JSONException unused3) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadVoice]: ==== done ==== 'original' missing. JSONstr=" + str);
                return false;
            }
        } catch (JSONException unused4) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadVoice]: ==== done ==== new JSONObject(jsonStr) fail...");
            return false;
        }
    }

    /* renamed from: G0 */
    public final void m16797G0(String str, String str2) {
        UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_voice]: ==== start");
        if (this.f14604v0) {
            return;
        }
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("thumbnail", str));
        arrayList.add(new C6301o("original", str2));
        UploadUtils.m16965l("Upload Performance", "P3 completeUploadMedia() ==== start");
        this.f14547U.m15698B(this.f14585m, arrayList, new FriendsClient.InterfaceC3051i() { // from class: k4.y0
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str3, String str4, String str5, String str6) {
                this.f17779a.m16773u1(str3, str4, str5, str6);
            }
        });
        try {
            synchronized (this.f14547U) {
                this.f14547U.wait();
            }
        } catch (InterruptedException e9) {
            UploadUtils.m16964k("UploadMediaHelperV2", "[do_phase3_voice]", e9);
        }
        UploadUtils.m16965l("UploadMediaHelperV2", "[do_phase3_voice]: ==== done !!!");
    }

    /* renamed from: G1 */
    public final boolean m16798G1(String str) throws JSONException {
        UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadVoiceComplete]: ==== start");
        boolean z8 = false;
        if (this.f14604v0) {
            return false;
        }
        try {
            try {
                try {
                    C2973l0 c2973l0M20188j = C5172p.m20188j(this.f14533N, new JSONObject(str).getJSONObject("result"));
                    this.f14541R = c2973l0M20188j;
                    if (c2973l0M20188j != null) {
                        Context baseContext = Globals.m7388i0().getBaseContext();
                        String name = new File(this.f14511C).getName();
                        this.f14541R.m15148t().f13201e = CLUtility.m16472O0(baseContext) + File.separator + name;
                        C2950b0.m14914m().m14712i(this.f14541R);
                        z8 = true;
                    }
                } catch (Exception e9) {
                    UploadUtils.m16964k("UploadMediaHelperV2", "[parseMediaUploadVoiceComplete]", e9);
                }
                UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadVoiceComplete]: ==== done !!! =========== result =" + z8);
                return z8;
            } catch (JSONException unused) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadVoiceComplete]: ==== done ==== 'result' missing. JSONstr=" + str);
                return false;
            }
        } catch (JSONException unused2) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadVoiceComplete]: ==== done ==== new JSONObject(jsonStr) fail...");
            return false;
        }
    }

    /* renamed from: H0 */
    public void m16799H0() {
        this.f14556Y0 = true;
    }

    /* renamed from: H1 */
    public final boolean m16800H1() {
        UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMedia]: ==== start");
        boolean zM16809N1 = this.f14590o0 ? true & m16809N1() : true;
        if (this.f14592p0) {
            zM16809N1 &= m16807M1();
        }
        if (this.f14600t0) {
            zM16809N1 &= m16802J1();
        }
        if (this.f14588n0) {
            zM16809N1 &= m16805L1();
        }
        if (this.f14596r0) {
            zM16809N1 &= m16801I1();
        }
        if (this.f14598s0) {
            zM16809N1 &= m16803K1();
        }
        UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMedia]: ==== done !!!!");
        return zM16809N1;
    }

    /* renamed from: I1 */
    public final boolean m16801I1() {
        boolean z8;
        UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaDoodle]: ==== start");
        boolean z9 = false;
        try {
            if (!this.f14596r0 || this.f14519G == null) {
                z9 = true;
            } else {
                if (this.f14588n0) {
                    UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaDoodle]: ==== already had photo to upload, cannot upload doodle !!!!");
                    z8 = false;
                } else {
                    z8 = true;
                }
                C3184u c3184u = this.f14519G;
                this.f14507A = c3184u.f14654b;
                this.f14509B = c3184u.f14653a;
                UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaDoodle]: ==== doodle:" + this.f14509B + ", thumb:" + this.f14507A);
                if (this.f14562b == null) {
                    this.f14562b = new UploadUtils.C3195a();
                }
                Pair<Integer, Integer> pairM16503X0 = CLUtility.m16503X0(this.f14507A, null);
                this.f14562b.f14724c = ((Integer) pairM16503X0.first).intValue();
                this.f14562b.f14725d = ((Integer) pairM16503X0.second).intValue();
                this.f14562b.f14723b = new File(this.f14507A).getName();
                this.f14562b.f14722a = new File(this.f14507A).getName();
                this.f14562b.f14726e = UploadUtils.m16954a(this.f14507A);
                UploadUtils.C3195a c3195a = this.f14562b;
                c3195a.f14727f = m16719R0(c3195a.f14726e);
                if (this.f14565c == null) {
                    this.f14565c = new UploadUtils.C3195a();
                }
                Pair<Integer, Integer> pairM16503X02 = CLUtility.m16503X0(this.f14509B, null);
                this.f14565c.f14724c = ((Integer) pairM16503X02.first).intValue();
                this.f14565c.f14725d = ((Integer) pairM16503X02.second).intValue();
                this.f14565c.f14723b = new File(this.f14509B).getName();
                this.f14565c.f14722a = new File(this.f14509B).getName();
                this.f14565c.f14726e = UploadUtils.m16954a(this.f14509B);
                UploadUtils.C3195a c3195a2 = this.f14565c;
                c3195a2.f14727f = m16719R0(c3195a2.f14726e);
                z9 = z8;
            }
        } catch (Exception e9) {
            UploadUtils.m16964k("UploadMediaHelperV2", "[prepareUploadMediaDoodle]", e9);
        }
        UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaDoodle]: ==== smfPreparePhotoSync.release(1) ====");
        this.f14526J0.release(1);
        UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaDoodle]: ==== done !!!!");
        return z9;
    }

    /* renamed from: J1 */
    public final boolean m16802J1() {
        UploadUtils.m16965l("UploadMediaHelperV2", " [prepareUploadMediaFile]: ==== start");
        boolean z8 = false;
        try {
            if (this.f14600t0 && CLUtility.m16613z1(this.f14527K, this.f14529L)) {
                UploadUtils.m16965l("UploadMediaHelperV2", " [prepareUploadMediaFile]: ==== filePath:" + this.f14527K + " fileUri:" + this.f14529L);
                if (this.f14571f == null) {
                    this.f14571f = new UploadUtils.C3195a();
                }
                this.f14571f.f14726e = null;
                File fileM16708J0 = this.f14556Y0 ? m16708J0(this.f14527K, this.f14529L, new C3171h()) : null;
                if (fileM16708J0 != null) {
                    this.f14571f.f14728g = fileM16708J0.getAbsolutePath();
                    this.f14571f.f14729h = Uri.fromFile(fileM16708J0);
                    this.f14571f.f14730i = fileM16708J0.length();
                    this.f14571f.f14727f = m16813Q0(fileM16708J0.getAbsolutePath());
                } else {
                    UploadUtils.C3195a c3195a = this.f14571f;
                    String str = this.f14527K;
                    c3195a.f14728g = str;
                    Uri uri = this.f14529L;
                    c3195a.f14729h = uri;
                    c3195a.f14730i = CLUtility.m16572p0(str, uri);
                    this.f14571f.f14727f = m16813Q0(this.f14527K);
                }
                UploadUtils.C3196b c3196b = this.f14581k;
                if (c3196b != null) {
                    c3196b.f14734b = mo16785A0(MediaType.FILE, this.f14571f);
                    UploadUtils.C3196b c3196b2 = this.f14581k;
                    long j9 = c3196b2.f14734b;
                    boolean z9 = j9 > 1;
                    c3196b2.f14733a = z9;
                    f14506c1 = ((int) j9) * 2;
                    if (z9) {
                        UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaFile] Enable Multipart size=" + this.f14581k.f14734b + " and Max retry count=" + f14506c1);
                    }
                }
                UploadUtils.C3195a c3195a2 = this.f14571f;
                FileItem fileItem = this.f14517F;
                c3195a2.f14723b = fileItem != null ? fileItem.m16114a() : "";
                UploadUtils.C3195a c3195a3 = this.f14571f;
                FileItem fileItem2 = this.f14517F;
                c3195a3.f14722a = fileItem2 != null ? fileItem2.m16114a() : "";
                z8 = true;
            } else {
                String str2 = Constants.IPC_BUNDLE_KEY_SEND_ERROR;
                if (!CLUtility.m16613z1(this.f14527K, this.f14529L)) {
                    str2 = String.format("[prepareUploadMediaFile] File path dose not exist!! (%s)", "filePath:" + this.f14527K + " fileUri:" + this.f14529L);
                } else if (!this.f14600t0) {
                    str2 = "[prepareUploadMediaFile] No file item";
                }
                UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaFile] Prepare upload file failed: " + str2);
            }
        } catch (Exception e9) {
            UploadUtils.m16964k("UploadMediaHelperV2", "[prepareUploadMediaFile]", e9);
        }
        UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaFile]: ==== smfPrepareVideoSync.release(1) ====");
        this.f14528K0.release(1);
        UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaFile]: ==== done !!!!");
        return z8;
    }

    /* renamed from: K1 */
    public final boolean m16803K1() {
        boolean z8;
        UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaLocationSnap]: ==== start");
        boolean z9 = false;
        try {
            if (!this.f14598s0 || this.f14521H == null) {
                z9 = true;
            } else {
                if (this.f14588n0) {
                    UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaLocationSnap]: ==== already had photo to upload, cannot upload Location snapshot image !!!!");
                    z8 = false;
                } else {
                    z8 = true;
                }
                this.f14507A = this.f14521H;
                this.f14509B = null;
                UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaLocationSnap]: ==== map:" + this.f14507A);
                if (this.f14562b == null) {
                    this.f14562b = new UploadUtils.C3195a();
                }
                Pair<Integer, Integer> pairM16503X0 = CLUtility.m16503X0(this.f14507A, null);
                this.f14562b.f14724c = ((Integer) pairM16503X0.first).intValue();
                this.f14562b.f14725d = ((Integer) pairM16503X0.second).intValue();
                this.f14562b.f14723b = new File(this.f14507A).getName();
                this.f14562b.f14722a = new File(this.f14507A).getName();
                this.f14562b.f14726e = UploadUtils.m16954a(this.f14507A);
                UploadUtils.C3195a c3195a = this.f14562b;
                c3195a.f14727f = m16719R0(c3195a.f14726e);
                this.f14565c = null;
                z9 = z8;
            }
        } catch (Exception e9) {
            UploadUtils.m16964k("UploadMediaHelperV2", "[prepareUploadMediaLocationSnap]", e9);
        }
        UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaLocationSnap]: ==== smfPreparePhotoSync.release(1) ====");
        this.f14526J0.release(1);
        UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaLocationSnap]: ==== done !!!!");
        return z9;
    }

    /* renamed from: L0 */
    public final String m16804L0() {
        HashMap map = new HashMap();
        map.put("albumId", this.f14531M);
        ImageItem imageItem = this.f14611z;
        map.put("imageItem", imageItem != null ? imageItem.m16127H() : null);
        map.put("bigPath", this.f14509B);
        map.put("smallPath", this.f14507A);
        map.put("voiceAlbumId", this.f14533N);
        map.put("voicePath", this.f14511C);
        map.put("hasVoice", Boolean.valueOf(this.f14590o0));
        map.put("hasPhoto", Boolean.valueOf(this.f14588n0));
        map.put("hasMessage", Boolean.valueOf(this.f14594q0));
        map.put("commentText", this.f14568d0);
        map.put("voiceDuration", this.f14570e0);
        map.put("thumbnailURL", this.f14573g);
        map.put("originalURL", this.f14575h);
        Date date = this.f14591p;
        map.put("photoTimeout", date != null ? Long.valueOf(date.getTime()) : null);
        map.put("photoCompleteURL", this.f14583l);
        map.put("voiceUploadURL", this.f14577i);
        Date date2 = this.f14593q;
        map.put("voiceTimeout", date2 != null ? Long.valueOf(date2.getTime()) : null);
        map.put("voiceCompleteURL", this.f14585m);
        C2973l0 c2973l0 = this.f14539Q;
        map.put("photoMediaObj", c2973l0 != null ? c2973l0.m15128O() : null);
        C2973l0 c2973l02 = this.f14541R;
        map.put("voiceMediaObj", c2973l02 != null ? c2973l02.m15128O() : null);
        map.put("photoStatus", Integer.valueOf(this.f14553X.ordinal()));
        map.put("voiceStatus", Integer.valueOf(this.f14555Y.ordinal()));
        map.put("failReason", Integer.valueOf(this.f14602u0.ordinal()));
        map.put("messageID", this.f14566c0);
        UploadUtils.C3195a c3195a = this.f14562b;
        map.put("smallUploadMedia", c3195a != null ? c3195a.toString() : null);
        UploadUtils.C3195a c3195a2 = this.f14565c;
        map.put("bigUploadMedia", c3195a2 != null ? c3195a2.toString() : null);
        UploadUtils.C3195a c3195a3 = this.f14567d;
        map.put("voiceUploadMedia", c3195a3 != null ? c3195a3.toString() : null);
        map.put("chatId", this.f14584l0);
        map.put("hasVideo", Boolean.valueOf(this.f14592p0));
        VideoItem videoItem = this.f14515E;
        map.put("videoItem", videoItem != null ? videoItem.m16227p() : null);
        map.put("videoAlbumId", this.f14535O);
        map.put("videoDuration", this.f14572f0);
        Date date3 = this.f14595r;
        map.put("videoTimeout", date3 != null ? Long.valueOf(date3.getTime()) : null);
        map.put("videoCompleteURL", this.f14587n);
        C2973l0 c2973l03 = this.f14543S;
        map.put("videoMediaObj", c2973l03 != null ? c2973l03.m15128O() : null);
        map.put("videoStatus", Integer.valueOf(this.f14557Z.ordinal()));
        UploadUtils.C3196b c3196b = this.f14579j;
        map.put("videoUploadInfo", c3196b != null ? c3196b.m16973h() : null);
        if (this.f14600t0) {
            map.put("hasFile", Boolean.TRUE);
            FileItem fileItem = this.f14517F;
            if (fileItem != null) {
                map.put("fileItem", fileItem.m16119g());
            }
            map.put("fileAlbumId", this.f14537P);
            Date date4 = this.f14597s;
            if (date4 != null) {
                map.put("fileTimeout", Long.valueOf(date4.getTime()));
            }
            map.put("fileCompleteURL", this.f14589o);
            C2973l0 c2973l04 = this.f14545T;
            if (c2973l04 != null) {
                map.put("fileMediaObj", c2973l04.m15128O());
            }
            map.put("fileStatus", Integer.valueOf(this.f14560a0.ordinal()));
            UploadUtils.C3196b c3196b2 = this.f14581k;
            if (c3196b2 != null) {
                map.put("fileUploadInfo", c3196b2.m16973h());
            }
        }
        map.put("hasDoodle", Boolean.valueOf(this.f14596r0));
        C3184u c3184u = this.f14519G;
        map.put("doodleItem", c3184u != null ? c3184u.m16872a() : null);
        map.put("hasLocationSnap", Boolean.valueOf(this.f14598s0));
        map.put("locationSnapPath", this.f14521H);
        String string = new JSONObject(map).toString();
        this.f14586m0 = string;
        return string;
    }

    /* renamed from: L1 */
    public final boolean m16805L1() throws Throwable {
        ImageItem imageItem;
        UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaPhoto]: ==== start");
        boolean z8 = false;
        try {
            if (!this.f14588n0 || (imageItem = this.f14611z) == null) {
                z8 = true;
            } else {
                String strValueOf = String.valueOf(imageItem.m16142o());
                String strM16144q = this.f14611z.m16144q();
                Uri uriM16510Z1 = CLUtility.m16510Z1(this.f14611z.m16145r());
                UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaPhoto]: ==== photo:" + strM16144q);
                UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaPhoto]: ==== photo uri:" + uriM16510Z1);
                long jCurrentTimeMillis = System.currentTimeMillis();
                UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaPhoto] CLUtility.obtainImages() ==== enter");
                Pair<String, String> pairM16485R1 = CLUtility.m16485R1(strValueOf, strM16144q, uriM16510Z1, -1, false);
                UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaPhoto] CLUtility.obtainImages() ==== leave (" + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms)");
                if (pairM16485R1 == null) {
                    UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaPhoto]: ==== obtain images fail !!!!");
                } else {
                    this.f14507A = (String) pairM16485R1.first;
                    this.f14509B = (String) pairM16485R1.second;
                    if (this.f14562b == null) {
                        this.f14562b = new UploadUtils.C3195a();
                    }
                    Pair<Integer, Integer> pairM16503X0 = CLUtility.m16503X0(this.f14507A, null);
                    this.f14562b.f14724c = ((Integer) pairM16503X0.first).intValue();
                    this.f14562b.f14725d = ((Integer) pairM16503X0.second).intValue();
                    this.f14562b.f14723b = this.f14611z.m16147t();
                    m16710K0(this.f14556Y0, this.f14562b, new File(this.f14507A));
                    if (this.f14556Y0) {
                        UploadUtils.C3195a c3195a = this.f14562b;
                        this.f14561a1 = C1199a.m5277a(c3195a.f14731j, c3195a.f14732k);
                    }
                    if (this.f14565c == null) {
                        this.f14565c = new UploadUtils.C3195a();
                    }
                    Pair<Integer, Integer> pairM16503X02 = CLUtility.m16503X0(this.f14509B, null);
                    this.f14565c.f14724c = ((Integer) pairM16503X02.first).intValue();
                    this.f14565c.f14725d = ((Integer) pairM16503X02.second).intValue();
                    this.f14565c.f14723b = this.f14611z.m16147t();
                    m16710K0(this.f14556Y0, this.f14565c, new File(this.f14509B));
                    if (this.f14556Y0) {
                        UploadUtils.C3195a c3195a2 = this.f14565c;
                        this.f14558Z0 = C1199a.m5277a(c3195a2.f14731j, c3195a2.f14732k);
                    }
                    z8 = true;
                }
            }
        } catch (Exception e9) {
            UploadUtils.m16964k("UploadMediaHelperV2", "[prepareUploadMediaPhoto]", e9);
        }
        UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaPhoto]: ==== smfPreparePhotoSync.release(1) ====");
        this.f14526J0.release(1);
        UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaPhoto]: ==== done !!!!");
        return z8;
    }

    /* renamed from: M0 */
    public String m16806M0() {
        return this.f14584l0;
    }

    /* renamed from: M1 */
    public final boolean m16807M1() {
        UploadUtils.m16965l("UploadMediaHelperV2", " [prepareUploadMediaVideo]: ==== start");
        VideoItem videoItem = this.f14515E;
        boolean z8 = false;
        if (videoItem == null) {
            UploadUtils.m16965l("UploadMediaHelperV2", " [prepareUploadMediaVideo] mVideoItem null");
            return false;
        }
        try {
            this.f14507A = videoItem.m16216e();
            if (this.f14592p0 && CLUtility.m16613z1(this.f14523I, this.f14525J) && new File(this.f14507A).exists()) {
                UploadUtils.m16965l("UploadMediaHelperV2", " [prepareUploadMediaVideo]: ==== video:" + this.f14523I + ", videoUri:" + this.f14525J + ", thumb:" + this.f14507A);
                if (this.f14562b == null) {
                    this.f14562b = new UploadUtils.C3195a();
                }
                Pair<Integer, Integer> pairM16503X0 = CLUtility.m16503X0(this.f14507A, null);
                this.f14562b.f14724c = ((Integer) pairM16503X0.first).intValue();
                this.f14562b.f14725d = ((Integer) pairM16503X0.second).intValue();
                this.f14562b.f14723b = this.f14515E.m16212a();
                this.f14562b.f14722a = this.f14515E.m16212a();
                this.f14562b.f14726e = UploadUtils.m16954a(this.f14507A);
                UploadUtils.C3195a c3195a = this.f14562b;
                c3195a.f14727f = m16719R0(c3195a.f14726e);
                if (this.f14569e == null) {
                    this.f14569e = new UploadUtils.C3195a();
                }
                UploadUtils.C3195a c3195a2 = this.f14569e;
                c3195a2.f14726e = null;
                String str = this.f14523I;
                c3195a2.f14728g = str;
                Uri uri = this.f14525J;
                c3195a2.f14729h = uri;
                c3195a2.f14730i = CLUtility.m16572p0(str, uri);
                this.f14569e.f14727f = m16813Q0(this.f14523I);
                UploadUtils.C3196b c3196b = this.f14579j;
                if (c3196b != null) {
                    c3196b.f14734b = mo16785A0(MediaType.VIDEO, this.f14569e);
                    UploadUtils.C3196b c3196b2 = this.f14579j;
                    long j9 = c3196b2.f14734b;
                    boolean z9 = j9 > 1;
                    c3196b2.f14733a = z9;
                    f14506c1 = ((int) j9) * 2;
                    if (z9) {
                        UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaVideo] Enable Multipart size=" + this.f14579j.f14734b + " and Max retry count=" + f14506c1);
                    }
                }
                this.f14569e.f14723b = this.f14515E.m16212a();
                this.f14569e.f14722a = this.f14515E.m16212a();
                z8 = true;
            } else {
                String str2 = Constants.IPC_BUNDLE_KEY_SEND_ERROR;
                if (!CLUtility.m16613z1(this.f14523I, this.f14525J)) {
                    str2 = String.format("[prepareUploadMediaVideo] Video path or uri dose not exist!! (%s)", "videoPath:" + this.f14523I + " videoUri:" + this.f14525J);
                } else if (!new File(this.f14507A).exists()) {
                    str2 = String.format("[prepareUploadMediaVideo] Video thumbnail path dose not exist!! (%s)", this.f14507A);
                } else if (!this.f14592p0) {
                    str2 = "[prepareUploadMediaVideo] No video item";
                }
                UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaVideo] Prepare upload video failed: " + str2);
            }
        } catch (Exception e9) {
            UploadUtils.m16964k("UploadMediaHelperV2", "[prepareUploadMediaVideo]", e9);
        }
        UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaVideo]: ==== smfPrepareVideoSync.release(1) ====");
        this.f14524I0.release(1);
        UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaVideo]: ==== done !!!!");
        return z8;
    }

    /* renamed from: N0 */
    public C2973l0 m16808N0() {
        return this.f14545T;
    }

    /* renamed from: N1 */
    public final boolean m16809N1() {
        boolean z8;
        UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaVoice]: ==== start");
        try {
            if (this.f14590o0 && new File(this.f14511C).exists()) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaVoice]: ==== voice:" + this.f14511C);
                if (this.f14567d == null) {
                    this.f14567d = new UploadUtils.C3195a();
                }
                this.f14567d.f14726e = UploadUtils.m16956c(this.f14511C, this.f14525J);
                this.f14567d.f14723b = CLUtility.m16552k0(this.f14523I, this.f14525J);
                this.f14567d.f14722a = CLUtility.m16552k0(this.f14523I, this.f14525J);
                UploadUtils.C3195a c3195a = this.f14567d;
                c3195a.f14727f = m16719R0(c3195a.f14726e);
            }
            z8 = true;
        } catch (Exception e9) {
            UploadUtils.m16964k("UploadMediaHelperV2", "[prepareUploadMediaVoice]", e9);
            z8 = false;
        }
        UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaVoice]: ==== smfPrepareVoiceSync.release(1) ====");
        this.f14522H0.release(1);
        UploadUtils.m16965l("UploadMediaHelperV2", "[prepareUploadMediaVoice]: ==== done !!!!");
        return z8;
    }

    /* renamed from: O0 */
    public UploadUtils.UploadResultType m16810O0() {
        return this.f14560a0;
    }

    /* renamed from: P0 */
    public final UploadMediaHelper m16811P0() {
        return this;
    }

    /* renamed from: P1 */
    public void m16812P1() {
        this.f14542R0 = 0;
    }

    /* renamed from: Q0 */
    public final String m16813Q0(String str) {
        return "Ignored";
    }

    /* renamed from: Q1 */
    public void m16814Q1(InterfaceC3183t interfaceC3183t) {
        this.f14551W = interfaceC3183t;
    }

    /* renamed from: R1 */
    public void m16815R1(AbstractC3185v abstractC3185v) {
        this.f14549V = abstractC3185v;
    }

    /* renamed from: S0 */
    public int m16816S0() {
        return f14506c1;
    }

    /* renamed from: S1 */
    public boolean m16817S1(String str) {
        if (str == null) {
            return false;
        }
        this.f14586m0 = str;
        return m16862w0();
    }

    /* renamed from: T0 */
    public InterfaceC3183t m16818T0() {
        return this.f14551W;
    }

    /* renamed from: T1 */
    public void m16819T1() {
        Log.d("UploadMediaHelperQueue", "=============== startUpload ==================");
        UploadUtils.m16965l("Upload Performance", "Trace upload process >>>>>>>>>>>>>>>>>>>>>>>>>>>");
        UploadUtils.m16965l("Upload Performance", "1. UploadMediaHelper.startUpload(); ======== start");
        UploadUtils.m16965l("UploadMediaHelperV2", "[startUpload]: ==== start");
        UploadUtils.m16965l("UploadMediaHelperV2", "[startUpload]: hasPhoto:" + this.f14588n0 + " hasVoice:" + this.f14590o0 + " hasVideo:" + this.f14592p0 + " hasFile:" + this.f14600t0 + " hasDoodle:" + this.f14596r0 + " hasMessage:" + this.f14594q0 + " hasLocationSnap:" + this.f14598s0);
        StringBuilder sb = new StringBuilder();
        sb.append("[startUpload]: Reason:");
        sb.append(this.f14602u0);
        sb.append(" Voice:");
        sb.append(this.f14555Y);
        sb.append(" Photo:");
        sb.append(this.f14553X);
        sb.append(" Video:");
        sb.append(this.f14557Z);
        UploadUtils.m16965l("UploadMediaHelperV2", sb.toString());
        new C3175l().start();
        new C3176m().start();
        UploadUtils.m16965l("Upload Performance", "1. UploadMediaHelper.startUpload(); ===== end");
    }

    /* renamed from: U0 */
    public String m16820U0() {
        return this.f14566c0;
    }

    /* renamed from: U1 */
    public final void m16821U1() {
        UploadUtils.m16965l("Upload Performance", "3.1 UploadMediaHelper.startUploadBig(); ======== start");
        UploadUtils.m16965l("UploadMediaHelperV2", "[startUploadBig]: Reason:" + this.f14602u0 + " Voice:" + this.f14555Y + " Photo:" + this.f14553X + " Video:" + this.f14557Z);
        if (this.f14604v0) {
            return;
        }
        new C3182s().start();
        UploadUtils.m16965l("Upload Performance", "3.1 UploadMediaHelper.startUploadBig(); ======== end");
    }

    /* renamed from: V0 */
    public MessageObj m16822V0() {
        return this.f14563b0;
    }

    /* renamed from: V1 */
    public final void m16823V1() {
        UploadUtils.m16965l("Upload Performance", "2.1 UploadMediaHelper.startUploadFile(); ======== start");
        UploadUtils.m16965l("UploadMediaHelperV2", "[startUploadFile]: Reason:" + this.f14602u0 + " File:" + this.f14560a0);
        if (this.f14604v0) {
            return;
        }
        new C3180q().start();
        UploadUtils.m16965l("Upload Performance", "2.1 UploadMediaHelper.startUploadFile(); ======== end");
    }

    /* renamed from: W0 */
    public C1199a m16824W0() {
        return this.f14558Z0;
    }

    /* renamed from: W1 */
    public final void m16825W1() {
        UploadUtils.m16965l("Upload Performance", "2.2 UploadMediaHelper.startUploadSmall(); ======== start");
        UploadUtils.m16965l("UploadMediaHelperV2", "[startUploadSmall]: Reason:" + this.f14602u0 + " Voice:" + this.f14555Y + " Photo:" + this.f14553X + " Video:" + this.f14557Z);
        if (this.f14604v0) {
            return;
        }
        new C3181r().start();
        UploadUtils.m16965l("Upload Performance", "2.2 UploadMediaHelper.startUploadSmall(); ======== end");
        if (this.f14554X0) {
            m16821U1();
            this.f14554X0 = false;
        }
    }

    /* renamed from: X0 */
    public C2973l0 m16826X0() {
        return this.f14539Q;
    }

    /* renamed from: X1 */
    public final void m16827X1() {
        UploadUtils.m16965l("Upload Performance", "2.1 UploadMediaHelper.startUploadVideo(); ======== start");
        UploadUtils.m16965l("UploadMediaHelperV2", "[startUploadVideo]: Reason:" + this.f14602u0 + " Voice:" + this.f14555Y + " Photo:" + this.f14553X + " Video:" + this.f14557Z);
        if (this.f14604v0) {
            return;
        }
        new C3179p().start();
        UploadUtils.m16965l("Upload Performance", "2.1 UploadMediaHelper.startUploadVideo(); ======== end");
    }

    /* renamed from: Y0 */
    public UploadUtils.UploadResultType m16828Y0() {
        return this.f14553X;
    }

    /* renamed from: Y1 */
    public final void m16829Y1() {
        UploadUtils.m16965l("Upload Performance", "2.1 UploadMediaHelper.startUploadVideoThumb(); ======== start");
        UploadUtils.m16965l("UploadMediaHelperV2", "[startUploadVideoThumb]: Reason:" + this.f14602u0 + " Voice:" + this.f14555Y + " Photo:" + this.f14553X + " Video:" + this.f14557Z);
        if (this.f14604v0) {
            return;
        }
        new C3178o().start();
        UploadUtils.m16965l("Upload Performance", "2.1 UploadMediaHelper.startUploadVideoThumb(); ======== end");
    }

    /* renamed from: Z0 */
    public int m16830Z0() {
        return this.f14574g0 + this.f14576h0 + this.f14578i0 + this.f14580j0 + this.f14582k0;
    }

    /* renamed from: Z1 */
    public final void m16831Z1() {
        UploadUtils.m16965l("Upload Performance", "2.1 UploadMediaHelper.startUploadVoice(); ======== start");
        UploadUtils.m16965l("UploadMediaHelperV2", "[startUploadVoice]: Reason:" + this.f14602u0 + " Voice:" + this.f14555Y + " Photo:" + this.f14553X + " Video:" + this.f14557Z);
        if (this.f14604v0) {
            return;
        }
        new C3177n().start();
        UploadUtils.m16965l("Upload Performance", "2.1 UploadMediaHelper.startUploadVoice(); ======== end");
    }

    /* renamed from: a1 */
    public int m16832a1() {
        return this.f14542R0;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* renamed from: a2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Pair<HttpURLConnection, DataOutputStream> m16833a2(String str, int i9) throws ProtocolException {
        HttpURLConnection httpURLConnection;
        DataOutputStream dataOutputStream;
        if (this.f14604v0) {
            return null;
        }
        UploadUtils.m16965l("UploadMediaHelperV2", "[tryConnect] ======== start");
        try {
            URL url = new URL(str);
            UploadUtils.m16965l("UploadMediaHelperV2", "[tryConnect] Use Default HttpURLConnection");
            httpURLConnection = (HttpURLConnection) url.openConnection();
        } catch (Exception e9) {
            e = e9;
            httpURLConnection = null;
        }
        try {
            httpURLConnection.setConnectTimeout(DefaultLoadControl.DEFAULT_MAX_BUFFER_MS);
            httpURLConnection.setReadTimeout(DefaultLoadControl.DEFAULT_MAX_BUFFER_MS);
            httpURLConnection.setRequestMethod("PUT");
            httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, "");
            httpURLConnection.setRequestProperty(HttpHeaders.USER_AGENT, "");
            httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "");
            httpURLConnection.setRequestProperty(HttpHeaders.CONNECTION, PreviewActivity.ON_CLICK_LISTENER_CLOSE);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setFixedLengthStreamingMode(i9);
            dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
        } catch (Exception e10) {
            e = e10;
            UploadUtils.m16964k("UploadMediaHelperV2", "[tryConnect]", e);
            dataOutputStream = null;
            if (dataOutputStream != null) {
            }
        }
        if (dataOutputStream != null) {
            return null;
        }
        return Pair.create(httpURLConnection, dataOutputStream);
    }

    /* renamed from: b1 */
    public C1199a m16834b1() {
        return this.f14561a1;
    }

    /* renamed from: b2 */
    public void mo16835b2() {
        UploadUtils.C3195a c3195a;
        UploadUtils.C3195a c3195a2;
        UploadUtils.m16965l("Upload Performance", "P3.3 uploadBig_complete(); ======== start");
        UploadUtils.m16965l("UploadMediaHelperV2", "[uploadBig_complete]: ==== start");
        if (this.f14604v0) {
            return;
        }
        Log.d("UploadMediaHelperQueue", "[uploadBig_complete] in");
        if (this.f14594q0) {
            Log.d("UploadMediaHelperQueue", "[uploadBig_complete] wait for mMessageSentSync");
            synchronized (this.f14546T0) {
                Log.d("UploadMediaHelperQueue", "[uploadBig_complete] get mMessageSentSync");
                if (this.f14550V0) {
                    Log.d("UploadMediaHelperQueue", "[uploadBig_complete] message sent before big img complete");
                    this.f14553X = UploadUtils.UploadResultType.STEP_3_SUCCESS;
                    this.f14602u0 = FailReason.FAIL_NONE;
                } else {
                    try {
                        Log.d("UploadMediaHelperQueue", "[uploadBig_complete] wait for sending message");
                        this.f14546T0.wait();
                        Log.d("UploadMediaHelperQueue", "[uploadBig_complete] message sent");
                        this.f14553X = UploadUtils.UploadResultType.STEP_3_SUCCESS;
                        this.f14602u0 = FailReason.FAIL_NONE;
                        Log.d("UploadMediaHelperQueue", "[uploadBig_complete] end, mPhotoStatus = " + this.f14553X);
                    } catch (InterruptedException e9) {
                        Thread.currentThread().interrupt();
                        e9.printStackTrace();
                    }
                }
            }
        } else {
            String str = this.f14599t;
            if (str != null && (c3195a2 = this.f14562b) != null && !str.equals(c3195a2.f14727f)) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[uploadBig_complete]: ==== small image MD5 mismatch !!! [" + this.f14599t + "] vs [" + this.f14562b.f14727f + "]");
            }
            String str2 = this.f14601u;
            if (str2 != null && (c3195a = this.f14565c) != null && !str2.equals(c3195a.f14727f)) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[uploadBig_complete]: ==== big image MD5 mismatch !!! [" + this.f14601u + "] vs [" + this.f14565c.f14727f + "]");
            }
            String str3 = this.f14507A;
            String strM16959f = str3 != null ? UploadUtils.m16959f(this.f14562b, str3, this.f14599t) : null;
            String str4 = this.f14609y;
            if (str4 == null || str4.isEmpty()) {
                UploadUtils.C3195a c3195a3 = this.f14565c;
                this.f14609y = UploadUtils.m16955b(c3195a3 == null ? "" : c3195a3.f14722a, this.f14509B, null);
            }
            String strM16961h = UploadUtils.m16961h(this.f14565c, this.f14509B, this.f14601u, this.f14609y, this.f14596r0 ? this.f14519G.f14655c : null);
            synchronized (this.f14548U0) {
                if (!this.f14552W0) {
                    try {
                        this.f14548U0.wait();
                    } catch (InterruptedException e10) {
                        Thread.currentThread().interrupt();
                        e10.printStackTrace();
                    }
                }
                m16789C0(strM16959f, strM16961h);
            }
        }
        UploadUtils.m16965l("UploadMediaHelperV2", "[uploadBig_complete]: ==== done !!!");
        UploadUtils.m16965l("Upload Performance", "P3.3 uploadBig_complete(); ======== end");
    }

    /* renamed from: c1 */
    public AudioItem m16836c1() {
        return this.f14513D;
    }

    /* renamed from: c2 */
    public final void m16837c2() {
        UploadUtils.m16965l("Upload Performance", "P3.1 uploadFile_complete(); ======== start");
        UploadUtils.m16965l("UploadMediaHelperV2", "[uploadFile_complete]: ==== start");
        if (this.f14604v0) {
            return;
        }
        if (C5170o0.m20170e(this.f14571f.f14727f)) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadFile_complete]: ==== there is no file file MD5 in mFileUploadMedia  !!!");
        } else if (!this.f14607x.equals(this.f14571f.f14727f)) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadFile_complete]: ==== file file MD5 mismatch !!! [" + this.f14607x + "] vs [" + this.f14571f.f14727f + "]");
        }
        String str = this.f14607x;
        if (this.f14581k.f14733a) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadFile_complete]: ==== multipart, reset md5");
            str = null;
        }
        mo16791D0(UploadUtils.m16958e(this.f14571f, this.f14527K, this.f14529L, str, "None"));
        UploadUtils.m16965l("UploadMediaHelperV2", "[uploadFile_complete]: ==== done !!!");
        UploadUtils.m16965l("Upload Performance", "P3.1 uploadFile_complete(); ======== end");
    }

    /* renamed from: d1 */
    public String m16838d1() {
        m16804L0();
        return this.f14586m0;
    }

    /* renamed from: d2 */
    public void mo16839d2() throws InterruptedException {
        UploadUtils.m16965l("Upload Performance", "P1.2 UploadMediaHelper.uploadFile_step1(); ======== start");
        UploadUtils.m16965l("UploadMediaHelperV2", "[uploadFile_step1]: Reason:" + this.f14602u0 + " File:" + this.f14560a0);
        UploadUtils.m16965l("UploadMediaHelperV2", "[uploadFile_step1]: ==== (File) STEP-1 start");
        this.f14560a0 = UploadUtils.UploadResultType.STEP_1_FAIL;
        if (!this.f14600t0) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadFile_step1]: ==== (File) STEP-1 done #### no file");
            return;
        }
        if (this.f14604v0) {
            return;
        }
        String strM7449L = Globals.m7388i0().m7449L();
        FileItem fileItem = this.f14517F;
        String strM16114a = fileItem != null ? fileItem.m16114a() : "";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("albumId", this.f14537P));
        arrayList.add(new C6301o("mediaType", "File"));
        arrayList.add(new C6301o("mediaName", strM16114a));
        try {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadFile_step1]: ==== smfPrepareFileSync.acquire(1) + release(1) ==== enter");
            this.f14528K0.acquire(1);
            this.f14528K0.release(1);
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadFile_step1]: ==== smfPrepareFileSync.acquire(1) + release(1) ==== leave");
            arrayList.add(new C6301o("partAmount", String.valueOf(this.f14581k.f14734b)));
            UploadUtils.m16965l("Upload Performance", "P3 SendRequest(file) ==== start");
            boolean zM15734m = this.f14547U.m15734m("media", "uploadMedia", arrayList, new FriendsClient.InterfaceC3051i() { // from class: k4.w0
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    this.f17769a.m16776v1(str, str2, str3, str4);
                }
            });
            try {
                synchronized (this.f14547U) {
                    if (zM15734m) {
                        this.f14547U.wait();
                    }
                }
            } catch (InterruptedException e9) {
                UploadUtils.m16964k("UploadMediaHelperV2", "[uploadFile_step1]", e9);
            }
            if (this.f14604v0) {
                return;
            }
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadFile_step1]: ==== (File) STEP-1 done");
            UploadUtils.m16965l("Upload Performance", "P1.2 UploadMediaHelper.uploadFile_step1(); ======== end");
        } catch (InterruptedException e10) {
            UploadUtils.m16964k("UploadMediaHelperV2", "[uploadFile_step1]: ==== smfPrepareFileSync.acquire(1) + release(1) ==== fail !!!!", e10);
        }
    }

    /* renamed from: e1 */
    public FileItem m16840e1() {
        return this.f14517F;
    }

    /* renamed from: e2 */
    public void mo16841e2() {
        UploadUtils.m16965l("Upload Performance", "P1.1 UploadMediaHelper.uploadPhoto_step1(); ======== start");
        UploadUtils.m16965l("UploadMediaHelperV2", "[uploadPhoto_step1]: Reason:" + this.f14602u0 + " Voice:" + this.f14555Y + " Photo:" + this.f14553X + " Video:" + this.f14557Z);
        UploadUtils.m16965l("UploadMediaHelperV2", "[uploadPhoto_step1]: ==== (Photo) STEP-1 start");
        UploadUtils.UploadResultType uploadResultType = UploadUtils.UploadResultType.STEP_1_FAIL;
        this.f14553X = uploadResultType;
        if (this.f14604v0) {
            return;
        }
        if (!this.f14588n0 && !this.f14596r0 && !this.f14598s0) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadPhoto_step1]: ==== (Photo) STEP-1 done ===== no photo or doodle or location snap");
            return;
        }
        String strM7449L = Globals.m7388i0().m7449L();
        boolean z8 = this.f14588n0;
        String name = null;
        String str = z8 ? "Photo" : this.f14596r0 ? "Doodle" : this.f14598s0 ? "Photo" : null;
        if (z8) {
            name = this.f14611z.m16147t();
        } else if (this.f14596r0) {
            name = "Doodle";
        } else if (this.f14598s0) {
            name = new File(this.f14521H).getName();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("albumId", this.f14531M));
        arrayList.add(new C6301o("mediaType", str));
        arrayList.add(new C6301o("mediaName", name));
        this.f14553X = uploadResultType;
        UploadUtils.m16965l("Upload Performance", "P3 SendRequest(photo) ==== start");
        boolean zM15734m = this.f14547U.m15734m("media", "uploadMedia", arrayList, new FriendsClient.InterfaceC3051i() { // from class: k4.d1
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str2, String str3, String str4, String str5) {
                this.f17646a.m16778w1(str2, str3, str4, str5);
            }
        });
        try {
            synchronized (this.f14547U) {
                if (zM15734m) {
                    this.f14547U.wait();
                }
            }
        } catch (InterruptedException e9) {
            UploadUtils.m16964k("UploadMediaHelperV2", "[uploadPhoto_step1]", e9);
        }
        UploadUtils.m16965l("UploadMediaHelperV2", "[uploadPhoto_step1]: ==== (Photo) STEP-1 done");
        UploadUtils.m16965l("Upload Performance", "P1.1 UploadMediaHelper.uploadPhoto_step1(); ======== end");
    }

    /* renamed from: f1 */
    public ImageItem m16842f1() {
        return this.f14611z;
    }

    /* renamed from: f2 */
    public final void m16843f2() {
        String strM16957d;
        UploadUtils.C3195a c3195a;
        UploadUtils.m16965l("Upload Performance", "P3.2 uploadSmall_complete(); ======== start");
        UploadUtils.m16965l("UploadMediaHelperV2", "[uploadSmall_complete]: ==== start");
        if (this.f14604v0) {
            return;
        }
        if (this.f14594q0) {
            String str = this.f14599t;
            if (str != null && (c3195a = this.f14562b) != null && !str.equals(c3195a.f14727f)) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[uploadSmall_complete]: ==== small image MD5 mismatch !!! [" + this.f14599t + "] vs [" + this.f14562b.f14727f + "]");
            }
            String strM16959f = UploadUtils.m16959f(this.f14562b, this.f14507A, this.f14599t);
            if (this.f14565c != null) {
                if (!Globals.m7388i0().m7550g2()) {
                    this.f14601u = this.f14565c.f14727f;
                }
                if (C5170o0.m20169d(this.f14609y)) {
                    this.f14609y = UploadUtils.m16955b(this.f14565c.f14722a, this.f14509B, null);
                }
                strM16957d = UploadUtils.m16961h(this.f14565c, this.f14509B, this.f14601u, this.f14609y, this.f14596r0 ? this.f14519G.f14655c : null);
            } else {
                strM16957d = UploadUtils.m16957d();
            }
            mo16793E0(strM16959f, strM16957d);
        } else {
            synchronized (this.f14548U0) {
                this.f14552W0 = true;
                this.f14553X = UploadUtils.UploadResultType.STEP_1_SUCCESS;
                this.f14602u0 = FailReason.FAIL_NONE;
                this.f14548U0.notifyAll();
                CLUtility.m16451J(this.f14507A);
            }
        }
        UploadUtils.m16965l("UploadMediaHelperV2", "[uploadSmall_complete]: ==== done !!!");
        UploadUtils.m16965l("Upload Performance", "P3.2 uploadSmall_complete(); ======== end");
    }

    public void finalize() throws Throwable {
        super.finalize();
        this.f14559a.shutdown();
        this.f14547U.m15717U0();
    }

    /* renamed from: g1 */
    public VideoItem m16844g1() {
        return this.f14515E;
    }

    /* renamed from: g2 */
    public final void m16845g2() {
        long jM16220i;
        long jM16214c;
        UploadUtils.C3195a c3195a;
        UploadUtils.m16965l("Upload Performance", "P3.1 uploadVideo_complete(); ======== start");
        UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVideo_complete]: ==== start");
        if (this.f14604v0) {
            return;
        }
        String str = this.f14599t;
        if (str != null && (c3195a = this.f14562b) != null && !str.equals(c3195a.f14727f)) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVideo_complete]: ==== video thumbnail MD5 mismatch !!! [" + this.f14599t + "] vs [" + this.f14562b.f14727f + "]");
        }
        if (this.f14507A == null) {
            this.f14507A = "";
        }
        String strM16959f = UploadUtils.m16959f(this.f14562b, this.f14507A, this.f14599t);
        String str2 = this.f14572f0;
        if (str2 == null) {
            str2 = "0";
        }
        String str3 = str2;
        if (C5170o0.m20170e(this.f14569e.f14727f)) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVideo_complete]: ==== there is no video file MD5 in mVideoUploadMedia  !!!");
        } else if (!C6383t.m24512a(this.f14605w, this.f14569e.f14727f)) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVideo_complete]: ==== video file MD5 mismatch !!! [" + this.f14605w + "] vs [" + this.f14569e.f14727f + "]");
        }
        VideoItem videoItem = this.f14515E;
        if (videoItem == null || !videoItem.m16215d()) {
            jM16220i = 0;
            jM16214c = 0;
        } else {
            jM16220i = this.f14515E.m16220i();
            jM16214c = this.f14515E.m16214c();
        }
        String str4 = this.f14605w;
        if (this.f14579j.f14733a) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVideo_complete]: ==== multipart, reset md5");
            str4 = null;
        }
        mo16795F0(strM16959f, UploadUtils.m16962i(this.f14569e, this.f14523I, this.f14525J, str4, str3, "None", jM16220i, jM16214c));
        UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVideo_complete]: ==== done !!!");
        UploadUtils.m16965l("Upload Performance", "P3.1 uploadVideo_complete(); ======== end");
    }

    /* renamed from: h1 */
    public C2973l0 m16846h1() {
        return this.f14543S;
    }

    /* renamed from: h2 */
    public void mo16847h2() throws InterruptedException {
        UploadUtils.m16965l("Upload Performance", "P1.2 UploadMediaHelper.uploadVideo_step1(); ======== start");
        UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVideo_step1]: Reason:" + this.f14602u0 + " Voice:" + this.f14555Y + " Photo:" + this.f14553X + " Video:" + this.f14557Z);
        UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVideo_step1]: ==== (Video) STEP-1 start");
        this.f14557Z = UploadUtils.UploadResultType.STEP_1_FAIL;
        if (!this.f14592p0) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVideo_step1]: ==== (Video) STEP-1 done #### no video");
            return;
        }
        if (this.f14604v0) {
            return;
        }
        String strM7449L = Globals.m7388i0().m7449L();
        VideoItem videoItem = this.f14515E;
        String strM16212a = videoItem != null ? videoItem.m16212a() : "";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("albumId", this.f14535O));
        arrayList.add(new C6301o("mediaType", "Video"));
        arrayList.add(new C6301o("mediaName", strM16212a));
        try {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVideo_step1]: ==== smfPrepareVideoSync.acquire(1) + release(1) ==== enter");
            this.f14524I0.acquire(1);
            this.f14524I0.release(1);
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVideo_step1]: ==== smfPrepareVideoSync.acquire(1) + release(1) ==== leave");
            arrayList.add(new C6301o("partAmount", String.valueOf(this.f14579j.f14734b)));
            UploadUtils.m16965l("Upload Performance", "P3 SendRequest(video) ==== start");
            boolean zM15734m = this.f14547U.m15734m("media", "uploadMedia", arrayList, new FriendsClient.InterfaceC3051i() { // from class: k4.z0
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    this.f17798a.m16780x1(str, str2, str3, str4);
                }
            });
            try {
                synchronized (this.f14547U) {
                    if (zM15734m) {
                        this.f14547U.wait();
                    }
                }
            } catch (InterruptedException e9) {
                UploadUtils.m16964k("UploadMediaHelperV2", "[uploadVideo_step1]", e9);
            }
            if (this.f14604v0) {
                return;
            }
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVideo_step1]: ==== (Video) STEP-1 done");
            UploadUtils.m16965l("Upload Performance", "P1.2 UploadMediaHelper.uploadVideo_step1(); ======== end");
        } catch (InterruptedException e10) {
            UploadUtils.m16964k("UploadMediaHelperV2", "[uploadVideo_step1]: ==== smfPrepareVideoSync.acquire(1) + release(1) ==== fail !!!!", e10);
        }
    }

    /* renamed from: i1 */
    public UploadUtils.UploadResultType m16848i1() {
        return this.f14557Z;
    }

    /* renamed from: i2 */
    public final void m16849i2() {
        UploadUtils.m16965l("Upload Performance", "P3.1 uploadVoice_complete(); ======== start");
        UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVoice_complete]: ==== start");
        if (this.f14604v0) {
            return;
        }
        String strM16957d = UploadUtils.m16957d();
        String str = this.f14570e0;
        if (str == null) {
            str = "0";
        }
        String str2 = this.f14603v;
        if (str2 != null && !str2.equals(this.f14567d.f14727f)) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVoice_complete]: ==== voice file MD5 mismatch !!! [" + this.f14603v + "] vs [" + this.f14567d.f14727f + "]");
        }
        m16797G0(strM16957d, UploadUtils.m16963j(this.f14567d, this.f14511C, this.f14603v, str, "Audio"));
        UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVoice_complete]: ==== done !!!");
        UploadUtils.m16965l("Upload Performance", "P3.1 uploadVoice_complete(); ======== end");
    }

    /* renamed from: j */
    public void m16850j() {
        try {
            UploadUtils.m16965l("UploadMediaHelperV2", "[abortUpload]: ==== in !!!!");
            this.f14604v0 = true;
            AsyncTaskC3186w asyncTaskC3186w = this.f14606w0;
            if (asyncTaskC3186w != null) {
                asyncTaskC3186w.m16882a();
                this.f14606w0.cancel(true);
            }
            UploadUtils.m16965l("UploadMediaHelperV2", "[abortUpload]: ==== out !!!!");
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: j1 */
    public C2973l0 m16851j1() {
        return this.f14541R;
    }

    /* renamed from: j2 */
    public final void m16852j2() {
        UploadUtils.m16965l("Upload Performance", "P1.2 UploadMediaHelper.uploadVoice_step1(); ======== start");
        UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVoice_step1]: Reason:" + this.f14602u0 + " Voice:" + this.f14555Y + " Photo:" + this.f14553X + " Video:" + this.f14557Z);
        UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVoice_step1]: ==== (Voice) STEP-1 start");
        this.f14555Y = UploadUtils.UploadResultType.STEP_1_FAIL;
        if (!this.f14590o0) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVoice_step1]: ==== (Voice) STEP-1 done #### no voice");
            return;
        }
        if (this.f14604v0) {
            return;
        }
        String strM7449L = Globals.m7388i0().m7449L();
        String name = new File(this.f14511C).getName();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("albumId", this.f14533N));
        arrayList.add(new C6301o("mediaType", "Audio"));
        arrayList.add(new C6301o("mediaName", name));
        UploadUtils.m16965l("Upload Performance", "P3 SendRequest(voice) ==== start");
        boolean zM15734m = this.f14547U.m15734m("media", "uploadMedia", arrayList, new FriendsClient.InterfaceC3051i() { // from class: k4.b1
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f17642a.m16782y1(str, str2, str3, str4);
            }
        });
        try {
            synchronized (this.f14547U) {
                if (zM15734m) {
                    this.f14547U.wait();
                }
            }
        } catch (InterruptedException e9) {
            UploadUtils.m16964k("UploadMediaHelperV2", "[uploadVoice_step1]", e9);
        }
        if (this.f14604v0) {
            return;
        }
        UploadUtils.m16965l("UploadMediaHelperV2", "[uploadVoice_step1]: ==== (Voice) STEP-1 done");
        UploadUtils.m16965l("Upload Performance", "P1.2 UploadMediaHelper.uploadVoice_step1(); ======== end");
    }

    /* renamed from: k1 */
    public UploadUtils.UploadResultType m16853k1() {
        return this.f14555Y;
    }

    /* renamed from: k2 */
    public final void m16854k2() {
        UploadUtils.UploadResultType uploadResultType;
        UploadUtils.UploadResultType uploadResultType2;
        UploadUtils.UploadResultType uploadResultType3;
        UploadUtils.UploadResultType uploadResultType4;
        UploadUtils.UploadResultType uploadResultType5;
        UploadUtils.m16965l("Upload Performance", "2. UploadMediaHelper.upload_fromStep2toEnd(); ======== start");
        UploadUtils.m16965l("UploadMediaHelperV2", "[upload_fromStep2toEnd]: Reason:" + this.f14602u0 + " Voice:" + this.f14555Y + " Photo:" + this.f14553X + " Video:" + this.f14557Z);
        UploadUtils.m16965l("UploadMediaHelperV2", "[upload_fromStep2toEnd]: ==== start");
        if (this.f14604v0) {
            return;
        }
        if (this.f14590o0 && ((uploadResultType5 = this.f14555Y) == UploadUtils.UploadResultType.STEP_1_SUCCESS || uploadResultType5 == UploadUtils.UploadResultType.STEP_2_VOICE_FAIL)) {
            m16831Z1();
        } else if ((this.f14588n0 || this.f14596r0 || this.f14598s0) && ((uploadResultType = this.f14553X) == UploadUtils.UploadResultType.STEP_1_SUCCESS || uploadResultType == UploadUtils.UploadResultType.STEP_2_SMALL_FAIL)) {
            m16825W1();
        } else if (this.f14592p0 && ((uploadResultType3 = this.f14557Z) == (uploadResultType4 = UploadUtils.UploadResultType.STEP_1_SUCCESS) || uploadResultType3 == UploadUtils.UploadResultType.STEP_2_SMALL_FAIL || uploadResultType3 == UploadUtils.UploadResultType.STEP_2_VIDEO_FAIL)) {
            if (uploadResultType3 == uploadResultType4 || uploadResultType3 == UploadUtils.UploadResultType.STEP_2_SMALL_FAIL) {
                m16829Y1();
            } else {
                m16827X1();
            }
        } else if (this.f14600t0 && ((uploadResultType2 = this.f14560a0) == UploadUtils.UploadResultType.STEP_1_SUCCESS || uploadResultType2 == UploadUtils.UploadResultType.STEP_2_FILE_FAIL)) {
            m16823V1();
        } else {
            UploadUtils.m16965l("UploadMediaHelperV2", "[upload_fromStep2toEnd] status no match.......something wrong !!!! Voice:" + this.f14555Y + " Photo:" + this.f14553X + " Video:" + this.f14557Z);
            AbstractC3185v abstractC3185v = this.f14549V;
            if (abstractC3185v != null) {
                abstractC3185v.mo8382e(this);
            }
        }
        UploadUtils.m16965l("UploadMediaHelperV2", "[upload_fromStep2toEnd]: ==== done !!!!");
        UploadUtils.m16965l("Upload Performance", "2. UploadMediaHelper.upload_fromStep2toEnd(); ======== end");
    }

    /* renamed from: l1 */
    public boolean m16855l1() {
        return this.f14600t0;
    }

    /* renamed from: l2 */
    public final void m16856l2() {
        UploadUtils.UploadResultType uploadResultType;
        UploadUtils.UploadResultType uploadResultType2;
        UploadUtils.UploadResultType uploadResultType3;
        UploadUtils.UploadResultType uploadResultType4;
        UploadUtils.UploadResultType uploadResultType5;
        UploadUtils.UploadResultType uploadResultType6;
        UploadUtils.m16965l("Upload Performance", "3. UploadMediaHelper.upload_fromStep3toEnd(); ======== start");
        UploadUtils.m16965l("UploadMediaHelperV2", "[upload_fromStep3toEnd]: Reason:" + this.f14602u0 + " Voice:" + this.f14555Y + " Photo:" + this.f14553X + " Video:" + this.f14557Z);
        UploadUtils.m16965l("UploadMediaHelperV2", "[upload_fromStep3toEnd]: ==== start");
        if (this.f14604v0) {
            return;
        }
        if (this.f14590o0 && ((uploadResultType6 = this.f14555Y) == UploadUtils.UploadResultType.STEP_1_SUCCESS || uploadResultType6 == UploadUtils.UploadResultType.STEP_3_VOICE_FAIL)) {
            m16831Z1();
        } else {
            boolean z8 = this.f14588n0;
            if ((z8 || this.f14596r0) && ((uploadResultType = this.f14553X) == UploadUtils.UploadResultType.STEP_1_SUCCESS || uploadResultType == UploadUtils.UploadResultType.STEP_3_SMALL_FAIL)) {
                m16825W1();
            } else if ((z8 || this.f14596r0) && ((uploadResultType2 = this.f14553X) == UploadUtils.UploadResultType.STEP_1_SUCCESS || uploadResultType2 == UploadUtils.UploadResultType.STEP_3_BIG_FAIL)) {
                m16821U1();
            } else if (this.f14592p0 && ((uploadResultType4 = this.f14557Z) == (uploadResultType5 = UploadUtils.UploadResultType.STEP_1_SUCCESS) || uploadResultType4 == UploadUtils.UploadResultType.STEP_2_SMALL_FAIL || uploadResultType4 == UploadUtils.UploadResultType.STEP_2_VIDEO_FAIL)) {
                if (uploadResultType4 == uploadResultType5 || uploadResultType4 == UploadUtils.UploadResultType.STEP_2_SMALL_FAIL) {
                    m16829Y1();
                } else {
                    m16827X1();
                }
            } else if (this.f14600t0 && ((uploadResultType3 = this.f14560a0) == UploadUtils.UploadResultType.STEP_1_SUCCESS || uploadResultType3 == UploadUtils.UploadResultType.STEP_2_FILE_FAIL)) {
                m16823V1();
            } else {
                UploadUtils.m16965l("UploadMediaHelperV2", "[upload_fromStep3toEnd] status no match.......something wrong !!!! Voice:" + this.f14555Y + " Photo:" + this.f14553X + " Video:" + this.f14557Z);
                AbstractC3185v abstractC3185v = this.f14549V;
                if (abstractC3185v != null) {
                    abstractC3185v.mo8382e(this);
                }
            }
        }
        UploadUtils.m16965l("UploadMediaHelperV2", "[upload_fromStep3toEnd]: ==== done !!!!");
        UploadUtils.m16965l("Upload Performance", "3. UploadMediaHelper.upload_fromStep3toEnd(); ======== end");
    }

    /* renamed from: m1 */
    public boolean m16857m1() {
        return this.f14588n0;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0024  */
    /* renamed from: m2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final UploadUtils.UploadResultType m16858m2() throws InterruptedException {
        UploadUtils.UploadResultType uploadResultType;
        UploadUtils.m16965l("Upload Performance", "1. UploadMediaHelper.upload_step1(); ======== start");
        UploadUtils.m16965l("UploadMediaHelperV2", "[upload_step1]: ==== STEP-1 start");
        UploadUtils.UploadResultType uploadResultType2 = UploadUtils.UploadResultType.STEP_1_SUCCESS;
        if (this.f14588n0 || this.f14596r0 || this.f14598s0) {
            mo16841e2();
            uploadResultType = this.f14553X;
            if (uploadResultType == uploadResultType2) {
                uploadResultType = uploadResultType2;
            }
        }
        if (uploadResultType == uploadResultType2 && this.f14590o0) {
            m16852j2();
            UploadUtils.UploadResultType uploadResultType3 = this.f14555Y;
            if (uploadResultType3 != uploadResultType2) {
                uploadResultType = uploadResultType3;
            }
        }
        if (uploadResultType == uploadResultType2 && this.f14592p0) {
            mo16847h2();
            UploadUtils.UploadResultType uploadResultType4 = this.f14557Z;
            if (uploadResultType4 != uploadResultType2) {
                uploadResultType = uploadResultType4;
            }
        }
        if (uploadResultType == uploadResultType2 && this.f14600t0) {
            mo16839d2();
            UploadUtils.UploadResultType uploadResultType5 = this.f14560a0;
            if (uploadResultType5 != uploadResultType2) {
                uploadResultType = uploadResultType5;
            }
        }
        UploadUtils.m16965l("UploadMediaHelperV2", "[upload_step1]: ==== STEP-1 done !!!!!");
        UploadUtils.m16965l("Upload Performance", "1. UploadMediaHelper.upload_step1(); ======== end");
        return uploadResultType;
    }

    /* renamed from: n1 */
    public boolean m16859n1() {
        return this.f14592p0;
    }

    /* renamed from: o1 */
    public boolean m16860o1() {
        return this.f14590o0;
    }

    /* renamed from: p1 */
    public void m16861p1() {
        this.f14542R0++;
    }

    /* renamed from: w0 */
    public final boolean m16862w0() {
        String str;
        if (this.f14586m0 == null) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.f14586m0);
            if (jSONObject == JSONObject.NULL) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[applyUploadContext] Upload Context = null");
                return false;
            }
            try {
                if (!jSONObject.isNull("albumId")) {
                    this.f14531M = jSONObject.getString("albumId");
                }
                if (!jSONObject.isNull("imageItem")) {
                    ImageItem imageItem = new ImageItem(new JSONObject(jSONObject.getString("imageItem")));
                    this.f14611z = imageItem;
                    this.f14609y = imageItem.m16141n();
                }
                if (!jSONObject.isNull("bigPath")) {
                    this.f14509B = jSONObject.getString("bigPath");
                }
                if (!jSONObject.isNull("smallPath")) {
                    this.f14507A = jSONObject.getString("smallPath");
                }
                if (!jSONObject.isNull("voiceAlbumId")) {
                    this.f14533N = jSONObject.getString("voiceAlbumId");
                }
                if (!jSONObject.isNull("voicePath")) {
                    this.f14511C = jSONObject.getString("voicePath");
                }
                if (!jSONObject.isNull("hasVoice")) {
                    this.f14590o0 = jSONObject.getBoolean("hasVoice");
                }
                if (!jSONObject.isNull("hasPhoto")) {
                    this.f14588n0 = jSONObject.getBoolean("hasPhoto");
                }
                if (!jSONObject.isNull("hasMessage")) {
                    this.f14594q0 = jSONObject.getBoolean("hasMessage");
                }
                if (!jSONObject.isNull("commentText")) {
                    this.f14568d0 = jSONObject.getString("commentText");
                }
                if (!jSONObject.isNull("voiceDuration")) {
                    this.f14570e0 = jSONObject.getString("voiceDuration");
                }
                if (!jSONObject.isNull("thumbnailURL")) {
                    this.f14573g = jSONObject.getString("thumbnailURL");
                }
                if (!jSONObject.isNull("originalURL")) {
                    this.f14575h = jSONObject.getString("originalURL");
                }
                if (!jSONObject.isNull("photoTimeout")) {
                    this.f14591p = new Date(jSONObject.getLong("photoTimeout"));
                }
                if (!jSONObject.isNull("photoCompleteURL")) {
                    this.f14583l = jSONObject.getString("photoCompleteURL");
                }
                if (!jSONObject.isNull("voiceUploadURL")) {
                    this.f14577i = jSONObject.getString("voiceUploadURL");
                }
                if (!jSONObject.isNull("voiceTimeout")) {
                    this.f14593q = new Date(jSONObject.getLong("voiceTimeout"));
                }
                if (!jSONObject.isNull("voiceCompleteURL")) {
                    this.f14585m = jSONObject.getString("voiceCompleteURL");
                }
                if (!jSONObject.isNull("photoMediaObj")) {
                    C2973l0 c2973l0 = new C2973l0(new JSONObject(jSONObject.getString("photoMediaObj")));
                    this.f14539Q = c2973l0;
                    if (c2973l0.m15149u() != null) {
                        this.f14599t = this.f14539Q.m15149u().f13199c;
                    }
                    if (this.f14539Q.m15148t() != null) {
                        this.f14601u = this.f14539Q.m15148t().f13199c;
                    }
                }
                if (!jSONObject.isNull("voiceMediaObj")) {
                    C2973l0 c2973l02 = new C2973l0(new JSONObject(jSONObject.getString("voiceMediaObj")));
                    this.f14541R = c2973l02;
                    if (c2973l02.m15148t() != null) {
                        this.f14603v = this.f14541R.m15148t().f13199c;
                    }
                }
                if (!jSONObject.isNull("photoStatus")) {
                    this.f14553X = UploadUtils.UploadResultType.values()[jSONObject.getInt("photoStatus")];
                }
                if (!jSONObject.isNull("voiceStatus")) {
                    this.f14555Y = UploadUtils.UploadResultType.values()[jSONObject.getInt("voiceStatus")];
                }
                if (!jSONObject.isNull("failReason")) {
                    this.f14602u0 = FailReason.values()[jSONObject.getInt("failReason")];
                }
                if (!jSONObject.isNull("messageID")) {
                    this.f14566c0 = jSONObject.getString("messageID");
                    this.f14563b0 = C2950b0.m14916o().m15179r(this.f14566c0);
                }
                if (!jSONObject.isNull("smallUploadMedia")) {
                    this.f14562b = new UploadUtils.C3195a(new JSONObject(jSONObject.getString("smallUploadMedia")));
                }
                if (!jSONObject.isNull("bigUploadMedia")) {
                    this.f14565c = new UploadUtils.C3195a(new JSONObject(jSONObject.getString("bigUploadMedia")));
                }
                if (!jSONObject.isNull("voiceUploadMedia")) {
                    this.f14567d = new UploadUtils.C3195a(new JSONObject(jSONObject.getString("voiceUploadMedia")));
                }
                if (!jSONObject.isNull("chatId")) {
                    this.f14584l0 = jSONObject.getString("chatId");
                }
                if (!jSONObject.isNull("hasVideo")) {
                    this.f14592p0 = jSONObject.getBoolean("hasVideo");
                }
                if (!jSONObject.isNull("videoItem")) {
                    VideoItem videoItem = new VideoItem(new JSONObject(jSONObject.getString("videoItem")));
                    this.f14515E = videoItem;
                    this.f14523I = videoItem.m16218g();
                    this.f14525J = CLUtility.m16510Z1(this.f14515E.m16219h());
                }
                if (!jSONObject.isNull("videoAlbumId")) {
                    this.f14535O = jSONObject.getString("videoAlbumId");
                }
                if (!jSONObject.isNull("videoDuration")) {
                    this.f14572f0 = jSONObject.getString("videoDuration");
                }
                if (!jSONObject.isNull("videoTimeout")) {
                    this.f14595r = new Date(jSONObject.getLong("videoTimeout"));
                }
                if (!jSONObject.isNull("videoCompleteURL")) {
                    this.f14587n = jSONObject.getString("videoCompleteURL");
                }
                if (!jSONObject.isNull("videoMediaObj")) {
                    C2973l0 c2973l03 = new C2973l0(new JSONObject(jSONObject.getString("videoMediaObj")));
                    this.f14543S = c2973l03;
                    if (c2973l03.m15148t() != null) {
                        this.f14605w = this.f14543S.m15148t().f13199c;
                    }
                }
                if (!jSONObject.isNull("videoStatus")) {
                    this.f14557Z = UploadUtils.UploadResultType.values()[jSONObject.getInt("videoStatus")];
                }
                if (!jSONObject.isNull("videoUploadInfo")) {
                    UploadUtils.C3196b c3196b = this.f14579j;
                    if (c3196b != null) {
                        c3196b.m16972g();
                        this.f14579j = null;
                    }
                    this.f14579j = new UploadUtils.C3196b(new JSONObject(jSONObject.getString("videoUploadInfo")));
                }
                if (!jSONObject.isNull("hasFile")) {
                    this.f14600t0 = jSONObject.getBoolean("hasFile");
                }
                if (!jSONObject.isNull("fileItem")) {
                    FileItem fileItem = new FileItem(new JSONObject(jSONObject.getString("fileItem")));
                    this.f14517F = fileItem;
                    this.f14527K = fileItem.m16115b();
                    this.f14529L = CLUtility.m16510Z1(this.f14517F.m16117d());
                }
                if (!jSONObject.isNull("fileAlbumId")) {
                    this.f14537P = jSONObject.getString("fileAlbumId");
                }
                if (!jSONObject.isNull("fileTimeout")) {
                    this.f14597s = new Date(jSONObject.getLong("fileTimeout"));
                }
                if (!jSONObject.isNull("fileCompleteURL")) {
                    this.f14589o = jSONObject.getString("fileCompleteURL");
                }
                if (!jSONObject.isNull("fileMediaObj")) {
                    C2973l0 c2973l04 = new C2973l0(new JSONObject(jSONObject.getString("fileMediaObj")));
                    this.f14545T = c2973l04;
                    if (c2973l04.m15148t() != null) {
                        this.f14607x = this.f14545T.m15148t().f13199c;
                    }
                }
                if (!jSONObject.isNull("fileStatus")) {
                    this.f14560a0 = UploadUtils.UploadResultType.values()[jSONObject.getInt("fileStatus")];
                }
                if (!jSONObject.isNull("fileUploadInfo")) {
                    UploadUtils.C3196b c3196b2 = this.f14581k;
                    if (c3196b2 != null) {
                        c3196b2.m16972g();
                    }
                    this.f14581k = new UploadUtils.C3196b(new JSONObject(jSONObject.getString("fileUploadInfo")));
                }
                if (!jSONObject.isNull("hasDoodle")) {
                    this.f14596r0 = jSONObject.getBoolean("hasDoodle");
                }
                if (!jSONObject.isNull("doodleItem")) {
                    this.f14519G = new C3184u(new JSONObject(jSONObject.getString("doodleItem")));
                }
                if (!jSONObject.isNull("hasLocationSnap")) {
                    this.f14598s0 = jSONObject.getBoolean("hasLocationSnap");
                }
                if (!jSONObject.isNull("locationSnapPath")) {
                    this.f14521H = jSONObject.getString("locationSnapPath");
                }
                return true;
            } catch (JSONException e9) {
                UploadUtils.m16964k("UploadMediaHelperV2", "[applyUploadContext]", e9);
                return false;
            }
        } catch (JSONException e10) {
            if (this.f14586m0 == null) {
                str = "[applyUploadContext] Parse error. Upload Context=NULL";
            } else {
                str = "[applyUploadContext] Parse error. Upload Context=" + this.f14586m0;
            }
            UploadUtils.m16964k("UploadMediaHelperV2", str, e10);
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x01ec A[PHI: r0 r2
      0x01ec: PHI (r0v46 java.net.HttpURLConnection) = 
      (r0v8 java.net.HttpURLConnection)
      (r0v16 java.net.HttpURLConnection)
      (r0v16 java.net.HttpURLConnection)
      (r0v24 java.net.HttpURLConnection)
      (r0v24 java.net.HttpURLConnection)
      (r0v63 java.net.HttpURLConnection)
      (r0v63 java.net.HttpURLConnection)
     binds: [B:63:0x01ea, B:56:0x01d6, B:58:0x01da, B:44:0x0186, B:46:0x018a, B:7:0x0056, B:9:0x005a] A[DONT_GENERATE, DONT_INLINE]
      0x01ec: PHI (r2v8 java.io.DataOutputStream) = 
      (r2v1 java.io.DataOutputStream)
      (r2v2 java.io.DataOutputStream)
      (r2v2 java.io.DataOutputStream)
      (r2v3 java.io.DataOutputStream)
      (r2v3 java.io.DataOutputStream)
      (r2v13 java.io.DataOutputStream)
      (r2v13 java.io.DataOutputStream)
     binds: [B:63:0x01ea, B:56:0x01d6, B:58:0x01da, B:44:0x0186, B:46:0x018a, B:7:0x0056, B:9:0x005a] A[DONT_GENERATE, DONT_INLINE]] */
    /* renamed from: x0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean m16863x0(String str, MediaType mediaType) throws InterruptedException, IOException {
        HttpURLConnection httpURLConnection;
        DataOutputStream dataOutputStream;
        byte[] bArr;
        int length;
        byte[] bArr2;
        long j9;
        byte[] bArr3;
        UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection] ======== start mediaType = " + mediaType);
        if (mediaType == MediaType.VOICE) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection] ======== voice (uploadURL = [" + str + "])");
            try {
                UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection]: ==== smfPrepareVoiceSync.acquire(1) + release(1) ==== enter");
                this.f14522H0.acquire(1);
                this.f14522H0.release(1);
                UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection]: ==== smfPrepareVoiceSync.acquire(1) + release(1) ==== leave");
                this.f14530L0 = new Semaphore(0, true);
                httpURLConnection = this.f14608x0;
                dataOutputStream = this.f14610y0;
                UploadUtils.C3195a c3195a = this.f14567d;
                length = (c3195a == null || (bArr3 = c3195a.f14726e) == null) ? 0 : bArr3.length;
            } catch (InterruptedException e9) {
                UploadUtils.m16964k("UploadMediaHelperV2", "[buildConnection]: ==== smfPrepareVoiceSync.acquire(1) + release(1) ==== fail !!!!", e9);
                UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection] ======== voice prepared fail");
                return false;
            }
        } else {
            if (mediaType == MediaType.VIDEO) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection] ======== video (uploadURL = [" + str + "])");
                try {
                    UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection]: ==== smfPrepareVideoSync.acquire(1) + release(1) ==== enter");
                    this.f14524I0.acquire(1);
                    this.f14524I0.release(1);
                    UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection]: ==== smfPrepareVideoSync.acquire(1) + release(1) ==== leave");
                    this.f14532M0 = new Semaphore(0, true);
                    httpURLConnection = this.f14612z0;
                    dataOutputStream = this.f14508A0;
                    j9 = this.f14569e.f14730i;
                } catch (InterruptedException e10) {
                    UploadUtils.m16964k("UploadMediaHelperV2", "[buildConnection]: ==== smfPrepareVideoSync.acquire(1) + release(1) ==== fail !!!!", e10);
                    UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection] ======== video prepared fail");
                    return false;
                }
            } else if (mediaType == MediaType.FILE) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection] ======== file (uploadURL = [" + str + "])");
                try {
                    UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection]: ==== smfPrepareFileSync.acquire(1) + release(1) ==== enter");
                    this.f14528K0.acquire(1);
                    this.f14528K0.release(1);
                    UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection]: ==== smfPrepareFileSync.acquire(1) + release(1) ==== leave");
                    this.f14540Q0 = new Semaphore(0, true);
                    httpURLConnection = this.f14518F0;
                    dataOutputStream = this.f14520G0;
                    j9 = this.f14571f.f14730i;
                } catch (InterruptedException e11) {
                    UploadUtils.m16964k("UploadMediaHelperV2", "[buildConnection]: ==== smfPrepareFileSync.acquire(1) + release(1) ==== fail !!!!", e11);
                    UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection] ======== file prepared fail");
                    return false;
                }
            } else if (mediaType == MediaType.VIDEO_IMG) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection] ======== video image (uploadURL = [" + str + "])");
                try {
                    UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection]: ==== smfPrepareVideoSync.acquire(1) + release(1) ==== enter");
                    this.f14524I0.acquire(1);
                    this.f14524I0.release(1);
                    UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection]: ==== smfPrepareVideoSync.acquire(1) + release(1) ==== leave");
                    this.f14538P0 = new Semaphore(0, true);
                    httpURLConnection = this.f14510B0;
                    dataOutputStream = this.f14512C0;
                    length = this.f14562b.f14726e.length;
                } catch (InterruptedException e12) {
                    UploadUtils.m16964k("UploadMediaHelperV2", "[buildConnection]: ==== smfPrepareVideoSync.acquire(1) + release(1) ==== fail !!!!", e12);
                    UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection] ======== video image prepared fail");
                    return false;
                }
            } else if (mediaType == MediaType.SMALL_IMG) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection] ======== small image (uploadURL = [" + str + "])");
                try {
                    UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection]: ==== smfPreparePhotoSync.acquire(1) + release(1) (small) ==== enter");
                    this.f14526J0.acquire(1);
                    this.f14526J0.release(1);
                    UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection]: ==== smfPreparePhotoSync.acquire(1) + release(1) (small) ==== leave");
                    this.f14534N0 = new Semaphore(0, true);
                    httpURLConnection = this.f14510B0;
                    dataOutputStream = this.f14512C0;
                    UploadUtils.C3195a c3195a2 = this.f14562b;
                    if (c3195a2 != null && (bArr2 = c3195a2.f14726e) != null) {
                        length = bArr2.length;
                    }
                } catch (InterruptedException e13) {
                    UploadUtils.m16964k("UploadMediaHelperV2", "[buildConnection]: ==== smfPreparePhotoSync.acquire(1) + release(1) (small) ==== fail !!!!", e13);
                    UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection] ======== small image prepared fail");
                    return false;
                }
            } else {
                if (mediaType == MediaType.BIG_IMG) {
                    UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection] ======== big image (uploadURL = [" + str + "])");
                    try {
                        UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection]: ==== smfPreparePhotoSync.acquire(1) + release(1) (big) ==== enter");
                        this.f14526J0.acquire(1);
                        this.f14526J0.release(1);
                        UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection]: ==== smfPreparePhotoSync.acquire(1) + release(1) (big) ==== leave");
                        this.f14536O0 = new Semaphore(0, true);
                        httpURLConnection = this.f14514D0;
                        dataOutputStream = this.f14516E0;
                        UploadUtils.C3195a c3195a3 = this.f14565c;
                        if (c3195a3 != null && (bArr = c3195a3.f14726e) != null) {
                            length = bArr.length;
                        }
                    } catch (InterruptedException e14) {
                        UploadUtils.m16964k("UploadMediaHelperV2", "[buildConnection]: ==== smfPreparePhotoSync.acquire(1) + release(1) (big) ==== fail !!!!", e14);
                        UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection] ======== big image upload fail");
                        return false;
                    }
                } else {
                    httpURLConnection = null;
                    dataOutputStream = null;
                }
            }
            length = (int) j9;
        }
        if (dataOutputStream != null) {
            try {
                dataOutputStream.close();
            } catch (Exception e15) {
                UploadUtils.m16964k("UploadMediaHelperV2", "[buildConnection] mediaType<" + mediaType + ">", e15);
            }
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        Pair<HttpURLConnection, DataOutputStream> pairM16833a2 = m16833a2(str, length);
        if (pairM16833a2 == null) {
            SystemClock.sleep(500L);
            UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection]: ==== retry tryConnect() 1 #####");
            pairM16833a2 = m16833a2(str, length);
        }
        if (pairM16833a2 == null) {
            SystemClock.sleep(500L);
            UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection]: ==== retry tryConnect() 2 #####");
            pairM16833a2 = m16833a2(str, length);
        }
        if (mediaType == MediaType.VOICE) {
            if (pairM16833a2 != null) {
                this.f14608x0 = (HttpURLConnection) pairM16833a2.first;
                this.f14610y0 = (DataOutputStream) pairM16833a2.second;
            }
            UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection]: ==== smfConnVoiceSync.release(1) ====");
            this.f14530L0.release(1);
        } else if (mediaType == MediaType.VIDEO) {
            if (pairM16833a2 != null) {
                this.f14612z0 = (HttpURLConnection) pairM16833a2.first;
                this.f14508A0 = (DataOutputStream) pairM16833a2.second;
            }
            UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection]: ==== smfConnVideoSync.release(1) ====");
            this.f14532M0.release(1);
        } else if (mediaType == MediaType.FILE) {
            if (pairM16833a2 != null) {
                this.f14518F0 = (HttpURLConnection) pairM16833a2.first;
                this.f14520G0 = (DataOutputStream) pairM16833a2.second;
            }
            UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection]: ==== smfConnFileSync.release(1) ====");
            this.f14540Q0.release(1);
        } else if (mediaType == MediaType.VIDEO_IMG) {
            if (pairM16833a2 != null) {
                this.f14510B0 = (HttpURLConnection) pairM16833a2.first;
                this.f14512C0 = (DataOutputStream) pairM16833a2.second;
            }
            UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection]: ==== smfConnVideoImgSync.release(1) ====");
            this.f14538P0.release(1);
        } else if (mediaType == MediaType.SMALL_IMG) {
            if (pairM16833a2 != null) {
                this.f14510B0 = (HttpURLConnection) pairM16833a2.first;
                this.f14512C0 = (DataOutputStream) pairM16833a2.second;
            }
            UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection]: ==== smfConnSmallSync.release(1) ====");
            this.f14534N0.release(1);
        } else if (mediaType == MediaType.BIG_IMG) {
            if (pairM16833a2 != null) {
                this.f14514D0 = (HttpURLConnection) pairM16833a2.first;
                this.f14516E0 = (DataOutputStream) pairM16833a2.second;
            }
            UploadUtils.m16965l("UploadMediaHelperV2", "[buildConnection]: ==== smfConnBigSync.release(1) ====");
            this.f14536O0.release(1);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[buildConnection] ======== done. mediaType = ");
        sb.append(mediaType);
        sb.append(" | result = ");
        sb.append(pairM16833a2 != null);
        UploadUtils.m16965l("UploadMediaHelperV2", sb.toString());
        return pairM16833a2 != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0090 A[Catch: Exception -> 0x008c, TRY_LEAVE, TryCatch #0 {Exception -> 0x008c, blocks: (B:27:0x0088, B:31:0x0090), top: B:51:0x0088 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0088 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: y0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean m16864y0(MediaType mediaType, int i9) throws InterruptedException, IOException {
        UploadUtils.C3196b.a aVarM16968c;
        String str;
        HttpURLConnection httpURLConnection;
        DataOutputStream dataOutputStream;
        int i10;
        long j9;
        Pair<HttpURLConnection, DataOutputStream> pairM16833a2;
        UploadUtils.m16965l("UploadMediaHelperV2", "[buildSinglePartConnection] ======== start(index=" + i9 + ")");
        if (mediaType == MediaType.VIDEO) {
            aVarM16968c = this.f14579j.m16968c(i9);
            if (aVarM16968c == null) {
                return false;
            }
            str = aVarM16968c.f14739c;
            try {
                UploadUtils.m16965l("UploadMediaHelperV2", "[buildSinglePartConnection]: ==== smfPrepareVideoSync.acquire(1) + release(1) ==== enter");
                this.f14524I0.acquire(1);
                this.f14524I0.release(1);
                UploadUtils.m16965l("UploadMediaHelperV2", "[buildSinglePartConnection]: ==== smfPrepareVideoSync.acquire(1) + release(1) ==== leave");
                httpURLConnection = aVarM16968c.f14742f;
                dataOutputStream = aVarM16968c.f14743g;
                j9 = aVarM16968c.f14740d;
            } catch (InterruptedException e9) {
                UploadUtils.m16964k("UploadMediaHelperV2", "[buildSinglePartConnection]: ==== smfPrepareVideoSync.acquire(1) + release(1) ==== fail !!!!", e9);
                return false;
            }
        } else {
            if (mediaType != MediaType.FILE) {
                aVarM16968c = null;
                str = "";
                httpURLConnection = null;
                dataOutputStream = null;
                i10 = 0;
                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                    } catch (Exception e10) {
                        UploadUtils.m16964k("UploadMediaHelperV2", "[buildSinglePartConnection]", e10);
                    }
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                pairM16833a2 = m16833a2(str, i10);
                if (pairM16833a2 == null) {
                    SystemClock.sleep(500L);
                    UploadUtils.m16965l("UploadMediaHelperV2", "[buildSinglePartConnection]: ==== retry tryConnect() 1 #####");
                    pairM16833a2 = m16833a2(str, i10);
                }
                if (pairM16833a2 == null) {
                    SystemClock.sleep(500L);
                    UploadUtils.m16965l("UploadMediaHelperV2", "[buildSinglePartConnection]: ==== retry tryConnect() 2 #####");
                    pairM16833a2 = m16833a2(str, i10);
                }
                if (pairM16833a2 != null) {
                    UploadUtils.m16965l("UploadMediaHelperV2", "[buildSinglePartConnection] Error!!! result==null");
                } else if (mediaType == MediaType.VIDEO || mediaType == MediaType.FILE) {
                    aVarM16968c.f14742f = (HttpURLConnection) pairM16833a2.first;
                    aVarM16968c.f14743g = (DataOutputStream) pairM16833a2.second;
                }
                boolean z8 = pairM16833a2 != null;
                UploadUtils.m16965l("UploadMediaHelperV2", "[buildSinglePartConnection] ======== done !!");
                return z8;
            }
            aVarM16968c = this.f14581k.m16968c(i9);
            if (aVarM16968c == null) {
                return false;
            }
            str = aVarM16968c.f14739c;
            try {
                UploadUtils.m16965l("UploadMediaHelperV2", "[buildSinglePartConnection]: ==== smfPrepareFileSync.acquire(1) + release(1) ==== enter");
                this.f14528K0.acquire(1);
                this.f14528K0.release(1);
                UploadUtils.m16965l("UploadMediaHelperV2", "[buildSinglePartConnection]: ==== smfPrepareFileSync.acquire(1) + release(1) ==== leave");
                httpURLConnection = aVarM16968c.f14742f;
                dataOutputStream = aVarM16968c.f14743g;
                j9 = aVarM16968c.f14740d;
            } catch (InterruptedException e11) {
                UploadUtils.m16964k("UploadMediaHelperV2", "[buildSinglePartConnection]: ==== smfPrepareFileSync.acquire(1) + release(1) ==== fail !!!!", e11);
                return false;
            }
        }
        i10 = (int) j9;
        if (dataOutputStream != null) {
        }
        if (httpURLConnection != null) {
        }
        pairM16833a2 = m16833a2(str, i10);
        if (pairM16833a2 == null) {
        }
        if (pairM16833a2 == null) {
        }
        if (pairM16833a2 != null) {
        }
        if (pairM16833a2 != null) {
        }
        UploadUtils.m16965l("UploadMediaHelperV2", "[buildSinglePartConnection] ======== done !!");
        return z8;
    }

    /* renamed from: z1 */
    public final boolean m16865z1(String str) throws JSONException, NumberFormatException {
        JSONObject jSONObject;
        long j9;
        UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadFile]: ==== start");
        if (this.f14604v0) {
            return false;
        }
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            try {
                JSONObject jSONObject3 = jSONObject2.getJSONObject("original");
                if (this.f14581k.f14733a) {
                    long length = jSONObject3.length();
                    UploadUtils.C3196b c3196b = this.f14581k;
                    if (length != c3196b.f14734b) {
                        UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadFile]: ==== done ==== Url size is no expected: " + jSONObject3.length());
                        return false;
                    }
                    c3196b.m16967b();
                    int i9 = 1;
                    while (true) {
                        long j10 = i9;
                        if (j10 <= this.f14581k.f14734b) {
                            try {
                                int i10 = Integer.parseInt(String.valueOf(i9));
                                if (j10 == this.f14581k.f14734b) {
                                    jSONObject = jSONObject3;
                                    long j11 = this.f14571f.f14730i;
                                    if (j11 % 5242880 != 0) {
                                        j9 = j11 % 5242880;
                                    }
                                    jSONObject3 = jSONObject;
                                    this.f14581k.m16966a(MediaType.FILE, i10, jSONObject3.getString(String.valueOf(i9)), j9, Math.max(0L, (i9 - 1) * 5242880), false);
                                    i9++;
                                } else {
                                    jSONObject = jSONObject3;
                                }
                                j9 = 5242880;
                                jSONObject3 = jSONObject;
                                this.f14581k.m16966a(MediaType.FILE, i10, jSONObject3.getString(String.valueOf(i9)), j9, Math.max(0L, (i9 - 1) * 5242880), false);
                                i9++;
                            } catch (NumberFormatException e9) {
                                UploadUtils.m16964k("UploadMediaHelperV2", "[parseMediaUploadFile]: ==== done ==== Cannot parse url index to int", e9);
                                return false;
                            }
                        }
                    }
                } else {
                    try {
                        try {
                            this.f14581k.m16966a(MediaType.FILE, Integer.parseInt("1"), jSONObject3.getString("1"), this.f14571f.f14730i, 0L, false);
                        } catch (Exception e10) {
                            UploadUtils.m16964k("UploadMediaHelperV2", "[parseMediaUploadFile]", e10);
                            return false;
                        }
                    } catch (NumberFormatException e11) {
                        UploadUtils.m16964k("UploadMediaHelperV2", "[parseMediaUploadFile]: ==== done ==== Cannot parse url index to int", e11);
                        return false;
                    }
                }
                try {
                    this.f14589o = jSONObject2.getString("complete");
                    try {
                        this.f14597s = new Date(jSONObject2.getLong("timeout") * 1000);
                        UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadFile]: ==== done !!!");
                        return true;
                    } catch (JSONException unused) {
                        UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadFile]: ==== done ==== 'complete' missing. JSONstr=" + str);
                        return false;
                    }
                } catch (JSONException unused2) {
                    UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadFile]: ==== done ==== 'complete' missing. JSONstr=" + str);
                    return false;
                }
            } catch (JSONException unused3) {
                UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadFile]: ==== done ==== 'original' missing. JSONstr=" + str);
                return false;
            }
        } catch (JSONException unused4) {
            UploadUtils.m16965l("UploadMediaHelperV2", "[parseMediaUploadFile]: ==== done ==== new JSONObject(jsonStr) fail...");
            return false;
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMediaHelper$u */
    public static class C3184u {

        /* renamed from: a */
        public String f14653a;

        /* renamed from: b */
        public String f14654b;

        /* renamed from: c */
        public String f14655c;

        public C3184u(String str, String str2, String str3) {
            this.f14653a = str;
            this.f14654b = str2;
            this.f14655c = str3;
        }

        /* renamed from: a */
        public String m16872a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("path", this.f14653a);
                jSONObject.put("thumb", this.f14654b);
                jSONObject.put(TtmlNode.ATTR_TTS_COLOR, this.f14655c);
                return jSONObject.toString();
            } catch (JSONException e9) {
                e9.printStackTrace();
                return "";
            }
        }

        public C3184u(JSONObject jSONObject) {
            try {
                this.f14653a = jSONObject.getString("path");
                this.f14654b = jSONObject.getString("thumb");
                this.f14655c = jSONObject.getString(TtmlNode.ATTR_TTS_COLOR);
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
        }
    }

    public UploadMediaHelper(String str, AudioItem audioItem) {
        this.f14559a = Executors.newFixedThreadPool(2, new ThreadFactoryC6373j("UploadMediaHelper"));
        this.f14562b = null;
        this.f14565c = null;
        this.f14567d = null;
        this.f14569e = null;
        this.f14571f = null;
        this.f14573g = null;
        this.f14575h = null;
        this.f14577i = null;
        this.f14579j = null;
        this.f14581k = null;
        this.f14583l = null;
        this.f14585m = null;
        this.f14587n = null;
        this.f14589o = null;
        this.f14591p = null;
        this.f14593q = null;
        this.f14595r = null;
        this.f14597s = null;
        this.f14599t = null;
        this.f14601u = null;
        this.f14603v = null;
        this.f14605w = null;
        this.f14607x = null;
        this.f14609y = null;
        this.f14611z = null;
        this.f14507A = null;
        this.f14509B = null;
        this.f14511C = null;
        this.f14513D = null;
        this.f14515E = null;
        this.f14517F = null;
        this.f14519G = null;
        this.f14521H = null;
        this.f14523I = null;
        this.f14525J = null;
        this.f14527K = null;
        this.f14529L = null;
        this.f14531M = null;
        this.f14533N = null;
        this.f14535O = null;
        this.f14537P = null;
        this.f14539Q = null;
        this.f14541R = null;
        this.f14543S = null;
        this.f14545T = null;
        this.f14547U = new FriendsClient(1);
        this.f14549V = null;
        this.f14551W = null;
        UploadUtils.UploadResultType uploadResultType = UploadUtils.UploadResultType.STEP_1_FAIL;
        this.f14553X = uploadResultType;
        this.f14555Y = uploadResultType;
        this.f14557Z = uploadResultType;
        this.f14560a0 = uploadResultType;
        this.f14563b0 = null;
        this.f14566c0 = null;
        this.f14568d0 = null;
        this.f14570e0 = null;
        this.f14572f0 = null;
        this.f14574g0 = 0;
        this.f14576h0 = 0;
        this.f14578i0 = 0;
        this.f14580j0 = 0;
        this.f14582k0 = 0;
        this.f14584l0 = null;
        this.f14586m0 = null;
        this.f14588n0 = false;
        this.f14590o0 = false;
        this.f14592p0 = false;
        this.f14594q0 = false;
        this.f14596r0 = false;
        this.f14598s0 = false;
        this.f14600t0 = false;
        this.f14602u0 = FailReason.FAIL_NONE;
        this.f14604v0 = false;
        this.f14606w0 = null;
        this.f14608x0 = null;
        this.f14610y0 = null;
        this.f14612z0 = null;
        this.f14508A0 = null;
        this.f14510B0 = null;
        this.f14512C0 = null;
        this.f14514D0 = null;
        this.f14516E0 = null;
        this.f14518F0 = null;
        this.f14520G0 = null;
        this.f14522H0 = new Semaphore(0, true);
        this.f14524I0 = new Semaphore(0, true);
        this.f14526J0 = new Semaphore(0, true);
        this.f14528K0 = new Semaphore(0, true);
        this.f14530L0 = null;
        this.f14532M0 = null;
        this.f14534N0 = null;
        this.f14536O0 = null;
        this.f14538P0 = null;
        this.f14540Q0 = null;
        this.f14542R0 = 0;
        this.f14544S0 = null;
        this.f14546T0 = new Object();
        this.f14548U0 = new Object();
        this.f14550V0 = false;
        this.f14552W0 = false;
        this.f14554X0 = true;
        this.f14556Y0 = false;
        this.f14558Z0 = null;
        this.f14561a1 = null;
        this.f14564b1 = new C3170g();
        this.f14533N = str;
        this.f14513D = audioItem;
        if (audioItem != null) {
            this.f14511C = audioItem.m14002a();
            this.f14570e0 = this.f14513D.m14003b();
            if (CLUtility.m16613z1(this.f14511C, null)) {
                this.f14590o0 = true;
            }
        }
    }

    public UploadMediaHelper(String str, VideoItem videoItem) {
        this.f14559a = Executors.newFixedThreadPool(2, new ThreadFactoryC6373j("UploadMediaHelper"));
        this.f14562b = null;
        this.f14565c = null;
        this.f14567d = null;
        this.f14569e = null;
        this.f14571f = null;
        this.f14573g = null;
        this.f14575h = null;
        this.f14577i = null;
        this.f14579j = null;
        this.f14581k = null;
        this.f14583l = null;
        this.f14585m = null;
        this.f14587n = null;
        this.f14589o = null;
        this.f14591p = null;
        this.f14593q = null;
        this.f14595r = null;
        this.f14597s = null;
        this.f14599t = null;
        this.f14601u = null;
        this.f14603v = null;
        this.f14605w = null;
        this.f14607x = null;
        this.f14609y = null;
        this.f14611z = null;
        this.f14507A = null;
        this.f14509B = null;
        this.f14511C = null;
        this.f14513D = null;
        this.f14515E = null;
        this.f14517F = null;
        this.f14519G = null;
        this.f14521H = null;
        this.f14523I = null;
        this.f14525J = null;
        this.f14527K = null;
        this.f14529L = null;
        this.f14531M = null;
        this.f14533N = null;
        this.f14535O = null;
        this.f14537P = null;
        this.f14539Q = null;
        this.f14541R = null;
        this.f14543S = null;
        this.f14545T = null;
        this.f14547U = new FriendsClient(1);
        this.f14549V = null;
        this.f14551W = null;
        UploadUtils.UploadResultType uploadResultType = UploadUtils.UploadResultType.STEP_1_FAIL;
        this.f14553X = uploadResultType;
        this.f14555Y = uploadResultType;
        this.f14557Z = uploadResultType;
        this.f14560a0 = uploadResultType;
        this.f14563b0 = null;
        this.f14566c0 = null;
        this.f14568d0 = null;
        this.f14570e0 = null;
        this.f14572f0 = null;
        this.f14574g0 = 0;
        this.f14576h0 = 0;
        this.f14578i0 = 0;
        this.f14580j0 = 0;
        this.f14582k0 = 0;
        this.f14584l0 = null;
        this.f14586m0 = null;
        this.f14588n0 = false;
        this.f14590o0 = false;
        this.f14592p0 = false;
        this.f14594q0 = false;
        this.f14596r0 = false;
        this.f14598s0 = false;
        this.f14600t0 = false;
        this.f14602u0 = FailReason.FAIL_NONE;
        this.f14604v0 = false;
        this.f14606w0 = null;
        this.f14608x0 = null;
        this.f14610y0 = null;
        this.f14612z0 = null;
        this.f14508A0 = null;
        this.f14510B0 = null;
        this.f14512C0 = null;
        this.f14514D0 = null;
        this.f14516E0 = null;
        this.f14518F0 = null;
        this.f14520G0 = null;
        this.f14522H0 = new Semaphore(0, true);
        this.f14524I0 = new Semaphore(0, true);
        this.f14526J0 = new Semaphore(0, true);
        this.f14528K0 = new Semaphore(0, true);
        this.f14530L0 = null;
        this.f14532M0 = null;
        this.f14534N0 = null;
        this.f14536O0 = null;
        this.f14538P0 = null;
        this.f14540Q0 = null;
        this.f14542R0 = 0;
        this.f14544S0 = null;
        this.f14546T0 = new Object();
        this.f14548U0 = new Object();
        this.f14550V0 = false;
        this.f14552W0 = false;
        this.f14554X0 = true;
        this.f14556Y0 = false;
        this.f14558Z0 = null;
        this.f14561a1 = null;
        this.f14564b1 = new C3170g();
        this.f14535O = str;
        this.f14515E = videoItem;
        if (videoItem != null) {
            this.f14592p0 = true;
            this.f14579j = new UploadUtils.C3196b();
            this.f14572f0 = String.valueOf(this.f14515E.m16213b());
            this.f14523I = this.f14515E.m16218g();
            this.f14525J = CLUtility.m16510Z1(this.f14515E.m16219h());
        }
    }

    public UploadMediaHelper(String str, FileItem fileItem) {
        this.f14559a = Executors.newFixedThreadPool(2, new ThreadFactoryC6373j("UploadMediaHelper"));
        this.f14562b = null;
        this.f14565c = null;
        this.f14567d = null;
        this.f14569e = null;
        this.f14571f = null;
        this.f14573g = null;
        this.f14575h = null;
        this.f14577i = null;
        this.f14579j = null;
        this.f14581k = null;
        this.f14583l = null;
        this.f14585m = null;
        this.f14587n = null;
        this.f14589o = null;
        this.f14591p = null;
        this.f14593q = null;
        this.f14595r = null;
        this.f14597s = null;
        this.f14599t = null;
        this.f14601u = null;
        this.f14603v = null;
        this.f14605w = null;
        this.f14607x = null;
        this.f14609y = null;
        this.f14611z = null;
        this.f14507A = null;
        this.f14509B = null;
        this.f14511C = null;
        this.f14513D = null;
        this.f14515E = null;
        this.f14517F = null;
        this.f14519G = null;
        this.f14521H = null;
        this.f14523I = null;
        this.f14525J = null;
        this.f14527K = null;
        this.f14529L = null;
        this.f14531M = null;
        this.f14533N = null;
        this.f14535O = null;
        this.f14537P = null;
        this.f14539Q = null;
        this.f14541R = null;
        this.f14543S = null;
        this.f14545T = null;
        this.f14547U = new FriendsClient(1);
        this.f14549V = null;
        this.f14551W = null;
        UploadUtils.UploadResultType uploadResultType = UploadUtils.UploadResultType.STEP_1_FAIL;
        this.f14553X = uploadResultType;
        this.f14555Y = uploadResultType;
        this.f14557Z = uploadResultType;
        this.f14560a0 = uploadResultType;
        this.f14563b0 = null;
        this.f14566c0 = null;
        this.f14568d0 = null;
        this.f14570e0 = null;
        this.f14572f0 = null;
        this.f14574g0 = 0;
        this.f14576h0 = 0;
        this.f14578i0 = 0;
        this.f14580j0 = 0;
        this.f14582k0 = 0;
        this.f14584l0 = null;
        this.f14586m0 = null;
        this.f14588n0 = false;
        this.f14590o0 = false;
        this.f14592p0 = false;
        this.f14594q0 = false;
        this.f14596r0 = false;
        this.f14598s0 = false;
        this.f14600t0 = false;
        this.f14602u0 = FailReason.FAIL_NONE;
        this.f14604v0 = false;
        this.f14606w0 = null;
        this.f14608x0 = null;
        this.f14610y0 = null;
        this.f14612z0 = null;
        this.f14508A0 = null;
        this.f14510B0 = null;
        this.f14512C0 = null;
        this.f14514D0 = null;
        this.f14516E0 = null;
        this.f14518F0 = null;
        this.f14520G0 = null;
        this.f14522H0 = new Semaphore(0, true);
        this.f14524I0 = new Semaphore(0, true);
        this.f14526J0 = new Semaphore(0, true);
        this.f14528K0 = new Semaphore(0, true);
        this.f14530L0 = null;
        this.f14532M0 = null;
        this.f14534N0 = null;
        this.f14536O0 = null;
        this.f14538P0 = null;
        this.f14540Q0 = null;
        this.f14542R0 = 0;
        this.f14544S0 = null;
        this.f14546T0 = new Object();
        this.f14548U0 = new Object();
        this.f14550V0 = false;
        this.f14552W0 = false;
        this.f14554X0 = true;
        this.f14556Y0 = false;
        this.f14558Z0 = null;
        this.f14561a1 = null;
        this.f14564b1 = new C3170g();
        this.f14537P = str;
        this.f14517F = fileItem;
        this.f14600t0 = true;
        this.f14581k = new UploadUtils.C3196b();
        this.f14527K = this.f14517F.m16115b();
        this.f14529L = CLUtility.m16510Z1(this.f14517F.m16117d());
    }

    public UploadMediaHelper(String str, ImageItem imageItem, String str2, String str3, String str4, String str5) {
        this(str, imageItem);
        this.f14533N = str2;
        this.f14511C = str3;
        this.f14568d0 = str4;
        if (CLUtility.m16613z1(str3, null)) {
            this.f14590o0 = true;
        }
        this.f14570e0 = str5;
    }

    public UploadMediaHelper(String str, ImageItem imageItem, String str2, String str3, String str4, VideoItem videoItem, String str5, FileItem fileItem, MessageObj messageObj, String str6) {
        String strM14747I;
        this(str, imageItem);
        this.f14533N = str2;
        this.f14535O = str4;
        this.f14511C = str3;
        this.f14515E = videoItem;
        this.f14537P = str5;
        this.f14517F = fileItem;
        this.f14563b0 = messageObj;
        this.f14584l0 = str6;
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
                UploadUtils.m16964k("UploadMediaHelperV2", "[UploadMediaHelper]", e9);
            }
        }
    }

    public UploadMediaHelper(String str, MessageObj messageObj, String str2, String str3) {
        this.f14559a = Executors.newFixedThreadPool(2, new ThreadFactoryC6373j("UploadMediaHelper"));
        this.f14562b = null;
        this.f14565c = null;
        this.f14567d = null;
        this.f14569e = null;
        this.f14571f = null;
        this.f14573g = null;
        this.f14575h = null;
        this.f14577i = null;
        this.f14579j = null;
        this.f14581k = null;
        this.f14583l = null;
        this.f14585m = null;
        this.f14587n = null;
        this.f14589o = null;
        this.f14591p = null;
        this.f14593q = null;
        this.f14595r = null;
        this.f14597s = null;
        this.f14599t = null;
        this.f14601u = null;
        this.f14603v = null;
        this.f14605w = null;
        this.f14607x = null;
        this.f14609y = null;
        this.f14611z = null;
        this.f14507A = null;
        this.f14509B = null;
        this.f14511C = null;
        this.f14513D = null;
        this.f14515E = null;
        this.f14517F = null;
        this.f14519G = null;
        this.f14521H = null;
        this.f14523I = null;
        this.f14525J = null;
        this.f14527K = null;
        this.f14529L = null;
        this.f14531M = null;
        this.f14533N = null;
        this.f14535O = null;
        this.f14537P = null;
        this.f14539Q = null;
        this.f14541R = null;
        this.f14543S = null;
        this.f14545T = null;
        this.f14547U = new FriendsClient(1);
        this.f14549V = null;
        this.f14551W = null;
        UploadUtils.UploadResultType uploadResultType = UploadUtils.UploadResultType.STEP_1_FAIL;
        this.f14553X = uploadResultType;
        this.f14555Y = uploadResultType;
        this.f14557Z = uploadResultType;
        this.f14560a0 = uploadResultType;
        this.f14563b0 = null;
        this.f14566c0 = null;
        this.f14568d0 = null;
        this.f14570e0 = null;
        this.f14572f0 = null;
        this.f14574g0 = 0;
        this.f14576h0 = 0;
        this.f14578i0 = 0;
        this.f14580j0 = 0;
        this.f14582k0 = 0;
        this.f14584l0 = null;
        this.f14586m0 = null;
        this.f14588n0 = false;
        this.f14590o0 = false;
        this.f14592p0 = false;
        this.f14594q0 = false;
        this.f14596r0 = false;
        this.f14598s0 = false;
        this.f14600t0 = false;
        this.f14602u0 = FailReason.FAIL_NONE;
        this.f14604v0 = false;
        this.f14606w0 = null;
        this.f14608x0 = null;
        this.f14610y0 = null;
        this.f14612z0 = null;
        this.f14508A0 = null;
        this.f14510B0 = null;
        this.f14512C0 = null;
        this.f14514D0 = null;
        this.f14516E0 = null;
        this.f14518F0 = null;
        this.f14520G0 = null;
        this.f14522H0 = new Semaphore(0, true);
        this.f14524I0 = new Semaphore(0, true);
        this.f14526J0 = new Semaphore(0, true);
        this.f14528K0 = new Semaphore(0, true);
        this.f14530L0 = null;
        this.f14532M0 = null;
        this.f14534N0 = null;
        this.f14536O0 = null;
        this.f14538P0 = null;
        this.f14540Q0 = null;
        this.f14542R0 = 0;
        this.f14544S0 = null;
        this.f14546T0 = new Object();
        this.f14548U0 = new Object();
        this.f14550V0 = false;
        this.f14552W0 = false;
        this.f14554X0 = true;
        this.f14556Y0 = false;
        this.f14558Z0 = null;
        this.f14561a1 = null;
        this.f14564b1 = new C3170g();
        this.f14531M = str;
        this.f14563b0 = messageObj;
        this.f14584l0 = str2;
        this.f14521H = str3;
        if (CLUtility.m16613z1(str3, null)) {
            this.f14598s0 = true;
        }
        MessageObj messageObj2 = this.f14563b0;
        this.f14594q0 = messageObj2 != null;
        if (messageObj2 != null) {
            try {
                this.f14566c0 = messageObj2.m14777o();
            } catch (Exception e9) {
                UploadUtils.m16964k("UploadMediaHelperV2", "[UploadMediaHelper]", e9);
            }
        }
    }

    public UploadMediaHelper(String str, C3184u c3184u) {
        this.f14559a = Executors.newFixedThreadPool(2, new ThreadFactoryC6373j("UploadMediaHelper"));
        this.f14562b = null;
        this.f14565c = null;
        this.f14567d = null;
        this.f14569e = null;
        this.f14571f = null;
        this.f14573g = null;
        this.f14575h = null;
        this.f14577i = null;
        this.f14579j = null;
        this.f14581k = null;
        this.f14583l = null;
        this.f14585m = null;
        this.f14587n = null;
        this.f14589o = null;
        this.f14591p = null;
        this.f14593q = null;
        this.f14595r = null;
        this.f14597s = null;
        this.f14599t = null;
        this.f14601u = null;
        this.f14603v = null;
        this.f14605w = null;
        this.f14607x = null;
        this.f14609y = null;
        this.f14611z = null;
        this.f14507A = null;
        this.f14509B = null;
        this.f14511C = null;
        this.f14513D = null;
        this.f14515E = null;
        this.f14517F = null;
        this.f14519G = null;
        this.f14521H = null;
        this.f14523I = null;
        this.f14525J = null;
        this.f14527K = null;
        this.f14529L = null;
        this.f14531M = null;
        this.f14533N = null;
        this.f14535O = null;
        this.f14537P = null;
        this.f14539Q = null;
        this.f14541R = null;
        this.f14543S = null;
        this.f14545T = null;
        this.f14547U = new FriendsClient(1);
        this.f14549V = null;
        this.f14551W = null;
        UploadUtils.UploadResultType uploadResultType = UploadUtils.UploadResultType.STEP_1_FAIL;
        this.f14553X = uploadResultType;
        this.f14555Y = uploadResultType;
        this.f14557Z = uploadResultType;
        this.f14560a0 = uploadResultType;
        this.f14563b0 = null;
        this.f14566c0 = null;
        this.f14568d0 = null;
        this.f14570e0 = null;
        this.f14572f0 = null;
        this.f14574g0 = 0;
        this.f14576h0 = 0;
        this.f14578i0 = 0;
        this.f14580j0 = 0;
        this.f14582k0 = 0;
        this.f14584l0 = null;
        this.f14586m0 = null;
        this.f14588n0 = false;
        this.f14590o0 = false;
        this.f14592p0 = false;
        this.f14594q0 = false;
        this.f14596r0 = false;
        this.f14598s0 = false;
        this.f14600t0 = false;
        this.f14602u0 = FailReason.FAIL_NONE;
        this.f14604v0 = false;
        this.f14606w0 = null;
        this.f14608x0 = null;
        this.f14610y0 = null;
        this.f14612z0 = null;
        this.f14508A0 = null;
        this.f14510B0 = null;
        this.f14512C0 = null;
        this.f14514D0 = null;
        this.f14516E0 = null;
        this.f14518F0 = null;
        this.f14520G0 = null;
        this.f14522H0 = new Semaphore(0, true);
        this.f14524I0 = new Semaphore(0, true);
        this.f14526J0 = new Semaphore(0, true);
        this.f14528K0 = new Semaphore(0, true);
        this.f14530L0 = null;
        this.f14532M0 = null;
        this.f14534N0 = null;
        this.f14536O0 = null;
        this.f14538P0 = null;
        this.f14540Q0 = null;
        this.f14542R0 = 0;
        this.f14544S0 = null;
        this.f14546T0 = new Object();
        this.f14548U0 = new Object();
        this.f14550V0 = false;
        this.f14552W0 = false;
        this.f14554X0 = true;
        this.f14556Y0 = false;
        this.f14558Z0 = null;
        this.f14561a1 = null;
        this.f14564b1 = new C3170g();
        this.f14531M = str;
        this.f14519G = c3184u;
        if (c3184u != null) {
            this.f14596r0 = true;
        }
    }

    public UploadMediaHelper(String str) {
        this.f14559a = Executors.newFixedThreadPool(2, new ThreadFactoryC6373j("UploadMediaHelper"));
        this.f14562b = null;
        this.f14565c = null;
        this.f14567d = null;
        this.f14569e = null;
        this.f14571f = null;
        this.f14573g = null;
        this.f14575h = null;
        this.f14577i = null;
        this.f14579j = null;
        this.f14581k = null;
        this.f14583l = null;
        this.f14585m = null;
        this.f14587n = null;
        this.f14589o = null;
        this.f14591p = null;
        this.f14593q = null;
        this.f14595r = null;
        this.f14597s = null;
        this.f14599t = null;
        this.f14601u = null;
        this.f14603v = null;
        this.f14605w = null;
        this.f14607x = null;
        this.f14609y = null;
        this.f14611z = null;
        this.f14507A = null;
        this.f14509B = null;
        this.f14511C = null;
        this.f14513D = null;
        this.f14515E = null;
        this.f14517F = null;
        this.f14519G = null;
        this.f14521H = null;
        this.f14523I = null;
        this.f14525J = null;
        this.f14527K = null;
        this.f14529L = null;
        this.f14531M = null;
        this.f14533N = null;
        this.f14535O = null;
        this.f14537P = null;
        this.f14539Q = null;
        this.f14541R = null;
        this.f14543S = null;
        this.f14545T = null;
        this.f14547U = new FriendsClient(1);
        this.f14549V = null;
        this.f14551W = null;
        UploadUtils.UploadResultType uploadResultType = UploadUtils.UploadResultType.STEP_1_FAIL;
        this.f14553X = uploadResultType;
        this.f14555Y = uploadResultType;
        this.f14557Z = uploadResultType;
        this.f14560a0 = uploadResultType;
        this.f14563b0 = null;
        this.f14566c0 = null;
        this.f14568d0 = null;
        this.f14570e0 = null;
        this.f14572f0 = null;
        this.f14574g0 = 0;
        this.f14576h0 = 0;
        this.f14578i0 = 0;
        this.f14580j0 = 0;
        this.f14582k0 = 0;
        this.f14584l0 = null;
        this.f14586m0 = null;
        this.f14588n0 = false;
        this.f14590o0 = false;
        this.f14592p0 = false;
        this.f14594q0 = false;
        this.f14596r0 = false;
        this.f14598s0 = false;
        this.f14600t0 = false;
        this.f14602u0 = FailReason.FAIL_NONE;
        this.f14604v0 = false;
        this.f14606w0 = null;
        this.f14608x0 = null;
        this.f14610y0 = null;
        this.f14612z0 = null;
        this.f14508A0 = null;
        this.f14510B0 = null;
        this.f14512C0 = null;
        this.f14514D0 = null;
        this.f14516E0 = null;
        this.f14518F0 = null;
        this.f14520G0 = null;
        this.f14522H0 = new Semaphore(0, true);
        this.f14524I0 = new Semaphore(0, true);
        this.f14526J0 = new Semaphore(0, true);
        this.f14528K0 = new Semaphore(0, true);
        this.f14530L0 = null;
        this.f14532M0 = null;
        this.f14534N0 = null;
        this.f14536O0 = null;
        this.f14538P0 = null;
        this.f14540Q0 = null;
        this.f14542R0 = 0;
        this.f14544S0 = null;
        this.f14546T0 = new Object();
        this.f14548U0 = new Object();
        this.f14550V0 = false;
        this.f14552W0 = false;
        this.f14554X0 = true;
        this.f14556Y0 = false;
        this.f14558Z0 = null;
        this.f14561a1 = null;
        this.f14564b1 = new C3170g();
        m16817S1(str);
    }

    public UploadMediaHelper() {
        this.f14559a = Executors.newFixedThreadPool(2, new ThreadFactoryC6373j("UploadMediaHelper"));
        this.f14562b = null;
        this.f14565c = null;
        this.f14567d = null;
        this.f14569e = null;
        this.f14571f = null;
        this.f14573g = null;
        this.f14575h = null;
        this.f14577i = null;
        this.f14579j = null;
        this.f14581k = null;
        this.f14583l = null;
        this.f14585m = null;
        this.f14587n = null;
        this.f14589o = null;
        this.f14591p = null;
        this.f14593q = null;
        this.f14595r = null;
        this.f14597s = null;
        this.f14599t = null;
        this.f14601u = null;
        this.f14603v = null;
        this.f14605w = null;
        this.f14607x = null;
        this.f14609y = null;
        this.f14611z = null;
        this.f14507A = null;
        this.f14509B = null;
        this.f14511C = null;
        this.f14513D = null;
        this.f14515E = null;
        this.f14517F = null;
        this.f14519G = null;
        this.f14521H = null;
        this.f14523I = null;
        this.f14525J = null;
        this.f14527K = null;
        this.f14529L = null;
        this.f14531M = null;
        this.f14533N = null;
        this.f14535O = null;
        this.f14537P = null;
        this.f14539Q = null;
        this.f14541R = null;
        this.f14543S = null;
        this.f14545T = null;
        this.f14547U = new FriendsClient(1);
        this.f14549V = null;
        this.f14551W = null;
        UploadUtils.UploadResultType uploadResultType = UploadUtils.UploadResultType.STEP_1_FAIL;
        this.f14553X = uploadResultType;
        this.f14555Y = uploadResultType;
        this.f14557Z = uploadResultType;
        this.f14560a0 = uploadResultType;
        this.f14563b0 = null;
        this.f14566c0 = null;
        this.f14568d0 = null;
        this.f14570e0 = null;
        this.f14572f0 = null;
        this.f14574g0 = 0;
        this.f14576h0 = 0;
        this.f14578i0 = 0;
        this.f14580j0 = 0;
        this.f14582k0 = 0;
        this.f14584l0 = null;
        this.f14586m0 = null;
        this.f14588n0 = false;
        this.f14590o0 = false;
        this.f14592p0 = false;
        this.f14594q0 = false;
        this.f14596r0 = false;
        this.f14598s0 = false;
        this.f14600t0 = false;
        this.f14602u0 = FailReason.FAIL_NONE;
        this.f14604v0 = false;
        this.f14606w0 = null;
        this.f14608x0 = null;
        this.f14610y0 = null;
        this.f14612z0 = null;
        this.f14508A0 = null;
        this.f14510B0 = null;
        this.f14512C0 = null;
        this.f14514D0 = null;
        this.f14516E0 = null;
        this.f14518F0 = null;
        this.f14520G0 = null;
        this.f14522H0 = new Semaphore(0, true);
        this.f14524I0 = new Semaphore(0, true);
        this.f14526J0 = new Semaphore(0, true);
        this.f14528K0 = new Semaphore(0, true);
        this.f14530L0 = null;
        this.f14532M0 = null;
        this.f14534N0 = null;
        this.f14536O0 = null;
        this.f14538P0 = null;
        this.f14540Q0 = null;
        this.f14542R0 = 0;
        this.f14544S0 = null;
        this.f14546T0 = new Object();
        this.f14548U0 = new Object();
        this.f14550V0 = false;
        this.f14552W0 = false;
        this.f14554X0 = true;
        this.f14556Y0 = false;
        this.f14558Z0 = null;
        this.f14561a1 = null;
        this.f14564b1 = new C3170g();
    }
}
