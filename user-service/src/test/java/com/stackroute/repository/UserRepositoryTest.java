package com.stackroute.repository;


import com.stackroute.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    User user;

    @Before
    public void setUp()
    {
        user = new User();
        user.setAge(10);
        user.setFirstName("John");
        user.setId(101);
        user.setLastName("Jenny");

    }

    @After
    public void tearDown(){

        userRepository.deleteAll();
    }


    @Test
    public void testSaveUser(){
     userRepository.save(user);
     User fetchUser = userRepository.findById(user.getId()).get();
        Assert.assertEquals(101,fetchUser.getId());

    }

    @Test
    public void testSaveUserFailure(){
        User testUser = new User("Harry","john",34,"Harry123",201);
        userRepository.save(user);
        User fetchUser = userRepository.findById(user.getId()).get();
        Assert.assertNotSame(testUser,user);
    }

    @Test
    public void testGetAllUser(){
        User u = new User("Johny","Jenny",10,"Johny12",102);
        User u1 = new User("Harry","Jenny",11,"Harry12",103);
        userRepository.save(u);
        userRepository.save(u1);

        List<User> list = userRepository.findAll();
        Assert.assertEquals("Johny",list.get(0).getFirstName());




    }


}
