package p086h4;

import android.content.Context;
import android.content.SharedPreferences;
import com.cyberlink.you.sticker.StickerObj;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/* renamed from: h4.f */
/* loaded from: classes.dex */
public class C5002f {

    /* renamed from: a */
    public SharedPreferences f17236a;

    /* renamed from: h4.f$a */
    public class a implements Comparable<a> {

        /* renamed from: b */
        public String f17237b;

        /* renamed from: c */
        public long f17238c;

        public a(String str, long j9) {
            this.f17237b = str;
            this.f17238c = j9;
        }

        @Override // java.lang.Comparable
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public int compareTo(a aVar) {
            return (int) Math.signum(aVar.f17238c - this.f17238c);
        }

        /* renamed from: c */
        public Long m19434c() {
            return Long.valueOf(Long.parseLong(this.f17237b));
        }
    }

    public C5002f(Context context) {
        this.f17236a = m19430e(context);
    }

    /* renamed from: a */
    public void m19426a(StickerObj stickerObj) {
        String string = Long.toString(stickerObj.m16285j());
        long jM19427b = m19427b();
        SharedPreferences.Editor editorEdit = this.f17236a.edit();
        editorEdit.putLong(string, jM19427b);
        editorEdit.apply();
    }

    /* renamed from: b */
    public long m19427b() {
        return new Date().getTime();
    }

    /* renamed from: c */
    public List<Long> m19428c() {
        Map<String, ?> all = this.f17236a.getAll();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            arrayList.add(new a(entry.getKey(), ((Long) entry.getValue()).longValue()));
        }
        Collections.sort(arrayList);
        int iM19429d = m19429d(arrayList.size());
        SharedPreferences.Editor editorEdit = this.f17236a.edit();
        for (int i9 = 0; i9 < arrayList.size(); i9++) {
            if (i9 < iM19429d) {
                arrayList2.add(((a) arrayList.get(i9)).m19434c());
            } else {
                editorEdit.remove(((a) arrayList.get(i9)).f17237b);
            }
        }
        editorEdit.apply();
        return arrayList2;
    }

    /* renamed from: d */
    public final int m19429d(int i9) {
        return Math.min(i9, 32);
    }

    /* renamed from: e */
    public SharedPreferences m19430e(Context context) {
        return context.getSharedPreferences("LastUsedStickerPref", 0);
    }

    /* renamed from: f */
    public boolean m19431f() {
        return this.f17236a.getAll().isEmpty();
    }
}
