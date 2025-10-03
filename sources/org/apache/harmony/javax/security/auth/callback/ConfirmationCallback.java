package org.apache.harmony.javax.security.auth.callback;

import java.io.Serializable;

/* loaded from: classes.dex */
public class ConfirmationCallback implements Callback, Serializable {
    public static final int CANCEL = 2;
    public static final int ERROR = 2;
    public static final int INFORMATION = 0;

    /* renamed from: NO */
    public static final int f19120NO = 1;

    /* renamed from: OK */
    public static final int f19121OK = 3;
    public static final int OK_CANCEL_OPTION = 2;
    public static final int UNSPECIFIED_OPTION = -1;
    public static final int WARNING = 1;
    public static final int YES = 0;
    public static final int YES_NO_CANCEL_OPTION = 1;
    public static final int YES_NO_OPTION = 0;
    private static final long serialVersionUID = -9095656433782481624L;
    private int defaultOption;
    private int messageType;
    private int optionType;
    private String[] options;
    private String prompt;
    private int selection;

    public ConfirmationCallback(int i9, int i10, int i11) {
        this.optionType = -1;
        if (i9 > 2 || i9 < 0) {
            throw new IllegalArgumentException("auth.16");
        }
        if (i10 != 0) {
            if (i10 != 1) {
                if (i10 != 2) {
                    throw new IllegalArgumentException("auth.18");
                }
                if (i11 != 3 && i11 != 2) {
                    throw new IllegalArgumentException("auth.17");
                }
            } else if (i11 != 0 && i11 != 1 && i11 != 2) {
                throw new IllegalArgumentException("auth.17");
            }
        } else if (i11 != 0 && i11 != 1) {
            throw new IllegalArgumentException("auth.17");
        }
        this.messageType = i9;
        this.optionType = i10;
        this.defaultOption = i11;
    }

    public int getDefaultOption() {
        return this.defaultOption;
    }

    public int getMessageType() {
        return this.messageType;
    }

    public int getOptionType() {
        return this.optionType;
    }

    public String[] getOptions() {
        return this.options;
    }

    public String getPrompt() {
        return this.prompt;
    }

    public int getSelectedIndex() {
        return this.selection;
    }

    public void setSelectedIndex(int i9) {
        String[] strArr = this.options;
        if (strArr != null) {
            if (i9 < 0 || i9 > strArr.length) {
                throw new ArrayIndexOutOfBoundsException("auth.1B");
            }
            this.selection = i9;
            return;
        }
        int i10 = this.optionType;
        if (i10 != 0) {
            if (i10 != 1) {
                if (i10 == 2 && i9 != 3 && i9 != 2) {
                    throw new IllegalArgumentException("auth.19");
                }
            } else if (i9 != 0 && i9 != 1 && i9 != 2) {
                throw new IllegalArgumentException("auth.19");
            }
        } else if (i9 != 0 && i9 != 1) {
            throw new IllegalArgumentException("auth.19");
        }
        this.selection = i9;
    }

    public ConfirmationCallback(int i9, String[] strArr, int i10) {
        this.optionType = -1;
        if (i9 <= 2 && i9 >= 0) {
            if (strArr != null && strArr.length != 0) {
                for (String str : strArr) {
                    if (str == null || str.length() == 0) {
                        throw new IllegalArgumentException("auth.1A");
                    }
                }
                if (i10 >= 0 && i10 < strArr.length) {
                    this.options = strArr;
                    this.defaultOption = i10;
                    this.messageType = i9;
                    return;
                }
                throw new IllegalArgumentException("auth.17");
            }
            throw new IllegalArgumentException("auth.1A");
        }
        throw new IllegalArgumentException("auth.16");
    }

    public ConfirmationCallback(String str, int i9, int i10, int i11) {
        this.optionType = -1;
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("auth.14");
        }
        if (i9 <= 2 && i9 >= 0) {
            if (i10 != 0) {
                if (i10 != 1) {
                    if (i10 != 2) {
                        throw new IllegalArgumentException("auth.18");
                    }
                    if (i11 != 3 && i11 != 2) {
                        throw new IllegalArgumentException("auth.17");
                    }
                } else if (i11 != 0 && i11 != 1 && i11 != 2) {
                    throw new IllegalArgumentException("auth.17");
                }
            } else if (i11 != 0 && i11 != 1) {
                throw new IllegalArgumentException("auth.17");
            }
            this.prompt = str;
            this.messageType = i9;
            this.optionType = i10;
            this.defaultOption = i11;
            return;
        }
        throw new IllegalArgumentException("auth.16");
    }

    public ConfirmationCallback(String str, int i9, String[] strArr, int i10) {
        this.optionType = -1;
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("auth.14");
        }
        if (i9 <= 2 && i9 >= 0) {
            if (strArr != null && strArr.length != 0) {
                for (String str2 : strArr) {
                    if (str2 == null || str2.length() == 0) {
                        throw new IllegalArgumentException("auth.1A");
                    }
                }
                if (i10 >= 0 && i10 < strArr.length) {
                    this.options = strArr;
                    this.defaultOption = i10;
                    this.messageType = i9;
                    this.prompt = str;
                    return;
                }
                throw new IllegalArgumentException("auth.17");
            }
            throw new IllegalArgumentException("auth.1A");
        }
        throw new IllegalArgumentException("auth.16");
    }
}
