package p062f0;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;
import p062f0.C4775b;

/* renamed from: f0.a */
/* loaded from: classes.dex */
public abstract class AbstractC4774a extends BaseAdapter implements Filterable, C4775b.a {

    /* renamed from: b */
    public boolean f16604b;

    /* renamed from: c */
    public boolean f16605c;

    /* renamed from: d */
    public Cursor f16606d;

    /* renamed from: e */
    public Context f16607e;

    /* renamed from: f */
    public int f16608f;

    /* renamed from: g */
    public a f16609g;

    /* renamed from: h */
    public DataSetObserver f16610h;

    /* renamed from: i */
    public C4775b f16611i;

    /* renamed from: j */
    public FilterQueryProvider f16612j;

    /* renamed from: f0.a$a */
    public class a extends ContentObserver {
        public a() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z8) {
            AbstractC4774a.this.m19008j();
        }
    }

    /* renamed from: f0.a$b */
    public class b extends DataSetObserver {
        public b() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            AbstractC4774a abstractC4774a = AbstractC4774a.this;
            abstractC4774a.f16604b = true;
            abstractC4774a.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            AbstractC4774a abstractC4774a = AbstractC4774a.this;
            abstractC4774a.f16604b = false;
            abstractC4774a.notifyDataSetInvalidated();
        }
    }

    public AbstractC4774a(Context context, Cursor cursor, boolean z8) {
        m19007g(context, cursor, z8 ? 1 : 2);
    }

    /* renamed from: b */
    public void mo916b(Cursor cursor) {
        Cursor cursorMo3441k = mo3441k(cursor);
        if (cursorMo3441k != null) {
            cursorMo3441k.close();
        }
    }

    public CharSequence convertToString(Cursor cursor) {
        return cursor == null ? "" : cursor.toString();
    }

    @Override // p062f0.C4775b.a
    /* renamed from: d */
    public Cursor mo19006d() {
        return this.f16606d;
    }

    /* renamed from: e */
    public Cursor mo917e(CharSequence charSequence) {
        FilterQueryProvider filterQueryProvider = this.f16612j;
        return filterQueryProvider != null ? filterQueryProvider.runQuery(charSequence) : this.f16606d;
    }

    /* renamed from: f */
    public abstract void mo918f(View view, Context context, Cursor cursor);

    /* renamed from: g */
    public void m19007g(Context context, Cursor cursor, int i9) {
        if ((i9 & 1) == 1) {
            i9 |= 2;
            this.f16605c = true;
        } else {
            this.f16605c = false;
        }
        boolean z8 = cursor != null;
        this.f16606d = cursor;
        this.f16604b = z8;
        this.f16607e = context;
        this.f16608f = z8 ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i9 & 2) == 2) {
            this.f16609g = new a();
            this.f16610h = new b();
        } else {
            this.f16609g = null;
            this.f16610h = null;
        }
        if (z8) {
            a aVar = this.f16609g;
            if (aVar != null) {
                cursor.registerContentObserver(aVar);
            }
            DataSetObserver dataSetObserver = this.f16610h;
            if (dataSetObserver != null) {
                cursor.registerDataSetObserver(dataSetObserver);
            }
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        Cursor cursor;
        if (!this.f16604b || (cursor = this.f16606d) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i9, View view, ViewGroup viewGroup) {
        if (!this.f16604b) {
            return null;
        }
        this.f16606d.moveToPosition(i9);
        if (view == null) {
            view = mo3440h(this.f16607e, this.f16606d, viewGroup);
        }
        mo918f(view, this.f16607e, this.f16606d);
        return view;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.f16611i == null) {
            this.f16611i = new C4775b(this);
        }
        return this.f16611i;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i9) {
        Cursor cursor;
        if (!this.f16604b || (cursor = this.f16606d) == null) {
            return null;
        }
        cursor.moveToPosition(i9);
        return this.f16606d;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i9) {
        Cursor cursor;
        if (this.f16604b && (cursor = this.f16606d) != null && cursor.moveToPosition(i9)) {
            return this.f16606d.getLong(this.f16608f);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i9, View view, ViewGroup viewGroup) {
        if (!this.f16604b) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (this.f16606d.moveToPosition(i9)) {
            if (view == null) {
                view = mo919i(this.f16607e, this.f16606d, viewGroup);
            }
            mo918f(view, this.f16607e, this.f16606d);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i9);
    }

    /* renamed from: h */
    public abstract View mo3440h(Context context, Cursor cursor, ViewGroup viewGroup);

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    /* renamed from: i */
    public abstract View mo919i(Context context, Cursor cursor, ViewGroup viewGroup);

    /* renamed from: j */
    public void m19008j() {
        Cursor cursor;
        if (!this.f16605c || (cursor = this.f16606d) == null || cursor.isClosed()) {
            return;
        }
        this.f16604b = this.f16606d.requery();
    }

    /* renamed from: k */
    public Cursor mo3441k(Cursor cursor) {
        Cursor cursor2 = this.f16606d;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            a aVar = this.f16609g;
            if (aVar != null) {
                cursor2.unregisterContentObserver(aVar);
            }
            DataSetObserver dataSetObserver = this.f16610h;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f16606d = cursor;
        if (cursor != null) {
            a aVar2 = this.f16609g;
            if (aVar2 != null) {
                cursor.registerContentObserver(aVar2);
            }
            DataSetObserver dataSetObserver2 = this.f16610h;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.f16608f = cursor.getColumnIndexOrThrow("_id");
            this.f16604b = true;
            notifyDataSetChanged();
        } else {
            this.f16608f = -1;
            this.f16604b = false;
            notifyDataSetInvalidated();
        }
        return cursor2;
    }

    public AbstractC4774a(Context context, Cursor cursor, int i9) {
        m19007g(context, cursor, i9);
    }
}
