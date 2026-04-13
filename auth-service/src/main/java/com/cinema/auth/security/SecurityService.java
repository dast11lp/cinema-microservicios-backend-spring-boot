package com.cinema.auth.security;

import com.cinema.auth.entities.MyUser;
import com.cinema.auth.entities.Role;
import com.cinema.auth.services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service("securityService")
public class SecurityService {

    @Autowired
    private MyUserService myUserService;
    
    Authentication authentication;

        public boolean hasUser(Long idUser) throws Exception{

            System.err.println("hasUser llamado con id: " + idUser);

        MyUser user = this.myUserService.findById(idUser);
        
        Collection<GrantedAuthority>  authoritiesUser = new ArrayList<GrantedAuthority>();
        
        for (Role role: user.getRoles()) {
			authoritiesUser.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		
		Collection<GrantedAuthority> authoritiesContext = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

            System.out.println("Roles del usuario DB: " + authoritiesUser);
            System.out.println("Roles del contexto: " + authoritiesContext);

		for(GrantedAuthority authority: authoritiesUser) {
			if(authoritiesContext.contains(authority)) {
				return true;
			}
//			break;
		}


        

        return false;  
    }
}
