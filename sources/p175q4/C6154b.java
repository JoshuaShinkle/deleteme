package p175q4;

import android.content.Context;
import android.widget.TextView;

/* renamed from: q4.b */
/* loaded from: classes.dex */
public class C6154b extends C6161i {

    /* renamed from: d */
    public String f20782d;

    public C6154b(int i9, Context context, int i10, int i11, int i12) {
        super(new TextView(context), i12);
        this.f20782d = "";
        TextView textView = (TextView) this.f20796b;
        textView.setTextColor(i10);
        textView.setTextSize(0, i11);
        m23612b(i9);
    }

    /* renamed from: b */
    public void m23612b(int i9) {
        String str = "+" + i9;
        this.f20782d = str;
        ((TextView) this.f20796b).setText(str);
    }
}
