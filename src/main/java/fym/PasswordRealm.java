package fym;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * Created by lenovo on 2020/1/14.
 */
public class PasswordRealm extends AuthorizingRealm {
    //Realm的名称
    @Override
    public String getName() {
        return "PasswordRealm";
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户名
        String principal = (String) token.getPrincipal();
        //根据用户名去数据库中查询查询用户对象,如果找不到就返回null
        //此处我们自己模拟数据库有一条数据 ---->zhangsan:666
        if(!"zhangsan".equals(principal)){
            //表示找不到用户,返回空认证信息
            return null;
        }
        //此处应该拿到登陆名到数据库中获取对应用户的密码
        //我们在这里就简单模拟从数据库中已经获取到密码了.
        //密码在数据库中是密文存储,对应明文为:root,盐:admin
        String password = "2a238413f0931a219bf70721c8e741d8";//静态数据,模拟数据库中获取到的.
        //盐,在数据库中存储的
        String salt = "zhangsan";
        //返回认证信息由父类AuthenticatingRealm进行认证
        //密码的匹配是AuthenticatingRealm进行匹配的,我们只需要返回认证信息回去即可.
        //返回认证信息的时候需要把盐也一并的传过去.
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(principal, password, ByteSource.Util.bytes(salt),getName());
        return simpleAuthenticationInfo;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
}
