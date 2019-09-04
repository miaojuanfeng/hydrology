package gov.gz.hydrology.service.write;

import gov.gz.hydrology.entity.write.User;

public interface UserService {
	User selectByPrimaryKey(String userId);
	int insertSelective(User user);
}
