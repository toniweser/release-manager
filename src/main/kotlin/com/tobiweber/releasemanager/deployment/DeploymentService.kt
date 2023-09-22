package com.tobiweber.releasemanager.deployment

import org.springframework.stereotype.Service

@Service
class DeploymentService(
    private val deploymentRepository: DeploymentRepository,
) {
    fun deploy(deploymentInputDto: DeploymentInputDto): Int {
        // TODO: Optimize performance by calculating system version number on database level
        val allDeployments = deploymentRepository.findAll()
        val maxSystemVersionNumber =
            if (allDeployments.isEmpty()) {
                0
            } else {
                allDeployments.maxOf { it.systemVersionNumber }
            }

        val deploymentExists = allDeployments.any {
            it.serviceName == deploymentInputDto.serviceName &&
                it.serviceVersionNumber == deploymentInputDto.serviceVersionNumber
        }

        val systemVersionNumber = if (deploymentExists) {
            maxSystemVersionNumber
        } else {
            maxSystemVersionNumber + 1
        }

        val deploymentEntity = DeploymentEntity(
            serviceName = deploymentInputDto.serviceName,
            serviceVersionNumber = deploymentInputDto.serviceVersionNumber,
            systemVersionNumber = systemVersionNumber,
        )

        deploymentRepository.save(deploymentEntity)
        return systemVersionNumber
    }
}
