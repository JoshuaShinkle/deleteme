package com.google.zxing.client.android.result;

import android.view.View;

/* loaded from: classes2.dex */
public final class ResultButtonListener implements View.OnClickListener {
    private final int index;
    private final ResultHandler resultHandler;

    public ResultButtonListener(ResultHandler resultHandler, int i9) {
        this.resultHandler = resultHandler;
        this.index = i9;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.resultHandler.handleButtonPress(this.index);
    }
}
