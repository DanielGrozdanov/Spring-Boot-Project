package online.store.onlineBookStore.services;

import online.store.onlineBookStore.models.entities.User;
import online.store.onlineBookStore.repositories.UserRepository;
import online.store.onlineBookStore.user.OnlineBookStoreUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class OnlineBookStoreDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public OnlineBookStoreDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = this.userRepository.findByUsername(username)
               .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));

       if (user.getRole().getName().name().equals("USER")){
           return userRoleMap(user);
       }else {
          return adminRoleMap(user);
       }
    }

    public UserDetails userRoleMap(User user){
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new OnlineBookStoreUserDetails(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                grantedAuthorities
        );
    }
    public UserDetails adminRoleMap(User user) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new OnlineBookStoreUserDetails(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                grantedAuthorities
        );
    }
}
