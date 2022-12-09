package trying.cosmos.global.email

enum class EmailTemplate(

    val templateName: String,

    val subject: String,

    val body: String

) {

    CERTIFICATION_CODE(
        "code",
        "이메일 인증을 완료해주세요",
        "계정 보안을 위하 이메일 주소를 인증해주세요. 다음 인증코드를 10분 이내에 입력해주세요."
    ),
    RESET_PASSWORD(
        "code",
        "비밀번호가 변경되었습니다.",
        "임시 비밀번호가 발급되었습니다. 보안을 위해 로그인 후 비밀번호를 변경해주세요."
    )
}