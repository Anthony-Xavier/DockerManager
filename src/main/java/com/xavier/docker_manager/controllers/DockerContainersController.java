package com.xavier.docker_manager.controllers;

import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.xavier.docker_manager.services.DockerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/containers")
public class DockerContainersController {

    private final DockerService dockerService;

    public DockerContainersController(DockerService dockerService) {
        this.dockerService = dockerService;
    }

    @GetMapping("/filter")
    private List<Container> containerImages(@RequestParam(required = false, defaultValue = "true") boolean showAll){
        return dockerService.listContainers(showAll);
    }

    @PostMapping("/start/{id}")
    private void startContainer(@PathVariable("id") String Id) {
        dockerService.startContainer(Id);
    }

    @DeleteMapping("/{id}")
    private void deleteContainer(@PathVariable("id") String Id) {
        dockerService.deleteContainer(Id);
    }

    @PostMapping("")
    public void createContainer(@RequestParam String imageName) {
        dockerService.createContainer(imageName);
    }
}
