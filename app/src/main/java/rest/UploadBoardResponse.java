package rest;


/**
 * 회원가입/로그인의 response 값들
 */
public class UploadBoardResponse {

    private String couple_yesno;
    private String[] category;
    private String error;
    private String error_msg;

    public String getCouple_yesno() {
        return couple_yesno;
    }

    public void setCouple_yesno(String couple_yesno) {
        this.couple_yesno = couple_yesno;
    }

    public String[] getCategory() {
        return category;
    }

    public void setCategory(String[] category) {
        this.category = category;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

}
