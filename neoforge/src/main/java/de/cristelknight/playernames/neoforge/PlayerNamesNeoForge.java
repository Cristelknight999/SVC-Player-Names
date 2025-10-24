package de.cristelknight.playernames.neoforge;

import de.cristelknight.playernames.PlayerNames;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = PlayerNames.MOD_ID, dist = Dist.CLIENT)
public final class PlayerNamesNeoForge {
    public PlayerNamesNeoForge() {
        // Run our common setup.
        PlayerNames.init();
    }
}
