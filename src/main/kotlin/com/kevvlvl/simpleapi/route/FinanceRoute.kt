package com.kevvlvl.simpleapi.route

import com.kevvlvl.simpleapi.handler.FinanceHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse

import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body

@Configuration(proxyBeanMethods = false)
class FinanceRoute {

    @Bean
    fun route(financeHandler: FinanceHandler): RouterFunction<ServerResponse> {

        return route(GET("/stocks")) {
            ok().body(financeHandler.stock(it))
        }
    }
}