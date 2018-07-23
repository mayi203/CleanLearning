package com.mayi.data.utils;

import android.content.Context;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author: liwenfei.
 * data: 2018/7/20 11:16.
 */
@Singleton
public class PathUtil {
    private String rootPath;
    private String cacheKey = "cache";
    private String cachePath;
    private String collectionKey = "collection";
    private String collectionPath;

    @Inject
    PathUtil(Context context){
        rootPath = context.getExternalFilesDir("article").getPath();
        cachePath = rootPath + File.separator + cacheKey;
        File cacheDir = new File(cachePath);
        if(!cacheDir.exists()){
            cacheDir.mkdir();
        }
        collectionPath = rootPath + File.separator + collectionKey;
        File collectsDir = new File(collectionPath);
        if(!collectsDir.exists()){
            collectsDir.mkdir();
        }
    }

    public String getCachePath(){
        return rootPath + File.separator + cacheKey + File.separator;
    }

    public String getCollectionPath(){
        return rootPath + File.separator + collectionKey + File.separator;
    }
}
