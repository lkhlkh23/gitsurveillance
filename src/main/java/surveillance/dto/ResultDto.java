package surveillance.dto;

public class ResultDto<T> {

    private T body;
    private boolean success;

    public ResultDto() {

    }

    public ResultDto(T body, boolean success) {
        this.body = body;
        this.success = success;
    }

    public T getObject() {
        return body;
    }

    public void setObject(T body) {
        this.body = body;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
