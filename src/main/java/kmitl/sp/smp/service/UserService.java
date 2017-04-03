package kmitl.sp.smp.service;

import kmitl.sp.smp.entity.User;

/**
 * Created by Jo on 4/3/2017.
 */
public interface UserService {
    User getUserByFacebookId(String facebookId);

    User getUserByUserId(String userId);

    User createNewUser();

    User createNewUser(String facebookId);
}
