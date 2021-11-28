package com.kevvlvl.simpleapi.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "company")
class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(nullable = false, length = 6)
    @NotBlank
    lateinit var symbol: String

    @Column(nullable = false, length = 100)
    @NotBlank
    lateinit var name: String
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Company

        if (symbol != other.symbol) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}
