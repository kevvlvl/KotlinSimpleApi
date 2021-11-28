package com.kevvlvl.simpleapi.route
import com.kevvlvl.simpleapi.TestData
import com.kevvlvl.simpleapi.model.Company
import com.kevvlvl.simpleapi.repository.CompanyRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class FinanceRouteTest {

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
    private lateinit var repository: CompanyRepository

    @Autowired
    private lateinit var webTestClient: WebTestClient

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
    fun getCompaniesCorrectlyAndReturnList() {

        val stubCompanies = TestData.getStubCompanies()

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

}