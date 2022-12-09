package trying.cosmos.global.email

interface EmailSender {

    fun send(template: EmailTemplate, to: String, model: Map<String, String>)
}