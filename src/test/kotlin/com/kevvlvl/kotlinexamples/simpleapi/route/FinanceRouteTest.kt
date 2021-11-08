package com.kevvlvl.kotlinexamples.simpleapi.route
import com.kevvlvl.kotlinexamples.simpleapi.handler.FinanceHandler
import com.kevvlvl.kotlinexamples.simpleapi.model.Company
import com.kevvlvl.kotlinexamples.simpleapi.repository.CompanyRepository
import com.kevvlvl.kotlinexamples.simpleapi.service.FinanceService
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(SpringExtension::class)
@WebFluxTest
@Import(FinanceHandler::class, FinanceService::class)
class FinanceRouteTest {

    @Autowired
    private lateinit var handler: FinanceHandler

    @MockBean
    private lateinit var companyRepository: CompanyRepository

    private lateinit var webTestClient: WebTestClient

    @BeforeEach
    fun init() {
        println("init test")

        val router = FinanceRoute().route(handler)
        webTestClient = WebTestClient
            .bindToRouterFunction(router)
            .build()
    }

    @Test
    fun getCompaniesCorrectlyAndReturnList() {

        val stubCompanies = getStubCompanies()

        Mockito.`when`(companyRepository.findAllByOrderByNameAsc()).thenReturn(stubCompanies)

        webTestClient.get()
            .uri("/stocks")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(Company::class.java).value<WebTestClient.ListBodySpec<Company>>{
                for(currentCompany in it) {
                    Assertions.assertTrue(stubCompanies.contains(currentCompany))
                }
            }

    }

    private fun getStubCompanies() : List<Company> {

        val companyAbc = Company()
        companyAbc.id = 1
        companyAbc.symbol = "ABC"
        companyAbc.name = "A Boring Company"

        val companyToto = Company()
        companyToto.id = 3
        companyToto.symbol = "TOTO"
        companyToto.name = "Great Sounding Vibes"

        return listOf(companyAbc, companyToto)
    }
}