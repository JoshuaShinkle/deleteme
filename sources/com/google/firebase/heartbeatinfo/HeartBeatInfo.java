package com.google.firebase.heartbeatinfo;

/* loaded from: classes2.dex */
public interface HeartBeatInfo {

    public enum HeartBeat {
        NONE(0),
        SDK(1),
        GLOBAL(2),
        COMBINED(3);

        private final int code;

        HeartBeat(int i9) {
            this.code = i9;
        }

        public int getCode() {
            return this.code;
        }
    }

    HeartBeat getHeartBeatCode(String str);
}
