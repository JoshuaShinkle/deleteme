package p224w.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.perfectcorp.utility.C4509d;
import p047d5.C4677a;
import p047d5.C4678b;
import p047d5.C4680d;
import p047d5.C4681e;
import p131l8.AbstractDialogC5301a;

/* loaded from: classes3.dex */
public class SimpleMessageDialog extends AbstractDialogC5301a {

    /* renamed from: l */
    public static int[] f21824l = {C4680d.MessageDialogButton1, C4680d.MessageDialogButton2, C4680d.MessageDialogButton3};

    /* renamed from: d */
    public final C6500c[] f21825d;

    /* renamed from: e */
    public final LayoutType f21826e;

    /* renamed from: f */
    public final int f21827f;

    /* renamed from: g */
    public final String f21828g;

    /* renamed from: h */
    public final String f21829h;

    /* renamed from: i */
    public final int f21830i;

    /* renamed from: j */
    public final int f21831j;

    /* renamed from: k */
    public final boolean f21832k;

    public enum LayoutType {
        LAYOUT_TYPE_VERTICAL_BUTTON(C4681e.simple_message_dialog);


        /* renamed from: id */
        public final int f21835id;

        LayoutType(int i9) {
            this.f21835id = i9;
        }
    }

    /* renamed from: w.dialogs.SimpleMessageDialog$a */
    public class ViewOnClickListenerC6498a implements View.OnClickListener {

        /* renamed from: b */
        public final /* synthetic */ C6500c f21836b;

        /* renamed from: c */
        public final /* synthetic */ int f21837c;

        public ViewOnClickListenerC6498a(C6500c c6500c, int i9) {
            this.f21836b = c6500c;
            this.f21837c = i9;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SimpleMessageDialog.this.dismiss();
            View.OnClickListener onClickListener = this.f21836b.f21852b;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
            DialogInterface.OnClickListener onClickListener2 = this.f21836b.f21853c;
            if (onClickListener2 != null) {
                onClickListener2.onClick(SimpleMessageDialog.this, this.f21837c);
            }
        }
    }

    /* renamed from: w.dialogs.SimpleMessageDialog$b */
    public static class C6499b {

        /* renamed from: a */
        public final Context f21839a;

        /* renamed from: b */
        public boolean f21840b;

        /* renamed from: c */
        public LayoutType f21841c;

        /* renamed from: d */
        public C6500c[] f21842d = new C6500c[3];

        /* renamed from: e */
        public int f21843e;

        /* renamed from: f */
        public String f21844f;

        /* renamed from: g */
        public String f21845g;

        /* renamed from: h */
        public int f21846h;

        /* renamed from: i */
        public int f21847i;

        /* renamed from: j */
        public boolean f21848j;

        public C6499b(Context context, boolean z8) {
            this.f21839a = context;
            this.f21840b = z8;
        }

        /* renamed from: k */
        public SimpleMessageDialog m24864k() {
            return new SimpleMessageDialog(this, null);
        }

        /* renamed from: l */
        public C6499b m24865l(boolean z8) {
            this.f21848j = z8;
            return this;
        }

        /* renamed from: m */
        public C6499b m24866m(C6500c c6500c) {
            this.f21842d[0] = c6500c;
            return this;
        }

        /* renamed from: n */
        public C6499b m24867n(C6500c c6500c) {
            this.f21842d[1] = c6500c;
            return this;
        }

        /* renamed from: o */
        public C6499b m24868o(LayoutType layoutType) {
            this.f21841c = layoutType;
            return this;
        }

        /* renamed from: p */
        public C6499b m24869p(String str, int i9) {
            this.f21845g = str;
            this.f21847i = i9;
            return this;
        }

        /* renamed from: q */
        public C6499b m24870q(String str, int i9) {
            this.f21844f = str;
            this.f21846h = i9;
            return this;
        }
    }

    public /* synthetic */ SimpleMessageDialog(C6499b c6499b, ViewOnClickListenerC6498a viewOnClickListenerC6498a) {
        this(c6499b);
    }

    @Override // p131l8.AbstractDialogC5301a
    /* renamed from: a */
    public int mo20690a() {
        return this.f21826e.f21835id;
    }

