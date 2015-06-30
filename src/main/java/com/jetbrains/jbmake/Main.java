package com.jetbrains.jbmake;

import com.jetbrains.jbmake.core.config.Configuration;
import com.jetbrains.jbmake.core.config.PreferencesBasedConfiguration;
import com.jetbrains.jbmake.core.config.XmlConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;

import java.util.prefs.Preferences;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Application application = new MakefileEditor();
        try {
            Preferences preferences = Preferences.userNodeForPackage(MakefileEditor.class);
            Configuration defaultConfiguration = XmlConfiguration.load(MakefileEditor.class.getClassLoader().getResourceAsStream("default-configuration.xml"));
            Configuration configuration = new PreferencesBasedConfiguration(preferences, defaultConfiguration);
            application.start(configuration);
        } catch (Throwable ex) {
            LOG.error(MarkerFactory.getMarker("FATAL"), "Internal error", ex);
            application.exit(ExitCode.GENERIC_ERROR);
        }
    }
}
