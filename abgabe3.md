## 3 - Schnittstelle planen

### 3.1 Schnittstellenplanung

Die Schnittstellenplanung beschreibt die Endpunkte um die oberhalb beschriebenen Anforderungen nach folgenden
#### Anforderungen

- A. Die Pfade der Endpunkte sind nach dem REST Paradigma aufgebaut.
- B. Mögliche Parameter (bspw. Ressourcen ID) im Aufrufpfad sind beschrieben.
- C. Die HTTP-Verben sind nach dem REST Paradigma gewählt.
- D. Sowohl für Erfolgs- als auch Fehlerfälle sind die Rückmeldungen der Endpunkte mit HTTP Status beschrieben, sowie wenn diese auftreten.

#### Schnittstellen

##### ApplicationUser

**Path:** `/user`

- index GET
	-  Path: `/user`
- show GET
	- Path: `/user/{id}`
- register POST
	- Path: `/user/register`
- login POST
	- Path: `/user/login`
- delete DELETE
	- Path: `/user/{id}`
- update 
	- PUT
		- Path: `/user/{id}`

##### Role

**Path:** `/role`

- index GET
	-  Path: `/role`
- show GET
	- Path: `/role/{id}`
- create POST
	- Path: `/role`
- delete DELETE
	- Path: `/role/{id}`
- update 
	- PUT
		- Path: `/role/{id}`

##### Booking

**Path:** `/booking`

- index GET
	-  Path: `/booking`
- show GET
	- Path: `/booking/{id}`
- create POST
	- Path: `/booking`
- accept POST
	- Path: `/booking/{id}/accept`
- decline POST
	- Path: `/booking/{id}/decline`
- cancel POST
	- Path: `/booking/{id}/cancel`
- delete DELETE
	- Path: `/booking/{id}`
- update 
	- PUT
		- Path: `/booking/{id}`

##### MeetingRoom

**Path:** `/meeting_room`

- index GET
	-  Path: `/meeting_room`
- show GET
	- Path: `/meeting_room/{id}`
- create POST
	- Path: `/meeting_room
- delete DELETE
	- Path: `/meeting_room/{id}`
- update 
	- PUT
		- Path: `/meeting_room/{id}`


##### TimeSlot

**Path:** `/time_slot`

- index GET
	-  Path: `/time_slot`
- show GET
	- Path: `/time_slot/{id}`
- create POST
	- Path: `/time_slot`
- delete DELETE
	- Path: `/time_slot/{id}`
- update 
	- PUT
		- Path: `/time_slot/{id}`

##### Noccos

**Path:** `/nocco`

- index GET
	-  Path: `/nocco`
- show GET
	- Path: `/nocco/{id}`
- create POST
	- Path: `/nocco`
- delete DELETE
	- Path: `/nocco/{id}`
- update 
	- PUT
		- Path: `/nocco/{id}`

### 3.2 Sequenzdiagramm

#### Anforderungen

#### Sequenzdiagramm

##### ApplicationUser-Entität

###### PlantUML code

```plantuml
@startuml User

title Endpunkte der ApplicationUser-Entität

actor User
participant ApplicationUserController
participant ApplicationUserService
participant EntityManager

User -> ApplicationUserController: GET /user
ApplicationUserController -> ApplicationUserService: index()
ApplicationUserService -> EntityManager: findAll()
ApplicationUserService <-- EntityManager: Users
ApplicationUserController <-- ApplicationUserService: Users
User <-- ApplicationUserController: HTTP 200 / Users als JSON 

User -> ApplicationUserController: GET /user/{id}
ApplicationUserController -> ApplicationUserService: getUser(id)
ApplicationUserService -> EntityManager: find(id)
ApplicationUserService <-- EntityManager: User
ApplicationUserController <-- ApplicationUserService: User
User <-- ApplicationUserController: HTTP 200 / User als JSON 

User -> ApplicationUserController: POST /user/register
ApplicationUserController -> ApplicationUserService: createUser(User)
ApplicationUserService -> EntityManager: persist(User)
ApplicationUserService <-- EntityManager: User
ApplicationUserController <-- ApplicationUserService: User
User <-- ApplicationUserController: HTTP 201 / User als JSON 

