package com.cyberlink.you.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseFragmentActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.C1886aj;
import com.cyberlink.you.activity.share.ShareMediaActivity;
import com.cyberlink.you.activity.share.ShareType;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.Permission.Permission;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;

/* loaded from: classes.dex */
public class SelectLocationActivity extends BaseFragmentActivity implements OnMapReadyCallback {

    /* renamed from: G */
    public static final LocationListener f8797G = new C1688a();

    /* renamed from: C */
    public AsyncTaskC1695h f8800C;

    /* renamed from: D */
    public boolean f8801D;

    /* renamed from: E */
    public GoogleMap.SnapshotReadyCallback f8802E;

    /* renamed from: d */
    public ProgressDialog f8805d;

    /* renamed from: e */
    public LatLng f8806e;

    /* renamed from: f */
    public float f8807f;

    /* renamed from: h */
    public double f8809h;

    /* renamed from: i */
    public double f8810i;

    /* renamed from: j */
    public String f8811j;

    /* renamed from: k */
    public String f8812k;

    /* renamed from: l */
    public double f8813l;

    /* renamed from: m */
    public double f8814m;

    /* renamed from: n */
    public boolean f8815n;

    /* renamed from: q */
    public LocationManager f8818q;

    /* renamed from: r */
    public EditText f8819r;

    /* renamed from: c */
    public GoogleMap f8804c = null;

    /* renamed from: g */
    public Uri f8808g = null;

    /* renamed from: o */
    public ArrayList<String> f8816o = new ArrayList<>();

    /* renamed from: p */
    public boolean f8817p = false;

    /* renamed from: s */
    public List<Address> f8820s = new ArrayList();

    /* renamed from: t */
    public boolean f8821t = false;

    /* renamed from: u */
    public boolean f8822u = false;

    /* renamed from: v */
    public Handler f8823v = new Handler();

    /* renamed from: w */
    public CustomSupportMapFragment f8824w = null;

