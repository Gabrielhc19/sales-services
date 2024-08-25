import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .antMatchers("/", "/v3/api-docs/**", "/swagger-ui/**").permitAll()  // Permitir acceso sin autenticación a estas rutas
                .anyRequest().authenticated()  // Todas las demás rutas requieren autenticación
            )
            .csrf().disable()
            .httpBasic();  // Autenticación básica si es necesario
        return http.build();
    }
}
