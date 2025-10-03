package androidx.appcompat.app;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.widget.NestedScrollView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.ref.WeakReference;
import p010b.C0560a;
import p010b.C0565f;
import p010b.C0569j;
import p042d0.C4647u;

/* loaded from: classes.dex */
public class AlertController {

    /* renamed from: A */
    public NestedScrollView f176A;

    /* renamed from: C */
    public Drawable f178C;

    /* renamed from: D */
    public ImageView f179D;

    /* renamed from: E */
    public TextView f180E;

    /* renamed from: F */
    public TextView f181F;

    /* renamed from: G */
    public View f182G;

    /* renamed from: H */
    public ListAdapter f183H;

    /* renamed from: J */
    public int f185J;

    /* renamed from: K */
    public int f186K;

    /* renamed from: L */
    public int f187L;

    /* renamed from: M */
    public int f188M;

    /* renamed from: N */
    public int f189N;

    /* renamed from: O */
    public int f190O;

    /* renamed from: P */
    public boolean f191P;

    /* renamed from: R */
    public Handler f193R;

    /* renamed from: a */
    public final Context f195a;

    /* renamed from: b */
    public final DialogC0123e f196b;

    /* renamed from: c */
    public final Window f197c;

    /* renamed from: d */
    public final int f198d;

    /* renamed from: e */
    public CharSequence f199e;

    /* renamed from: f */
    public CharSequence f200f;

    /* renamed from: g */
    public ListView f201g;

    /* renamed from: h */
    public View f202h;

    /* renamed from: i */
    public int f203i;

    /* renamed from: j */
    public int f204j;

    /* renamed from: k */
    public int f205k;

    /* renamed from: l */
    public int f206l;

    /* renamed from: m */
    public int f207m;

    /* renamed from: o */
    public Button f209o;

    /* renamed from: p */
    public CharSequence f210p;

    /* renamed from: q */
    public Message f211q;

    /* renamed from: r */
    public Drawable f212r;

    /* renamed from: s */
    public Button f213s;

    /* renamed from: t */
    public CharSequence f214t;

    /* renamed from: u */
    public Message f215u;

    /* renamed from: v */
    public Drawable f216v;

    /* renamed from: w */
    public Button f217w;

    /* renamed from: x */
    public CharSequence f218x;

    /* renamed from: y */
    public Message f219y;

    /* renamed from: z */
    public Drawable f220z;

    /* renamed from: n */
    public boolean f208n = false;

    /* renamed from: B */
    public int f177B = 0;

    /* renamed from: I */
    public int f184I = -1;

    /* renamed from: Q */
    public int f192Q = 0;

    /* renamed from: S */
    public final View.OnClickListener f194S = new ViewOnClickListenerC0097a();

    public static class RecycleListView extends ListView {

        /* renamed from: b */
        public final int f221b;

