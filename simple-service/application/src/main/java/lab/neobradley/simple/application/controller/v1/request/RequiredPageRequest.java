package lab.neobradley.simple.application.controller.v1.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Optional;

@ApiModel("分页请求")
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class RequiredPageRequest {

    @Min(1)
    @ApiModelProperty(value = "当前页数", example = "1", required = true)
    private Integer page;

    @ApiModelProperty(value = "分页条数, 最大 1000", example = "50", required = true)
    @Min(1)
    @Max(1000)
    private Integer size;

    public Integer getPage() {
        return Optional.ofNullable(page).orElse(1);
    }

    public Integer getSize() {
        return Optional.ofNullable(size).orElse(50);
    }
}
