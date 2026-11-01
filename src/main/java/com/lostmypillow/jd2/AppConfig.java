package com.lostmypillow.jd2;

import com.lostmypillow.jd2.jd.JdClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration
@ImportHttpServices({
        JdClient.class
})
public class AppConfig {
    @Bean
    public RestClientHttpServiceGroupConfigurer groupConfigurer(@Value("${jd_instance.url:http://localhost:3128}") String baseUrl) {
        return groups -> groups.forEachClient((group, builder) -> builder.baseUrl(baseUrl));
    }
}
