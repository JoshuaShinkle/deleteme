package org.apache.harmony.javax.security.auth;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.DomainCombiner;
import java.security.Permission;
import java.security.Principal;
import java.security.PrivilegedAction;
import java.security.PrivilegedExceptionAction;
import java.security.ProtectionDomain;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/* loaded from: classes.dex */
public final class Subject implements Serializable {
    private static final long serialVersionUID = -8308522755600156056L;
    private final Set<Principal> principals;
    private transient SecureSet<Object> privateCredentials;
    private transient SecureSet<Object> publicCredentials;
    private boolean readOnly;
    private static final AuthPermission _AS = new AuthPermission("doAs");
    private static final AuthPermission _AS_PRIVILEGED = new AuthPermission("doAsPrivileged");
    private static final AuthPermission _SUBJECT = new AuthPermission("getSubject");
    private static final AuthPermission _PRINCIPALS = new AuthPermission("modifyPrincipals");
    private static final AuthPermission _PRIVATE_CREDENTIALS = new AuthPermission("modifyPrivateCredentials");
    private static final AuthPermission _PUBLIC_CREDENTIALS = new AuthPermission("modifyPublicCredentials");
    private static final AuthPermission _READ_ONLY = new AuthPermission("setReadOnly");

    public Subject() {
        this.principals = new SecureSet(_PRINCIPALS);
        this.publicCredentials = new SecureSet<>(_PUBLIC_CREDENTIALS);
        this.privateCredentials = new SecureSet<>(_PRIVATE_CREDENTIALS);
        this.readOnly = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkPermission(Permission permission) {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(permission);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkState() {
        if (this.readOnly) {
            throw new IllegalStateException("auth.0A");
        }
    }

    public static Object doAs(Subject subject, PrivilegedAction privilegedAction) {
        checkPermission(_AS);
        return doAs_PrivilegedAction(subject, privilegedAction, AccessController.getContext());
    }

    public static Object doAsPrivileged(Subject subject, PrivilegedAction privilegedAction, AccessControlContext accessControlContext) {
        checkPermission(_AS_PRIVILEGED);
        return accessControlContext == null ? doAs_PrivilegedAction(subject, privilegedAction, new AccessControlContext(new ProtectionDomain[0])) : doAs_PrivilegedAction(subject, privilegedAction, accessControlContext);
    }

    private static Object doAs_PrivilegedAction(Subject subject, PrivilegedAction privilegedAction, final AccessControlContext accessControlContext) {
        final SubjectDomainCombiner subjectDomainCombiner = subject == null ? null : new SubjectDomainCombiner(subject);
        return AccessController.doPrivileged(privilegedAction, (AccessControlContext) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.apache.harmony.javax.security.auth.Subject.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                return new AccessControlContext(accessControlContext, subjectDomainCombiner);
            }
        }));
    }

    private static Object doAs_PrivilegedExceptionAction(Subject subject, PrivilegedExceptionAction privilegedExceptionAction, final AccessControlContext accessControlContext) {
        final SubjectDomainCombiner subjectDomainCombiner = subject == null ? null : new SubjectDomainCombiner(subject);
        return AccessController.doPrivileged(privilegedExceptionAction, (AccessControlContext) AccessController.doPrivileged(new PrivilegedAction<AccessControlContext>() { // from class: org.apache.harmony.javax.security.auth.Subject.2
            @Override // java.security.PrivilegedAction
            public AccessControlContext run() {
                return new AccessControlContext(accessControlContext, subjectDomainCombiner);
            }
        }));
    }

    public static Subject getSubject(final AccessControlContext accessControlContext) {
        checkPermission(_SUBJECT);
        if (accessControlContext == null) {
            throw new NullPointerException("auth.09");
        }
        DomainCombiner domainCombiner = (DomainCombiner) AccessController.doPrivileged(new PrivilegedAction<DomainCombiner>() { // from class: org.apache.harmony.javax.security.auth.Subject.3
            @Override // java.security.PrivilegedAction
            public DomainCombiner run() {
                return accessControlContext.getDomainCombiner();
            }
        });
        if (domainCombiner == null || !(domainCombiner instanceof SubjectDomainCombiner)) {
            return null;
        }
        return ((SubjectDomainCombiner) domainCombiner).getSubject();
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.publicCredentials = new SecureSet<>(_PUBLIC_CREDENTIALS);
        this.privateCredentials = new SecureSet<>(_PRIVATE_CREDENTIALS);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Subject.class == obj.getClass()) {
            Subject subject = (Subject) obj;
            if (this.principals.equals(subject.principals) && this.publicCredentials.equals(subject.publicCredentials) && this.privateCredentials.equals(subject.privateCredentials)) {
                return true;
            }
        }
        return false;
    }

    public Set<Principal> getPrincipals() {
        return this.principals;
    }

    public Set<Object> getPrivateCredentials() {
        return this.privateCredentials;
    }

    public Set<Object> getPublicCredentials() {
        return this.publicCredentials;
    }

    public int hashCode() {
        return this.principals.hashCode() + this.privateCredentials.hashCode() + this.publicCredentials.hashCode();
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public void setReadOnly() {
        checkPermission(_READ_ONLY);
        this.readOnly = true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Subject:\n");
        Iterator<Principal> it = this.principals.iterator();
        while (it.hasNext()) {
            sb.append("\tPrincipal: ");
            sb.append(it.next());
            sb.append('\n');
        }
        Iterator<Object> it2 = this.publicCredentials.iterator();
        while (it2.hasNext()) {
            sb.append("\tPublic Credential: ");
            sb.append(it2.next());
            sb.append('\n');
        }
        int length = sb.length() - 1;
        Iterator<Object> it3 = this.privateCredentials.iterator();
        while (it3.hasNext()) {
            try {
                sb.append("\tPrivate Credential: ");
                sb.append(it3.next());
                sb.append('\n');
            } catch (SecurityException unused) {
                sb.delete(length, sb.length());
                sb.append("\tPrivate Credentials: no accessible information\n");
            }
        }
        return sb.toString();
    }

    public <T extends Principal> Set<T> getPrincipals(Class<T> cls) {
        return ((SecureSet) this.principals).get(cls);
    }

    public <T> Set<T> getPrivateCredentials(Class<T> cls) {
        return (Set<T>) this.privateCredentials.get(cls);
    }

    public <T> Set<T> getPublicCredentials(Class<T> cls) {
        return (Set<T>) this.publicCredentials.get(cls);
    }

    public final class SecureSet<SST> extends AbstractSet<SST> implements Serializable {
        private static final int SET_Principal = 0;
        private static final int SET_PrivCred = 1;
        private static final int SET_PubCred = 2;
        private static final long serialVersionUID = 7911754171111800359L;
        private LinkedList<SST> elements;
        private transient AuthPermission permission;
        private int setType;

        public class SecureIterator implements Iterator<SST> {
            protected Iterator<SST> iterator;

            public SecureIterator(Iterator<SST> it) {
                this.iterator = it;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.iterator.hasNext();
            }

            @Override // java.util.Iterator
            public SST next() {
                return this.iterator.next();
            }

            @Override // java.util.Iterator
            public void remove() {
                Subject.this.checkState();
                Subject.checkPermission(SecureSet.this.permission);
                this.iterator.remove();
            }
        }

        public SecureSet(AuthPermission authPermission) {
            this.permission = authPermission;
            this.elements = new LinkedList<>();
        }

        private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
            objectInputStream.defaultReadObject();
            int i9 = this.setType;
            if (i9 == 0) {
                this.permission = Subject._PRINCIPALS;
            } else if (i9 == 1) {
                this.permission = Subject._PRIVATE_CREDENTIALS;
            } else {
                if (i9 != 2) {
                    throw new IllegalArgumentException();
                }
                this.permission = Subject._PUBLIC_CREDENTIALS;
            }
            Iterator<SST> it = this.elements.iterator();
            while (it.hasNext()) {
                verifyElement(it.next());
            }
        }

        private void verifyElement(Object obj) {
            obj.getClass();
            if (this.permission == Subject._PRINCIPALS && !Principal.class.isAssignableFrom(obj.getClass())) {
                throw new IllegalArgumentException("auth.0B");
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            if (this.permission == Subject._PRIVATE_CREDENTIALS) {
                Iterator<SST> it = iterator();
                while (it.hasNext()) {
                    it.next();
                }
                this.setType = 1;
            } else if (this.permission == Subject._PRINCIPALS) {
                this.setType = 0;
            } else {
                this.setType = 2;
            }
            objectOutputStream.defaultWriteObject();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean add(SST sst) {
            verifyElement(sst);
            Subject.this.checkState();
            Subject.checkPermission(this.permission);
            if (this.elements.contains(sst)) {
                return false;
            }
            this.elements.add(sst);
            return true;
        }

        public final <E> Set<E> get(final Class<E> cls) {
            cls.getClass();
            AbstractSet<E> abstractSet = new AbstractSet<E>() { // from class: org.apache.harmony.javax.security.auth.Subject.SecureSet.2
                private LinkedList<E> elements = new LinkedList<>();

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean add(E e9) {
                    if (cls.isAssignableFrom(e9.getClass())) {
                        if (this.elements.contains(e9)) {
                            return false;
                        }
                        this.elements.add(e9);
                        return true;
                    }
                    throw new IllegalArgumentException("auth.0C " + cls.getName());
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator<E> iterator() {
                    return this.elements.iterator();
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean retainAll(Collection<?> collection) {
                    collection.getClass();
                    return super.retainAll(collection);
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                public int size() {
                    return this.elements.size();
                }
            };
            Iterator<SST> it = iterator();
            while (it.hasNext()) {
                SST next = it.next();
                if (cls.isAssignableFrom(next.getClass())) {
                    abstractSet.add(cls.cast(next));
                }
            }
            return abstractSet;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<SST> iterator() {
            return this.permission == Subject._PRIVATE_CREDENTIALS ? new SecureSet<SST>.SecureIterator(this.elements.iterator()) { // from class: org.apache.harmony.javax.security.auth.Subject.SecureSet.1
                @Override // org.apache.harmony.javax.security.auth.Subject.SecureSet.SecureIterator, java.util.Iterator
                public SST next() {
                    SST next = this.iterator.next();
                    Subject.checkPermission(new PrivateCredentialPermission(next.getClass().getName(), (Set<Principal>) Subject.this.principals));
                    return next;
                }
            } : new SecureIterator(this.elements.iterator());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            collection.getClass();
            return super.retainAll(collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.elements.size();
        }

        public SecureSet(Subject subject, AuthPermission authPermission, Collection<? extends SST> collection) {
            this(authPermission);
            boolean z8 = collection.getClass().getClassLoader() == null;
            for (SST sst : collection) {
                verifyElement(sst);
                if (z8 || !this.elements.contains(sst)) {
                    this.elements.add(sst);
                }
            }
        }
    }

    public static Object doAs(Subject subject, PrivilegedExceptionAction privilegedExceptionAction) {
        checkPermission(_AS);
        return doAs_PrivilegedExceptionAction(subject, privilegedExceptionAction, AccessController.getContext());
    }

    public static Object doAsPrivileged(Subject subject, PrivilegedExceptionAction privilegedExceptionAction, AccessControlContext accessControlContext) {
        checkPermission(_AS_PRIVILEGED);
        if (accessControlContext == null) {
            return doAs_PrivilegedExceptionAction(subject, privilegedExceptionAction, new AccessControlContext(new ProtectionDomain[0]));
        }
        return doAs_PrivilegedExceptionAction(subject, privilegedExceptionAction, accessControlContext);
    }

    public Subject(boolean z8, Set<? extends Principal> set, Set<?> set2, Set<?> set3) {
        if (set != null && set2 != null && set3 != null) {
            this.principals = new SecureSet(this, _PRINCIPALS, set);
            this.publicCredentials = new SecureSet<>(this, _PUBLIC_CREDENTIALS, set2);
            this.privateCredentials = new SecureSet<>(this, _PRIVATE_CREDENTIALS, set3);
            this.readOnly = z8;
            return;
        }
        throw null;
    }
}
