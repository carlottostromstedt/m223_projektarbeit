package ch.zli.m223.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.Fetch;

import java.util.Set;

@Entity
@Table(name="NOCCO")
public class Nocco {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long id;

  @NotBlank(message="Name may not be blank")
  @Column(nullable = false)
  private String name;

  @NotNull(message="Caffeine may not be null")
  @Column(nullable = false)
  private int caffeine;
  
  @JsonIgnoreProperties({ "participants", "bookings" })
  @OneToMany(mappedBy = "nocco")
  @Fetch(FetchMode.JOIN)
  private Set<Booking> bookings;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCaffeine() {
    return caffeine;
  }

  public void setCaffeine(int caffeine) {
    this.caffeine = caffeine;
  }

  public Set<Booking> getBookings() {
    return bookings;
  }

  public void setBookings(Set<Booking> bookings) {
    this.bookings = bookings;
  }
}