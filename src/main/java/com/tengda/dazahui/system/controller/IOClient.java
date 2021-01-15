package com.tengda.dazahui.system.controller;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @Author teswell
 * @Date 2021/1/7 17:09
 * @function
 */
public class IOClient {

    public static void main(String[] args) {
        new Thread(() ->{
            try {
                Socket socket  = new Socket("127.0.0.1",8888);
                while (true){
                    try {
                        socket.getOutputStream().write((new Date() + ": 现今文言文是中国古代的一种书面语言组成的文章，主要包括以先秦时期的口语为基础而形成的书面语言。春秋战国时期，用于记载文字的物品还未被发明，记载文字用的是竹简、丝绸等物。随着历史变迁，口语的演变，文言文和口语的差别逐渐扩大，“文言文”成了读书人的专用。\n" +
                                "文言文是相对现今新文化运动之后白话文来讲的，古代并无文言文这一说法。其特征是注重典故、骈骊对仗、音律工整，包含策、诗、词、曲、八股、骈文等多种文体。经过历代文人修饰越显浮华，唐代起大文学家韩愈等发起“古文运动”，主张回归通俗古文。现代书籍中的文言文，为了便于阅读理解，一般都会对其标注标点符号。").getBytes());
                        socket.getOutputStream().flush();
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
