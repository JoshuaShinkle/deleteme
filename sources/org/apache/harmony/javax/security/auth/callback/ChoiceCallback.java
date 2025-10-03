package org.apache.harmony.javax.security.auth.callback;

import java.io.Serializable;

/* loaded from: classes.dex */
public class ChoiceCallback implements Callback, Serializable {
    private static final long serialVersionUID = -3975664071579892167L;
    private String[] choices;
    private int defaultChoice;
    private boolean multipleSelectionsAllowed;
    private String prompt;
    private int[] selections;

    public ChoiceCallback(String str, String[] strArr, int i9, boolean z8) {
        setPrompt(str);
        setChoices(strArr);
        setDefaultChoice(i9);
        this.multipleSelectionsAllowed = z8;
    }

    private void setChoices(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("auth.1C");
        }
        for (String str : strArr) {
            if (str == null || str.length() == 0) {
                throw new IllegalArgumentException("auth.1C");
            }
        }
        this.choices = strArr;
    }

    private void setDefaultChoice(int i9) {
        if (i9 < 0 || i9 >= this.choices.length) {
            throw new IllegalArgumentException("auth.1D");
        }
        this.defaultChoice = i9;
    }

    private void setPrompt(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("auth.14");
        }
        this.prompt = str;
    }

    public boolean allowMultipleSelections() {
        return this.multipleSelectionsAllowed;
    }

    public String[] getChoices() {
        return this.choices;
    }

    public int getDefaultChoice() {
        return this.defaultChoice;
    }

    public String getPrompt() {
        return this.prompt;
    }

    public int[] getSelectedIndexes() {
        return this.selections;
    }

    public void setSelectedIndex(int i9) {
        this.selections = new int[]{i9};
    }

    public void setSelectedIndexes(int[] iArr) {
        if (!this.multipleSelectionsAllowed) {
            throw new UnsupportedOperationException();
        }
        this.selections = iArr;
    }
}
