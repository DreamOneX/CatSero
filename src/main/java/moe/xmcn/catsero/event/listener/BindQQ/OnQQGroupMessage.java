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
package moe.xmcn.catsero.event.listener.BindQQ;

import me.dreamvoid.miraimc.api.MiraiMC;
import me.dreamvoid.miraimc.bukkit.event.message.passive.MiraiGroupMessageEvent;
import moe.xmcn.catsero.util.Config;
import moe.xmcn.catsero.util.Players;
import moe.xmcn.catsero.util.QCommandParser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;

public class OnQQGroupMessage implements Listener {

    @EventHandler
    public void onMiraiGroupMessageEvent(MiraiGroupMessageEvent event) {
        String[] args = QCommandParser.getParser.parse(event.getMessage());
        if (args != null) {
            if (Config.UsesConfig.getBoolean("bind-qq.enabled") && Objects.equals(args[0], "bind")) {
                if (event.getSenderID() == Config.QQ_OP) {
                    //有OP权限
                    switch (args[1]) {
                        case "add":
                            if (args.length == 4) {
                                //添加绑定
                                if (!Objects.requireNonNull(MiraiMC.getBind(Long.parseLong(args[2]))).toString().equals("") || MiraiMC.getBind(Players.getPlayer(args[2]).getUniqueId()) != 0L) {
                                    Config.sendMiraiGroupMessage(Config.Prefix_QQ + Config.getMsgByMsID("qq.bind-qq.already-bind"));
                                } else {
                                    MiraiMC.addBind(Players.getPlayer(args[3]).getUniqueId(), Long.parseLong(args[2]));
                                    Config.sendMiraiGroupMessage(Config.Prefix_QQ + Config.getMsgByMsID("qq.bind-qq.add-success"));
                                }
                            }
                            break;
                        case "remove":
                            if (args.length == 3) {
                                //移除绑定
                                if (!Objects.requireNonNull(MiraiMC.getBind(Long.parseLong(args[2]))).toString().equals("") || MiraiMC.getBind(Players.getPlayer(args[2]).getUniqueId()) != 0L) {
                                    MiraiMC.removeBind(Long.parseLong(args[2]));
                                    MiraiMC.removeBind(Players.getPlayer(args[2]).getUniqueId());
                                    Config.sendMiraiGroupMessage(Config.Prefix_QQ + Config.getMsgByMsID("qq.bind-qq.remove-success"));
                                } else {
                                    Config.sendMiraiGroupMessage(Config.Prefix_QQ + Config.getMsgByMsID("qq.bind-qq.no-bind-found"));
                                }
                            }
                            break;
                        default:
                            Config.sendMiraiGroupMessage(Config.Prefix_QQ + Config.getMsgByMsID("qq.invalid-options"));
                    }
                } else {
                    //无OP权限
                    Config.sendMiraiGroupMessage(Config.Prefix_QQ + Config.getMsgByMsID("qq.no-permission"));
                }
            }
        }
    }

}