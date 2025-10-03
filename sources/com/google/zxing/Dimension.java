package com.google.zxing;

/* loaded from: classes2.dex */
public final class Dimension {
    private final int height;
    private final int width;

    public Dimension(int i9, int i10) {
        if (i9 < 0 || i10 < 0) {
            throw new IllegalArgumentException();
        }
        this.width = i9;
        this.height = i10;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Dimension)) {
            return false;
        }
        Dimension dimension = (Dimension) obj;
        return this.width == dimension.width && this.height == dimension.height;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return (this.width * 32713) + this.height;
    }

    public String toString() {
        return this.width + "x" + this.height;
    }
}
