package hello.advanced.proxyconfig.v4_postprocessor;

import hello.advanced.proxyconfig.AppV1Config;
import hello.advanced.proxyconfig.AppV2Config;
import hello.advanced.proxyconfig.v3_proxyfactory.LogTraceAdvice;
import hello.advanced.proxyconfig.v4_postprocessor.postprocessor.PackageLogTracePostProcessor;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Slf4j
//@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class BeanPostProcessorConfig {

    @Bean
    public PackageLogTracePostProcessor logTracePostProcessor(LogTrace trace) {
        return new PackageLogTracePostProcessor("hello.advanced.proxyapp", getAdvisor(trace));
    }

    private Advisor getAdvisor(LogTrace trace) {
        // pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");
        // advise
        LogTraceAdvice advice = new LogTraceAdvice(trace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
