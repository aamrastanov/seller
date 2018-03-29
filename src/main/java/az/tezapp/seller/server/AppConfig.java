package az.tezapp.seller.server;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import az.tezapp.seller.server.controller.WebRequestTraceFilter;
import az.tezapp.seller.server.model.AppProperties;

@Configuration
public class AppConfig {

    @Value("${resources.path.windows}")
    private String resourcesPathOnWindows;

    @Value("${resources.path.linux}")
    private String resourcesPathOnLinux;

    @Value("${resources.webname}")
    private String resourcesWebName;

    @Bean
    public AppProperties configureAppProperties() {
        String resourcesPath = null;
        String ocName = System.getProperty("os.name");
        if (ocName.startsWith("Linux")) {
            resourcesPath = resourcesPathOnLinux;
        } else if (ocName.startsWith("Windows")) {
            resourcesPath = resourcesPathOnWindows;
        }
        return new AppProperties(resourcesWebName, resourcesPath);
    }

    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(requestFilter());
        registration.addUrlPatterns("/rest/*");
        registration.setName("requestFilter");
        return registration;
    }

    @Bean(name = "requestFilter")
    public Filter requestFilter() {
        return new WebRequestTraceFilter();
    }
}
