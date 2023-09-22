package com.tobiweber.releasemanager.deployment

import org.springframework.data.jpa.repository.JpaRepository

interface DeploymentRepository : JpaRepository<DeploymentEntity, Long>
