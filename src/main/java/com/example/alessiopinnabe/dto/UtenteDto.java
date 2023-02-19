package com.example.alessiopinnabe.dto;

import com.example.alessiopinnabe.dto.core.ObjectDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UtenteDto extends ObjectDTO implements UserDetails {
    private String anagrafica;
    @NotNull private String email;
    private String username;
    private String provider;
    private String photoUrl;
    private DominioDto tipoUtente;
    private Integer totAcquistiProdotti;
    private Integer totAcquistiEventi;
    private List<TokenDto> tokens;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean CredentialsNonExpired;
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
