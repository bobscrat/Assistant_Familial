// package fr.acdo.configuration;
//
// import javax.transaction.Transactional;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import
// org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import
// org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
// import org.springframework.security.core.authority.AuthorityUtils;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import
// org.springframework.security.core.userdetails.UsernameNotFoundException;
//
// import fr.acdo.repository.UserRepository;
//
// public class GlobalSecurity extends GlobalAuthenticationConfigurerAdapter {
//
// @Autowired
// private UserRepository repo;
//
// @Override
// public void init(AuthenticationManagerBuilder auth) throws Exception {
// auth.userDetailsService(userDetailsService());
//
// }
//
// @Bean
// UserDetailsService userDetailsService() {
// return new UserDetailsService() {
//
// @Override
// @Transactional
// public UserDetails loadUserByUsername(String email) throws
// UsernameNotFoundException {
// fr.acdo.domain.User monUser = repo.findByEmail(email);
//
// if (monUser != null) {
//
// return new User(monUser.getEmail(), monUser.getPassword(), true, true, true,
// true,
// AuthorityUtils.createAuthorityList("USER"));
// } else {
// throw new UsernameNotFoundException(email + " non trouvé.");
// }
// }
// };
// }
// }
