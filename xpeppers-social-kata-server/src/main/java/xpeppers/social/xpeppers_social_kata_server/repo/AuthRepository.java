package xpeppers.social.xpeppers_social_kata_server.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends CrudRepository<UserLogin, Long>{
	
	//List<UserLoginInfo> findByUser(String user);

}
