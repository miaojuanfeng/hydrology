package gov.gz.hydrology;

import gov.gz.hydrology.constant.CommonConst;
import gov.gz.hydrology.thread.DataThread;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication
public class HydrologyApplication implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CommonConst.APPLICATION_CONTEXT = applicationContext;
    }

    public static void main(String[] args) {
        SpringApplication.run(HydrologyApplication.class, args);

        new DataThread();
    }

}
