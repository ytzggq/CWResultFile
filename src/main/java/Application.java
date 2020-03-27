import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableScheduling
@ComponentScan("com")
/*
 * springboot的入口,这种写法不行要注解扫描包了
 * */
public class Application {

    private static ConfigurableApplicationContext context;
    public static void main(String[] args){

        Application.context=SpringApplication.run(Application.class,args);
    }
    @PreDestroy
    /*在开发中我们如果要在关闭spring容器后释放一些资源,通常的做法有如下几种:
1.在方法上加上@PreDestroy注解*/
    public void close(){
        Application.context.close();
    }
}
