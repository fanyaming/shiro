package fym;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by lenovo on 2020/1/14.
 */
public class LoginLogoutTest {
    @Test
    public void test() throws  Exception{
        //1、创建SecurityManager工厂,IniSecurityManagerFactory可以从ini文件中初始化SecurityManager环境
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2、创建SecurityManager
        SecurityManager securityManager = factory.getInstance();
        //3、将securityManager设置到运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        //4、在运行环境下创建Subject
        Subject subject =  SecurityUtils.getSubject();
        //5、创建token令牌，记录用户认证的身份和凭证即账号和密码
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "666");
        try {
            //6、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
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
    @Test
    public void testRole() throws Exception {
        //1、创建SecurityManager工厂,IniSecurityManagerFactory可以从ini文件中初始化SecurityManager环境
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        //2、创建SecurityManager
        SecurityManager securityManager = factory.getInstance();
        //3、将securityManager设置到运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        //4、在运行环境下创建Subject
        Subject subject =  SecurityUtils.getSubject();
        //5、创建token令牌，记录用户认证的身份和凭证即账号和密码
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "666");
        try {
            //6、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //7、身份验证失败
            e.printStackTrace();
        }
        //8、用户认证状态
        Boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("用户认证状态：" + isAuthenticated);
        //是否有某一个角色
        System.out.println("用户是否拥有一个角色：" + subject.hasRole("role2"));
        //是否有多个角色
        System.out.println("用户是否拥有多个角色：" + subject.hasAllRoles(Arrays.asList("role1", "role2")));

        //角色检查，如果没有就跑出异常
        //subject.checkRole("role1");
        //subject.checkRoles(Arrays.asList("role1", "role2"));
    }
    @Test
    public void testPermission() throws Exception {
        //1、创建SecurityManager工厂,IniSecurityManagerFactory可以从ini文件中初始化SecurityManager环境
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        //2、创建SecurityManager
        SecurityManager securityManager = factory.getInstance();
        //3、将securityManager设置到运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        //4、在运行环境下创建Subject
        Subject subject =  SecurityUtils.getSubject();
        //5、创建token令牌，记录用户认证的身份和凭证即账号和密码
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "666");
        try {
            //6、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //7、身份验证失败
            e.printStackTrace();
        }
        //8、用户认证状态
        Boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("用户认证状态：" + isAuthenticated);
        //是否有某一个权限
        System.out.println("用户是否拥有一个权限：" + subject.isPermitted("user:create"));
        //是否有多个权限
        System.out.println("用户是否拥有多个权限：" + subject.isPermittedAll("user:create","user:update"));

        //权限检查，如果没有就跑出异常
        //subject.checkPermission("user:delete");
        //subject.checkPermissions("user:create","user:delete");
        //是否具备编号为1的user资源进行delete操作
        //subject.checkPermission("user:delete:1");
    }
}
