package com.tobiweber.releasemanager.technical.database

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FlywayMigrationConfiguration {
    @Bean
    fun migrateStrategy(): FlywayMigrationStrategy {
        return FlywayMigrationStrategy { flyway ->
            flyway.clean()
            flyway.migrate()
        }
    }
}
