package com.mayi.data.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * @author: liwenfei.
 * data: 2018/7/20 11:16.
 */
public class PathUtil {
    private static PathUtil _instance = new PathUtil();

    public static PathUtil getInstance(){
        return _instance;
    }
    private String rootPath;
//    private String articleKey = "article";
//    private String articlePath;
    private PathUtil(){

    }

    public void init(Context context){
        rootPath = context.getExternalFilesDir("article").getPath();

//        articlePath = rootPath + File.separator + articleKey;
//        File dir = new File(articlePath);
//        if(!dir.exists()){
//            dir.mkdir();
//        }
    }

    public String getArticlePath(){
        return rootPath;
    }
}
