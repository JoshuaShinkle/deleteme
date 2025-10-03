package org.webrtc;

import android.content.Context;
import android.os.Build;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.webrtc.NetworkMonitorAutoDetect;

/* loaded from: classes3.dex */
public class NetworkMonitor {
    private static final String TAG = "NetworkMonitor";
    private static NetworkMonitor instance;
    private final Context applicationContext;
    private NetworkMonitorAutoDetect autoDetector;
    private NetworkMonitorAutoDetect.ConnectionType currentConnectionType = NetworkMonitorAutoDetect.ConnectionType.CONNECTION_UNKNOWN;
    private final ArrayList<Long> nativeNetworkObservers;
    private final ArrayList<NetworkObserver> networkObservers;

    public interface NetworkObserver {
        void onConnectionTypeChanged(NetworkMonitorAutoDetect.ConnectionType connectionType);
    }

    private NetworkMonitor(Context context) {
        assertIsTrue(context != null);
        this.applicationContext = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.nativeNetworkObservers = new ArrayList<>();
        this.networkObservers = new ArrayList<>();
    }

    public static void addNetworkObserver(NetworkObserver networkObserver) {
        getInstance().addNetworkObserverInternal(networkObserver);
    }

    private void addNetworkObserverInternal(NetworkObserver networkObserver) {
        this.networkObservers.add(networkObserver);
    }

    private static int androidSdkInt() {
        return Build.VERSION.SDK_INT;
    }

    private static void assertIsTrue(boolean z8) {
        if (!z8) {
            throw new AssertionError("Expected to be true");
        }
    }

    private void destroyAutoDetector() {
        NetworkMonitorAutoDetect networkMonitorAutoDetect = this.autoDetector;
        if (networkMonitorAutoDetect != null) {
            networkMonitorAutoDetect.destroy();
            this.autoDetector = null;
        }
    }

    public static NetworkMonitorAutoDetect getAutoDetectorForTest() {
        return getInstance().autoDetector;
    }

    private NetworkMonitorAutoDetect.ConnectionType getCurrentConnectionType() {
        return this.currentConnectionType;
    }

    private long getCurrentDefaultNetId() {
        NetworkMonitorAutoDetect networkMonitorAutoDetect = this.autoDetector;
        if (networkMonitorAutoDetect == null) {
            return -1L;
        }
        return networkMonitorAutoDetect.getDefaultNetId();
    }

    public static NetworkMonitor getInstance() {
        return instance;
    }

    public static NetworkMonitor init(Context context) {
        if (!isInitialized()) {
            instance = new NetworkMonitor(context);
        }
        return instance;
    }

    public static boolean isInitialized() {
        return instance != null;
    }

    public static boolean isOnline() {
        return getInstance().getCurrentConnectionType() != NetworkMonitorAutoDetect.ConnectionType.CONNECTION_NONE;
    }

    private native void nativeNotifyConnectionTypeChanged(long j9);

    private native void nativeNotifyOfActiveNetworkList(long j9, NetworkMonitorAutoDetect.NetworkInformation[] networkInformationArr);

    private native void nativeNotifyOfNetworkConnect(long j9, NetworkMonitorAutoDetect.NetworkInformation networkInformation);

    private native void nativeNotifyOfNetworkDisconnect(long j9, long j10);

    private boolean networkBindingSupported() {
        NetworkMonitorAutoDetect networkMonitorAutoDetect = this.autoDetector;
        return networkMonitorAutoDetect != null && networkMonitorAutoDetect.supportNetworkCallback();
    }

