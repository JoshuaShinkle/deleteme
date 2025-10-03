package com.cyberlink.meeting.page;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CalendarContract;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import com.cyberlink.meeting.doserver.C1260a;
import com.cyberlink.meeting.model.Meeting;
import com.cyberlink.meeting.page.ScheduleMeetingActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.selectfromfriendgroup.SelectFromFriendGroupActivity;
import com.cyberlink.you.feedback.PromisedTask;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.p036ui.DialogC3133q;
import com.cyberlink.you.p036ui.ViewOnClickListenerC3127k;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.gms.plus.PlusShare;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5145g;
import p116k4.C5170o0;
import p116k4.C5179r0;
import p116k4.C5187v0;
import p218v2.C6456d;

/* loaded from: classes.dex */
public class ScheduleMeetingActivity extends BaseActivity {

    /* renamed from: A */
    public String f6424A;

    /* renamed from: B */
    public WaitingRoomType f6425B;

    /* renamed from: E */
    public Calendar f6428E;

    /* renamed from: F */
    public Calendar f6429F;

    /* renamed from: G */
    public String f6430G;

    /* renamed from: H */
    public CalendarType f6431H;

    /* renamed from: c */
    public TextView f6436c;

    /* renamed from: d */
    public TextView f6437d;

    /* renamed from: e */
    public TextView f6438e;

    /* renamed from: f */
    public TextView f6439f;

    /* renamed from: g */
    public EditText f6440g;

    /* renamed from: h */
    public TextView f6441h;

    /* renamed from: i */
    public ImageView f6442i;

    /* renamed from: j */
    public TextView f6443j;

    /* renamed from: k */
    public ImageView f6444k;

    /* renamed from: l */
    public View f6445l;

    /* renamed from: m */
    public TextView f6446m;

    /* renamed from: n */
    public ImageView f6447n;

    /* renamed from: o */
    public View f6448o;

    /* renamed from: p */
    public View f6449p;

    /* renamed from: q */
    public View f6450q;

    /* renamed from: r */
    public View f6451r;

    /* renamed from: s */
    public ImageView f6452s;

    /* renamed from: t */
    public TextView f6453t;

    /* renamed from: u */
    public View f6454u;

    /* renamed from: v */
    public ImageView f6455v;

    /* renamed from: w */
    public View f6456w;

    /* renamed from: x */
    public View f6457x;

    /* renamed from: y */
    public TextView f6458y;

    /* renamed from: z */
    public View f6459z;

    /* renamed from: C */
    public List<Friend> f6426C = new ArrayList();

    /* renamed from: D */
    public List<Friend> f6427D = new ArrayList();

    /* renamed from: I */
    public View.OnClickListener f6432I = new ViewOnClickListenerC1284b();

    /* renamed from: J */
    public View.OnClickListener f6433J = new ViewOnClickListenerC1285c();

    /* renamed from: K */
    public View.OnClickListener f6434K = new ViewOnClickListenerC1286d();

    /* renamed from: L */
    public View.OnClickListener f6435L = new ViewOnClickListenerC1287e();

    public enum CalendarType {
        Start,
        End
    }

    public enum WaitingRoomType {
        Organization("NOT_IN_CREATOR_ORG"),
        Host("NOT_HOST");

        private final String condition;

        WaitingRoomType(String str) {
            this.condition = str;
        }
    }

    /* renamed from: com.cyberlink.meeting.page.ScheduleMeetingActivity$a */
    public class C1283a extends PromisedTask.AbstractC3021b<Meeting> {

        /* renamed from: j */
        public final /* synthetic */ DialogC3133q f6466j;

        /* renamed from: k */
        public final /* synthetic */ String f6467k;

