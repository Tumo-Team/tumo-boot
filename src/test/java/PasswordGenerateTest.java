import cn.tycoding.boot.TumoBootApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tycoding
 * @since 2020/10/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TumoBootApp.class)
public class PasswordGenerateTest {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void passwordTest() {
        // 默认用户名：tumo-boot 密码：tycoding
        System.out.println(passwordEncoder.encode("tycoding")); // $2a$10$.Sb3c/st.CpxxLp5N1X7f.PTfJEUm/yHg3ZH4V5cjDaU1tYbup8Na

        Long l = 1320191857076097026L;
        System.out.println(l);
    }
}
