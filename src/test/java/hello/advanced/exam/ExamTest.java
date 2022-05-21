package hello.advanced.exam;

import hello.advanced.aopapp.exam.ExamService;
import hello.advanced.aopapp.exam.aop.RetryAspect;
import hello.advanced.aopapp.exam.aop.TraceAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import({TraceAspect.class, RetryAspect.class})
@SpringBootTest
public class ExamTest {

    @Autowired
    ExamService service;

    @Test
    void test() {
        for (int i = 0; i < 5; i++) {
            log.info("client request={}", i);
            service.request("data" + i);
        }
    }
}