        public C1283a(DialogC3133q dialogC3133q, String str) {
            this.f6466j = dialogC3133q;
            this.f6467k = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: v */
        public /* synthetic */ void m5987v(Meeting meeting, String str, DialogInterface dialogInterface, int i9) {
            try {
                ScheduleMeetingActivity.this.startActivity(new Intent("android.intent.action.INSERT").setData(CalendarContract.Events.CONTENT_URI).putExtra("beginTime", ScheduleMeetingActivity.this.f6428E.getTimeInMillis()).putExtra("endTime", ScheduleMeetingActivity.this.f6429F.getTimeInMillis()).putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, meeting.title).putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, str));
            } catch (ActivityNotFoundException unused) {
                C5187v0.m20267c(R.string.error_no_calendar_app);
            }
            ScheduleMeetingActivity.this.m5973t1();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: w */
        public /* synthetic */ void m5988w(DialogInterface dialogInterface, int i9) {
            ScheduleMeetingActivity.this.m5973t1();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: x */
        public /* synthetic */ void m5989x(String str, DialogInterface dialogInterface, int i9) {
            C5145g.m20042a(str);
            C5187v0.m20267c(R.string.clm_schedule_meeting_copy_invitation_done);
            ScheduleMeetingActivity.this.m5973t1();
        }

        /* renamed from: y */
        public static /* synthetic */ void m5990y(DialogInterface dialogInterface, int i9) {
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) {
            this.f6466j.dismiss();
            if (i9 == 404 && str.contains("Invitee not found")) {
                C5187v0.m20267c(R.string.clm_schedule_meeting_error_invite);
                return;
            }
            if (i9 == 409 && str.contains("Scheduled time conflicts")) {
                C3123g.m16382a(ScheduleMeetingActivity.this).setMessage(R.string.clm_schedule_meeting_time_conflict_error).setCancelable(false).setPositiveButton(R.string.close, new DialogInterface.OnClickListener() { // from class: o2.e1
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i10) {
                        ScheduleMeetingActivity.C1283a.m5990y(dialogInterface, i10);
                    }
                }).show();
                return;
            }
            C5187v0.m20271g("ScheduleMeeting fail | errorCode : " + i9 + " errorBody : " + str);
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: z, reason: merged with bridge method [inline-methods] */
        public void mo5703q(final Meeting meeting) throws NumberFormatException {
            final String str;
            this.f6466j.dismiss();
            String strM20172g = C5170o0.m20172g(meeting.eventId, 3, '-');
            String strM5893L0 = ScheduleMeetingActivity.m5893L0(ScheduleMeetingActivity.this.f6428E);
            String strM5893L02 = ScheduleMeetingActivity.m5893L0(ScheduleMeetingActivity.this.f6429F);
            String strM5894M0 = ScheduleMeetingActivity.m5894M0(ScheduleMeetingActivity.this.f6428E);
            if (!ScheduleMeetingActivity.this.m5969N0() || ScheduleMeetingActivity.this.f6447n.isSelected() || ScheduleMeetingActivity.this.f6444k.isSelected()) {
                String str2 = String.format(ScheduleMeetingActivity.this.getString(R.string.schedule_meeting_prefix), meeting.title, strM5893L0 + " ~ " + strM5893L02 + " (UTC" + strM5894M0 + ")", meeting.shareAddr, strM20172g);
                if (!C5170o0.m20170e(this.f6467k)) {
                    str2 = str2 + String.format(ScheduleMeetingActivity.this.getString(R.string.schedule_meeting_password), this.f6467k);
                }
                str = str2 + ScheduleMeetingActivity.this.getString(R.string.schedule_meeting_suffix);
            } else {
                UserInfo userInfoM16497V0 = CLUtility.m16497V0(ScheduleMeetingActivity.this);
                String str3 = userInfoM16497V0 != null ? userInfoM16497V0.f13778c : "";
                String str4 = String.format(ScheduleMeetingActivity.this.getString(R.string.schedule_meeting_phone_line_prefix), str3, meeting.title, strM5893L0 + " ~ " + strM5893L02 + " (UTC" + strM5894M0 + ")");
                StringBuilder sb = new StringBuilder();
                sb.append(str4);
                sb.append(ScheduleMeetingActivity.this.getString(R.string.schedule_meeting_phone_line_via_u, meeting.shareAddr));
                String string = sb.toString();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(string);
                ScheduleMeetingActivity scheduleMeetingActivity = ScheduleMeetingActivity.this;
                sb2.append(scheduleMeetingActivity.getString(R.string.schedule_meeting_phone_line_via_phone, CLUtility.m16456K0(scheduleMeetingActivity), strM20172g));
                String string2 = sb2.toString();
                if (!C5170o0.m20170e(this.f6467k)) {
                    string2 = string2 + String.format(ScheduleMeetingActivity.this.getString(R.string.schedule_meeting_phone_line_password), this.f6467k);
                }
                str = (string2 + "-------------------------------------------------------------\n") + ScheduleMeetingActivity.this.getString(R.string.schedule_meeting_phone_line_suffix, CLUtility.m16565n1());
            }
            AlertDialog alertDialogShow = C3123g.m16382a(ScheduleMeetingActivity.this).setMessage(R.string.clm_schedule_meeting_remind_add_to_calendar).setPositiveButton(R.string.clm_schedule_meeting_add_to_calendar, new DialogInterface.OnClickListener() { // from class: o2.f1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f18324b.m5987v(meeting, str, dialogInterface, i9);
                }
            }).setNegativeButton(R.string.close, new DialogInterface.OnClickListener() { // from class: o2.g1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f18331b.m5988w(dialogInterface, i9);
                }
            }).setNeutralButton(R.string.clm_schedule_meeting_copy_invitation, new DialogInterface.OnClickListener() { // from class: o2.h1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f18336b.m5989x(str, dialogInterface, i9);
                }
            }).show();
            TextView textView = (TextView) alertDialogShow.findViewById(android.R.id.message);
            if (textView != null) {
                textView.setTextSize(0, Globals.m7374X0().getDimensionPixelSize(R.dimen.t20dp));
                textView.setGravity(17);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1, 3.0f);
            layoutParams.gravity = 17;
            Button button = alertDialogShow.getButton(-1);
            if (button != null) {
                button.setLayoutParams(layoutParams);
                button.setTextSize(0, Globals.m7374X0().getDimensionPixelSize(R.dimen.t16dp));
                C5179r0.m20247b(button, 2);
            }
            Button button2 = alertDialogShow.getButton(-2);
            if (button2 != null) {
                button2.setLayoutParams(layoutParams);
                button2.setTextSize(0, Globals.m7374X0().getDimensionPixelSize(R.dimen.t16dp));
                C5179r0.m20247b(button2, 2);
            }
            Button button3 = alertDialogShow.getButton(-3);
            if (button3 != null) {
                button3.setLayoutParams(layoutParams);
                button3.setTextSize(0, Globals.m7374X0().getDimensionPixelSize(R.dimen.t16dp));
                C5179r0.m20247b(button3, 2);
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.page.ScheduleMeetingActivity$b */
    public class ViewOnClickListenerC1284b implements View.OnClickListener {
        public ViewOnClickListenerC1284b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ScheduleMeetingActivity.this.f6431H = CalendarType.Start;
            ScheduleMeetingActivity.this.f6436c.setEnabled(false);
            new DialogFragmentC1288f().show(ScheduleMeetingActivity.this.getFragmentManager(), "");
        }
    }

