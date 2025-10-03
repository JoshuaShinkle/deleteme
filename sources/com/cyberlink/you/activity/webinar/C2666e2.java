package com.cyberlink.you.activity.webinar;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.activity.webinar.ExpandableData;
import com.perfectcorp.ycl.p040bc.model.Live;
import com.perfectcorp.ycl.p040bc.model.WatchHistoryList;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.cyberlink.you.activity.webinar.e2 */
/* loaded from: classes.dex */
public class C2666e2 extends RecyclerView.AbstractC0446g implements InterfaceC2674g2, InterfaceC2670f2 {

    /* renamed from: a */
    public final MyWebinarActivity f12120a;

    /* renamed from: b */
    public ArrayList<ExpandableData> f12121b;

    /* renamed from: c */
    public final b f12122c;

    /* renamed from: d */
    public boolean f12123d = false;

    /* renamed from: e */
    public int f12124e;

    /* renamed from: com.cyberlink.you.activity.webinar.e2$a */
    public static /* synthetic */ class a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f12125a;

        static {
            int[] iArr = new int[ExpandableData.DataType.values().length];
            f12125a = iArr;
            try {
                iArr[ExpandableData.DataType.REGISTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f12125a[ExpandableData.DataType.HISTORY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.e2$b */
    public interface b {
        /* renamed from: a */
        void mo13947a(int i9);
    }

    public C2666e2(ArrayList<ExpandableData> arrayList, b bVar, MyWebinarActivity myWebinarActivity) {
        this.f12121b = arrayList;
        this.f12122c = bVar;
        this.f12120a = myWebinarActivity;
    }

    @Override // com.cyberlink.you.activity.webinar.InterfaceC2670f2
    /* renamed from: a */
    public void mo13934a(int i9, MyWebinarActivity myWebinarActivity) {
        ExpandableData expandableData = this.f12121b.get(i9);
        if (!m13944k()) {
            int i10 = a.f12125a[expandableData.m13492a().ordinal()];
            if (i10 == 1) {
                myWebinarActivity.m13871T(this.f12121b.get(i9).m13499h().liveId);
                return;
            } else {
                if (i10 != 2) {
                    return;
                }
                myWebinarActivity.m13871T(this.f12121b.get(i9).m13497f().liveId);
                return;
            }
        }
        if (expandableData.m13492a() == ExpandableData.DataType.HISTORY) {
            if (expandableData.m13501j()) {
                expandableData.m13503l(false);
                this.f12124e--;
            } else {
                expandableData.m13503l(true);
                this.f12124e++;
            }
            notifyItemChanged(i9);
            myWebinarActivity.m13877n0(this.f12124e);
        }
    }

    @Override // com.cyberlink.you.activity.webinar.InterfaceC2674g2
    /* renamed from: b */
    public boolean mo13935b(int i9) {
        return m13945l(i9, true);
    }

    /* renamed from: c */
    public void m13936c(int i9, WatchHistoryList.LiveHistoryInfo liveHistoryInfo) {
        ExpandableData.DataType dataType = ExpandableData.DataType.HISTORY;
        int iM13939f = m13939f(dataType);
        if (iM13939f == -1) {
            iM13939f = this.f12121b.size();
            this.f12121b.add(new ExpandableData(this.f12120a.getString(R.string.clmw_recorded_webinars_title), dataType));
        }
        ExpandableData expandableData = this.f12121b.get(iM13939f);
        expandableData.m13495d().add(i9, liveHistoryInfo);
        if (expandableData.m13500i()) {
            int i10 = iM13939f + i9 + 1;
            this.f12121b.add(i10, new ExpandableData(liveHistoryInfo));
            notifyItemRangeInserted(i10, 1);
        }
    }

    /* renamed from: d */
    public void m13937d(int i9, Live live) {
        ExpandableData.DataType dataType = ExpandableData.DataType.REGISTER;
        int iM13939f = m13939f(dataType);
        if (iM13939f == -1) {
            this.f12121b.add(0, new ExpandableData(this.f12120a.getString(R.string.clmw_upcoming_webinars_title), dataType));
            iM13939f = 0;
        }
        ExpandableData expandableData = this.f12121b.get(iM13939f);
        expandableData.m13496e().add(i9, live);
        if (expandableData.m13500i()) {
            int i10 = iM13939f + i9 + 1;
            this.f12121b.add(i10, new ExpandableData(live));
            notifyItemRangeInserted(i10, 1);
        }
    }

    /* renamed from: e */
    public void m13938e(ExpandableData expandableData, int i9) {
        int i10;
        if (this.f12123d && expandableData.m13492a() == ExpandableData.DataType.HISTORY) {
            ArrayList<Boolean> arrayListM13494c = expandableData.m13494c();
            arrayListM13494c.clear();
            i10 = 0;
            while (true) {
                int i11 = i9 + 1;
                if (this.f12121b.size() <= i11 || this.f12121b.get(i11).m13498g() != 1) {
                    break;
                }
                arrayListM13494c.add(Boolean.valueOf(this.f12121b.remove(i11).m13501j()));
                i10++;
            }
        } else {
            i10 = 0;
            while (true) {
                int i12 = i9 + 1;
                if (this.f12121b.size() <= i12 || this.f12121b.get(i12).m13498g() != 1) {
                    break;
                }
                this.f12121b.remove(i12);
                i10++;
            }
        }
        expandableData.m13502k(false);
        notifyItemChanged(i9);
        notifyItemRangeRemoved(i9 + 1, i10);
    }

    /* renamed from: f */
    public final int m13939f(ExpandableData.DataType dataType) {
        int i9 = a.f12125a[dataType.ordinal()];
        if (i9 == 1) {
            if (this.f12121b.size() > 0 && this.f12121b.get(0).m13492a() == dataType) {
                return 0;
            }
        } else if (i9 == 2) {
            for (int i10 = 0; i10 < this.f12121b.size(); i10++) {
                if (this.f12121b.get(i10).m13492a() == dataType) {
                    return i10;
                }
            }
        }
        return -1;
    }

    /* renamed from: g */
    public void m13940g(boolean z8, ArrayList<WatchHistoryList.LiveHistoryInfo> arrayList) {
        this.f12123d = false;
        if (z8) {
            int iM13939f = m13939f(ExpandableData.DataType.HISTORY);
            ArrayList<WatchHistoryList.LiveHistoryInfo> arrayListM13495d = this.f12121b.get(iM13939f).m13495d();
            arrayListM13495d.removeAll(arrayList);
            if (m13945l(iM13939f, false)) {
                m13945l(iM13939f, false);
            }
            if (arrayListM13495d.size() == 0) {
                this.f12121b.remove(iM13939f);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
    public int getItemCount() {
        return this.f12121b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
    public int getItemViewType(int i9) {
        return this.f12121b.get(i9).m13498g();
    }

    /* renamed from: h */
    public void m13941h(ExpandableData expandableData, int i9, boolean z8) {
        int size;
        int i10 = a.f12125a[expandableData.m13492a().ordinal()];
        int i11 = 0;
        if (i10 == 1) {
            ArrayList<Live> arrayListM13496e = expandableData.m13496e();
            size = arrayListM13496e.size();
            while (i11 < size) {
                this.f12121b.add(i9 + i11 + 1, new ExpandableData(arrayListM13496e.get(i11)));
                i11++;
            }
        } else {
            if (i10 != 2) {
                throw new IllegalArgumentException("dataType [" + expandableData.m13492a() + "] is not valid");
            }
            ArrayList<WatchHistoryList.LiveHistoryInfo> arrayListM13495d = expandableData.m13495d();
            size = arrayListM13495d.size();
            if (this.f12123d) {
                ArrayList<Boolean> arrayListM13494c = expandableData.m13494c();
                while (i11 < size) {
                    this.f12121b.add(i9 + i11 + 1, new ExpandableData(arrayListM13495d.get(i11), arrayListM13494c.get(i11).booleanValue()));
                    i11++;
                }
            } else {
                while (i11 < size) {
                    this.f12121b.add(i9 + i11 + 1, new ExpandableData(arrayListM13495d.get(i11)));
                    i11++;
                }
            }
        }
        expandableData.m13502k(true);
        notifyItemChanged(i9);
        notifyItemRangeInserted(i9 + 1, size);
        if (z8) {
            this.f12122c.mo13947a(i9);
        }
    }

    /* renamed from: i */
    public ArrayList<WatchHistoryList.LiveHistoryInfo> m13942i() {
        ArrayList<WatchHistoryList.LiveHistoryInfo> arrayList = new ArrayList<>();
        int iM13939f = m13939f(ExpandableData.DataType.HISTORY);
        ExpandableData expandableData = this.f12121b.get(iM13939f);
        if (expandableData.m13500i()) {
            while (true) {
                iM13939f++;
                if (iM13939f >= this.f12121b.size()) {
                    break;
                }
                if (this.f12121b.get(iM13939f).m13501j()) {
                    arrayList.add(this.f12121b.get(iM13939f).m13497f());
                }
            }
        } else {
            ArrayList<WatchHistoryList.LiveHistoryInfo> arrayListM13495d = expandableData.m13495d();
            ArrayList<Boolean> arrayListM13494c = expandableData.m13494c();
            for (int i9 = 0; i9 < arrayListM13495d.size(); i9++) {
                if (arrayListM13494c.get(i9).booleanValue()) {
                    arrayList.add(arrayListM13495d.get(i9));
                }
            }
        }
        return arrayList;
    }

    /* renamed from: j */
    public final void m13943j() {
        Iterator<ExpandableData> it = this.f12121b.iterator();
        while (it.hasNext()) {
            it.next().m13503l(false);
        }
        ExpandableData expandableData = this.f12121b.get(m13939f(ExpandableData.DataType.HISTORY));
        ArrayList<Boolean> arrayListM13494c = expandableData.m13494c();
        arrayListM13494c.clear();
        for (int i9 = 0; i9 < expandableData.m13495d().size(); i9++) {
            arrayListM13494c.add(i9, Boolean.FALSE);
        }
        this.f12124e = 0;
    }

    /* renamed from: k */
    public boolean m13944k() {
        return this.f12123d;
    }

    /* renamed from: l */
    public final boolean m13945l(int i9, boolean z8) {
        ExpandableData expandableData = this.f12121b.get(i9);
        if (expandableData.m13500i()) {
            m13938e(expandableData, i9);
            return true;
        }
        m13941h(expandableData, i9, z8);
        return false;
    }

    /* renamed from: m */
    public void m13946m(boolean z8) {
        this.f12123d = z8;
        if (z8) {
            m13943j();
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
    public void onBindViewHolder(RecyclerView.AbstractC0442c0 abstractC0442c0, int i9) {
        ExpandableData expandableData = this.f12121b.get(i9);
        int iM13498g = expandableData.m13498g();
        if (iM13498g == 0) {
            ((ViewOnClickListenerC2651b) abstractC0442c0).m13932a(expandableData);
            return;
        }
        if (iM13498g == 1) {
            ((ViewOnClickListenerC2647a) abstractC0442c0).m13931a(expandableData, this);
            return;
        }
        throw new IllegalArgumentException("onBindViewHolder viewType [ " + expandableData.m13498g() + " ] is not valid");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
    public RecyclerView.AbstractC0442c0 onCreateViewHolder(ViewGroup viewGroup, int i9) {
        if (i9 == 0) {
            return new ViewOnClickListenerC2651b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_friends_titles, viewGroup, false), this);
        }
        if (i9 == 1) {
            return new ViewOnClickListenerC2647a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_webinars_history_item, viewGroup, false), this.f12120a, this);
        }
        throw new IllegalArgumentException("onCreateViewHolder viewType [ " + i9 + " ] is not valid");
    }
}
