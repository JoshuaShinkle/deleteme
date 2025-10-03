package org.apache.harmony.javax.security.auth.login;

import org.apache.harmony.javax.security.auth.AuthPermission;

/* loaded from: classes.dex */
public abstract class Configuration {
    private static final String LOGIN_CONFIGURATION_PROVIDER = "login.configuration.provider";
    private static Configuration configuration;
    private static final AuthPermission GET_LOGIN_CONFIGURATION = new AuthPermission("getLoginConfiguration");
    private static final AuthPermission SET_LOGIN_CONFIGURATION = new AuthPermission("setLoginConfiguration");

    public static Configuration getAccessibleConfiguration() {
        Configuration configuration2;
        Configuration configuration3 = configuration;
        if (configuration3 != null) {
            return configuration3;
        }
        synchronized (Configuration.class) {
            if (configuration == null) {
                configuration = getDefaultProvider();
            }
            configuration2 = configuration;
        }
        return configuration2;
    }

    public static Configuration getConfiguration() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(GET_LOGIN_CONFIGURATION);
        }
        return getAccessibleConfiguration();
    }

    private static final Configuration getDefaultProvider() {
        return new Configuration() { // from class: org.apache.harmony.javax.security.auth.login.Configuration.1
            @Override // org.apache.harmony.javax.security.auth.login.Configuration
            public AppConfigurationEntry[] getAppConfigurationEntry(String str) {
                return new AppConfigurationEntry[0];
            }

            @Override // org.apache.harmony.javax.security.auth.login.Configuration
            public void refresh() {
            }
        };
    }

    public static void setConfiguration(Configuration configuration2) {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(SET_LOGIN_CONFIGURATION);
        }
        configuration = configuration2;
    }

    public abstract AppConfigurationEntry[] getAppConfigurationEntry(String str);

    public abstract void refresh();
}
