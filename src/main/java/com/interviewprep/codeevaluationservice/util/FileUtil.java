package com.interviewprep.codeevaluationservice.util;

import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    public static String saveCodeToFile(String code, String filename){
        String filePath = "/tmp/" + filename;
        try(FileWriter writer = new FileWriter(filePath)){
            writer.write(code);
        }catch(IOException e){
            throw new RuntimeException("Error writing code to file",e);
        }
        return filePath;
    }
}
