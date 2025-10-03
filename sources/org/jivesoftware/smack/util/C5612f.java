package org.jivesoftware.smack.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: org.jivesoftware.smack.util.f */
/* loaded from: classes.dex */
public class C5612f implements Appendable, CharSequence {

    /* renamed from: b */
    public final List<CharSequence> f19518b = new ArrayList(20);

    /* renamed from: c */
    public String f19519c;

    @Override // java.lang.Appendable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public C5612f append(char c9) {
        this.f19518b.add(Character.toString(c9));
        m22330e();
        return this;
    }

    @Override // java.lang.Appendable
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public C5612f append(CharSequence charSequence) {
        this.f19518b.add(charSequence);
        m22330e();
        return this;
    }

    @Override // java.lang.Appendable
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public C5612f append(CharSequence charSequence, int i9, int i10) {
        this.f19518b.add(charSequence.subSequence(i9, i10));
        m22330e();
        return this;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i9) {
        String str = this.f19519c;
        if (str != null) {
            return str.charAt(i9);
        }
        for (CharSequence charSequence : this.f19518b) {
            if (i9 < charSequence.length()) {
                return charSequence.charAt(i9);
            }
            i9 -= charSequence.length();
        }
        throw new IndexOutOfBoundsException();
    }

    /* renamed from: d */
    public C5612f m22329d(C5612f c5612f) {
        this.f19518b.addAll(c5612f.f19518b);
        m22330e();
        return this;
    }

    /* renamed from: e */
    public final void m22330e() {
        this.f19519c = null;
    }

    @Override // java.lang.CharSequence
    public int length() {
        String str = this.f19519c;
        if (str != null) {
            return str.length();
        }
        Iterator<CharSequence> it = this.f19518b.iterator();
        int length = 0;
        while (it.hasNext()) {
            length += it.next().length();
        }
        return length;
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i9, int i10) {
        return toString().subSequence(i9, i10);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        if (this.f19519c == null) {
            StringBuilder sb = new StringBuilder(length());
            Iterator<CharSequence> it = this.f19518b.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
            }
            this.f19519c = sb.toString();
        }
        return this.f19519c;
    }
}
