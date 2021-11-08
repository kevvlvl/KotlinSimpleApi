package com.kevvlvl.kotlinexamples.simpleapi.route

import com.kevvlvl.kotlinexamples.simpleapi.handler.FinanceHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RequestPredicates.accept
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse

import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body

@Configuration(proxyBeanMethods = false)
class FinanceRoute {

    @Bean
    fun route(financeHandler: FinanceHandler): RouterFunction<ServerResponse> {

        return route(GET("/stocks").and(accept(MediaType.APPLICATION_JSON))) {
            ok().body(financeHandler.stock(it))
        }
    }
}