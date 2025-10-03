package p116k4;

import android.widget.Filter;
import java.util.ArrayList;
import java.util.List;
import p209u2.C6386w;

/* renamed from: k4.l0 */
/* loaded from: classes.dex */
public abstract class AbstractC5161l0<T> extends Filter {

    /* renamed from: a */
    public C6386w f17689a = new C6386w();

    /* renamed from: a */
    public abstract List<T> mo3351a();

    /* renamed from: b */
    public Object mo13230b(String str) {
        return null;
    }

    /* renamed from: c */
    public abstract boolean mo3352c(T t8, String str, Object obj);

    /* renamed from: d */
    public final String m20104d(CharSequence charSequence) {
        return charSequence.toString().toLowerCase();
    }

    @Override // android.widget.Filter
    public Filter.FilterResults performFiltering(CharSequence charSequence) {
        String strM20104d = m20104d(charSequence);
        Filter.FilterResults filterResults = new Filter.FilterResults();
        ArrayList arrayList = new ArrayList();
        this.f17689a.m24533e();
        List<T> listMo3351a = mo3351a();
        Object objMo13230b = mo13230b(strM20104d);
        for (T t8 : listMo3351a) {
            if (mo3352c(t8, strM20104d, objMo13230b)) {
                arrayList.add(t8);
            }
        }
        this.f17689a.m24532d("performFiltering() : " + getClass().getSimpleName());
        filterResults.values = arrayList;
        filterResults.count = arrayList.size();
        return filterResults;
    }
}
