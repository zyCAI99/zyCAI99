package test.czy;

import java.util.HashMap;
import java.util.Map;

public class MainTest{
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        int argsLength = args.length;   //校验路径参数
        if (argsLength != 3) {
            System.out.println("请输入三个正确的参数");
            throw new exception("参数异常");
        }


        // 将txt文件转化为String类
        String str1 = check.getText(args[0]);
        String str2 = check.getText(args[1]);



        Map<String,int[]> map = new HashMap<>();
        map = compare.getFrequency(str1,str2);
        double result = compare.Similary(map);
        
        check.writeFiletoDisk(args[2], Double.toString(result));
        long end = System.currentTimeMillis();
        System.out.println("一共用时：" + (end - start) / 1000.0 + "秒");

    }
}

