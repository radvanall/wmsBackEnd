package com.warehousemanagement.wms.utils;

import java.io.File;
import java.util.Arrays;

public class ImageHandler {
    private final String[] imgFormats = {"png", "jpg", "jpeg", "web"};
    public String setImgName(String imgName,String folder){
        String[] imgParts = imgName.split("\\.");

        if(imgParts.length>1){
            boolean contains = Arrays.stream(imgFormats).anyMatch(imgParts[imgParts.length-1]
                    .toLowerCase()::equals);
            if(!contains) imgName=imgName+".jpg";
        }
        else if(imgParts.length<=1){imgName=imgName+".jpg";}
//        File check=new File(folder+imgName);
//        if(check.exists()){
            long now = java.time.Instant.now().getEpochSecond();
            imgName=now+imgName;
//    }
        return imgName;
    }
}
