package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class zzfk {
    private final String zza;
    private final Bundle zzb;
    private boolean zzc;
    private Bundle zzd;
    private final /* synthetic */ zzfj zze;

    public zzfk(zzfj zzfjVar, String str, Bundle bundle) {
        this.zze = zzfjVar;
        Preconditions.checkNotEmpty(str);
        this.zza = str;
        this.zzb = new Bundle();
    }

    private final String zzb(Bundle bundle) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj != null) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("n", str);
                    jSONObject.put("v", String.valueOf(obj));
                    if (obj instanceof String) {
                        jSONObject.put("t", "s");
                    } else if (obj instanceof Long) {
                        jSONObject.put("t", "l");
                    } else if (obj instanceof Double) {
                        jSONObject.put("t", "d");
                    } else {
                        this.zze.zzq().zze().zza("Cannot serialize bundle value to SharedPreferences. Type", obj.getClass());
                    }
                    jSONArray.put(jSONObject);
                } catch (JSONException e9) {
                    this.zze.zzq().zze().zza("Cannot serialize bundle value to SharedPreferences", e9);
                }
            }
        }
        return jSONArray.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0099 A[Catch: NumberFormatException | JSONException -> 0x00a1, NumberFormatException | JSONException -> 0x00a1, TRY_LEAVE, TryCatch #1 {NumberFormatException | JSONException -> 0x00a1, blocks: (B:9:0x0028, B:30:0x0071, B:30:0x0071, B:31:0x0081, B:31:0x0081, B:32:0x008d, B:32:0x008d, B:33:0x0099, B:33:0x0099, B:16:0x004a, B:19:0x0054, B:22:0x005e), top: B:47:0x0028, outer: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Bundle zza() throws JSONException {
        char c9;
        if (!this.zzc) {
            this.zzc = true;
            String string = this.zze.zzf().getString(this.zza, null);
            if (string != null) {
                try {
                    Bundle bundle = new Bundle();
                    JSONArray jSONArray = new JSONArray(string);
                    for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                        try {
                            JSONObject jSONObject = jSONArray.getJSONObject(i9);
                            String string2 = jSONObject.getString("n");
                            String string3 = jSONObject.getString("t");
                            int iHashCode = string3.hashCode();
                            if (iHashCode == 100) {
                                if (string3.equals("d")) {
                                    c9 = 1;
                                }
                                if (c9 == 0) {
                                }
                            } else if (iHashCode != 108) {
                                c9 = (iHashCode == 115 && string3.equals("s")) ? (char) 0 : (char) 65535;
                                if (c9 == 0) {
                                    bundle.putString(string2, jSONObject.getString("v"));
                                } else if (c9 == 1) {
                                    bundle.putDouble(string2, Double.parseDouble(jSONObject.getString("v")));
                                } else if (c9 != 2) {
                                    this.zze.zzq().zze().zza("Unrecognized persisted bundle type. Type", string3);
                                } else {
                                    bundle.putLong(string2, Long.parseLong(jSONObject.getString("v")));
                                }
                            } else {
                                if (string3.equals("l")) {
                                    c9 = 2;
                                }
                                if (c9 == 0) {
                                }
                            }
                        } catch (NumberFormatException | JSONException unused) {
                            this.zze.zzq().zze().zza("Error reading value from SharedPreferences. Value dropped");
                        }
                    }
                    this.zzd = bundle;
                } catch (JSONException unused2) {
                    this.zze.zzq().zze().zza("Error loading bundle from SharedPreferences. Values will be lost");
                }
            }
            if (this.zzd == null) {
                this.zzd = this.zzb;
            }
        }
        return this.zzd;
    }

    public final void zza(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        SharedPreferences.Editor editorEdit = this.zze.zzf().edit();
        if (bundle.size() == 0) {
            editorEdit.remove(this.zza);
        } else {
            editorEdit.putString(this.zza, zzb(bundle));
        }
        editorEdit.apply();
        this.zzd = bundle;
    }
}
