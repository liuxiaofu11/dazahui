package com.tengda.dazahui.system.suanfa;

/**
 * @Author teswell
 * @Date 2020/12/9 14:48
 * @function
 */
public class SuShu {
    //【程序2】 题目：判断101-200之间有多少个素数，并输出所有素数。
    //
    //1.程序分析：判断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除，
    //
    //则表明此数不是素数，反之是素数。
    public static void main(String[] args) {
        int k = 0;
        math mymath = new math();
        for (int i = 2; i < 200; i++) {
            if (mymath.iszhishu(i) == true) {
                System.out.println(i);
            }
        }

    }
}

class math {

    public boolean iszhishu(int x) {
        for (int i = 2; i <= x / 2; i++) {
            if (x % 2 == 0) {
                return false;
            }
        }
        return true;
    }
}
