package trying.cosmos.global.email

import org.springframework.core.io.ClassPathResource
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.scheduling.annotation.Async
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine
import trying.cosmos.global.configuration.ImageProperties
import java.io.IOException
import javax.activation.DataHandler
import javax.mail.BodyPart
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

open class GmailSender(

    private val emailSender: JavaMailSender,

    private val templateEngine: SpringTemplateEngine,

    private val imageProperties: ImageProperties

): EmailSender {

    @Async
    override fun send(template: EmailTemplate, to: String, model: Map<String, String>) {
        try {
            val message: MimeMessage = emailSender.createMimeMessage()
            message.subject = template.subject
            message.setFrom("[우주라이크] Trying <wouldulikeofficial@gmail.com>")
            message.addRecipient(Message.RecipientType.TO, InternetAddress(to))

            val multipart = MimeMultipart("related")
            val messageBodyPart: BodyPart = MimeBodyPart()
            val context = Context()
            context.setVariable("body", template.body)
            model.forEach { (name: String?, value: String?) ->
                context.setVariable(name, value)
            }

            val html: String = templateEngine.process("${template.templateName}.html", context)
            messageBodyPart.setContent(html, "text/html;charset=utf-8")
            multipart.addBodyPart(messageBodyPart)

            val imageBodyPart: BodyPart = MimeBodyPart()
            val resource = ClassPathResource(imageProperties.logo)
            imageBodyPart.dataHandler = DataHandler(resource.url)
            imageBodyPart.setHeader("Content-ID", "<image>")
            multipart.addBodyPart(imageBodyPart)
            message.setContent(multipart)
            emailSender.send(message)
        } catch (e: MessagingException) {
            throw RuntimeException("메일 전송 실패", e)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
}