User -> ApplicationUserController: POST /user/login
ApplicationUserController -> ApplicationUserService: login(email)
ApplicationUserService -> EntityManager: entityManager.createQuery(query).getSingleResult();
ApplicationUserService <-- EntityManager: Response.ok(credential).cookie(cookie).header(header).build();
ApplicationUserController <-- ApplicationUserService: Response.ok(credential).cookie(cookie).header(header).build();
User <-- ApplicationUserController: HTTP 200 / cookie

User -> ApplicationUserController: DELETE /user/{id}
ApplicationUserController -> ApplicationUserService: delete(id)
ApplicationUserService -> EntityManager: remove(User)
ApplicationUserService <-- EntityManager: Response.status(204).build()
ApplicationUserController <-- ApplicationUserService: HTTP 204
User <-- ApplicationUserController: HTTP 204

User -> ApplicationUserController: PUT /user/{id}
ApplicationUserController -> ApplicationUserService: update(id, user)
ApplicationUserService -> EntityManager: user.setId(id); merge(user);
ApplicationUserService <-- EntityManager: User
ApplicationUserController <-- ApplicationUserService: User
User <-- ApplicationUserController: HTTP 200 / User als JSON

@enduml

```
###### Bild des Sequenzdiagramms

<img src="assets/sequenzdiagramm_application_user.png">

##### Role-Entität

###### PlantUML code

```plantuml
@startuml Role

title Endpunkte der Role-Entität

actor Benutzer
participant RoleController
participant RoleService
participant EntityManager

Benutzer -> RoleController: GET /role
RoleController -> RoleService: index()
RoleService -> EntityManager: findAll()
RoleService <-- EntityManager: Roles
RoleController <-- RoleService: Roles
Benutzer <-- RoleController: HTTP 200 / Roles als JSON 

Benutzer -> RoleController: GET /role/{id}
RoleController -> RoleService: getRole(id)
RoleService -> EntityManager: find(id)
RoleService <-- EntityManager: Role
RoleController <-- RoleService: Role
Benutzer <-- RoleController: HTTP 200 / Role als JSON 

Benutzer -> RoleController: POST /role
RoleController -> RoleService: create(Role)
RoleService -> EntityManager: persist(Role)
RoleService <-- EntityManager: Role
RoleController <-- RoleService: Role
Benutzer <-- RoleController: HTTP 201 / Role als JSON 

Benutzer -> RoleController: DELETE /role/{id}
RoleController -> RoleService: delete(id)
RoleService -> EntityManager: remove(Role)
RoleService <-- EntityManager: HTTP 204
RoleController <-- RoleService: HTTP 204
Benutzer <-- RoleController: HTTP 204

Benutzer -> RoleController: PUT /role/{id}
RoleController -> RoleService: update(id, Role)
RoleService -> EntityManager: role.setId(id); merge(Role)
RoleService <-- EntityManager: Role
RoleController <-- RoleService: Role
Benutzer <-- RoleController: HTTP 200 / Role als JSON

@enduml

```
###### Bild des Sequenzdiagramms

<img src="assets/sequenzdiagramm_role.png">

##### Booking

###### PlantUML code

```plantuml
@startuml Booking

Title Endpunkte der Booking-Entität

actor Benutzer
participant BookingController
participant BookingService
participant EntityManager

Benutzer -> BookingController: GET /booking
BookingController -> BookingService: index()
BookingService -> EntityManager: findAll()
BookingService <-- EntityManager: Bookings
BookingController <-- BookingService: Bookings
Benutzer <-- BookingController: HTTP 200 / Bookings als JSON 

Benutzer -> BookingController: GET /booking/{id}
BookingController -> BookingService: getBooking(id)
BookingService -> EntityManager: find(id)
BookingService <-- EntityManager: Booking
BookingController <-- BookingService: Booking
Benutzer <-- BookingController: HTTP 200 / Booking als JSON 

Benutzer -> BookingController: POST /booking
BookingController -> BookingService: create(Booking)
BookingService -> EntityManager: persist(Booking)
BookingService <-- EntityManager: Booking
BookingController <-- BookingService: Booking
Benutzer <-- BookingController: HTTP 200 / Booking als JSON 

