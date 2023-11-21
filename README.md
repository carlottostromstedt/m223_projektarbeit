# Projektarbeit Uek M223 - Coworking-Space-Buchungsapplikation

## Setup

1. Git-Projekt klonen
2. Stelle sicher, dass Docker installiert ist und läuft.
3. Verzeichnis mit VS-Code öffnen
4. Dev-Container Extension installieren https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers
5. Öffne das Projekt im Entwicklungscontainer.
6. Starte das Projekt mit dem Kommando `Quarkus: Debug current Quarkus Project`

### Web-Applikation

Die Applikation läuft auf der addresse und port: http://localhost:8080

Swagger API Interface läuft auf http://localhost:8080/q/swagger-ui/ 

### Datenbank

Die Daten werden in einer PostgreSQL-Datenbank gespeichert. In der Entwicklungsumgebung wird diese in der [docker-compose-yml](./.devcontainer/docker-compose.yml) konfiguriert.

### Datenbankadministration

Über http://localhost:5050 ist PgAdmin4 erreichbar. Damit lässt sich die Datenbank komfortabel verwalten. Der Benutzername lautet `zli@example.com` und das Passwort `zli*123`. Die Verbindung zur PostgreSQL-Datenbank muss zuerst mit folgenden Daten konfiguriert werden:
 - Host name/address: `db`
 - Port: `5432`
 - Maintenance database: `postgres`
 - Username: `postgres`
 - Password: `postgres`

### Testdaten

Testdaten sind unter `src/main/resources/import.sql`. Sie werden automatisch beim Start von Quarkus geladen.

## Ausgangslage
Ein Coworking Space in der Agglomeration von Zürich möchte in Zukunft seine Mitglieder und die Nutzung des Angebots digital über eine Webapplikation erfassen. Dazu sollte zuerst ein minimaler Prototyp realisiert werden, um den Kunden besser abholen zu können.
## Aufgabe
Die zu entwickelnde Prototyp soll aus einer Server- und Client-Applikation bestehen. Die Client-Applikation benutzt die Server-Applikation über eine HTTP API. Für den Prototyp sind folgende, menschliche Akteure vorgesehen:

- Administrator
- Mitglied
- Besucher (nicht authentifizierter Benutzer)

## Funktionale Anwendungsfälle

Folgende funktionalen Anwendungsfälle sollen mindestens im Prototypen implementiert werden:
- Als Besucher möchte ich mich mit meinem Vor- und Nachnamen, meiner E-Mail-Adresse und einem Passwort registrieren, damit ich die Rolle Mitglied bekommen kann.
- Als Besucher möchte ich mich mit meiner E-Mail-Adresse und meinem Passwort anmelden, damit ich mich als Mitglied oder Administrator authentifizieren kann.
- Als Mitglied möchte ich halbe und ganze Tage an bestimmten Daten im Coworking Space als Buchung anfragen, damit ich die Angebote des Coworking Space nutzen kann.
- Als Mitglied möchte ich den Status meiner Buchungen überprüfen, damit ich erfahre, ob meine Buchung bestätigt oder abgelehnt wurde.
- Als Mitglied kann ich meine zukünftigen Buchungen stornieren, damit ich auf Veränderungen in meiner Terminplanung reagieren kann.
- Als Administrator kann ich Mitglieder verwalten (erstellen, bearbeiten, löschen), damit ich die Mitglieder organisieren kann.
 - Als Administrator kann ich Buchungsanfragen akzeptieren und ablehnen, damit die Mitglieder das Angebot des Coworking Space nutzen können.
- Als Administrator kann ich Buchungen verwalten (erstellen, bearbeiten, löschen), damit ich die Buchungen organisieren kann.

## Nicht-funktionale Anwendungsfälle

Folgende nicht-funktionale Anforderungen sollen mindestens im Prototypen umgesetzt werden:
- Das Datenmodell erfüllt die erste, zweite und dritte Normalform nach der relationalen Entwurfstheorie.
- Der erste Besucher bekommt nach der Registrierung die Rolle Administrator anstatt Mitglied.
- Die Authentifizierung erfolgt mittels JSON Web Token (JWT nach RFC 7519) über den HTTP Header 'Authorization'.
- Das JWT läuft 24 Stunden nach der Ausstellung ab und verliert seine Gültigkeit.
- Das JWT wird clientseitig während dessen Lebensdauer persistent aufbewahrt.

## 1 - Anforderungen analysieren
Zusätzliche, projektrelevante Anforderungen wurden nach folgenden Kriterien beschrieben.

### 1.1 Erweiterte Anforderungen
#### A. Drei zusätzliche, einzigartige, funktionale Anforderungen sind als User Stories (Als [Akteur], kann ich [Funktion], damit [Kontext]) beschrieben.

- Als Mitglied, kann ich weitere Mitglieder zu einer Buchung hinzufügen, damit ein Coworking-Termin im Coworking-Space erstellt werden kann
- Als Mitglied, kann ich spezifische Meetingräume buchen, damit Meetings abgehalten werden können
- Als Mitglied, kann ich meiner Buchung Noccos (Energy-Drinks) hinzufügen, damit ich koffeinmässig fit bin für meine Arbeit 

