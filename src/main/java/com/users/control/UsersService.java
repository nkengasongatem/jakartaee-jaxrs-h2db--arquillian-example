package com.users.control;

import com.users.entity.User;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nkengasong
 */
@Stateless
public class UsersService {
    
    @PersistenceContext
    private EntityManager em;
    
    // query all users
    public List getAll() {
        return em.createQuery("FROM User u", User.class).getResultList();
    }
    
    // query a given user by id
    public Optional<User> getOne(Long id) {
        User found = em.find(User.class, id);
        return found != null ? Optional.of(found) : Optional.empty();
    }
    
    // add a new user
    public void add(User user) {
       em.persist(user);
    }
    
    // update an existing user
    public boolean update(User user) {
        User found = em.find(User.class, user.getId());
        if (found != null) {
            user.setRegisteredDate(found.getRegisteredDate());
            em.merge(user);
            return true;
        }
        return false;
    }
}
