package moe.xmcn.catsero.events.listeners.WeatherInfo;

import me.dreamvoid.miraimc.api.MiraiBot;
import me.dreamvoid.miraimc.bukkit.event.message.passive.MiraiGroupMessageEvent;
import moe.xmcn.catsero.events.gists.WeatherUtils;
import moe.xmcn.catsero.utils.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class OnQQGroupMessage implements Listener {

    @EventHandler
    public void onMiraiGroupMessageEvent(MiraiGroupMessageEvent event) {
        if (Config.UsesConfig.getBoolean("weatherinfo.enabled") && event.getGroupID() == Config.Use_Group && event.getBotID() == Config.Use_Bot) {
            isEnabled(event);
        }
    }

    private void isEnabled(MiraiGroupMessageEvent event) {
        String msg = event.getMessage();
        String[] args = msg.split(" ");
        if (args[0].equalsIgnoreCase("catsero") && args[1].equalsIgnoreCase("weather")) {
            if (Config.UsesConfig.getBoolean("weatherinfo.op-only")) {
                if (event.getSenderID() == Config.QQ_OP) {
                    if (args.length == 3 && event.getGroupID() == Config.Use_Group) {
                        try {
                            try {
                                MiraiBot.getBot(Config.Use_Bot).getGroup(Config.Use_Group).sendMessageMirai(Config.Prefix_QQ + Config.getMsgByMsID("qq.weatherinfo.doing"));
                            } catch (NoSuchElementException nse) {
                                Config.plugin.getLogger().warning(Config.getMsgByMsID("general.send-message-qq.error").replace("%error%", nse + Arrays.toString(nse.getStackTrace())));
                            }
                            String[] resvi = WeatherUtils.getWeather(args[2]);
                            String message = Config.getMsgByMsID("qq.weatherinfo.success")
                                    .replace("%type%", resvi[4])
                                    .replace("%temperature%", resvi[1])
                                    .replace("%wind%", resvi[2])
                                    .replace("%wind_direction%", resvi[3])
                                    .replace("%date%", resvi[0]);
                            try {
                                MiraiBot.getBot(Config.Use_Bot).getGroup(Config.Use_Group).sendMessageMirai(message);
                            } catch (NoSuchElementException nse) {
                                Config.plugin.getLogger().warning(Config.getMsgByMsID("general.send-message-qq.error").replace("%error%", nse + Arrays.toString(nse.getStackTrace())));
                            }
                        } catch (UnsupportedEncodingException uee) {
                            try {
                                MiraiBot.getBot(Config.Use_Bot).getGroup(Config.Use_Group).sendMessageMirai(Config.Prefix_QQ + Config.getMsgByMsID("qq.weatherinfo.error"));
                            } catch (NoSuchElementException nse) {
                                Config.plugin.getLogger().warning(Config.getMsgByMsID("general.send-message-qq.error").replace("%error%", nse + Arrays.toString(nse.getStackTrace())));
                            }
                        }
                    } else {
                        try {
                            MiraiBot.getBot(Config.Use_Bot).getGroup(Config.Use_Group).sendMessageMirai(Config.Prefix_QQ + Config.getMsgByMsID("qq.no-permission"));
                        } catch (NoSuchElementException nse) {
                            Config.plugin.getLogger().warning(Config.getMsgByMsID("general.send-message-qq.error").replace("%error%", nse + Arrays.toString(nse.getStackTrace())));
                        }
                    }
                } else {
                    try {
                        MiraiBot.getBot(Config.Use_Bot).getGroup(Config.Use_Group).sendMessageMirai(Config.Prefix_QQ + Config.getMsgByMsID("qq.weatherinfo.null-city"));
                    } catch (NoSuchElementException nse) {
                        Config.plugin.getLogger().warning(Config.getMsgByMsID("general.send-message-qq.error").replace("%error%", nse + Arrays.toString(nse.getStackTrace())));
                    }
                }
            } else {
                if (args.length == 3 && event.getGroupID() == Config.Use_Group) {
                    try {
                        try {
                            MiraiBot.getBot(Config.Use_Bot).getGroup(Config.Use_Group).sendMessageMirai(Config.Prefix_QQ + Config.getMsgByMsID("qq.weatherinfo.doing"));
                        } catch (NoSuchElementException nse) {
                            Config.plugin.getLogger().warning(Config.getMsgByMsID("general.send-message-qq.error").replace("%error%", nse + Arrays.toString(nse.getStackTrace())));
                        }
                        String[] resvi = WeatherUtils.getWeather(args[2]);
                        String message = Config.getMsgByMsID("qq.weatherinfo.success")
                                .replace("%type%", resvi[4])
                                .replace("%temperature%", resvi[1])
                                .replace("%wind%", resvi[2])
                                .replace("%wind_direction%", resvi[3])
                                .replace("%date%", resvi[0]);
                        try {
                            MiraiBot.getBot(Config.Use_Bot).getGroup(Config.Use_Group).sendMessageMirai(message);
                        } catch (NoSuchElementException nse) {
                            Config.plugin.getLogger().warning(Config.getMsgByMsID("general.send-message-qq.error").replace("%error%", nse + Arrays.toString(nse.getStackTrace())));
                        }
                    } catch (UnsupportedEncodingException uee) {
                        try {
                            MiraiBot.getBot(Config.Use_Bot).getGroup(Config.Use_Group).sendMessageMirai(Config.Prefix_QQ + Config.getMsgByMsID("qq.weatherinfo.error"));
                        } catch (NoSuchElementException nse) {
                            Config.plugin.getLogger().warning(Config.getMsgByMsID("general.send-message-qq.error").replace("%error%", nse + Arrays.toString(nse.getStackTrace())));
                        }
                    }
                } else {
                    try {
                        MiraiBot.getBot(Config.Use_Bot).getGroup(Config.Use_Group).sendMessageMirai(Config.Prefix_QQ + Config.getMsgByMsID("qq.no-permission"));
                    } catch (NoSuchElementException nse) {
                        Config.plugin.getLogger().warning(Config.getMsgByMsID("general.send-message-qq.error").replace("%error%", nse + Arrays.toString(nse.getStackTrace())));
                    }
                }
            }
        }
    }

}
