package com.rockerhieu.emojicon.emoji;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class Emojicon implements Serializable {
    private static final long serialVersionUID = 1;
    private String emoji;
    private int icon;
    private char value;

    private Emojicon() {
    }

    public static Emojicon fromChar(char c9) {
        Emojicon emojicon = new Emojicon();
        emojicon.emoji = Character.toString(c9);
        return emojicon;
    }

    public static Emojicon fromChars(String str) {
        Emojicon emojicon = new Emojicon();
        emojicon.emoji = str;
        return emojicon;
    }

    public static Emojicon fromCodePoint(int i9) {
        Emojicon emojicon = new Emojicon();
        emojicon.emoji = newString(i9);
        return emojicon;
    }

    public static Emojicon fromResource(int i9, int i10) {
        Emojicon emojicon = new Emojicon();
        emojicon.icon = i9;
        emojicon.value = (char) i10;
        return emojicon;
    }

    public static final String newString(int i9) {
        return Character.charCount(i9) == 1 ? String.valueOf(i9) : new String(Character.toChars(i9));
    }

    public String getEmoji() {
        return this.emoji;
    }

    public int getIcon() {
        return this.icon;
    }

    public char getValue() {
        return this.value;
    }

    public Emojicon(String str) {
        this.emoji = str;
    }
}
