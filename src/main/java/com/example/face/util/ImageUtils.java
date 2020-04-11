package com.example.face.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.util.List;

/**
 * @author fan.li
 * @date 2020-04-04
 * @description
 */

public class ImageUtils {
    public static File base64ToFile(String base64String) {
        String[] strings = base64String.split(",");
        String extension;
        // check image's extension
        switch (strings[0]) {
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default: //should write cases for more images types
                extension = "jpg";
                break;
        }
        //convert base64 string to binary data
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        String path = "/Users/apple/Downloads/切图/发现/头像_v1" + extension;
        File file = new File(path);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static String encodeFileToBase64Binary(File file){
        String encodedfile = null;
        String filename = file.getName();
        String extension = "";
        // check image's extension

        if (filename.contains("png")) {
            extension = "data:image/jpeg;base64";
        } else if (filename.contains("jpeg")) {
            extension = "data:image/jpeg;base64";
        } else if (filename.contains("jpg")) {
            extension = "data:image/jpg;base64";
        }
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return encodedfile;
    }

    public static File handleFileSize(File file, int maxSize)  {
        while (file.length() > maxSize) {
            System.out.println(file.length());
            try {
                List<File> fileList = Thumbnails.of(file).scale(0.9f).asFiles(Rename.NO_CHANGE);
                System.out.println(fileList);
                if (!fileList.isEmpty()) {
                    file = fileList.get(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static void main(String args[]) {
        File file = new File("/Users/apple/Downloads/切图/发现/testv3.png");

        File file2 = handleFileSize(file, 100 * 1000);

        System.out.println(file2.length());

        System.out.println(file.length());
        System.out.println(file.getName());
        System.out.println(encodeFileToBase64Binary(file));

        File file1 = base64ToFile(encodeFileToBase64Binary(file));
        System.out.println(file1.getName());
        System.out.println(encodeFileToBase64Binary(file1));

    }
}

