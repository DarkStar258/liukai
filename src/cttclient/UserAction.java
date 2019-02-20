package cttclient;

public class UserAction {

    //准备动作
    public static final String REDY = "#REDY";
    //抽牌动作
    public static final String DRAW = "#DRAW";

    //判断用户输入是否合法
    public static boolean judgeMsg(String msg){
        if(msg.equals(REDY)
                || msg.startsWith(DRAW)){
            return true;
        }
        return false;
    }


}
