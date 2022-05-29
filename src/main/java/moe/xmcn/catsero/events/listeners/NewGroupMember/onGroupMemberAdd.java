package moe.xmcn.catsero.events.listeners.NewGroupMember;

import me.dreamvoid.miraimc.api.MiraiBot;
import me.dreamvoid.miraimc.bukkit.event.MiraiGroupMemberJoinEvent;
import moe.xmcn.catsero.utils.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class onGroupMemberAdd implements Listener {


    @EventHandler
    public void OnGroupMemberAdd(MiraiGroupMemberJoinEvent event) {
        if (Config.INSTANCE.getUsesConfig().getBoolean("new-group-member-message.enabled") && event.getGroupID() == Config.INSTANCE.getUse_Group() && event.getBotID() == Config.INSTANCE.getUse_Bot()) {
            long code = event.getNewMemberID();
            String message = Config.INSTANCE.getUsesConfig().getString("new-group-member-message.format");
            message = message
                    .replace("%code%", String.valueOf(code))
                    .replace("%at%", "[mirai:at:" + code + "]");
            try {
                MiraiBot.getBot(Config.INSTANCE.getUse_Bot()).getGroup(Config.INSTANCE.getUse_Group()).sendMessageMirai(message);
            } catch (NoSuchElementException nse) {
                System.out.println("发送消息时发生异常:\n" + nse + Arrays.toString(nse.getStackTrace()));
            }
        }

    }
}