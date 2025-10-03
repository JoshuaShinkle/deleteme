package p199t1;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/* renamed from: t1.e */
/* loaded from: classes.dex */
public class C6280e {
    /* renamed from: a */
    public <Z> AbstractC6284i<ImageView, Z> m24054a(ImageView imageView, Class<Z> cls) {
        if (Bitmap.class.equals(cls)) {
            return new C6277b(imageView);
        }
        if (Drawable.class.isAssignableFrom(cls)) {
            return new C6278c(imageView);
        }
        throw new IllegalArgumentException("Unhandled class: " + cls + ", try .as*(Class).transcode(ResourceTranscoder)");
    }
}
