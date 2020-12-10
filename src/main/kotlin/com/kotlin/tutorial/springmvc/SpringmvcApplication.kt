package com.kotlin.tutorial.springmvc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringmvcApplication

fun main(args: Array<String>) {
    runApplication<SpringmvcApplication>(*args)
}
