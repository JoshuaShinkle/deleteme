package com.cyberlink.you.utility.Permission;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;

/* loaded from: classes.dex */
public enum Permission {
    MICROPHONE { // from class: com.cyberlink.you.utility.Permission.Permission.1
        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: a */
        public int mo16656a() {
            return 1;
        }

        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: b */
        public String mo16657b() {
            return "android.permission.RECORD_AUDIO";
        }
    },
    PHONE_STATE { // from class: com.cyberlink.you.utility.Permission.Permission.2
        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: a */
        public int mo16656a() {
            return 8;
        }

        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: b */
        public String mo16657b() {
            return "android.permission.READ_PHONE_STATE";
        }
    },
    CAMERA { // from class: com.cyberlink.you.utility.Permission.Permission.3
        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: a */
        public int mo16656a() {
            return 4;
        }

        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: b */
        public String mo16657b() {
            return "android.permission.CAMERA";
        }
    },
    LOCATION { // from class: com.cyberlink.you.utility.Permission.Permission.4
        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: a */
        public int mo16656a() {
            return 16;
        }

        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: b */
        public String mo16657b() {
            return "android.permission.ACCESS_FINE_LOCATION";
        }
    },
    STORAGE { // from class: com.cyberlink.you.utility.Permission.Permission.5
        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: a */
        public int mo16656a() {
            return 32;
        }

        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: b */
        public String mo16657b() {
            return "android.permission.WRITE_EXTERNAL_STORAGE";
        }
    },
    GET_ACCOUNTS { // from class: com.cyberlink.you.utility.Permission.Permission.6
        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: a */
        public int mo16656a() {
            return 64;
        }

        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: b */
        public String mo16657b() {
            return "android.permission.GET_ACCOUNTS";
        }
    },
    CALL_PHONE { // from class: com.cyberlink.you.utility.Permission.Permission.7
        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: a */
        public int mo16656a() {
            return 128;
        }

        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: b */
        public String mo16657b() {
            return "android.permission.CALL_PHONE";
        }
    },
    BLUETOOTH_BLE { // from class: com.cyberlink.you.utility.Permission.Permission.8
        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: a */
        public int mo16656a() {
            return 256;
        }

        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: b */
        public String mo16657b() {
            return "android.permission.BLUETOOTH_CONNECT";
        }
    },
    NOTIFICATION { // from class: com.cyberlink.you.utility.Permission.Permission.9
        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: a */
        public int mo16656a() {
            return 512;
        }

        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: b */
        public String mo16657b() {
            return "android.permission.POST_NOTIFICATIONS";
        }
    },
    IMAGE { // from class: com.cyberlink.you.utility.Permission.Permission.10
        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: a */
        public int mo16656a() {
            return UserMetadata.MAX_ATTRIBUTE_SIZE;
        }

        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: b */
        public String mo16657b() {
            return "android.permission.READ_MEDIA_IMAGES";
        }
    },
    VIDEO { // from class: com.cyberlink.you.utility.Permission.Permission.11
        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: a */
        public int mo16656a() {
            return 2048;
        }

        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: b */
        public String mo16657b() {
            return "android.permission.READ_MEDIA_VIDEO";
        }
    },
    NULL { // from class: com.cyberlink.you.utility.Permission.Permission.12
        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: a */
        public int mo16656a() {
            return -1;
        }

        @Override // com.cyberlink.you.utility.Permission.Permission
        /* renamed from: b */
        public String mo16657b() {
            return "";
        }
    };

    /* renamed from: a */
    public abstract int mo16656a();

    /* renamed from: b */
    public abstract String mo16657b();
}
