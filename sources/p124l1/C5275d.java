package p124l1;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import java.util.List;
import p012b1.InterfaceC0595j;
import p243y0.C6592e;
import p243y0.InterfaceC6593f;

/* renamed from: l1.d */
/* loaded from: classes.dex */
public class C5275d implements InterfaceC6593f<Uri, Drawable> {

    /* renamed from: a */
    public final Context f17877a;

    public C5275d(Context context) {
        this.f17877a = context.getApplicationContext();
    }

    @Override // p243y0.InterfaceC6593f
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceC0595j<Drawable> mo3998b(Uri uri, int i9, int i10, C6592e c6592e) throws NumberFormatException {
        int iM20543f = m20543f(uri);
        String authority = uri.getAuthority();
        return C5274c.m20539f(C5272a.m20534b(this.f17877a, authority.equals(this.f17877a.getPackageName()) ? this.f17877a : m20541d(uri, authority), iM20543f));
    }

    /* renamed from: d */
    public final Context m20541d(Uri uri, String str) {
        try {
            return this.f17877a.createPackageContext(str, 0);
        } catch (PackageManager.NameNotFoundException e9) {
            throw new IllegalArgumentException("Failed to obtain context or unrecognized Uri format for: " + uri, e9);
        }
    }

    @Override // p243y0.InterfaceC6593f
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public boolean mo3997a(Uri uri, C6592e c6592e) {
        return uri.getScheme().equals("android.resource");
    }

    /* renamed from: f */
    public final int m20543f(Uri uri) throws NumberFormatException {
        Integer numValueOf;
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.size() == 2) {
            String authority = uri.getAuthority();
            numValueOf = Integer.valueOf(this.f17877a.getResources().getIdentifier(pathSegments.get(1), pathSegments.get(0), authority));
        } else if (pathSegments.size() == 1) {
            try {
                numValueOf = Integer.valueOf(pathSegments.get(0));
            } catch (NumberFormatException unused) {
            }
        } else {
            numValueOf = null;
        }
        if (numValueOf == null) {
            throw new IllegalArgumentException("Unrecognized Uri format: " + uri);
        }
        if (numValueOf.intValue() != 0) {
            return numValueOf.intValue();
        }
        throw new IllegalArgumentException("Failed to obtain resource id for: " + uri);
    }
}
