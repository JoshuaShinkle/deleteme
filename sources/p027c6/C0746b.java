package p027c6;

import java.util.NoSuchElementException;
import kotlin.collections.AbstractC5236s;

/* renamed from: c6.b */
/* loaded from: classes2.dex */
public final class C0746b extends AbstractC5236s {

    /* renamed from: b */
    public final int f3490b;

    /* renamed from: c */
    public final int f3491c;

    /* renamed from: d */
    public boolean f3492d;

    /* renamed from: e */
    public int f3493e;

    public C0746b(int i9, int i10, int i11) {
        this.f3490b = i11;
        this.f3491c = i10;
        boolean z8 = true;
        if (i11 <= 0 ? i9 < i10 : i9 > i10) {
            z8 = false;
        }
        this.f3492d = z8;
        this.f3493e = z8 ? i9 : i10;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f3492d;
    }

    @Override // kotlin.collections.AbstractC5236s
    public int nextInt() {
        int i9 = this.f3493e;
        if (i9 != this.f3491c) {
            this.f3493e = this.f3490b + i9;
        } else {
            if (!this.f3492d) {
                throw new NoSuchElementException();
            }
            this.f3492d = false;
        }
        return i9;
    }
}
