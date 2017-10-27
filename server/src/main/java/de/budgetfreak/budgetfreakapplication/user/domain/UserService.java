package de.budgetfreak.budgetfreakapplication.user.domain;

import de.budgetfreak.budgetfreakapplication.user.rest.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Service
public class UserService  {

    public List<User> getAllUser() {
        throw new NotImplementedException();
    }

    public User createUser(String name){
        throw new NotImplementedException();
    }
}
