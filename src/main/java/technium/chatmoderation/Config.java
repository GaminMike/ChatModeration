package technium.chatmoderation;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Config {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CONFIG;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> defaultConfig;

    static {
        BUILDER.push("Moderation Options");
        defaultConf(BUILDER);
        BUILDER.pop();
        CONFIG = BUILDER.build();
    }

    public static void loadConfig(ForgeConfigSpec config, String path) {
        CommentedFileConfig file = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig(file);
    }

    public static void defaultConf(ForgeConfigSpec.Builder config) {
        defaultConfig = config.comment("List of words to censor:").defineList("Censored words/phrases: ", Collections.emptyList(), s -> s instanceof String);
    }

}
