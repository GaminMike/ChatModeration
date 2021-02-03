package technium.chatmoderation;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber()
public class ServerChatEventHandler {
    private static final Set<Pattern> CENSORED_WORDS = ((Collection<String>) Config.defaultConfig.get()).stream().map(pattern -> "(?i)" + pattern)
            .map(Pattern::compile)
            .collect(Collectors.toSet());

    @SubscribeEvent
    public static void onChatMessageServer(ServerChatEvent event) {
        String finalMessage = event.getMessage();
        for (Pattern word : CENSORED_WORDS) {
            finalMessage = word.matcher(finalMessage).replaceAll("****");
                    //finalMessage.replaceAll(word + "(?i)", "****");
        }
        finalMessage = "<" + event.getUsername() + "> " + finalMessage;
        StringTextComponent finalStringTextComponent = new StringTextComponent(finalMessage);
        event.setComponent(finalStringTextComponent);
    }

}
