package com.cyberlink.you.utility;

import android.net.Uri;
import android.util.Log;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2951b1;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.friends.C3061a;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.pages.photoimport.FileItem;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.pages.photoimport.VideoItem;
import com.cyberlink.you.utility.UploadMediaHelper;
import com.cyberlink.you.utility.UploadUtils;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.plus.PlusShare;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import org.json.JSONException;
import org.json.JSONObject;
import p182r2.C6196d0;
import p182r2.C6199g;
import p182r2.C6201i;
import p182r2.C6202j;
import p209u2.C6369f;
import p209u2.C6389z;
import p218v2.C6456d;
import p236x2.C6566a;

/* loaded from: classes.dex */
public class UploadMultipleChatMediaHelperQueue {

    /* renamed from: a */
    public final LinkedBlockingQueue<UploadMediaHelper> f14669a;

    /* renamed from: b */
    public final C6389z<InterfaceC3194g> f14670b;

    /* renamed from: c */
    public boolean f14671c;

    /* renamed from: d */
    public final Object f14672d;

    /* renamed from: e */
    public boolean f14673e;

    /* renamed from: f */
    public boolean f14674f;

    /* renamed from: g */
    public boolean f14675g;

    /* renamed from: h */
    public final ArrayList<String> f14676h;

    /* renamed from: i */
    public String f14677i;

    /* renamed from: j */
    public Object f14678j;

    /* renamed from: k */
    public C3198b f14679k;

    /* renamed from: l */
    public XMPPManager.InterfaceC2849a0 f14680l;

    /* renamed from: m */
    public C6456d.j f14681m;

    /* renamed from: n */
    public UploadMediaHelper.AbstractC3185v f14682n;

    public enum CallbackType {
        BEGIN_UPLOAD,
        VOICE_COMPLETE,
        SMALL_COMPLETE,
        PROGRESS,
        COMPLETE,
        EXPIRED,
        VIDEO_COMPLETE,
        FILE_COMPLETE
    }

