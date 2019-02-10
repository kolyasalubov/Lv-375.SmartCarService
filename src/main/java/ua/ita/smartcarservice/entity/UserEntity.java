package ua.ita.smartcarservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import ua.ita.smartcarservice.entity.technicalservice.UserTechnicalService;
import ua.ita.smartcarservice.entity.booking.WorkTime;
import ua.ita.smartcarservice.entity.technicalservice.WorkersSkill;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 100, nullable = false, unique = true)
	private String username;
	@Column(length = 100, nullable = false)
	private String password;
	@Column(length = 30, nullable = true)
	private String email;
	@Column(length = 50, nullable = false)
	private String fullName;
	@Column(length = 15, unique = true)
	private String numberPhone;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles = new HashSet<RoleEntity>();

	@JsonIgnore
	@OneToOne(mappedBy = "userId")
	private UserTechnicalService userTechnicalService;

	@JsonIgnore
	@OneToOne(mappedBy = "workerId", cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private WorkersSkill workersSkill;

	@JsonIgnore
	@OneToMany(mappedBy = "worker")
	Set<WorkTime> workTimes;
	
	public UserEntity() {
		
	}


	    public UserEntity(String username, String password, String email, String fullName, String numberPhone) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
		this.numberPhone = numberPhone;
	}







	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUserName() {
		return username;
	}


	public void setUserName(String userName) {
		this.username = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Set<RoleEntity> getRoles() {
		return roles;
	}


	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
