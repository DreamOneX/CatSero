/*
 * CatSero v2
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
package moe.xmcn.catsero.listeners;

import moe.xmcn.catsero.Configuration;
import moe.xmcn.catsero.listeners.advancementforward.OnPlayerAdvancementDone;
import moe.xmcn.catsero.listeners.chatforward.OnAsyncPlayerChatToQQ;
import moe.xmcn.catsero.listeners.chatforward.OnGroupMessageToMC;
import moe.xmcn.catsero.listeners.chatforward.OnTrChatToQQ;
import moe.xmcn.catsero.listeners.deadthforward.OnPlayerDeath;
import moe.xmcn.catsero.listeners.getonlinelist.OnGroupMessageRequestList;
import moe.xmcn.catsero.listeners.gettps.OnGroupMessageRequestTPS;
import moe.xmcn.catsero.listeners.groupmemberleave.OnMemberLeave;
import moe.xmcn.catsero.listeners.joinquitforward.OnPlayerJoin;
import moe.xmcn.catsero.listeners.joinquitforward.OnPlayerQuit;
import moe.xmcn.catsero.listeners.newgroupmemberwelcome.OnMemberJoin;
import moe.xmcn.catsero.listeners.qwhitelist.RefuseNoWhiteList;
import moe.xmcn.catsero.listeners.qwhitelist.SelfApplication;
import moe.xmcn.catsero.listeners.qwhitelist.WhiteListEditor;
import moe.xmcn.catsero.utils.Envrionment;
import org.bukkit.plugin.PluginManager;

public interface ListenerRegister {

    PluginManager pm = Configuration.plugin.getServer().getPluginManager();

    static void register() {
        // 玩家加入/退出转发
        pm.registerEvents(new OnPlayerJoin(), Configuration.plugin);
        pm.registerEvents(new OnPlayerQuit(), Configuration.plugin);

        // 玩家死亡转发
        pm.registerEvents(new OnPlayerDeath(), Configuration.plugin);

        // TPS获取
        pm.registerEvents(new OnGroupMessageRequestTPS(), Configuration.plugin);

        // 进度转发
        pm.registerEvents(new OnPlayerAdvancementDone(), Configuration.plugin);

        // 新群员
        pm.registerEvents(new OnMemberJoin(), Configuration.plugin);

        // 玩家列表
        pm.registerEvents(new OnGroupMessageRequestList(), Configuration.plugin);

        // 聊天转发
        pm.registerEvents(new OnGroupMessageToMC(), Configuration.plugin);
        if (Envrionment.Depends.TrChat)
            pm.registerEvents(new OnTrChatToQQ(), Configuration.plugin);
        else
            pm.registerEvents(new OnAsyncPlayerChatToQQ(), Configuration.plugin);

        // QQ白名单
        pm.registerEvents(new RefuseNoWhiteList(), Configuration.plugin);
        pm.registerEvents(new WhiteListEditor(), Configuration.plugin);
        pm.registerEvents(new SelfApplication(), Configuration.plugin);

        // 群员离群
        pm.registerEvents(new OnMemberLeave(), Configuration.plugin);
    }

}
