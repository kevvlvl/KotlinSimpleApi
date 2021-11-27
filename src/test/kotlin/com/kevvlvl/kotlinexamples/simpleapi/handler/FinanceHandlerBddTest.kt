package com.kevvlvl.kotlinexamples.simpleapi.handler

import com.kevvlvl.kotlinexamples.simpleapi.TestData
import com.kevvlvl.kotlinexamples.simpleapi.model.Company
import com.kevvlvl.kotlinexamples.simpleapi.repository.CompanyRepository
import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.spring.CucumberContextConfiguration
import org.assertj.core.api.Assertions
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.web.reactive.function.server.ServerRequest
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import reactor.core.publisher.Flux

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
@Testcontainers
class FinanceHandlerBddTest {

    companion object {

        lateinit var returnedCompaniesFlux: Flux<Company>

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

    @Mock
    private lateinit var request: ServerRequest

    @Before
    fun init() {

        println("init test")

        MockitoAnnotations.openMocks(this)

        this.repository.deleteAll()
    }

    @After
    fun cleanup() {

        println("cleanup test")
        this.repository.deleteAll()
    }

    @Given("the valid stocks collection available for query")
    fun setupScenarioValidStocksData() {

        println("GIVEN - init test")
        this.repository.saveAll(TestData.getStubCompanies())
    }

    @When("calling the get stocks handler")
    fun callStocksHandler() {

        returnedCompaniesFlux = this.financeHandler.stock(request)
    }

    @Then("list of stocks are returned as a list")
    fun verifyStocksCallDataAsList() {

        Assertions.assertThat(returnedCompaniesFlux).isNotNull
        val returnedCompanies = arrayListOf<Company>()
        returnedCompaniesFlux.collectList().subscribe(returnedCompanies::addAll)

        Assertions.assertThat(returnedCompanies).isEqualTo(TestData.getStubCompanies())
    }
}