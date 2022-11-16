package lab.neobradley.simple.application.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "enabled", prefix = "swagger", havingValue = "true")
public class SwaggerConfig {
//
//    private static final String[] PATHS = {"/**"};
//    private static final String[] PACKAGED_TO_SCAN = {"lab.neobradley.simple.application.controller.v1.*"};
//
//    @Bean
//    public GlobalOpenApiCustomizer orderGlobalOpenApiCustomizer() {
//        return openApi -> {
//            if (openApi.getTags() != null) {
//                openApi.getTags().forEach(tag -> {
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("x-order", RandomUtils.nextInt(0, 100));
//                    tag.setExtensions(map);
//                });
//            }
//
//        };
//    }
//
//    @Bean
//    public GroupedOpenApi userApi() {
//        return GroupedOpenApi.builder()
//            .group("default")
//            .pathsToMatch(PATHS)
//            .addOperationCustomizer((operation, handlerMethod) -> operation
//                .addParametersItem(new HeaderParameter().name("groupCode")
//                    .example("测试")
//                    .description("集团code")
//                    .schema(new StringSchema()._default("BR").name("groupCode").description("集团code"))))
//            .packagesToScan(PACKAGED_TO_SCAN).build();
//    }
//
//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//            .info(new Info()
//                .title("API DOC")
//                .version("1.0")
//                .description("API DOC BY NEOBRADLEY"));
//    }

}
