package com.kevvlvl.kotlinexamples.simpleapi.route

import com.kevvlvl.kotlinexamples.simpleapi.handler.FinanceHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RequestPredicates.accept
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration(proxyBeanMethods = false)
class FinanceRoute {

    @Bean
    fun route(financeHandler: FinanceHandler): RouterFunction<ServerResponse> {

        return RouterFunctions
            .route(
                GET("/stock").and(accept(MediaType.APPLICATION_JSON)),
                financeHandler::stock)
    }
}