package p143n1;

import android.util.Log;
import com.bumptech.glide.load.EncodeStrategy;
import java.io.File;
import java.io.IOException;
import p012b1.InterfaceC0595j;
import p226w1.C6508a;
import p243y0.C6592e;
import p243y0.InterfaceC6594g;

/* renamed from: n1.d */
/* loaded from: classes.dex */
public class C5351d implements InterfaceC6594g<C5350c> {
    @Override // p243y0.InterfaceC6594g
    /* renamed from: a */
    public EncodeStrategy mo19854a(C6592e c6592e) {
        return EncodeStrategy.SOURCE;
    }

    @Override // p243y0.InterfaceC6588a
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public boolean mo19086b(InterfaceC0595j<C5350c> interfaceC0595j, File file, C6592e c6592e) throws Throwable {
        try {
            C6508a.m24915d(interfaceC0595j.get().m21011c(), file);
            return true;
        } catch (IOException e9) {
            if (Log.isLoggable("GifEncoder", 5)) {
                Log.w("GifEncoder", "Failed to encode GIF drawable data", e9);
            }
            return false;
        }
    }
}
