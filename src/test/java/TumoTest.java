import org.junit.Test;

/**
 * 测试类，如果开启了@RunWith和@SpringBootTest注解，将启动Spring容器，否则就是正常的测试类
 *
 * @author tycoding
 * @since 2021/1/29
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = TumoBootApp.class)
public class TumoTest {

    @Test
    public void test() {
        System.out.println("Hello Tumo-Team~");
    }
}
