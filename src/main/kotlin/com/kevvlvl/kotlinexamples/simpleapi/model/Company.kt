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
}
