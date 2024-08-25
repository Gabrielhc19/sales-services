import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/v3/api-docs")
                .allowedOrigins("https://editor.swagger.io")  // Permitir solicitudes desde Swagger Editor en línea
                .allowedMethods("GET")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
