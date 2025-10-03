package p244y1;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: y1.b */
/* loaded from: classes.dex */
public class C6597b<T> extends ArrayAdapter<T> {

    /* renamed from: b */
    public boolean f22173b;

    /* renamed from: c */
    public Set<T> f22174c;

    /* renamed from: d */
    public List<T> f22175d;

    /* renamed from: e */
    public final List<T> f22176e;

    /* renamed from: f */
    public Filter f22177f;

    public C6597b(Context context, int i9, List<T> list) {
        super(context, i9, list);
        this.f22173b = false;
        this.f22174c = new HashSet();
        this.f22176e = list;
        this.f22175d = list;
    }

    /* renamed from: a */
    public List<T> m25232a() {
        return new ArrayList(this.f22174c);
    }

    /* renamed from: b */
    public boolean m25233b() {
        return !this.f22174c.isEmpty();
    }

    /* renamed from: c */
    public boolean m25234c() {
        return this.f22173b;
    }

    /* renamed from: d */
    public boolean m25235d(int i9) {
        return this.f22174c.contains(this.f22175d.get(i9));
    }

    /* renamed from: e */
    public void m25236e(boolean z8) {
        this.f22173b = z8;
        this.f22174c.clear();
    }

    /* renamed from: f */
    public void m25237f(int i9, boolean z8) {
        T t8 = this.f22175d.get(i9);
        if (t8 == null) {
            return;
        }
        if (z8) {
            this.f22174c.add(t8);
        } else {
            this.f22174c.remove(t8);
        }
        notifyDataSetChanged();
    }

    /* renamed from: g */
    public void m25238g(T t8, boolean z8) {
        if (z8 ? this.f22174c.add(t8) : this.f22174c.remove(t8)) {
            notifyDataSetChanged();
        }
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        return this.f22175d.size();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public T getItem(int i9) {
        return this.f22175d.get(i9);
    }
}
