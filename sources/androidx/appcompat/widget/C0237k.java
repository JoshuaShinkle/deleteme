package androidx.appcompat.widget;

import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import p021c0.C0702h;

/* renamed from: androidx.appcompat.widget.k */
/* loaded from: classes.dex */
public final class C0237k {

    /* renamed from: a */
    public TextView f1121a;

    /* renamed from: b */
    public TextClassifier f1122b;

    public C0237k(TextView textView) {
        this.f1121a = (TextView) C0702h.m3468b(textView);
    }

    /* renamed from: a */
    public TextClassifier m909a() {
        TextClassifier textClassifier = this.f1122b;
        if (textClassifier != null) {
            return textClassifier;
        }
        TextClassificationManager textClassificationManager = (TextClassificationManager) this.f1121a.getContext().getSystemService(TextClassificationManager.class);
        return textClassificationManager != null ? textClassificationManager.getTextClassifier() : TextClassifier.NO_OP;
    }

    /* renamed from: b */
    public void m910b(TextClassifier textClassifier) {
        this.f1122b = textClassifier;
    }
}
