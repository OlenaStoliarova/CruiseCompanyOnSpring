package ua.training.cruise_company_on_spring.service;

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
import ua.training.cruise_company_on_spring.entity.User;
import ua.training.cruise_company_on_spring.entity.UserRole;
import ua.training.cruise_company_on_spring.repository.UserRepository;

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

    public boolean saveUser(User user) {
        if(user.getRole() == null)
            user.setRole(UserRole.ROLE_TOURIST);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        try{
            userRepository.save(user);
        }catch (DataIntegrityViolationException exception){
            LOGGER.error("User wasn't saved {}, {}", user, exception.getMessage());
            return false;
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
