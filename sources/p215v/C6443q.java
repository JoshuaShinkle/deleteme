package p215v;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import java.io.IOException;
import p001a0.C0004b;
import p206u.C6347a;

/* renamed from: v.q */
/* loaded from: classes.dex */
public class C6443q extends C6444r {
    @Override // p215v.C6444r
    /* renamed from: a */
    public Typeface mo24604a(Context context, C6347a.b bVar, Resources resources, int i9) throws IOException {
        C6347a.c[] cVarArrM24353a = bVar.m24353a();
        int length = cVarArrM24353a.length;
        FontFamily.Builder builder = null;
        int i10 = 0;
        while (true) {
            int i11 = 1;
            if (i10 >= length) {
                break;
            }
            C6347a.c cVar = cVarArrM24353a[i10];
            try {
                Font.Builder weight = new Font.Builder(resources, cVar.m24355b()).setWeight(cVar.m24358e());
                if (!cVar.m24359f()) {
                    i11 = 0;
                }
                Font fontBuild = weight.setSlant(i11).setTtcIndex(cVar.m24356c()).setFontVariationSettings(cVar.m24357d()).build();
                if (builder == null) {
                    builder = new FontFamily.Builder(fontBuild);
                } else {
                    builder.addFont(fontBuild);
                }
            } catch (IOException unused) {
            }
            i10++;
        }
        if (builder == null) {
            return null;
        }
        return new Typeface.CustomFallbackBuilder(builder.build()).setStyle(new FontStyle((i9 & 1) != 0 ? 700 : 400, (i9 & 2) != 0 ? 1 : 0)).build();
    }

    @Override // p215v.C6444r
    /* renamed from: b */
    public Typeface mo24606b(Context context, CancellationSignal cancellationSignal, C0004b.f[] fVarArr, int i9) throws IOException {
        ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor;
        ContentResolver contentResolver = context.getContentResolver();
        int length = fVarArr.length;
        FontFamily.Builder builder = null;
        int i10 = 0;
        while (true) {
            int i11 = 1;
            if (i10 >= length) {
                if (builder == null) {
                    return null;
                }
                return new Typeface.CustomFallbackBuilder(builder.build()).setStyle(new FontStyle((i9 & 1) != 0 ? 700 : 400, (i9 & 2) != 0 ? 1 : 0)).build();
            }
            C0004b.f fVar = fVarArr[i10];
            try {
                parcelFileDescriptorOpenFileDescriptor = contentResolver.openFileDescriptor(fVar.m37c(), "r", cancellationSignal);
            } catch (IOException unused) {
                continue;
            }
            if (parcelFileDescriptorOpenFileDescriptor == null) {
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                }
                i10++;
            } else {
                try {
                    Font.Builder weight = new Font.Builder(parcelFileDescriptorOpenFileDescriptor).setWeight(fVar.m38d());
                    if (!fVar.m39e()) {
                        i11 = 0;
                    }
                    Font fontBuild = weight.setSlant(i11).setTtcIndex(fVar.m36b()).build();
                    if (builder == null) {
                        builder = new FontFamily.Builder(fontBuild);
                    } else {
                        builder.addFont(fontBuild);
                    }
                } catch (Throwable th) {
                    try {
                        parcelFileDescriptorOpenFileDescriptor.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
            parcelFileDescriptorOpenFileDescriptor.close();
            i10++;
        }
    }

    @Override // p215v.C6444r
    /* renamed from: c */
    public Typeface mo24607c(Context context, Resources resources, int i9, String str, int i10) {
        try {
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(new Font.Builder(resources, i9).build()).build()).setStyle(new FontStyle((i10 & 1) != 0 ? 700 : 400, (i10 & 2) != 0 ? 1 : 0)).build();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // p215v.C6444r
    /* renamed from: e */
    public C0004b.f mo24630e(C0004b.f[] fVarArr, int i9) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }
}
