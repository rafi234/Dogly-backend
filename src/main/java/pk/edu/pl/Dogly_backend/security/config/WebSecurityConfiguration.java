package pk.edu.pl.Dogly_backend.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pk.edu.pl.Dogly_backend.security.UserDetailsImpl;
import pk.edu.pl.Dogly_backend.security.filter.CustomAuthorizationFilter;
import pk.edu.pl.Dogly_backend.user.UserRepository;

;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
  private final UserRepository userRepository;
  private final CustomAuthorizationFilter authorizationFilter;


  @Autowired
  public WebSecurityConfiguration(
    UserRepository userRepository,
    @Lazy CustomAuthorizationFilter authorizationFilter) {
    this.userRepository = userRepository;
    this.authorizationFilter = authorizationFilter;
  }

  @Bean
  protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors(Customizer.withDefaults());

    http.csrf(AbstractHttpConfigurer::disable);

    http.authorizeHttpRequests(auth -> auth
      .anyRequest().permitAll()
    );

    http.sessionManagement(session ->
      session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    );

    http.addFilterBefore(
      authorizationFilter,
      UsernamePasswordAuthenticationFilter.class
    );

    return http.build();
  }

  @Bean
  public UserDetailsService detailsService() {
    return username -> new UserDetailsImpl(userRepository.findByEmailIgnoreCase(username)
      .orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not exist!")));

  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(detailsService());
    authProvider.setPasswordEncoder(getEncoder());
    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
      .userDetailsService(detailsService())
      .passwordEncoder(getEncoder())
      .and().build();
  }

  @Bean
  public PasswordEncoder getEncoder() {
    return new BCryptPasswordEncoder(13);
  }
}
