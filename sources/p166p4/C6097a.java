package p166p4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.cyberlink.you.widgetpool.common.C3241a;
import com.cyberlink.you.widgetpool.panel.coloreffectpanel.C3243a;
import com.cyberlink.you.widgetpool.panel.coloreffectpanel.ColorEffectUtility;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.List;

/* renamed from: p4.a */
/* loaded from: classes.dex */
public class C6097a extends ArrayAdapter<Object> {

    /* renamed from: f */
    public static LruCache<String, Bitmap> f20699f = new LruCache<>(30);

    /* renamed from: b */
    public int f20700b;

    /* renamed from: c */
    public Context f20701c;

    /* renamed from: d */
    public C3243a f20702d;

    /* renamed from: e */
    public boolean f20703e;

    public C6097a(Context context, List<Object> list, C3243a c3243a) {
        super(context, 0, list);
        this.f20700b = -1;
        this.f20703e = true;
        this.f20701c = context;
        this.f20702d = c3243a;
    }

    /* renamed from: a */
    public void m23395a() {
        f20699f.evictAll();
    }

    /* renamed from: b */
    public int m23396b() {
        return this.f20700b;
    }

    /* renamed from: c */
    public String m23397c(int i9, int i10) {
        return this.f20702d.m17332e(i9, i10);
    }

    /* renamed from: d */
    public void m23398d(int i9) {
        if (i9 <= -1 || i9 >= getCount()) {
            this.f20700b = 0;
        } else {
            this.f20700b = i9;
        }
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x009e: MOVE (r2 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:47:0x009e */
    /* JADX WARN: Removed duplicated region for block: B:39:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0098  */
    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public View getView(int i9, View view, ViewGroup viewGroup) throws Throwable {
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2;
        OutOfMemoryError e9;
        IOException e10;
        C3241a c3241a = view != null ? (C3241a) view : new C3241a(this.f20701c);
        int iM17317d = ColorEffectUtility.m17317d(i9);
        String strM17333f = this.f20702d.m17333f(iM17317d, i9);
        Bitmap bitmapDecodeFile = f20699f.get(strM17333f);
        if (bitmapDecodeFile == null || bitmapDecodeFile.isRecycled()) {
            BufferedInputStream bufferedInputStream3 = null;
            try {
            } catch (Throwable th) {
                th = th;
                bufferedInputStream3 = bufferedInputStream;
            }
            try {
                try {
                    if (strM17333f.indexOf("asset://") == 0) {
                        bufferedInputStream2 = new BufferedInputStream(this.f20701c.getAssets().open(strM17333f.substring(8)));
                        try {
                            bitmapDecodeFile = BitmapFactory.decodeStream(bufferedInputStream2);
                            bufferedInputStream3 = bufferedInputStream2;
                        } catch (IOException e11) {
                            e10 = e11;
                            e10.printStackTrace();
                            if (bufferedInputStream2 != null) {
                                bufferedInputStream2.close();
                            }
                            if (bitmapDecodeFile != null) {
                            }
                            c3241a.setTag(Integer.valueOf(i9));
                            c3241a.setImage(bitmapDecodeFile);
                            c3241a.setName(this.f20702d.m17332e(iM17317d, i9));
                            c3241a.setCheckd(this.f20700b == i9);
                            return c3241a;
                        } catch (OutOfMemoryError e12) {
                            e9 = e12;
                            e9.printStackTrace();
                            if (bufferedInputStream2 != null) {
                                bufferedInputStream2.close();
                            }
                            if (bitmapDecodeFile != null) {
                            }
                            c3241a.setTag(Integer.valueOf(i9));
                            c3241a.setImage(bitmapDecodeFile);
                            c3241a.setName(this.f20702d.m17332e(iM17317d, i9));
                            c3241a.setCheckd(this.f20700b == i9);
                            return c3241a;
                        }
                    } else {
                        bitmapDecodeFile = BitmapFactory.decodeFile(strM17333f);
                    }
                } catch (IOException e13) {
                    e13.printStackTrace();
                }
            } catch (IOException e14) {
                bufferedInputStream2 = null;
                e10 = e14;
            } catch (OutOfMemoryError e15) {
                bufferedInputStream2 = null;
                e9 = e15;
            } catch (Throwable th2) {
                th = th2;
                if (bufferedInputStream3 != null) {
                    try {
                        bufferedInputStream3.close();
                    } catch (IOException e16) {
                        e16.printStackTrace();
                    }
                }
                throw th;
            }
            if (bufferedInputStream3 != null) {
                bufferedInputStream3.close();
            }
            if (bitmapDecodeFile != null) {
                f20699f.put(strM17333f, bitmapDecodeFile);
            }
        }
        c3241a.setTag(Integer.valueOf(i9));
        c3241a.setImage(bitmapDecodeFile);
        c3241a.setName(this.f20702d.m17332e(iM17317d, i9));
        c3241a.setCheckd(this.f20700b == i9);
        return c3241a;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i9) {
        return this.f20703e;
    }
}
