package com.tobiweber.releasemanager.deployment

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "deployment")
data class DeploymentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var serviceName: String,
    var serviceVersionNumber: Int,
    var systemVersionNumber: Int,
)
