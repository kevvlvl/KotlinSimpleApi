package com.kevvlvl.kotlinexamples.simpleapi.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

        if (id != other.id) return false
        if (symbol != other.symbol) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + symbol.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}
