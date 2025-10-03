package org.jsoup.parser;

import java.util.ArrayList;
import p090h8.C5031b;

/* loaded from: classes.dex */
public class ParseErrorList extends ArrayList<C5031b> {
    private final int maxSize;

    public ParseErrorList(int i9, int i10) {
        super(i9);
        this.maxSize = i10;
    }

    /* renamed from: b */
    public static ParseErrorList m22987b() {
        return new ParseErrorList(0, 0);
    }

    /* renamed from: c */
    public static ParseErrorList m22988c(int i9) {
        return new ParseErrorList(16, i9);
    }

    /* renamed from: a */
    public boolean m22989a() {
        return size() < this.maxSize;
    }
}
