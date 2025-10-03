package com.mobeta.android.dslv;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import p016b5.C0686b;
import p016b5.C0687c;
import p016b5.C0688d;
import p016b5.ViewOnTouchListenerC0685a;

/* loaded from: classes2.dex */
public class DragSortListView extends ListView {

    /* renamed from: A */
    public RunnableC4489h f15798A;

    /* renamed from: B */
    public float f15799B;

    /* renamed from: C */
    public float f15800C;

    /* renamed from: D */
    public int f15801D;

    /* renamed from: E */
    public int f15802E;

    /* renamed from: F */
    public float f15803F;

    /* renamed from: G */
    public float f15804G;

    /* renamed from: H */
    public float f15805H;

    /* renamed from: I */
    public float f15806I;

    /* renamed from: J */
    public float f15807J;

    /* renamed from: K */
    public InterfaceC4488g f15808K;

    /* renamed from: L */
    public int f15809L;

    /* renamed from: M */
    public int f15810M;

    /* renamed from: N */
    public int f15811N;

    /* renamed from: O */
    public int f15812O;

    /* renamed from: P */
    public boolean f15813P;

    /* renamed from: Q */
    public boolean f15814Q;

    /* renamed from: R */
    public InterfaceC4494m f15815R;

    /* renamed from: S */
    public MotionEvent f15816S;

    /* renamed from: T */
    public int f15817T;

    /* renamed from: U */
    public float f15818U;

    /* renamed from: V */
    public float f15819V;

    /* renamed from: W */
    public C4486e f15820W;

    /* renamed from: a0 */
    public boolean f15821a0;

    /* renamed from: b */
    public View f15822b;

    /* renamed from: b0 */
    public C4491j f15823b0;

    /* renamed from: c */
    public Point f15824c;

    /* renamed from: c0 */
    public boolean f15825c0;

    /* renamed from: d */
    public Point f15826d;

    /* renamed from: d0 */
    public boolean f15827d0;

    /* renamed from: e */
    public int f15828e;

    /* renamed from: e0 */
    public C4495n f15829e0;

    /* renamed from: f */
    public boolean f15830f;

    /* renamed from: f0 */
    public C4496o f15831f0;

    /* renamed from: g */
    public DataSetObserver f15832g;

    /* renamed from: g0 */
    public C4492k f15833g0;

    /* renamed from: h */
    public float f15834h;

    /* renamed from: h0 */
    public boolean f15835h0;

    /* renamed from: i */
    public float f15836i;

    /* renamed from: i0 */
    public float f15837i0;

    /* renamed from: j */
    public int f15838j;

    /* renamed from: j0 */
    public String f15839j0;

    /* renamed from: k */
    public int f15840k;

    /* renamed from: k0 */
    public String f15841k0;

    /* renamed from: l */
    public int f15842l;

    /* renamed from: l0 */
    public String f15843l0;

    /* renamed from: m */
    public boolean f15844m;

    /* renamed from: m0 */
    public String f15845m0;

    /* renamed from: n */
    public int f15846n;

    /* renamed from: n0 */
    public boolean f15847n0;

    /* renamed from: o */
    public int f15848o;

    /* renamed from: o0 */
    public int f15849o0;

    /* renamed from: p */
    public int f15850p;

    /* renamed from: p0 */
    public boolean f15851p0;

    /* renamed from: q */
    public InterfaceC4487f f15852q;

    /* renamed from: q0 */
    public boolean f15853q0;

    /* renamed from: r */
    public InterfaceC4493l f15854r;

    /* renamed from: s */
    public InterfaceC4497p f15855s;

    /* renamed from: t */
    public boolean f15856t;

    /* renamed from: u */
    public int f15857u;

    /* renamed from: v */
    public int f15858v;

    /* renamed from: w */
    public int f15859w;

    /* renamed from: x */
    public int f15860x;

    /* renamed from: y */
    public int f15861y;

    /* renamed from: z */
    public View[] f15862z;

    /* renamed from: com.mobeta.android.dslv.DragSortListView$a */
    public class C4482a implements InterfaceC4488g {
        public C4482a() {
        }

        @Override // com.mobeta.android.dslv.DragSortListView.InterfaceC4488g
        /* renamed from: a */
        public float mo12438a(float f9, long j9) {
            return DragSortListView.this.f15807J * f9;
        }
    }

    /* renamed from: com.mobeta.android.dslv.DragSortListView$b */
    public class C4483b extends DataSetObserver {
        public C4483b() {
        }

        /* renamed from: a */
        public final void m18066a() {
            if (DragSortListView.this.f15857u == 4) {
                DragSortListView.this.m18032O();
            }
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            m18066a();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            m18066a();
        }
    }

    /* renamed from: com.mobeta.android.dslv.DragSortListView$c */
    public class DialogInterfaceOnClickListenerC4484c implements DialogInterface.OnClickListener {

        /* renamed from: b */
        public final /* synthetic */ int f15865b;

        /* renamed from: c */
        public final /* synthetic */ float f15866c;

        public DialogInterfaceOnClickListenerC4484c(int i9, float f9) {
            this.f15865b = i9;
            this.f15866c = f9;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i9) {
            DragSortListView.this.m18054k0(this.f15865b, this.f15866c);
        }
    }