#### B. Drei zusätzliche, einzigartige, nicht-funktionale Anforderungen sind messbar beschrieben.

- Passwörter werden gehasht abgelegt
- Der User muss nie länger als eine Sekunde auf das laden der Seite warten
- Die Web-Applikation hat 99 % Verfügbarkeit

#### C. Die zusätzlichen Anforderungen sind projektrelevant und auf die Bedürfnisse von einem Coworking Space abgestimmt.

#### Alle Funktionale Anwendungsfälle

Folgende funktionalen Anwendungsfälle sollen mindestens im Prototypen implementiert werden:
- Als Besucher möchte ich mich mit meinem Vor- und Nachnamen, meiner E-Mail-Adresse und einem Passwort registrieren, damit ich die Rolle Mitglied bekommen kann.
- Als Besucher möchte ich mich mit meiner E-Mail-Adresse und meinem Passwort anmelden, damit ich mich als Mitglied oder Administrator authentifizieren kann.
- Als Mitglied möchte ich halbe und ganze Tage an bestimmten Daten im Coworking Space als Buchung anfragen, damit ich die Angebote des Coworking Space nutzen kann.
- Als Mitglied möchte ich den Status meiner Buchungen überprüfen, damit ich erfahre, ob meine Buchung bestätigt oder abgelehnt wurde.
- Als Mitglied kann ich meine zukünftigen Buchungen stornieren, damit ich auf Veränderungen in meiner Terminplanung reagieren kann.
- Als Administrator kann ich Mitglieder verwalten (erstellen, bearbeiten, löschen), damit ich die Mitglieder organisieren kann.
 - Als Administrator kann ich Buchungsanfragen akzeptieren und ablehnen, damit die Mitglieder das Angebot des Coworking Space nutzen können.
- Als Administrator kann ich Buchungen verwalten (erstellen, bearbeiten, löschen), damit ich die Buchungen organisieren kann.
- Als Mitglied, kann ich weitere Mitglieder zu einer Buchung hinzufügen, damit ein Coworking-Termin im Coworking-Space erstellt werden kann
- Als Mitglied, kann ich spezifische Meetingräume buchen, damit Meetings abgehalten werden können
- Als Mitglied, kann ich meiner Buchung Noccos (Energy-Drinks) hinzufügen, damit ich koffeinmässig fit bin für meine Arbeit 

#### Alle nicht-funktionale Anwendungsfälle

Folgende nicht-funktionale Anforderungen sollen mindestens im Prototypen umgesetzt werden:
- Das Datenmodell erfüllt die erste, zweite und dritte Normalform nach der relationalen Entwurfstheorie.
- Der erste Besucher bekommt nach der Registrierung die Rolle Administrator anstatt Mitglied.
- Die Authentifizierung erfolgt mittels JSON Web Token (JWT nach RFC 7519) über den HTTP Header 'Authorization'.
- Das JWT läuft 24 Stunden nach der Ausstellung ab und verliert seine Gültigkeit.
- Das JWT wird clientseitig während dessen Lebensdauer persistent aufbewahrt.
- Passwörter werden gehasht abgelegt
- Der User muss nie länger als eine Sekunde auf das laden der Seite warten
- Die Web-Applikation hat 99 % Verfügbarkeit

### 1.2 - Persona

Personas wurden für die im Projekt vorhandenen Akteure nach folgenden Kriterien beschrieben.
#### Anforderungen

- A. Für jeden Akteur wird eine Persona beschrieben.
- B. Jede Persona ist mit Vor- und Nachnamen, Alter und Geschlecht beschrieben.
- C. Jede Persona ist mit einem passenden Bild beschrieben.
- D. Zu jeder Persona ist ihre berufliche Tätigkeit beschrieben.
- E. Zu jeder Persona ist beschrieben, aus welchen Gründen die Angebote von einem Coworking Space benutzt werden.

#### Personas

##### Persona 1 - Nicht angemeldeter User

<img src="assets/richie_rich.png" width="300">

- **Name:** Richie Rich
- **Alter:** 10
- **Geschlecht:** Männlich
- **Rolle:** Nicht angemeldeter User
- **Beruf:** Richie ist ein reiches Schulkind.
- **Gründe für Besuch von Coworking Space:**  Richie wird das Rich-Imperium von seinem Vater Richard Rich Sr. übernehmen und möchte sich für den Coworking Space anmelden um dort seine erste Meetings abhalten zu können.
##### Persona 2 - Angemeldeter User

<img src="assets/patrick_bateman.png" width="200" height ="200">

