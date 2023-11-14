package com.tsurutan.headlessbms.e2e.annotations

import com.tsurutan.headlessbms.e2e.configurations.E2EConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = [E2EConfiguration::class]) // TODO: What is @ContextConfiguration
annotation class E2E
