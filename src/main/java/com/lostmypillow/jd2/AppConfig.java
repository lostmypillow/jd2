package com.lostmypillow.jd2;

import com.lostmypillow.jd2.accounts.AccountsClient;
import com.lostmypillow.jd2.accountsv2.AccountsV2Client;
import com.lostmypillow.jd2.captcha.CaptchaClient;
import com.lostmypillow.jd2.captchaforward.CaptchaForwardClient;
import com.lostmypillow.jd2.config.ConfigClient;
import com.lostmypillow.jd2.contentv2.ContentV2Client;
import com.lostmypillow.jd2.device.DeviceClient;
import com.lostmypillow.jd2.dialogs.DialogsClient;
import com.lostmypillow.jd2.downloadcontroller.DownloadControllerClient;
import com.lostmypillow.jd2.downloadevents.DownloadEventsClient;
import com.lostmypillow.jd2.downloadsv2.DownloadsV2Client;
import com.lostmypillow.jd2.events.EventsClient;
import com.lostmypillow.jd2.extensions.ExtensionsClient;
import com.lostmypillow.jd2.extraction.ExtractionClient;
import com.lostmypillow.jd2.flash.FlashClient;
import com.lostmypillow.jd2.jd.JdClient;
import com.lostmypillow.jd2.linkcollector.LinkCollectorClient;
import com.lostmypillow.jd2.linkcrawler.LinkCrawlerClient;
import com.lostmypillow.jd2.linkgrabberv2.LinkGrabberClient;
import com.lostmypillow.jd2.log.LogClient;
import com.lostmypillow.jd2.plugins.PluginsClient;
import com.lostmypillow.jd2.polling.PollingClient;
import com.lostmypillow.jd2.reconnect.ReconnectClient;
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
        JdClient.class, LinkGrabberClient.class, RootClient.class, AccountsClient.class, AccountsV2Client.class, CaptchaClient.class, CaptchaForwardClient.class, ConfigClient.class, ContentV2Client.class, DeviceClient.class, DialogsClient.class, DownloadControllerClient.class, DownloadEventsClient.class, DownloadsV2Client.class, EventsClient.class, ExtensionsClient.class, ExtractionClient.class, FlashClient.class, LinkCollectorClient.class, LinkCrawlerClient.class, LogClient.class, PluginsClient.class, PollingClient.class, ReconnectClient.class
})
@EnableAsync
public class AppConfig {
    @Bean
    public RestClientHttpServiceGroupConfigurer groupConfigurer(@Value("${jd_instance.url:http://localhost:3128}") String baseUrl) {
        return groups -> groups.forEachClient((group, builder) -> builder.baseUrl(baseUrl));
    }
}