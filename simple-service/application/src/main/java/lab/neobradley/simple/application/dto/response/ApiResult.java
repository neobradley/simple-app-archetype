package lab.neobradley.simple.application.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

// @ApiModel("统一返回格式")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = -172123143300508639L;

    @Schema(description = "状态码", required = true)
    private Integer status;

    @Schema(description = "错误描述", required = true)
    private String message;

    @Schema(description = "返回数据")
    private T data;

    public ApiResult(StatusCode statusCode) {
        this.status = statusCode.getStatus();
        this.message = statusCode.getMessage();
        if (StatusCode.SUCCESS.getStatus() == statusCode.getStatus()) {
            this.data = (T) "ok";
        }
    }

    public ApiResult(StatusCode statusCode, T resultObject) {
        this(statusCode);
        data = resultObject;
    }

    public ApiResult(StatusCode statusCode, String message) {
        this(statusCode);
        this.message = message;
    }
}
