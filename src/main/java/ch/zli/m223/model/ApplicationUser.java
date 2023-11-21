package ch.zli.m223.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@Entity
@Table(name="APPLICATION_USER")
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(readOnly = true)
    private Long id;

    @NotBlank(message="Last name may not be blank")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message="First name may not be blank")
    @Column(nullable = false)
    private String lastName;

    @NotBlank(message="Email may not be blank")
    @Column(nullable = false, unique = true)
    public String email;

    @NotBlank(message="Username may not be blank")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message="Password may not be blank")
    @Column(nullable = false)
    public String password;

    @JsonIgnoreProperties("applicationUsers")
    @ManyToOne(optional = true)
    @Fetch(FetchMode.JOIN)
    private Role role;

    @JsonIgnoreProperties("participants")
    @ManyToMany(mappedBy = "participants")
    @Fetch(FetchMode.JOIN)
    Set<Booking> bookings;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}

	
}
