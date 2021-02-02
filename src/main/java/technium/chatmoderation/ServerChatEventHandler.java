package technium.chatmoderation;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class ServerChatEventHandler {

    @SubscribeEvent
    public static void onChatMessageServer(ServerChatEvent event) {
        ChatModeration.LOGGER.info(event.getComponent().toString());
        if (event.getMessage().contains("badword")) {
            ITextComponent replacementText = new StringTextComponent("<" + event.getUsername() + "> " + event.getMessage().replace("badword", "****"));
            event.setComponent(replacementText);
        }
    }

}
