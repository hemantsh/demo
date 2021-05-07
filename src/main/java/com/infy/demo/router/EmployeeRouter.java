package com.infy.demo.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import com.infy.demo.handler.EmployeeHandler;

@Configuration
public class EmployeeRouter {

	@Bean
	public RouterFunction<ServerResponse> route(EmployeeHandler empHandler) {

		return RouterFunctions
				.route(GET("/employees").and(accept(MediaType.APPLICATION_JSON))
						,empHandler::getEmployees )
				.andRoute(GET("/employee/{id}").and(accept(MediaType.APPLICATION_JSON))
						,empHandler::getEmployeeDetails )
				.andRoute(POST("/employee").and(accept(MediaType.APPLICATION_JSON))
						,empHandler::insertEmployee )
				.andRoute(DELETE("/employee/{id}").and(accept(MediaType.APPLICATION_JSON))
						,empHandler::deleteEmployee )
				.andRoute(PUT("/employee").and(accept(MediaType.APPLICATION_JSON))
						,empHandler::updateEmployee );
	    
	}
}
