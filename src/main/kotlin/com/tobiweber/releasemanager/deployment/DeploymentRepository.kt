package com.tobiweber.releasemanager.deployment

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DeploymentRepository : JpaRepository<DeploymentEntity, Long> {
    /**
     * Returns the max system version number or 0 if no deployment exists.
     */
    @Query("SELECT COALESCE(MAX(d.systemVersionNumber), 0) FROM DeploymentEntity d")
    fun findMaxSystemVersionNumber(): Int
    fun existsByServiceNameAndServiceVersionNumber(serviceName: String, serviceVersionNumber: Int): Boolean
    fun findBySystemVersionNumberLessThanEqual(systemVersionNumber: Int): List<DeploymentEntity>
}
