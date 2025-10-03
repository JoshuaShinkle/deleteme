package com.cyberlink.you.activity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;

/* loaded from: classes.dex */
public class ShowTextInfoActivity extends BaseActivity {
    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_show_text_info);
        if (getIntent() != null) {
            String stringExtra = getIntent().getStringExtra("displayText");
            if (stringExtra == null) {
                stringExtra = "No Info Data show \n";
            }
            TextView textView = (TextView) findViewById(R.id.ShowTextInfoTextArea);
            textView.setText(stringExtra);
            textView.setMovementMethod(new ScrollingMovementMethod());
        }
    }
}