    private void notifyObserversOfConnectionTypeChange(NetworkMonitorAutoDetect.ConnectionType connectionType) {
        Iterator<Long> it = this.nativeNetworkObservers.iterator();
        while (it.hasNext()) {
            nativeNotifyConnectionTypeChanged(it.next().longValue());
        }
        Iterator<NetworkObserver> it2 = this.networkObservers.iterator();
        while (it2.hasNext()) {
            it2.next().onConnectionTypeChanged(connectionType);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyObserversOfNetworkConnect(NetworkMonitorAutoDetect.NetworkInformation networkInformation) {
        Iterator<Long> it = this.nativeNetworkObservers.iterator();
        while (it.hasNext()) {
            nativeNotifyOfNetworkConnect(it.next().longValue(), networkInformation);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyObserversOfNetworkDisconnect(long j9) {
        Iterator<Long> it = this.nativeNetworkObservers.iterator();
        while (it.hasNext()) {
            nativeNotifyOfNetworkDisconnect(it.next().longValue(), j9);
        }
    }

    public static void removeNetworkObserver(NetworkObserver networkObserver) {
        getInstance().removeNetworkObserverInternal(networkObserver);
    }

    private void removeNetworkObserverInternal(NetworkObserver networkObserver) {
        this.networkObservers.remove(networkObserver);
    }

    public static void resetInstanceForTests(Context context) {
        instance = new NetworkMonitor(context);
    }

    public static void setAutoDetectConnectivityState(boolean z8) {
        getInstance().setAutoDetectConnectivityStateInternal(z8);
    }

    private void setAutoDetectConnectivityStateInternal(boolean z8) {
        if (!z8) {
            destroyAutoDetector();
        } else if (this.autoDetector == null) {
            NetworkMonitorAutoDetect networkMonitorAutoDetect = new NetworkMonitorAutoDetect(new NetworkMonitorAutoDetect.Observer() { // from class: org.webrtc.NetworkMonitor.1
                @Override // org.webrtc.NetworkMonitorAutoDetect.Observer
                public void onConnectionTypeChanged(NetworkMonitorAutoDetect.ConnectionType connectionType) {
                    NetworkMonitor.this.updateCurrentConnectionType(connectionType);
                }

                @Override // org.webrtc.NetworkMonitorAutoDetect.Observer
                public void onNetworkConnect(NetworkMonitorAutoDetect.NetworkInformation networkInformation) {
                    NetworkMonitor.this.notifyObserversOfNetworkConnect(networkInformation);
                }

                @Override // org.webrtc.NetworkMonitorAutoDetect.Observer
                public void onNetworkDisconnect(long j9) {
                    NetworkMonitor.this.notifyObserversOfNetworkDisconnect(j9);
                }
            }, this.applicationContext);
            this.autoDetector = networkMonitorAutoDetect;
            updateCurrentConnectionType(NetworkMonitorAutoDetect.getConnectionType(networkMonitorAutoDetect.getCurrentNetworkState()));
            updateActiveNetworkList();
        }
    }

    private void startMonitoring(long j9) {
        Logging.m23185d(TAG, "Start monitoring from native observer " + j9);
        this.nativeNetworkObservers.add(Long.valueOf(j9));
        setAutoDetectConnectivityStateInternal(true);
    }

    private void stopMonitoring(long j9) {
        Logging.m23185d(TAG, "Stop monitoring from native observer " + j9);
        setAutoDetectConnectivityStateInternal(false);
        this.nativeNetworkObservers.remove(Long.valueOf(j9));
    }

    private void updateActiveNetworkList() {
        List<NetworkMonitorAutoDetect.NetworkInformation> activeNetworkList = this.autoDetector.getActiveNetworkList();
        if (activeNetworkList == null || activeNetworkList.size() == 0) {
            return;
        }
        NetworkMonitorAutoDetect.NetworkInformation[] networkInformationArr = (NetworkMonitorAutoDetect.NetworkInformation[]) activeNetworkList.toArray(new NetworkMonitorAutoDetect.NetworkInformation[activeNetworkList.size()]);
        Iterator<Long> it = this.nativeNetworkObservers.iterator();
        while (it.hasNext()) {
            nativeNotifyOfActiveNetworkList(it.next().longValue(), networkInformationArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCurrentConnectionType(NetworkMonitorAutoDetect.ConnectionType connectionType) {
        this.currentConnectionType = connectionType;
        notifyObserversOfConnectionTypeChange(connectionType);
    }
}
