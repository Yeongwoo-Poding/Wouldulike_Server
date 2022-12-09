package trying.cosmos.global.email

open class MockMailSender: EmailSender {

    override fun send(template: EmailTemplate, to: String, model: Map<String, String>) {
        return
    }
}