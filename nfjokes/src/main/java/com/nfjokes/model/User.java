package com.nfjokes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends BaseEntity implements UserDetails, Serializable {
    private static final long serialVersionUID = 2201274762053296980L;

    @Size(min = 2, max = 15)
    private String name;
    //@Size(min = 2, max = 15)
    private String password;
    @NotEmpty
    @Email
    @Column(unique=true)
    private String email;
    @Lob
    private byte[] image;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Joke> jokes = new ArrayList<>();

    private boolean nonLocked;
    private boolean enabled;
    private UserRole role;

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        final Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        if(role != null) {
            grantedAuthorities.add(new GrantedAuthority() {
				private static final long serialVersionUID = -395586327825919568L;
				@Override
				public String getAuthority() {
					return role.getAuthority();
				}
			});
        }
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return nonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    
    public String userImage() {
    	return Base64.getEncoder().encodeToString(image);
    }
}
