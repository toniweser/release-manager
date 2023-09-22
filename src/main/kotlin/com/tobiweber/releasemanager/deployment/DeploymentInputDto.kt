package com.tobiweber.releasemanager.deployment

data class DeploymentInputDto(
    val serviceName: String,
    val serviceVersionNumber: Int,
)
