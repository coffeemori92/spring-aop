package hello.advanced.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component {

    private final Component component;

    public MessageDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("messageDecorator 실행");
        String result = component.operation();
        String decoResult = "*****" + result + "*****";
        log.info("messageDecorator 꾸미기 적용 전 ={}, 적용후 ={}", result, decoResult);
        return decoResult;
    }
}
