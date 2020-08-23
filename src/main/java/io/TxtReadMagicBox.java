package io;

import java.io.*;

/**
 * @author lmc
 * @date 2020/8/22 10:13
 */
public class TxtReadMagicBox {
    /*
    读取一列数据并拼接（非字符串）
     */
    public static void readNumDataAddCommaForSqlIn(String fileName) throws Exception {
        String project_path = System.getProperty("user.dir");


        File file = new File(project_path + "/file/demo/" + fileName + ".txt");


        //InputStream 是字节输入流的所有类的超类,一般我们使用它的子类,如FileInputStream等.
        InputStream fis = new FileInputStream(file);
        BufferedReader in = new BufferedReader(new InputStreamReader(fis, "UTF-8"));


        String result = "(";


        String line = "";
        while ((line = in.readLine()) != null) {


            result += line + ",";


        }

        result = result.substring(0, result.length() - 1) + ")";

        System.out.println(result);


    }

    /*
    读取一列数据并拼接（字符串）
    */
    public static void readStrDataAddCommaForSqlIn(String fileName) throws Exception {
        String project_path = System.getProperty("user.dir");


        File file = new File(project_path + "/file/demo/" + fileName + ".txt");


        //InputStream 是字节输入流的所有类的超类,一般我们使用它的子类,如FileInputStream等.
        InputStream fis = new FileInputStream(file);
        BufferedReader in = new BufferedReader(new InputStreamReader(fis, "UTF-8"));


        String result = "(";


        String line = "";
        while ((line = in.readLine()) != null) {


            result += "'" + line + "'" + ",";


        }

        result = result.substring(0, result.length() - 1) + ")";

        System.out.println(result);



    }



}
