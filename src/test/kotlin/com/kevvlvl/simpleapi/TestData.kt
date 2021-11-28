package com.kevvlvl.simpleapi

import com.kevvlvl.simpleapi.model.Company

class TestData {

    companion object {
        fun getStubCompanies() : List<Company> {

            val companyAbc = Company()
            companyAbc.symbol = "ABC"
            companyAbc.name = "A Boring Company"

            val companyToto = Company()
            companyToto.symbol = "TOTO"
            companyToto.name = "Great Sounding Vibes"

            return listOf(companyAbc, companyToto)
        }
    }
}