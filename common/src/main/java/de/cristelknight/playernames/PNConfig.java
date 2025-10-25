package de.cristelknight.playernames;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.cristelknight.cristellib.config.simple.ConfigRegistry;
import de.cristelknight.cristellib.config.simple.ConfigSettings;
import de.cristelknight.cristellib.config.simple.custom.AlphaColorField;


public record PNConfig(
        boolean alwaysShow,
        double scale,
        double xOffset,
        double yOffset,
        boolean shadowText,
        AlphaColorField color
) {

    public static final Codec<PNConfig> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    Codec.BOOL.fieldOf("alwaysShow").forGetter(PNConfig::alwaysShow),
                    Codec.DOUBLE.fieldOf("scale").forGetter(PNConfig::scale),
                    Codec.DOUBLE.fieldOf("xOffset").forGetter(PNConfig::xOffset),
                    Codec.DOUBLE.fieldOf("yOffset").forGetter(PNConfig::yOffset),
                    Codec.BOOL.fieldOf("shadowText").forGetter(PNConfig::shadowText),
                    de.cristelknight.cristellib.config.simple.custom.AlphaColorField.CODEC.fieldOf("color").forGetter(PNConfig::color)
            ).apply(builder, PNConfig::new)
    );

    public static final ConfigSettings<PNConfig> SETTINGS = new ConfigSettings<>() {
        @Override
        public String getSubPath() {
            return PlayerNames.MOD_ID + "/config";
        }

        @Override
        public Codec<PNConfig> getCodec() {
            return CODEC;
        }

        @Override
        public PNConfig getDefault() {
            return new PNConfig(
                    false,
                    0.8d,
                    0d,
                    0d,
                    true,
                    new AlphaColorField(255, 255, 255, 255)
            );
        }
    };

    public static void register() {
        ConfigRegistry.registerWithScreen(PNConfig.class, SETTINGS,
                PlayerNames.MOD_ID, "Simple Voice Chat Player Names");
    }
}
