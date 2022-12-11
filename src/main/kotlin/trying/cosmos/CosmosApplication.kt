package trying.cosmos

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableAsync

@EnableJpaAuditing
@EnableAsync
@ConfigurationPropertiesScan
@SpringBootApplication
class CosmosApplication

fun main(args: Array<String>) {
	runApplication<CosmosApplication>(*args)
}
