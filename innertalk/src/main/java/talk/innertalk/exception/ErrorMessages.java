package talk.innertalk.exception;

public final class ErrorMessages {

    public static final String NO_SUCH_MEMBER = "회원을 찾을 수 없습니다.";
    public static final String DUPLICATED_EMAIL = "이미 사용중인 이메일입니다.";
    public static final String DUPLICATED_NICKNAME = "이미 사용중인 닉네임 입니다.";
    public static final String NOT_EQUAL_PASSWORD ="비밀번호가 다릅니다.";
    public static final String NO_SUCH_POST = "게시글을 찾을 수 없습니다.";
    public static final String NO_SUCH_COMMENT = "댓글을 찾을 수 없습니다.";
    public static final String DUPLICATED_BOOKMARK="이미 북마크한 게시글입니다.";
    public static final String EXISTS_LIKE="이미 공감한 게시글입니다.";
    public static final String EXISTS_REPORT_POST="이미 신고한 게시글입니다.";
    public static final String No_SUCH_REPORT = "신고를 찾을 수 없습니다.";


    private ErrorMessages() {
        //생성자 private으로 막아서 객체 생성 못하게 함
    }

}
