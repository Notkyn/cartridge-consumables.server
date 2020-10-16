package ua.notky.cartridge.consumables.tools.extension;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.*;
import org.springframework.util.StopWatch;

@Slf4j
public class TimingExtension implements BeforeTestExecutionCallback,
        AfterTestExecutionCallback, AfterAllCallback, BeforeAllCallback {
    private StopWatch stopWatch;

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        log.info("Start stopWatch");
        System.out.println("Start stopWatch");
        stopWatch.start(extensionContext.getDisplayName());
    }


    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        log.info('\n' + stopWatch.prettyPrint() + '\n');
        System.out.println("\n" + stopWatch.prettyPrint() + "\n");
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        stopWatch.stop();
        log.info("stop stopWatch");
        System.out.println("stop stopWatch");
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        stopWatch = new StopWatch("Execution time of " + extensionContext.getRequiredTestClass().getSimpleName());
    }
}
