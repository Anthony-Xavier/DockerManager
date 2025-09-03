package com.xavier.docker_manager.config;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DockerClientConfig {

    @Value("${docker.socket}")
    private String dockerSoket;
    @Bean
    public DockerClient buildDockerClient(){
        DefaultDockerClientConfig.Builder dockerClientConfigBuilder = DefaultDockerClientConfig
                .createDefaultConfigBuilder();

        if(this.dockerSoket != null && this.dockerSoket.startsWith("unix://")){
            dockerClientConfigBuilder.withDockerHost(dockerSoket)
                    .withDockerTlsVerify(false);
        }

        DefaultDockerClientConfig dockerClientConfig = dockerClientConfigBuilder
                .build();

        ApacheDockerHttpClient dockerHttpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(dockerClientConfig.getDockerHost()).build();

        return DockerClientBuilder.getInstance(dockerClientConfig)
                .withDockerHttpClient(dockerHttpClient)
                .build();
    }
}
