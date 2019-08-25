package com.auto;

import com.auto.structure.Base;
import com.auto.utils.FileUtil;

import java.util.HashMap;

public class MaxProjectCreator {

    public static void main(String[] args) {
        /* com.主项目名称*/
        Base.basePackage = "com.max";
        /* 主项目名称*/
        Base.baseArt = "max";
        Base.basePath = "C://code/"+ Base.baseArt+"/";
        HashMap<String,String[]> model = new HashMap();
        HashMap<String,String[]> serviceModel = new HashMap();
        /**提现服务 各种业务场景的限制
         * 1.有效投注和打码量 流水清零时间
         * 2.每日可提款总额增加值 和 可提款总次数增加值 有效期。
         * 有效投注  要求   当日次数 当日总计 最近提现时间。如果时间是当前，就获取当日次数和总计
         * */
        serviceModel.put("view",new String[]{"view", "activityConfig","activityRecord", "activityOrder"});
        serviceModel.put("activity",new String[]{"activityProperty","activityConfig","activityRecord", "activityOrder"});
        serviceModel.put("job",new String[]{"job"});
        serviceModel.put("proxy",new String[]{"proxy", "invited","proxyOrder"});
        /* 直线会员 团队成员 */
        serviceModel.put("user",new String[]{"user", "userAction","userBank"});
        /*notice 0 公告 1 轮播图  2 活动（注意与什么活动什么时间段内冲突） */
        serviceModel.put("system",new String[]{ "bank","bankView", "message", "notice","msg","contact","forbiden"});
        serviceModel.put("wallet",new String[]{"wallet", "walleteRcord", "walletOrder","walletSeries"});
        serviceModel.put("transfer",new String[]{"transfer", "transferRecord", "transferOrder", "transferLimit"});
        serviceModel.put("withdraw",new String[]{"withdraw", "withdrawRecord", "withdrawOrder", "withdrawLimit", "withdrawLimitRecord", "costLimit", "costLimitRecord"});
        serviceModel.put("recharge",new String[]{"recharge", "rechargeRecord", "rechargeOrder","rechargeMerchant", "rechargeChannel", "rechargeWay"});
        /*gameType : 0 真人视讯 1 电竞投注 2 体育投注 3 彩票投注 4 电子游戏 5 棋牌游戏 6 捕鱼游戏 7 其它*/
        serviceModel.put("game",new String[]{"game","gameMaintain","gameView","gameType","gameTypeView", "gameRecord","gameTransferSeries","gameTransferOrder","gameMerchant", "gameChannel","gameCost"});
        serviceModel.put("app",new String[]{"app", "appMaintain"});
        Base.initModel(serviceModel,"service");

        HashMap<String,String[]> webModel = new HashMap();
        webModel.put("forehead",new String[]{"user", "proxy","recharge", "wallet","game", "withdraw","activity","message", "notice"});
        webModel.put("backend",new String[]{"user",  "proxy","recharge", "wallet","game", "withdraw","activity","message", "notice"});
        Base.initModel(webModel,"web");


        model.putAll(serviceModel);
        model.putAll(webModel);
        String modulePomPath4 =  "/pom.xml";
        FileUtil.createFile(modulePomPath4);
        FileUtil.WriteStringToFile(modulePomPath4, Base.getProjectFtl(model));
    }


}
