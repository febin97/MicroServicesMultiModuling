package com.stackroute.moviezuulgateway;

import com.stackroute.moviezuulgateway.filters.ErrorFilter;
import com.stackroute.moviezuulgateway.filters.PostFilter;
import com.stackroute.moviezuulgateway.filters.PreFilter;
import com.stackroute.moviezuulgateway.filters.RouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class MovieZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieZuulGatewayApplication.class, args);
	}

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}

}
