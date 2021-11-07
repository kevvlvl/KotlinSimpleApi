package com.kevvlvl.kotlinexamples.simpleapi.repository

import com.kevvlvl.kotlinexamples.simpleapi.model.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository: JpaRepository<Company, Long> {

    fun findAllByOrderByNameAsc(): List<Company>

}