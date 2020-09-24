package test.czy;


import java.io.*;

public class check {

    public static String getText(String path) throws Exception{

        if(path == null || "".equals(path)){				//检查路径是否正确
            throw new exception("您的文件路径为空");
        }


        StringBuffer sb = new StringBuffer();

        BufferedReader br = null;
        try {
            File srcFile = new File(path);
            FileReader fr = new FileReader(srcFile);

            br = new BufferedReader(fr);

            
            String data;
            while ((data = br.readLine()) != null) {

                data.replaceAll("\\s*", "");
                data.replaceAll("[\\pP\\p{Punct}]","");
                sb.append(data);
                sb.append("\n");

            }

        } catch (Exception e) {
            throw new exception("您的文件为空");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
        return sb.toString();
    }

    public static void writeFiletoDisk(String fileName, String result) throws Exception {  //创建文件
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new exception("您输入的路径可能存在错误");
            }
        }
        FileOutputStream fileOutputStream = null;
        fileOutputStream = new FileOutputStream(fileName);
        fileOutputStream.write(result.getBytes());
        System.out.println("相似度为:" + result + "  已经写入" + fileName);
    }
}

