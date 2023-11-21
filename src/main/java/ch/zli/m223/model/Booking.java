package ch.zli.m223.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import org.hibernate.annotations.Fetch;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name="BOOKING",
    uniqueConstraints = {
      @UniqueConstraint(columnNames = {"bookingdate","meetingRoom_id", "timeSlot_id"})
    }
)
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long id;

  @NotNull(message="Booking date may not be null")
  @Column(nullable = false)
  private Date bookingDate;

  public enum AcceptanceState {
      PENDING, ACCEPTED, DECLINED;
  }

  public enum State {
    ACTIVE, CANCELED;
  }

  @NotNull(message = "Booking state may not be null")
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private AcceptanceState acceptanceState;


  @NotNull(message = "Booking state may not be null")
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private State state;
  
  @JsonIgnoreProperties("bookings")
  @ManyToMany
  @JoinTable(
  name = "application_user_booking", 
  joinColumns = @JoinColumn(name = "application_user_id"), 
  inverseJoinColumns = @JoinColumn(name = "booking_id"),
  uniqueConstraints = @UniqueConstraint(columnNames = {"booking_id", "application_user_id"})) 
  Set<ApplicationUser> participants;

  @JsonIgnoreProperties("bookings")
  @ManyToOne(optional = false)
  @Fetch(FetchMode.JOIN)
  private MeetingRoom meetingRoom;

  @JsonIgnoreProperties("bookings")
  @ManyToOne(optional = false)
  @Fetch(FetchMode.JOIN)
  private TimeSlot timeSlot;

  @JsonIgnoreProperties("bookings")
  @ManyToOne(optional = true)
  @Fetch(FetchMode.JOIN)
  private Nocco nocco;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getBookingDate() {
    return bookingDate;
  }

  public void setBookingDate(Date bookingDate) {
    this.bookingDate = bookingDate;
  }

  public AcceptanceState getAcceptanceState() {
    return acceptanceState;
  }

  public void setAcceptanceState(AcceptanceState acceptanceState) {
    this.acceptanceState = acceptanceState;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public Set<ApplicationUser> getParticipants() {
    return participants;
  }

  public void setParticipants(Set<ApplicationUser> participants) {
    this.participants = participants;
  }

  public MeetingRoom getMeetingRoom() {
    return meetingRoom;
  }

  public void setMeetingRoom(MeetingRoom meetingRoom) {
    this.meetingRoom = meetingRoom;
  }

  public TimeSlot getTimeSlot() {
    return timeSlot;
  }

  public void setTimeSlot(TimeSlot timeSlot) {
    this.timeSlot = timeSlot;
  }

  public Nocco getNocco() {
    return nocco;
  }

  public void setNocco(Nocco nocco) {
    this.nocco = nocco;
  }
}