- **Name:** Patrick Bateman
- **Alter:** 27
- **Geschlecht:** Männlich
- **Rolle:** User
- **Beruf:** Bateman ist **Börsenmakler bei Pierce & Pierce**, arbeitet aber eigentlich kaum.
- **Gründe für Besuch von Coworking Space:** Patrick Bateman möchte gerne einen Ort haben wo er potentielle Kunden und Geschäftspartner treffen kann. Bateman möchte auch einen Ort haben an dem er in aller Ruhe mit seinen Arbeitskollegen Visitenkarten vergleichen kann.

##### Persona 3 - Administrator

<img src="assets/elon_musk.png" width="200" height ="200">

- **Name:** Elon Musk
- **Alter:** 52
- **Geschlecht:** Männlich
- **Rolle:** Administrator
- **Beruf:** Elon Musk war ein Multi-Milliardär und Unternehmer, der aber mit dem Kauf von Twitter die Firma und sich selber in den Ruin trieb.
- **Gründe für Besuch von Coworking Space:** Elon Musk möchte gerne eine neue Firma starten und hat sich daher dafür entschieden in der Zürcher Agglomeration einen Coworking-Space zu bauen. Mit seiner Erfahrung von Twitter / X möchte er als Administrator den reibungslosen Betrieb der Buchungsplatform garantieren.

### 1.3 - Anwendungsfalldiagramm

Ein Anwendungsfalldiagramm wurde nach UML 2 und folgenden Kriterien erstellt.
#### Anforderungen

- A. Der Systemkontext und ein projektrelevanter Systemtitel ist abgebildet.
- B. Alle Akteure und deren Namen sind visualisiert.
- C. Alle funktionalen Anforderungen (inkl. der zusätzlichen Anforderungen aus Kriterium 1) sind als Anwendungsfälle abgebildet.
- D. Die Relationen zwischen Akteuren und Anforderungen sind visualisiert.

#### Diagramm - PlantUML code

```plantuml
@startuml
title Anwendungsfalldiagramm - Coworking-Space-Buchungstool
left to right direction
skinparam packageStyle rectangle

actor Besucher as B
actor Mitglied as M
actor Administrator as A

rectangle "Benutzer-Authentifizierung" {
    B -- (Registrieren)
    B -- (Anmelden)
}

rectangle "Buchungsverwaltung" {
    M -- (Buchung anfragen)
    M -- (Buchungsstatus überprüfen)
    M -- (Buchung stornieren)
    M -- (Mitglieder zur Buchung hinzufügen)
    M -- (Bestimmte Meetingräume buchen)
    M -- (Noccos zur Buchung hinzufügen)
    A -- (Mitglieder verwalten)
    A -- (Buchungsanfragen verwalten)
    A -- (Buchungen verwalten)
}

@enduml
```

#### Diagramm - Bild

<img src="assets/anwendungsfalldiagramm.png" height="800">

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
  booking_day date
  meeting_room_id integer
  time_slot_id integer
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

Table booking_nocco {
  booking_id integer
  nocco_id integer
}

Table nocco {
  id integer [primary key]
  name varchar unique
  caffeine int
}

Ref: booking_application_user.application_user_id > application_user.id
Ref: booking_application_user.booking_id > booking.id
Ref: booking.meeting_room_id - meeting_room.id
Ref: booking_nocco.booking_id > booking.id
Ref: booking_nocco.nocco_id > nocco.id
Ref: application_user.role_id - role.id
Ref: booking.time_slot_id - time_slot.id
```

###### Bild des ERDs

<img src="assets/erd.png" width="600">

#### Fachklassendiagramm

###### dbdiagram.io - code

```sql
Table ApplicationUser {
  id integer [primary key]
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
	- PATCH
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
	- PATCH
		- Path: `/role/{id}`

##### Booking

**Path:** `/booking`

- index GET
	-  Path: `/booking`
- show GET
	- Path: `/booking/{id}`
- create POST
	- Path: `/booking`
- delete DELETE
	- Path: `/booking/{id}`
- update 
	- PUT
		- Path: `/booking/{id}`
	- PATCH
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
	- PATCH
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
	- PATCH
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
	- PATCH
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
ApplicationUserController -> ApplicationUserService: login(username)
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

User -> ApplicationUserController: PATCH /user/{id}
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

Benutzer -> RoleController: PATCH /role/{id}
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
Benutzer <-- BookingController: HTTP 201 / Booking als JSON 

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

Benutzer -> BookingController: PATCH /booking/{id}
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
Benutzer <-- MeetingRoomController: HTTP 201 / MeetingRoom als JSON 

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

Benutzer -> MeetingRoomController: PATCH /meeting_room/{id}
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

Benutzer -> TimeSlotController: PATCH /time_slot/{id}
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

Benutzer -> NoccosController: PATCH /nocco/{id}
NoccosController -> NoccosService: update(id, Nocco)
NoccosService -> EntityManager: Nocco.setId(id); merge(Nocco);
NoccosService <-- EntityManager: Nocco
NoccosController <-- NoccosService: Nocco
Benutzer <-- NoccosController: HTTP 200 / Nocco als JSON

@enduml

```
###### Bild des Sequenzdiagramms

<img src="assets/sequenzdiagramm_nocco.png">