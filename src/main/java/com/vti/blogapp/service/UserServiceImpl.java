package com.vti.blogapp.service;

import com.vti.blogapp.dto.UserDto;
import com.vti.blogapp.entity.Role;
import com.vti.blogapp.form.UserCreateForm;
import com.vti.blogapp.mapper.UserMapper;
import com.vti.blogapp.repository.RoleRepository;
import com.vti.blogapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserDto create(UserCreateForm form) {
        var user = UserMapper.map(form);
        var encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        var role = roleRepository.findByType(Role.Type.USER);
        user.setRoles(Set.of(role));
        var savedUser = userRepository.save(user);
        return UserMapper.map(savedUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException(username);
        }
        var authoritites = new ArrayList<GrantedAuthority>();
        for (var role : user.getRoles()) {
            var authority = new SimpleGrantedAuthority(role.getType().toString());
            authoritites.add(authority);
        }
        return new User(user.getUsername(), user.getPassword(), authoritites);
    }
}
