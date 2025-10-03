package com.cyberlink.you.fingerpaint.kernel;

import java.util.ArrayList;

/* renamed from: com.cyberlink.you.fingerpaint.kernel.b */
/* loaded from: classes.dex */
public class C3035b extends AbstractC3034a {

    /* renamed from: b */
    public float[] f13561b;

    /* renamed from: c */
    public float[] f13562c;

    /* renamed from: d */
    public float[] f13563d;

    public C3035b(FingerPaintObject fingerPaintObject) {
        super(fingerPaintObject);
        this.f13561b = new float[2];
        this.f13562c = new float[2];
        this.f13563d = new float[2];
        for (int i9 = 0; i9 < 2; i9++) {
            this.f13561b[i9] = 0.0f;
            this.f13562c[i9] = 0.0f;
            this.f13563d[i9] = 0.0f;
        }
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.AbstractC3034a
    /* renamed from: a */
    public void mo15574a(ArrayList<FingerPaintObject> arrayList) {
        FingerPaintObject fingerPaintObject;
        if (arrayList == null || (fingerPaintObject = this.f13560a) == null) {
            return;
        }
        arrayList.add(fingerPaintObject);
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.AbstractC3034a
    /* renamed from: b */
    public float[] mo15575b() {
        return this.f13562c;
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.AbstractC3034a
    /* renamed from: c */
    public float[] mo15576c() {
        return this.f13563d;
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.AbstractC3034a
    /* renamed from: d */
    public float[] mo15577d() {
        return this.f13561b;
    }

    @Override // com.cyberlink.you.fingerpaint.kernel.AbstractC3034a
    /* renamed from: e */
    public void mo15578e(ArrayList<FingerPaintObject> arrayList) {
        FingerPaintObject fingerPaintObject;
        if (arrayList == null || (fingerPaintObject = this.f13560a) == null) {
            return;
        }
        arrayList.remove(fingerPaintObject);
    }
}
