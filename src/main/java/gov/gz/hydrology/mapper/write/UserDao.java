package gov.gz.hydrology.mapper.write;

import gov.gz.hydrology.entity.write.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
	User selectByPrimaryKey(@Param("userId") String userId);
	int insertSelective(User user);
	int updateSelective(User user);
}
