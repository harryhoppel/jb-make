package com.jetbrains.jbmake.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * @author daywalker
 * @since 29/06/15.
 */
public class PreferencesBasedConfiguration implements Configuration {
    private static final Logger LOG = LoggerFactory.getLogger(PreferencesBasedConfiguration.class);

    private final Configuration defaultConfiguration;

    private final Preferences preferences;

    public PreferencesBasedConfiguration(Preferences preferences, Configuration defaultConfiguration) {
        this.preferences = preferences;
        this.defaultConfiguration = defaultConfiguration;
    }

    public String getValue(String key) {
        return preferences.get(key, defaultConfiguration.getValue(key));
    }

    public void putValue(String key, String value) {
        if (value == null) {
            preferences.remove(key);
        } else {
            preferences.put(key, value);
        }
    }

    @Override
    public void save() {
        try {
            preferences.flush();
        } catch (BackingStoreException e) {
            LOG.error("Can't save configuration", e);
        }
    }
}