    /* renamed from: com.cyberlink.meeting.page.ScheduleMeetingActivity$c */
    public class ViewOnClickListenerC1285c implements View.OnClickListener {
        public ViewOnClickListenerC1285c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ScheduleMeetingActivity.this.f6431H = CalendarType.Start;
            ScheduleMeetingActivity.this.m5975u1();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.ScheduleMeetingActivity$d */
    public class ViewOnClickListenerC1286d implements View.OnClickListener {
        public ViewOnClickListenerC1286d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ScheduleMeetingActivity.this.f6431H = CalendarType.End;
            ScheduleMeetingActivity.this.f6438e.setEnabled(false);
            new DialogFragmentC1288f().show(ScheduleMeetingActivity.this.getFragmentManager(), "");
        }
    }

    /* renamed from: com.cyberlink.meeting.page.ScheduleMeetingActivity$e */
    public class ViewOnClickListenerC1287e implements View.OnClickListener {
        public ViewOnClickListenerC1287e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ScheduleMeetingActivity.this.f6431H = CalendarType.End;
            ScheduleMeetingActivity.this.m5975u1();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.ScheduleMeetingActivity$f */
    public static class DialogFragmentC1288f extends DialogFragment {
        /* renamed from: c */
        public static /* synthetic */ void m5994c(DialogInterface dialogInterface, int i9) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m5995d(Calendar calendar, TextView textView, DatePicker datePicker, int i9, int i10, int i11) {
            Calendar calendar2 = (Calendar) calendar.clone();
            calendar2.set(i9, i10, i11);
            if (calendar2.getTimeInMillis() < System.currentTimeMillis()) {
                C3123g.m16382a(getActivity()).setMessage(R.string.clm_schedule_meeting_time_in_past_error).setCancelable(false).setPositiveButton(R.string.close, new DialogInterface.OnClickListener() { // from class: o2.j1
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i12) {
                        ScheduleMeetingActivity.DialogFragmentC1288f.m5994c(dialogInterface, i12);
                    }
                }).show();
                return;
            }
            calendar.set(5, i11);
            calendar.set(2, i10);
            calendar.set(1, i9);
            textView.setText(ScheduleMeetingActivity.m5887G0("EEE, M/dd/yyyy", calendar));
        }

