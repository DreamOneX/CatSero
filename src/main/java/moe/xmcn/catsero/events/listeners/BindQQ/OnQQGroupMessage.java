/*
 * 此文件为 Minecraft服务器 插件 CatSero 的一部分
 * 请在符合开源许可证的情况下修改/发布
 * This file is part of the Minecraft Server plugin CatSero
 * Please modify/publish subject to open source license
 *
 * Copyright 2022 XiaMoHuaHuo_CN.
 *
 * GitHub: https://github.com/XiaMoHuaHuo-CN/CatSero
 * License: GNU Affero General Public License v3.0
 * https://github.com/XiaMoHuaHuo-CN/CatSero/blob/main/LICENSE
 *
 * Permissions of this strongest copyleft license are
 * conditioned on making available complete source code
 * of licensed works and modifications, which include
 * larger works using a licensed work, under the same
 * license. Copyright and license notices must be preserved.
 * Contributors provide an express grant of patent rights.
 * When a modified version is used to provide a service over
 * a network, the complete source code of the modified
 * version must be made available.
 */
package moe.xmcn.catsero.events.listeners.BindQQ;

import me.dreamvoid.miraimc.api.MiraiMC;
import me.dreamvoid.miraimc.bukkit.event.message.passive.MiraiGroupMessageEvent;
import moe.xmcn.catsero.utils.Config;
import moe.xmcn.catsero.utils.Players;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;

public class OnQQGroupMessage implements Listener {

    @EventHandler
    public void onMiraiGroupMessageEvent(MiraiGroupMessageEvent event) {
        if (Config.UsesConfig.getBoolean("bind-qq.enabled")) {
            String message = event.getMessage();
            String[] args = message.split(" ");
            if (Objects.equals(args[0], "catsero") && Objects.equals(args[1], "bind")) {
                if (event.getSenderID() == Config.QQ_OP) {
                    //有OP权限
                    if (args.length == 5 && Objects.equals(args[2], "add")) {
                        //添加绑定
                        if (!Objects.requireNonNull(MiraiMC.getBind(Long.parseLong(args[3]))).toString().equals("") || MiraiMC.getBind(Players.getPlayer(args[3]).getUniqueId()) != 0L) {
                            Config.sendMiraiGroupMessage(Config.Prefix_QQ + Config.getMsgByMsID("qq.bind-qq.already-bind"));
                        } else {
                            MiraiMC.addBind(Players.getPlayer(args[4]).getUniqueId(), Long.parseLong(args[3]));
                            Config.sendMiraiGroupMessage(Config.Prefix_QQ + Config.getMsgByMsID("qq.bind-qq.add-success"));
                        }
                    } else if (args.length == 4 && Objects.equals(args[2], "remove")) {
                        //移除绑定
                        if (!Objects.requireNonNull(MiraiMC.getBind(Long.parseLong(args[3]))).toString().equals("") || MiraiMC.getBind(Players.getPlayer(args[3]).getUniqueId()) != 0L) {
                            MiraiMC.removeBind(Long.parseLong(args[3]));
                            MiraiMC.removeBind(Players.getPlayer(args[3]).getUniqueId());
                            Config.sendMiraiGroupMessage(Config.Prefix_QQ + Config.getMsgByMsID("qq.bind-qq.remove-success"));

                        } else {
                            Config.sendMiraiGroupMessage(Config.Prefix_QQ + Config.getMsgByMsID("qq.bind-qq.no-bind-found"));
                        }
                    }
                } else {
                    //无OP权限
                    Config.sendMiraiGroupMessage(Config.Prefix_QQ + Config.getMsgByMsID("qq.no-permission"));
                }
            }
        }
    }

}
