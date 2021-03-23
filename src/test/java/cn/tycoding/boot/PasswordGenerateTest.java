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
        // 默认用户名：Tumo-Boot 密码：Tumo-Boot
        System.out.println(passwordEncoder.encode("Tumo-Boot")); // $2a$10$ZjqngBAeTeUEvd3oe1DrL.I0uKO7X.6IZVWAb3Zc4TmqmID.zOWGe
    }
}
