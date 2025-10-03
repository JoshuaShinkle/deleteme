package com.cyberlink.clrtc.model;

/* loaded from: classes.dex */
public class ActiveMedia {

    /* renamed from: c */
    public static final ActiveMedia f5373c = new ActiveMedia();

    /* renamed from: a */
    public final int f5374a;

    /* renamed from: b */
    public final Type f5375b;

    public enum Type {
        NO_DATA(0),
        MICROPHONE(1),
        WEBCAM(2),
        DESKTOP(3),
        ICON(4);

        private final int value;

        Type(int i9) {
            this.value = i9;
        }

        /* renamed from: a */
        public static Type m5009a(int i9) {
            for (Type type : values()) {
                if (type.value == i9) {
                    return type;
                }
            }
            return NO_DATA;
        }
    }

    public ActiveMedia() {
        this.f5374a = -1;
        this.f5375b = Type.NO_DATA;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ActiveMedia)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ActiveMedia activeMedia = (ActiveMedia) obj;
        return this.f5374a == activeMedia.f5374a && this.f5375b == activeMedia.f5375b;
    }

    public ActiveMedia(int i9, Type type) {
        if (i9 >= 0) {
            this.f5374a = i9;
            this.f5375b = type;
        } else {
            throw new IllegalArgumentException("Stream id cannot be negative: " + i9);
        }
    }
}