        @Override // android.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            final Calendar calendarM5966I0 = ((ScheduleMeetingActivity) getActivity()).m5966I0();
            final TextView textViewM5967J0 = ((ScheduleMeetingActivity) getActivity()).m5967J0();
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() { // from class: o2.i1
                @Override // android.app.DatePickerDialog.OnDateSetListener
                public final void onDateSet(DatePicker datePicker, int i9, int i10, int i11) {
                    this.f18341a.m5995d(calendarM5966I0, textViewM5967J0, datePicker, i9, i10, i11);
                }
            }, calendarM5966I0.get(1), calendarM5966I0.get(2), calendarM5966I0.get(5));
            DatePicker datePicker = datePickerDialog.getDatePicker();
            Calendar calendar = (Calendar) Calendar.getInstance().clone();
            calendar.set(11, 0);
            calendar.set(12, 0);
            datePicker.setMinDate(calendar.getTime().getTime());
            if (((ScheduleMeetingActivity) getActivity()).f6429F.equals(calendarM5966I0)) {
                datePicker.setMaxDate(((Calendar) ((ScheduleMeetingActivity) getActivity()).f6428E.clone()).getTime().getTime() + DateUtils.MILLIS_PER_DAY);
            }
            return datePickerDialog;
        }

        @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) throws Resources.NotFoundException {
            super.onDismiss(dialogInterface);
            ((ScheduleMeetingActivity) getActivity()).m5967J0().setEnabled(true);
            ((ScheduleMeetingActivity) getActivity()).m5971r0();
            ((ScheduleMeetingActivity) getActivity()).m5972s0();
        }
    }

    /* renamed from: G0 */
    public static String m5887G0(String str, Calendar calendar) {
        return new SimpleDateFormat(str, Locale.getDefault()).format(calendar.getTime());
    }

    /* renamed from: H0 */
    public static String m5889H0(Calendar calendar) {
        return m5887G0("yyyy-MM-dd'T'HH:mm:ssZZZZZ", calendar);
    }

    /* renamed from: L0 */
    public static String m5893L0(Calendar calendar) {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault()).format(calendar.getTime());
    }

    /* renamed from: M0 */
    public static String m5894M0(Calendar calendar) throws NumberFormatException {
        String str = new SimpleDateFormat("Z", Locale.getDefault()).format(calendar.getTime());
        return str.substring(0, 1) + String.valueOf(Integer.parseInt(str.substring(1, 3)));
    }

    /* renamed from: P0 */
    public static /* synthetic */ void m5898P0(DialogInterface dialogInterface, int i9) {
    }

    /* renamed from: Q0 */
    public static /* synthetic */ void m5900Q0(DialogInterface dialogInterface, int i9) {
    }

    /* renamed from: R0 */
    public static /* synthetic */ void m5901R0(DialogInterface dialogInterface, int i9) {
    }

    /* renamed from: S0 */
    public static /* synthetic */ void m5902S0(DialogInterface dialogInterface, int i9) {
    }

    /* renamed from: T0 */
    public static /* synthetic */ void m5904T0(DialogInterface dialogInterface, int i9) {
    }

    /* renamed from: U0 */
    public static /* synthetic */ void m5905U0(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: V0 */
    public /* synthetic */ void m5907V0(View view) {
        m5973t1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: W0 */
    public /* synthetic */ void m5908W0(View view) throws JSONException {
        m5965F0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: X0 */
    public /* synthetic */ void m5910X0(View view) {
        m5974u0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Y0 */
    public /* synthetic */ void m5912Y0(View view) {
        m5963D0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Z0 */
    public /* synthetic */ void m5914Z0(View view) {
        m5979x0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a1 */
    public /* synthetic */ void m5915a1(View view) {
        m5980y0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b1 */
    public /* synthetic */ void m5917b1(View view) {
        m5977w0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c1 */
    public /* synthetic */ void m5919c1(View view) {
        m5964E0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d1 */
    public /* synthetic */ void m5921d1(View view) {
        m5962C0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e1 */
    public /* synthetic */ void m5923e1(View view) {
        m5981z0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f1 */
    public /* synthetic */ void m5924f1(View view) {
        m5978w1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g1 */
    public /* synthetic */ void m5926g1(View view) {
        m5976v1();
    }

    /* renamed from: h1 */
    public static /* synthetic */ void m5927h1(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i1 */
    public /* synthetic */ void m5930i1(Calendar calendar, TextView textView, TimePicker timePicker, int i9, int i10) {
        Calendar calendar2 = (Calendar) calendar.clone();
        calendar2.set(11, i9);
        calendar2.set(12, i10);
        if (calendar2.getTimeInMillis() < System.currentTimeMillis()) {
            C3123g.m16382a(this).setMessage(R.string.clm_schedule_meeting_time_in_past_error).setCancelable(false).setPositiveButton(R.string.close, new DialogInterface.OnClickListener() { // from class: o2.k0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i11) {
                    ScheduleMeetingActivity.m5927h1(dialogInterface, i11);
                }
            }).show();
            return;
        }
        calendar.set(11, i9);
        calendar.set(12, i10);
        textView.setText(m5887G0(DateFormat.is24HourFormat(this) ? "HH:mm" : "h:mm a", calendar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j1 */
    public /* synthetic */ void m5933j1(DialogInterface dialogInterface) throws Resources.NotFoundException {
        m5968K0().setEnabled(true);
        m5971r0();
        m5972s0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k1 */
    public /* synthetic */ void m5936k1(Dialog dialog, EditText editText, View view) {
        dialog.dismiss();
        String string = editText.getText().toString();
        this.f6424A = string;
        if (TextUtils.isEmpty(string)) {
            this.f6424A = getResources().getString(R.string.clm_schedule_meeting_waiting_room_default_description);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m1 */
    public /* synthetic */ void m5941m1() {
        CLUtility.m16589t1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n1 */
    public /* synthetic */ void m5944n1(DialogInterface dialogInterface) {
        new Handler().postDelayed(new Runnable() { // from class: o2.v0
            @Override // java.lang.Runnable
            public final void run() {
                this.f18363b.m5941m1();
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o1 */
    public /* synthetic */ void m5947o1(TextView textView, TextView textView2, View view) {
        textView.setTextColor(getResources().getColor(R.color.you_color_normal_blue));
        textView2.setTextColor(getResources().getColor(R.color.black));
        this.f6425B = WaitingRoomType.Organization;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p1 */
    public /* synthetic */ void m5949p1(TextView textView, TextView textView2, View view) {
        textView.setTextColor(getResources().getColor(R.color.black));
        textView2.setTextColor(getResources().getColor(R.color.you_color_normal_blue));
        this.f6425B = WaitingRoomType.Host;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q1 */
    public /* synthetic */ void m5952q1(Dialog dialog, View view) {
        dialog.dismiss();
        if (WaitingRoomType.Organization == this.f6425B) {
            this.f6458y.setText(R.string.clm_schedule_meeting_waiting_room_organization);
        } else {
            this.f6458y.setText(R.string.clm_schedule_meeting_waiting_room_host);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r1 */
    public /* synthetic */ void m5954r1() {
        CLUtility.m16589t1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s1 */
    public /* synthetic */ void m5956s1(DialogInterface dialogInterface) {
        new Handler().postDelayed(new Runnable() { // from class: o2.u0
            @Override // java.lang.Runnable
            public final void run() {
                this.f18361b.m5954r1();
            }
        }, 100L);
    }

    /* renamed from: C0 */
    public final void m5962C0() {
        this.f6450q.setSelected(!r0.isSelected());
    }

    /* renamed from: D0 */
    public final void m5963D0() {
        Intent intent = new Intent(getApplicationContext(), (Class<?>) SelectFromFriendGroupActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean("scheduleHost", true);
        bundle.putInt("selectLimit", 150);
        Globals.C1408b.m7655b().m7657d("INTENT_PREV_SELECTED_USERS", this.f6427D);
        bundle.putString("page", "scheduleMeetingPage");
        intent.putExtras(bundle);
        startActivityForResult(intent, 2);
    }

    /* renamed from: E0 */
    public final void m5964E0() {
        Intent intent = new Intent(getApplicationContext(), (Class<?>) SelectFromFriendGroupActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean("invitationOnly", true);
        bundle.putInt("selectLimit", 150);
        Globals.C1408b.m7655b().m7657d("INTENT_PREV_SELECTED_USERS", this.f6426C);
        bundle.putString("page", "scheduleMeetingPage");
        intent.putExtras(bundle);
        startActivityForResult(intent, 1);
    }

    /* renamed from: F0 */
    public final void m5965F0() throws JSONException {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        JSONException e9;
        Globals.m7388i0().m7514Y3(this.f6425B.condition);
        CLUtility.m16589t1(this);
        if (this.f6440g.length() <= 0 && C5170o0.m20170e(this.f6430G)) {
            C3123g.m16382a(this).setMessage(R.string.clm_schedule_meeting_input_subject).setCancelable(false).setPositiveButton(R.string.close, new DialogInterface.OnClickListener() { // from class: o2.p0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    ScheduleMeetingActivity.m5900Q0(dialogInterface, i9);
                }
            }).show();
            return;
        }
        if (this.f6444k.isSelected() && this.f6443j.length() < 4) {
            C3123g.m16382a(this).setMessage(R.string.registration_email_password_rule).setCancelable(false).setPositiveButton(R.string.close, new DialogInterface.OnClickListener() { // from class: o2.q0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    ScheduleMeetingActivity.m5901R0(dialogInterface, i9);
                }
            }).show();
            return;
        }
        if (this.f6428E.getTime().before(new Date(FriendsClient.m15646K()))) {
            C3123g.m16382a(this).setMessage(R.string.clm_schedule_meeting_time_in_past_error).setCancelable(false).setPositiveButton(R.string.close, new DialogInterface.OnClickListener() { // from class: o2.r0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    ScheduleMeetingActivity.m5902S0(dialogInterface, i9);
                }
            }).show();
            return;
        }
        if (this.f6429F.getTimeInMillis() <= this.f6428E.getTimeInMillis()) {
            C3123g.m16382a(this).setMessage(R.string.clm_schedule_meeting_error_duration).setCancelable(false).setPositiveButton(R.string.close, new DialogInterface.OnClickListener() { // from class: o2.s0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    ScheduleMeetingActivity.m5904T0(dialogInterface, i9);
                }
            }).show();
            return;
        }
        if (!C6456d.m24714D().m24748G()) {
            C3123g.m16382a(this).setMessage(R.string.error_server_response).setCancelable(false).setPositiveButton(R.string.close, new DialogInterface.OnClickListener() { // from class: o2.t0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    ScheduleMeetingActivity.m5905U0(dialogInterface, i9);
                }
            }).show();
            return;
        }
        String strM7506X = Globals.m7388i0().m7506X();
        String string = this.f6440g.length() <= 0 ? this.f6430G : this.f6440g.getText().toString();
        String strM5889H0 = m5889H0(this.f6428E);
        String strM5889H02 = m5889H0(this.f6429F);
        String id = this.f6428E.getTimeZone().getID();
        String string2 = this.f6444k.isSelected() ? this.f6443j.getText().toString() : null;
        if (this.f6447n.isSelected()) {
            jSONObject = new JSONObject();
            try {
                jSONObject.put("isInvitationOnly", this.f6447n.isSelected());
                jSONObject.put("needFaceVerification", this.f6450q.isSelected());
                for (Friend friend : this.f6427D) {
                    if (!this.f6426C.contains(friend)) {
                        this.f6426C.add(friend);
                    }
                }
                JSONArray jSONArray = new JSONArray();
                Iterator<Friend> it = this.f6426C.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next().f13645c);
                }
                jSONObject.put("inviteeUids", jSONArray);
            } catch (JSONException e10) {
                e10.printStackTrace();
            }
        } else {
            jSONObject = null;
        }
        ArrayList arrayList = new ArrayList();
        if (this.f6452s.isSelected()) {
            Iterator<Friend> it2 = this.f6427D.iterator();
            while (it2.hasNext()) {
                arrayList.add(Long.valueOf(it2.next().f13645c));
            }
            arrayList.add(Globals.m7388i0().m7568k1());
        }
        if (this.f6455v.isSelected()) {
            try {
                jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("condition", this.f6425B.condition);
                    if (TextUtils.isEmpty(this.f6424A)) {
                        this.f6424A = getResources().getString(R.string.clm_schedule_meeting_waiting_room_default_description);
                    }
                    jSONObject2.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, this.f6424A);
                } catch (JSONException e11) {
                    e9 = e11;
                    e9.printStackTrace();
                    C1260a.m5685w(strM7506X, string, string2, jSONObject, arrayList, strM5889H0, strM5889H02, id, jSONObject2, Boolean.valueOf(this.f6442i.isSelected())).m15439e(new C1283a(new DialogC3133q.b(this).m16411b(), string2));
                }
            } catch (JSONException e12) {
                jSONObject2 = null;
                e9 = e12;
            }
        } else {
            jSONObject2 = null;
        }
        C1260a.m5685w(strM7506X, string, string2, jSONObject, arrayList, strM5889H0, strM5889H02, id, jSONObject2, Boolean.valueOf(this.f6442i.isSelected())).m15439e(new C1283a(new DialogC3133q.b(this).m16411b(), string2));
    }

    /* renamed from: I0 */
    public Calendar m5966I0() {
        return this.f6431H == CalendarType.Start ? this.f6428E : this.f6429F;
    }

    /* renamed from: J0 */
    public TextView m5967J0() {
        return this.f6431H == CalendarType.Start ? this.f6436c : this.f6438e;
    }

    /* renamed from: K0 */
    public TextView m5968K0() {
        return this.f6431H == CalendarType.Start ? this.f6437d : this.f6439f;
    }

    /* renamed from: N0 */
    public final boolean m5969N0() {
        for (String str : Globals.m7388i0().m7455M0().split(",")) {
            if ("PSTN".equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: O0 */
    public final void m5970O0() throws Resources.NotFoundException {
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        this.f6428E = calendar;
        calendar.add(12, 10);
        if (this.f6428E.get(12) != 0) {
            this.f6428E.add(10, 1);
        }
        this.f6428E.set(12, 0);
        TextView textView = (TextView) findViewById(R.id.startDate);
        this.f6436c = textView;
        textView.setText(m5887G0("EEE, M/dd/yyyy", this.f6428E));
        this.f6436c.setOnClickListener(this.f6432I);
        String str = DateFormat.is24HourFormat(this) ? "HH:mm" : "h:mm a";
        TextView textView2 = (TextView) findViewById(R.id.startTime);
        this.f6437d = textView2;
        textView2.setText(m5887G0(str, this.f6428E));
        this.f6437d.setOnClickListener(this.f6433J);
        Calendar calendar2 = (Calendar) this.f6428E.clone();
        this.f6429F = calendar2;
        calendar2.add(10, 1);
        TextView textView3 = (TextView) findViewById(R.id.endDate);
        this.f6438e = textView3;
        textView3.setText(m5887G0("EEE, M/dd/yyyy", this.f6429F));
        this.f6438e.setOnClickListener(this.f6434K);
        TextView textView4 = (TextView) findViewById(R.id.endTime);
        this.f6439f = textView4;
        textView4.setText(m5887G0(str, this.f6429F));
        this.f6439f.setOnClickListener(this.f6435L);
        findViewById(R.id.ScheduleMeetingBackBtn).setOnClickListener(new View.OnClickListener() { // from class: o2.a0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f18308b.m5907V0(view);
            }
        });
        View viewFindViewById = findViewById(R.id.ScheduleMeetingBtn);
        this.f6451r = viewFindViewById;
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: o2.x0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws JSONException {
                this.f18367b.m5908W0(view);
            }
        });
        this.f6441h = (TextView) findViewById(R.id.NotificationTextView);
        ImageView imageView = (ImageView) findViewById(R.id.NotificationCheckBox);
        this.f6442i = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: o2.y0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f18369b.m5914Z0(view);
            }
        });
        ImageView imageView2 = (ImageView) findViewById(R.id.ProtectCheckBox);
        this.f6444k = imageView2;
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: o2.z0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f18371b.m5915a1(view);
            }
        });
        View viewFindViewById2 = findViewById(R.id.PasswordLayout);
        this.f6445l = viewFindViewById2;
        viewFindViewById2.setVisibility(8);
        this.f6440g = (EditText) findViewById(R.id.idEditText);
        this.f6443j = (TextView) findViewById(R.id.PasswordEditText);
        this.f6446m = (TextView) findViewById(R.id.InvitationSizeView);
        ImageView imageView3 = (ImageView) findViewById(R.id.InvitationCheckBox);
        this.f6447n = imageView3;
        imageView3.setOnClickListener(new View.OnClickListener() { // from class: o2.a1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f18309b.m5917b1(view);
            }
        });
        View viewFindViewById3 = findViewById(R.id.GotoInvitePage);
        this.f6448o = viewFindViewById3;
        viewFindViewById3.setOnClickListener(new View.OnClickListener() { // from class: o2.b1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f18312b.m5919c1(view);
            }
        });
        this.f6446m.setText(getString(R.string.clm_schedule_invite_people) + " : " + this.f6426C.size());
        View viewFindViewById4 = findViewById(R.id.FaceJoinLayout);
        this.f6449p = viewFindViewById4;
        viewFindViewById4.setVisibility(8);
        View viewFindViewById5 = findViewById(R.id.FaceJoinCheckBox);
        this.f6450q = viewFindViewById5;
        viewFindViewById5.setOnClickListener(new View.OnClickListener() { // from class: o2.c1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f18315b.m5921d1(view);
            }
        });
        this.f6455v = (ImageView) findViewById(R.id.WaitingRoomCheckBox);
        this.f6456w = findViewById(R.id.waiting_room_enabled_content);
        this.f6457x = findViewById(R.id.GotoWaitingRoomOptionPage);
        this.f6458y = (TextView) findViewById(R.id.WaitingRoomOption);
        this.f6459z = findViewById(R.id.WaitingRoomDescription);
        this.f6455v.setOnClickListener(new View.OnClickListener() { // from class: o2.d1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f18317b.m5923e1(view);
            }
        });
        boolean zM7563j2 = Globals.m7388i0().m7563j2();
        this.f6455v.setSelected(zM7563j2);
        if (zM7563j2) {
            this.f6456w.setVisibility(0);
        } else {
            this.f6456w.setVisibility(8);
        }
        this.f6457x.setOnClickListener(new View.OnClickListener() { // from class: o2.b0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f18311b.m5924f1(view);
            }
        });
        this.f6459z.setOnClickListener(new View.OnClickListener() { // from class: o2.c0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f18314b.m5926g1(view);
            }
        });
        String strM7520a1 = Globals.m7388i0().m7520a1();
        if (TextUtils.isEmpty(strM7520a1) || WaitingRoomType.Host.condition.equals(strM7520a1)) {
            this.f6425B = WaitingRoomType.Host;
            this.f6458y.setText(R.string.clm_schedule_meeting_waiting_room_host);
        } else {
            this.f6425B = WaitingRoomType.Organization;
        }
        this.f6452s = (ImageView) findViewById(R.id.HostCheckBox);
        this.f6453t = (TextView) findViewById(R.id.HostSizeView);
        this.f6454u = findViewById(R.id.gotoHostPage);
        this.f6453t.setText(getString(R.string.clm_schedule_meeting_host_btn) + " : " + (this.f6427D.size() + 1));
        this.f6452s.setOnClickListener(new View.OnClickListener() { // from class: o2.l0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f18348b.m5910X0(view);
            }
        });
        this.f6454u.setOnClickListener(new View.OnClickListener() { // from class: o2.w0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f18365b.m5912Y0(view);
            }
        });
        this.f6452s.callOnClick();
        UserInfo userInfoM16497V0 = CLUtility.m16497V0(this);
        if (userInfoM16497V0 != null && !C5170o0.m20170e(userInfoM16497V0.f13778c)) {
            this.f6430G = userInfoM16497V0.f13778c + "'s meeting";
            this.f6440g.requestFocus();
            this.f6440g.setHint(this.f6430G);
            this.f6440g.setText(this.f6430G);
            EditText editText = this.f6440g;
            editText.setSelection(editText.length());
        }
        m5972s0();
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
        if (i9 != 1) {
            if (i9 == 2 && i10 == -1) {
                this.f6427D = Globals.C1408b.m7655b().m7656c("INTENT_RESULT_SELECTED_USERS_LIST");
                this.f6453t.setText(getString(R.string.clm_schedule_meeting_host_btn) + " : " + (this.f6427D.size() + 1));
                return;
            }
            return;
        }
        if (i10 == -1) {
            List<Friend> listM7656c = Globals.C1408b.m7655b().m7656c("INTENT_RESULT_SELECTED_USERS_LIST");
            this.f6426C = listM7656c;
            if (listM7656c.size() > 0) {
                this.f6451r.setEnabled(true);
            } else {
                this.f6451r.setEnabled(false);
            }
            this.f6446m.setText(getString(R.string.clm_schedule_invite_people) + " : " + this.f6426C.size());
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) throws Resources.NotFoundException {
        super.onCreate(bundle);
        setContentView(R.layout.activity_schedule_meeting);
        m5970O0();
    }

    /* renamed from: r0 */
    public final void m5971r0() {
        if (this.f6428E.getTimeInMillis() >= this.f6429F.getTimeInMillis()) {
            Calendar calendar = (Calendar) this.f6428E.clone();
            this.f6429F = calendar;
            calendar.add(10, 1);
            this.f6438e.setText(m5887G0("EEE, M/dd/yyyy", this.f6429F));
            this.f6439f.setText(m5887G0(DateFormat.is24HourFormat(this) ? "HH:mm" : "h:mm a", this.f6429F));
            return;
        }
        if (this.f6429F.getTimeInMillis() - this.f6428E.getTimeInMillis() > DateUtils.MILLIS_PER_DAY) {
            this.f6429F.setTimeInMillis(this.f6428E.getTimeInMillis() + DateUtils.MILLIS_PER_DAY);
            this.f6438e.setText(m5887G0("EEE, M/dd/yyyy", this.f6429F));
            this.f6439f.setText(m5887G0(DateFormat.is24HourFormat(this) ? "HH:mm" : "h:mm a", this.f6429F));
            C3123g.m16382a(this).setMessage(R.string.clm_schedule_meeting_duration_error).setCancelable(false).setPositiveButton(R.string.close, new DialogInterface.OnClickListener() { // from class: o2.d0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    ScheduleMeetingActivity.m5898P0(dialogInterface, i9);
                }
            }).show();
        }
    }

    /* renamed from: s0 */
    public final void m5972s0() throws Resources.NotFoundException {
        TextView textView = (TextView) findViewById(R.id.ScheduleMeetingDuration);
        long timeInMillis = this.f6429F.getTimeInMillis() - this.f6428E.getTimeInMillis();
        if (timeInMillis <= 0) {
            textView.setText("");
            textView.setVisibility(4);
            return;
        }
        long j9 = timeInMillis / 1000;
        String string = getResources().getString(R.string.clm_schedule_meeting_duration);
        TimeUnit timeUnit = TimeUnit.SECONDS;
        int days = (int) timeUnit.toDays(j9);
        long hours = timeUnit.toHours(j9) - (days * 24);
        long minutes = timeUnit.toMinutes(j9) - (timeUnit.toHours(j9) * 60);
        if (days > 0) {
            string = string + getResources().getQuantityString(R.plurals.self_destruct_days, days, Integer.valueOf(days)) + StringUtils.SPACE;
        }
        if (hours > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            int i9 = (int) hours;
            sb.append(getResources().getQuantityString(R.plurals.self_destruct_hours, i9, Integer.valueOf(i9)));
            sb.append(StringUtils.SPACE);
            string = sb.toString();
        }
        if (minutes > 0) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(string);
            int i10 = (int) minutes;
            sb2.append(getResources().getQuantityString(R.plurals.self_destruct_minutes, i10, Integer.valueOf(i10)));
            sb2.append(StringUtils.SPACE);
            string = sb2.toString();
        }
        textView.setVisibility(0);
        textView.setText(string);
    }

    /* renamed from: t1 */
    public final void m5973t1() {
        finish();
    }

    /* renamed from: u0 */
    public final void m5974u0() {
        boolean z8 = !this.f6452s.isSelected();
        this.f6452s.setSelected(z8);
        if (z8) {
            this.f6454u.setVisibility(0);
        } else {
            this.f6454u.setVisibility(8);
        }
    }

    /* renamed from: u1 */
    public final void m5975u1() {
        m5968K0().setEnabled(true);
        final Calendar calendarM5966I0 = m5966I0();
        final TextView textViewM5968K0 = m5968K0();
        new ViewOnClickListenerC3127k(this, new TimePickerDialog.OnTimeSetListener() { // from class: o2.e0
            @Override // android.app.TimePickerDialog.OnTimeSetListener
            public final void onTimeSet(TimePicker timePicker, int i9, int i10) {
                this.f18319a.m5930i1(calendarM5966I0, textViewM5968K0, timePicker, i9, i10);
            }
        }, new DialogInterface.OnDismissListener() { // from class: o2.f0
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) throws Resources.NotFoundException {
                this.f18323b.m5933j1(dialogInterface);
            }
        }, calendarM5966I0.get(11), calendarM5966I0.get(12), DateFormat.is24HourFormat(this)).m16389b();
    }

    /* renamed from: v1 */
    public final void m5976v1() {
        final Dialog dialogM16384c = C3123g.m16384c(this);
        View viewInflate = LayoutInflater.from(this).inflate(R.layout.dialog_waiting_room_description, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.waitingRoomDescriptionTitle);
        final EditText editText = (EditText) viewInflate.findViewById(R.id.descriptionEdit);
        textView.setText(R.string.clm_schedule_meeting_waiting_room_custom_description_hint);
        if (TextUtils.isEmpty(this.f6424A)) {
            this.f6424A = getResources().getString(R.string.clm_schedule_meeting_waiting_room_default_description);
        }
        editText.setText(this.f6424A);
        viewInflate.findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() { // from class: o2.m0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f18349b.m5936k1(dialogM16384c, editText, view);
            }
        });
        viewInflate.findViewById(R.id.clearEditText).setOnClickListener(new View.OnClickListener() { // from class: o2.n0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                editText.setText("");
            }
        });
        dialogM16384c.setContentView(viewInflate);
        dialogM16384c.show();
        dialogM16384c.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: o2.o0
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f18356b.m5944n1(dialogInterface);
            }
        });
    }

    /* renamed from: w0 */
    public final void m5977w0() {
        boolean z8 = !this.f6447n.isSelected();
        this.f6447n.setSelected(z8);
        if (!z8) {
            this.f6451r.setEnabled(true);
            this.f6448o.setVisibility(8);
        } else {
            if (this.f6426C.size() > 0) {
                this.f6451r.setEnabled(true);
            } else {
                this.f6451r.setEnabled(false);
            }
            this.f6448o.setVisibility(0);
        }
    }

    /* renamed from: w1 */
    public final void m5978w1() {
        final Dialog dialogM16384c = C3123g.m16384c(this);
        dialogM16384c.setContentView(R.layout.dialog_waiting_room_option);
        final TextView textView = (TextView) dialogM16384c.findViewById(R.id.OrganizationOption);
        final TextView textView2 = (TextView) dialogM16384c.findViewById(R.id.HostOption);
        dialogM16384c.setCancelable(false);
        if (TextUtils.isEmpty(Globals.m7388i0().m7442J0())) {
            textView.setVisibility(8);
        }
        if (WaitingRoomType.Organization == this.f6425B) {
            textView.setTextColor(getResources().getColor(R.color.you_color_normal_blue));
        } else {
            textView2.setTextColor(getResources().getColor(R.color.you_color_normal_blue));
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: o2.g0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f18328b.m5947o1(textView, textView2, view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: o2.h0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f18333b.m5949p1(textView, textView2, view);
            }
        });
        dialogM16384c.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() { // from class: o2.i0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f18339b.m5952q1(dialogM16384c, view);
            }
        });
        dialogM16384c.show();
        dialogM16384c.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: o2.j0
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f18345b.m5956s1(dialogInterface);
            }
        });
    }

    /* renamed from: x0 */
    public final void m5979x0() {
        this.f6442i.setSelected(!this.f6442i.isSelected());
    }

    /* renamed from: y0 */
    public final void m5980y0() {
        boolean z8 = !this.f6444k.isSelected();
        this.f6444k.setSelected(z8);
        if (z8) {
            this.f6445l.setVisibility(0);
        } else {
            this.f6445l.setVisibility(8);
        }
    }

    /* renamed from: z0 */
    public final void m5981z0() {
        boolean z8 = !this.f6455v.isSelected();
        this.f6455v.setSelected(z8);
        if (z8) {
            this.f6456w.setVisibility(0);
        } else {
            this.f6456w.setVisibility(8);
        }
        Globals.m7388i0().m7576l4(z8);
    }
}
