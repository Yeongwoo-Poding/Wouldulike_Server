package trying.cosmos.global.configuration

import org.springframework.boot.web.servlet.server.Encoding
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import java.util.*

@Configuration
class MessageConfig {

    @Bean
    fun messageSource(): MessageSource? {
        Locale.setDefault(Locale.KOREAN)
        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setBasename("classpath:/messages/messages")
        messageSource.setDefaultEncoding(Encoding.DEFAULT_CHARSET.toString())
        messageSource.setDefaultLocale(Locale.getDefault())
        messageSource.setCacheSeconds(600)
        return messageSource
    }
}