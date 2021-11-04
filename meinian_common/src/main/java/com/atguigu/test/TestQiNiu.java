package com.atguigu.test;

import com.atguigu.util.QiniuUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author lbstart
 * @create 2021-06-02 12:50
 */
public class TestQiNiu {
    /*

    */
    @Test
    public void test() throws IOException {
        //QiniuUtils.upload2Qiniu("D:\\temp\\90\\jjy94.jpg","jjy94.jpg");

        //QiniuUtils.deleteFileFromQiniu("jjy94.jpg");

        File file = new File("D:\\photo\\电脑壁纸\\car.jpg");
        //init array with file length
        byte[] bytesArray = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(bytesArray); //read file into bytes[]
        QiniuUtils.upload2Qiniu(bytesArray,"car1.jpg");
    }
}
