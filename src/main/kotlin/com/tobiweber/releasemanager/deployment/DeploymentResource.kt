package com.tobiweber.releasemanager.deployment

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
class DeploymentResource(
    private val deploymentService: DeploymentService,
) {
    /**
     * Creates a new deployment and returns the system version number.
     * @param deploymentDto The input DTO containing the service name and version number
     * @return The system version number
     */
    @PostMapping("/deploy")
    fun deploy(@RequestBody deploymentDto: DeploymentDto): Int =
        deploymentService.deploy(deploymentDto)

    /**
     * Returns all latest services for the given system version sorted by service name ASC.
     * @param systemVersion The system version number
     * @return The list of the latest services
     */
    @GetMapping("/services")
    fun getServices(@RequestParam(required = true) systemVersion: Int): List<DeploymentDto> =
        deploymentService.getServices(systemVersion)
}
