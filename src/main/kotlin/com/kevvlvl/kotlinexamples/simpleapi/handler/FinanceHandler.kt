package com.kevvlvl.kotlinexamples.simpleapi.handler

import com.kevvlvl.kotlinexamples.simpleapi.service.FinanceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class FinanceHandler @Autowired constructor(private val financeService: FinanceService) {

    fun stock(request: ServerRequest): Mono<ServerResponse> {

        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(financeService::getAllPublicCompanies));
    }
}