    @Override // p131l8.AbstractDialogC5301a
    /* renamed from: b */
    public void mo20691b(View view) {
        super.mo20691b(view);
        if (this.f21832k) {
            int i9 = C4680d.MessageDialogEditText;
            if (findViewById(i9) != null) {
                findViewById(i9).setVisibility(0);
            }
        }
        SparseArray sparseArray = new SparseArray();
        for (int i10 : f21824l) {
            View viewFindViewById = findViewById(i10);
            viewFindViewById.setVisibility(8);
            sparseArray.put(i10, viewFindViewById);
        }
        int i11 = 0;
        while (true) {
            C6500c[] c6500cArr = this.f21825d;
            if (i11 >= c6500cArr.length) {
                return;
            }
            int[] iArr = f21824l;
            if (i11 >= iArr.length) {
                throw new IllegalArgumentException("Too many buttons !");
            }
            C6500c c6500c = c6500cArr[i11];
            if (c6500c != null) {
                View view2 = (View) sparseArray.get(iArr[i11]);
                view2.setClickable(c6500c.f21854d);
                view2.setVisibility(0);
                if (c6500c.f21854d) {
                    view2.setOnClickListener(new ViewOnClickListenerC6498a(c6500c, i11));
                }
                TextView textView = (TextView) findViewById(f21824l[i11]);
                textView.setText(c6500c.f21851a);
                textView.setTextColor(c6500c.f21855e);
                TextView textView2 = (TextView) findViewById(C4680d.MessageDialogTitleText);
                if (C4509d.m18119a(this.f21828g)) {
                    textView2.setVisibility(8);
                } else {
                    textView2.setVisibility(0);
                    textView2.setText(this.f21828g);
                    textView2.setTextColor(this.f21830i);
                }
                TextView textView3 = (TextView) findViewById(C4680d.MessageDialogMessageText);
                if (textView3 != null) {
                    if (TextUtils.isEmpty(this.f21829h)) {
                        textView3.setVisibility(8);
                    } else {
                        textView3.setVisibility(0);
                        textView3.setText(this.f21829h);
                        textView3.setTextColor(this.f21831j);
                    }
                }
            }
            i11++;
        }
    }

    /* renamed from: d */
    public String m24853d() {
        int i9 = C4680d.MessageDialogEditText;
        return findViewById(i9) != null ? ((EditText) findViewById(i9)).getText().toString() : "";
    }

    public SimpleMessageDialog(C6499b c6499b) {
        super(c6499b.f21839a, c6499b.f21840b);
        this.f21825d = c6499b.f21842d;
        this.f21826e = c6499b.f21841c;
        this.f21827f = c6499b.f21843e;
        this.f21828g = c6499b.f21844f;
        this.f21830i = c6499b.f21846h;
        this.f21829h = c6499b.f21845g;
        this.f21831j = c6499b.f21847i;
        this.f21832k = c6499b.f21848j;
    }

    /* renamed from: w.dialogs.SimpleMessageDialog$c */
    public static class C6500c {

        /* renamed from: f */
        public static final int f21849f = C4677a.m18710n().getResources().getColor(C4678b.ycl_text_style_d);

        /* renamed from: g */
        public static final int f21850g = C4677a.m18710n().getResources().getColor(C4678b.ycl_text_style_h);

        /* renamed from: a */
        public final String f21851a;

        /* renamed from: b */
        public final View.OnClickListener f21852b;

        /* renamed from: c */
        public final DialogInterface.OnClickListener f21853c;

        /* renamed from: d */
        public final boolean f21854d;

        /* renamed from: e */
        public final int f21855e;

        public C6500c(String str, View.OnClickListener onClickListener, boolean z8, int i9) {
            this.f21851a = str;
            this.f21852b = onClickListener;
            this.f21853c = null;
            this.f21854d = z8;
            this.f21855e = i9;
        }

        public C6500c(String str, DialogInterface.OnClickListener onClickListener, int i9) {
            this.f21851a = str;
            this.f21852b = null;
            this.f21853c = onClickListener;
            this.f21854d = true;
            this.f21855e = i9;
        }
    }
}
