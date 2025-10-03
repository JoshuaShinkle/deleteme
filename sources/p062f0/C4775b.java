package p062f0;

import android.database.Cursor;
import android.widget.Filter;

/* renamed from: f0.b */
/* loaded from: classes.dex */
public class C4775b extends Filter {

    /* renamed from: a */
    public a f16615a;

    /* renamed from: f0.b$a */
    public interface a {
        /* renamed from: b */
        void mo916b(Cursor cursor);

        CharSequence convertToString(Cursor cursor);

        /* renamed from: d */
        Cursor mo19006d();

        /* renamed from: e */
        Cursor mo917e(CharSequence charSequence);
    }

    public C4775b(a aVar) {
        this.f16615a = aVar;
    }

    @Override // android.widget.Filter
    public CharSequence convertResultToString(Object obj) {
        return this.f16615a.convertToString((Cursor) obj);
    }

    @Override // android.widget.Filter
    public Filter.FilterResults performFiltering(CharSequence charSequence) {
        Cursor cursorMo917e = this.f16615a.mo917e(charSequence);
        Filter.FilterResults filterResults = new Filter.FilterResults();
        if (cursorMo917e != null) {
            filterResults.count = cursorMo917e.getCount();
            filterResults.values = cursorMo917e;
        } else {
            filterResults.count = 0;
            filterResults.values = null;
        }
        return filterResults;
    }

    @Override // android.widget.Filter
    public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        Cursor cursorMo19006d = this.f16615a.mo19006d();
        Object obj = filterResults.values;
        if (obj == null || obj == cursorMo19006d) {
            return;
        }
        this.f16615a.mo916b((Cursor) obj);
    }
}
