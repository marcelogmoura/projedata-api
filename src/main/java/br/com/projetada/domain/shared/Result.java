package br.com.projetada.domain.shared;
import lombok.Getter;

@Getter
public class Result<T> {
    private final T value;
    private final String error;
    private final boolean isSuccess;

    private Result(T value, String error, boolean isSuccess) {
        this.value = value;
        this.error = error;
        this.isSuccess = isSuccess;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(value, null, true);
    }

    public static <T> Result<T> failure(String error) {
        return new Result<>(null, error, false);
    }
}