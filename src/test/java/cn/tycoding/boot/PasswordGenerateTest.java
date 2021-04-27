package cn.tycoding.boot;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author tycoding
 * @since 2020/10/19
 */
public class PasswordGenerateTest {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void passwordTest() {
        // 默认用户名：tumo-boot 密码：123456
        System.out.println(passwordEncoder.encode("123456")); // $2a$10$TlSIkPzm5QqkSMhtP0nFQ.fx864TTT6meypBChmMCcGrkq.5RLh0K
    }
}
