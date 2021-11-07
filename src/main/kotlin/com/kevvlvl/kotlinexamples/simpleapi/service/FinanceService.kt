package com.kevvlvl.kotlinexamples.simpleapi.service

import com.kevvlvl.kotlinexamples.simpleapi.model.Company
import com.kevvlvl.kotlinexamples.simpleapi.repository.CompanyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class FinanceService @Autowired constructor(private val companyRepository: CompanyRepository) {

    fun getAllPublicCompanies() : Flux<Company> {
        return Flux.fromIterable(companyRepository.findAllByOrderByNameAsc())
    }
}