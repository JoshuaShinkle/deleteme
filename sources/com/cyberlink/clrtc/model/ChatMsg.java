package com.cyberlink.clrtc.model;

import android.text.TextUtils;
import p003a2.C0011a;

/* loaded from: classes.dex */
public class ChatMsg implements Comparable<ChatMsg> {

    /* renamed from: b */
    public C0011a f5382b;

    /* renamed from: c */
    public final int f5383c;

    /* renamed from: d */
    public final int f5384d;

    /* renamed from: e */
    public final long f5385e;

    /* renamed from: f */
    public Status f5386f;

    /* renamed from: g */
    public final boolean f5387g;

    /* renamed from: h */
    public final String f5388h;

    /* renamed from: i */
    public boolean f5389i;

    /* renamed from: j */
    public final int f5390j;

    /* renamed from: k */
    public boolean f5391k;

    public enum Status {
        UNKNOWN,
        NORMAL,
        SENDING,
        FAILED
    }

    public ChatMsg(C0011a c0011a, int i9, long j9, boolean z8, String str, int i10) {
        this(c0011a.f63b, i9, j9, z8, str, i10);
        this.f5382b = c0011a;
    }

    /* renamed from: a */
    public boolean m5010a() {
        return this.f5389i;
    }

    @Override // java.lang.Comparable
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(ChatMsg chatMsg) {
        int iCompare = Long.compare(this.f5385e, chatMsg.f5385e);
        if (iCompare != 0) {
            return iCompare;
        }
        int iCompareTo = this.f5382b.compareTo(chatMsg.f5382b);
        return iCompareTo != 0 ? iCompareTo : this.f5388h.compareToIgnoreCase(chatMsg.f5388h);
    }

    /* renamed from: c */
    public boolean m5012c() {
        return this.f5391k;
    }

    /* renamed from: d */
    public Status m5013d() {
        return this.f5386f;
    }

    /* renamed from: e */
    public C0011a m5014e() {
        return this.f5382b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ChatMsg)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ChatMsg chatMsg = (ChatMsg) obj;
        return this.f5385e == chatMsg.f5385e && this.f5383c == chatMsg.f5383c && TextUtils.equals(this.f5388h, chatMsg.f5388h);
    }

    /* renamed from: f */
    public void m5015f(boolean z8) {
        this.f5389i = z8;
    }

    /* renamed from: g */
    public void m5016g() {
        this.f5391k = true;
    }

    /* renamed from: h */
    public ChatMsg m5017h(Status status) {
        if (Status.UNKNOWN != status) {
            this.f5386f = status;
            return this;
        }
        throw new IllegalArgumentException("Cannot set status with: " + status);
    }

    public int hashCode() {
        int i9 = ((this.f5383c * 31) + this.f5384d) * 31;
        long j9 = this.f5385e;
        return ((i9 + ((int) (j9 ^ (j9 >>> 32)))) * 31) + this.f5388h.hashCode();
    }

    /* renamed from: i */
    public ChatMsg m5018i(C0011a c0011a) {
        if (C0011a.f62e == c0011a) {
            throw new IllegalArgumentException("Only set user if you know who he is.");
        }
        if (c0011a.f63b != this.f5383c) {
            throw new IllegalArgumentException("Only set user with identical user id.");
        }
        this.f5382b = c0011a;
        return this;
    }

    public ChatMsg(int i9, int i10, long j9, boolean z8, String str, int i11) {
        this.f5386f = Status.UNKNOWN;
        this.f5389i = true;
        this.f5391k = false;
        this.f5383c = i9;
        this.f5384d = i10;
        this.f5385e = j9;
        this.f5387g = z8;
        this.f5388h = str;
        this.f5390j = i11;
    }
}
