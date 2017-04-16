package ru.kpfu.itis.group11501.vitise.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Properties;

/**
 * Created by Марат on 05.04.2017.
 */
public class FileUploader {
    public static String uploadFile(MultipartFile file, String directory) {
        StringBuilder name = new StringBuilder();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String rootPath = getRootPath();
                name.append("/loadFiles");
                name.append(File.separator).append(directory);
                File dir = new File(rootPath + name);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                name.append(File.separator).append(file.getOriginalFilename());
                File uploadedFile = new File(rootPath + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return name.toString();
    }

    public static String getRootPath() {
        FileInputStream fis;
        Properties property = new Properties();
        String rootPath = "";
        try {
            fis = new FileInputStream("\\vitise\\src\\main\\resources\\path.properties");
            property.load(fis);
            rootPath = property.getProperty("webapp.path");

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл path.properties отсуствует!");
        }
        return rootPath;
    }
}
