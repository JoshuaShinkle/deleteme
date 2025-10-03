package androidx.appcompat.widget;

import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

/* renamed from: androidx.appcompat.widget.g */
/* loaded from: classes.dex */
public class C0229g {
    /* renamed from: a */
    public static InputConnection m845a(InputConnection inputConnection, EditorInfo editorInfo, View view) {
        if (inputConnection != null && editorInfo.hintText == null) {
            ViewParent parent = view.getParent();
            while (true) {
                if (!(parent instanceof View)) {
                    break;
                }
                if (parent instanceof InterfaceC0260v0) {
                    editorInfo.hintText = ((InterfaceC0260v0) parent).m1071a();
                    break;
                }
                parent = parent.getParent();
            }
        }
        return inputConnection;
    }
}
