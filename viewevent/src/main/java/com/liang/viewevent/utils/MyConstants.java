package com.liang.viewevent.utils;

import android.os.Environment;

/**
 * Created by liliang on 2017/4/10.
 */

public class MyConstants {
//    public static final String CHAPTER_2_PATH = Environment.getExternalStorageDirectory().getPath()
//            + "/singwhatiwanna/chapter_2/";
//
//    public static final String CACHE_FILE_PATH = CHAPTER_2_PATH + "usercache";

    public static final String CHAPTER_2_PATH = Environment
            .getExternalStorageDirectory().getPath()
            + "/singwhatiwanna/chapter_2/";

    public static final String CACHE_FILE_PATH = CHAPTER_2_PATH + "usercache";

    public static final int MSG_FROM_CLIENT = 0;
    public static final int MSG_FROM_SERVICE = 1;
}
