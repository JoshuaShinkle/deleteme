package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

/* loaded from: classes2.dex */
public interface IGoogleMapDelegate extends IInterface {
    com.google.android.gms.internal.maps.zzh addCircle(CircleOptions circleOptions);

    com.google.android.gms.internal.maps.zzk addGroundOverlay(GroundOverlayOptions groundOverlayOptions);

    com.google.android.gms.internal.maps.zzt addMarker(MarkerOptions markerOptions);

    com.google.android.gms.internal.maps.zzw addPolygon(PolygonOptions polygonOptions);

    com.google.android.gms.internal.maps.zzz addPolyline(PolylineOptions polylineOptions);

    com.google.android.gms.internal.maps.zzac addTileOverlay(TileOverlayOptions tileOverlayOptions);

    void animateCamera(IObjectWrapper iObjectWrapper);

    void animateCameraWithCallback(IObjectWrapper iObjectWrapper, zzc zzcVar);

    void animateCameraWithDurationAndCallback(IObjectWrapper iObjectWrapper, int i9, zzc zzcVar);

    void clear();

    CameraPosition getCameraPosition();

    com.google.android.gms.internal.maps.zzn getFocusedBuilding();

    void getMapAsync(zzap zzapVar);

    int getMapType();

    float getMaxZoomLevel();

    float getMinZoomLevel();

    Location getMyLocation();

    IProjectionDelegate getProjection();

    IUiSettingsDelegate getUiSettings();

    boolean isBuildingsEnabled();

    boolean isIndoorEnabled();

    boolean isMyLocationEnabled();

    boolean isTrafficEnabled();

    void moveCamera(IObjectWrapper iObjectWrapper);

    void onCreate(Bundle bundle);

    void onDestroy();

    void onEnterAmbient(Bundle bundle);

    void onExitAmbient();

    void onLowMemory();

    void onPause();

    void onResume();

    void onSaveInstanceState(Bundle bundle);

    void onStart();

    void onStop();

    void resetMinMaxZoomPreference();

    void setBuildingsEnabled(boolean z8);

    void setContentDescription(String str);

    boolean setIndoorEnabled(boolean z8);

    void setInfoWindowAdapter(zzh zzhVar);

    void setLatLngBoundsForCameraTarget(LatLngBounds latLngBounds);

    void setLocationSource(ILocationSourceDelegate iLocationSourceDelegate);

    boolean setMapStyle(MapStyleOptions mapStyleOptions);

    void setMapType(int i9);

    void setMaxZoomPreference(float f9);

    void setMinZoomPreference(float f9);

    void setMyLocationEnabled(boolean z8);

    void setOnCameraChangeListener(zzl zzlVar);

    void setOnCameraIdleListener(zzn zznVar);

    void setOnCameraMoveCanceledListener(zzp zzpVar);

    void setOnCameraMoveListener(zzr zzrVar);

    void setOnCameraMoveStartedListener(zzt zztVar);

    void setOnCircleClickListener(zzv zzvVar);

    void setOnGroundOverlayClickListener(zzx zzxVar);

    void setOnIndoorStateChangeListener(zzz zzzVar);

    void setOnInfoWindowClickListener(zzab zzabVar);

    void setOnInfoWindowCloseListener(zzad zzadVar);

    void setOnInfoWindowLongClickListener(zzaf zzafVar);

    void setOnMapClickListener(zzaj zzajVar);

    void setOnMapLoadedCallback(zzal zzalVar);

    void setOnMapLongClickListener(zzan zzanVar);

    void setOnMarkerClickListener(zzar zzarVar);

    void setOnMarkerDragListener(zzat zzatVar);

    void setOnMyLocationButtonClickListener(zzav zzavVar);

    void setOnMyLocationChangeListener(zzax zzaxVar);

    void setOnMyLocationClickListener(zzaz zzazVar);

    void setOnPoiClickListener(zzbb zzbbVar);

    void setOnPolygonClickListener(zzbd zzbdVar);

    void setOnPolylineClickListener(zzbf zzbfVar);

    void setPadding(int i9, int i10, int i11, int i12);

    void setTrafficEnabled(boolean z8);

    void setWatermarkEnabled(boolean z8);

    void snapshot(zzbs zzbsVar, IObjectWrapper iObjectWrapper);

    void snapshotForTest(zzbs zzbsVar);

    void stopAnimation();

    boolean useViewLifecycleWhenInFragment();
}
