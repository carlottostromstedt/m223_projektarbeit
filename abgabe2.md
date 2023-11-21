## 2 - Persistenzschicht planen

### 2.1 Fachklassendiagramm

Das Fachklassendiagramm bildet alle Entitätsklassen als Klassendiagramm (ohne Methoden) nach folgenden Kriterien ab.
#### Anforderungen

- A. Die Entitäten können die von den Anforderungen verlangten Daten aufnehmen.
- B. Die Daten werden normalisiert abgelegt.
- C. Die Beziehungen zwischen den Entitäten werden hergestellt.
- D. Die Multiplizität wird für die Beziehungen angegeben.

#### Umsetzung

##### ERD

Habe zuerst ein ERD gemacht anstatt ein Fachklassendiagramm. Ich füge es totzdem hier noch hinzu.
###### dbdiagram.io - code

https://dbdiagram.io/d

```sql
Table application_user {
  id integer [primary key]
  firstname varchar
  lastname varchar
  email varchar unique
	username varchar unique
	password varchar unique
  role_id integer
}

Table role {
  id integer [primary key]
  type varchar unique
}

Table booking {
  id integer [primary key]
  acceptancestate varchar
  state varchar
  booking_day date
  meeting_room_id integer
  time_slot_id integer
  nocco_id integer
}

Table time_slot {
  id integer [primary key]
  type varchar unique
}

Table booking_application_user {
  application_user_id integer
  booking_id integer
}

Table meeting_room {
  id integer [primary key]
  name varchar unique
}


Table nocco {
  id integer [primary key]
  name varchar unique
  caffeine int
}

Ref: booking_application_user.application_user_id > application_user.id
Ref: booking_application_user.booking_id > booking.id
Ref: booking.meeting_room_id > meeting_room.id
Ref: booking.nocco_id > nocco.id
Ref: application_user.role_id > role.id
Ref: booking.time_slot_id > time_slot.id
```

###### Bild des ERDs

<img src="assets/erd.png" width="600">

#### Fachklassendiagramm

###### dbdiagram.io - code

```sql
Table ApplicationUser {
  id integer [primary key]
  firstname String
  lastname String
  email String
	username String
	password String
  role Role
}

Table Role {
  id integer [primary key]
  type String
}

Table Booking {
  id integer [primary key]
  bookingDate date
  acceptancestate enum
  state enum
  participants "Set<ApplicationUser>"
  meetingRoom MeetingRoom
  noccos "Set<Nocco>"
  timeSlot Timeslot
}

Table TimeSlot {
  id integer [primary key]
  type String
}

Table MeetingRoom {
  id integer [primary key]
  type String
}

Table Nocco {
  id integer [primary key]
  name String
  caffeine integer
}

Ref: ApplicationUser.role > Role.type
Ref: Booking.participants <> ApplicationUser.id
Ref: Booking.meetingRoom > MeetingRoom.id
Ref: Booking.noccos <> Nocco.id
Ref: Booking.timeSlot > TimeSlot.id
```
###### Bild des Fachklassendiagramms

<img src="assets/fachklassendiagramm.png" width="600" >
