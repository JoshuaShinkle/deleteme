package org.apache.harmony.javax.security.auth;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Principal;
import java.util.Set;

/* loaded from: classes.dex */
public final class PrivateCredentialPermission extends Permission {
    private static final String READ = "read";
    private static final long serialVersionUID = 5284372143517237068L;
    private String credentialClass;
    private transient int offset;
    private transient CredOwner[] set;

    public static final class CredOwner implements Serializable {
        private static final long serialVersionUID = -5607449830436408266L;
        private transient boolean isClassWildcard;
        private transient boolean isPNameWildcard;
        String principalClass;
        String principalName;

        public CredOwner(String str, String str2) {
            if ("*".equals(str)) {
                this.isClassWildcard = true;
            }
            if ("*".equals(str2)) {
                this.isPNameWildcard = true;
            }
            if (this.isClassWildcard && !this.isPNameWildcard) {
                throw new IllegalArgumentException("auth.12");
            }
            this.principalClass = str;
            this.principalName = str2;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CredOwner)) {
                return false;
            }
            CredOwner credOwner = (CredOwner) obj;
            return this.principalClass.equals(credOwner.principalClass) && this.principalName.equals(credOwner.principalName);
        }

        public int hashCode() {
            return this.principalClass.hashCode() + this.principalName.hashCode();
        }

        public boolean implies(Object obj) {
            if (obj == this) {
                return true;
            }
            CredOwner credOwner = (CredOwner) obj;
            if (this.isClassWildcard || this.principalClass.equals(credOwner.principalClass)) {
                return this.isPNameWildcard || this.principalName.equals(credOwner.principalName);
            }
            return false;
        }
    }

    public PrivateCredentialPermission(String str, String str2) {
        super(str);
        if (!READ.equalsIgnoreCase(str2)) {
            throw new IllegalArgumentException("auth.11");
        }
        initTargetName(str);
    }

    private void initTargetName(String str) {
        boolean z8;
        if (str == null) {
            throw new NullPointerException("auth.0E");
        }
        String strTrim = str.trim();
        if (strTrim.length() == 0) {
            throw new IllegalArgumentException("auth.0F");
        }
        int iIndexOf = strTrim.indexOf(32);
        if (iIndexOf == -1) {
            throw new IllegalArgumentException("auth.10");
        }
        this.credentialClass = strTrim.substring(0, iIndexOf);
        int i9 = iIndexOf + 1;
        int length = strTrim.length();
        int i10 = 0;
        while (i9 < length) {
            int iIndexOf2 = strTrim.indexOf(32, i9);
            int iIndexOf3 = strTrim.indexOf(34, iIndexOf2 + 2);
            if (iIndexOf2 == -1 || iIndexOf3 == -1 || strTrim.charAt(iIndexOf2 + 1) != '\"') {
                throw new IllegalArgumentException("auth.10");
            }
            i9 = iIndexOf3 + 2;
            i10++;
        }
        if (i10 < 1) {
            throw new IllegalArgumentException("auth.10");
        }
        int iIndexOf4 = strTrim.indexOf(32) + 1;
        this.set = new CredOwner[i10];
        for (int i11 = 0; i11 < i10; i11++) {
            int iIndexOf5 = strTrim.indexOf(32, iIndexOf4);
            int i12 = iIndexOf5 + 2;
            int iIndexOf6 = strTrim.indexOf(34, i12);
            CredOwner credOwner = new CredOwner(strTrim.substring(iIndexOf4, iIndexOf5), strTrim.substring(i12, iIndexOf6));
            int i13 = 0;
            while (true) {
                if (i13 >= this.offset) {
                    z8 = false;
                    break;
                } else {
                    if (this.set[i13].equals(credOwner)) {
                        z8 = true;
                        break;
                    }
                    i13++;
                }
            }
            if (!z8) {
                CredOwner[] credOwnerArr = this.set;
                int i14 = this.offset;
                this.offset = i14 + 1;
                credOwnerArr[i14] = credOwner;
            }
            iIndexOf4 = iIndexOf6 + 2;
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        initTargetName(getName());
    }

    private boolean sameMembers(Object[] objArr, Object[] objArr2, int i9) {
        boolean z8;
        if (objArr == null && objArr2 == null) {
            return true;
        }
        if (objArr == null || objArr2 == null) {
            return false;
        }
        for (int i10 = 0; i10 < i9; i10++) {
            int i11 = 0;
            while (true) {
                if (i11 >= i9) {
                    z8 = false;
                    break;
                }
                if (objArr[i10].equals(objArr2[i11])) {
                    z8 = true;
                    break;
                }
                i11++;
            }
            if (!z8) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object obj) {
        int i9;
        if (obj == this) {
            return true;
        }
        if (obj == null || PrivateCredentialPermission.class != obj.getClass()) {
            return false;
        }
        PrivateCredentialPermission privateCredentialPermission = (PrivateCredentialPermission) obj;
        return this.credentialClass.equals(privateCredentialPermission.credentialClass) && (i9 = this.offset) == privateCredentialPermission.offset && sameMembers(this.set, privateCredentialPermission.set, i9);
    }

    @Override // java.security.Permission
    public String getActions() {
        return READ;
    }

    public String getCredentialClass() {
        return this.credentialClass;
    }

    public String[][] getPrincipals() {
        String[][] strArr = (String[][]) Array.newInstance((Class<?>) String.class, this.offset, 2);
        for (int i9 = 0; i9 < strArr.length; i9++) {
            String[] strArr2 = strArr[i9];
            CredOwner[] credOwnerArr = this.set;
            strArr2[0] = credOwnerArr[i9].principalClass;
            strArr[i9][1] = credOwnerArr[i9].principalName;
        }
        return strArr;
    }

    public int hashCode() {
        int iHashCode = 0;
        for (int i9 = 0; i9 < this.offset; i9++) {
            iHashCode += this.set[i9].hashCode();
        }
        return getCredentialClass().hashCode() + iHashCode;
    }

    @Override // java.security.Permission
    public boolean implies(Permission permission) {
        if (permission == null || PrivateCredentialPermission.class != permission.getClass()) {
            return false;
        }
        PrivateCredentialPermission privateCredentialPermission = (PrivateCredentialPermission) permission;
        if (!"*".equals(this.credentialClass) && !this.credentialClass.equals(privateCredentialPermission.getCredentialClass())) {
            return false;
        }
        int i9 = privateCredentialPermission.offset;
        if (i9 == 0) {
            return true;
        }
        CredOwner[] credOwnerArr = this.set;
        CredOwner[] credOwnerArr2 = privateCredentialPermission.set;
        int i10 = this.offset;
        for (int i11 = 0; i11 < i10; i11++) {
            int i12 = 0;
            while (i12 < i9 && !credOwnerArr[i11].implies(credOwnerArr2[i12])) {
                i12++;
            }
            if (i12 == credOwnerArr2.length) {
                return false;
            }
        }
        return true;
    }

    @Override // java.security.Permission
    public PermissionCollection newPermissionCollection() {
        return null;
    }

    public PrivateCredentialPermission(String str, Set<Principal> set) {
        super(str);
        this.credentialClass = str;
        this.set = new CredOwner[set.size()];
        for (Principal principal : set) {
            CredOwner credOwner = new CredOwner(principal.getClass().getName(), principal.getName());
            boolean z8 = false;
            int i9 = 0;
            while (true) {
                if (i9 >= this.offset) {
                    break;
                }
                if (this.set[i9].equals(credOwner)) {
                    z8 = true;
                    break;
                }
                i9++;
            }
            if (!z8) {
                CredOwner[] credOwnerArr = this.set;
                int i10 = this.offset;
                this.offset = i10 + 1;
                credOwnerArr[i10] = credOwner;
            }
        }
    }
}
