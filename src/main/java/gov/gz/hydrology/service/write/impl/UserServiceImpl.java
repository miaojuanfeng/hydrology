package gov.gz.hydrology.service.write.impl;

import gov.gz.hydrology.entity.write.User;
import gov.gz.hydrology.mapper.write.UserDao;
import gov.gz.hydrology.service.write.StationService;
import gov.gz.hydrology.service.write.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	public User selectByPrimaryKey(String userId) {
		return userDao.selectByPrimaryKey(userId);
	}

	@Override
	public int insertSelective(User user) {
		return userDao.insertSelective(user);
	}

	@Override
	public int updateSelective(User user) {
		return userDao.updateSelective(user);
	}

}