    /* renamed from: com.mobeta.android.dslv.DragSortListView$d */
    public class DialogInterfaceOnClickListenerC4485d implements DialogInterface.OnClickListener {
        public DialogInterfaceOnClickListenerC4485d() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i9) {
        }
    }

    /* renamed from: com.mobeta.android.dslv.DragSortListView$e */
    public class C4486e extends BaseAdapter {

        /* renamed from: b */
        public ListAdapter f15869b;

        /* renamed from: com.mobeta.android.dslv.DragSortListView$e$a */
        public class a extends DataSetObserver {

            /* renamed from: a */
            public final /* synthetic */ DragSortListView f15871a;

            public a(DragSortListView dragSortListView) {
                this.f15871a = dragSortListView;
            }

            @Override // android.database.DataSetObserver
            public void onChanged() {
                C4486e.this.notifyDataSetChanged();
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                C4486e.this.notifyDataSetInvalidated();
            }
        }

        public C4486e(ListAdapter listAdapter) {
            this.f15869b = listAdapter;
            listAdapter.registerDataSetObserver(new a(DragSortListView.this));
        }

        /* renamed from: a */
        public ListAdapter m18067a() {
            return this.f15869b;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            return this.f15869b.areAllItemsEnabled();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.f15869b.getCount();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i9) {
            return this.f15869b.getItem(i9);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i9) {
            return this.f15869b.getItemId(i9);
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i9) {
            return this.f15869b.getItemViewType(i9);
        }

        @Override // android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            C0686b c0686b;
            if (view != null) {
                c0686b = (C0686b) view;
                View childAt = c0686b.getChildAt(0);
                View view2 = this.f15869b.getView(i9, childAt, DragSortListView.this);
                if (view2 != childAt) {
                    if (childAt != null) {
                        c0686b.removeViewAt(0);
                    }
                    c0686b.addView(view2);
                }
            } else {
                View view3 = this.f15869b.getView(i9, null, DragSortListView.this);
                C0686b c0687c = view3 instanceof Checkable ? new C0687c(DragSortListView.this.getContext()) : new C0686b(DragSortListView.this.getContext());
                c0687c.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
                c0687c.addView(view3);
                c0686b = c0687c;
            }
            DragSortListView dragSortListView = DragSortListView.this;
            dragSortListView.m18027J(i9 + dragSortListView.getHeaderViewsCount(), c0686b, true);
            return c0686b;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getViewTypeCount() {
            return this.f15869b.getViewTypeCount();
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return this.f15869b.hasStableIds();
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean isEmpty() {
            return this.f15869b.isEmpty();
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i9) {
            return this.f15869b.isEnabled(i9);
        }
    }

    /* renamed from: com.mobeta.android.dslv.DragSortListView$f */
    public interface InterfaceC4487f {
        /* renamed from: a */
        void mo18068a(int i9, int i10);
    }

    /* renamed from: com.mobeta.android.dslv.DragSortListView$g */
    public interface InterfaceC4488g {
        /* renamed from: a */
        float mo12438a(float f9, long j9);
    }

    /* renamed from: com.mobeta.android.dslv.DragSortListView$h */
    public class RunnableC4489h implements Runnable {

        /* renamed from: b */
        public boolean f15873b;

        /* renamed from: c */
        public long f15874c;

        /* renamed from: d */
        public long f15875d;

        /* renamed from: e */
        public int f15876e;

        /* renamed from: f */
        public float f15877f;

        /* renamed from: g */
        public long f15878g;

        /* renamed from: h */
        public int f15879h;

        /* renamed from: i */
        public float f15880i;

        /* renamed from: j */
        public boolean f15881j = false;

        public RunnableC4489h() {
        }

        /* renamed from: a */
        public int m18069a() {
            if (this.f15881j) {
                return this.f15879h;
            }
            return -1;
        }

        /* renamed from: b */
        public boolean m18070b() {
            return this.f15881j;
        }

        /* renamed from: c */
        public void m18071c(int i9) {
            if (this.f15881j) {
                return;
            }
            this.f15873b = false;
            this.f15881j = true;
            long jUptimeMillis = SystemClock.uptimeMillis();
            this.f15878g = jUptimeMillis;
            this.f15874c = jUptimeMillis;
            this.f15879h = i9;
            DragSortListView.this.post(this);
        }

        /* renamed from: d */
        public void m18072d(boolean z8) {
            if (!z8) {
                this.f15873b = true;
            } else {
                DragSortListView.this.removeCallbacks(this);
                this.f15881j = false;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f15873b) {
                this.f15881j = false;
                return;
            }
            int firstVisiblePosition = DragSortListView.this.getFirstVisiblePosition();
            int lastVisiblePosition = DragSortListView.this.getLastVisiblePosition();
            int count = DragSortListView.this.getCount();
            int paddingTop = DragSortListView.this.getPaddingTop();
            int height = (DragSortListView.this.getHeight() - paddingTop) - DragSortListView.this.getPaddingBottom();
            int iMin = Math.min(DragSortListView.this.f15810M, DragSortListView.this.f15828e + DragSortListView.this.f15860x);
            int iMax = Math.max(DragSortListView.this.f15810M, DragSortListView.this.f15828e - DragSortListView.this.f15860x);
            if (this.f15879h == 0) {
                View childAt = DragSortListView.this.getChildAt(0);
                if (childAt == null) {
                    this.f15881j = false;
                    return;
                } else {
                    if (firstVisiblePosition == 0 && childAt.getTop() == paddingTop) {
                        this.f15881j = false;
                        return;
                    }
                    this.f15880i = DragSortListView.this.f15808K.mo12438a((DragSortListView.this.f15804G - iMax) / DragSortListView.this.f15805H, this.f15874c);
                }
            } else {
                View childAt2 = DragSortListView.this.getChildAt(lastVisiblePosition - firstVisiblePosition);
                if (childAt2 == null) {
                    this.f15881j = false;
                    return;
                } else {
                    if (lastVisiblePosition == count - 1 && childAt2.getBottom() <= height + paddingTop) {
                        this.f15881j = false;
                        return;
                    }
                    this.f15880i = -DragSortListView.this.f15808K.mo12438a((iMin - DragSortListView.this.f15803F) / DragSortListView.this.f15806I, this.f15874c);
                }
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            this.f15875d = jUptimeMillis;
            float f9 = jUptimeMillis - this.f15874c;
            this.f15877f = f9;
            int iRound = Math.round(this.f15880i * f9);
            this.f15876e = iRound;
            if (iRound >= 0) {
                this.f15876e = Math.min(height, iRound);
                lastVisiblePosition = firstVisiblePosition;
            } else {
                this.f15876e = Math.max(-height, iRound);
            }
            View childAt3 = DragSortListView.this.getChildAt(lastVisiblePosition - firstVisiblePosition);
            int top = childAt3.getTop() + this.f15876e;
            if (lastVisiblePosition == 0 && top > paddingTop) {
                top = paddingTop;
            }
            DragSortListView.this.f15825c0 = true;
            DragSortListView.this.setSelectionFromTop(lastVisiblePosition, top - paddingTop);
            DragSortListView.this.layoutChildren();
            DragSortListView.this.invalidate();
            DragSortListView.this.f15825c0 = false;
            DragSortListView.this.m18038U(lastVisiblePosition, childAt3, false);
            this.f15874c = this.f15875d;
            DragSortListView.this.post(this);
        }
    }

    /* renamed from: com.mobeta.android.dslv.DragSortListView$i */
    public interface InterfaceC4490i extends InterfaceC4493l, InterfaceC4487f, InterfaceC4497p {
    }

    /* renamed from: com.mobeta.android.dslv.DragSortListView$j */
    public class C4491j {

        /* renamed from: b */
        public File f15884b;

        /* renamed from: a */
        public StringBuilder f15883a = new StringBuilder();

        /* renamed from: c */
        public int f15885c = 0;

        /* renamed from: d */
        public int f15886d = 0;

        /* renamed from: e */
        public boolean f15887e = false;

        public C4491j() throws IOException {
            File file = new File(Environment.getExternalStorageDirectory(), "dslv_state.txt");
            this.f15884b = file;
            if (file.exists()) {
                return;
            }
            try {
                this.f15884b.createNewFile();
                Log.d("mobeta", "file created");
            } catch (IOException e9) {
                Log.w("mobeta", "Could not create dslv_state.txt");
                Log.d("mobeta", e9.getMessage());
            }
        }

        /* renamed from: a */
        public void m18073a() throws Throwable {
            if (this.f15887e) {
                this.f15883a.append("<DSLVState>\n");
                int childCount = DragSortListView.this.getChildCount();
                int firstVisiblePosition = DragSortListView.this.getFirstVisiblePosition();
                this.f15883a.append("    <Positions>");
                for (int i9 = 0; i9 < childCount; i9++) {
                    StringBuilder sb = this.f15883a;
                    sb.append(firstVisiblePosition + i9);
                    sb.append(",");
                }
                this.f15883a.append("</Positions>\n");
                this.f15883a.append("    <Tops>");
                for (int i10 = 0; i10 < childCount; i10++) {
                    StringBuilder sb2 = this.f15883a;
                    sb2.append(DragSortListView.this.getChildAt(i10).getTop());
                    sb2.append(",");
                }
                this.f15883a.append("</Tops>\n");
                this.f15883a.append("    <Bottoms>");
                for (int i11 = 0; i11 < childCount; i11++) {
                    StringBuilder sb3 = this.f15883a;
                    sb3.append(DragSortListView.this.getChildAt(i11).getBottom());
                    sb3.append(",");
                }
                this.f15883a.append("</Bottoms>\n");
                StringBuilder sb4 = this.f15883a;
                sb4.append("    <FirstExpPos>");
                sb4.append(DragSortListView.this.f15840k);
                sb4.append("</FirstExpPos>\n");
                StringBuilder sb5 = this.f15883a;
                sb5.append("    <FirstExpBlankHeight>");
                DragSortListView dragSortListView = DragSortListView.this;
                int iM18046c0 = dragSortListView.m18046c0(dragSortListView.f15840k);
                DragSortListView dragSortListView2 = DragSortListView.this;
                sb5.append(iM18046c0 - dragSortListView2.m18044a0(dragSortListView2.f15840k));
                sb5.append("</FirstExpBlankHeight>\n");
                StringBuilder sb6 = this.f15883a;
                sb6.append("    <SecondExpPos>");
                sb6.append(DragSortListView.this.f15842l);
                sb6.append("</SecondExpPos>\n");
                StringBuilder sb7 = this.f15883a;
                sb7.append("    <SecondExpBlankHeight>");
                DragSortListView dragSortListView3 = DragSortListView.this;
                int iM18046c02 = dragSortListView3.m18046c0(dragSortListView3.f15842l);
                DragSortListView dragSortListView4 = DragSortListView.this;
                sb7.append(iM18046c02 - dragSortListView4.m18044a0(dragSortListView4.f15842l));
                sb7.append("</SecondExpBlankHeight>\n");
                StringBuilder sb8 = this.f15883a;
                sb8.append("    <SrcPos>");
                sb8.append(DragSortListView.this.f15846n);
                sb8.append("</SrcPos>\n");
                StringBuilder sb9 = this.f15883a;
                sb9.append("    <SrcHeight>");
                sb9.append(DragSortListView.this.f15859w + DragSortListView.this.getDividerHeight());
                sb9.append("</SrcHeight>\n");
                StringBuilder sb10 = this.f15883a;
                sb10.append("    <ViewHeight>");
                sb10.append(DragSortListView.this.getHeight());
                sb10.append("</ViewHeight>\n");
                StringBuilder sb11 = this.f15883a;
                sb11.append("    <LastY>");
                sb11.append(DragSortListView.this.f15811N);
                sb11.append("</LastY>\n");
                StringBuilder sb12 = this.f15883a;
                sb12.append("    <FloatY>");
                sb12.append(DragSortListView.this.f15828e);
                sb12.append("</FloatY>\n");
                this.f15883a.append("    <ShuffleEdges>");
                for (int i12 = 0; i12 < childCount; i12++) {
                    StringBuilder sb13 = this.f15883a;
                    DragSortListView dragSortListView5 = DragSortListView.this;
                    sb13.append(dragSortListView5.m18047d0(firstVisiblePosition + i12, dragSortListView5.getChildAt(i12).getTop()));
                    sb13.append(",");
                }
                this.f15883a.append("</ShuffleEdges>\n");
                this.f15883a.append("</DSLVState>\n");
                int i13 = this.f15885c + 1;
                this.f15885c = i13;
                if (i13 > 1000) {
                    m18074b();
                    this.f15885c = 0;
                }
            }
        }

        /* renamed from: b */
        public void m18074b() throws Throwable {
            FileWriter fileWriter;
            Throwable th;
            if (this.f15887e) {
                FileWriter fileWriter2 = null;
                try {
                    try {
                        fileWriter = new FileWriter(this.f15884b, this.f15886d != 0);
                        try {
                            fileWriter.write(this.f15883a.toString());
                            StringBuilder sb = this.f15883a;
                            sb.delete(0, sb.length());
                            fileWriter.flush();
                            this.f15886d++;
                            fileWriter.close();
                        } catch (IOException unused) {
                            fileWriter2 = fileWriter;
                            if (fileWriter2 != null) {
                                fileWriter2.close();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileWriter != null) {
                                try {
                                    fileWriter.close();
                                } catch (IOException e9) {
                                    e9.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e10) {
                        e10.printStackTrace();
                    }
                } catch (IOException unused2) {
                } catch (Throwable th3) {
                    fileWriter = null;
                    th = th3;
                }
            }
        }

        /* renamed from: c */
        public void m18075c() {
            this.f15883a.append("<DSLVStates>\n");
            this.f15886d = 0;
            this.f15887e = true;
        }

        /* renamed from: d */
        public void m18076d() throws Throwable {
            if (this.f15887e) {
                this.f15883a.append("</DSLVStates>\n");
                m18074b();
                this.f15887e = false;
            }
        }
    }

    /* renamed from: com.mobeta.android.dslv.DragSortListView$k */
    public class C4492k extends RunnableC4498q {

        /* renamed from: k */
        public int f15889k;

        /* renamed from: l */
        public int f15890l;

        /* renamed from: m */
        public float f15891m;

        /* renamed from: n */
        public float f15892n;

        public C4492k(float f9, int i9) {
            super(f9, i9);
        }

        @Override // com.mobeta.android.dslv.DragSortListView.RunnableC4498q
        /* renamed from: b */
        public void mo18077b() {
            this.f15889k = DragSortListView.this.f15838j;
            this.f15890l = DragSortListView.this.f15846n;
            DragSortListView.this.f15857u = 2;
            this.f15891m = DragSortListView.this.f15824c.y - m18080g();
            this.f15892n = DragSortListView.this.f15824c.x - DragSortListView.this.getPaddingLeft();
        }

        @Override // com.mobeta.android.dslv.DragSortListView.RunnableC4498q
        /* renamed from: c */
        public void mo18078c() {
            DragSortListView.this.m18043Z();
        }

        @Override // com.mobeta.android.dslv.DragSortListView.RunnableC4498q
        /* renamed from: d */
        public void mo18079d(float f9, float f10) {
            int iM18080g = m18080g();
            int paddingLeft = DragSortListView.this.getPaddingLeft();
            float f11 = DragSortListView.this.f15824c.y - iM18080g;
            float f12 = DragSortListView.this.f15824c.x - paddingLeft;
            float f13 = 1.0f - f10;
            if (f13 < Math.abs(f11 / this.f15891m) || f13 < Math.abs(f12 / this.f15892n)) {
                DragSortListView.this.f15824c.y = iM18080g + ((int) (this.f15891m * f13));
                DragSortListView.this.f15824c.x = DragSortListView.this.getPaddingLeft() + ((int) (this.f15892n * f13));
                DragSortListView.this.m18039V(true);
            }
        }

        /* renamed from: g */
        public final int m18080g() {
            int bottom;
            int firstVisiblePosition = DragSortListView.this.getFirstVisiblePosition();
            int dividerHeight = (DragSortListView.this.f15858v + DragSortListView.this.getDividerHeight()) / 2;
            View childAt = DragSortListView.this.getChildAt(this.f15889k - firstVisiblePosition);
            if (childAt == null) {
                m18087a();
                return -1;
            }
            int i9 = this.f15889k;
            int i10 = this.f15890l;
            if (i9 == i10) {
                return childAt.getTop();
            }
            if (i9 < i10) {
                bottom = childAt.getTop();
            } else {
                bottom = childAt.getBottom() + dividerHeight;
                dividerHeight = DragSortListView.this.f15859w;
            }
            return bottom - dividerHeight;
        }
    }

    /* renamed from: com.mobeta.android.dslv.DragSortListView$l */
    public interface InterfaceC4493l {
        /* renamed from: c */
        void mo18081c(int i9, int i10);
    }

    /* renamed from: com.mobeta.android.dslv.DragSortListView$m */
    public interface InterfaceC4494m {
        /* renamed from: a */
        void mo18082a(View view);

        /* renamed from: b */
        View mo18083b(int i9);

        /* renamed from: c */
        void mo3429c(View view, Point point, Point point2);
    }

    /* renamed from: com.mobeta.android.dslv.DragSortListView$n */
    public class C4495n {

        /* renamed from: a */
        public SparseIntArray f15894a;

        /* renamed from: b */
        public ArrayList<Integer> f15895b;

        /* renamed from: c */
        public int f15896c;

        public C4495n(int i9) {
            this.f15894a = new SparseIntArray(i9);
            this.f15895b = new ArrayList<>(i9);
            this.f15896c = i9;
        }

        /* renamed from: a */
        public void m18084a(int i9, int i10) {
            int i11 = this.f15894a.get(i9, -1);
            if (i11 != i10) {
                if (i11 != -1) {
                    this.f15895b.remove(Integer.valueOf(i9));
                } else if (this.f15894a.size() == this.f15896c) {
                    this.f15894a.delete(this.f15895b.remove(0).intValue());
                }
                this.f15894a.put(i9, i10);
                this.f15895b.add(Integer.valueOf(i9));
            }
        }

        /* renamed from: b */
        public void m18085b() {
            this.f15894a.clear();
            this.f15895b.clear();
        }

        /* renamed from: c */
        public int m18086c(int i9) {
            return this.f15894a.get(i9, -1);
        }
    }

    /* renamed from: com.mobeta.android.dslv.DragSortListView$o */
    public class C4496o extends RunnableC4498q {

        /* renamed from: k */
        public float f15898k;

        /* renamed from: l */
        public float f15899l;

        /* renamed from: m */
        public float f15900m;

        /* renamed from: n */
        public int f15901n;

        /* renamed from: o */
        public int f15902o;

        /* renamed from: p */
        public int f15903p;

        /* renamed from: q */
        public int f15904q;

        public C4496o(float f9, int i9) {
            super(f9, i9);
            this.f15901n = -1;
            this.f15902o = -1;
        }

        @Override // com.mobeta.android.dslv.DragSortListView.RunnableC4498q
        /* renamed from: b */
        public void mo18077b() {
            this.f15901n = -1;
            this.f15902o = -1;
            this.f15903p = DragSortListView.this.f15840k;
            this.f15904q = DragSortListView.this.f15842l;
            DragSortListView.this.f15857u = 1;
            this.f15898k = DragSortListView.this.f15824c.x;
            if (!DragSortListView.this.f15835h0) {
                DragSortListView.this.m18036S();
                return;
            }
            float width = DragSortListView.this.getWidth() * 2.0f;
            if (DragSortListView.this.f15837i0 == BitmapDescriptorFactory.HUE_RED) {
                DragSortListView.this.f15837i0 = (this.f15898k >= BitmapDescriptorFactory.HUE_RED ? 1 : -1) * width;
                return;
            }
            float f9 = width * 2.0f;
            if (DragSortListView.this.f15837i0 < BitmapDescriptorFactory.HUE_RED) {
                float f10 = -f9;
                if (DragSortListView.this.f15837i0 > f10) {
                    DragSortListView.this.f15837i0 = f10;
                    return;
                }
            }
            if (DragSortListView.this.f15837i0 <= BitmapDescriptorFactory.HUE_RED || DragSortListView.this.f15837i0 >= f9) {
                return;
            }
            DragSortListView.this.f15837i0 = f9;
        }

        @Override // com.mobeta.android.dslv.DragSortListView.RunnableC4498q
        /* renamed from: c */
        public void mo18078c() {
            DragSortListView.this.m18040W();
        }

        @Override // com.mobeta.android.dslv.DragSortListView.RunnableC4498q
        /* renamed from: d */
        public void mo18079d(float f9, float f10) {
            View childAt;
            float f11 = 1.0f - f10;
            int firstVisiblePosition = DragSortListView.this.getFirstVisiblePosition();
            View childAt2 = DragSortListView.this.getChildAt(this.f15903p - firstVisiblePosition);
            if (DragSortListView.this.f15835h0) {
                float fUptimeMillis = (SystemClock.uptimeMillis() - this.f15906b) / 1000.0f;
                if (fUptimeMillis == BitmapDescriptorFactory.HUE_RED) {
                    return;
                }
                float f12 = DragSortListView.this.f15837i0 * fUptimeMillis;
                int width = DragSortListView.this.getWidth();
                DragSortListView dragSortListView = DragSortListView.this;
                float f13 = (dragSortListView.f15837i0 > BitmapDescriptorFactory.HUE_RED ? 1 : -1) * fUptimeMillis;
                float f14 = width;
                DragSortListView.m18011l(dragSortListView, f13 * f14);
                this.f15898k += f12;
                Point point = DragSortListView.this.f15824c;
                float f15 = this.f15898k;
                point.x = (int) f15;
                if (f15 < f14 && f15 > (-width)) {
                    this.f15906b = SystemClock.uptimeMillis();
                    DragSortListView.this.m18039V(true);
                    return;
                }
            }
            if (childAt2 != null) {
                if (this.f15901n == -1) {
                    this.f15901n = DragSortListView.this.m18045b0(this.f15903p, childAt2, false);
                    this.f15899l = childAt2.getHeight() - this.f15901n;
                }
                int iMax = Math.max((int) (this.f15899l * f11), 1);
                ViewGroup.LayoutParams layoutParams = childAt2.getLayoutParams();
                layoutParams.height = this.f15901n + iMax;
                childAt2.setLayoutParams(layoutParams);
            }
            int i9 = this.f15904q;
            if (i9 == this.f15903p || (childAt = DragSortListView.this.getChildAt(i9 - firstVisiblePosition)) == null) {
                return;
            }
            if (this.f15902o == -1) {
                this.f15902o = DragSortListView.this.m18045b0(this.f15904q, childAt, false);
                this.f15900m = childAt.getHeight() - this.f15902o;
            }
            int iMax2 = Math.max((int) (f11 * this.f15900m), 1);
            ViewGroup.LayoutParams layoutParams2 = childAt.getLayoutParams();
            layoutParams2.height = this.f15902o + iMax2;
            childAt.setLayoutParams(layoutParams2);
        }
    }

    /* renamed from: com.mobeta.android.dslv.DragSortListView$p */
    public interface InterfaceC4497p {
        void remove(int i9);
    }

    /* renamed from: com.mobeta.android.dslv.DragSortListView$q */
    public class RunnableC4498q implements Runnable {

        /* renamed from: b */
        public long f15906b;

        /* renamed from: c */
        public float f15907c;

        /* renamed from: d */
        public float f15908d;

        /* renamed from: e */
        public float f15909e;

        /* renamed from: f */
        public float f15910f;

        /* renamed from: g */
        public float f15911g;

        /* renamed from: h */
        public float f15912h;

        /* renamed from: i */
        public boolean f15913i;

        public RunnableC4498q(float f9, int i9) {
            this.f15908d = f9;
            this.f15907c = i9;
            float f10 = 1.0f / ((f9 * 2.0f) * (1.0f - f9));
            this.f15912h = f10;
            this.f15909e = f10;
            this.f15910f = f9 / ((f9 - 1.0f) * 2.0f);
            this.f15911g = 1.0f / (1.0f - f9);
        }

        /* renamed from: a */
        public void m18087a() {
            this.f15913i = true;
        }

        /* renamed from: b */
        public void mo18077b() {
            throw null;
        }

        /* renamed from: c */
        public void mo18078c() {
            throw null;
        }

        /* renamed from: d */
        public void mo18079d(float f9, float f10) {
            throw null;
        }

        /* renamed from: e */
        public void m18088e() {
            this.f15906b = SystemClock.uptimeMillis();
            this.f15913i = false;
            mo18077b();
            DragSortListView.this.post(this);
        }

        /* renamed from: f */
        public float m18089f(float f9) {
            float f10 = this.f15908d;
            if (f9 < f10) {
                return this.f15909e * f9 * f9;
            }
            if (f9 < 1.0f - f10) {
                return this.f15910f + (this.f15911g * f9);
            }
            float f11 = f9 - 1.0f;
            return 1.0f - ((this.f15912h * f11) * f11);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f15913i) {
                return;
            }
            float fUptimeMillis = (SystemClock.uptimeMillis() - this.f15906b) / this.f15907c;
            if (fUptimeMillis >= 1.0f) {
                mo18079d(1.0f, 1.0f);
                mo18078c();
            } else {
                mo18079d(fUptimeMillis, m18089f(fUptimeMillis));
                DragSortListView.this.post(this);
            }
        }
    }

    public DragSortListView(Context context, AttributeSet attributeSet) {
        int i9;
        super(context, attributeSet);
        this.f15824c = new Point();
        this.f15826d = new Point();
        this.f15830f = false;
        this.f15834h = 1.0f;
        this.f15836i = 1.0f;
        this.f15844m = false;
        this.f15856t = true;
        this.f15857u = 0;
        this.f15858v = 1;
        this.f15861y = 0;
        this.f15862z = new View[1];
        this.f15799B = 0.33333334f;
        this.f15800C = 0.33333334f;
        this.f15807J = 0.5f;
        this.f15808K = new C4482a();
        this.f15812O = 0;
        this.f15813P = false;
        this.f15814Q = false;
        this.f15815R = null;
        this.f15817T = 0;
        this.f15818U = 0.25f;
        this.f15819V = BitmapDescriptorFactory.HUE_RED;
        this.f15821a0 = false;
        this.f15825c0 = false;
        this.f15827d0 = false;
        this.f15829e0 = new C4495n(3);
        this.f15837i0 = BitmapDescriptorFactory.HUE_RED;
        this.f15839j0 = "";
        this.f15841k0 = "";
        this.f15843l0 = "";
        this.f15845m0 = "";
        this.f15847n0 = false;
        this.f15849o0 = 1;
        this.f15851p0 = false;
        this.f15853q0 = false;
        int i10 = 150;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C0688d.DragSortListView, 0, 0);
            this.f15858v = Math.max(1, typedArrayObtainStyledAttributes.getDimensionPixelSize(C0688d.DragSortListView_collapsed_height, 1));
            boolean z8 = typedArrayObtainStyledAttributes.getBoolean(C0688d.DragSortListView_track_drag_sort, false);
            this.f15821a0 = z8;
            if (z8) {
                this.f15823b0 = new C4491j();
            }
            float f9 = typedArrayObtainStyledAttributes.getFloat(C0688d.DragSortListView_float_alpha, this.f15834h);
            this.f15834h = f9;
            this.f15836i = f9;
            this.f15856t = typedArrayObtainStyledAttributes.getBoolean(C0688d.DragSortListView_drag_enabled, this.f15856t);
            float fMax = Math.max(BitmapDescriptorFactory.HUE_RED, Math.min(1.0f, 1.0f - typedArrayObtainStyledAttributes.getFloat(C0688d.DragSortListView_slide_shuffle_speed, 0.75f)));
            this.f15818U = fMax;
            this.f15844m = fMax > BitmapDescriptorFactory.HUE_RED;
            setDragScrollStart(typedArrayObtainStyledAttributes.getFloat(C0688d.DragSortListView_drag_scroll_start, this.f15799B));
            this.f15807J = typedArrayObtainStyledAttributes.getFloat(C0688d.DragSortListView_max_drag_scroll_speed, this.f15807J);
            int i11 = typedArrayObtainStyledAttributes.getInt(C0688d.DragSortListView_remove_animation_duration, 150);
            i9 = typedArrayObtainStyledAttributes.getInt(C0688d.DragSortListView_drop_animation_duration, 150);
            boolean z9 = typedArrayObtainStyledAttributes.getBoolean(C0688d.DragSortListView_use_default_controller, true);
            this.f15851p0 = typedArrayObtainStyledAttributes.getBoolean(C0688d.DragSortListView_remove_enabled, this.f15851p0);
            if (z9) {
                int i12 = typedArrayObtainStyledAttributes.getInt(C0688d.DragSortListView_remove_mode, 1);
                boolean z10 = typedArrayObtainStyledAttributes.getBoolean(C0688d.DragSortListView_sort_enabled, true);
                int i13 = typedArrayObtainStyledAttributes.getInt(C0688d.DragSortListView_drag_start_mode, 0);
                int resourceId = typedArrayObtainStyledAttributes.getResourceId(C0688d.DragSortListView_drag_handle_id, 0);
                int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(C0688d.DragSortListView_fling_handle_id, 0);
                int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(C0688d.DragSortListView_click_remove_id, 0);
                int color = typedArrayObtainStyledAttributes.getColor(C0688d.DragSortListView_float_background_color, -16777216);
                ViewOnTouchListenerC0685a viewOnTouchListenerC0685a = new ViewOnTouchListenerC0685a(this, resourceId, i13, i12, resourceId3, resourceId2);
                viewOnTouchListenerC0685a.m3433n(this.f15851p0);
                viewOnTouchListenerC0685a.m3435p(z10);
                viewOnTouchListenerC0685a.m18094d(color);
                this.f15815R = viewOnTouchListenerC0685a;
                setOnTouchListener(viewOnTouchListenerC0685a);
            }
            typedArrayObtainStyledAttributes.recycle();
            i10 = i11;
        } else {
            i9 = 150;
        }
        this.f15798A = new RunnableC4489h();
        if (i10 > 0) {
            this.f15831f0 = new C4496o(0.5f, i10);
        }
        if (i9 > 0) {
            this.f15833g0 = new C4492k(0.5f, i9);
        }
        this.f15816S = MotionEvent.obtain(0L, 0L, 3, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0, 0);
        this.f15832g = new C4483b();
    }

    /* renamed from: l */
    public static /* synthetic */ float m18011l(DragSortListView dragSortListView, float f9) {
        float f10 = dragSortListView.f15837i0 + f9;
        dragSortListView.f15837i0 = f10;
        return f10;
    }

    /* renamed from: I */
    public final void m18026I() {
        int firstVisiblePosition = getFirstVisiblePosition();
        int lastVisiblePosition = getLastVisiblePosition();
        int iMin = Math.min(lastVisiblePosition - firstVisiblePosition, ((getCount() - 1) - getFooterViewsCount()) - firstVisiblePosition);
        for (int iMax = Math.max(0, getHeaderViewsCount() - firstVisiblePosition); iMax <= iMin; iMax++) {
            View childAt = getChildAt(iMax);
            if (childAt != null) {
                m18027J(firstVisiblePosition + iMax, childAt, false);
            }
        }
    }

    /* renamed from: J */
    public final void m18027J(int i9, View view, boolean z8) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int iM18031N = (i9 == this.f15846n || i9 == this.f15840k || i9 == this.f15842l) ? m18031N(i9, view, z8) : -2;
        if (iM18031N != layoutParams.height) {
            layoutParams.height = iM18031N;
            view.setLayoutParams(layoutParams);
        }
        if (i9 == this.f15840k || i9 == this.f15842l) {
            int i10 = this.f15846n;
            if (i9 < i10) {
                ((C0686b) view).setGravity(80);
            } else if (i9 > i10) {
                ((C0686b) view).setGravity(48);
            }
        }
        int visibility = view.getVisibility();
        int i11 = (i9 != this.f15846n || this.f15822b == null) ? 0 : 4;
        if (i11 != visibility) {
            view.setVisibility(i11);
        }
    }

    /* renamed from: K */
    public final void m18028K() {
        int firstVisiblePosition = getFirstVisiblePosition();
        if (this.f15846n < firstVisiblePosition) {
            View childAt = getChildAt(0);
            setSelectionFromTop(firstVisiblePosition - 1, (childAt != null ? childAt.getTop() : 0) - getPaddingTop());
        }
    }

    /* renamed from: L */
    public final int m18029L(int i9, View view, int i10, int i11) {
        int i12;
        int i13;
        int iM18044a0 = m18044a0(i9);
        int height = view.getHeight();
        int iM18030M = m18030M(i9, iM18044a0);
        int i14 = this.f15846n;
        if (i9 != i14) {
            i12 = height - iM18044a0;
            i13 = iM18030M - iM18044a0;
        } else {
            i12 = height;
            i13 = iM18030M;
        }
        int i15 = this.f15859w;
        int i16 = this.f15840k;
        if (i14 != i16 && i14 != this.f15842l) {
            i15 -= this.f15858v;
        }
        if (i9 <= i10) {
            if (i9 > i16) {
                return 0 + (i15 - i13);
            }
            return 0;
        }
        if (i9 == i11) {
            if (i9 <= i16) {
                i12 -= i15;
            } else if (i9 == this.f15842l) {
                return 0 + (height - iM18030M);
            }
            return 0 + i12;
        }
        if (i9 <= i16) {
            return 0 - i15;
        }
        if (i9 == this.f15842l) {
            return 0 - i13;
        }
        return 0;
    }

    /* renamed from: M */
    public final int m18030M(int i9, int i10) {
        boolean z8 = this.f15844m && this.f15840k != this.f15842l;
        int i11 = this.f15859w;
        int i12 = this.f15858v;
        int i13 = i11 - i12;
        int i14 = (int) (this.f15819V * i13);
        int i15 = this.f15846n;
        return i9 == i15 ? i15 == this.f15840k ? z8 ? i14 + i12 : i11 : i15 == this.f15842l ? i11 - i14 : i12 : i9 == this.f15840k ? z8 ? i10 + i14 : i10 + i13 : i9 == this.f15842l ? (i10 + i13) - i14 : i10;
    }

    /* renamed from: N */
    public final int m18031N(int i9, View view, boolean z8) {
        return m18030M(i9, m18045b0(i9, view, z8));
    }

    /* renamed from: O */
    public void m18032O() {
        if (this.f15857u == 4) {
            this.f15798A.m18072d(true);
            m18036S();
            m18033P();
            m18026I();
            if (this.f15814Q) {
                this.f15857u = 3;
            } else {
                this.f15857u = 0;
            }
        }
    }

    /* renamed from: P */
    public final void m18033P() {
        this.f15846n = -1;
        this.f15840k = -1;
        this.f15842l = -1;
        this.f15838j = -1;
    }

    /* renamed from: Q */
    public void m18034Q(int i9, float f9) {
        if (!this.f15847n0) {
            m18054k0(i9, f9);
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), this.f15849o0);
        builder.setTitle(this.f15839j0);
        builder.setMessage(this.f15841k0);
        builder.setPositiveButton(this.f15843l0, new DialogInterfaceOnClickListenerC4484c(i9, f9));
        builder.setNegativeButton(this.f15845m0, new DialogInterfaceOnClickListenerC4485d());
        builder.show();
    }

    /* renamed from: R */
    public final void m18035R(int i9, int i10) {
        Point point = this.f15824c;
        point.x = i9 - this.f15848o;
        point.y = i10 - this.f15850p;
        m18039V(true);
        int iMin = Math.min(i10, this.f15828e + this.f15860x);
        int iMax = Math.max(i10, this.f15828e - this.f15860x);
        int iM18069a = this.f15798A.m18069a();
        int i11 = this.f15811N;
        if (iMin > i11 && iMin > this.f15802E && iM18069a != 1) {
            if (iM18069a != -1) {
                this.f15798A.m18072d(true);
            }
            this.f15798A.m18071c(1);
        } else if (iMax < i11 && iMax < this.f15801D && iM18069a != 0) {
            if (iM18069a != -1) {
                this.f15798A.m18072d(true);
            }
            this.f15798A.m18071c(0);
        } else {
            if (iMax < this.f15801D || iMin > this.f15802E || !this.f15798A.m18070b()) {
                return;
            }
            this.f15798A.m18072d(true);
        }
    }

    /* renamed from: S */
    public final void m18036S() {
        View view = this.f15822b;
        if (view != null) {
            view.setVisibility(8);
            InterfaceC4494m interfaceC4494m = this.f15815R;
            if (interfaceC4494m != null) {
                interfaceC4494m.mo18082a(this.f15822b);
            }
            this.f15822b = null;
            invalidate();
        }
    }

    /* renamed from: T */
    public final void m18037T() {
        this.f15817T = 0;
        this.f15814Q = false;
        if (this.f15857u == 3) {
            this.f15857u = 0;
        }
        this.f15836i = this.f15834h;
        this.f15853q0 = false;
        this.f15829e0.m18085b();
    }

    /* renamed from: U */
    public final void m18038U(int i9, View view, boolean z8) {
        this.f15825c0 = true;
        m18063t0();
        int i10 = this.f15840k;
        int i11 = this.f15842l;
        boolean zM18064u0 = m18064u0();
        if (zM18064u0) {
            m18026I();
            setSelectionFromTop(i9, (view.getTop() + m18029L(i9, view, i10, i11)) - getPaddingTop());
            layoutChildren();
        }
        if (zM18064u0 || z8) {
            invalidate();
        }
        this.f15825c0 = false;
    }

    /* renamed from: V */
    public final void m18039V(boolean z8) {
        int firstVisiblePosition = getFirstVisiblePosition() + (getChildCount() / 2);
        View childAt = getChildAt(getChildCount() / 2);
        if (childAt == null) {
            return;
        }
        m18038U(firstVisiblePosition, childAt, z8);
    }

    /* renamed from: W */
    public final void m18040W() {
        m18041X(this.f15846n - getHeaderViewsCount());
    }

    /* renamed from: X */
    public final void m18041X(int i9) {
        this.f15857u = 1;
        InterfaceC4497p interfaceC4497p = this.f15855s;
        if (interfaceC4497p != null) {
            interfaceC4497p.remove(i9);
        }
        m18036S();
        m18028K();
        m18033P();
        if (this.f15814Q) {
            this.f15857u = 3;
        } else {
            this.f15857u = 0;
        }
    }

    /* renamed from: Y */
    public final void m18042Y(int i9, Canvas canvas) {
        ViewGroup viewGroup;
        int i10;
        int top;
        Drawable divider = getDivider();
        int dividerHeight = getDividerHeight();
        if (divider == null || dividerHeight == 0 || (viewGroup = (ViewGroup) getChildAt(i9 - getFirstVisiblePosition())) == null) {
            return;
        }
        int paddingLeft = getPaddingLeft();
        int width = getWidth() - getPaddingRight();
        int height = viewGroup.getChildAt(0).getHeight();
        if (i9 > this.f15846n) {
            top = viewGroup.getTop() + height;
            i10 = dividerHeight + top;
        } else {
            int bottom = viewGroup.getBottom() - height;
            int i11 = bottom - dividerHeight;
            i10 = bottom;
            top = i11;
        }
        canvas.save();
        canvas.clipRect(paddingLeft, top, width, i10);
        divider.setBounds(paddingLeft, top, width, i10);
        divider.draw(canvas);
        canvas.restore();
    }

    /* renamed from: Z */
    public final void m18043Z() {
        int i9;
        this.f15857u = 2;
        if (this.f15854r != null && (i9 = this.f15838j) >= 0 && i9 < getCount()) {
            int headerViewsCount = getHeaderViewsCount();
            this.f15854r.mo18081c(this.f15846n - headerViewsCount, this.f15838j - headerViewsCount);
        }
        m18036S();
        m18028K();
        m18033P();
        m18026I();
        if (this.f15814Q) {
            this.f15857u = 3;
        } else {
            this.f15857u = 0;
        }
    }

    /* renamed from: a0 */
    public final int m18044a0(int i9) {
        View view;
        if (i9 == this.f15846n) {
            return 0;
        }
        View childAt = getChildAt(i9 - getFirstVisiblePosition());
        if (childAt != null) {
            return m18045b0(i9, childAt, false);
        }
        int iM18086c = this.f15829e0.m18086c(i9);
        if (iM18086c != -1) {
            return iM18086c;
        }
        ListAdapter adapter = getAdapter();
        int itemViewType = adapter.getItemViewType(i9);
        int viewTypeCount = adapter.getViewTypeCount();
        if (viewTypeCount != this.f15862z.length) {
            this.f15862z = new View[viewTypeCount];
        }
        if (itemViewType >= 0) {
            View view2 = this.f15862z[itemViewType];
            if (view2 == null) {
                view = adapter.getView(i9, null, this);
                this.f15862z[itemViewType] = view;
            } else {
                view = adapter.getView(i9, view2, this);
            }
        } else {
            view = adapter.getView(i9, null, this);
        }
        int iM18045b0 = m18045b0(i9, view, true);
        this.f15829e0.m18084a(i9, iM18045b0);
        return iM18045b0;
    }

    /* renamed from: b0 */
    public final int m18045b0(int i9, View view, boolean z8) {
        int i10;
        if (i9 == this.f15846n) {
            return 0;
        }
        if (i9 >= getHeaderViewsCount() && i9 < getCount() - getFooterViewsCount()) {
            view = ((ViewGroup) view).getChildAt(0);
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null && (i10 = layoutParams.height) > 0) {
            return i10;
        }
        int height = view.getHeight();
        if (height != 0 && !z8) {
            return height;
        }
        m18051h0(view);
        return view.getMeasuredHeight();
    }

    /* renamed from: c0 */
    public final int m18046c0(int i9) {
        View childAt = getChildAt(i9 - getFirstVisiblePosition());
        return childAt != null ? childAt.getHeight() : m18030M(i9, m18044a0(i9));
    }

    /* renamed from: d0 */
    public final int m18047d0(int i9, int i10) {
        int headerViewsCount = getHeaderViewsCount();
        int footerViewsCount = getFooterViewsCount();
        if (i9 <= headerViewsCount || i9 >= getCount() - footerViewsCount) {
            return i10;
        }
        int dividerHeight = getDividerHeight();
        int i11 = this.f15859w - this.f15858v;
        int iM18044a0 = m18044a0(i9);
        int iM18046c0 = m18046c0(i9);
        int i12 = this.f15842l;
        int i13 = this.f15846n;
        if (i12 <= i13) {
            if (i9 != i12 || this.f15840k == i12) {
                if (i9 > i12 && i9 <= i13) {
                }
            } else if (i9 == i13) {
                i10 += iM18046c0;
                i11 = this.f15859w;
            } else {
                i10 += iM18046c0 - iM18044a0;
            }
            i10 -= i11;
        } else if (i9 > i13 && i9 <= this.f15840k) {
            i10 += i11;
        } else if (i9 == i12 && this.f15840k != i12) {
            i10 += iM18046c0 - iM18044a0;
        }
        return i9 <= i13 ? i10 + (((this.f15859w - dividerHeight) - m18044a0(i9 - 1)) / 2) : i10 + (((iM18044a0 - dividerHeight) - this.f15859w) / 2);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        float f9;
        super.dispatchDraw(canvas);
        if (this.f15857u != 0) {
            int i9 = this.f15840k;
            if (i9 != this.f15846n) {
                m18042Y(i9, canvas);
            }
            int i10 = this.f15842l;
            if (i10 != this.f15840k && i10 != this.f15846n) {
                m18042Y(i10, canvas);
            }
        }
        View view = this.f15822b;
        if (view != null) {
            int width = view.getWidth();
            int height = this.f15822b.getHeight();
            int i11 = this.f15824c.x;
            int width2 = getWidth();
            if (i11 < 0) {
                i11 = -i11;
            }
            if (i11 < width2) {
                float f10 = (width2 - i11) / width2;
                f9 = f10 * f10;
            } else {
                f9 = BitmapDescriptorFactory.HUE_RED;
            }
            int i12 = (int) (this.f15836i * 255.0f * f9);
            canvas.save();
            Point point = this.f15824c;
            canvas.translate(point.x, point.y);
            canvas.clipRect(0, 0, width, height);
            canvas.saveLayerAlpha(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, width, height, i12, 31);
            this.f15822b.draw(canvas);
            canvas.restore();
            canvas.restore();
        }
    }

    /* renamed from: e0 */
    public boolean m18048e0() {
        return this.f15856t || this.f15851p0;
    }

    /* renamed from: f0 */
    public boolean m18049f0() {
        return this.f15853q0;
    }

    /* renamed from: g0 */
    public final void m18050g0() {
        View view = this.f15822b;
        if (view != null) {
            m18051h0(view);
            int measuredHeight = this.f15822b.getMeasuredHeight();
            this.f15859w = measuredHeight;
            this.f15860x = measuredHeight / 2;
        }
    }

    public float getFloatAlpha() {
        return this.f15836i;
    }

    public ListAdapter getInputAdapter() {
        C4486e c4486e = this.f15820W;
        if (c4486e == null) {
            return null;
        }
        return c4486e.m18067a();
    }

    /* renamed from: h0 */
    public final void m18051h0(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new AbsListView.LayoutParams(-1, -2);
            view.setLayoutParams(layoutParams);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(this.f15861y, getListPaddingLeft() + getListPaddingRight(), layoutParams.width);
        int i9 = layoutParams.height;
        view.measure(childMeasureSpec, i9 > 0 ? View.MeasureSpec.makeMeasureSpec(i9, 1073741824) : View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    /* renamed from: i0 */
    public boolean m18052i0(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 1) {
            if (this.f15857u == 4) {
                m18060q0(false);
            }
            m18037T();
        } else if (action != 2) {
            if (action == 3) {
                if (this.f15857u == 4) {
                    m18032O();
                }
                m18037T();
            }
        } else if (this.f15856t) {
            m18035R((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        return true;
    }

    /* renamed from: j0 */
    public void m18053j0(int i9) {
        this.f15835h0 = false;
        m18034Q(i9, BitmapDescriptorFactory.HUE_RED);
    }

    /* renamed from: k0 */
    public void m18054k0(int i9, float f9) {
        int i10 = this.f15857u;
        if (i10 == 0 || i10 == 4) {
            if (i10 == 0) {
                int headerViewsCount = getHeaderViewsCount() + i9;
                this.f15846n = headerViewsCount;
                this.f15840k = headerViewsCount;
                this.f15842l = headerViewsCount;
                this.f15838j = headerViewsCount;
                View childAt = getChildAt(headerViewsCount - getFirstVisiblePosition());
                if (childAt != null) {
                    childAt.setVisibility(4);
                }
            }
            this.f15857u = 1;
            this.f15837i0 = f9;
            if (this.f15814Q) {
                int i11 = this.f15817T;
                if (i11 == 1) {
                    super.onTouchEvent(this.f15816S);
                } else if (i11 == 2) {
                    super.onInterceptTouchEvent(this.f15816S);
                }
            }
            C4496o c4496o = this.f15831f0;
            if (c4496o != null) {
                c4496o.m18088e();
            } else {
                m18041X(i9);
            }
        }
    }

    /* renamed from: l0 */
    public final void m18055l0(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action != 0) {
            this.f15811N = this.f15810M;
        }
        this.f15809L = (int) motionEvent.getX();
        int y8 = (int) motionEvent.getY();
        this.f15810M = y8;
        if (action == 0) {
            this.f15811N = y8;
        }
    }

    @Override // android.widget.ListView, android.widget.AbsListView
    public void layoutChildren() {
        super.layoutChildren();
        View view = this.f15822b;
        if (view != null) {
            if (view.isLayoutRequested() && !this.f15830f) {
                m18050g0();
            }
            View view2 = this.f15822b;
            view2.layout(0, 0, view2.getMeasuredWidth(), this.f15822b.getMeasuredHeight());
            this.f15830f = false;
        }
    }

    /* renamed from: m0 */
    public void m18056m0(float f9, float f10) {
        if (f10 > 0.5f) {
            this.f15800C = 0.5f;
        } else {
            this.f15800C = f10;
        }
        if (f9 > 0.5f) {
            this.f15799B = 0.5f;
        } else {
            this.f15799B = f9;
        }
        if (getHeight() != 0) {
            m18065v0();
        }
    }

    /* renamed from: n0 */
    public void m18057n0(int i9, String str, String str2, String str3, String str4) {
        this.f15849o0 = i9;
        this.f15839j0 = str;
        this.f15841k0 = str2;
        this.f15843l0 = str3;
        this.f15845m0 = str4;
        this.f15847n0 = true;
    }

    /* renamed from: o0 */
    public boolean m18058o0(int i9, int i10, int i11, int i12) {
        InterfaceC4494m interfaceC4494m;
        View viewMo18083b;
        if (!this.f15814Q || (interfaceC4494m = this.f15815R) == null || (viewMo18083b = interfaceC4494m.mo18083b(i9)) == null) {
            return false;
        }
        return m18059p0(i9, viewMo18083b, i10, i11, i12);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) throws Throwable {
        super.onDraw(canvas);
        if (this.f15821a0) {
            this.f15823b0.m18073a();
        }
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z8;
        if (!this.f15856t && !this.f15851p0) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        m18055l0(motionEvent);
        this.f15813P = true;
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            if (this.f15857u != 0) {
                this.f15827d0 = true;
                return true;
            }
            this.f15814Q = true;
        }
        if (this.f15822b != null) {
            z8 = true;
        } else {
            if (super.onInterceptTouchEvent(motionEvent)) {
                this.f15853q0 = true;
                z8 = true;
            } else {
                z8 = false;
            }
            if (action == 1 || action == 3) {
                m18037T();
            } else if (z8) {
                this.f15817T = 1;
            } else {
                this.f15817T = 2;
            }
        }
        if (action == 1 || action == 3) {
            this.f15814Q = false;
        }
        return z8;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    public void onMeasure(int i9, int i10) {
        super.onMeasure(i9, i10);
        View view = this.f15822b;
        if (view != null) {
            if (view.isLayoutRequested()) {
                m18050g0();
            }
            this.f15830f = true;
        }
        this.f15861y = i9;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    public void onSizeChanged(int i9, int i10, int i11, int i12) {
        super.onSizeChanged(i9, i10, i11, i12);
        m18065v0();
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z8 = false;
        if (this.f15827d0) {
            this.f15827d0 = false;
            return false;
        }
        if (!this.f15856t && !this.f15851p0) {
            return super.onTouchEvent(motionEvent);
        }
        boolean z9 = this.f15813P;
        this.f15813P = false;
        if (!z9) {
            m18055l0(motionEvent);
        }
        int i9 = this.f15857u;
        if (i9 == 4) {
            m18052i0(motionEvent);
            return true;
        }
        if (i9 == 0 && super.onTouchEvent(motionEvent)) {
            z8 = true;
        }
        int action = motionEvent.getAction() & 255;
        if (action == 1 || action == 3) {
            m18037T();
        } else if (z8) {
            this.f15817T = 1;
        }
        return z8;
    }

    /* renamed from: p0 */
    public boolean m18059p0(int i9, View view, int i10, int i11, int i12) {
        if (this.f15857u != 0 || !this.f15814Q || this.f15822b != null || view == null || (!this.f15856t && !this.f15851p0)) {
            return false;
        }
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        int headerViewsCount = i9 + getHeaderViewsCount();
        this.f15840k = headerViewsCount;
        this.f15842l = headerViewsCount;
        this.f15846n = headerViewsCount;
        this.f15838j = headerViewsCount;
        this.f15857u = 4;
        this.f15812O = i10 | 0;
        this.f15822b = view;
        m18050g0();
        this.f15848o = i11;
        this.f15850p = i12;
        Point point = this.f15824c;
        point.x = this.f15809L - i11;
        point.y = this.f15810M - i12;
        View childAt = getChildAt(this.f15846n - getFirstVisiblePosition());
        if (childAt != null) {
            childAt.setVisibility(4);
        }
        if (this.f15821a0) {
            this.f15823b0.m18075c();
        }
        int i13 = this.f15817T;
        if (i13 == 1) {
            super.onTouchEvent(this.f15816S);
        } else if (i13 == 2) {
            super.onInterceptTouchEvent(this.f15816S);
        }
        requestLayout();
        return true;
    }

    /* renamed from: q0 */
    public boolean m18060q0(boolean z8) {
        this.f15835h0 = false;
        return m18061r0(z8, BitmapDescriptorFactory.HUE_RED);
    }

    /* renamed from: r0 */
    public boolean m18061r0(boolean z8, float f9) throws Throwable {
        if (this.f15822b == null) {
            return false;
        }
        this.f15798A.m18072d(true);
        if (z8) {
            m18034Q(this.f15846n - getHeaderViewsCount(), f9);
        } else {
            C4492k c4492k = this.f15833g0;
            if (c4492k != null) {
                c4492k.m18088e();
            } else {
                m18043Z();
            }
        }
        if (this.f15821a0) {
            this.f15823b0.m18076d();
        }
        return true;
    }

    @Override // android.widget.AbsListView, android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.f15825c0) {
            return;
        }
        super.requestLayout();
    }

    /* renamed from: s0 */
    public boolean m18062s0(boolean z8, float f9) {
        this.f15835h0 = true;
        return m18061r0(z8, f9);
    }

    public void setDragEnabled(boolean z8) {
        this.f15856t = z8;
    }

    public void setDragListener(InterfaceC4487f interfaceC4487f) {
        this.f15852q = interfaceC4487f;
    }

    public void setDragScrollProfile(InterfaceC4488g interfaceC4488g) {
        if (interfaceC4488g != null) {
            this.f15808K = interfaceC4488g;
        }
    }

    public void setDragScrollStart(float f9) {
        m18056m0(f9, f9);
    }

    public void setDragSortListener(InterfaceC4490i interfaceC4490i) {
        setDropListener(interfaceC4490i);
        setDragListener(interfaceC4490i);
        setRemoveListener(interfaceC4490i);
    }

    public void setDropListener(InterfaceC4493l interfaceC4493l) {
        this.f15854r = interfaceC4493l;
    }

    public void setFloatAlpha(float f9) {
        this.f15836i = f9;
    }

    public void setFloatViewManager(InterfaceC4494m interfaceC4494m) {
        this.f15815R = interfaceC4494m;
    }

    public void setMaxScrollSpeed(float f9) {
        this.f15807J = f9;
    }

    public void setRemoveListener(InterfaceC4497p interfaceC4497p) {
        this.f15855s = interfaceC4497p;
    }

    /* renamed from: t0 */
    public final void m18063t0() {
        int i9;
        int i10;
        if (this.f15815R != null) {
            this.f15826d.set(this.f15809L, this.f15810M);
            this.f15815R.mo3429c(this.f15822b, this.f15824c, this.f15826d);
        }
        Point point = this.f15824c;
        int i11 = point.x;
        int i12 = point.y;
        int paddingLeft = getPaddingLeft();
        int i13 = this.f15812O;
        if (((i13 & 1) == 0 && i11 > paddingLeft) || ((i13 & 2) == 0 && i11 < paddingLeft)) {
            this.f15824c.x = paddingLeft;
        }
        int headerViewsCount = getHeaderViewsCount();
        int footerViewsCount = getFooterViewsCount();
        int firstVisiblePosition = getFirstVisiblePosition();
        int lastVisiblePosition = getLastVisiblePosition();
        int paddingTop = getPaddingTop();
        if (firstVisiblePosition < headerViewsCount) {
            paddingTop = getChildAt((headerViewsCount - firstVisiblePosition) - 1).getBottom();
        }
        if ((this.f15812O & 8) == 0 && firstVisiblePosition <= (i10 = this.f15846n)) {
            paddingTop = Math.max(getChildAt(i10 - firstVisiblePosition).getTop(), paddingTop);
        }
        int height = getHeight() - getPaddingBottom();
        if (lastVisiblePosition >= (getCount() - footerViewsCount) - 1) {
            height = getChildAt(((getCount() - footerViewsCount) - 1) - firstVisiblePosition).getBottom();
        }
        if ((this.f15812O & 4) == 0 && lastVisiblePosition >= (i9 = this.f15846n)) {
            height = Math.min(getChildAt(i9 - firstVisiblePosition).getBottom(), height);
        }
        if (i12 < paddingTop) {
            this.f15824c.y = paddingTop;
        } else {
            int i14 = this.f15859w;
            if (i12 + i14 > height) {
                this.f15824c.y = height - i14;
            }
        }
        this.f15828e = this.f15824c.y + this.f15860x;
    }

    /* renamed from: u0 */
    public final boolean m18064u0() {
        int i9;
        int i10;
        int firstVisiblePosition = getFirstVisiblePosition();
        int count = this.f15840k;
        View childAt = getChildAt(count - firstVisiblePosition);
        if (childAt == null) {
            count = (getChildCount() / 2) + firstVisiblePosition;
            childAt = getChildAt(count - firstVisiblePosition);
        }
        int top = childAt.getTop();
        int height = childAt.getHeight();
        int iM18047d0 = m18047d0(count, top);
        int dividerHeight = getDividerHeight();
        if (this.f15828e < iM18047d0) {
            while (count >= 0) {
                count--;
                int iM18046c0 = m18046c0(count);
                if (count == 0) {
                    i9 = (top - dividerHeight) - iM18046c0;
                    int i11 = iM18047d0;
                    iM18047d0 = i9;
                    i10 = i11;
                    break;
                }
                top -= iM18046c0 + dividerHeight;
                int iM18047d02 = m18047d0(count, top);
                if (this.f15828e >= iM18047d02) {
                    i10 = iM18047d0;
                    iM18047d0 = iM18047d02;
                    break;
                }
                iM18047d0 = iM18047d02;
            }
            i10 = iM18047d0;
        } else {
            int count2 = getCount();
            while (count < count2) {
                if (count == count2 - 1) {
                    i9 = top + dividerHeight + height;
                    int i112 = iM18047d0;
                    iM18047d0 = i9;
                    i10 = i112;
                    break;
                }
                top += height + dividerHeight;
                int i12 = count + 1;
                int iM18046c02 = m18046c0(i12);
                int iM18047d03 = m18047d0(i12, top);
                if (this.f15828e < iM18047d03) {
                    i10 = iM18047d0;
                    iM18047d0 = iM18047d03;
                    break;
                }
                count = i12;
                height = iM18046c02;
                iM18047d0 = iM18047d03;
            }
            i10 = iM18047d0;
        }
        int headerViewsCount = getHeaderViewsCount();
        int footerViewsCount = getFooterViewsCount();
        int i13 = this.f15840k;
        int i14 = this.f15842l;
        float f9 = this.f15819V;
        if (this.f15844m) {
            int iAbs = Math.abs(iM18047d0 - i10);
            int i15 = this.f15828e;
            if (i15 < iM18047d0) {
                int i16 = iM18047d0;
                iM18047d0 = i10;
                i10 = i16;
            }
            int i17 = (int) (this.f15818U * 0.5f * iAbs);
            float f10 = i17;
            int i18 = iM18047d0 + i17;
            int i19 = i10 - i17;
            if (i15 < i18) {
                this.f15840k = count - 1;
                this.f15842l = count;
                this.f15819V = ((i18 - i15) * 0.5f) / f10;
            } else if (i15 < i19) {
                this.f15840k = count;
                this.f15842l = count;
            } else {
                this.f15840k = count;
                this.f15842l = count + 1;
                this.f15819V = (((i10 - i15) / f10) + 1.0f) * 0.5f;
            }
        } else {
            this.f15840k = count;
            this.f15842l = count;
        }
        if (this.f15840k < headerViewsCount) {
            this.f15840k = headerViewsCount;
            this.f15842l = headerViewsCount;
            count = headerViewsCount;
        } else if (this.f15842l >= getCount() - footerViewsCount) {
            count = (getCount() - footerViewsCount) - 1;
            this.f15840k = count;
            this.f15842l = count;
        }
        boolean z8 = (this.f15840k == i13 && this.f15842l == i14 && this.f15819V == f9) ? false : true;
        int i20 = this.f15838j;
        if (count == i20) {
            return z8;
        }
        InterfaceC4487f interfaceC4487f = this.f15852q;
        if (interfaceC4487f != null) {
            interfaceC4487f.mo18068a(i20 - headerViewsCount, count - headerViewsCount);
        }
        this.f15838j = count;
        return true;
    }

    /* renamed from: v0 */
    public final void m18065v0() {
        int paddingTop = getPaddingTop();
        float height = (getHeight() - paddingTop) - getPaddingBottom();
        float f9 = paddingTop;
        float f10 = (this.f15799B * height) + f9;
        this.f15804G = f10;
        float f11 = ((1.0f - this.f15800C) * height) + f9;
        this.f15803F = f11;
        this.f15801D = (int) f10;
        this.f15802E = (int) f11;
        this.f15805H = f10 - f9;
        this.f15806I = (paddingTop + r1) - f11;
    }

    @Override // android.widget.AdapterView
    public void setAdapter(ListAdapter listAdapter) {
        if (listAdapter != null) {
            this.f15820W = new C4486e(listAdapter);
            listAdapter.registerDataSetObserver(this.f15832g);
            if (listAdapter instanceof InterfaceC4493l) {
                setDropListener((InterfaceC4493l) listAdapter);
            }
            if (listAdapter instanceof InterfaceC4487f) {
                setDragListener((InterfaceC4487f) listAdapter);
            }
            if (listAdapter instanceof InterfaceC4497p) {
                setRemoveListener((InterfaceC4497p) listAdapter);
            }
        } else {
            this.f15820W = null;
        }
        super.setAdapter((ListAdapter) this.f15820W);
    }
}
