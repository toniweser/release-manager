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

    @Test
    fun `should deploy and return correct system version number`() {
        var systemVersionService = deploymentResource.deploy(DeploymentInputDto("Service A", 1))
        assertThat(systemVersionService).isEqualTo(1)

        systemVersionService = deploymentResource.deploy(DeploymentInputDto("Service B", 1))
        assertThat(systemVersionService).isEqualTo(2)

        systemVersionService = deploymentResource.deploy(DeploymentInputDto("Service A", 2))
        assertThat(systemVersionService).isEqualTo(3)

        systemVersionService = deploymentResource.deploy(DeploymentInputDto("Service B", 1))
        assertThat(systemVersionService).isEqualTo(3)

        systemVersionService = deploymentResource.deploy(DeploymentInputDto("Service A", 1))
        assertThat(systemVersionService).isEqualTo(3)
    }
}
