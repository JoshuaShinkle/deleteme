package androidx.appcompat.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import p042d0.AbstractC4615b;
import p042d0.C4614a0;
import p197t.C6273a;
import p233x.InterfaceMenuC6559a;

/* renamed from: androidx.appcompat.view.menu.g */
/* loaded from: classes.dex */
public class C0137g implements InterfaceMenuC6559a {
    private static final String ACTION_VIEW_STATES_KEY = "android:menu:actionviewstates";
    private static final String EXPANDED_ACTION_VIEW_ID = "android:menu:expandedactionview";
    private static final String PRESENTER_KEY = "android:menu:presenters";
    private static final String TAG = "MenuBuilder";
    private static final int[] sCategoryToOrder = {1, 4, 5, 3, 2, 0};
    private a mCallback;
    private final Context mContext;
    private ContextMenu.ContextMenuInfo mCurrentMenuInfo;
    private C0139i mExpandedItem;
    Drawable mHeaderIcon;
    CharSequence mHeaderTitle;
    View mHeaderView;
    private boolean mOverrideVisibleItems;
    private boolean mQwertyMode;
    private final Resources mResources;
    private boolean mShortcutsVisible;
    private int mDefaultShowAsAction = 0;
    private boolean mPreventDispatchingItemsChanged = false;
    private boolean mItemsChangedWhileDispatchPrevented = false;
    private boolean mStructureChangedWhileDispatchPrevented = false;
    private boolean mOptionalIconsVisible = false;
    private boolean mIsClosing = false;
    private ArrayList<C0139i> mTempShortcutItemList = new ArrayList<>();
    private CopyOnWriteArrayList<WeakReference<InterfaceC0143m>> mPresenters = new CopyOnWriteArrayList<>();
    private boolean mGroupDividerEnabled = false;
    private ArrayList<C0139i> mItems = new ArrayList<>();
    private ArrayList<C0139i> mVisibleItems = new ArrayList<>();
    private boolean mIsVisibleItemsStale = true;
    private ArrayList<C0139i> mActionItems = new ArrayList<>();
    private ArrayList<C0139i> mNonActionItems = new ArrayList<>();
    private boolean mIsActionItemsStale = true;

    /* renamed from: androidx.appcompat.view.menu.g$a */
    public interface a {
        boolean onMenuItemSelected(C0137g c0137g, MenuItem menuItem);

        void onMenuModeChange(C0137g c0137g);
    }

    /* renamed from: androidx.appcompat.view.menu.g$b */
    public interface b {
        /* renamed from: a */
        boolean mo461a(C0139i c0139i);
    }

    public C0137g(Context context) {
        this.mContext = context;
        this.mResources = context.getResources();
        setShortcutsVisibleInner(true);
    }

    private C0139i createNewMenuItem(int i9, int i10, int i11, int i12, CharSequence charSequence, int i13) {
        return new C0139i(this, i9, i10, i11, i12, charSequence, i13);
    }

    private void dispatchPresenterUpdate(boolean z8) {
        if (this.mPresenters.isEmpty()) {
            return;
        }
        stopDispatchingItemsChanged();
        Iterator<WeakReference<InterfaceC0143m>> it = this.mPresenters.iterator();
        while (it.hasNext()) {
            WeakReference<InterfaceC0143m> next = it.next();
            InterfaceC0143m interfaceC0143m = next.get();
            if (interfaceC0143m == null) {
                this.mPresenters.remove(next);
            } else {
                interfaceC0143m.updateMenuView(z8);
            }
        }
        startDispatchingItemsChanged();
    }

    private void dispatchRestoreInstanceState(Bundle bundle) {
        Parcelable parcelable;
        SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(PRESENTER_KEY);
        if (sparseParcelableArray == null || this.mPresenters.isEmpty()) {
            return;
        }
        Iterator<WeakReference<InterfaceC0143m>> it = this.mPresenters.iterator();
        while (it.hasNext()) {
            WeakReference<InterfaceC0143m> next = it.next();
            InterfaceC0143m interfaceC0143m = next.get();
            if (interfaceC0143m == null) {
                this.mPresenters.remove(next);
            } else {
                int id = interfaceC0143m.getId();
                if (id > 0 && (parcelable = (Parcelable) sparseParcelableArray.get(id)) != null) {
                    interfaceC0143m.onRestoreInstanceState(parcelable);
                }
            }
        }
    }

