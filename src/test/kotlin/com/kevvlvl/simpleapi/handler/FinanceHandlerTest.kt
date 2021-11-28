package com.kevvlvl.simpleapi.handler

import com.kevvlvl.simpleapi.TestData
import com.kevvlvl.simpleapi.model.Company
import com.kevvlvl.simpleapi.repository.CompanyRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.web.reactive.function.server.ServerRequest
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import reactor.core.publisher.Flux

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class FinanceHandlerTest {

    companion object {

        @Container
        val container = PostgreSQLContainer<Nothing>("postgres:14-alpine3.14")
            .apply {
                withDatabaseName("testdb")
                withUsername("testUser")
                withPassword("testPass123")
                withInitScript("postgres-init.sql")
                start()
            }

        @JvmStatic
        @DynamicPropertySource
        fun postgresProperties(registry: DynamicPropertyRegistry) {

            registry.add("spring.datasource.url", container::getJdbcUrl)
            registry.add("spring.datasource.username", container::getUsername)
            registry.add("spring.datasource.password", container::getPassword)
        }
    }

    @Autowired
    private lateinit var financeHandler: FinanceHandler

    @Autowired
    private lateinit var repository: CompanyRepository

    @MockBean
    private lateinit var request: ServerRequest

    @BeforeEach
    fun init() {

        println("init test")
        this.repository.saveAll(TestData.getStubCompanies())
    }

    @AfterEach
    fun cleanup() {

        println("cleanup test")
        this.repository.deleteAll()
    }

    @Test
    fun getStocksCorrectlyAndList() {

        val stubCompanies = TestData.getStubCompanies()
        val returnedCompaniesFlux: Flux<Company> = this.financeHandler.stock(request)

        assertThat(returnedCompaniesFlux).isNotNull

        val returnedCompanies = arrayListOf<Company>()
        returnedCompaniesFlux.collectList().subscribe(returnedCompanies::addAll)

        assertThat(returnedCompanies).isEqualTo(stubCompanies)
    }
}