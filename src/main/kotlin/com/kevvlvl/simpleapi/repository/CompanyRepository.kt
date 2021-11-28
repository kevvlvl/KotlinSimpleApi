package com.kevvlvl.simpleapi.repository

import com.kevvlvl.simpleapi.model.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository: JpaRepository<Company, Long> {

    fun findAllByOrderByNameAsc(): List<Company>

}