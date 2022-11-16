package lab.neobradley.simple.application.controller.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lab.neobradley.simple.application.dto.response.ApiResult;
import lab.neobradley.simple.application.dto.response.StatusCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "HelloWorld", description = "API 測試")
@RequestMapping("/v1/hello-world")
@CrossOrigin(allowCredentials = "true", value = "http://localhost")
@RestController
public class HelloWorldController {

    @Operation(summary = "Hello World")
    @GetMapping("/")
    public ApiResult helloWorld() {
        return new ApiResult(StatusCode.SUCCESS, "Hello World");
    }

}
