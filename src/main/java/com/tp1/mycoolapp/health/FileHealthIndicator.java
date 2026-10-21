package com.tp1.mycoolapp.health;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class FileHealthIndicator implements HealthIndicator {

	private final Path file;

	public FileHealthIndicator(@Value("${app.health.file}") String file) {
		this.file = Path.of(file).toAbsolutePath();
	}

	@Override
	public Health health() {
		if (Files.exists(this.file)) {
			return Health.up().withDetail("file", this.file.toString()).build();
		}
		return Health.down()
			.withDetail("file", this.file.toString())
			.withDetail("reason", "file not found")
			.build();
	}

}
