package com.tobiweber.releasemanager.deployment

fun DeploymentEntity.toDto(): DeploymentDto {
    return DeploymentDto(
        serviceName = this.serviceName,
        serviceVersionNumber = this.serviceVersionNumber,
    )
}