Benutzer -> BookingController: POST /accept/{id}
BookingController -> BookingService: accept(id)
BookingService -> EntityManager: accept(Booking)
BookingService <-- EntityManager: HTTP 204
BookingController <-- BookingService: HTTP 204
Benutzer <-- BookingController: HTTP 204

Benutzer -> BookingController: POST /decline/{id}
BookingController -> BookingService: decline(id)
BookingService -> EntityManager: decline(Booking)
BookingService <-- EntityManager: HTTP 204
BookingController <-- BookingService: HTTP 204
Benutzer <-- BookingController: HTTP 204

Benutzer -> BookingController: POST /cancel/{id}
BookingController -> BookingService: cancel(id)
BookingService -> EntityManager: cancel(Booking)
BookingService <-- EntityManager: HTTP 204
BookingController <-- BookingService: HTTP 204
Benutzer <-- BookingController: HTTP 204

Benutzer -> BookingController: DELETE /booking/{id}
BookingController -> BookingService: delete(id)
BookingService -> EntityManager: remove(Booking)
BookingService <-- EntityManager: HTTP 204
BookingController <-- BookingService: HTTP 204
Benutzer <-- BookingController: HTTP 204

Benutzer -> BookingController: PUT /booking/{id}
BookingController -> BookingService: update(id, Booking)
BookingService -> EntityManager: booking.setId(id); merge(Booking);
BookingService <-- EntityManager: Booking
BookingController <-- BookingService: Booking
Benutzer <-- BookingController: HTTP 200 / Booking als JSON

@enduml
```
###### Bild des Sequenzdiagramms

<img src="assets/sequenzdiagramm_booking.png">

##### MeetingRoom-Entität

###### PlantUML code

```plantuml
@startuml MeetingRoom

title Endpunkte der MeetingRoom-Entität

actor Benutzer
participant MeetingRoomController
participant MeetingRoomService
participant EntityManager

Benutzer -> MeetingRoomController: GET /meeting_room
MeetingRoomController -> MeetingRoomService: index()
MeetingRoomService -> EntityManager: findAll()
MeetingRoomService <-- EntityManager: MeetingRooms
MeetingRoomController <-- MeetingRoomService: MeetingRooms
Benutzer <-- MeetingRoomController: HTTP 200 / MeetingRooms als JSON 

Benutzer -> MeetingRoomController: GET /meeting_room/{id}
MeetingRoomController -> MeetingRoomService: getMeetingRoom(id)
MeetingRoomService -> EntityManager: find(id)
MeetingRoomService <-- EntityManager: MeetingRoom
MeetingRoomController <-- MeetingRoomService: MeetingRoom
Benutzer <-- MeetingRoomController: HTTP 200 / MeetingRoom als JSON 

Benutzer -> MeetingRoomController: POST /meeting_room
MeetingRoomController -> MeetingRoomService: create(meetingRoom)
MeetingRoomService -> EntityManager: persist(meetingRoom)
MeetingRoomService <-- EntityManager: MeetingRoom
MeetingRoomController <-- MeetingRoomService: MeetingRoom
Benutzer <-- MeetingRoomController: HTTP 200 / MeetingRoom als JSON 

Benutzer -> MeetingRoomController: DELETE /meeting_room/{id}
MeetingRoomController -> MeetingRoomService: delete(id)
MeetingRoomService -> EntityManager: remove(meetingRoom)
MeetingRoomService <-- EntityManager: HTTP 204
MeetingRoomController <-- MeetingRoomService: HTTP 204
Benutzer <-- MeetingRoomController: HTTP 204

Benutzer -> MeetingRoomController: PUT /meeting_room/{id}
MeetingRoomController -> MeetingRoomService: update(id, meetingRoom)
MeetingRoomService -> EntityManager: meetingRoom.setId(id); merge(meetingRoom);
MeetingRoomService <-- EntityManager: MeetingRoom
MeetingRoomController <-- MeetingRoomService: MeetingRoom
Benutzer <-- MeetingRoomController: HTTP 200 / MeetingRoom als JSON

@enduml
```
###### Bild des Sequenzdiagramms

<img src="assets/sequenzdiagramm_meeting_room.png">

##### TimeSlot

###### PlantUML code

```plantuml
@startuml TimeSlot

title Endpunkt der TimeSlot-Entität

