package p125l2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* renamed from: l2.a */
/* loaded from: classes.dex */
public final class C5277a {

    /* renamed from: a */
    public final ByteBuffer f17878a;

    /* renamed from: b */
    public boolean f17879b;

    /* renamed from: c */
    public int f17880c;

    /* renamed from: d */
    public ByteOrder f17881d;

    public C5277a(ByteBuffer byteBuffer) {
        this.f17878a = byteBuffer;
        this.f17879b = byteBuffer.isDirect();
        this.f17880c = byteBuffer.remaining();
        this.f17881d = byteBuffer.order();
    }

    /* renamed from: a */
    public ByteBuffer m20546a() {
        ByteBuffer byteBufferOrder = (this.f17879b ? ByteBuffer.allocateDirect(this.f17880c) : ByteBuffer.allocate(this.f17880c)).order(this.f17881d);
        m20547b(byteBufferOrder);
        return byteBufferOrder;
    }

    /* renamed from: b */
    public final void m20547b(ByteBuffer byteBuffer) {
        int iPosition = this.f17878a.position();
        int iLimit = this.f17878a.limit();
        int iPosition2 = byteBuffer.position();
        try {
            this.f17878a.limit(this.f17880c + iPosition);
            byteBuffer.put(this.f17878a);
        } finally {
            byteBuffer.position(iPosition2);
            this.f17878a.limit(iLimit).position(iPosition);
        }
    }

    /* renamed from: c */
    public C5277a m20548c(int i9) {
        this.f17880c = i9;
        return this;
    }
}
