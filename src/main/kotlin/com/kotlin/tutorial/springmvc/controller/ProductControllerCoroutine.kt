package com.kotlin.tutorial.springmvc.controller

import com.kotlin.tutorial.springmvc.utils.logger
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange

@RestController
class ProductControllerCoroutine(var restTemplate: RestTemplate) {

    var log = logger()

    @GetMapping("/get/mvc")
    fun getProduct(): Product {
        return Product(name = "mvc-apple")
    }

    @GetMapping("/get/coroutine")
    fun getProductCoroutine(): Product = runBlocking {

        var resp = async {
            log.info("rest template...")
            restTemplate.exchange<Int>("http://localhost:8080/get", HttpMethod.GET, null, Int.javaClass).body
        }

        Product(name = "coroutine-apple ${resp.await()}")
    }

    @GetMapping("/get")
    fun get(): Int {
        return 5
    }
}

data class Product(
        var id: Int = 0,
        var name: String = "apple",
        var price: Double = 0.0
)

