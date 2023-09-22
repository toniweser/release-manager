package com.tobiweber.releasemanager.deployment

import org.springframework.stereotype.Service

@Service
class DeploymentService(
    private val deploymentRepository: DeploymentRepository,
) {
    fun deploy(deploymentDto: DeploymentDto): Int {
        val maxSystemVersionNumber = deploymentRepository.findMaxSystemVersionNumber()
        val deploymentExists = deploymentRepository.existsByServiceNameAndServiceVersionNumber(
            deploymentDto.serviceName,
            deploymentDto.serviceVersionNumber,
        )

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
                deployments.maxBy { it.systemVersionNumber }
            }
            .sortedBy { it.serviceName }
        return services.map { it.toDto() }
    }
}
