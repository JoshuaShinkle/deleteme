package com.cyberlink.you.bulletin;

import java.io.Serializable;

/* loaded from: classes.dex */
public class AudioItem implements Serializable {
    private static final long serialVersionUID = 1;
    private String audioPath;
    private String duration;

    public AudioItem(String str, String str2) {
        this.audioPath = str;
        this.duration = str2;
    }

    /* renamed from: a */
    public String m14002a() {
        return this.audioPath;
    }

    /* renamed from: b */
    public String m14003b() {
        return this.duration;
    }

    public String toString() {
        return (("{\"Audio Path\":\"" + m14002a() + "\",") + "\"Duration\":\"" + m14003b() + "\"") + "}";
    }
}
