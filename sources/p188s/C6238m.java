package p188s;

import android.os.Bundle;
import androidx.core.graphics.drawable.IconCompat;
import com.google.android.gms.plus.PlusShare;
import p188s.C6232g;

/* renamed from: s.m */
/* loaded from: classes.dex */
public class C6238m {

    /* renamed from: a */
    public static final Object f21041a = new Object();

    /* renamed from: b */
    public static final Object f21042b = new Object();

    /* renamed from: a */
    public static Bundle m23873a(C6232g.a aVar) {
        Bundle bundle = new Bundle();
        IconCompat iconCompatM23819d = aVar.m23819d();
        bundle.putInt("icon", iconCompatM23819d != null ? iconCompatM23819d.m1507b() : 0);
        bundle.putCharSequence(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, aVar.m23823h());
        bundle.putParcelable("actionIntent", aVar.m23816a());
        Bundle bundle2 = aVar.m23818c() != null ? new Bundle(aVar.m23818c()) : new Bundle();
        bundle2.putBoolean("android.support.allowGeneratedReplies", aVar.m23817b());
        bundle.putBundle("extras", bundle2);
        bundle.putParcelableArray("remoteInputs", m23875c(aVar.m23820e()));
        bundle.putBoolean("showsUserInterface", aVar.m23822g());
        bundle.putInt("semanticAction", aVar.m23821f());
        return bundle;
    }

    /* renamed from: b */
    public static Bundle m23874b(C6240o c6240o) {
        new Bundle();
        throw null;
    }

    /* renamed from: c */
    public static Bundle[] m23875c(C6240o[] c6240oArr) {
        if (c6240oArr == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[c6240oArr.length];
        for (int i9 = 0; i9 < c6240oArr.length; i9++) {
            C6240o c6240o = c6240oArr[i9];
            bundleArr[i9] = m23874b(null);
        }
        return bundleArr;
    }
}
