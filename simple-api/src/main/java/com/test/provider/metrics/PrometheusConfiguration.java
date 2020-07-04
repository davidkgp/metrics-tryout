package com.test.provider.metrics;

import com.codahale.metrics.MetricRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.dropwizard.DropwizardExports;
import io.prometheus.client.hotspot.MemoryPoolsExports;
import io.prometheus.client.hotspot.StandardExports;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@EnablePrometheusEndpoint
public class PrometheusConfiguration {

    private final MetricRegistry dropwizardMetricRegistry;

    @Autowired
    public PrometheusConfiguration(MetricRegistry dropwizardMetricRegistry) {
        this.dropwizardMetricRegistry = dropwizardMetricRegistry;
    }

    @PostConstruct
    public void registerPrometheusCollectors() {
/*        Note that the call to CollectorRegistry.defaultRegistry.clear() is a workaround for
        unit tests failing due to ‘metric already registered’ errors. This error occurs since
        defaultRegistry is static and the Spring context is fired up multiple times during unit testing*/
        CollectorRegistry.defaultRegistry.clear();
        new StandardExports().register();
        new MemoryPoolsExports().register();
        new DropwizardExports(dropwizardMetricRegistry).register();
    }
}
