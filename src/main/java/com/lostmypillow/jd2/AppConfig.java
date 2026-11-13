package com.lostmypillow.jd2;

import com.lostmypillow.jd2.accounts.AccountsClient;
import com.lostmypillow.jd2.accountsv2.AccountsV2Client;
import com.lostmypillow.jd2.captcha.CaptchaClient;
import com.lostmypillow.jd2.captchaforward.CaptchaForwardClient;
import com.lostmypillow.jd2.jd.JdClient;
import com.lostmypillow.jd2.linkgrabberv2.LinkGrabberClient;
import com.lostmypillow.jd2.root.RootClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.ImportHttpServices;
import tools.jackson.databind.ObjectMapper;

@Configuration
@ImportHttpServices({
        JdClient.class, LinkGrabberClient.class, RootClient.class, AccountsClient.class, AccountsV2Client.class, CaptchaClient.class, CaptchaForwardClient.class
})
@EnableAsync
public class AppConfig {
    @Bean
    public RestClientHttpServiceGroupConfigurer groupConfigurer(@Value("${jd_instance.url:http://localhost:3128}") String baseUrl) {
        return groups -> groups.forEachClient((group, builder) -> builder.baseUrl(baseUrl));
    }
}