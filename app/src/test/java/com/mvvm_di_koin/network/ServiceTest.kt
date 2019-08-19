package com.mvvm_di_koin.network

import com.mvvm_di_koin.data.API_KEY
import com.mvvm_di_koin.data.BASE_URL
import io.restassured.RestAssured.given
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.junit.After
import org.junit.Test

class ServiceTest {

    private var spec: RequestSpecification = RequestSpecBuilder().
        setContentType(ContentType.JSON).
        setBaseUri(BASE_URL).
        addFilter(ResponseLoggingFilter()).
        addFilter(RequestLoggingFilter()).build()

    @Test
    fun topHeadLineApi(){
        given()
            .queryParam("country", "us")
            .queryParam("apiKey", API_KEY)
            .spec(spec)
            .`when`()
            .get("top-headlines")
            .then()
            .statusCode(200)
    }

    @Test
    fun everythingApi(){
        given()
            .queryParam("q", "u").queryParam("apiKey", API_KEY)
            .spec(spec)
            .`when`()
            .get("everything")
            .then()
            .statusCode(200)
    }

    @Test
    fun sourceApi(){
        given()
            .queryParam("apiKey", API_KEY)
            .spec(spec)
            .`when`()
            .get("sources")
            .then()
            .statusCode(200)
    }

    @After
    fun tearDown() {
    }

}