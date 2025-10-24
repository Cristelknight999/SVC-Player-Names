package de.cristelknight.playernames.fabric;

import de.cristelknight.playernames.PlayerNames;
import net.fabricmc.api.ClientModInitializer;

public final class PlayerNamesFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PlayerNames.init();
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
    }
}
