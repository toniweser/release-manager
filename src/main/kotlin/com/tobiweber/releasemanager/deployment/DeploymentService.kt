package com.tobiweber.releasemanager.deployment

import org.springframework.stereotype.Service

@Service
class DeploymentService(
    private val deploymentRepository: DeploymentRepository,
) {
    fun deploy(deploymentDto: DeploymentDto): Int {
        // TODO: Optimize performance by calculating system version number on database level
        val allDeployments = deploymentRepository.findAll()
        val maxSystemVersionNumber =
            if (allDeployments.isEmpty()) {
                0
            } else {
                allDeployments.maxOf { it.systemVersionNumber }
            }

        val deploymentExists = allDeployments.any {
            it.serviceName == deploymentDto.serviceName &&
                it.serviceVersionNumber == deploymentDto.serviceVersionNumber
        }

        val systemVersionNumber = if (deploymentExists) {
            maxSystemVersionNumber
        } else {
            maxSystemVersionNumber + 1
        }

        val deploymentEntity = DeploymentEntity(
            serviceName = deploymentDto.serviceName,
            serviceVersionNumber = deploymentDto.serviceVersionNumber,
            systemVersionNumber = systemVersionNumber,
        )

        deploymentRepository.save(deploymentEntity)
        return systemVersionNumber
    }

    fun getServices(systemVersion: Int): List<DeploymentDto> {
        val deployments = deploymentRepository.findBySystemVersionNumberLessThanEqual(systemVersion)
        // Filter for latest deployment for each service
        val services = deployments
            .groupBy { it.serviceName }
            .map { (_, deployments) ->
                deployments.maxByOrNull { it.systemVersionNumber }!!
            }
            .sortedBy { it.serviceName }
        return services.map { it.toDto() }
    }
}
