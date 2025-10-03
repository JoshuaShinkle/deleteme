package com.google.android.exoplayer2.text.ttml;

/* loaded from: classes.dex */
final class TtmlRegion {

    /* renamed from: id */
    public final String f15321id;
    public final float line;
    public final int lineAnchor;
    public final int lineType;
    public final float position;
    public final float width;

    public TtmlRegion(String str) {
        this(str, Float.MIN_VALUE, Float.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE);
    }

    public TtmlRegion(String str, float f9, float f10, int i9, int i10, float f11) {
        this.f15321id = str;
        this.position = f9;
        this.line = f10;
        this.lineType = i9;
        this.lineAnchor = i10;
        this.width = f11;
    }
}
