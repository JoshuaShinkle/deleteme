package org.apache.harmony.javax.security.auth.login;

import java.io.IOException;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;
import org.apache.harmony.javax.security.auth.AuthPermission;
import org.apache.harmony.javax.security.auth.Subject;
import org.apache.harmony.javax.security.auth.callback.Callback;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.apache.harmony.javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.harmony.javax.security.auth.login.AppConfigurationEntry;
import org.apache.harmony.javax.security.auth.spi.LoginModule;

/* loaded from: classes.dex */
public class LoginContext {
    private static final String DEFAULT_CALLBACK_HANDLER_PROPERTY = "auth.login.defaultCallbackHandler";
    private static final int OPTIONAL = 0;
    private static final int REQUIRED = 1;
    private static final int REQUISITE = 2;
    private static final int SUFFICIENT = 3;
    private CallbackHandler callbackHandler;
    private ClassLoader contextClassLoader;
    private boolean loggedIn;
    private Module[] modules;
    private Map<String, ?> sharedState;
    private Subject subject;
    private AccessControlContext userContext;
    private boolean userProvidedConfig;
    private boolean userProvidedSubject;

    public class ContextedCallbackHandler implements CallbackHandler {
        private final CallbackHandler hiddenHandlerRef;

        public ContextedCallbackHandler(CallbackHandler callbackHandler) {
            this.hiddenHandlerRef = callbackHandler;
        }

