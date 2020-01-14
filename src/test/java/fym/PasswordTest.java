package fym;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Created by lenovo on 2020/1/14.
 */
public class PasswordTest {
    @Test
    public void testPassword() throws Exception {
        //1、创建SecurityManager工厂,IniSecurityManagerFactory可以从ini文件中初始化SecurityManager环境
        IniSecurityManagerFactory factory= new IniSecurityManagerFactory("classpath:shiro-cryptography.ini");
        //2、创建SecurityManager
        SecurityManager securityManager = factory.getInstance();
        //3、将securityManager设置到运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        //4、在运行环境下创建Subject
        Subject subject =  SecurityUtils.getSubject();
        //5、创建token令牌，记录用户认证的身份和凭证即账号和密码
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "root");
        try {
            //6、登录，即身份验证
            subject.login(token);
        } catch (Exception e) {
            //7、身份验证失败
            e.printStackTrace();
        }
        //8、用户认证状态
        Boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("用户认证状态：" + isAuthenticated);
        //9、用户退出
        subject.logout();
        isAuthenticated = subject.isAuthenticated();
        System.out.println("用户认证状态：" + isAuthenticated);
    }
}