    private void dispatchSaveInstanceState(Bundle bundle) {
        Parcelable parcelableOnSaveInstanceState;
        if (this.mPresenters.isEmpty()) {
            return;
        }
        SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
        Iterator<WeakReference<InterfaceC0143m>> it = this.mPresenters.iterator();
        while (it.hasNext()) {
            WeakReference<InterfaceC0143m> next = it.next();
            InterfaceC0143m interfaceC0143m = next.get();
            if (interfaceC0143m == null) {
                this.mPresenters.remove(next);
            } else {
                int id = interfaceC0143m.getId();
                if (id > 0 && (parcelableOnSaveInstanceState = interfaceC0143m.onSaveInstanceState()) != null) {
                    sparseArray.put(id, parcelableOnSaveInstanceState);
                }
            }
        }
        bundle.putSparseParcelableArray(PRESENTER_KEY, sparseArray);
    }

    private boolean dispatchSubMenuSelected(SubMenuC0148r subMenuC0148r, InterfaceC0143m interfaceC0143m) {
        if (this.mPresenters.isEmpty()) {
            return false;
        }
        boolean zOnSubMenuSelected = interfaceC0143m != null ? interfaceC0143m.onSubMenuSelected(subMenuC0148r) : false;
        Iterator<WeakReference<InterfaceC0143m>> it = this.mPresenters.iterator();
        while (it.hasNext()) {
            WeakReference<InterfaceC0143m> next = it.next();
            InterfaceC0143m interfaceC0143m2 = next.get();
            if (interfaceC0143m2 == null) {
                this.mPresenters.remove(next);
            } else if (!zOnSubMenuSelected) {
                zOnSubMenuSelected = interfaceC0143m2.onSubMenuSelected(subMenuC0148r);
            }
        }
        return zOnSubMenuSelected;
    }

