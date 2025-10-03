package com.cyberlink.you.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.fragment.app.Fragment;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseFragmentActivity;
import com.cyberlink.you.activity.MessageGeneratorFragment;
import com.cyberlink.you.activity.share.ShareMediaActivity;
import com.cyberlink.you.activity.share.ShareType;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.p036ui.ViewOnClickListenerC3127k;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.sticker.StickerObj;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.exoplayer2.extractor.p037ts.TsExtractor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import p042d0.C4619d;
import p173q2.C6127a;

/* loaded from: classes.dex */
public class ScheduleSendActivity extends BaseFragmentActivity {

    /* renamed from: l */
    public SendType f8737l;

    /* renamed from: c */
    public final int f8728c = 5;

    /* renamed from: d */
    public TextView f8729d = null;

    /* renamed from: e */
    public TextView f8730e = null;

    /* renamed from: f */
    public Button f8731f = null;

    /* renamed from: g */
    public TextView f8732g = null;

    /* renamed from: h */
    public final Calendar f8733h = Calendar.getInstance();

    /* renamed from: i */
    public C4619d f8734i = null;

    /* renamed from: j */
    public MessageGeneratorFragment f8735j = null;

    /* renamed from: k */
    public final View.OnClickListener f8736k = new View.OnClickListener() { // from class: com.cyberlink.you.activity.nd
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10928b.m9645a1(view);
        }
    };

    /* renamed from: m */
    public final View.OnClickListener f8738m = new View.OnClickListener() { // from class: com.cyberlink.you.activity.od
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11012b.m9646b1(view);
        }
    };

    /* renamed from: n */
    public final View.OnClickListener f8739n = new View.OnClickListener() { // from class: com.cyberlink.you.activity.pd
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11042b.m9647c1(view);
        }
    };

    /* renamed from: o */
    public final View.OnClickListener f8740o = new View.OnClickListener() { // from class: com.cyberlink.you.activity.qd
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11085b.m9650f1(view);
        }
    };

    public enum SendType {
        DELAY_SEND(R.string.send_it_later, R.string.send_btn),
        BROADCAST(R.string.broadcast_btn, R.string.registration_phone_registration_next_btn);

        private final int resButton;
        private final int resTitle;

        SendType(int i9, int i10) {
            this.resTitle = i9;
            this.resButton = i10;
        }
    }

    /* renamed from: com.cyberlink.you.activity.ScheduleSendActivity$a */
    public class C1671a extends GestureDetector.SimpleOnGestureListener {
        public C1671a() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            CLUtility.m16589t1(ScheduleSendActivity.this);
            return false;
        }
    }

    /* renamed from: com.cyberlink.you.activity.ScheduleSendActivity$b */
    public class C1672b implements MessageGeneratorFragment.InterfaceC1555g {
        public C1672b() {
        }

        @Override // com.cyberlink.you.activity.MessageGeneratorFragment.InterfaceC1555g
        /* renamed from: a */
        public void mo8751a(CharSequence charSequence) {
            ScheduleSendActivity.this.f8731f.setVisibility(0);
            if (TextUtils.isEmpty(charSequence)) {
                ScheduleSendActivity.this.f8731f.setEnabled(false);
            } else {
                ScheduleSendActivity.this.f8731f.setEnabled(true);
            }
        }

        @Override // com.cyberlink.you.activity.MessageGeneratorFragment.InterfaceC1555g
        /* renamed from: b */
        public void mo8752b(String str) {
            ScheduleSendActivity.this.f8731f.setVisibility(8);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (ScheduleSendActivity.this.f8733h.getTime().before(new Date(FriendsClient.m15646K()))) {
                ScheduleSendActivity.this.m9657k1();
                return;
            }
            if (ScheduleSendActivity.this.f8737l == SendType.DELAY_SEND) {
                Intent intent = new Intent();
                intent.putExtra("type", MessageGeneratorFragment.InputType.Voice.toString());
                intent.putExtra("time", ScheduleSendActivity.this.f8733h.getTimeInMillis());
                intent.putExtra("voice", str);
                ScheduleSendActivity.this.setResult(-1, intent);
                ScheduleSendActivity.this.finish();
                return;
            }
            if (ScheduleSendActivity.this.f8737l == SendType.BROADCAST) {
                Intent intent2 = new Intent(ScheduleSendActivity.this, (Class<?>) ShareMediaActivity.class);
                intent2.putExtra("type", MessageGeneratorFragment.InputType.Voice.toString());
                intent2.putExtra("shareType", ShareType.Broadcast.toString());
                intent2.putExtra("time", ScheduleSendActivity.this.f8733h.getTimeInMillis());
                intent2.putExtra("voice", str);
                ScheduleSendActivity.this.startActivityForResult(intent2, 5);
            }
        }

        @Override // com.cyberlink.you.activity.MessageGeneratorFragment.InterfaceC1555g
        /* renamed from: c */
        public void mo8753c(StickerObj stickerObj) {
            ScheduleSendActivity.this.f8731f.setVisibility(0);
            if (stickerObj != null) {
                ScheduleSendActivity.this.f8731f.setEnabled(true);
            } else {
                ScheduleSendActivity.this.f8731f.setEnabled(false);
            }
        }

        @Override // com.cyberlink.you.activity.MessageGeneratorFragment.InterfaceC1555g
        /* renamed from: d */
        public void mo8754d(MessageGeneratorFragment.PhotoType photoType, ArrayList<ImageItem> arrayList) {
            if (photoType == null || arrayList == null) {
                ScheduleSendActivity.this.f8731f.setVisibility(8);
                return;
            }
            if (ScheduleSendActivity.this.f8733h.getTime().before(new Date(FriendsClient.m15646K()))) {
                ScheduleSendActivity.this.m9657k1();
                return;
            }
            if (photoType == MessageGeneratorFragment.PhotoType.Camera || photoType == MessageGeneratorFragment.PhotoType.Gallery) {
                if (ScheduleSendActivity.this.f8737l == SendType.DELAY_SEND) {
                    Intent intent = new Intent();
                    intent.putExtra("type", MessageGeneratorFragment.InputType.Photo.toString());
                    intent.putExtra("subtype", photoType.toString());
                    intent.putExtra("time", ScheduleSendActivity.this.f8733h.getTimeInMillis());
                    intent.putExtra("photo", arrayList);
                    ScheduleSendActivity.this.setResult(-1, intent);
                    ScheduleSendActivity.this.finish();
                    return;
                }
                if (ScheduleSendActivity.this.f8737l == SendType.BROADCAST) {
                    Intent intent2 = new Intent(ScheduleSendActivity.this, (Class<?>) ShareMediaActivity.class);
                    intent2.putExtra("type", MessageGeneratorFragment.InputType.Photo.toString());
                    intent2.putExtra("subtype", photoType.toString());
                    intent2.putExtra("shareType", ShareType.Broadcast.toString());
                    intent2.putExtra("time", ScheduleSendActivity.this.f8733h.getTimeInMillis());
                    intent2.putExtra("photo", arrayList);
                    ScheduleSendActivity.this.startActivityForResult(intent2, 5);
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.ScheduleSendActivity$c */
    public static class DialogFragmentC1673c extends DialogFragment {

        /* renamed from: b */
        public TextView f8746b;

        /* renamed from: c */
        public Calendar f8747c;

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m9663d(DatePicker datePicker, int i9, int i10, int i11) {
            this.f8747c.set(5, i11);
            this.f8747c.set(2, i10);
            this.f8747c.set(1, i9);
            this.f8746b.setText(new SimpleDateFormat("EEE, MMM dd, yyyy").format(this.f8747c.getTime()));
        }

        @Override // android.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() { // from class: com.cyberlink.you.activity.xd
                @Override // android.app.DatePickerDialog.OnDateSetListener
                public final void onDateSet(DatePicker datePicker, int i9, int i10, int i11) {
                    this.f12253a.m9663d(datePicker, i9, i10, i11);
                }
            }, this.f8747c.get(1), this.f8747c.get(2), this.f8747c.get(5));
            DatePicker datePicker = datePickerDialog.getDatePicker();
            Calendar calendar = (Calendar) Calendar.getInstance().clone();
            calendar.set(11, 0);
            calendar.set(12, 0);
            datePicker.setMinDate(calendar.getTime().getTime());
            return datePickerDialog;
        }

        @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            super.onDismiss(dialogInterface);
            this.f8746b.setEnabled(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a1 */
    public /* synthetic */ void m9645a1(View view) {
        CLUtility.m16589t1(this);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b1 */
    public /* synthetic */ void m9646b1(View view) {
        CLUtility.m16589t1(this);
        Object objM8738o = this.f8735j.m8738o();
        if (objM8738o == null) {
            return;
        }
        if (this.f8733h.getTime().before(new Date(FriendsClient.m15646K()))) {
            m9657k1();
            return;
        }
        MessageGeneratorFragment.InputType inputTypeM8739p = this.f8735j.m8739p();
        SendType sendType = this.f8737l;
        if (sendType == SendType.DELAY_SEND) {
            if (inputTypeM8739p.equals(MessageGeneratorFragment.InputType.Text)) {
                Intent intent = new Intent();
                intent.putExtra("type", inputTypeM8739p.toString());
                intent.putExtra("time", this.f8733h.getTimeInMillis());
                intent.putExtra("message", (String) objM8738o);
                setResult(-1, intent);
                finish();
                return;
            }
            if (inputTypeM8739p.equals(MessageGeneratorFragment.InputType.Sticker)) {
                Intent intent2 = new Intent();
                intent2.putExtra("type", inputTypeM8739p.toString());
                intent2.putExtra("time", this.f8733h.getTimeInMillis());
                intent2.putExtra("sticker", (StickerObj) objM8738o);
                setResult(-1, intent2);
                finish();
                return;
            }
            return;
        }
        if (sendType == SendType.BROADCAST) {
            if (inputTypeM8739p.equals(MessageGeneratorFragment.InputType.Text)) {
                Intent intent3 = new Intent(this, (Class<?>) ShareMediaActivity.class);
                intent3.putExtra("shareType", ShareType.Broadcast.toString());
                intent3.putExtra("type", inputTypeM8739p.toString());
                intent3.putExtra("time", this.f8733h.getTimeInMillis());
                intent3.putExtra("message", (String) objM8738o);
                startActivityForResult(intent3, 5);
                return;
            }
            if (inputTypeM8739p.equals(MessageGeneratorFragment.InputType.Sticker)) {
                Intent intent4 = new Intent(this, (Class<?>) ShareMediaActivity.class);
                intent4.putExtra("shareType", ShareType.Broadcast.toString());
                intent4.putExtra("type", inputTypeM8739p.toString());
                intent4.putExtra("time", this.f8733h.getTimeInMillis());
                intent4.putExtra("sticker", (StickerObj) objM8738o);
                startActivityForResult(intent4, 5);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c1 */
    public /* synthetic */ void m9647c1(View view) {
        if (m9656Z0()) {
            return;
        }
        this.f8729d.setEnabled(false);
        DialogFragmentC1673c dialogFragmentC1673c = new DialogFragmentC1673c();
        dialogFragmentC1673c.f8746b = this.f8729d;
        dialogFragmentC1673c.f8747c = this.f8733h;
        dialogFragmentC1673c.show(getFragmentManager(), "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d1 */
    public /* synthetic */ void m9648d1(TimePicker timePicker, int i9, int i10) {
        this.f8733h.set(11, i9);
        this.f8733h.set(12, i10);
        this.f8730e.setText(new SimpleDateFormat(DateFormat.is24HourFormat(this) ? "HH:mm" : "h:mm a").format(this.f8733h.getTime()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e1 */
    public /* synthetic */ void m9649e1(DialogInterface dialogInterface) {
        this.f8730e.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f1 */
    public /* synthetic */ void m9650f1(View view) {
        if (m9656Z0()) {
            return;
        }
        this.f8730e.setEnabled(false);
        new ViewOnClickListenerC3127k(this, new TimePickerDialog.OnTimeSetListener() { // from class: com.cyberlink.you.activity.vd
            @Override // android.app.TimePickerDialog.OnTimeSetListener
            public final void onTimeSet(TimePicker timePicker, int i9, int i10) {
                this.f11818a.m9648d1(timePicker, i9, i10);
            }
        }, new DialogInterface.OnDismissListener() { // from class: com.cyberlink.you.activity.wd
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f11850b.m9649e1(dialogInterface);
            }
        }, this.f8733h.get(11), this.f8733h.get(12), DateFormat.is24HourFormat(this)).m16389b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g1 */
    public /* synthetic */ boolean m9651g1(View view, MotionEvent motionEvent) {
        C4619d c4619d = this.f8734i;
        if (c4619d == null) {
            return false;
        }
        return c4619d.m18406a(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i1 */
    public /* synthetic */ void m9653i1() {
        if (getResources().getConfiguration().keyboardHidden == 1) {
            final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
            scrollView.post(new Runnable() { // from class: com.cyberlink.you.activity.td
                @Override // java.lang.Runnable
                public final void run() {
                    scrollView.fullScroll(TsExtractor.TS_STREAM_TYPE_HDMV_DTS);
                }
            });
        }
    }

    /* renamed from: j1 */
    public static /* synthetic */ void m9654j1(DialogInterface dialogInterface, int i9) {
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /* renamed from: Y0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m9655Y0() {
        int i9;
        int i10 = this.f8733h.get(7);
        int i11 = this.f8733h.get(11);
        if (7 == i10) {
            i9 = 2;
        } else if (1 != i10) {
            i9 = 9 <= i11 ? 6 == i10 ? 3 : 1 : 0;
        }
        this.f8733h.add(5, i9);
        this.f8733h.set(11, 9);
        this.f8733h.set(12, 0);
        this.f8733h.set(13, 0);
        this.f8733h.set(14, 0);
    }

    /* renamed from: Z0 */
    public boolean m9656Z0() {
        return (this.f8729d.isEnabled() && this.f8730e.isEnabled()) ? false : true;
    }

    /* renamed from: k1 */
    public final void m9657k1() {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setTitle(R.string.warning_timer_title);
        builderM16382a.setMessage(R.string.warning_timer_picker);
        builderM16382a.setPositiveButton(getString(R.string.bc_dialog_button_ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.ud
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                ScheduleSendActivity.m9654j1(dialogInterface, i9);
            }
        });
        builderM16382a.show();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
        if (i9 == 5 && i10 == -1) {
            finish();
        }
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) throws Resources.NotFoundException {
        super.onCreate(bundle);
        setContentView(R.layout.activity_schedule_send);
        this.f8734i = new C4619d(this, new C1671a());
        findViewById(R.id.scrollView).setOnTouchListener(new View.OnTouchListener() { // from class: com.cyberlink.you.activity.rd
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f11144b.m9651g1(view, motionEvent);
            }
        });
        findViewById(android.R.id.content).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.cyberlink.you.activity.sd
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                this.f11175b.m9653i1();
            }
        });
        TextView textView = (TextView) findViewById(R.id.guideText);
        ImageView imageView = (ImageView) findViewById(R.id.avatar);
        ImageView imageView2 = (ImageView) findViewById(R.id.broadcast);
        Intent intent = getIntent();
        SendType sendType = SendType.DELAY_SEND;
        this.f8737l = sendType;
        String stringExtra = "";
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                SendType sendType2 = SendType.BROADCAST;
                if (sendType2.name().equals(extras.getString(sendType2.name()))) {
                    this.f8737l = sendType2;
                }
            }
            SendType sendType3 = this.f8737l;
            if (sendType3 == sendType) {
                Group group = (Group) intent.getParcelableExtra("Group");
                if (group != null) {
                    textView.setText(getString(R.string.send_it_later_guide, group.f13717d));
                    C6127a.m23470k(this, imageView, group);
                } else {
                    textView.setText(getString(R.string.self_destruct_guide, ""));
                    imageView.setImageResource(R.drawable.pic_default_group);
                }
                stringExtra = intent.getStringExtra("chatText");
            } else if (sendType3 == SendType.BROADCAST) {
                imageView.setVisibility(8);
                imageView2.setVisibility(0);
                textView.setText(getString(R.string.send_broadcast_guide));
            }
        } else {
            textView.setText(getString(R.string.self_destruct_guide, ""));
            imageView.setImageResource(R.drawable.pic_default_group);
        }
        findViewById(R.id.back).setOnClickListener(this.f8736k);
        Button button = (Button) findViewById(R.id.scheduleSend);
        this.f8731f = button;
        button.setOnClickListener(this.f8738m);
        this.f8731f.setText(this.f8737l.resButton);
        m9655Y0();
        TextView textView2 = (TextView) findViewById(R.id.date);
        this.f8729d = textView2;
        textView2.setText(new SimpleDateFormat("EEE, MMM dd, yyyy").format(this.f8733h.getTime()));
        this.f8729d.setOnClickListener(this.f8739n);
        String str = DateFormat.is24HourFormat(this) ? "HH:mm" : "h:mm a";
        TextView textView3 = (TextView) findViewById(R.id.time);
        this.f8730e = textView3;
        textView3.setText(new SimpleDateFormat(str).format(this.f8733h.getTime()));
        this.f8730e.setOnClickListener(this.f8740o);
        this.f8732g = (TextView) findViewById(R.id.InvitationSmsTopBarTitle);
        String string = getResources().getString(this.f8737l.resTitle);
        if (this.f8737l == SendType.BROADCAST) {
            string = string + "(1/2)";
        }
        this.f8732g.setText(string);
        Fragment fragmentMo1848e = getSupportFragmentManager().mo1848e("SS_MSG_FRAG");
        if (fragmentMo1848e != null) {
            this.f8735j = (MessageGeneratorFragment) fragmentMo1848e;
        } else {
            this.f8735j = new MessageGeneratorFragment();
            getSupportFragmentManager().mo1844a().m1981c(R.id.messageFragmentContainer, this.f8735j, "SS_MSG_FRAG").mo1794h();
        }
        this.f8735j.m8744v(new C1672b());
        this.f8735j.m8742t(stringExtra);
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f8734i = null;
    }
}
