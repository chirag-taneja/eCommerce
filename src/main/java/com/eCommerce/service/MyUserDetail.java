package com.eCommerce.service;

import com.eCommerce.entity.Role;
import com.eCommerce.entity.User;
import com.eCommerce.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class MyUserDetail implements UserDetailsService {
    UserRepo userRepo;
    @Autowired
    public MyUserDetail(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username).get();
        if (user!=null)
        {
            String roles[]=new String[user.getRole().size()];
            int i=0;
            for (Role role:user.getRole()
            ) {
                roles[i]=role.getRoleName();
                i++;
            }
            UserDetails userDetails= org.springframework.security.core.userdetails.User.builder().username(user.getUserName()).password(user.getPassword()).roles(roles)
                    .build();
            return userDetails;
        }
        else {
            throw new UsernameNotFoundException("user name not found");
        }
    }
}
