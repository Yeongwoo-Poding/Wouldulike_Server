package trying.cosmos.global.email

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.mail.javamail.JavaMailSender
import org.thymeleaf.spring5.SpringTemplateEngine
import trying.cosmos.global.configuration.ImageProperties

@Configuration
class EmailConfig(

    private val emailSender: JavaMailSender,

    private val templateEngine: SpringTemplateEngine,

    private val imageProperties: ImageProperties,

) {

    @Bean
    @Profile("local")
    fun emailSender(): EmailSender = GmailSender(emailSender, templateEngine, imageProperties)

    @Bean
    @Profile("test")
    fun mockMailSender(): EmailSender = MockMailSender()
}