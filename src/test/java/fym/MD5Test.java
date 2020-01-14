package fym;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

/**
 * Created by lenovo on 2020/1/14.
 */
public class MD5Test {
    @Test
    public void testCryptography() throws Exception {
        //md5加密，不加盐
        String password_md5 = new Md5Hash("root").toString();
        System.out.println("md5加密，不加盐="+password_md5);
        //md5加密，加盐，一次散列
        String password_md5_salt_1 = new Md5Hash("root", "zhangsan", 1).toString();
        System.out.println("password_md5_salt_1="+password_md5_salt_1);
        //md5加密，加盐，一次散列
        String password_md5_salt_2 = new Md5Hash("root", "zhangsan", 2).toString();
        System.out.println("password_md5_sale_2="+password_md5_salt_2);
        //两次散列相当于md5(md5())
        //使用SimpleHash
        String simpleHash = new SimpleHash("MD5", "root", "zhangsan",1).toString();
        System.out.println(simpleHash);
    }
}
