package com.cyberlink.you.p036ui;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import com.cyberlink.p030U.R;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.Locale;

/* renamed from: com.cyberlink.you.ui.k */
/* loaded from: classes.dex */
public class ViewOnClickListenerC3127k implements View.OnClickListener {

    /* renamed from: b */
    public final String f14404b = "TimePickerInterval";

    /* renamed from: c */
    public Dialog f14405c;

    /* renamed from: d */
    public Activity f14406d;

    /* renamed from: e */
    public TimePicker f14407e;

    /* renamed from: f */
    public final TimePickerDialog.OnTimeSetListener f14408f;

    public ViewOnClickListenerC3127k(Activity activity, TimePickerDialog.OnTimeSetListener onTimeSetListener, DialogInterface.OnDismissListener onDismissListener, int i9, int i10, boolean z8) {
        this.f14406d = activity;
        View viewInflate = activity.getLayoutInflater().inflate(R.layout.dialog_time_picker_interval, (ViewGroup) null, false);
        this.f14407e = (TimePicker) viewInflate.findViewById(R.id.timePicker);
        TextView textView = (TextView) viewInflate.findViewById(R.id.btn_ok);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.btn_cancel);
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
        this.f14407e.setIs24HourView(Boolean.valueOf(z8));
        this.f14407e.setCurrentHour(Integer.valueOf(i9));
        this.f14407e.setCurrentMinute(Integer.valueOf(i10));
        m16388a();
        Dialog dialog = new Dialog(this.f14406d, R.style.DialogBaseTheme);
        this.f14405c = dialog;
        dialog.setContentView(viewInflate);
        this.f14406d.getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        this.f14405c.getWindow().setLayout((int) (r4.widthPixels * 0.85d), -2);
        if (onDismissListener != null) {
            this.f14405c.setOnDismissListener(onDismissListener);
        }
        this.f14408f = onTimeSetListener;
    }

    /* renamed from: a */
    public final void m16388a() {
        try {
            NumberPicker numberPicker = (NumberPicker) this.f14407e.findViewById(Resources.getSystem().getIdentifier("minute", TtmlNode.ATTR_ID, "android"));
            numberPicker.setMinValue(0);
            numberPicker.setMaxValue(3);
            ArrayList arrayList = new ArrayList();
            for (int i9 = 0; i9 < 60; i9 += 15) {
                arrayList.add(String.format(Locale.getDefault(), "%02d", Integer.valueOf(i9)));
            }
            numberPicker.setDisplayedValues((String[]) arrayList.toArray(new String[0]));
        } catch (Exception e9) {
            Log.e("TimePickerInterval", "Exception: " + e9);
        }
    }

    /* renamed from: b */
    public void m16389b() {
        this.f14405c.show();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_cancel) {
            this.f14405c.cancel();
            return;
        }
        if (id == R.id.btn_ok && this.f14408f != null) {
            this.f14407e.clearFocus();
            TimePickerDialog.OnTimeSetListener onTimeSetListener = this.f14408f;
            TimePicker timePicker = this.f14407e;
            onTimeSetListener.onTimeSet(timePicker, timePicker.getHour(), this.f14407e.getMinute() * 15);
            this.f14405c.dismiss();
        }
    }
}
