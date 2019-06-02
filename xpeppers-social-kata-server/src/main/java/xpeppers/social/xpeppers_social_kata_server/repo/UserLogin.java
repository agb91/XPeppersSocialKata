package xpeppers.social.xpeppers_social_kata_server.repo;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserLogin {

	@Id
	@GeneratedValue
	private Long id;
	private String user = "";
	private String password = "";
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserLogin(Long id, String user, String password) {
		super();
		this.id = id;
		this.user = user;
		this.password = password;
	}
	public UserLogin() {
		super();
	}
	
	
	
	
	
	
	
	

}
