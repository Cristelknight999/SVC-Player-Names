package de.cristelknight.playernames;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PlayerNames {
    public static final String MOD_ID = "svc_player_names";

    public static final Logger LOG = LogManager.getLogger(MOD_ID);

    public static void init() {
        PNConfig.register();
    }
}
