package p181r1;

import com.bumptech.glide.load.ImageHeaderParser;
import java.util.ArrayList;
import java.util.List;

/* renamed from: r1.b */
/* loaded from: classes.dex */
public final class C6184b {

    /* renamed from: a */
    public final List<ImageHeaderParser> f20838a = new ArrayList();

    /* renamed from: a */
    public synchronized void m23652a(ImageHeaderParser imageHeaderParser) {
        this.f20838a.add(imageHeaderParser);
    }

    /* renamed from: b */
    public synchronized List<ImageHeaderParser> m23653b() {
        return this.f20838a;
    }
}
