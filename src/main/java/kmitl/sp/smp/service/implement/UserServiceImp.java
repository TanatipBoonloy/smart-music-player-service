package kmitl.sp.smp.service.implement;

import kmitl.sp.smp.entity.User;
import kmitl.sp.smp.repository.UserRepository;
import kmitl.sp.smp.service.UserService;
import kmitl.sp.smp.util.ThrowExceptionUtil;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Jo on 4/3/2017.
 */
@Service
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;

    @Inject
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByFacebookId(String facebookId) {
        return getByFacebookId(facebookId);
    }

    @Override
    public User getUserByUserId(String userId) {
        return getByUserId(userId);
    }

    @Override
    public User createNewUser() {
        User user = createEmptyUser();
        String hashId = hashStringWithSHA512("" + user.getId());

        user.setUserId(hashId);
        return save(user);
    }

    @Override
    public User createNewUser(String facebookId) {
        User user = createEmptyUser();
        String hashId = hashStringWithSHA512("" + user.getId());

        user.setUserId(hashId);
        user.setFacebookId(facebookId);
        return save(user);
    }

    private User createEmptyUser() {
        User user = new User();
        return save(user);
    }

    private User getByFacebookId(String facebookId) {
        return this.userRepository.findByFacebookId(facebookId);
    }

    private User getByUserId(String userId) {
        return this.userRepository.findByUserId(userId);
    }
    private User save(User user) {
        return userRepository.save(user);
    }

    private String hashStringWithSHA512(String word) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(word.getBytes());
            byte data[] = md.digest();

            StringBuffer hex = new StringBuffer();

            for (int i = 0; i < data.length; i++) {
                hex.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
            }

            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new InternalError("Hash Error");
        }
    }
}
