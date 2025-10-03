package p106j4;

import android.content.Context;
import android.net.Uri;
import com.cyberlink.mediacodec.Transcoder;
import com.cyberlink.you.Globals;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import java.io.FileNotFoundException;

/* renamed from: j4.a */
/* loaded from: classes.dex */
public class C5095a implements InterfaceC5096b {

    /* renamed from: a */
    public Transcoder f17543a;

    /* renamed from: b */
    public int f17544b;

    /* renamed from: c */
    public int f17545c;

    /* renamed from: j4.a$a */
    public class a implements Transcoder.InterfaceC1243a {

        /* renamed from: a */
        public final /* synthetic */ InterfaceC5101g f17546a;

        public a(InterfaceC5101g interfaceC5101g) {
            this.f17546a = interfaceC5101g;
        }

        @Override // com.cyberlink.mediacodec.Transcoder.InterfaceC1243a
        /* renamed from: a */
        public void mo5528a(int i9) {
            this.f17546a.mo16342a(i9);
        }

        @Override // com.cyberlink.mediacodec.Transcoder.InterfaceC1243a
        /* renamed from: b */
        public void mo5529b(Transcoder transcoder) {
            if (transcoder.m5518q()) {
                Transcoder.TRANSCODER_STATUS transcoder_statusM5517p = transcoder.m5517p();
                if (b.f17548a[transcoder_statusM5517p.ordinal()] != 1) {
                    this.f17546a.onError(transcoder_statusM5517p.toString());
                } else {
                    this.f17546a.mo16343b();
                }
            }
        }
    }

    /* renamed from: j4.a$b */
    public static /* synthetic */ class b {

        /* renamed from: a */
        public static final /* synthetic */ int[] f17548a;

        static {
            int[] iArr = new int[Transcoder.TRANSCODER_STATUS.values().length];
            f17548a = iArr;
            try {
                iArr[Transcoder.TRANSCODER_STATUS.STATUS_SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public C5095a(int i9, int i10) {
        this.f17544b = i9;
        this.f17545c = i10;
    }

    @Override // p106j4.InterfaceC5096b
    /* renamed from: a */
    public void mo19948a() {
    }

    @Override // p106j4.InterfaceC5096b
    /* renamed from: b */
    public void mo19949b(String str, Uri uri, Context context, String str2, InterfaceC5101g interfaceC5101g) throws FileNotFoundException {
        if (this.f17544b == 0) {
            this.f17544b = 640;
        }
        if (this.f17545c == 0) {
            this.f17545c = 480;
        }
        Transcoder transcoder = new Transcoder();
        this.f17543a = transcoder;
        transcoder.m5522u(str2);
        if (uri != null) {
            this.f17543a.m5524w(uri);
            try {
                this.f17543a.m5521t(Globals.m7372O().getContentResolver().openAssetFileDescriptor(uri, "r"));
            } catch (FileNotFoundException e9) {
                ULogUtility.m16670f("CESARTransCodeAdapter", "startTransCode FileNotFoundException:" + e9.getMessage());
            }
        }
        if (CLUtility.m16613z1(str, null)) {
            this.f17543a.m5523v(str);
        }
        this.f17543a.m5520s(context);
        this.f17543a.m5525x(this.f17544b, this.f17545c);
        this.f17543a.m5526y(m19950c(interfaceC5101g));
        this.f17543a.start();
    }

    /* renamed from: c */
    public final Transcoder.InterfaceC1243a m19950c(InterfaceC5101g interfaceC5101g) {
        return new a(interfaceC5101g);
    }

    @Override // p106j4.InterfaceC5096b
    public void cancel() {
        this.f17543a.interrupt();
    }

    @Override // p106j4.InterfaceC5096b
    public void pause() {
    }

    @Override // p106j4.InterfaceC5096b
    public void release() {
        this.f17543a = null;
    }
}
