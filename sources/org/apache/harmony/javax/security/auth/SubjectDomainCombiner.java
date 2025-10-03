package org.apache.harmony.javax.security.auth;

import java.security.DomainCombiner;
import java.security.Principal;
import java.security.ProtectionDomain;
import java.util.Set;

/* loaded from: classes.dex */
public class SubjectDomainCombiner implements DomainCombiner {
    private static final AuthPermission _GET = new AuthPermission("getSubjectFromDomainCombiner");
    private Subject subject;

    public SubjectDomainCombiner(Subject subject) {
        subject.getClass();
        this.subject = subject;
    }

    @Override // java.security.DomainCombiner
    public ProtectionDomain[] combine(ProtectionDomain[] protectionDomainArr, ProtectionDomain[] protectionDomainArr2) {
        int i9;
        int length = protectionDomainArr != null ? protectionDomainArr.length + 0 : 0;
        if (protectionDomainArr2 != null) {
            length += protectionDomainArr2.length;
        }
        if (length == 0) {
            return null;
        }
        ProtectionDomain[] protectionDomainArr3 = new ProtectionDomain[length];
        if (protectionDomainArr != null) {
            Set<Principal> principals = this.subject.getPrincipals();
            Principal[] principalArr = (Principal[]) principals.toArray(new Principal[principals.size()]);
            i9 = 0;
            while (i9 < protectionDomainArr.length) {
                if (protectionDomainArr[i9] != null) {
                    protectionDomainArr3[i9] = new ProtectionDomain(protectionDomainArr[i9].getCodeSource(), protectionDomainArr[i9].getPermissions(), protectionDomainArr[i9].getClassLoader(), principalArr);
                }
                i9++;
            }
        } else {
            i9 = 0;
        }
        if (protectionDomainArr2 != null) {
            System.arraycopy(protectionDomainArr2, 0, protectionDomainArr3, i9, protectionDomainArr2.length);
        }
        return protectionDomainArr3;
    }

    public Subject getSubject() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(_GET);
        }
        return this.subject;
    }
}