actor Benutzer
participant TimeSlotController
participant TimeSlotService
participant EntityManager

Benutzer -> TimeSlotController: GET /time_slot
TimeSlotController -> TimeSlotService: index()
TimeSlotService -> EntityManager: findAll()
TimeSlotService <-- EntityManager: TimeSlots
TimeSlotController <-- TimeSlotService: TimeSlots
Benutzer <-- TimeSlotController: HTTP 200 / TimeSlots als JSON 

Benutzer -> TimeSlotController: GET /time_slot/{id}
TimeSlotController -> TimeSlotService: getTimeSlot(id)
TimeSlotService -> EntityManager: find(id)
TimeSlotService <-- EntityManager: TimeSlot
TimeSlotController <-- TimeSlotService: TimeSlot
Benutzer <-- TimeSlotController: HTTP 200 / TimeSlot als JSON 

Benutzer -> TimeSlotController: POST /time_slot
TimeSlotController -> TimeSlotService: create(TimeSlot)
TimeSlotService -> EntityManager: persist(TimeSlot)
TimeSlotService <-- EntityManager: TimeSlot
TimeSlotController <-- TimeSlotService: TimeSlot
Benutzer <-- TimeSlotController: HTTP 201 / TimeSlot als JSON 

Benutzer -> TimeSlotController: DELETE /time_slot/{id}
TimeSlotController -> TimeSlotService: delete(id)
TimeSlotService -> EntityManager: remove(TimeSlot)
TimeSlotService <-- EntityManager: HTTP 204
TimeSlotController <-- TimeSlotService: HTTP 204
Benutzer <-- TimeSlotController: HTTP 204

Benutzer -> TimeSlotController: PUT /time_slot/{id}
TimeSlotController -> TimeSlotService: update(id, TimeSlot)
TimeSlotService -> EntityManager: TimeSlot.setId(id); merge(TimeSlot);
TimeSlotService <-- EntityManager: TimeSlot
TimeSlotController <-- TimeSlotService: TimeSlot
Benutzer <-- TimeSlotController: HTTP 200 / TimeSlot als JSON

@enduml

```
###### Bild des Sequenzdiagramms

<img src="assets/sequenzdiagramm_time_slot.png">

##### Nocco-Entität
###### PlantUML code

```plantuml
@startuml Nocco

title Endpunkte der Nocco-Entität

actor Benutzer
participant NoccosController
participant NoccosService
participant EntityManager

Benutzer -> NoccosController: GET /nocco
NoccosController -> NoccosService: index()
NoccosService -> EntityManager: findAll()
NoccosService <-- EntityManager: Noccos
NoccosController <-- NoccosService: Noccos
Benutzer <-- NoccosController: HTTP 200 / Noccos als JSON 

Benutzer -> NoccosController: GET /nocco/{id}
NoccosController -> NoccosService: getNocco(id)
NoccosService -> EntityManager: find(id)
NoccosService <-- EntityManager: Nocco
NoccosController <-- NoccosService: Nocco
Benutzer <-- NoccosController: HTTP 200 / Nocco als JSON 

Benutzer -> NoccosController: POST /nocco
NoccosController -> NoccosService: create(Nocco)
NoccosService -> EntityManager: persist(Nocco)
NoccosService <-- EntityManager: Nocco
NoccosController <-- NoccosService: Nocco
Benutzer <-- NoccosController: HTTP 201 / Nocco als JSON 

Benutzer -> NoccosController: DELETE /nocco/{id}
NoccosController -> NoccosService: delete(id)
NoccosService -> EntityManager: remove(Nocco)
NoccosService <-- EntityManager: HTTP 204
NoccosController <-- NoccosService: HTTP 204
Benutzer <-- NoccosController: HTTP 204

Benutzer -> NoccosController: PUT /nocco/{id}
NoccosController -> NoccosService: update(id, Nocco)
NoccosService -> EntityManager: Nocco.setId(id); merge(Nocco);
NoccosService <-- EntityManager: Nocco
NoccosController <-- NoccosService: Nocco
Benutzer <-- NoccosController: HTTP 200 / Nocco als JSON

@enduml
```
###### Bild des Sequenzdiagramms

<img src="assets/sequenzdiagramm_nocco.png">