        /* renamed from: c */
        public final int f222c;

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0569j.RecycleListView);
            this.f222c = typedArrayObtainStyledAttributes.getDimensionPixelOffset(C0569j.RecycleListView_paddingBottomNoButtons, -1);
            this.f221b = typedArrayObtainStyledAttributes.getDimensionPixelOffset(C0569j.RecycleListView_paddingTopNoTitle, -1);
        }

        /* renamed from: a */
        public void m245a(boolean z8, boolean z9) {
            if (z9 && z8) {
                return;
            }
            setPadding(getPaddingLeft(), z8 ? getPaddingTop() : this.f221b, getPaddingRight(), z9 ? getPaddingBottom() : this.f222c);
        }
    }

    /* renamed from: androidx.appcompat.app.AlertController$a */
    public class ViewOnClickListenerC0097a implements View.OnClickListener {
        public ViewOnClickListenerC0097a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Message message;
            Message message2;
            Message message3;
            AlertController alertController = AlertController.this;
            Message messageObtain = (view != alertController.f209o || (message3 = alertController.f211q) == null) ? (view != alertController.f213s || (message2 = alertController.f215u) == null) ? (view != alertController.f217w || (message = alertController.f219y) == null) ? null : Message.obtain(message) : Message.obtain(message2) : Message.obtain(message3);
            if (messageObtain != null) {
                messageObtain.sendToTarget();
            }
            AlertController alertController2 = AlertController.this;
            alertController2.f193R.obtainMessage(1, alertController2.f196b).sendToTarget();
        }
    }

    /* renamed from: androidx.appcompat.app.AlertController$b */
    public static class C0098b {

        /* renamed from: A */
        public int f224A;

        /* renamed from: B */
        public int f225B;

        /* renamed from: C */
        public int f226C;

        /* renamed from: D */
        public int f227D;

        /* renamed from: F */
        public boolean[] f229F;

        /* renamed from: G */
        public boolean f230G;

        /* renamed from: H */
        public boolean f231H;

        /* renamed from: J */
        public DialogInterface.OnMultiChoiceClickListener f233J;

        /* renamed from: K */
        public Cursor f234K;

        /* renamed from: L */
        public String f235L;

        /* renamed from: M */
        public String f236M;

        /* renamed from: N */
        public AdapterView.OnItemSelectedListener f237N;

        /* renamed from: a */
        public final Context f239a;

        /* renamed from: b */
        public final LayoutInflater f240b;

        /* renamed from: d */
        public Drawable f242d;

        /* renamed from: f */
        public CharSequence f244f;

        /* renamed from: g */
        public View f245g;

        /* renamed from: h */
        public CharSequence f246h;

        /* renamed from: i */
        public CharSequence f247i;

        /* renamed from: j */
        public Drawable f248j;

        /* renamed from: k */
        public DialogInterface.OnClickListener f249k;

        /* renamed from: l */
        public CharSequence f250l;

        /* renamed from: m */
        public Drawable f251m;

        /* renamed from: n */
        public DialogInterface.OnClickListener f252n;

        /* renamed from: o */
        public CharSequence f253o;

        /* renamed from: p */
        public Drawable f254p;

        /* renamed from: q */
        public DialogInterface.OnClickListener f255q;

        /* renamed from: s */
        public DialogInterface.OnCancelListener f257s;

        /* renamed from: t */
        public DialogInterface.OnDismissListener f258t;

        /* renamed from: u */
        public DialogInterface.OnKeyListener f259u;

        /* renamed from: v */
        public CharSequence[] f260v;

        /* renamed from: w */
        public ListAdapter f261w;

        /* renamed from: x */
        public DialogInterface.OnClickListener f262x;

        /* renamed from: y */
        public int f263y;

        /* renamed from: z */
        public View f264z;

        /* renamed from: c */
        public int f241c = 0;

        /* renamed from: e */
        public int f243e = 0;

        /* renamed from: E */
        public boolean f228E = false;

        /* renamed from: I */
        public int f232I = -1;

        /* renamed from: O */
        public boolean f238O = true;

        /* renamed from: r */
        public boolean f256r = true;

        /* renamed from: androidx.appcompat.app.AlertController$b$a */
        public class a extends ArrayAdapter<CharSequence> {

            /* renamed from: b */
            public final /* synthetic */ RecycleListView f265b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Context context, int i9, int i10, CharSequence[] charSequenceArr, RecycleListView recycleListView) {
                super(context, i9, i10, charSequenceArr);
                this.f265b = recycleListView;
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(int i9, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i9, view, viewGroup);
                boolean[] zArr = C0098b.this.f229F;
                if (zArr != null && zArr[i9]) {
                    this.f265b.setItemChecked(i9, true);
                }
                return view2;
            }
        }

        /* renamed from: androidx.appcompat.app.AlertController$b$b */
        public class b extends CursorAdapter {

            /* renamed from: b */
            public final int f267b;

            /* renamed from: c */
            public final int f268c;

            /* renamed from: d */
            public final /* synthetic */ RecycleListView f269d;

            /* renamed from: e */
            public final /* synthetic */ AlertController f270e;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(Context context, Cursor cursor, boolean z8, RecycleListView recycleListView, AlertController alertController) {
                super(context, cursor, z8);
                this.f269d = recycleListView;
                this.f270e = alertController;
                Cursor cursor2 = getCursor();
                this.f267b = cursor2.getColumnIndexOrThrow(C0098b.this.f235L);
                this.f268c = cursor2.getColumnIndexOrThrow(C0098b.this.f236M);
            }

            @Override // android.widget.CursorAdapter
            public void bindView(View view, Context context, Cursor cursor) {
                ((CheckedTextView) view.findViewById(R.id.text1)).setText(cursor.getString(this.f267b));
                this.f269d.setItemChecked(cursor.getPosition(), cursor.getInt(this.f268c) == 1);
            }

            @Override // android.widget.CursorAdapter
            public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                return C0098b.this.f240b.inflate(this.f270e.f188M, viewGroup, false);
            }
        }

        /* renamed from: androidx.appcompat.app.AlertController$b$c */
        public class c implements AdapterView.OnItemClickListener {

            /* renamed from: b */
            public final /* synthetic */ AlertController f272b;

            public c(AlertController alertController) {
                this.f272b = alertController;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
                C0098b.this.f262x.onClick(this.f272b.f196b, i9);
                if (C0098b.this.f231H) {
                    return;
                }
                this.f272b.f196b.dismiss();
            }
        }

        /* renamed from: androidx.appcompat.app.AlertController$b$d */
        public class d implements AdapterView.OnItemClickListener {

            /* renamed from: b */
            public final /* synthetic */ RecycleListView f274b;

            /* renamed from: c */
            public final /* synthetic */ AlertController f275c;

            public d(RecycleListView recycleListView, AlertController alertController) {
                this.f274b = recycleListView;
                this.f275c = alertController;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
                boolean[] zArr = C0098b.this.f229F;
                if (zArr != null) {
                    zArr[i9] = this.f274b.isItemChecked(i9);
                }
                C0098b.this.f233J.onClick(this.f275c.f196b, i9, this.f274b.isItemChecked(i9));
            }
        }

        public C0098b(Context context) {
            this.f239a = context;
            this.f240b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        /* renamed from: a */
        public void m246a(AlertController alertController) {
            View view = this.f245g;
            if (view != null) {
                alertController.m231k(view);
            } else {
                CharSequence charSequence = this.f244f;
                if (charSequence != null) {
                    alertController.m236p(charSequence);
                }
                Drawable drawable = this.f242d;
                if (drawable != null) {
                    alertController.m233m(drawable);
                }
                int i9 = this.f241c;
                if (i9 != 0) {
                    alertController.m232l(i9);
                }
                int i10 = this.f243e;
                if (i10 != 0) {
                    alertController.m232l(alertController.m223c(i10));
                }
            }
            CharSequence charSequence2 = this.f246h;
            if (charSequence2 != null) {
                alertController.m234n(charSequence2);
            }
            CharSequence charSequence3 = this.f247i;
            if (charSequence3 != null || this.f248j != null) {
                alertController.m230j(-1, charSequence3, this.f249k, null, this.f248j);
            }
            CharSequence charSequence4 = this.f250l;
            if (charSequence4 != null || this.f251m != null) {
                alertController.m230j(-2, charSequence4, this.f252n, null, this.f251m);
            }
            CharSequence charSequence5 = this.f253o;
            if (charSequence5 != null || this.f254p != null) {
                alertController.m230j(-3, charSequence5, this.f255q, null, this.f254p);
            }
            if (this.f260v != null || this.f234K != null || this.f261w != null) {
                m247b(alertController);
            }
            View view2 = this.f264z;
            if (view2 != null) {
                if (this.f228E) {
                    alertController.m239s(view2, this.f224A, this.f225B, this.f226C, this.f227D);
                    return;
                } else {
                    alertController.m238r(view2);
                    return;
                }
            }
            int i11 = this.f263y;
            if (i11 != 0) {
                alertController.m237q(i11);
            }
        }

        /* renamed from: b */
        public final void m247b(AlertController alertController) {
            ListAdapter c0100d;
            RecycleListView recycleListView = (RecycleListView) this.f240b.inflate(alertController.f187L, (ViewGroup) null);
            if (this.f230G) {
                c0100d = this.f234K == null ? new a(this.f239a, alertController.f188M, R.id.text1, this.f260v, recycleListView) : new b(this.f239a, this.f234K, false, recycleListView, alertController);
            } else {
                int i9 = this.f231H ? alertController.f189N : alertController.f190O;
                if (this.f234K != null) {
                    c0100d = new SimpleCursorAdapter(this.f239a, i9, this.f234K, new String[]{this.f235L}, new int[]{R.id.text1});
                } else {
                    c0100d = this.f261w;
                    if (c0100d == null) {
                        c0100d = new C0100d(this.f239a, i9, R.id.text1, this.f260v);
                    }
                }
            }
            alertController.f183H = c0100d;
            alertController.f184I = this.f232I;
            if (this.f262x != null) {
                recycleListView.setOnItemClickListener(new c(alertController));
            } else if (this.f233J != null) {
                recycleListView.setOnItemClickListener(new d(recycleListView, alertController));
            }
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.f237N;
            if (onItemSelectedListener != null) {
                recycleListView.setOnItemSelectedListener(onItemSelectedListener);
            }
            if (this.f231H) {
                recycleListView.setChoiceMode(1);
            } else if (this.f230G) {
                recycleListView.setChoiceMode(2);
            }
            alertController.f201g = recycleListView;
        }
    }

    /* renamed from: androidx.appcompat.app.AlertController$c */
    public static final class HandlerC0099c extends Handler {

        /* renamed from: a */
        public WeakReference<DialogInterface> f277a;

        public HandlerC0099c(DialogInterface dialogInterface) {
            this.f277a = new WeakReference<>(dialogInterface);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i9 = message.what;
            if (i9 == -3 || i9 == -2 || i9 == -1) {
                ((DialogInterface.OnClickListener) message.obj).onClick(this.f277a.get(), message.what);
            } else {
                if (i9 != 1) {
                    return;
                }
                ((DialogInterface) message.obj).dismiss();
            }
        }
    }

    /* renamed from: androidx.appcompat.app.AlertController$d */
    public static class C0100d extends ArrayAdapter<CharSequence> {
        public C0100d(Context context, int i9, int i10, CharSequence[] charSequenceArr) {
            super(context, i9, i10, charSequenceArr);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public long getItemId(int i9) {
            return i9;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }
    }

    public AlertController(Context context, DialogC0123e dialogC0123e, Window window) {
        this.f195a = context;
        this.f196b = dialogC0123e;
        this.f197c = window;
        this.f193R = new HandlerC0099c(dialogC0123e);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, C0569j.AlertDialog, C0560a.alertDialogStyle, 0);
        this.f185J = typedArrayObtainStyledAttributes.getResourceId(C0569j.AlertDialog_android_layout, 0);
        this.f186K = typedArrayObtainStyledAttributes.getResourceId(C0569j.AlertDialog_buttonPanelSideLayout, 0);
        this.f187L = typedArrayObtainStyledAttributes.getResourceId(C0569j.AlertDialog_listLayout, 0);
        this.f188M = typedArrayObtainStyledAttributes.getResourceId(C0569j.AlertDialog_multiChoiceItemLayout, 0);
        this.f189N = typedArrayObtainStyledAttributes.getResourceId(C0569j.AlertDialog_singleChoiceItemLayout, 0);
        this.f190O = typedArrayObtainStyledAttributes.getResourceId(C0569j.AlertDialog_listItemLayout, 0);
        this.f191P = typedArrayObtainStyledAttributes.getBoolean(C0569j.AlertDialog_showTitle, true);
        this.f198d = typedArrayObtainStyledAttributes.getDimensionPixelSize(C0569j.AlertDialog_buttonIconDimen, 0);
        typedArrayObtainStyledAttributes.recycle();
        dialogC0123e.supportRequestWindowFeature(1);
    }

    /* renamed from: a */
    public static boolean m220a(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (m220a(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: y */
    public static boolean m221y(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(C0560a.alertDialogCenterButtons, typedValue, true);
        return typedValue.data != 0;
    }

    /* renamed from: b */
    public final void m222b(Button button) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        button.setLayoutParams(layoutParams);
    }

    /* renamed from: c */
    public int m223c(int i9) {
        TypedValue typedValue = new TypedValue();
        this.f195a.getTheme().resolveAttribute(i9, typedValue, true);
        return typedValue.resourceId;
    }

    /* renamed from: d */
    public ListView m224d() {
        return this.f201g;
    }

    /* renamed from: e */
    public void m225e() {
        this.f196b.setContentView(m229i());
        m244x();
    }

    /* renamed from: f */
    public boolean m226f(int i9, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f176A;
        return nestedScrollView != null && nestedScrollView.m1538l(keyEvent);
    }

    /* renamed from: g */
    public boolean m227g(int i9, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f176A;
        return nestedScrollView != null && nestedScrollView.m1538l(keyEvent);
    }

    /* renamed from: h */
    public final ViewGroup m228h(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }

    /* renamed from: i */
    public final int m229i() {
        int i9 = this.f186K;
        return (i9 != 0 && this.f192Q == 1) ? i9 : this.f185J;
    }

    /* renamed from: j */
    public void m230j(int i9, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message, Drawable drawable) {
        if (message == null && onClickListener != null) {
            message = this.f193R.obtainMessage(i9, onClickListener);
        }
        if (i9 == -3) {
            this.f218x = charSequence;
            this.f219y = message;
            this.f220z = drawable;
        } else if (i9 == -2) {
            this.f214t = charSequence;
            this.f215u = message;
            this.f216v = drawable;
        } else {
            if (i9 != -1) {
                throw new IllegalArgumentException("Button does not exist");
            }
            this.f210p = charSequence;
            this.f211q = message;
            this.f212r = drawable;
        }
    }

    /* renamed from: k */
    public void m231k(View view) {
        this.f182G = view;
    }

    /* renamed from: l */
    public void m232l(int i9) {
        this.f178C = null;
        this.f177B = i9;
        ImageView imageView = this.f179D;
        if (imageView != null) {
            if (i9 == 0) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
                this.f179D.setImageResource(this.f177B);
            }
        }
    }

    /* renamed from: m */
    public void m233m(Drawable drawable) {
        this.f178C = drawable;
        this.f177B = 0;
        ImageView imageView = this.f179D;
        if (imageView != null) {
            if (drawable == null) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
                this.f179D.setImageDrawable(drawable);
            }
        }
    }

    /* renamed from: n */
    public void m234n(CharSequence charSequence) {
        this.f200f = charSequence;
        TextView textView = this.f181F;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    /* renamed from: o */
    public final void m235o(ViewGroup viewGroup, View view, int i9, int i10) {
        View viewFindViewById = this.f197c.findViewById(C0565f.scrollIndicatorUp);
        View viewFindViewById2 = this.f197c.findViewById(C0565f.scrollIndicatorDown);
        C4647u.m18560o0(view, i9, i10);
        if (viewFindViewById != null) {
            viewGroup.removeView(viewFindViewById);
        }
        if (viewFindViewById2 != null) {
            viewGroup.removeView(viewFindViewById2);
        }
    }

    /* renamed from: p */
    public void m236p(CharSequence charSequence) {
        this.f199e = charSequence;
        TextView textView = this.f180E;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    /* renamed from: q */
    public void m237q(int i9) {
        this.f202h = null;
        this.f203i = i9;
        this.f208n = false;
    }

    /* renamed from: r */
    public void m238r(View view) {
        this.f202h = view;
        this.f203i = 0;
        this.f208n = false;
    }

    /* renamed from: s */
    public void m239s(View view, int i9, int i10, int i11, int i12) {
        this.f202h = view;
        this.f203i = 0;
        this.f208n = true;
        this.f204j = i9;
        this.f205k = i10;
        this.f206l = i11;
        this.f207m = i12;
    }

    /* renamed from: t */
    public final void m240t(ViewGroup viewGroup) {
        int i9;
        Button button = (Button) viewGroup.findViewById(R.id.button1);
        this.f209o = button;
        button.setOnClickListener(this.f194S);
        if (TextUtils.isEmpty(this.f210p) && this.f212r == null) {
            this.f209o.setVisibility(8);
            i9 = 0;
        } else {
            this.f209o.setText(this.f210p);
            Drawable drawable = this.f212r;
            if (drawable != null) {
                int i10 = this.f198d;
                drawable.setBounds(0, 0, i10, i10);
                this.f209o.setCompoundDrawables(this.f212r, null, null, null);
            }
            this.f209o.setVisibility(0);
            i9 = 1;
        }
        Button button2 = (Button) viewGroup.findViewById(R.id.button2);
        this.f213s = button2;
        button2.setOnClickListener(this.f194S);
        if (TextUtils.isEmpty(this.f214t) && this.f216v == null) {
            this.f213s.setVisibility(8);
        } else {
            this.f213s.setText(this.f214t);
            Drawable drawable2 = this.f216v;
            if (drawable2 != null) {
                int i11 = this.f198d;
                drawable2.setBounds(0, 0, i11, i11);
                this.f213s.setCompoundDrawables(this.f216v, null, null, null);
            }
            this.f213s.setVisibility(0);
            i9 |= 2;
        }
        Button button3 = (Button) viewGroup.findViewById(R.id.button3);
        this.f217w = button3;
        button3.setOnClickListener(this.f194S);
        if (TextUtils.isEmpty(this.f218x) && this.f220z == null) {
            this.f217w.setVisibility(8);
        } else {
            this.f217w.setText(this.f218x);
            Drawable drawable3 = this.f220z;
            if (drawable3 != null) {
                int i12 = this.f198d;
                drawable3.setBounds(0, 0, i12, i12);
                this.f217w.setCompoundDrawables(this.f220z, null, null, null);
            }
            this.f217w.setVisibility(0);
            i9 |= 4;
        }
        if (m221y(this.f195a)) {
            if (i9 == 1) {
                m222b(this.f209o);
            } else if (i9 == 2) {
                m222b(this.f213s);
            } else if (i9 == 4) {
                m222b(this.f217w);
            }
        }
        if (i9 != 0) {
            return;
        }
        viewGroup.setVisibility(8);
    }

    /* renamed from: u */
    public final void m241u(ViewGroup viewGroup) {
        NestedScrollView nestedScrollView = (NestedScrollView) this.f197c.findViewById(C0565f.scrollView);
        this.f176A = nestedScrollView;
        nestedScrollView.setFocusable(false);
        this.f176A.setNestedScrollingEnabled(false);
        TextView textView = (TextView) viewGroup.findViewById(R.id.message);
        this.f181F = textView;
        if (textView == null) {
            return;
        }
        CharSequence charSequence = this.f200f;
        if (charSequence != null) {
            textView.setText(charSequence);
            return;
        }
        textView.setVisibility(8);
        this.f176A.removeView(this.f181F);
        if (this.f201g == null) {
            viewGroup.setVisibility(8);
            return;
        }
        ViewGroup viewGroup2 = (ViewGroup) this.f176A.getParent();
        int iIndexOfChild = viewGroup2.indexOfChild(this.f176A);
        viewGroup2.removeViewAt(iIndexOfChild);
        viewGroup2.addView(this.f201g, iIndexOfChild, new ViewGroup.LayoutParams(-1, -1));
    }

    /* renamed from: v */
    public final void m242v(ViewGroup viewGroup) {
        View viewInflate = this.f202h;
        if (viewInflate == null) {
            viewInflate = this.f203i != 0 ? LayoutInflater.from(this.f195a).inflate(this.f203i, viewGroup, false) : null;
        }
        boolean z8 = viewInflate != null;
        if (!z8 || !m220a(viewInflate)) {
            this.f197c.setFlags(131072, 131072);
        }
        if (!z8) {
            viewGroup.setVisibility(8);
            return;
        }
        FrameLayout frameLayout = (FrameLayout) this.f197c.findViewById(C0565f.custom);
        frameLayout.addView(viewInflate, new ViewGroup.LayoutParams(-1, -1));
        if (this.f208n) {
            frameLayout.setPadding(this.f204j, this.f205k, this.f206l, this.f207m);
        }
        if (this.f201g != null) {
            ((LinearLayoutCompat.C0183a) viewGroup.getLayoutParams()).f849a = BitmapDescriptorFactory.HUE_RED;
        }
    }

    /* renamed from: w */
    public final void m243w(ViewGroup viewGroup) {
        if (this.f182G != null) {
            viewGroup.addView(this.f182G, 0, new ViewGroup.LayoutParams(-1, -2));
            this.f197c.findViewById(C0565f.title_template).setVisibility(8);
            return;
        }
        this.f179D = (ImageView) this.f197c.findViewById(R.id.icon);
        if (!(!TextUtils.isEmpty(this.f199e)) || !this.f191P) {
            this.f197c.findViewById(C0565f.title_template).setVisibility(8);
            this.f179D.setVisibility(8);
            viewGroup.setVisibility(8);
            return;
        }
        TextView textView = (TextView) this.f197c.findViewById(C0565f.alertTitle);
        this.f180E = textView;
        textView.setText(this.f199e);
        int i9 = this.f177B;
        if (i9 != 0) {
            this.f179D.setImageResource(i9);
            return;
        }
        Drawable drawable = this.f178C;
        if (drawable != null) {
            this.f179D.setImageDrawable(drawable);
        } else {
            this.f180E.setPadding(this.f179D.getPaddingLeft(), this.f179D.getPaddingTop(), this.f179D.getPaddingRight(), this.f179D.getPaddingBottom());
            this.f179D.setVisibility(8);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: x */
    public final void m244x() {
        View viewFindViewById;
        ListAdapter listAdapter;
        View viewFindViewById2;
        View viewFindViewById3 = this.f197c.findViewById(C0565f.parentPanel);
        int i9 = C0565f.topPanel;
        View viewFindViewById4 = viewFindViewById3.findViewById(i9);
        int i10 = C0565f.contentPanel;
        View viewFindViewById5 = viewFindViewById3.findViewById(i10);
        int i11 = C0565f.buttonPanel;
        View viewFindViewById6 = viewFindViewById3.findViewById(i11);
        ViewGroup viewGroup = (ViewGroup) viewFindViewById3.findViewById(C0565f.customPanel);
        m242v(viewGroup);
        View viewFindViewById7 = viewGroup.findViewById(i9);
        View viewFindViewById8 = viewGroup.findViewById(i10);
        View viewFindViewById9 = viewGroup.findViewById(i11);
        ViewGroup viewGroupM228h = m228h(viewFindViewById7, viewFindViewById4);
        ViewGroup viewGroupM228h2 = m228h(viewFindViewById8, viewFindViewById5);
        ViewGroup viewGroupM228h3 = m228h(viewFindViewById9, viewFindViewById6);
        m241u(viewGroupM228h2);
        m240t(viewGroupM228h3);
        m243w(viewGroupM228h);
        boolean z8 = viewGroup.getVisibility() != 8;
        boolean z9 = (viewGroupM228h == null || viewGroupM228h.getVisibility() == 8) ? 0 : 1;
        boolean z10 = (viewGroupM228h3 == null || viewGroupM228h3.getVisibility() == 8) ? false : true;
        if (!z10 && viewGroupM228h2 != null && (viewFindViewById2 = viewGroupM228h2.findViewById(C0565f.textSpacerNoButtons)) != null) {
            viewFindViewById2.setVisibility(0);
        }
        if (z9 != 0) {
            NestedScrollView nestedScrollView = this.f176A;
            if (nestedScrollView != null) {
                nestedScrollView.setClipToPadding(true);
            }
            View viewFindViewById10 = (this.f200f == null && this.f201g == null) ? null : viewGroupM228h.findViewById(C0565f.titleDividerNoCustom);
            if (viewFindViewById10 != null) {
                viewFindViewById10.setVisibility(0);
            }
        } else if (viewGroupM228h2 != null && (viewFindViewById = viewGroupM228h2.findViewById(C0565f.textSpacerNoTitle)) != null) {
            viewFindViewById.setVisibility(0);
        }
        ListView listView = this.f201g;
        if (listView instanceof RecycleListView) {
            ((RecycleListView) listView).m245a(z9, z10);
        }
        if (!z8) {
            View view = this.f201g;
            if (view == null) {
                view = this.f176A;
            }
            if (view != null) {
                m235o(viewGroupM228h2, view, z9 | (z10 ? 2 : 0), 3);
            }
        }
        ListView listView2 = this.f201g;
        if (listView2 == null || (listAdapter = this.f183H) == null) {
            return;
        }
        listView2.setAdapter(listAdapter);
        int i12 = this.f184I;
        if (i12 > -1) {
            listView2.setItemChecked(i12, true);
            listView2.setSelection(i12);
        }
    }
}
