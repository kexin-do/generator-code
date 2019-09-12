package com.kexin_do.tool.generator.tools.other;

import java.io.File;

public class FileMaker {

    public static File makeFile(String filePath){
        File fileDir = new File(filePath);
        if (!fileDir.getParentFile().exists()) {
            fileDir.getParentFile().mkdirs();
        }
        return fileDir;
    }
}
