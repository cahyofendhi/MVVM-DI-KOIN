package com.mvvm_di_koin.module.repository

import org.junit.After
import org.junit.Before
import org.koin.core.inject
import org.koin.test.KoinTest

class NewsRepositoryTest: KoinTest {

    private val repository : NewsRepository by inject()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }
}