        @Override // org.apache.harmony.javax.security.auth.callback.CallbackHandler
        public void handle(final Callback[] callbackArr) throws PrivilegedActionException, UnsupportedCallbackException, IOException {
            try {
                AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() { // from class: org.apache.harmony.javax.security.auth.login.LoginContext.ContextedCallbackHandler.1
                    @Override // java.security.PrivilegedExceptionAction
                    public Void run() {
                        ContextedCallbackHandler.this.hiddenHandlerRef.handle(callbackArr);
                        return null;
                    }
                }, LoginContext.this.userContext);
            } catch (PrivilegedActionException e9) {
                if (!(e9.getCause() instanceof UnsupportedCallbackException)) {
                    throw ((IOException) e9.getCause());
                }
                throw ((UnsupportedCallbackException) e9.getCause());
            }
        }
    }

    public final class Module {
        AppConfigurationEntry entry;
        int flag;
        Class<?> klass;
        LoginModule module;

        public Module(AppConfigurationEntry appConfigurationEntry) {
            this.entry = appConfigurationEntry;
            AppConfigurationEntry.LoginModuleControlFlag controlFlag = appConfigurationEntry.getControlFlag();
            if (controlFlag == AppConfigurationEntry.LoginModuleControlFlag.OPTIONAL) {
                this.flag = 0;
                return;
            }
            if (controlFlag == AppConfigurationEntry.LoginModuleControlFlag.REQUISITE) {
                this.flag = 2;
            } else if (controlFlag == AppConfigurationEntry.LoginModuleControlFlag.SUFFICIENT) {
                this.flag = 3;
            } else {
                this.flag = 1;
            }
        }

        public void create(Subject subject, CallbackHandler callbackHandler, Map<String, ?> map) throws LoginException {
            String loginModuleName = this.entry.getLoginModuleName();
            if (this.klass == null) {
                try {
                    this.klass = Class.forName(loginModuleName, false, LoginContext.this.contextClassLoader);
                } catch (ClassNotFoundException e9) {
                    throw ((LoginException) new LoginException("auth.39 " + loginModuleName).initCause(e9));
                }
            }
            if (this.module == null) {
                try {
                    LoginModule loginModule = (LoginModule) this.klass.newInstance();
                    this.module = loginModule;
                    loginModule.initialize(subject, callbackHandler, map, this.entry.getOptions());
                } catch (IllegalAccessException e10) {
                    throw ((LoginException) new LoginException("auth.3A " + loginModuleName).initCause(e10));
                } catch (InstantiationException e11) {
                    throw ((LoginException) new LoginException("auth.3A" + loginModuleName).initCause(e11));
                }
            }
        }

        public int getFlag() {
            return this.flag;
        }
    }

    public LoginContext(String str) throws PrivilegedActionException, LoginException {
        init(str, null, null, null);
    }

    private void init(String str, Subject subject, final CallbackHandler callbackHandler, Configuration configuration) throws PrivilegedActionException, LoginException {
        this.subject = subject;
        int i9 = 0;
        this.userProvidedSubject = subject != null;
        if (str == null) {
            throw new LoginException("auth.00");
        }
        if (configuration == null) {
            configuration = Configuration.getAccessibleConfiguration();
        } else {
            this.userProvidedConfig = true;
        }
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null && !this.userProvidedConfig) {
            securityManager.checkPermission(new AuthPermission("createLoginContext." + str));
        }
        AppConfigurationEntry[] appConfigurationEntry = configuration.getAppConfigurationEntry(str);
        if (appConfigurationEntry == null) {
            if (securityManager != null && !this.userProvidedConfig) {
                securityManager.checkPermission(new AuthPermission("createLoginContext.other"));
            }
            appConfigurationEntry = configuration.getAppConfigurationEntry("other");
            if (appConfigurationEntry == null) {
                throw new LoginException("auth.35 " + str);
            }
        }
        this.modules = new Module[appConfigurationEntry.length];
        while (true) {
            Module[] moduleArr = this.modules;
            if (i9 >= moduleArr.length) {
                try {
                    break;
                } catch (PrivilegedActionException e9) {
                    throw ((LoginException) new LoginException("auth.36").initCause(e9.getCause()));
                }
            } else {
                moduleArr[i9] = new Module(appConfigurationEntry[i9]);
                i9++;
            }
        }
        AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() { // from class: org.apache.harmony.javax.security.auth.login.LoginContext.1
            @Override // java.security.PrivilegedExceptionAction
            public Void run() throws ClassNotFoundException {
                LoginContext.this.contextClassLoader = Thread.currentThread().getContextClassLoader();
                if (LoginContext.this.contextClassLoader == null) {
                    LoginContext.this.contextClassLoader = ClassLoader.getSystemClassLoader();
                }
                CallbackHandler callbackHandler2 = callbackHandler;
                if (callbackHandler2 == null) {
                    String property = Security.getProperty(LoginContext.DEFAULT_CALLBACK_HANDLER_PROPERTY);
                    if (property == null || property.length() == 0) {
                        return null;
                    }
                    Class<?> cls = Class.forName(property, true, LoginContext.this.contextClassLoader);
                    LoginContext.this.callbackHandler = (CallbackHandler) cls.newInstance();
                } else {
                    LoginContext.this.callbackHandler = callbackHandler2;
                }
                return null;
            }
        });
        if (this.userProvidedConfig) {
            this.userContext = AccessController.getContext();
        } else if (this.callbackHandler != null) {
            this.userContext = AccessController.getContext();
            this.callbackHandler = new ContextedCallbackHandler(this.callbackHandler);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:39:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00e1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void loginImpl() throws LoginException {
        boolean z8;
        boolean z9;
        if (this.subject == null) {
            this.subject = new Subject();
        }
        if (this.sharedState == null) {
            this.sharedState = new HashMap();
        }
        int[] iArr = new int[4];
        int[] iArr2 = new int[4];
        Module[] moduleArr = this.modules;
        int length = moduleArr.length;
        Throwable cause = null;
        int i9 = 0;
        while (true) {
            if (i9 >= length) {
                break;
            }
            Module module = moduleArr[i9];
            try {
                module.create(this.subject, this.callbackHandler, this.sharedState);
                if (module.module.login()) {
                    int flag = module.getFlag();
                    iArr2[flag] = iArr2[flag] + 1;
                    int flag2 = module.getFlag();
                    iArr[flag2] = iArr[flag2] + 1;
                    if (module.getFlag() == 3) {
                        break;
                    }
                } else {
                    continue;
                }
            } catch (Throwable th) {
                if (cause == null) {
                    cause = th;
                }
                if (module.klass == null) {
                    iArr2[1] = iArr2[1] + 1;
                    break;
                }
                int flag3 = module.getFlag();
                iArr2[flag3] = iArr2[flag3] + 1;
                if (module.getFlag() == 2) {
                    break;
                }
            }
            i9++;
        }
        int i10 = iArr[1];
        int i11 = iArr2[1];
        if (i10 == i11) {
            int i12 = iArr[2];
            int i13 = iArr2[2];
            z8 = i12 != i13 || (i11 == 0 && i13 == 0 && iArr[0] == 0 && iArr[3] == 0);
        }
        int[] iArr3 = new int[4];
        iArr2[3] = 0;
        iArr2[2] = 0;
        iArr2[1] = 0;
        iArr2[0] = 0;
        if (!z8) {
            for (Module module2 : this.modules) {
                if (module2.klass != null) {
                    int flag4 = module2.getFlag();
                    iArr2[flag4] = iArr2[flag4] + 1;
                    try {
                        module2.module.commit();
                        int flag5 = module2.getFlag();
                        iArr3[flag5] = iArr3[flag5] + 1;
                    } catch (Throwable th2) {
                        if (cause == null) {
                            cause = th2;
                        }
                    }
                }
            }
        }
        int i14 = iArr3[1];
        int i15 = iArr2[1];
        if (i14 == i15) {
            int i16 = iArr3[2];
            int i17 = iArr2[2];
            z9 = i16 != i17 || (i15 == 0 && i17 == 0 && iArr3[0] == 0 && iArr3[3] == 0);
        }
        if (!z9) {
            this.loggedIn = true;
            return;
        }
        for (Module module3 : this.modules) {
            try {
                module3.module.abort();
            } catch (Throwable th3) {
                if (cause == null) {
                    cause = th3;
                }
            }
        }
        if ((cause instanceof PrivilegedActionException) && cause.getCause() != null) {
            cause = cause.getCause();
        }
        if (!(cause instanceof LoginException)) {
            throw ((LoginException) new LoginException("auth.37").initCause(cause));
        }
        throw ((LoginException) cause);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logoutImpl() throws LoginException {
        if (this.subject == null) {
            throw new LoginException("auth.38");
        }
        this.loggedIn = false;
        Throwable cause = null;
        int i9 = 0;
        for (Module module : this.modules) {
            try {
                module.module.logout();
                i9++;
            } catch (Throwable th) {
                if (cause == null) {
                    cause = th;
                }
            }
        }
        if (cause != null || i9 == 0) {
            if ((cause instanceof PrivilegedActionException) && cause.getCause() != null) {
                cause = cause.getCause();
            }
            if (!(cause instanceof LoginException)) {
                throw ((LoginException) new LoginException("auth.37").initCause(cause));
            }
            throw ((LoginException) cause);
        }
    }

    public Subject getSubject() {
        if (this.userProvidedSubject || this.loggedIn) {
            return this.subject;
        }
        return null;
    }

    public void login() throws PrivilegedActionException, LoginException {
        PrivilegedExceptionAction<Void> privilegedExceptionAction = new PrivilegedExceptionAction<Void>() { // from class: org.apache.harmony.javax.security.auth.login.LoginContext.2
            @Override // java.security.PrivilegedExceptionAction
            public Void run() throws LoginException {
                LoginContext.this.loginImpl();
                return null;
            }
        };
        try {
            if (this.userProvidedConfig) {
                AccessController.doPrivileged(privilegedExceptionAction, this.userContext);
            } else {
                AccessController.doPrivileged(privilegedExceptionAction);
            }
        } catch (PrivilegedActionException e9) {
            throw ((LoginException) e9.getException());
        }
    }

    public void logout() throws PrivilegedActionException, LoginException {
        PrivilegedExceptionAction<Void> privilegedExceptionAction = new PrivilegedExceptionAction<Void>() { // from class: org.apache.harmony.javax.security.auth.login.LoginContext.3
            @Override // java.security.PrivilegedExceptionAction
            public Void run() throws LoginException {
                LoginContext.this.logoutImpl();
                return null;
            }
        };
        try {
            if (this.userProvidedConfig) {
                AccessController.doPrivileged(privilegedExceptionAction, this.userContext);
            } else {
                AccessController.doPrivileged(privilegedExceptionAction);
            }
        } catch (PrivilegedActionException e9) {
            throw ((LoginException) e9.getException());
        }
    }

    public LoginContext(String str, CallbackHandler callbackHandler) throws PrivilegedActionException, LoginException {
        if (callbackHandler != null) {
            init(str, null, callbackHandler, null);
            return;
        }
        throw new LoginException("auth.34");
    }

    public LoginContext(String str, Subject subject) throws PrivilegedActionException, LoginException {
        if (subject != null) {
            init(str, subject, null, null);
            return;
        }
        throw new LoginException("auth.03");
    }

    public LoginContext(String str, Subject subject, CallbackHandler callbackHandler) throws PrivilegedActionException, LoginException {
        if (subject == null) {
            throw new LoginException("auth.03");
        }
        if (callbackHandler != null) {
            init(str, subject, callbackHandler, null);
            return;
        }
        throw new LoginException("auth.34");
    }

    public LoginContext(String str, Subject subject, CallbackHandler callbackHandler, Configuration configuration) throws PrivilegedActionException, LoginException {
        init(str, subject, callbackHandler, configuration);
    }
}
