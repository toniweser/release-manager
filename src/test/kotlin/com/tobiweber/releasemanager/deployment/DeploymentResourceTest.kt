package com.tobiweber.releasemanager.deployment

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@ActiveProfiles("test")
@Rollback
@Transactional
@SpringBootTest
class DeploymentResourceTest {
    @Autowired
    private lateinit var deploymentResource: DeploymentResource

    @Autowired
    private lateinit var deploymentRepository: DeploymentRepository

    @Test
    fun `should deploy and return correct system version number`() {
        var systemVersionNumber = deploymentResource.deploy(DeploymentDto("Service A", 1))
        assertThat(systemVersionNumber).isEqualTo(1)

        systemVersionNumber = deploymentResource.deploy(DeploymentDto("Service B", 1))
        assertThat(systemVersionNumber).isEqualTo(2)

        systemVersionNumber = deploymentResource.deploy(DeploymentDto("Service A", 2))
        assertThat(systemVersionNumber).isEqualTo(3)

        systemVersionNumber = deploymentResource.deploy(DeploymentDto("Service B", 1))
        assertThat(systemVersionNumber).isEqualTo(3)

        systemVersionNumber = deploymentResource.deploy(DeploymentDto("Service A", 1))
        assertThat(systemVersionNumber).isEqualTo(3)
    }

    @Test
    fun `should return correct services for system version`() {
        clearDeploymentDatabase()
        val deployment1 = DeploymentDto("Service A", 1)
        val deployment2 = DeploymentDto("Service B", 1)
        val deployment3 = DeploymentDto("Service A", 2)
        val deployment4 = DeploymentDto("Service B", 1)

        deploymentResource.deploy(deployment1)
        deploymentResource.deploy(deployment2)
        deploymentResource.deploy(deployment3)
        deploymentResource.deploy(deployment4)

        var services = deploymentResource.getServices(1)
        assertThat(services).hasSize(1)
        assertThat(services.first()).isEqualTo(deployment1)

        services = deploymentResource.getServices(2)
        assertThat(services).hasSize(2)
        assertThat(services).containsExactlyInAnyOrder(deployment1, deployment2)

        services = deploymentResource.getServices(3)
        assertThat(services).hasSize(2)
        assertThat(services).containsExactlyInAnyOrder(deployment3, deployment4)
    }

    @Test
    fun `should return no services if there is no deployment`() {
        clearDeploymentDatabase()
        val services = deploymentResource.getServices(1)
        assertThat(services).isEmpty()
    }

    private fun clearDeploymentDatabase() {
        deploymentRepository.deleteAll()
        assertThat(deploymentRepository.findAll()).isEmpty()
    }
}
