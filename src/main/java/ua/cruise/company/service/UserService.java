package ua.cruise.company.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cruise.company.entity.User;
import ua.cruise.company.entity.UserRole;
import ua.cruise.company.repository.UserRepository;
import ua.cruise.company.service.exception.NoEntityFoundException;
import ua.cruise.company.service.exception.NonUniqueObjectException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private static final Logger LOGGER= LoggerFactory.getLogger(UserService.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) throws NonUniqueObjectException {
        if(user.getRole() == null)
            user.setRole(UserRole.ROLE_TOURIST);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        try{
            userRepository.save(user);
        }catch (DataIntegrityViolationException exception){
            LOGGER.error("User wasn't saved {}, {}", user, exception.getMessage());
            throw new NonUniqueObjectException("User with such email already exists");
        }

        return true;
    }

    @Transactional
    public boolean updateUserRole(String email, UserRole newRole) throws NoEntityFoundException {
        User userFromDB = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoEntityFoundException("User with provided email was not found: " + email));

        userFromDB.setRole(newRole);
        userRepository.save(userFromDB);
        return true;
    }

}
