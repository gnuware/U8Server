package com.u8.server.utils;

import com.u8.server.data.UMsdkOrder;
import com.u8.server.data.UOrder;
import com.u8.server.data.UUser;
import com.u8.server.log.Log;

public class UGenerator {

    /***
     * 生成appkey
     * @param appID
     * @param createTime
     * @return
     */
    public static String generateAppKey(int appID, long createTime){

        String txt = appID + "" + createTime;

        return EncryptUtils.md5(txt);
    }

    /***
     * 生成用于生成登录认证使用的Token
     * @param user
     * @return
     */
    public static String generateToken(UUser user, String appKey){
        String txt = user.getId() + "-" + user.getChannelUserID() + "-" + appKey + "-" + user.getLastLoginTime().getTime();

        Log.d("The txt to generate token is " + txt);
        return EncryptUtils.md5(txt);
    }

    /***
     * 生成sign
     * @param order
     * @return
     */
    public static String generateSign(UOrder order){

        StringBuilder sb = new StringBuilder();

        sb.append("appID=").append(order.getAppID())
                .append("channelID=").append(order.getChannelID())
                .append("currency").append(order.getCurrency())
                .append("extension").append(order.getExtension())
                .append("money=").append(order.getMoney())
                .append("orderID=").append(order.getOrderID())
                .append("userID=").append(order.getUserID());

        return EncryptUtils.md5(sb.toString());
    }

    /***
     * 生成应用宝sign
     * @param order
     * @return
     */
    public static String generateMsdkSign(UMsdkOrder order){

        StringBuilder sb = new StringBuilder();

        sb.append("appID=").append(order.getAppID())
                .append("channelID=").append(order.getChannelID())
                .append("coinNum=").append(order.getCoinNum())
                .append("firstPay=").append(order.getFirstPay())
                .append("allMoney=").append(order.getAllMoney())
                .append("userID=").append(order.getUserID());

        return EncryptUtils.md5(sb.toString());
    }
}
