package com.cyberlink.you.activity.webinar;

import com.perfectcorp.ycl.p040bc.model.Live;
import com.perfectcorp.ycl.p040bc.model.WatchHistoryList;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ExpandableData {

    /* renamed from: a */
    public int f11852a;

    /* renamed from: b */
    public DataType f11853b;

    /* renamed from: c */
    public String f11854c;

    /* renamed from: d */
    public boolean f11855d;

    /* renamed from: e */
    public ArrayList<WatchHistoryList.LiveHistoryInfo> f11856e;

    /* renamed from: f */
    public ArrayList<Live> f11857f;

    /* renamed from: g */
    public ArrayList<Boolean> f11858g;

    /* renamed from: h */
    public WatchHistoryList.LiveHistoryInfo f11859h;

    /* renamed from: i */
    public Live f11860i;

    /* renamed from: j */
    public boolean f11861j;

    public enum DataType {
        REGISTER,
        HISTORY
    }

    public ExpandableData(String str, DataType dataType) {
        this.f11852a = 0;
        this.f11853b = dataType;
        this.f11854c = str;
        this.f11855d = true;
        this.f11856e = new ArrayList<>();
        this.f11857f = new ArrayList<>();
        this.f11858g = new ArrayList<>();
    }

    /* renamed from: a */
    public DataType m13492a() {
        return this.f11853b;
    }

    /* renamed from: b */
    public String m13493b() {
        return this.f11854c;
    }

    /* renamed from: c */
    public ArrayList<Boolean> m13494c() {
        return this.f11858g;
    }

    /* renamed from: d */
    public ArrayList<WatchHistoryList.LiveHistoryInfo> m13495d() {
        return this.f11856e;
    }

    /* renamed from: e */
    public ArrayList<Live> m13496e() {
        return this.f11857f;
    }

    /* renamed from: f */
    public WatchHistoryList.LiveHistoryInfo m13497f() {
        return this.f11859h;
    }

    /* renamed from: g */
    public int m13498g() {
        return this.f11852a;
    }

    /* renamed from: h */
    public Live m13499h() {
        return this.f11860i;
    }

    /* renamed from: i */
    public boolean m13500i() {
        return this.f11855d;
    }

    /* renamed from: j */
    public boolean m13501j() {
        return this.f11861j;
    }

    /* renamed from: k */
    public void m13502k(boolean z8) {
        this.f11855d = z8;
    }

    /* renamed from: l */
    public void m13503l(boolean z8) {
        this.f11861j = z8;
    }

    public ExpandableData(WatchHistoryList.LiveHistoryInfo liveHistoryInfo) {
        this(liveHistoryInfo, false);
    }

    public ExpandableData(Live live) {
        this(live, false);
    }

    public ExpandableData(WatchHistoryList.LiveHistoryInfo liveHistoryInfo, boolean z8) {
        this.f11852a = 1;
        this.f11853b = DataType.HISTORY;
        this.f11859h = liveHistoryInfo;
        this.f11861j = z8;
    }

    public ExpandableData(Live live, boolean z8) {
        this.f11852a = 1;
        this.f11853b = DataType.REGISTER;
        this.f11860i = live;
        this.f11861j = z8;
    }
}