    /* renamed from: x */
    public View.OnClickListener f8825x = new View.OnClickListener() { // from class: com.cyberlink.you.activity.zd
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f12305b.m9697G1(view);
        }
    };

    /* renamed from: y */
    public View.OnClickListener f8826y = new View.OnClickListener() { // from class: com.cyberlink.you.activity.ae
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) throws IOException {
            this.f9722b.m9698H1(view);
        }
    };

    /* renamed from: z */
    public View.OnClickListener f8827z = new ViewOnClickListenerC1689b();

    /* renamed from: A */
    public final GoogleMap.OnCameraIdleListener f8798A = new C1692e();

    /* renamed from: B */
    public final View.OnClickListener f8799B = new ViewOnClickListenerC1693f();

    /* renamed from: F */
    public Runnable f8803F = new RunnableC1694g();

    /* renamed from: com.cyberlink.you.activity.SelectLocationActivity$a */
    public class C1688a implements LocationListener {
        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            Log.v("SelectLocationActivity", "onLocationChanged: " + location.getAltitude() + ", " + location.getLatitude());
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i9, Bundle bundle) {
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelectLocationActivity$b */
    public class ViewOnClickListenerC1689b implements View.OnClickListener {
        public ViewOnClickListenerC1689b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m9754c(Dialog dialog, View view) {
            Intent intent = new Intent(SelectLocationActivity.this, (Class<?>) ShareMediaActivity.class);
            intent.putStringArrayListExtra("sharedMediaMsgIdList", SelectLocationActivity.this.f8816o);
            intent.putExtra("shareType", ShareType.Forward.toString());
            SelectLocationActivity.this.startActivity(intent);
            dialog.dismiss();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m9755d(Dialog dialog, View view) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(String.format(Locale.getDefault(), "http://maps.google.com/maps?hl=zh&mrt=loc&q=%f,%f", Double.valueOf(SelectLocationActivity.this.f8809h), Double.valueOf(SelectLocationActivity.this.f8810i))));
                intent.addFlags(0);
                if (SelectLocationActivity.this.m9739D1("com.google.android.apps.maps")) {
                    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                }
                SelectLocationActivity.this.startActivity(intent);
            } catch (ActivityNotFoundException e9) {
                e9.printStackTrace();
            }
            dialog.dismiss();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            final Dialog dialogM16384c = C3123g.m16384c(SelectLocationActivity.this);
            dialogM16384c.setContentView(R.layout.dialog_location_more);
            dialogM16384c.findViewById(R.id.fowardBtn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.ee
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f10407b.m9754c(dialogM16384c, view2);
                }
            });
            dialogM16384c.findViewById(R.id.openInGoogleMap).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.fe
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f10442b.m9755d(dialogM16384c, view2);
                }
            });
            dialogM16384c.show();
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelectLocationActivity$c */
    public class C1690c implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ Runnable f8829a;

        public C1690c(Runnable runnable) {
            this.f8829a = runnable;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            C5187v0.m20267c(R.string.permission_denied);
            SelectLocationActivity.this.finish();
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            this.f8829a.run();
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelectLocationActivity$d */
    public class C1691d implements C1886aj.a {
        public C1691d() {
        }

        @Override // com.cyberlink.you.activity.C1886aj.a
        /* renamed from: a */
        public void mo9756a() {
            CLUtility.m16589t1(SelectLocationActivity.this);
            SelectLocationActivity.this.f8821t = false;
            SelectLocationActivity.this.f8822u = true;
        }

        @Override // com.cyberlink.you.activity.C1886aj.a
        /* renamed from: b */
        public void mo9757b() {
            SelectLocationActivity.this.f8821t = true;
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelectLocationActivity$e */
    public class C1692e implements GoogleMap.OnCameraIdleListener {
        public C1692e() {
        }

        @Override // com.google.android.gms.maps.GoogleMap.OnCameraIdleListener
        public void onCameraIdle() throws IOException {
            if (SelectLocationActivity.this.f8821t && SelectLocationActivity.this.f8822u) {
                SelectLocationActivity.this.f8822u = false;
                SelectLocationActivity.this.f8817p = false;
                CameraPosition cameraPosition = SelectLocationActivity.this.f8804c.getCameraPosition();
                if (cameraPosition == null) {
                    Log.d("SelectLocationActivity", "Camera position is unavailable");
                    return;
                }
                SelectLocationActivity.this.m9745O1(cameraPosition.target, "", false);
                SelectLocationActivity.this.f8809h = cameraPosition.target.latitude;
                SelectLocationActivity.this.f8810i = cameraPosition.target.longitude;
                SelectLocationActivity.this.f8804c.animateCamera(CameraUpdateFactory.newLatLngZoom(cameraPosition.target, SelectLocationActivity.this.f8804c.getCameraPosition().zoom));
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelectLocationActivity$f */
    public class ViewOnClickListenerC1693f implements View.OnClickListener {
        public ViewOnClickListenerC1693f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (SelectLocationActivity.this.f8804c == null) {
                SelectLocationActivity.this.finish();
                return;
            }
            View viewFindViewById = SelectLocationActivity.this.getWindow().findViewById(android.R.id.content);
            int top = viewFindViewById.getTop() + viewFindViewById.getPaddingTop();
            Point point = new Point();
            SelectLocationActivity.this.getWindowManager().getDefaultDisplay().getSize(point);
            point.x /= 2;
            point.y = (point.y - top) / 2;
            SelectLocationActivity selectLocationActivity = SelectLocationActivity.this;
            selectLocationActivity.f8806e = selectLocationActivity.f8804c.getProjection().fromScreenLocation(point);
            if (SelectLocationActivity.this.f8806e == null) {
                SelectLocationActivity.this.finish();
                return;
            }
            LatLng latLng = new LatLng(SelectLocationActivity.this.f8809h, SelectLocationActivity.this.f8810i);
            SelectLocationActivity.this.f8804c.clear();
            try {
                SelectLocationActivity.this.f8804c.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.location_on_map)).position(latLng).alpha(0.6f).flat(true));
            } catch (Exception e9) {
                Log.d("SelectLocationActivity", e9.getMessage());
            }
            SelectLocationActivity.this.f8804c.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, SelectLocationActivity.this.f8804c.getCameraPosition().zoom));
            SelectLocationActivity selectLocationActivity2 = SelectLocationActivity.this;
            selectLocationActivity2.f8807f = selectLocationActivity2.f8804c.getCameraPosition().zoom;
            SelectLocationActivity selectLocationActivity3 = SelectLocationActivity.this;
            selectLocationActivity3.f8805d = ProgressDialog.show(selectLocationActivity3, "", selectLocationActivity3.getString(R.string.processing), true);
            SelectLocationActivity.this.f8823v.postDelayed(SelectLocationActivity.this.f8803F, 500L);
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelectLocationActivity$g */
    public class RunnableC1694g implements Runnable {
        public RunnableC1694g() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m9759b(Bitmap bitmap) throws IOException {
            Bitmap bitmapM9737z1 = SelectLocationActivity.m9737z1(bitmap);
            try {
                String strM9694B1 = SelectLocationActivity.m9694B1(SelectLocationActivity.this.getApplicationContext(), String.valueOf(System.currentTimeMillis()));
                FileOutputStream fileOutputStream = new FileOutputStream(strM9694B1);
                bitmapM9737z1.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                SelectLocationActivity.this.f8808g = Uri.parse(strM9694B1);
                fileOutputStream.flush();
                fileOutputStream.close();
                Log.d("SelectLocationActivity", "getScreenShot in OK!");
            } catch (FileNotFoundException e9) {
                Log.e("ImageCapture", "FileNotFoundException");
                Log.e("ImageCapture", e9.getMessage());
            } catch (IOException e10) {
                Log.e("ImageCapture", "IOException");
                Log.e("ImageCapture", e10.getMessage());
            }
            if (!SelectLocationActivity.this.isFinishing() && SelectLocationActivity.this.f8808g == null && SelectLocationActivity.this.f8805d != null) {
                SelectLocationActivity.this.f8805d.dismiss();
            } else {
                if (SelectLocationActivity.this.f8801D) {
                    return;
                }
                SelectLocationActivity.this.f8801D = true;
                SelectLocationActivity.this.f8800C = new AsyncTaskC1695h(SelectLocationActivity.this, null);
                SelectLocationActivity.this.f8800C.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.d("SelectLocationActivity", "mGetScreenShotTask in ");
            SelectLocationActivity.this.f8802E = new GoogleMap.SnapshotReadyCallback() { // from class: com.cyberlink.you.activity.ge
                @Override // com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback
                public final void onSnapshotReady(Bitmap bitmap) throws IOException {
                    this.f10694a.m9759b(bitmap);
                }
            };
            SelectLocationActivity.this.f8804c.snapshot(SelectLocationActivity.this.f8802E);
        }
    }

    /* renamed from: com.cyberlink.you.activity.SelectLocationActivity$h */
    public class AsyncTaskC1695h extends AsyncTask<Void, Void, ArrayList<Address>> {
        public AsyncTaskC1695h() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ArrayList<Address> doInBackground(Void... voidArr) throws IOException {
            ArrayList<Address> arrayList = new ArrayList<>();
            if (SelectLocationActivity.this.f8820s.isEmpty()) {
                try {
                    List<Address> fromLocation = new Geocoder(SelectLocationActivity.this.getApplicationContext(), Locale.getDefault()).getFromLocation(SelectLocationActivity.this.f8806e.latitude, SelectLocationActivity.this.f8806e.longitude, 1);
                    if (fromLocation != null && !fromLocation.isEmpty()) {
                        arrayList.addAll(fromLocation);
                    }
                } catch (Exception e9) {
                    e9.printStackTrace();
                }
            } else {
                arrayList.addAll(SelectLocationActivity.this.f8820s);
            }
            return arrayList;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(ArrayList<Address> arrayList) {
            SelectLocationActivity.this.f8801D = false;
            Location location = new Location("U");
            location.setLatitude(SelectLocationActivity.this.f8809h);
            location.setLongitude(SelectLocationActivity.this.f8810i);
            Intent intent = new Intent();
            intent.putExtra(FirebaseAnalytics.Param.LOCATION, location);
            intent.putParcelableArrayListExtra("location_addresses", arrayList);
            intent.putExtra("snapshotUrl", SelectLocationActivity.this.f8808g);
            SelectLocationActivity.this.setResult(-1, intent);
            if (SelectLocationActivity.this.f8805d != null) {
                SelectLocationActivity.this.f8805d.dismiss();
            }
            SelectLocationActivity.this.finish();
        }

        public /* synthetic */ AsyncTaskC1695h(SelectLocationActivity selectLocationActivity, C1688a c1688a) {
            this();
        }
    }

    /* renamed from: A1 */
    public static float m9693A1(int i9, int i10, int i11) {
        float f9 = i9 < i10 ? i11 / i9 : i11 / i10;
        if (f9 > 1.0f) {
            f9 = 1.0f;
        }
        return f9 < BitmapDescriptorFactory.HUE_RED ? BitmapDescriptorFactory.HUE_RED : f9;
    }

    /* renamed from: B1 */
    public static String m9694B1(Context context, String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        Log.d("SelectLocationActivity", "the android version is above 4.1");
        return CLUtility.m16573p1(str, ".jpeg", new File(CLUtility.m16472O0(context)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E1 */
    public /* synthetic */ boolean m9695E1(TextView textView, int i9, KeyEvent keyEvent) throws IOException {
        if (i9 != 3) {
            return false;
        }
        m9743M1();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: F1 */
    public /* synthetic */ void m9696F1(View view) throws IOException {
        Location locationM9751y1 = m9751y1();
        if (locationM9751y1 == null) {
            m9747Q1();
            return;
        }
        LatLng latLng = new LatLng(locationM9751y1.getLatitude(), locationM9751y1.getLongitude());
        if (!this.f8817p) {
            if (this.f8815n) {
                m9745O1(latLng, "", false);
            }
            try {
                this.f8804c.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_me)).position(latLng).anchor(0.5f, 0.5f).flat(true));
            } catch (Exception e9) {
                Log.d("SelectLocationActivity", e9.getMessage());
            }
            this.f8809h = locationM9751y1.getLatitude();
            this.f8810i = locationM9751y1.getLongitude();
            this.f8817p = true;
        }
        try {
            this.f8804c.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, this.f8804c.getCameraPosition().zoom));
        } catch (OutOfMemoryError unused) {
            this.f8804c.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: G1 */
    public /* synthetic */ void m9697G1(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H1 */
    public /* synthetic */ void m9698H1(View view) throws IOException {
        m9743M1();
    }

    /* renamed from: z1 */
    public static Bitmap m9737z1(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float fM9693A1 = m9693A1(width, height, 390);
        Matrix matrix = new Matrix();
        matrix.postScale(fM9693A1, fM9693A1);
        boolean z8 = width < height;
        int i9 = z8 ? width : height;
        return Bitmap.createBitmap(bitmap, z8 ? 0 : (width - height) / 2, z8 ? (height - width) / 2 : 0, i9, i9, matrix, true);
    }

    /* renamed from: C1 */
    public final void m9738C1() {
        try {
            if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) == 0) {
                m9749w1();
            } else {
                finish();
            }
        } catch (Exception e9) {
            m9749w1();
            Log.d("SelectLocationActivity", " Create exception should reCreateView, Exception = " + e9);
        }
    }

    /* renamed from: D1 */
    public final boolean m9739D1(String str) throws PackageManager.NameNotFoundException {
        try {
            getApplicationContext().getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* renamed from: J1 */
    public final void m9740J1() {
        LocationManager locationManager = this.f8818q;
        if (locationManager == null) {
            return;
        }
        locationManager.removeUpdates(f8797G);
    }

    /* renamed from: K1 */
    public final void m9741K1() {
        if (this.f8818q == null) {
            return;
        }
        try {
            this.f8818q.requestLocationUpdates("gps", TimeUnit.MINUTES.toMillis(1L), 10.0f, f8797G);
        } catch (SecurityException unused) {
        }
    }

    /* renamed from: L1 */
    public final void m9742L1(Location location) {
        if (location != null) {
            Globals.m7388i0().getSharedPreferences("U", 0).edit().putFloat("location_zoom", this.f8807f).putString("location_latitude", String.valueOf(location.getLatitude())).putString("location_longitude", String.valueOf(location.getLongitude())).apply();
        }
    }

    /* renamed from: M1 */
    public final void m9743M1() throws IOException {
        if (this.f8819r.getText().toString().equals("")) {
            return;
        }
        String string = this.f8819r.getText().toString();
        try {
            View viewFindViewById = getWindow().findViewById(android.R.id.content);
            int top = viewFindViewById.getTop() + viewFindViewById.getPaddingTop();
            Point point = new Point();
            getWindowManager().getDefaultDisplay().getSize(point);
            point.x /= 2;
            point.y = (point.y - top) / 2;
            LatLng latLngFromScreenLocation = this.f8804c.getProjection().fromScreenLocation(point);
            List<Address> fromLocationName = new Geocoder(getApplicationContext(), Locale.getDefault()).getFromLocationName(string, 10);
            if (fromLocationName == null || fromLocationName.isEmpty()) {
                C5187v0.m20271g(getString(R.string.error_search_place_name, string));
            } else {
                this.f8804c.clear();
                this.f8817p = false;
                double d9 = 255.0d;
                for (Address address : fromLocationName) {
                    if (fromLocationName.size() == 1) {
                        this.f8813l = address.getLatitude();
                        this.f8814m = address.getLongitude();
                    }
                    double dSqrt = Math.sqrt(((latLngFromScreenLocation.latitude - address.getLatitude()) * (latLngFromScreenLocation.latitude - address.getLatitude())) + ((latLngFromScreenLocation.longitude - address.getLongitude()) * (latLngFromScreenLocation.longitude - address.getLongitude())));
                    if (dSqrt < d9) {
                        this.f8813l = address.getLatitude();
                        this.f8814m = address.getLongitude();
                        d9 = dSqrt;
                    }
                }
                LatLng latLng = new LatLng(this.f8813l, this.f8814m);
                m9745O1(latLng, string, true);
                this.f8809h = this.f8813l;
                this.f8810i = this.f8814m;
                this.f8804c.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, this.f8804c.getCameraPosition().zoom));
            }
        } catch (Exception e9) {
            e9.printStackTrace();
            C5187v0.m20271g(getString(R.string.error_search_place_name, string));
        }
        CLUtility.m16589t1(this);
    }

    /* renamed from: N1 */
    public final Address m9744N1(LatLng latLng) {
        Address address = new Address(Locale.getDefault());
        address.setLatitude(latLng.latitude);
        address.setLongitude(latLng.longitude);
        return address;
    }

    /* renamed from: O1 */
    public final void m9745O1(LatLng latLng, String str, boolean z8) throws IOException {
        try {
            List<Address> fromLocation = new Geocoder(getApplicationContext(), Locale.getDefault()).getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (fromLocation.isEmpty()) {
                try {
                    fromLocation.add(m9744N1(latLng));
                    this.f8804c.clear();
                    this.f8804c.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.location_on_map)).position(latLng).alpha(0.6f).flat(true));
                } catch (Exception e9) {
                    Log.d("SelectLocationActivity", e9.getMessage());
                }
            } else if (z8) {
                try {
                    this.f8804c.clear();
                    this.f8804c.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.location_on_map)).position(latLng).title(str).alpha(0.6f).flat(true)).showInfoWindow();
                } catch (Exception e10) {
                    Log.d("SelectLocationActivity", e10.getMessage());
                }
            } else {
                String addressLine = fromLocation.get(0).getAddressLine(0);
                try {
                    this.f8804c.clear();
                    this.f8804c.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.location_on_map)).position(latLng).title(addressLine).snippet(this.f8812k).alpha(0.6f).flat(true)).showInfoWindow();
                } catch (Exception e11) {
                    Log.d("SelectLocationActivity", e11.getMessage());
                }
            }
            this.f8820s = fromLocation;
        } catch (Exception e12) {
            e12.printStackTrace();
        }
    }

    /* renamed from: P1 */
    public final void m9746P1() {
        UiSettings uiSettings = this.f8804c.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setCompassEnabled(true);
        if (this.f8815n) {
            uiSettings.setMapToolbarEnabled(false);
        } else {
            uiSettings.setMapToolbarEnabled(true);
        }
        uiSettings.setMyLocationButtonEnabled(false);
    }

    /* renamed from: Q1 */
    public final void m9747Q1() {
        if (getApplicationContext() == null) {
            return;
        }
        final Dialog dialogM16384c = C3123g.m16384c(this);
        dialogM16384c.setContentView(R.layout.dialog_location_detect_error);
        dialogM16384c.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.yd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialogM16384c.dismiss();
            }
        });
        dialogM16384c.show();
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m9748v1(new Runnable() { // from class: com.cyberlink.you.activity.be
            @Override // java.lang.Runnable
            public final void run() {
                this.f9751b.m9738C1();
            }
        });
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        m9750x1();
        super.onDestroy();
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    public void onMapReady(GoogleMap googleMap) throws IOException {
        if (googleMap == null) {
            ULogUtility.m16670f("SelectLocationActivity", "map is null");
            return;
        }
        this.f8804c = googleMap;
        SharedPreferences sharedPreferences = Globals.m7388i0().getSharedPreferences("U", 0);
        this.f8807f = sharedPreferences.getFloat("location_zoom", 13.0f);
        if (this.f8815n) {
            Location locationM9751y1 = m9751y1();
            if (locationM9751y1 == null) {
                this.f8809h = Double.parseDouble(sharedPreferences.getString("location_latitude", "0"));
                this.f8810i = Double.parseDouble(sharedPreferences.getString("location_longitude", "0"));
                m9747Q1();
            } else {
                this.f8809h = locationM9751y1.getLatitude();
                this.f8810i = locationM9751y1.getLongitude();
            }
            LatLng latLng = new LatLng(this.f8809h, this.f8810i);
            m9745O1(latLng, "", false);
            try {
                this.f8804c.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_me)).anchor(0.5f, 0.5f).position(latLng).flat(true));
            } catch (Exception e9) {
                Log.d("SelectLocationActivity", e9.getMessage());
            }
            this.f8817p = true;
            this.f8804c.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, this.f8807f));
            this.f8804c.setOnCameraIdleListener(this.f8798A);
        } else {
            LatLng latLng2 = new LatLng(this.f8809h, this.f8810i);
            String str = this.f8811j;
            if (str == null || str.equals("null")) {
                try {
                    this.f8804c.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.location_on_map)).position(latLng2).title(this.f8812k).flat(true)).showInfoWindow();
                } catch (Exception e10) {
                    Log.d("SelectLocationActivity", e10.getMessage());
                }
            } else {
                try {
                    this.f8804c.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.location_on_map)).position(latLng2).title(this.f8811j).snippet(this.f8812k).flat(true)).showInfoWindow();
                } catch (Exception e11) {
                    Log.d("SelectLocationActivity", e11.getMessage());
                }
            }
            this.f8804c.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(this.f8809h, this.f8810i), this.f8807f));
        }
        m9746P1();
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        m9740J1();
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        m9741K1();
    }

    /* renamed from: v1 */
    public final void m9748v1(Runnable runnable) {
        C5287b.m20584g(new Permission[]{Permission.LOCATION}, new C1690c(runnable), this);
    }

    /* renamed from: w1 */
    public final void m9749w1() {
        setContentView(R.layout.activity_select_location);
        this.f8815n = true;
        this.f8818q = (LocationManager) getSystemService(FirebaseAnalytics.Param.LOCATION);
        m9741K1();
        TextView textView = (TextView) findViewById(R.id.btnShareLocation);
        textView.setOnClickListener(this.f8799B);
        ((ImageView) findViewById(R.id.backBtn)).setOnClickListener(this.f8825x);
        ImageView imageView = (ImageView) findViewById(R.id.MoreBtn);
        imageView.setOnClickListener(this.f8827z);
        EditText editText = (EditText) findViewById(R.id.LocationInputEditText);
        this.f8819r = editText;
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.cyberlink.you.activity.ce
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView2, int i9, KeyEvent keyEvent) {
                return this.f9784b.m9695E1(textView2, i9, keyEvent);
            }
        });
        ((ImageView) findViewById(R.id.myLocation)).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.de
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IOException {
                this.f10373b.m9696F1(view);
            }
        });
        findViewById(R.id.SearchBtn).setOnClickListener(this.f8826y);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f8809h = extras.getDouble("latitude");
            this.f8810i = extras.getDouble("longitude");
            this.f8811j = extras.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
            this.f8812k = extras.getString("address");
            this.f8815n = false;
            boolean z8 = extras.getBoolean("viewOnly");
            String string = extras.getString(Constants.FirelogAnalytics.PARAM_MESSAGE_ID);
            if (z8) {
                imageView.setVisibility(0);
                textView.setVisibility(8);
                this.f8819r.setVisibility(8);
                findViewById(R.id.SearchBtn).setVisibility(8);
                findViewById(R.id.imgCenter).setVisibility(8);
            }
            if (string != null) {
                this.f8816o.add(string);
            }
        } else {
            imageView.setVisibility(8);
            textView.setVisibility(0);
            this.f8819r.setVisibility(0);
            findViewById(R.id.SearchBtn).setVisibility(0);
        }
        CustomSupportMapFragment customSupportMapFragment = (CustomSupportMapFragment) getSupportFragmentManager().mo1847d(R.id.map);
        this.f8824w = customSupportMapFragment;
        customSupportMapFragment.getMapAsync(this);
        this.f8824w.m7996g(new C1691d());
    }

    /* renamed from: x1 */
    public final void m9750x1() {
        GoogleMap googleMap = this.f8804c;
        if (googleMap != null) {
            googleMap.clear();
            this.f8804c = null;
        }
        this.f8824w = null;
        this.f8818q = null;
        this.f8806e = null;
        ProgressDialog progressDialog = this.f8805d;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.f8805d.dismiss();
            this.f8805d = null;
        }
        ArrayList<String> arrayList = this.f8816o;
        if (arrayList != null) {
            arrayList.clear();
            this.f8816o = null;
        }
        EditText editText = this.f8819r;
        if (editText != null) {
            editText.setOnEditorActionListener(null);
        }
        List<Address> list = this.f8820s;
        if (list != null) {
            list.clear();
            this.f8820s = null;
        }
        AsyncTaskC1695h asyncTaskC1695h = this.f8800C;
        if (asyncTaskC1695h != null) {
            asyncTaskC1695h.cancel(false);
        }
        Handler handler = this.f8823v;
        if (handler != null) {
            handler.removeCallbacks(this.f8803F);
            this.f8823v = null;
        }
        if (this.f8803F != null) {
            this.f8803F = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x001d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0034  */
    /* renamed from: y1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Location m9751y1() {
        Location lastKnownLocation;
        Location lastKnownLocation2 = null;
        try {
            lastKnownLocation = this.f8818q.getLastKnownLocation("gps");
            try {
                lastKnownLocation2 = this.f8818q.getLastKnownLocation("network");
            } catch (SecurityException e9) {
                e = e9;
                e.printStackTrace();
                if (0 >= (lastKnownLocation == null ? lastKnownLocation.getTime() : 0L) - (lastKnownLocation2 == null ? lastKnownLocation2.getTime() : 0L)) {
                }
            }
        } catch (SecurityException e10) {
            e = e10;
            lastKnownLocation = null;
        }
        if (0 >= (lastKnownLocation == null ? lastKnownLocation.getTime() : 0L) - (lastKnownLocation2 == null ? lastKnownLocation2.getTime() : 0L)) {
            m9742L1(lastKnownLocation);
            return lastKnownLocation;
        }
        m9742L1(lastKnownLocation2);
        return lastKnownLocation2;
    }
}
