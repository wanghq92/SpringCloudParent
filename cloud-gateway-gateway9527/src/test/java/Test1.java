import org.junit.Test;

import java.time.ZonedDateTime;

/**
 * @author whq
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021-12-28 11:40
 */
public class Test1 {

    @Test
    public void test1(){
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
    }
}
