package com.warehousemanagement.wms.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class CompareFiles {
    public static Boolean compareFiles(File firstFile,File secondFile) throws IOException{
        if(firstFile.length()!=secondFile.length()){
            return false;
        }
        try (FileInputStream fis1 = new FileInputStream(firstFile);
             FileInputStream fis2 = new FileInputStream(secondFile)){
            int bufferSize=8192;
            byte[] buffer1=new byte[bufferSize];
            byte[] buffer2=new byte[bufferSize];
            int bytesRead1,bytesRead2;

            while ((bytesRead1=fis1.read(buffer1))!=-1){
                bytesRead2=fis2.read(buffer2);
                for (int i = 0; i < bytesRead1; i++) {
                    if (buffer1[i] != buffer2[i]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
