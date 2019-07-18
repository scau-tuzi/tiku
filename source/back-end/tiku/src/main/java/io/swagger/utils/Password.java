package io.swagger.utils;

import io.swagger.pojo.dao.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class Password {
    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private static String algorithmName = "md5";

    /**给用户对象设置密码
     * @param user
     * @param password
     */
    public static void encryptPassword(User user,String password) {
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String passmd5 = new SimpleHash(algorithmName, password+user.getSalt()).toHex();
        String newPassword = new SimpleHash(algorithmName, passmd5).toHex();
        user.setPasswordSaltMd5(newPassword);
    }

    /**校验密码
     * @param user
     * @param password
     */
    public static boolean checkPassword(User user,String password) {
        String passmd5 = new SimpleHash(algorithmName, password+user.getSalt()).toHex();
        String newPassword = new SimpleHash(algorithmName, passmd5).toHex();
        return newPassword.equals(user.getPasswordSaltMd5());
    }
}