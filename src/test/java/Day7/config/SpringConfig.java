package Day7.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@Configuration

@ComponentScan(basePackages="Day7")
@ImportResource(locations={"classpath:cucumber.xml"})
public class SpringConfig {

}
