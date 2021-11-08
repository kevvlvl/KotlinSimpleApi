package com.kevvlvl.kotlinexamples.simpleapi.handler

import com.kevvlvl.kotlinexamples.simpleapi.model.Company
import com.kevvlvl.kotlinexamples.simpleapi.service.FinanceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import reactor.core.publisher.Flux

@Component
class FinanceHandler @Autowired constructor(private val financeService: FinanceService) {

    fun stock(request: ServerRequest): Flux<Company> {
        return financeService.getAllPublicCompanies()
    }
}