    private static int findInsertIndex(ArrayList<C0139i> arrayList, int i9) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size).m526f() <= i9) {
                return size + 1;
            }
        }
        return 0;
    }

    private static int getOrdering(int i9) {
        int i10 = ((-65536) & i9) >> 16;
        if (i10 >= 0) {
            int[] iArr = sCategoryToOrder;
            if (i10 < iArr.length) {
                return (i9 & 65535) | (iArr[i10] << 16);
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    private void removeItemAtInt(int i9, boolean z8) {
        if (i9 < 0 || i9 >= this.mItems.size()) {
            return;
        }
        this.mItems.remove(i9);
        if (z8) {
            onItemsChanged(true);
        }
    }

    private void setHeaderInternal(int i9, CharSequence charSequence, int i10, Drawable drawable, View view) {
        Resources resources = getResources();
        if (view != null) {
            this.mHeaderView = view;
            this.mHeaderTitle = null;
            this.mHeaderIcon = null;
        } else {
            if (i9 > 0) {
                this.mHeaderTitle = resources.getText(i9);
            } else if (charSequence != null) {
                this.mHeaderTitle = charSequence;
            }
            if (i10 > 0) {
                this.mHeaderIcon = C6273a.m24025d(getContext(), i10);
            } else if (drawable != null) {
                this.mHeaderIcon = drawable;
            }
            this.mHeaderView = null;
        }
        onItemsChanged(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void setShortcutsVisibleInner(boolean z8) {
        boolean z9;
        if (z8) {
            z9 = this.mResources.getConfiguration().keyboard != 1 && C4614a0.m18392c(ViewConfiguration.get(this.mContext), this.mContext);
        }
        this.mShortcutsVisible = z9;
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return addInternal(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i9, int i10, int i11, ComponentName componentName, Intent[] intentArr, Intent intent, int i12, MenuItem[] menuItemArr) {
        int i13;
        PackageManager packageManager = this.mContext.getPackageManager();
        List<ResolveInfo> listQueryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = listQueryIntentActivityOptions != null ? listQueryIntentActivityOptions.size() : 0;
        if ((i12 & 1) == 0) {
            removeGroup(i9);
        }
        for (int i14 = 0; i14 < size; i14++) {
            ResolveInfo resolveInfo = listQueryIntentActivityOptions.get(i14);
            int i15 = resolveInfo.specificIndex;
            Intent intent2 = new Intent(i15 < 0 ? intent : intentArr[i15]);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            intent2.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
            MenuItem intent3 = add(i9, i10, i11, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent2);
            if (menuItemArr != null && (i13 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i13] = intent3;
            }
        }
        return size;
    }

    public MenuItem addInternal(int i9, int i10, int i11, CharSequence charSequence) {
        int ordering = getOrdering(i11);
        C0139i c0139iCreateNewMenuItem = createNewMenuItem(i9, i10, i11, ordering, charSequence, this.mDefaultShowAsAction);
        ContextMenu.ContextMenuInfo contextMenuInfo = this.mCurrentMenuInfo;
        if (contextMenuInfo != null) {
            c0139iCreateNewMenuItem.m542v(contextMenuInfo);
        }
        ArrayList<C0139i> arrayList = this.mItems;
        arrayList.add(findInsertIndex(arrayList, ordering), c0139iCreateNewMenuItem);
        onItemsChanged(true);
        return c0139iCreateNewMenuItem;
    }

    public void addMenuPresenter(InterfaceC0143m interfaceC0143m) {
        addMenuPresenter(interfaceC0143m, this.mContext);
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public void changeMenuMode() {
        a aVar = this.mCallback;
        if (aVar != null) {
            aVar.onMenuModeChange(this);
        }
    }

    @Override // android.view.Menu
    public void clear() {
        C0139i c0139i = this.mExpandedItem;
        if (c0139i != null) {
            collapseItemActionView(c0139i);
        }
        this.mItems.clear();
        onItemsChanged(true);
    }

    public void clearAll() {
        this.mPreventDispatchingItemsChanged = true;
        clear();
        clearHeader();
        this.mPresenters.clear();
        this.mPreventDispatchingItemsChanged = false;
        this.mItemsChangedWhileDispatchPrevented = false;
        this.mStructureChangedWhileDispatchPrevented = false;
        onItemsChanged(true);
    }

    public void clearHeader() {
        this.mHeaderIcon = null;
        this.mHeaderTitle = null;
        this.mHeaderView = null;
        onItemsChanged(false);
    }

    public final void close(boolean z8) {
        if (this.mIsClosing) {
            return;
        }
        this.mIsClosing = true;
        Iterator<WeakReference<InterfaceC0143m>> it = this.mPresenters.iterator();
        while (it.hasNext()) {
            WeakReference<InterfaceC0143m> next = it.next();
            InterfaceC0143m interfaceC0143m = next.get();
            if (interfaceC0143m == null) {
                this.mPresenters.remove(next);
            } else {
                interfaceC0143m.onCloseMenu(this, z8);
            }
        }
        this.mIsClosing = false;
    }

    public boolean collapseItemActionView(C0139i c0139i) {
        boolean zCollapseItemActionView = false;
        if (!this.mPresenters.isEmpty() && this.mExpandedItem == c0139i) {
            stopDispatchingItemsChanged();
            Iterator<WeakReference<InterfaceC0143m>> it = this.mPresenters.iterator();
            while (it.hasNext()) {
                WeakReference<InterfaceC0143m> next = it.next();
                InterfaceC0143m interfaceC0143m = next.get();
                if (interfaceC0143m == null) {
                    this.mPresenters.remove(next);
                } else {
                    zCollapseItemActionView = interfaceC0143m.collapseItemActionView(this, c0139i);
                    if (zCollapseItemActionView) {
                        break;
                    }
                }
            }
            startDispatchingItemsChanged();
            if (zCollapseItemActionView) {
                this.mExpandedItem = null;
            }
        }
        return zCollapseItemActionView;
    }

    public boolean dispatchMenuItemSelected(C0137g c0137g, MenuItem menuItem) {
        a aVar = this.mCallback;
        return aVar != null && aVar.onMenuItemSelected(c0137g, menuItem);
    }

    public boolean expandItemActionView(C0139i c0139i) {
        boolean zExpandItemActionView = false;
        if (this.mPresenters.isEmpty()) {
            return false;
        }
        stopDispatchingItemsChanged();
        Iterator<WeakReference<InterfaceC0143m>> it = this.mPresenters.iterator();
        while (it.hasNext()) {
            WeakReference<InterfaceC0143m> next = it.next();
            InterfaceC0143m interfaceC0143m = next.get();
            if (interfaceC0143m == null) {
                this.mPresenters.remove(next);
            } else {
                zExpandItemActionView = interfaceC0143m.expandItemActionView(this, c0139i);
                if (zExpandItemActionView) {
                    break;
                }
            }
        }
        startDispatchingItemsChanged();
        if (zExpandItemActionView) {
            this.mExpandedItem = c0139i;
        }
        return zExpandItemActionView;
    }

    public int findGroupIndex(int i9) {
        return findGroupIndex(i9, 0);
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i9) {
        MenuItem menuItemFindItem;
        int size = size();
        for (int i10 = 0; i10 < size; i10++) {
            C0139i c0139i = this.mItems.get(i10);
            if (c0139i.getItemId() == i9) {
                return c0139i;
            }
            if (c0139i.hasSubMenu() && (menuItemFindItem = c0139i.getSubMenu().findItem(i9)) != null) {
                return menuItemFindItem;
            }
        }
        return null;
    }

    public int findItemIndex(int i9) {
        int size = size();
        for (int i10 = 0; i10 < size; i10++) {
            if (this.mItems.get(i10).getItemId() == i9) {
                return i10;
            }
        }
        return -1;
    }

    public C0139i findItemWithShortcutForKey(int i9, KeyEvent keyEvent) {
        ArrayList<C0139i> arrayList = this.mTempShortcutItemList;
        arrayList.clear();
        findItemsWithShortcutForKey(arrayList, i9, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return arrayList.get(0);
        }
        boolean zIsQwertyMode = isQwertyMode();
        for (int i10 = 0; i10 < size; i10++) {
            C0139i c0139i = arrayList.get(i10);
            char alphabeticShortcut = zIsQwertyMode ? c0139i.getAlphabeticShortcut() : c0139i.getNumericShortcut();
            char[] cArr = keyData.meta;
            if ((alphabeticShortcut == cArr[0] && (metaState & 2) == 0) || ((alphabeticShortcut == cArr[2] && (metaState & 2) != 0) || (zIsQwertyMode && alphabeticShortcut == '\b' && i9 == 67))) {
                return c0139i;
            }
        }
        return null;
    }

    public void findItemsWithShortcutForKey(List<C0139i> list, int i9, KeyEvent keyEvent) {
        boolean zIsQwertyMode = isQwertyMode();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i9 == 67) {
            int size = this.mItems.size();
            for (int i10 = 0; i10 < size; i10++) {
                C0139i c0139i = this.mItems.get(i10);
                if (c0139i.hasSubMenu()) {
                    ((C0137g) c0139i.getSubMenu()).findItemsWithShortcutForKey(list, i9, keyEvent);
                }
                char alphabeticShortcut = zIsQwertyMode ? c0139i.getAlphabeticShortcut() : c0139i.getNumericShortcut();
                if (((modifiers & 69647) == ((zIsQwertyMode ? c0139i.getAlphabeticModifiers() : c0139i.getNumericModifiers()) & 69647)) && alphabeticShortcut != 0) {
                    char[] cArr = keyData.meta;
                    if ((alphabeticShortcut == cArr[0] || alphabeticShortcut == cArr[2] || (zIsQwertyMode && alphabeticShortcut == '\b' && i9 == 67)) && c0139i.isEnabled()) {
                        list.add(c0139i);
                    }
                }
            }
        }
    }

    public void flagActionItems() {
        ArrayList<C0139i> visibleItems = getVisibleItems();
        if (this.mIsActionItemsStale) {
            Iterator<WeakReference<InterfaceC0143m>> it = this.mPresenters.iterator();
            boolean zFlagActionItems = false;
            while (it.hasNext()) {
                WeakReference<InterfaceC0143m> next = it.next();
                InterfaceC0143m interfaceC0143m = next.get();
                if (interfaceC0143m == null) {
                    this.mPresenters.remove(next);
                } else {
                    zFlagActionItems |= interfaceC0143m.flagActionItems();
                }
            }
            if (zFlagActionItems) {
                this.mActionItems.clear();
                this.mNonActionItems.clear();
                int size = visibleItems.size();
                for (int i9 = 0; i9 < size; i9++) {
                    C0139i c0139i = visibleItems.get(i9);
                    if (c0139i.m532l()) {
                        this.mActionItems.add(c0139i);
                    } else {
                        this.mNonActionItems.add(c0139i);
                    }
                }
            } else {
                this.mActionItems.clear();
                this.mNonActionItems.clear();
                this.mNonActionItems.addAll(getVisibleItems());
            }
            this.mIsActionItemsStale = false;
        }
    }

    public ArrayList<C0139i> getActionItems() {
        flagActionItems();
        return this.mActionItems;
    }

    public String getActionViewStatesKey() {
        return ACTION_VIEW_STATES_KEY;
    }

    public Context getContext() {
        return this.mContext;
    }

    public C0139i getExpandedItem() {
        return this.mExpandedItem;
    }

    public Drawable getHeaderIcon() {
        return this.mHeaderIcon;
    }

    public CharSequence getHeaderTitle() {
        return this.mHeaderTitle;
    }

    public View getHeaderView() {
        return this.mHeaderView;
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i9) {
        return this.mItems.get(i9);
    }

    public ArrayList<C0139i> getNonActionItems() {
        flagActionItems();
        return this.mNonActionItems;
    }

    public boolean getOptionalIconsVisible() {
        return this.mOptionalIconsVisible;
    }

    public Resources getResources() {
        return this.mResources;
    }

    public C0137g getRootMenu() {
        return this;
    }

    public ArrayList<C0139i> getVisibleItems() {
        if (!this.mIsVisibleItemsStale) {
            return this.mVisibleItems;
        }
        this.mVisibleItems.clear();
        int size = this.mItems.size();
        for (int i9 = 0; i9 < size; i9++) {
            C0139i c0139i = this.mItems.get(i9);
            if (c0139i.isVisible()) {
                this.mVisibleItems.add(c0139i);
            }
        }
        this.mIsVisibleItemsStale = false;
        this.mIsActionItemsStale = true;
        return this.mVisibleItems;
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        if (this.mOverrideVisibleItems) {
            return true;
        }
        int size = size();
        for (int i9 = 0; i9 < size; i9++) {
            if (this.mItems.get(i9).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public boolean isGroupDividerEnabled() {
        return this.mGroupDividerEnabled;
    }

    public boolean isQwertyMode() {
        return this.mQwertyMode;
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int i9, KeyEvent keyEvent) {
        return findItemWithShortcutForKey(i9, keyEvent) != null;
    }

    public boolean isShortcutsVisible() {
        return this.mShortcutsVisible;
    }

    public void onItemActionRequestChanged(C0139i c0139i) {
        this.mIsActionItemsStale = true;
        onItemsChanged(true);
    }

    public void onItemVisibleChanged(C0139i c0139i) {
        this.mIsVisibleItemsStale = true;
        onItemsChanged(true);
    }

    public void onItemsChanged(boolean z8) {
        if (this.mPreventDispatchingItemsChanged) {
            this.mItemsChangedWhileDispatchPrevented = true;
            if (z8) {
                this.mStructureChangedWhileDispatchPrevented = true;
                return;
            }
            return;
        }
        if (z8) {
            this.mIsVisibleItemsStale = true;
            this.mIsActionItemsStale = true;
        }
        dispatchPresenterUpdate(z8);
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i9, int i10) {
        return performItemAction(findItem(i9), i10);
    }

    public boolean performItemAction(MenuItem menuItem, int i9) {
        return performItemAction(menuItem, null, i9);
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i9, KeyEvent keyEvent, int i10) {
        C0139i c0139iFindItemWithShortcutForKey = findItemWithShortcutForKey(i9, keyEvent);
        boolean zPerformItemAction = c0139iFindItemWithShortcutForKey != null ? performItemAction(c0139iFindItemWithShortcutForKey, i10) : false;
        if ((i10 & 2) != 0) {
            close(true);
        }
        return zPerformItemAction;
    }

    @Override // android.view.Menu
    public void removeGroup(int i9) {
        int iFindGroupIndex = findGroupIndex(i9);
        if (iFindGroupIndex >= 0) {
            int size = this.mItems.size() - iFindGroupIndex;
            int i10 = 0;
            while (true) {
                int i11 = i10 + 1;
                if (i10 >= size || this.mItems.get(iFindGroupIndex).getGroupId() != i9) {
                    break;
                }
                removeItemAtInt(iFindGroupIndex, false);
                i10 = i11;
            }
            onItemsChanged(true);
        }
    }

    @Override // android.view.Menu
    public void removeItem(int i9) {
        removeItemAtInt(findItemIndex(i9), true);
    }

    public void removeItemAt(int i9) {
        removeItemAtInt(i9, true);
    }

    public void removeMenuPresenter(InterfaceC0143m interfaceC0143m) {
        Iterator<WeakReference<InterfaceC0143m>> it = this.mPresenters.iterator();
        while (it.hasNext()) {
            WeakReference<InterfaceC0143m> next = it.next();
            InterfaceC0143m interfaceC0143m2 = next.get();
            if (interfaceC0143m2 == null || interfaceC0143m2 == interfaceC0143m) {
                this.mPresenters.remove(next);
            }
        }
    }

    public void restoreActionViewStates(Bundle bundle) {
        MenuItem menuItemFindItem;
        if (bundle == null) {
            return;
        }
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(getActionViewStatesKey());
        int size = size();
        for (int i9 = 0; i9 < size; i9++) {
            MenuItem item = getItem(i9);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                actionView.restoreHierarchyState(sparseParcelableArray);
            }
            if (item.hasSubMenu()) {
                ((SubMenuC0148r) item.getSubMenu()).restoreActionViewStates(bundle);
            }
        }
        int i10 = bundle.getInt(EXPANDED_ACTION_VIEW_ID);
        if (i10 <= 0 || (menuItemFindItem = findItem(i10)) == null) {
            return;
        }
        menuItemFindItem.expandActionView();
    }

    public void restorePresenterStates(Bundle bundle) {
        dispatchRestoreInstanceState(bundle);
    }

    public void saveActionViewStates(Bundle bundle) {
        int size = size();
        SparseArray<? extends Parcelable> sparseArray = null;
        for (int i9 = 0; i9 < size; i9++) {
            MenuItem item = getItem(i9);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                actionView.saveHierarchyState(sparseArray);
                if (item.isActionViewExpanded()) {
                    bundle.putInt(EXPANDED_ACTION_VIEW_ID, item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((SubMenuC0148r) item.getSubMenu()).saveActionViewStates(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(getActionViewStatesKey(), sparseArray);
        }
    }

    public void savePresenterStates(Bundle bundle) {
        dispatchSaveInstanceState(bundle);
    }

    public void setCallback(a aVar) {
        this.mCallback = aVar;
    }

    public void setCurrentMenuInfo(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.mCurrentMenuInfo = contextMenuInfo;
    }

    public C0137g setDefaultShowAsAction(int i9) {
        this.mDefaultShowAsAction = i9;
        return this;
    }

    public void setExclusiveItemChecked(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.mItems.size();
        stopDispatchingItemsChanged();
        for (int i9 = 0; i9 < size; i9++) {
            C0139i c0139i = this.mItems.get(i9);
            if (c0139i.getGroupId() == groupId && c0139i.m533m() && c0139i.isCheckable()) {
                c0139i.m539s(c0139i == menuItem);
            }
        }
        startDispatchingItemsChanged();
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i9, boolean z8, boolean z9) {
        int size = this.mItems.size();
        for (int i10 = 0; i10 < size; i10++) {
            C0139i c0139i = this.mItems.get(i10);
            if (c0139i.getGroupId() == i9) {
                c0139i.m540t(z9);
                c0139i.setCheckable(z8);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupDividerEnabled(boolean z8) {
        this.mGroupDividerEnabled = z8;
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i9, boolean z8) {
        int size = this.mItems.size();
        for (int i10 = 0; i10 < size; i10++) {
            C0139i c0139i = this.mItems.get(i10);
            if (c0139i.getGroupId() == i9) {
                c0139i.setEnabled(z8);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i9, boolean z8) {
        int size = this.mItems.size();
        boolean z9 = false;
        for (int i10 = 0; i10 < size; i10++) {
            C0139i c0139i = this.mItems.get(i10);
            if (c0139i.getGroupId() == i9 && c0139i.m545y(z8)) {
                z9 = true;
            }
        }
        if (z9) {
            onItemsChanged(true);
        }
    }

    public C0137g setHeaderIconInt(Drawable drawable) {
        setHeaderInternal(0, null, 0, drawable, null);
        return this;
    }

    public C0137g setHeaderTitleInt(CharSequence charSequence) {
        setHeaderInternal(0, charSequence, 0, null, null);
        return this;
    }

    public C0137g setHeaderViewInt(View view) {
        setHeaderInternal(0, null, 0, null, view);
        return this;
    }

    public void setOptionalIconsVisible(boolean z8) {
        this.mOptionalIconsVisible = z8;
    }

    public void setOverrideVisibleItems(boolean z8) {
        this.mOverrideVisibleItems = z8;
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z8) {
        this.mQwertyMode = z8;
        onItemsChanged(false);
    }

    public void setShortcutsVisible(boolean z8) {
        if (this.mShortcutsVisible == z8) {
            return;
        }
        setShortcutsVisibleInner(z8);
        onItemsChanged(false);
    }

    @Override // android.view.Menu
    public int size() {
        return this.mItems.size();
    }

    public void startDispatchingItemsChanged() {
        this.mPreventDispatchingItemsChanged = false;
        if (this.mItemsChangedWhileDispatchPrevented) {
            this.mItemsChangedWhileDispatchPrevented = false;
            onItemsChanged(this.mStructureChangedWhileDispatchPrevented);
        }
    }

    public void stopDispatchingItemsChanged() {
        if (this.mPreventDispatchingItemsChanged) {
            return;
        }
        this.mPreventDispatchingItemsChanged = true;
        this.mItemsChangedWhileDispatchPrevented = false;
        this.mStructureChangedWhileDispatchPrevented = false;
    }

    @Override // android.view.Menu
    public MenuItem add(int i9) {
        return addInternal(0, 0, 0, this.mResources.getString(i9));
    }

    public void addMenuPresenter(InterfaceC0143m interfaceC0143m, Context context) {
        this.mPresenters.add(new WeakReference<>(interfaceC0143m));
        interfaceC0143m.initForMenu(context, this);
        this.mIsActionItemsStale = true;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i9) {
        return addSubMenu(0, 0, 0, this.mResources.getString(i9));
    }

    public int findGroupIndex(int i9, int i10) {
        int size = size();
        if (i10 < 0) {
            i10 = 0;
        }
        while (i10 < size) {
            if (this.mItems.get(i10).getGroupId() == i9) {
                return i10;
            }
            i10++;
        }
        return -1;
    }

    public boolean performItemAction(MenuItem menuItem, InterfaceC0143m interfaceC0143m, int i9) {
        C0139i c0139i = (C0139i) menuItem;
        if (c0139i == null || !c0139i.isEnabled()) {
            return false;
        }
        boolean zM531k = c0139i.m531k();
        AbstractC4615b abstractC4615bMo469b = c0139i.mo469b();
        boolean z8 = abstractC4615bMo469b != null && abstractC4615bMo469b.mo548a();
        if (c0139i.m530j()) {
            zM531k |= c0139i.expandActionView();
            if (zM531k) {
                close(true);
            }
        } else if (c0139i.hasSubMenu() || z8) {
            if ((i9 & 4) == 0) {
                close(false);
            }
            if (!c0139i.hasSubMenu()) {
                c0139i.m544x(new SubMenuC0148r(getContext(), this, c0139i));
            }
            SubMenuC0148r subMenuC0148r = (SubMenuC0148r) c0139i.getSubMenu();
            if (z8) {
                abstractC4615bMo469b.mo551f(subMenuC0148r);
            }
            zM531k |= dispatchSubMenuSelected(subMenuC0148r, interfaceC0143m);
            if (!zM531k) {
                close(true);
            }
        } else if ((i9 & 1) == 0) {
            close(true);
        }
        return zM531k;
    }

    public C0137g setHeaderIconInt(int i9) {
        setHeaderInternal(0, null, i9, null, null);
        return this;
    }

    public C0137g setHeaderTitleInt(int i9) {
        setHeaderInternal(i9, null, 0, null, null);
        return this;
    }

    @Override // android.view.Menu
    public MenuItem add(int i9, int i10, int i11, CharSequence charSequence) {
        return addInternal(i9, i10, i11, charSequence);
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i9, int i10, int i11, CharSequence charSequence) {
        C0139i c0139i = (C0139i) addInternal(i9, i10, i11, charSequence);
        SubMenuC0148r subMenuC0148r = new SubMenuC0148r(this.mContext, this, c0139i);
        c0139i.m544x(subMenuC0148r);
        return subMenuC0148r;
    }

    @Override // android.view.Menu
    public MenuItem add(int i9, int i10, int i11, int i12) {
        return addInternal(i9, i10, i11, this.mResources.getString(i12));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i9, int i10, int i11, int i12) {
        return addSubMenu(i9, i10, i11, this.mResources.getString(i12));
    }

    @Override // android.view.Menu
    public void close() {
        close(true);
    }
}
