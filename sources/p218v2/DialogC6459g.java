package p218v2;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.p033u.glide.MediaLoader;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.pages.photoimport.FileItem;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.utility.CLUtility;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import p173q2.C6136j;

/* renamed from: v2.g */
/* loaded from: classes.dex */
public class DialogC6459g extends Dialog implements View.OnClickListener {

    /* renamed from: b */
    public Context f21766b;

    /* renamed from: c */
    public TextView f21767c;

    /* renamed from: d */
    public View f21768d;

    /* renamed from: e */
    public ImageView f21769e;

    /* renamed from: f */
    public TextView f21770f;

    /* renamed from: g */
    public View f21771g;

    /* renamed from: h */
    public a f21772h;

    /* renamed from: i */
    public ProgressBar f21773i;

    /* renamed from: j */
    public TextView f21774j;

    /* renamed from: k */
    public View.OnClickListener f21775k;

    /* renamed from: v2.g$a */
    public interface a {
        /* renamed from: a */
        void mo7918a();
    }

    public DialogC6459g(Context context, int i9) {
        super(context, i9);
        this.f21772h = null;
        this.f21775k = new View.OnClickListener() { // from class: v2.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f21765b.m24759l(view);
            }
        };
        this.f21766b = context;
        setContentView(R.layout.custom_import_dialog);
        this.f21769e = (ImageView) findViewById(R.id.importImage);
        this.f21767c = (TextView) findViewById(R.id.dialogCustomTitle);
        this.f21771g = findViewById(R.id.divLine);
        TextView textView = (TextView) findViewById(R.id.btnCancel);
        this.f21770f = textView;
        textView.setOnClickListener(this.f21775k);
        this.f21773i = (ProgressBar) findViewById(R.id.progressbarHorizontal);
        this.f21774j = (TextView) findViewById(R.id.progressText);
        this.f21768d = findViewById(R.id.dialogContent);
        setCanceledOnTouchOutside(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public /* synthetic */ void m24759l(View view) {
        m24760b();
    }

    /* renamed from: b */
    public void m24760b() {
        a aVar = this.f21772h;
        if (aVar != null) {
            aVar.mo7918a();
        }
        dismiss();
    }

    /* renamed from: c */
    public void m24761c(int i9) {
        this.f21769e.setImageResource(i9);
    }

    /* renamed from: d */
    public void m24762d(C2973l0 c2973l0) {
        if (c2973l0 == null || this.f21766b == null || this.f21769e == null) {
            return;
        }
        if ("File".equals(c2973l0.m15147s())) {
            this.f21769e.setImageResource(CLUtility.m16564n0(c2973l0.m15145q(), true));
        } else {
            MediaLoader.m7156o(this.f21766b, this.f21769e, c2973l0, MediaLoader.Option.THUMBNAIL);
        }
    }

    /* renamed from: e */
    public void m24763e(FileItem fileItem) {
        ImageView imageView;
        if (fileItem == null || this.f21766b == null || (imageView = this.f21769e) == null) {
            return;
        }
        imageView.setImageResource(CLUtility.m16564n0(fileItem.m16114a(), true));
    }

    /* renamed from: f */
    public void m24764f(ImageItem imageItem) {
        String strM16135h = imageItem != null ? imageItem.m16135h() : null;
        Uri uriM16510Z1 = imageItem != null ? CLUtility.m16510Z1(imageItem.m16136i()) : null;
        if (uriM16510Z1 != null) {
            C6136j.m23596p(this.f21766b, this.f21769e, uriM16510Z1, R.drawable.doc_thumbnail_default, 384);
        } else {
            C6136j.m23601u(this.f21766b, this.f21769e, strM16135h, R.drawable.doc_thumbnail_default, 384);
        }
    }

    /* renamed from: g */
    public void m24765g(String str) {
        C6136j.m23600t(this.f21766b, this.f21769e, str, R.drawable.doc_thumbnail_default);
    }

    /* renamed from: h */
    public void m24766h(boolean z8) {
        if (z8) {
            this.f21773i.setVisibility(0);
        } else {
            this.f21773i.setVisibility(8);
        }
    }

    /* renamed from: i */
    public void m24767i(boolean z8) {
        if (z8) {
            this.f21774j.setVisibility(0);
        } else {
            this.f21774j.setVisibility(8);
        }
    }

    /* renamed from: j */
    public void m24768j() {
        this.f21768d.setVisibility(8);
    }

    /* renamed from: k */
    public void m24769k() {
        this.f21771g.setVisibility(8);
    }

    /* renamed from: m */
    public void m24770m(a aVar) {
        this.f21772h = aVar;
    }

    /* renamed from: n */
    public void m24771n(String str, String str2) {
        String string = this.f21766b.getResources().getString(R.string.uploading);
        this.f21767c.setText(string + " (" + str + "/" + str2 + ")");
    }

    /* renamed from: o */
    public void m24772o(int i9) {
        ProgressBar progressBar = this.f21773i;
        if (progressBar == null || progressBar.getVisibility() != 0) {
            return;
        }
        this.f21773i.setProgress(i9);
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    /* renamed from: p */
    public void m24773p(int i9, int i10, int i11) {
        ProgressBar progressBar = this.f21773i;
        if (progressBar != null && progressBar.getVisibility() == 0) {
            this.f21773i.setProgress(i9);
        }
        if (this.f21774j.getVisibility() == 0) {
            if (i11 > 1048576) {
                this.f21774j.setText(String.format("%d KB / %d KB", Integer.valueOf(i10 / UserMetadata.MAX_ATTRIBUTE_SIZE), Integer.valueOf(i11 / UserMetadata.MAX_ATTRIBUTE_SIZE)));
            } else {
                this.f21774j.setText(String.format("%d Bytes / %d Bytes", Integer.valueOf(i10), Integer.valueOf(i11)));
            }
        }
    }

    /* renamed from: q */
    public void m24774q(boolean z8) {
        this.f21773i.setIndeterminate(z8);
    }

    /* renamed from: r */
    public void m24775r(String str, String str2) throws Resources.NotFoundException {
        String string = this.f21766b.getResources().getString(R.string.downloading);
        this.f21767c.setText(string + " (" + str + "/" + str2 + ")");
    }

    /* renamed from: s */
    public void m24776s(String str) {
        if (str != null) {
            this.f21767c.setText(str);
        }
    }
}
