package pk.edu.pl.Dogly_backend.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pk.edu.pl.Dogly_backend.security.role.Group;
import pk.edu.pl.Dogly_backend.user.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

  private User user;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getGrantedAuthorities();
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  private List<GrantedAuthority> getGrantedAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>(user.getRoles().size());
    for (Group userGroup : user.getRoles()) {
      authorities.add(new SimpleGrantedAuthority("ROLE_" + userGroup.getRole().name()));
    }
    return authorities;
  }
}
