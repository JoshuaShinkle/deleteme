package com.google.zxing.client.android.wifi;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.util.Log;
import com.google.zxing.client.result.WifiParsedResult;
import java.util.List;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public final class WifiConfigManager extends AsyncTask<WifiParsedResult, Object, Object> {
    private static final Pattern HEX_DIGITS = Pattern.compile("[0-9A-Fa-f]+");
    private static final String TAG = "WifiConfigManager";
    private final WifiManager wifiManager;

    public WifiConfigManager(WifiManager wifiManager) {
        this.wifiManager = wifiManager;
    }

    private static WifiConfiguration changeNetworkCommon(WifiParsedResult wifiParsedResult) {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedProtocols.clear();
        wifiConfiguration.SSID = quoteNonHex(wifiParsedResult.getSsid(), new int[0]);
        wifiConfiguration.hiddenSSID = wifiParsedResult.isHidden();
        return wifiConfiguration;
    }

    private static void changeNetworkUnEncrypted(WifiManager wifiManager, WifiParsedResult wifiParsedResult) {
        WifiConfiguration wifiConfigurationChangeNetworkCommon = changeNetworkCommon(wifiParsedResult);
        wifiConfigurationChangeNetworkCommon.allowedKeyManagement.set(0);
        updateNetwork(wifiManager, wifiConfigurationChangeNetworkCommon);
    }

    private static void changeNetworkWEP(WifiManager wifiManager, WifiParsedResult wifiParsedResult) {
        WifiConfiguration wifiConfigurationChangeNetworkCommon = changeNetworkCommon(wifiParsedResult);
        wifiConfigurationChangeNetworkCommon.wepKeys[0] = quoteNonHex(wifiParsedResult.getPassword(), 10, 26, 58);
        wifiConfigurationChangeNetworkCommon.wepTxKeyIndex = 0;
        wifiConfigurationChangeNetworkCommon.allowedAuthAlgorithms.set(1);
        wifiConfigurationChangeNetworkCommon.allowedKeyManagement.set(0);
        wifiConfigurationChangeNetworkCommon.allowedGroupCiphers.set(2);
        wifiConfigurationChangeNetworkCommon.allowedGroupCiphers.set(3);
        wifiConfigurationChangeNetworkCommon.allowedGroupCiphers.set(0);
        wifiConfigurationChangeNetworkCommon.allowedGroupCiphers.set(1);
        updateNetwork(wifiManager, wifiConfigurationChangeNetworkCommon);
    }

    private static void changeNetworkWPA(WifiManager wifiManager, WifiParsedResult wifiParsedResult) {
        WifiConfiguration wifiConfigurationChangeNetworkCommon = changeNetworkCommon(wifiParsedResult);
        wifiConfigurationChangeNetworkCommon.preSharedKey = quoteNonHex(wifiParsedResult.getPassword(), 64);
        wifiConfigurationChangeNetworkCommon.allowedAuthAlgorithms.set(0);
        wifiConfigurationChangeNetworkCommon.allowedProtocols.set(0);
        wifiConfigurationChangeNetworkCommon.allowedProtocols.set(1);
        wifiConfigurationChangeNetworkCommon.allowedKeyManagement.set(1);
        wifiConfigurationChangeNetworkCommon.allowedKeyManagement.set(2);
        wifiConfigurationChangeNetworkCommon.allowedPairwiseCiphers.set(1);
        wifiConfigurationChangeNetworkCommon.allowedPairwiseCiphers.set(2);
        wifiConfigurationChangeNetworkCommon.allowedGroupCiphers.set(2);
        wifiConfigurationChangeNetworkCommon.allowedGroupCiphers.set(3);
        updateNetwork(wifiManager, wifiConfigurationChangeNetworkCommon);
    }

    private static String convertToQuotedString(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        if (str.charAt(0) == '\"' && str.charAt(str.length() - 1) == '\"') {
            return str;
        }
        return '\"' + str + '\"';
    }

    private static Integer findNetworkInExistingConfig(WifiManager wifiManager, String str) {
        List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
        if (configuredNetworks == null) {
            return null;
        }
        for (WifiConfiguration wifiConfiguration : configuredNetworks) {
            String str2 = wifiConfiguration.SSID;
            if (str2 != null && str2.equals(str)) {
                return Integer.valueOf(wifiConfiguration.networkId);
            }
        }
        return null;
    }

    private static boolean isHexOfLength(CharSequence charSequence, int... iArr) {
        if (charSequence != null && HEX_DIGITS.matcher(charSequence).matches()) {
            if (iArr.length == 0) {
                return true;
            }
            for (int i9 : iArr) {
                if (charSequence.length() == i9) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String quoteNonHex(String str, int... iArr) {
        return isHexOfLength(str, iArr) ? str : convertToQuotedString(str);
    }

    private static void updateNetwork(WifiManager wifiManager, WifiConfiguration wifiConfiguration) {
        Integer numFindNetworkInExistingConfig = findNetworkInExistingConfig(wifiManager, wifiConfiguration.SSID);
        if (numFindNetworkInExistingConfig != null) {
            Log.i(TAG, "Removing old configuration for network " + wifiConfiguration.SSID);
            wifiManager.removeNetwork(numFindNetworkInExistingConfig.intValue());
            wifiManager.saveConfiguration();
        }
        int iAddNetwork = wifiManager.addNetwork(wifiConfiguration);
        if (iAddNetwork < 0) {
            Log.w(TAG, "Unable to add network " + wifiConfiguration.SSID);
            return;
        }
        if (!wifiManager.enableNetwork(iAddNetwork, true)) {
            Log.w(TAG, "Failed to enable network " + wifiConfiguration.SSID);
            return;
        }
        Log.i(TAG, "Associating to network " + wifiConfiguration.SSID);
        wifiManager.saveConfiguration();
    }

    @Override // android.os.AsyncTask
    public Object doInBackground(WifiParsedResult... wifiParsedResultArr) throws InterruptedException {
        int i9 = 0;
        WifiParsedResult wifiParsedResult = wifiParsedResultArr[0];
        if (!this.wifiManager.isWifiEnabled()) {
            String str = TAG;
            Log.i(str, "Enabling wi-fi...");
            if (!this.wifiManager.setWifiEnabled(true)) {
                Log.w(str, "Wi-fi could not be enabled!");
                return null;
            }
            Log.i(str, "Wi-fi enabled");
            while (!this.wifiManager.isWifiEnabled()) {
                if (i9 >= 10) {
                    Log.i(TAG, "Took too long to enable wi-fi, quitting");
                    return null;
                }
                Log.i(TAG, "Still waiting for wi-fi to enable...");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException unused) {
                }
                i9++;
            }
        }
        String networkEncryption = wifiParsedResult.getNetworkEncryption();
        try {
            NetworkType networkTypeForIntentValue = NetworkType.forIntentValue(networkEncryption);
            if (networkTypeForIntentValue == NetworkType.NO_PASSWORD) {
                changeNetworkUnEncrypted(this.wifiManager, wifiParsedResult);
            } else {
                String password = wifiParsedResult.getPassword();
                if (password != null && !password.isEmpty()) {
                    if (networkTypeForIntentValue == NetworkType.WEP) {
                        changeNetworkWEP(this.wifiManager, wifiParsedResult);
                    } else if (networkTypeForIntentValue == NetworkType.WPA) {
                        changeNetworkWPA(this.wifiManager, wifiParsedResult);
                    }
                }
            }
            return null;
        } catch (IllegalArgumentException unused2) {
            Log.w(TAG, "Bad network type; see NetworkType values: " + networkEncryption);
            return null;
        }
    }
}
