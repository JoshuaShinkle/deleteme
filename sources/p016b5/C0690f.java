package p016b5;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/* renamed from: b5.f */
/* loaded from: classes2.dex */
public class C0690f extends AbstractC0689e {

    /* renamed from: p */
    public int[] f3338p;

    /* renamed from: q */
    public int[] f3339q;

    /* renamed from: r */
    public int f3340r;

    /* renamed from: s */
    public String[] f3341s;

    public C0690f(Context context, int i9, Cursor cursor, String[] strArr, int[] iArr, int i10) {
        super(context, i9, cursor, i10);
        this.f3340r = -1;
        this.f3339q = iArr;
        this.f3341s = strArr;
        m3442p(cursor, strArr);
    }

    @Override // p062f0.AbstractC4774a, p062f0.C4775b.a
    public CharSequence convertToString(Cursor cursor) {
        int i9 = this.f3340r;
        return i9 > -1 ? cursor.getString(i9) : super.convertToString(cursor);
    }

    @Override // p062f0.AbstractC4774a
    /* renamed from: f */
    public void mo918f(View view, Context context, Cursor cursor) {
        int[] iArr = this.f3339q;
        int length = iArr.length;
        int[] iArr2 = this.f3338p;
        for (int i9 = 0; i9 < length; i9++) {
            View viewFindViewById = view.findViewById(iArr[i9]);
            if (viewFindViewById != null) {
                String string = cursor.getString(iArr2[i9]);
                if (string == null) {
                    string = "";
                }
                if (viewFindViewById instanceof TextView) {
                    m3444r((TextView) viewFindViewById, string);
                } else {
                    if (!(viewFindViewById instanceof ImageView)) {
                        throw new IllegalStateException(viewFindViewById.getClass().getName() + " is not a  view that can be bounds by this SimpleCursorAdapter");
                    }
                    m3443q((ImageView) viewFindViewById, string);
                }
            }
        }
    }

    @Override // com.mobeta.android.dslv.AbstractC4499a, p062f0.AbstractC4774a
    /* renamed from: k */
    public Cursor mo3441k(Cursor cursor) {
        m3442p(cursor, this.f3341s);
        return super.mo3441k(cursor);
    }

    /* renamed from: p */
    public final void m3442p(Cursor cursor, String[] strArr) {
        if (cursor == null) {
            this.f3338p = null;
            return;
        }
        int length = strArr.length;
        int[] iArr = this.f3338p;
        if (iArr == null || iArr.length != length) {
            this.f3338p = new int[length];
        }
        for (int i9 = 0; i9 < length; i9++) {
            this.f3338p[i9] = cursor.getColumnIndexOrThrow(strArr[i9]);
        }
    }

    /* renamed from: q */
    public void m3443q(ImageView imageView, String str) {
        try {
            imageView.setImageResource(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            imageView.setImageURI(Uri.parse(str));
        }
    }

    /* renamed from: r */
    public void m3444r(TextView textView, String str) {
        textView.setText(str);
    }
}
