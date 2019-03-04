package ua.ita.smartcarservice.service.impl;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.testng.Assert;
import org.testng.IObjectFactory;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.service.UserPrinciple;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;


@PrepareForTest(UserDetailsServiceImpl.class)
public class UserDetailsServiceImplTest {

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @Test
    public void testLoadUserByUsername(String username) {

        UserRepository userRepository = PowerMockito.mock(UserRepository.class);
        UserPrinciple userPrinciple = PowerMockito.mock(UserPrinciple.class);
        UserEntity userEntity = userRepository.findByUsername("admin").get();
        PowerMockito.when(userRepository.findByUsername("admin")).thenReturn(java.util.Optional.of(userEntity));

        Object actual;
        Object expected;

        expected = new UserEntity();
        actual = userRepository.findByUsername("admin");

        Assert.assertEquals(actual, expected);


    }
}