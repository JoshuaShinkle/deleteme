package com.cyberlink.clrtc;

/* loaded from: classes.dex */
public enum NileError {
    MSG_TOO_LONG(-3),
    SERVER_BUSY(-2),
    RTC_ERROR(-1),
    NONE(0),
    UNKNOWN(1),
    JOIN_FAILED(2),
    HOST_CLOSED(6),
    SERVER_DOWN(7),
    SERVER_DISCONNECTED(8),
    CHANGE_DISPLAY_MODE_FAILED(9),
    MEDIA_NOT_UPDATED(10),
    SERVER_UPDATE(11),
    CALL_JOIN_FAILED(100),
    CALL_JOIN_TIMEOUT(101),
    CALL_CALLEE_TIMEOUT(106),
    CALL_PREJOIN_FAILED(107),
    CALL_PREJOIN_TIMEOUT(108),
    CALL_CONFIRMJOIN_FAILED(110),
    CALL_CONFIRMJOIN_TIMEOUT(111),
    CALL_CONNECTION_BROKEN(112),
    CALL_SERVER_ERROR(117);

    private final int code;

    NileError(int i9) {
        this.code = i9;
    }

    /* renamed from: a */
    public static NileError m4475a(int i9) {
        for (NileError nileError : values()) {
            if (nileError.code == i9) {
                return nileError;
            }
        }
        return UNKNOWN;
    }
}