    /* renamed from: com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue$a */
    public class C3188a implements XMPPManager.InterfaceC2849a0 {
        public C3188a() {
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2849a0
        /* renamed from: F */
        public void mo5716F(String str) {
            m16948b(str);
        }

        /* renamed from: b */
        public final void m16948b(String str) {
            int iIndexOf;
            if (!UploadMultipleChatMediaHelperQueue.this.f14676h.isEmpty() && (iIndexOf = UploadMultipleChatMediaHelperQueue.this.f14676h.indexOf(str)) >= 0) {
                UploadMultipleChatMediaHelperQueue.this.f14676h.remove(iIndexOf);
                UploadMultipleChatMediaHelperQueue.this.m16930V();
            }
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2849a0
        /* renamed from: p */
        public void mo5718p(String str, Date date) {
            m16948b(str);
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue$b */
    public class C3189b extends UploadMediaHelper.AbstractC3185v {
        public C3189b() {
        }

        @Override // com.cyberlink.you.utility.UploadMediaHelper.AbstractC3185v
        /* renamed from: a */
        public void mo16873a(UploadMediaHelper uploadMediaHelper) {
            UploadMultipleChatMediaHelperQueue.this.m16927S(CallbackType.PROGRESS, uploadMediaHelper.m16820U0(), uploadMediaHelper.m16830Z0());
        }

        @Override // com.cyberlink.you.utility.UploadMediaHelper.AbstractC3185v
        /* renamed from: c */
        public void mo8381c(UploadMediaHelper uploadMediaHelper) {
            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onStep1Complete] in");
            m16949f(uploadMediaHelper);
        }

        @Override // com.cyberlink.you.utility.UploadMediaHelper.AbstractC3185v
        /* renamed from: d */
        public void mo16875d(UploadMediaHelper uploadMediaHelper) {
            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onStep2Complete] in");
            m16949f(uploadMediaHelper);
        }

        @Override // com.cyberlink.you.utility.UploadMediaHelper.AbstractC3185v
        /* renamed from: e */
        public void mo8382e(UploadMediaHelper uploadMediaHelper) throws JSONException, NumberFormatException {
            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onStep3Complete] in");
            UploadMultipleChatMediaHelperQueue.this.m16928T(uploadMediaHelper);
            m16949f(uploadMediaHelper);
        }

        /* JADX WARN: Removed duplicated region for block: B:66:0x02a4 A[Catch: all -> 0x02ef, TryCatch #1 {, blocks: (B:38:0x00d6, B:40:0x0103, B:42:0x0109, B:44:0x010f, B:45:0x0113, B:47:0x0121, B:49:0x0159, B:64:0x028d, B:66:0x02a4, B:68:0x02c1, B:69:0x02ed, B:67:0x02b5, B:50:0x0183, B:51:0x019f, B:53:0x01a9, B:55:0x01d6, B:57:0x020a, B:59:0x0212, B:63:0x0286, B:60:0x0244, B:61:0x026a), top: B:78:0x00d6, outer: #0 }] */
        /* JADX WARN: Removed duplicated region for block: B:67:0x02b5 A[Catch: all -> 0x02ef, TryCatch #1 {, blocks: (B:38:0x00d6, B:40:0x0103, B:42:0x0109, B:44:0x010f, B:45:0x0113, B:47:0x0121, B:49:0x0159, B:64:0x028d, B:66:0x02a4, B:68:0x02c1, B:69:0x02ed, B:67:0x02b5, B:50:0x0183, B:51:0x019f, B:53:0x01a9, B:55:0x01d6, B:57:0x020a, B:59:0x0212, B:63:0x0286, B:60:0x0244, B:61:0x026a), top: B:78:0x00d6, outer: #0 }] */
        /* renamed from: f */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void m16949f(UploadMediaHelper uploadMediaHelper) {
            boolean z8;
            try {
                MessageObj.MessageType messageTypeM14778p = uploadMediaHelper.m16822V0().m14778p();
                UploadUtils.UploadResultType uploadResultTypeM16853k1 = messageTypeM14778p == MessageObj.MessageType.Audio ? uploadMediaHelper.m16853k1() : messageTypeM14778p == MessageObj.MessageType.Video ? uploadMediaHelper.m16848i1() : messageTypeM14778p == MessageObj.MessageType.File ? uploadMediaHelper.m16810O0() : uploadMediaHelper.m16828Y0();
                UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleStepComplete]" + Thread.currentThread().getName() + " In. Result type:" + uploadResultTypeM16853k1);
                Log.d("UploadMediaHelperQueue", "[onHandleStepComplete]" + Thread.currentThread().getName() + " In. Result type:" + uploadResultTypeM16853k1);
                if (uploadResultTypeM16853k1 != UploadUtils.UploadResultType.STEP_1_FAIL && uploadResultTypeM16853k1 != UploadUtils.UploadResultType.STEP_2_VOICE_FAIL && uploadResultTypeM16853k1 != UploadUtils.UploadResultType.STEP_2_VIDEO_FAIL && uploadResultTypeM16853k1 != UploadUtils.UploadResultType.STEP_2_FILE_FAIL && uploadResultTypeM16853k1 != UploadUtils.UploadResultType.STEP_2_SMALL_FAIL && uploadResultTypeM16853k1 != UploadUtils.UploadResultType.STEP_3_VOICE_FAIL && uploadResultTypeM16853k1 != UploadUtils.UploadResultType.STEP_3_VIDEO_FAIL && uploadResultTypeM16853k1 != UploadUtils.UploadResultType.STEP_3_FILE_FAIL && uploadResultTypeM16853k1 != UploadUtils.UploadResultType.STEP_3_SMALL_FAIL && uploadResultTypeM16853k1 != UploadUtils.UploadResultType.STEP_3_BIG_FAIL && uploadResultTypeM16853k1 != UploadUtils.UploadResultType.STEP_3_SUCCESS) {
                    UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleStepComplete] Upload is not fail case and reset retry count to 0.");
                    uploadMediaHelper.m16812P1();
                    return;
                }
                Log.d("UploadMediaHelperQueue", "[onHandleStepComplete]" + Thread.currentThread().getName() + " wait for mQueueSync");
                synchronized (UploadMultipleChatMediaHelperQueue.this.f14672d) {
                    Log.d("UploadMediaHelperQueue", "[onHandleStepComplete]" + Thread.currentThread().getName() + " get mQueueSync");
                    String strM16820U0 = uploadMediaHelper.m16820U0();
                    C2973l0 c2973l0M16826X0 = uploadMediaHelper.m16826X0();
                    if (c2973l0M16826X0 == null) {
                        c2973l0M16826X0 = uploadMediaHelper.m16851j1();
                    }
                    if (c2973l0M16826X0 == null) {
                        c2973l0M16826X0 = uploadMediaHelper.m16846h1();
                    }
                    if (c2973l0M16826X0 == null) {
                        c2973l0M16826X0 = uploadMediaHelper.m16808N0();
                    }
                    String strM16838d1 = uploadMediaHelper.m16838d1();
                    UploadUtils.UploadResultType uploadResultType = UploadUtils.UploadResultType.STEP_3_SUCCESS;
                    if (uploadResultTypeM16853k1.equals(uploadResultType)) {
                        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleStepComplete] Upload '" + strM16820U0 + "' successfully.");
                        UploadMultipleChatMediaHelperQueue.this.m16933Y(true);
                        MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(strM16820U0);
                        Log.d("UploadMediaHelperQueue", "[onHandleStepComplete] upload success");
                        Log.d("UploadMediaHelperQueue", "====================================================");
                        if (messageObjM15179r != null) {
                            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleStepComplete] Set '" + strM16820U0 + "' message upload status to UPLOAD_STATUS_COMPLETED");
                            messageObjM15179r.m14765a0("3");
                            C2950b0.m14916o().m15159D(strM16820U0, messageObjM15179r, "UploadStatus");
                        } else {
                            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleStepComplete] Set '" + strM16820U0 + "' message upload status to STATUS_NORMAL, but can't be found in Message DB.");
                        }
                    } else if (uploadMediaHelper.m16832a1() < uploadMediaHelper.m16816S0()) {
                        uploadMediaHelper.m16861p1();
                        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleStepComplete] Upload '" + strM16820U0 + "' failed and need to retry(mRetryCount:'" + uploadMediaHelper.m16832a1() + "')");
                    } else {
                        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleStepComplete] Upload '" + strM16820U0 + "' failed and can't be retry anymore.");
                        UploadMultipleChatMediaHelperQueue.this.m16933Y(false);
                        uploadMediaHelper.m16812P1();
                        Log.d("UploadMediaHelperQueue", "[onHandleStepComplete] resetRetryCount");
                        MessageObj messageObjM15179r2 = C2950b0.m14916o().m15179r(strM16820U0);
                        if (messageObjM15179r2 == null) {
                            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleStepComplete] Force delete, but the '" + strM16820U0 + "' can't be found in Message DB.");
                        } else if (uploadResultTypeM16853k1.equals(UploadUtils.UploadResultType.STEP_3_BIG_FAIL)) {
                            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleStepComplete] Force delete '" + strM16820U0 + "'. The result type is STEP_3_BIG_FAIL, but set upload status to UPLOAD_STATUS_BIG_FAILED");
                            messageObjM15179r2.m14765a0("5");
                            C2950b0.m14916o().m15159D(strM16820U0, messageObjM15179r2, "UploadStatus");
                            Log.d("UploadMediaHelperQueue", "[onHandleStepComplete] upload big img fail");
                            z8 = true;
                            Log.d("UploadMediaHelperQueue", "====================================================");
                            UploadMultipleChatMediaHelperQueue.this.m16938d0(strM16820U0, uploadResultTypeM16853k1, strM16838d1, z8);
                            UploadMultipleChatMediaHelperQueue.this.f14673e = false;
                            UploadMultipleChatMediaHelperQueue.this.m16925Q(CallbackType.COMPLETE, c2973l0M16826X0, strM16820U0);
                            if (uploadResultTypeM16853k1.equals(uploadResultType)) {
                                UploadMultipleChatMediaHelperQueue.this.m16934Z(uploadMediaHelper.m16822V0(), c2973l0M16826X0);
                                UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleStepComplete] The upload task is finished. Continue to upload next media immediately.");
                            } else {
                                UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleStepComplete] The upload task is failed. Wait 3000 millisecond to run next.");
                                UploadMultipleChatMediaHelperQueue.this.f14674f = true;
                            }
                            UploadMultipleChatMediaHelperQueue.this.f14672d.notifyAll();
                            Log.d("UploadMediaHelperQueue", "[onHandleStepComplete]" + Thread.currentThread().getName() + " end and notify mQueueSync");
                        } else {
                            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleStepComplete] The '" + strM16820U0 + "' set upload status to UPLOAD_STATUS_FAILED.");
                            UploadMultipleChatMediaHelperQueue.m16893G(messageObjM15179r2);
                            Log.d("UploadMediaHelperQueue", "[onHandleStepComplete] upload small img fail");
                        }
                        z8 = false;
                        Log.d("UploadMediaHelperQueue", "====================================================");
                        UploadMultipleChatMediaHelperQueue.this.m16938d0(strM16820U0, uploadResultTypeM16853k1, strM16838d1, z8);
                        UploadMultipleChatMediaHelperQueue.this.f14673e = false;
                        UploadMultipleChatMediaHelperQueue.this.m16925Q(CallbackType.COMPLETE, c2973l0M16826X0, strM16820U0);
                        if (uploadResultTypeM16853k1.equals(uploadResultType)) {
                        }
                        UploadMultipleChatMediaHelperQueue.this.f14672d.notifyAll();
                        Log.d("UploadMediaHelperQueue", "[onHandleStepComplete]" + Thread.currentThread().getName() + " end and notify mQueueSync");
                    }
                    z8 = false;
                    UploadMultipleChatMediaHelperQueue.this.m16938d0(strM16820U0, uploadResultTypeM16853k1, strM16838d1, z8);
                    UploadMultipleChatMediaHelperQueue.this.f14673e = false;
                    UploadMultipleChatMediaHelperQueue.this.m16925Q(CallbackType.COMPLETE, c2973l0M16826X0, strM16820U0);
                    if (uploadResultTypeM16853k1.equals(uploadResultType)) {
                    }
                    UploadMultipleChatMediaHelperQueue.this.f14672d.notifyAll();
                    Log.d("UploadMediaHelperQueue", "[onHandleStepComplete]" + Thread.currentThread().getName() + " end and notify mQueueSync");
                }
            } catch (Exception e9) {
                UploadUtils.m16964k("UploadMultipleChatMediaHelperQueue", "[onHandleStepComplete]", e9);
            }
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue$c */
    public class C3190c extends C6389z.a<InterfaceC3194g> {

        /* renamed from: a */
        public final /* synthetic */ CallbackType f14694a;

        /* renamed from: b */
        public final /* synthetic */ String f14695b;

        /* renamed from: c */
        public final /* synthetic */ C2973l0 f14696c;

        public C3190c(CallbackType callbackType, String str, C2973l0 c2973l0) {
            this.f14694a = callbackType;
            this.f14695b = str;
            this.f14696c = c2973l0;
        }

        @Override // p209u2.C6389z.a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo5635b(InterfaceC3194g interfaceC3194g) {
            CallbackType callbackType = this.f14694a;
            if (callbackType == CallbackType.SMALL_COMPLETE) {
                interfaceC3194g.mo6940G(this.f14695b);
                return;
            }
            if (callbackType == CallbackType.VOICE_COMPLETE) {
                interfaceC3194g.mo6943W(this.f14695b);
                return;
            }
            if (callbackType == CallbackType.VIDEO_COMPLETE) {
                UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", " [notifyListener] In : CallbackType = " + this.f14694a);
                interfaceC3194g.mo6942U(this.f14695b);
                MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(this.f14695b);
                if (messageObjM15179r != null) {
                    CLUtility.m16566n2(messageObjM15179r);
                    return;
                }
                return;
            }
            if (callbackType != CallbackType.COMPLETE) {
                if (callbackType == CallbackType.FILE_COMPLETE) {
                    interfaceC3194g.mo6941K(this.f14695b);
                    return;
                } else {
                    UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[notifyListener] Incorrect callback Type");
                    return;
                }
            }
            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", " [notifyListener] In : CallbackType = " + this.f14694a);
            interfaceC3194g.mo6946m(this.f14696c, this.f14695b);
            MessageObj messageObjM15179r2 = C2950b0.m14916o().m15179r(this.f14695b);
            if (messageObjM15179r2 != null) {
                CLUtility.m16566n2(messageObjM15179r2);
            }
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue$d */
    public class C3191d extends C6389z.a<InterfaceC3194g> {

        /* renamed from: a */
        public final /* synthetic */ CallbackType f14698a;

        /* renamed from: b */
        public final /* synthetic */ String f14699b;

        /* renamed from: c */
        public final /* synthetic */ int f14700c;

        public C3191d(CallbackType callbackType, String str, int i9) {
            this.f14698a = callbackType;
            this.f14699b = str;
            this.f14700c = i9;
        }

        @Override // p209u2.C6389z.a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo5635b(InterfaceC3194g interfaceC3194g) {
            CallbackType callbackType = this.f14698a;
            if (callbackType == CallbackType.BEGIN_UPLOAD) {
                interfaceC3194g.mo6945f0(this.f14699b, this.f14700c);
            } else if (callbackType == CallbackType.PROGRESS) {
                interfaceC3194g.mo6947t(this.f14699b, this.f14700c);
            } else {
                UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[notifyListener] Incorrect callback Type");
            }
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue$e */
    public class C3192e extends C6389z.a<InterfaceC3194g> {

        /* renamed from: a */
        public final /* synthetic */ CallbackType f14702a;

        /* renamed from: b */
        public final /* synthetic */ String f14703b;

        public C3192e(CallbackType callbackType, String str) {
            this.f14702a = callbackType;
            this.f14703b = str;
        }

        @Override // p209u2.C6389z.a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo5635b(InterfaceC3194g interfaceC3194g) {
            if (this.f14702a == CallbackType.EXPIRED) {
                interfaceC3194g.mo6948t0(this.f14703b);
            } else {
                UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[notifyListener] Incorrect callback Type");
            }
        }
    }

    /* renamed from: com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue$f */
    public static class C3193f {

        /* renamed from: a */
        public static final UploadMultipleChatMediaHelperQueue f14705a = new UploadMultipleChatMediaHelperQueue(null);
    }

    /* renamed from: com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue$g */
    public interface InterfaceC3194g extends C6389z.b {
        /* renamed from: G */
        void mo6940G(String str);

        /* renamed from: K */
        void mo6941K(String str);

        /* renamed from: U */
        void mo6942U(String str);

        /* renamed from: W */
        void mo6943W(String str);

        /* renamed from: f0 */
        void mo6945f0(String str, int i9);

        /* renamed from: m */
        void mo6946m(C2973l0 c2973l0, String str);

        /* renamed from: t */
        void mo6947t(String str, int i9);

        /* renamed from: t0 */
        void mo6948t0(String str);
    }

    public /* synthetic */ UploadMultipleChatMediaHelperQueue(C3188a c3188a) {
        this();
    }

    /* renamed from: F */
    public static UploadMultipleChatMediaHelperQueue m16892F() {
        return C3193f.f14705a;
    }

    /* renamed from: G */
    public static void m16893G(MessageObj messageObj) {
        C6566a.m25152k(true, messageObj.m14778p());
        messageObj.m14765a0("4");
        C2950b0.m14916o().m15159D(messageObj.m14777o(), messageObj, "UploadStatus");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L */
    public /* synthetic */ void m16894L(List list) {
        Iterator<UploadMediaHelper> it = this.f14669a.iterator();
        int i9 = 0;
        while (it.hasNext()) {
            UploadMediaHelper next = it.next();
            MessageObj messageObjM16822V0 = next.m16822V0();
            if (messageObjM16822V0 != null) {
                Iterator it2 = list.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        String str = (String) it2.next();
                        if (str.equals(messageObjM16822V0.m14777o())) {
                            i9++;
                            next.m16850j();
                            C2950b0.m14916o().m15169h(str);
                            C2950b0.m14926y().m14882h(str);
                            break;
                        }
                    }
                }
            }
        }
        if (i9 >= this.f14669a.size()) {
            this.f14673e = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M */
    public /* synthetic */ void m16895M(String str) {
        UploadMediaHelper next;
        Thread.currentThread().setName("Delete upload media");
        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[deleteMedia] In.");
        if (str == null || str.isEmpty()) {
            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[deleteMedia] Invalid message id.");
            return;
        }
        if (this.f14669a.isEmpty()) {
            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[deleteMedia] mUploadMediaHelperQueue is null or size is 0");
            return;
        }
        try {
            synchronized (this.f14672d) {
                C2950b0.m14926y().m14882h(str);
                UploadMediaHelper uploadMediaHelperPeek = this.f14669a.peek();
                if (str.equals(uploadMediaHelperPeek.m16820U0())) {
                    uploadMediaHelperPeek.m16850j();
                    UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[deleteMedia] The message '" + str + "' is uploading media.");
                    m16932X(uploadMediaHelperPeek);
                    this.f14673e = false;
                    this.f14672d.notifyAll();
                } else {
                    UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[deleteMedia] The message '" + str + "' is NOT uploading media, find it in mUploadMediaHelperQueue");
                    Iterator<UploadMediaHelper> it = this.f14669a.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            next = null;
                            break;
                        } else {
                            next = it.next();
                            if (str.equals(next.m16820U0())) {
                                break;
                            }
                        }
                    }
                    if (next != null) {
                        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[deleteMedia] The message '" + str + "' is in mUploadMediaHelperQueue");
                        m16932X(next);
                    } else {
                        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[deleteMedia] The message '" + str + "' is NOT in mUploadMediaHelperQueue");
                    }
                }
            }
        } catch (Exception e9) {
            UploadUtils.m16964k("UploadMultipleChatMediaHelperQueue", "[deleteMedia]", e9);
        }
        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[deleteMedia] out.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N */
    public /* synthetic */ void m16896N() {
        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onConnected] in");
        m16929U();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O */
    public /* synthetic */ void m16897O() {
        Thread.currentThread().setName("Upload Chat Media");
        while (!this.f14675g) {
            m16919D();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: P */
    public /* synthetic */ void m16898P() {
        this.f14669a.clear();
        Thread.currentThread().setName("Fill Upload Media Queue");
        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[start] generateUnUploadedMedia before");
        List<C2951b1> listM14884j = C2950b0.m14926y().m14884j(1);
        if (listM14884j != null) {
            m16920E(listM14884j);
            m16929U();
        } else {
            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[start] There is no re-send medias");
        }
        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[start] generateUnUploadedMedia after");
    }

    /* renamed from: A */
    public void m16916A(boolean z8, String str, String str2, MessageObj messageObj, String str3) {
        m16944w(z8, null, null, str, str2, null, null, null, null, messageObj, str3, null, false);
    }

    /* renamed from: B */
    public final void m16917B(UploadMediaHelper uploadMediaHelper, MessageObj messageObj) {
        C6201i c6201i = new C6201i(uploadMediaHelper.m16806M0(), messageObj);
        if (!c6201i.m23721f()) {
            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[beforeAddUploadHelper] Add uploadMediaHelper but send message is not waiting for upload media object. MessageType:" + messageObj.m14778p().toString());
        }
        c6201i.m23720e(c6201i.m23721f());
        C6196d0.m23692d().m23700j(c6201i);
        uploadMediaHelper.m16814Q1(c6201i.m23718a());
        uploadMediaHelper.m16815R1(this.f14682n);
    }

    /* renamed from: C */
    public void m16918C(final String str) {
        new Thread(new Runnable() { // from class: k4.i1
            @Override // java.lang.Runnable
            public final void run() {
                this.f17673b.m16895M(str);
            }
        }).start();
    }

    /* renamed from: D */
    public final void m16919D() {
        Log.d("UploadMediaHelperQueue", "[do_Upload] wait for mQueueSync");
        synchronized (this.f14672d) {
            Log.d("UploadMediaHelperQueue", "[do_Upload] get mQueueSync");
            try {
                Log.d("UploadMediaHelperQueue", "[do_Upload] wait for WAKEUP_TIME 60s");
                this.f14672d.wait(60000L);
                UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[do_Upload] in.");
            } catch (Exception e9) {
                UploadUtils.m16964k("UploadMultipleChatMediaHelperQueue", "[do_Upload]", e9);
            }
            if (this.f14675g) {
                UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[do_Upload] Is stop.");
                Log.d("UploadMediaHelperQueue", "[do_Upload] Is stop.");
                return;
            }
            if (this.f14673e) {
                UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[do_Upload] Uploading media now");
                Log.d("UploadMediaHelperQueue", "[do_Upload] Uploading media now");
                return;
            }
            if (this.f14674f) {
                UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[do_Upload] Before delay wait.");
                Log.d("UploadMediaHelperQueue", "[do_Upload] wait for DELAY_NEXT_TIME 3s");
                this.f14672d.wait(3000L);
                UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[do_Upload] After delay wait.");
                this.f14674f = false;
            }
            while (true) {
                Log.d("UploadMediaHelperQueue", "[do_Upload] in while");
                UploadMediaHelper uploadMediaHelperPeek = this.f14669a.peek();
                if (uploadMediaHelperPeek == null) {
                    UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[do_Upload] empty queue.");
                    break;
                }
                String strM16820U0 = uploadMediaHelperPeek.m16820U0();
                MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(strM16820U0);
                boolean z8 = messageObjM15179r != null && m16924K(messageObjM15179r.m14788z().getTime());
                if (messageObjM15179r == null || z8) {
                    UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[do_Upload] The message'" + strM16820U0 + "' is null or expired('" + z8 + "').");
                    this.f14673e = false;
                    m16933Y(false);
                    C2950b0.m14926y().m14882h(strM16820U0);
                    if (messageObjM15179r != null && z8) {
                        m16893G(messageObjM15179r);
                        m16926R(CallbackType.EXPIRED, strM16820U0);
                    }
                } else if (C6456d.m24714D().m24748G()) {
                    this.f14673e = true;
                    UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[do_Upload] The message '" + strM16820U0 + "' is valid and Start upload");
                    C6202j.m23722a(messageObjM15179r.m14777o());
                    uploadMediaHelperPeek.m16819T1();
                    Log.d("UploadMediaHelperQueue", "[do_Upload] uploadMediaHelper startUpload");
                    m16927S(CallbackType.BEGIN_UPLOAD, strM16820U0, uploadMediaHelperPeek.m16830Z0());
                } else {
                    UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[do_Upload] The network is disconnected.");
                }
            }
            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[do_Upload] out.");
            Log.d("UploadMediaHelperQueue", "[do_Upload] end");
        }
    }

    /* renamed from: E */
    public final void m16920E(List<C2951b1> list) {
        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[generateUnUploadedMedia] in");
        for (C2951b1 c2951b1 : list) {
            try {
                MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(c2951b1.m14931d());
                String strM14930c = c2951b1.m14930c();
                boolean zM16924K = m16924K(c2951b1.m14929b());
                if (zM16924K || messageObjM15179r == null || strM14930c == null || strM14930c.isEmpty()) {
                    UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[generateUnUploadedMedia] Don't add '" + c2951b1.m14931d() + "' in upload queue. bExpired = " + zM16924K);
                    if (messageObjM15179r == null) {
                        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[generateUnUploadedMedia] The '" + c2951b1.m14931d() + "' can't be found in MessageObj DB.");
                        C2950b0.m14926y().m14882h(c2951b1.m14931d());
                    } else if (messageObjM15179r.m14744F().equals("4")) {
                        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[generateUnUploadedMedia] The '" + c2951b1.m14931d() + "' upload status is already UPLOAD_STATUS_FAILED.");
                    } else {
                        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[generateUnUploadedMedia] Update '" + c2951b1.m14931d() + "' upload status to STATUS_UPLOAD_FAIL");
                        m16893G(messageObjM15179r);
                    }
                } else if (messageObjM15179r.m14744F().equals("4")) {
                    UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[generateUnUploadedMedia] The '" + c2951b1.m14931d() + "' upload status is UPLOAD_STATUS_FAILED. Don't add in queue.");
                } else {
                    UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[generateUnUploadedMedia] Add '" + c2951b1.m14931d() + "' to Upload Media Queue");
                    UploadMediaHelper uploadMediaHelper = new UploadMediaHelper();
                    if (uploadMediaHelper.m16817S1(strM14930c)) {
                        m16946y(uploadMediaHelper, messageObjM15179r);
                    } else {
                        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[generateUnUploadedMedia] '" + c2951b1.m14931d() + "' set context failed.");
                        m16893G(messageObjM15179r);
                    }
                }
            } catch (Exception e9) {
                UploadUtils.m16964k("UploadMultipleChatMediaHelperQueue", "[generateUnUploadedMedia]", e9);
            }
        }
    }

    /* renamed from: H */
    public void m16921H(int i9) {
        synchronized (this.f14678j) {
            C3198b c3198b = this.f14679k;
            if (c3198b != null) {
                c3198b.m17009o2(i9);
            }
        }
    }

    /* renamed from: I */
    public void m16922I(int i9, int i10, String str, int i11, String str2) {
        synchronized (this.f14678j) {
            C3198b c3198b = this.f14679k;
            if (c3198b != null) {
                c3198b.m17010p2(i9, i10, str, i11, str2);
            }
        }
    }

    /* renamed from: J */
    public void m16923J(int i9, String[] strArr, String[] strArr2, String str) {
        synchronized (this.f14678j) {
            C3198b c3198b = this.f14679k;
            if (c3198b != null) {
                c3198b.m17011q2(i9, strArr, strArr2, str);
            }
        }
    }

    /* renamed from: K */
    public final boolean m16924K(long j9) {
        return (FriendsClient.m15646K() - j9) / 1000 >= 3600;
    }

    /* renamed from: Q */
    public final void m16925Q(CallbackType callbackType, C2973l0 c2973l0, String str) {
        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[notifyListener] In : CallbackType = " + callbackType);
        this.f14670b.m24540f(new C3190c(callbackType, str, c2973l0));
    }

    /* renamed from: R */
    public final void m16926R(CallbackType callbackType, String str) {
        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[notifyListener] In : CallbackType = " + callbackType);
        this.f14670b.m24540f(new C3192e(callbackType, str));
    }

    /* renamed from: S */
    public final void m16927S(CallbackType callbackType, String str, int i9) {
        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[notifyListener] In : CallbackType = " + callbackType);
        this.f14670b.m24540f(new C3191d(callbackType, str, i9));
    }

    /* renamed from: T */
    public final void m16928T(UploadMediaHelper uploadMediaHelper) throws JSONException, NumberFormatException {
        CallbackType callbackType;
        UploadUtils.UploadResultType uploadResultTypeM16828Y0;
        CallbackType callbackType2;
        String str;
        String str2;
        String strValueOf;
        try {
            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleMessage] in");
            String strM16820U0 = uploadMediaHelper.m16820U0();
            MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(strM16820U0);
            if (messageObjM15179r == null) {
                UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleMessage] '" + strM16820U0 + "' can't get messageObj from Message DB.");
                return;
            }
            C2973l0 c2973l0M16826X0 = uploadMediaHelper.m16826X0();
            boolean z8 = c2973l0M16826X0 != null;
            C2973l0 c2973l0M16851j1 = uploadMediaHelper.m16851j1();
            boolean z9 = c2973l0M16851j1 != null;
            C2973l0 c2973l0M16846h1 = uploadMediaHelper.m16846h1();
            boolean z10 = c2973l0M16846h1 != null;
            C2973l0 c2973l0M16808N0 = uploadMediaHelper.m16808N0();
            boolean z11 = c2973l0M16808N0 != null;
            boolean z12 = (z8 || z9 || !z10) ? false : true;
            boolean z13 = (z8 || !z9 || z10) ? false : true;
            boolean z14 = messageObjM15179r.m14778p() == MessageObj.MessageType.Photo;
            boolean z15 = messageObjM15179r.m14778p() == MessageObj.MessageType.ShareLocation;
            boolean z16 = messageObjM15179r.m14778p() == MessageObj.MessageType.File;
            if (z12) {
                CallbackType callbackType3 = CallbackType.VIDEO_COMPLETE;
                uploadResultTypeM16828Y0 = uploadMediaHelper.m16848i1();
                callbackType2 = callbackType3;
                c2973l0M16808N0 = c2973l0M16846h1;
            } else if (z13) {
                CallbackType callbackType4 = CallbackType.VOICE_COMPLETE;
                uploadResultTypeM16828Y0 = uploadMediaHelper.m16853k1();
                callbackType2 = callbackType4;
                c2973l0M16808N0 = c2973l0M16851j1;
            } else {
                if (z16) {
                    callbackType = CallbackType.FILE_COMPLETE;
                    uploadResultTypeM16828Y0 = uploadMediaHelper.m16810O0();
                } else {
                    callbackType = CallbackType.SMALL_COMPLETE;
                    uploadResultTypeM16828Y0 = uploadMediaHelper.m16828Y0();
                    c2973l0M16808N0 = c2973l0M16826X0;
                }
                callbackType2 = callbackType;
            }
            UploadUtils.UploadResultType uploadResultType = uploadResultTypeM16828Y0;
            if (!uploadResultTypeM16828Y0.equals(UploadUtils.UploadResultType.STEP_3_SUCCESS)) {
                UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleMessage] The message '" + strM16820U0 + "'object is not STEP_3_SUCCESS. Don't send message.");
                return;
            }
            if (!z9 && !z8 && !z10 && !z11) {
                UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleMessage] The message '" + strM16820U0 + "' is not voice and photo and video and file.");
                return;
            }
            if (c2973l0M16808N0 == null) {
                UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleMessage] The message '" + strM16820U0 + "' doesn't have mediaObj.");
                return;
            }
            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleMessage] Success");
            String strValueOf2 = String.valueOf(c2973l0M16808N0.m15144p());
            ImageItem imageItemM16842f1 = uploadMediaHelper.m16842f1();
            String strM14575S = null;
            String strM16144q = imageItemM16842f1 != null ? imageItemM16842f1.m16144q() : null;
            Uri uriM16510Z1 = imageItemM16842f1 != null ? CLUtility.m16510Z1(imageItemM16842f1.m16145r()) : null;
            String strValueOf3 = imageItemM16842f1 != null ? String.valueOf(imageItemM16842f1.m16142o()) : null;
            if (z15) {
                UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleMessage] The message '" + strM16820U0 + "' is Snapshot only.");
                try {
                    JSONObject jSONObject = new JSONObject(messageObjM15179r.m14779q());
                    String string = jSONObject.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                    String string2 = jSONObject.getString("address");
                    double d9 = Double.parseDouble(jSONObject.getString("latitude"));
                    double d10 = Double.parseDouble(jSONObject.getString("longitude"));
                    String str3 = c2973l0M16808N0.m15149u().f13200d;
                    UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleMessage] snapshotUrl " + str3);
                    strM14575S = C2925v.m14556H(string, string2, d9, d10, str3, 0, 0, this.f14677i, strValueOf2);
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
            } else if (z14) {
                UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleMessage] The message '" + strM16820U0 + "' is photo only.");
                strM14575S = C2925v.m14570O(strM16144q, null, c2973l0M16808N0.m15148t().f13200d, strValueOf3, strValueOf2, uploadMediaHelper.m16834b1(), uploadMediaHelper.m16824W0(), c2973l0M16808N0.m15151w(), c2973l0M16808N0.m15141m());
            } else if (z13) {
                UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleMessage] The message '" + strM16820U0 + "' is not voice only.");
                strM14575S = C2925v.m14580X(messageObjM15179r.m14747I("audioPath"), strValueOf2, messageObjM15179r.m14747I("duration"));
            } else {
                if (z12) {
                    UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleMessage] The message '" + strM16820U0 + "' is not video only.");
                    VideoItem videoItemM16844g1 = uploadMediaHelper.m16844g1();
                    strM14575S = C2925v.m14579W(videoItemM16844g1 != null ? videoItemM16844g1.m16218g() : "0", videoItemM16844g1 != null ? CLUtility.m16510Z1(videoItemM16844g1.m16219h()) : null, strValueOf2, videoItemM16844g1 != null ? String.valueOf(videoItemM16844g1.m16213b()) : "0", videoItemM16844g1 != null ? videoItemM16844g1.m16216e() : "", videoItemM16844g1 != null ? String.valueOf(videoItemM16844g1.m16217f()) : "", videoItemM16844g1 != null ? String.valueOf(videoItemM16844g1.m16215d()) : "false", videoItemM16844g1 != null ? String.valueOf(videoItemM16844g1.m16220i()) : "0", videoItemM16844g1 != null ? String.valueOf(videoItemM16844g1.m16214c()) : "0");
                } else if (z16) {
                    UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleMessage] The message '" + strM16820U0 + "' is file only.");
                    FileItem fileItemM16840e1 = uploadMediaHelper.m16840e1();
                    strM14575S = C2925v.m14572P(fileItemM16840e1, c2973l0M16808N0, uploadMediaHelper.m16824W0());
                    if (CLUtility.m16613z1(fileItemM16840e1.m16115b(), CLUtility.m16510Z1(fileItemM16840e1.m16117d())) && fileItemM16840e1.m16118f()) {
                        C6369f.m24463e(new File(fileItemM16840e1.m16115b()));
                    }
                } else {
                    UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleMessage] The message '" + strM16820U0 + "' is not photo and voice only.");
                    if (c2973l0M16851j1 != null) {
                        String strM14747I = messageObjM15179r.m14747I("noteMediaDescription");
                        List<C3061a> listM15224m = C2950b0.m14920s().m15224m(c2973l0M16808N0.m15144p());
                        str = "Audio";
                        strValueOf = listM15224m != null ? String.valueOf(listM15224m.size()) : "1";
                        str2 = strM14747I;
                    } else {
                        str = "";
                        str2 = str;
                        strValueOf = "1";
                    }
                    strM14575S = C2925v.m14575S(strValueOf2, messageObjM15179r.m14747I(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION), str, str2, strValueOf, strM16144q, uriM16510Z1, c2973l0M16808N0.m15148t().f13200d, strValueOf3, c2973l0M16808N0.m15151w(), c2973l0M16808N0.m15141m());
                }
            }
            ArrayList arrayList = new ArrayList();
            messageObjM15179r.m14757S(strM14575S);
            arrayList.add("MessageContent");
            messageObjM15179r.m14765a0("2");
            arrayList.add("UploadStatus");
            C2950b0.m14916o().m15160E(messageObjM15179r.m14777o(), messageObjM15179r, arrayList);
            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleMessage] The message '" + strM16820U0 + "' is saved.");
            m16935a0(messageObjM15179r);
            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleMessage] The message '" + strM16820U0 + "' is sent.");
            m16938d0(strM16820U0, uploadResultType, uploadMediaHelper.m16838d1(), false);
            m16925Q(callbackType2, c2973l0M16808N0, strM16820U0);
            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[onHandleMessage] The message '" + strM16820U0 + "' is notified.");
        } catch (Exception e10) {
            UploadUtils.m16964k("UploadMultipleChatMediaHelperQueue", "[onHandleMessage]", e10);
        }
    }

    /* renamed from: U */
    public final void m16929U() {
        Log.d("UploadMediaHelperQueue", "[onNotifyQueue] wait for mQueueSync");
        synchronized (this.f14672d) {
            Log.d("UploadMediaHelperQueue", "[onNotifyQueue] get mQueueSync");
            this.f14672d.notifyAll();
            Log.d("UploadMediaHelperQueue", "[onNotifyQueue] end and notify mQueueSync");
        }
    }

    /* renamed from: V */
    public final void m16930V() {
        if (this.f14669a.isEmpty() && this.f14676h.isEmpty()) {
            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[releaseConnection] Release XMPP connection reference.");
            C6456d.m24714D().m24751K("UploadMultipleChatMediaHelperQueue");
        }
    }

    /* renamed from: W */
    public void m16931W(InterfaceC3194g interfaceC3194g) {
        this.f14670b.m24541g(interfaceC3194g);
    }

    /* renamed from: X */
    public final void m16932X(UploadMediaHelper uploadMediaHelper) {
        UploadMediaHelper.InterfaceC3183t interfaceC3183tM16818T0 = uploadMediaHelper.m16818T0();
        if (interfaceC3183tM16818T0 instanceof C6199g.a) {
            ((C6199g.a) interfaceC3183tM16818T0).onCancel();
        }
        try {
            try {
                this.f14669a.remove(uploadMediaHelper);
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        } finally {
            m16930V();
        }
    }

    /* renamed from: Y */
    public final void m16933Y(boolean z8) {
        Log.d("UploadMediaHelperQueue", "removeUploadHelper:" + z8);
        try {
            try {
                UploadMediaHelper uploadMediaHelperPoll = this.f14669a.poll();
                if (uploadMediaHelperPoll != null) {
                    if (uploadMediaHelperPoll instanceof C3198b) {
                        m16936b0(null);
                    }
                    UploadMediaHelper.InterfaceC3183t interfaceC3183tM16818T0 = uploadMediaHelperPoll.m16818T0();
                    MessageObj messageObjM16822V0 = uploadMediaHelperPoll.m16822V0();
                    if (interfaceC3183tM16818T0 instanceof C6199g.a) {
                        if (!C2925v.m14604k0(messageObjM16822V0) && z8) {
                            ((C6199g.a) interfaceC3183tM16818T0).onResume();
                        } else {
                            ((C6199g.a) interfaceC3183tM16818T0).onCancel();
                        }
                    }
                }
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        } finally {
            m16930V();
        }
    }

    /* renamed from: Z */
    public final void m16934Z(MessageObj messageObj, C2973l0 c2973l0) {
        if (C2925v.m14604k0(messageObj)) {
            String strM14777o = messageObj.m14777o();
            messageObj.m14758T(C2925v.m14582Z(messageObj, c2973l0));
            C2950b0.m14916o().m15159D(strM14777o, messageObj, "MessageId");
        }
    }

    /* renamed from: a0 */
    public final void m16935a0(MessageObj messageObj) {
        if (!C2925v.m14604k0(messageObj)) {
            this.f14676h.add(messageObj.m14777o());
        } else {
            messageObj.m14762X("10");
            C2950b0.m14916o().m15159D(messageObj.m14777o(), messageObj, "Status");
        }
    }

    /* renamed from: b0 */
    public void m16936b0(C3198b c3198b) {
        synchronized (this.f14678j) {
            this.f14679k = c3198b;
        }
    }

    /* renamed from: c0 */
    public void m16937c0() {
        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[start] in.");
        if (this.f14671c) {
            return;
        }
        this.f14671c = true;
        new Thread(new Runnable() { // from class: k4.g1
            @Override // java.lang.Runnable
            public final void run() {
                this.f17663b.m16897O();
            }
        }).start();
        new Thread(new Runnable() { // from class: k4.h1
            @Override // java.lang.Runnable
            public final void run() {
                this.f17671b.m16898P();
            }
        }).start();
        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[start] out.");
    }

    /* renamed from: d0 */
    public final void m16938d0(String str, UploadUtils.UploadResultType uploadResultType, String str2, boolean z8) {
        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[updateUploadMediaObj] In : resultType = " + uploadResultType);
        if (str == null) {
            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[updateUploadMediaObj] Invalid message id");
            return;
        }
        if (uploadResultType.equals(UploadUtils.UploadResultType.STEP_3_SUCCESS) || z8) {
            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[updateUploadMediaObj] Delete '" + str + "' from UploadMedia DB");
            C2950b0.m14926y().m14882h(str);
            return;
        }
        C2951b1 c2951b1M14883i = C2950b0.m14926y().m14883i(str);
        if (c2951b1M14883i == null) {
            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[updateUploadMediaObj] '" + str + "' is not found.");
            return;
        }
        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[updateUploadMediaObj] Update '" + str + "' to UploadMedia DB");
        c2951b1M14883i.m14934g(str2);
        C2950b0.m14926y().m14887m(str, c2951b1M14883i, "UploadContext");
    }

    /* renamed from: f */
    public void m16939f(final List<String> list) {
        new Thread(new Runnable() { // from class: k4.j1
            @Override // java.lang.Runnable
            public final void run() {
                this.f17675b.m16894L(list);
            }
        }).start();
    }

    /* renamed from: s */
    public final void m16940s() {
        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[acquireConnection] Acquire connection ref.");
        C6456d.m24714D().m24743A("UploadMultipleChatMediaHelperQueue");
    }

    /* renamed from: t */
    public void m16941t(boolean z8, String str, FileItem fileItem, MessageObj messageObj, String str2, boolean z9) {
        m16944w(z8, null, null, null, null, null, null, str, fileItem, messageObj, str2, null, z9);
    }

    /* renamed from: u */
    public void m16942u(InterfaceC3194g interfaceC3194g) {
        this.f14670b.m24539c(interfaceC3194g);
    }

    /* renamed from: v */
    public void m16943v(boolean z8, String str, ImageItem imageItem, MessageObj messageObj, String str2, boolean z9) {
        m16944w(z8, str, imageItem, null, null, null, null, null, null, messageObj, str2, null, z9);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v0, types: [com.cyberlink.you.utility.UploadMediaHelper] */
    /* JADX WARN: Type inference failed for: r16v1 */
    /* JADX WARN: Type inference failed for: r16v2, types: [com.cyberlink.you.utility.UploadMediaHelper] */
    /* JADX WARN: Type inference failed for: r16v3, types: [com.cyberlink.you.utility.b] */
    /* renamed from: w */
    public void m16944w(boolean z8, String str, ImageItem imageItem, String str2, String str3, String str4, VideoItem videoItem, String str5, FileItem fileItem, MessageObj messageObj, String str6, String str7, boolean z9) {
        UploadMediaHelper uploadMediaHelper;
        ?? uploadMediaHelper2;
        if (messageObj == null || ((str != null && imageItem == null) || ((str2 != null && str3 == null) || ((str4 != null && videoItem == null) || (str5 != null && fileItem == null))))) {
            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[addMedia] Add media failed.");
            return;
        }
        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[addMedia] in : Message id('" + messageObj.m14777o() + "'");
        Log.d("UploadMediaHelperQueue", "[addMedia] wait for mQueueSync");
        synchronized (this.f14672d) {
            Log.d("UploadMediaHelperQueue", "[addMedia] get mQueueSync");
            C2951b1 c2951b1M14883i = C2950b0.m14926y().m14883i(messageObj.m14777o());
            if (c2951b1M14883i == null) {
                UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[addMedia] '" + messageObj.m14777o() + "' is new upload media");
                if (z9) {
                    Log.d("UploadMediaHelperQueue", "[addMedia] new UploadMeetingMediaHelper");
                    uploadMediaHelper2 = new C3198b(str, imageItem, str2, str3, str4, videoItem, str5, fileItem, messageObj, str6, this);
                    long jM17008n2 = uploadMediaHelper2.m17008n2();
                    try {
                        JSONObject jSONObject = new JSONObject(messageObj.m14779q());
                        jSONObject.put("mediaId", jM17008n2);
                        messageObj.m14757S(jSONObject.toString());
                        C2950b0.m14916o().m15158C(messageObj.m14777o(), messageObj);
                    } catch (JSONException unused) {
                    }
                } else if (str7 != null) {
                    this.f14677i = str7;
                    uploadMediaHelper2 = new UploadMediaHelper(str, messageObj, str6, str7);
                } else {
                    uploadMediaHelper2 = new UploadMediaHelper(str, imageItem, str2, str3, str4, videoItem, str5, fileItem, messageObj, str6);
                }
                C2950b0.m14926y().m14881g(new C2951b1(-1L, messageObj.m14777o(), 1, uploadMediaHelper2.m16838d1(), messageObj.m14788z().getTime()));
                uploadMediaHelper = uploadMediaHelper2;
            } else {
                UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[addMedia] '" + messageObj.m14777o() + "' is not new upload media");
                uploadMediaHelper = new UploadMediaHelper(c2951b1M14883i.m14930c());
                c2951b1M14883i.m14933f(messageObj.m14788z().getTime());
                C2950b0.m14926y().m14887m(messageObj.m14777o(), c2951b1M14883i, "SendingTime");
            }
            if (z8) {
                uploadMediaHelper.m16799H0();
            }
            m16946y(uploadMediaHelper, messageObj);
            this.f14672d.notifyAll();
            Log.d("UploadMediaHelperQueue", "[addMedia] end and notify mQueueSync");
        }
    }

    /* renamed from: x */
    public void m16945x(boolean z8, String str, ImageItem imageItem, MessageObj messageObj, String str2, String str3) {
        m16944w(z8, str, imageItem, null, null, null, null, null, null, messageObj, str2, str3, false);
    }

    /* renamed from: y */
    public final void m16946y(UploadMediaHelper uploadMediaHelper, MessageObj messageObj) {
        m16917B(uploadMediaHelper, messageObj);
        Iterator<UploadMediaHelper> it = this.f14669a.iterator();
        while (it.hasNext()) {
            UploadMediaHelper next = it.next();
            UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[addUploadHelper] Helper Id = " + next.m16820U0());
            if (next.m16820U0().equals(uploadMediaHelper.m16820U0())) {
                UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[addUploadHelper] mUploadMediaHelperQueue contains this uploadHelper. Don't need to add into queue.");
                return;
            }
        }
        UploadUtils.m16965l("UploadMultipleChatMediaHelperQueue", "[addUploadHelper] Add uploadMediaHelper and acquire connection ref.");
        m16940s();
        this.f14669a.add(uploadMediaHelper);
    }

    /* renamed from: z */
    public void m16947z(boolean z8, String str, VideoItem videoItem, MessageObj messageObj, String str2, boolean z9) {
        m16944w(z8, null, null, null, null, str, videoItem, null, null, messageObj, str2, null, z9);
    }

    public UploadMultipleChatMediaHelperQueue() {
        this.f14669a = new LinkedBlockingQueue<>();
        this.f14670b = new C6389z<>("ChatMediaQueue");
        this.f14671c = false;
        this.f14672d = new Object();
        this.f14673e = false;
        this.f14674f = false;
        this.f14675g = false;
        this.f14676h = new ArrayList<>();
        this.f14678j = null;
        this.f14679k = null;
        this.f14680l = new C3188a();
        this.f14681m = new C6456d.j() { // from class: k4.f1
            @Override // p218v2.C6456d.j
            public final void onConnected() {
                this.f17662b.m16896N();
            }
        };
        this.f14682n = new C3189b();
        this.f14678j = new Object();
        C6456d.m24714D().m24744B(this.f14681m);
        XMPPManager.m14184g0().m14206G(this.f14680l);
        m16937c0();
    }
}
