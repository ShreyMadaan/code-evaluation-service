package com.interviewprep.codeevaluationservice.util;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.Volume;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.LogContainerResultCallback;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DockerRunner {
    private final DockerClient dockerClient;

    public DockerRunner() {
        this.dockerClient = DockerClientBuilder.getInstance().build();
    }

    public String runJavaCode(String codeFilePath){
        File codeFile = new File(codeFilePath);
        File sourceDir = codeFile.getParentFile();
        if (sourceDir == null) {
            throw new IllegalArgumentException("Invalid code file path: " + codeFilePath);
        }

        Volume volume = new Volume("/app");
        HostConfig hostConfig = HostConfig.newHostConfig()
                .withBinds(new Bind(sourceDir.getAbsolutePath(), volume));

        CreateContainerResponse container = dockerClient.createContainerCmd("openjdk:17")
                .withWorkingDir("/app")
                .withHostConfig(hostConfig)
                .withCmd("bash", "-c", "javac Main.java && java Main")
                .exec();

        String containerId = container.getId();
        StringBuilder output = new StringBuilder();

        try {
            dockerClient.startContainerCmd(containerId).exec();

            try (LogContainerResultCallback callback = new LogContainerResultCallback() {
                @Override
                public void onNext(Frame frame) {
                    output.append(new String(frame.getPayload(), StandardCharsets.UTF_8));
                    super.onNext(frame);
                }
            }) {
                dockerClient.logContainerCmd(containerId)
                        .withStdOut(true)
                        .withStdErr(true)
                        .withFollowStream(true)
                        .exec(callback)
                        .awaitCompletion();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted while waiting for container logs", e);
            } catch (IOException e) {
                throw new RuntimeException("Failed to read container logs", e);
            }

            return output.toString();
        } finally {
            dockerClient.removeContainerCmd(containerId).withForce(true).exec();
        }
    }

    public String runPythonCode(String codeFilePath) {
        File codeFile = new File(codeFilePath);
        File sourceDir = codeFile.getParentFile();
        if (sourceDir == null) {
            throw new IllegalArgumentException("Invalid code file path: " + codeFilePath);
        }

        Volume volume = new Volume("/app");
        HostConfig hostConfig = HostConfig.newHostConfig()
                .withBinds(new Bind(sourceDir.getAbsolutePath(), volume));

        CreateContainerResponse container = dockerClient.createContainerCmd("python:3.11")
                .withWorkingDir("/app")
                .withHostConfig(hostConfig)
                .withCmd("python", codeFile.getName())
                .exec();

        String containerId = container.getId();
        StringBuilder output = new StringBuilder();

        try {
            dockerClient.startContainerCmd(containerId).exec();

            try (LogContainerResultCallback callback = new LogContainerResultCallback() {
                @Override
                public void onNext(Frame frame) {
                    output.append(new String(frame.getPayload(), StandardCharsets.UTF_8));
                    super.onNext(frame);
                }
            }) {
                dockerClient.logContainerCmd(containerId)
                        .withStdOut(true)
                        .withStdErr(true)
                        .withFollowStream(true)
                        .exec(callback)
                        .awaitCompletion();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted while waiting for container logs", e);
            } catch (IOException e) {
                throw new RuntimeException("Failed to read container logs", e);
            }

            return output.toString();
        } finally {
            dockerClient.removeContainerCmd(containerId).withForce(true).exec();
        }
    }

    public String runCppCode(String codeFilePath) {
        File codeFile = new File(codeFilePath);
        File sourceDir = codeFile.getParentFile();
        if (sourceDir == null) {
            throw new IllegalArgumentException("Invalid code file path: " + codeFilePath);
        }

        Volume volume = new Volume("/app");
        HostConfig hostConfig = HostConfig.newHostConfig()
                .withBinds(new Bind(sourceDir.getAbsolutePath(), volume));

        CreateContainerResponse container = dockerClient.createContainerCmd("gcc:latest")
                .withWorkingDir("/app")
                .withHostConfig(hostConfig)
                .withCmd("bash", "-c", "g++ " + codeFile.getName() + " -o /app/a.out && /app/a.out")
                .exec();

        String containerId = container.getId();
        StringBuilder output = new StringBuilder();

        try {
            dockerClient.startContainerCmd(containerId).exec();

            try (LogContainerResultCallback callback = new LogContainerResultCallback() {
                @Override
                public void onNext(Frame frame) {
                    output.append(new String(frame.getPayload(), StandardCharsets.UTF_8));
                    super.onNext(frame);
                }
            }) {
                dockerClient.logContainerCmd(containerId)
                        .withStdOut(true)
                        .withStdErr(true)
                        .withFollowStream(true)
                        .exec(callback)
                        .awaitCompletion();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted while waiting for container logs", e);
            } catch (IOException e) {
                throw new RuntimeException("Failed to read container logs", e);
            }

            return output.toString();
        } finally {
            dockerClient.removeContainerCmd(containerId).withForce(true).exec();
        }
    }
}
