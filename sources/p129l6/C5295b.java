package p129l6;

import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.firebase.iid.ServiceStarter;
import java.util.Arrays;
import java.util.logging.Logger;
import p007a6.C0042f;
import p007a6.C0045i;

/* renamed from: l6.b */
/* loaded from: classes.dex */
public final class C5295b {
    /* renamed from: b */
    public static final String m20655b(long j9) {
        String str;
        if (j9 <= -999500000) {
            str = ((j9 - 500000000) / 1000000000) + " s ";
        } else if (j9 <= -999500) {
            str = ((j9 - 500000) / 1000000) + " ms";
        } else if (j9 <= 0) {
            str = ((j9 - ServiceStarter.ERROR_UNKNOWN) / CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT) + " µs";
        } else if (j9 < 999500) {
            str = ((j9 + ServiceStarter.ERROR_UNKNOWN) / CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT) + " µs";
        } else if (j9 < 999500000) {
            str = ((j9 + 500000) / 1000000) + " ms";
        } else {
            str = ((j9 + 500000000) / 1000000000) + " s ";
        }
        C0045i c0045i = C0045i.f138a;
        String str2 = String.format("%6s", Arrays.copyOf(new Object[]{str}, 1));
        C0042f.m157d(str2, "format(format, *args)");
        return str2;
    }

    /* renamed from: c */
    public static final void m20656c(AbstractC5294a abstractC5294a, C5297d c5297d, String str) {
        Logger loggerM20683a = C5298e.f17982h.m20683a();
        StringBuilder sb = new StringBuilder();
        sb.append(c5297d.m20663f());
        sb.append(' ');
        C0045i c0045i = C0045i.f138a;
        String str2 = String.format("%-22s", Arrays.copyOf(new Object[]{str}, 1));
        C0042f.m157d(str2, "format(format, *args)");
        sb.append(str2);
        sb.append(": ");
        sb.append(abstractC5294a.m20648b());
        loggerM20683a.fine(sb.toString());
    }
}
