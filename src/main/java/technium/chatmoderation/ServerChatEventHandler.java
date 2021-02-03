package technium.chatmoderation;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;
import java.util.regex.Pattern;

@Mod.EventBusSubscriber()
public class ServerChatEventHandler {
    public static List<String> CENSORED_WORDS = (List<String>) Config.defaultConfig.get();
    public static Set<String> CENSORED_WORDS_SET = new HashSet<>(CENSORED_WORDS);

    @SubscribeEvent
    public static void onChatMessageServer(ServerChatEvent event) {
        String finalMessage = event.getMessage();
        for (String word : CENSORED_WORDS) {
            finalMessage = finalMessage.replaceAll(word + "(?i)", "****");
        }
        finalMessage = "<" + event.getUsername() + "> " + finalMessage;
        StringTextComponent finalStringTextComponent = new StringTextComponent(finalMessage);
        event.setComponent(finalStringTextComponent);
    }

}
