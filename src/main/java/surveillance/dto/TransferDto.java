package surveillance.dto;

public class TransferDto<T> {

    private T body;
    private boolean success;

    public TransferDto() {

    }

    public TransferDto(T body, boolean success) {
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
