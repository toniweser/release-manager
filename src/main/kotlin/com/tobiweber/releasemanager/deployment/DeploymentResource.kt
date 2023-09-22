package com.tobiweber.releasemanager.deployment

import com.tobiweber.releasemanager.deployment.DeploymentResource.Companion.PATH
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = [PATH], produces = [MediaType.APPLICATION_JSON_VALUE])
class DeploymentResource(
    private val deploymentService: DeploymentService,
) {
    /**
     * Creates a new deployment and returns the system version number.
     * @param deploymentInputDto The input DTO containing the service name and version number
     * @return The system version number
     */
    @PostMapping
    fun deploy(@RequestBody deploymentInputDto: DeploymentInputDto): Int =
        deploymentService.deploy(deploymentInputDto)

    companion object {
        const val PATH = "/deploy"
    }
}
