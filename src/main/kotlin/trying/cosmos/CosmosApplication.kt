package trying.cosmos

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class CosmosApplication

fun main(args: Array<String>) {
	runApplication<CosmosApplication>(*args)
}
