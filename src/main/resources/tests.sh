# # Curl admin
# response_admin=$(curl -i -X 'POST' \
#   'http://localhost:8080/user/login' \
#   -H 'accept: */*' \
#   -H 'Content-Type: application/json' \
#   -d '{
#   "email": "luke@jedi.com",
#   "password": "Force123"
# }')

# # Extract the authorization bearer token from the response headers
# authorization_admin=$(echo "$response_admin" | grep -i '^authorization:')

# # Curl admin
# response_member=$(curl -i -X 'POST' \
#   'http://localhost:8080/user/login' \
#   -H 'accept: */*' \
#   -H 'Content-Type: application/json' \
#   -d '{
#   "email": "treasurehunter@email.com",
#   "password": "XmarkstheSpot!"
# }')

# # Extract the authorization bearer token from the response headers
# authorization_member=$(echo "$response_member" | grep -i '^authorization:')

authorization_admin="authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2V4YW1wbGUuY29tL2lzc3VlciIsInVwbiI6Impkb2VAcXVhcmt1cy5pbyIsImdyb3VwcyI6WyJBZG1pbmlzdHJhdG9yIl0sImVtYWlsIjoibHVrZUBqZWRpLmNvbSIsImlhdCI6MTcwMDU1MzMyMiwiZXhwIjoxNzAwNTk2NTIyLCJqdGkiOiJjZmQzMDI1MS0yNzExLTRkMmEtYmI2OC05MDEzNzE3MWMyZGYifQ.Wp3nN2MOYjoVrfxb5okULPh1PJVNjiPfNm93RFTkEGt3J6jdvtNAXWRG3Z_WIum-SO2f5NsRPf6vrkxwy0GlGuLwERkyypLLNh6-8jReywYTw_Ad99nBLGvOHwVoRgqXUMxdVYZapUoFXHKYO0JoBJ3VPCIy-7sWLsuO0sTRxfAnxdThHui7EjAqmQjwwRc1LvZDydjBT_NawlevW9BplR31iWcTXD3QPTT3oMAtWdp3d_R6O0rhscOF0QK5kaQQ2c_h8Wmy3l74uMT2vSc-WiaKh12nHme8asLF82GCN_BTnqH-sED2htxkeDbQYI9Tf78q3BpNelsm68PSWil17g"

authorization_member="authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2V4YW1wbGUuY29tL2lzc3VlciIsInVwbiI6Impkb2VAcXVhcmt1cy5pbyIsImdyb3VwcyI6WyJNZW1iZXIiXSwiZW1haWwiOiJ0cmVhc3VyZWh1bnRlckBlbWFpbC5jb20iLCJpYXQiOjE3MDA1NTQ3MTQsImV4cCI6MTcwMDU5NzkxNCwianRpIjoiM2EyMTkyZGYtZWFmMS00MjE0LWE5MDAtZjgwNzFkNWE1MzI4In0.kMzcZPpeDAsfD5zhevsdOaEzqIiWm423b1xHFBlaBNfc_aFKJsplM1ZoldvlqD6LTUcjB_xY4FQQkgVd5tQ7GeR2AKnm7bbAu0tBBYLggE9XWX6atddg6Z9s1-rAm1odQgulJd4dQlnfHOwQOezLwoIEGejSTtr4lMuX5veJEkSQOdx0iQCThkUVCZ2cKchjtPAbUbo3-jzWSievM0yrYvBXLhSbROjlgk0JKkt2JKv1M4Viw468Szcebg6nAIyr5RUAUVe3fmvzCOGALqsmsmO289NtFk6rGsg4GAry1mmd7Lf6zT1GfdUQ6bX7GNEU3U1ySaIL27GXrVoxLVrOCA" 

LIGHT_BLUE='\033[1;34m'
GREEN='\033[0;32m'
NC='\033[0m' # No Color


echo "\n\n${LIGHT_BLUE}APPLICATION USER BLOCK:${NC} \n"
# Create User
echo "${GREEN}Create User:${NC} \n"

response=$(curl -X 'POST' \
  'http://localhost:8080/user/register' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
    "firstName": "test",
    "lastName": "tester",
    "email": "test@email.com",
    "username": "testerino",
    "password": "test1234"
  }')

echo "\n\n$response \n"

user_id=$(echo "$response" | grep -o '"id":[0-9]*,"firstName"' | awk -F'"id":|,"firstName"' '{print $2}')
# Display the extracted user_id

echo "\n\n${GREEN}Login with this User:${NC} \n"

curl -i -X 'POST' \
  'http://localhost:8080/user/login' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "email": "test@email.com",
  "password": "test1234"
}'

echo "\n\n${GREEN}Index Users as Administrator:${NC} \n"

curl -X 'GET' \
  'http://localhost:8080/user' \
  -H 'accept: application/json' -H "$authorization_admin"

echo "\n\n${GREEN}Update user as Administrator:${NC} \n"

curl -i -X 'PUT' \
  'http://localhost:8080/user/3' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -H "$authorization_admin" \
  -d '{
  "firstName": "Update",
  "lastName": "Me",
  "email": "update@me.com",
  "username": "updateme",
  "password": "update1234"
}'

echo "\n\n${GREEN}Delete user as Administrator:${NC} \n"

# Check if user_id variable is set
echo "User ID: ${user_id}"

# Perform the DELETE request using curl if user_id is not empty
if [ -n "${user_id}" ]; then
  curl -i -X 'DELETE' \
    "http://localhost:8080/user/${user_id}" \
    -H 'accept: */*' \
    -H "$authorization_admin"
else
  echo "User ID is empty or not properly set."
fi

echo "\n\n${LIGHT_BLUE}BOOKING BLOCK:${NC} \n"

printf "\n\n${GREEN}Index Administrator (All) Bookings:${NC}"

echo "\n"
# Administrator
curl -X 'GET' \
  'http://localhost:8080/booking' \
  -H 'accept: application/json' -H "$authorization_admin"

echo "\n\n${GREEN}Member Bookings:${NC} \n"
# Member
curl -X 'GET' \
  'http://localhost:8080/booking' \
  -H 'accept: application/json' -H "$authorization_member"

# Show an individual Booking

echo "\n\n${GREEN}Show a single booking:${NC} \n"
curl -X 'GET' \
  'http://localhost:8080/booking/1' \
  -H 'accept: application/json' -H "$authorization_admin"

# Create a Booking as an Administrator
echo "\n\n${GREEN}Create Booking as Administrator:${NC} \n"
booking_response=$(curl -X 'POST' \
  'http://localhost:8080/booking' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -H "$authorization_admin" \
  -d '{
  "bookingDate": "2023-03-10",
  "acceptanceState": "PENDING",
  "state": "ACTIVE",
  "meetingRoom": {
    "id": 1
  },
  "timeSlot": {
    "id": 2
  },
  "nocco": {
    "id": 2
  }
}')



echo "\n\n$booking_response"

booking_id=$(echo "$booking_response" | grep -o '"id":[0-9]*,"bookingDate"' | awk -F'"id":|,"bookingDate"' '{print $2}')

echo "\n\n${GREEN}Create Booking as Member:${NC} \n"

booking_response_member=$(curl -X 'POST' \
  'http://localhost:8080/booking' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -H "$authorization_member" \
  -d '{
  "bookingDate": "2023-04-10",
  "acceptanceState": "PENDING",
  "state": "ACTIVE",
  "meetingRoom": {
    "id": 1
  },
  "timeSlot": {
    "id": 2
  },
  "nocco": {
    "id": 2
  }
}')



echo "\n\n$booking_response_member"

booking_id_member=$(echo "$booking_response_member" | grep -o '"id":[0-9]*,"bookingDate"' | awk -F'"id":|,"bookingDate"' '{print $2}')


echo "\n\n${GREEN}Update Booking as Administrator:${NC} \n"

curl -i -X 'PUT' \
  'http://localhost:8080/booking/3' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -H "$authorization_admin" \
  -d '{
  "bookingDate": "2023-03-10",
  "acceptanceState": "PENDING",
  "state": "ACTIVE",
  "meetingRoom": {
    "id": 4
  },
  "timeSlot": {
    "id": 2
  },
  "nocco": {
    "id": 4
  }
}'


echo "\n\n${GREEN}Cancel Booking as Member:${NC} \n"

curl -i -X 'POST' \
  'http://localhost:8080/booking/2/cancel' \
  -H 'accept: application/json' -H "$authorization_member"

curl -X 'GET' \
'http://localhost:8080/booking/2' \
-H 'accept: application/json' -H "$authorization_admin"


echo "\n\n${GREEN}Accept Booking as Administrator:${NC} \n"
# Accept Meeting
curl -i -X 'POST' \
  'http://localhost:8080/booking/2/accept' \
  -H 'accept: application/json' -H "$authorization_admin"

curl -X 'GET' \
'http://localhost:8080/booking/2' \
-H 'accept: application/json' -H "$authorization_admin"

echo "\n\n${GREEN}Decline Booking as Administrator:${NC} \n"
# Decline Booking
curl -i -X 'POST' \
  'http://localhost:8080/booking/3/decline' \
  -H 'accept: application/json' -H "$authorization_admin"

curl -X 'GET' \
'http://localhost:8080/booking/2' \
-H 'accept: application/json' -H "$authorization_admin"


# Delete a Booking
echo "\n\n${GREEN}Delete a booking:${NC} \n"

echo "Booking ID: ${booking_id}"

if [ -n "${booking_id}" ]; then
  curl -i -X 'DELETE' \
    "http://localhost:8080/booking/${booking_id}" \
    -H 'accept: */*' \
    -H "$authorization_admin"
else
  echo "Booking ID is empty or not properly set."
fi

curl -i -X 'DELETE' \
    "http://localhost:8080/booking/${booking_id_member}" \
    -H 'accept: */*' \
    -H "$authorization_admin"

echo "\n\n${LIGHT_BLUE}MEETING ROOM BLOCK:${NC} \n"

printf "\n\n${GREEN}Index Administrator (All) Meeting Rooms:${NC}"
echo "\n"
# Administrator
curl -X 'GET' \
  'http://localhost:8080/meeting_room' \
  -H 'accept: application/json' -H "$authorization_admin"

# Show an individual Meeting Room

echo "\n\n${GREEN}Show a single Meeting Room:${NC} \n"
curl -X 'GET' \
  'http://localhost:8080/meeting_room/1' \
  -H 'accept: application/json' -H "$authorization_admin"

echo "\n\n${GREEN}Create Meeting Room:${NC} \n"
meeting_room_response=$(curl -X 'POST' \
  'http://localhost:8080/meeting_room' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -H "$authorization_admin" \
  -d '{
  "roomName": "Epsilon"
}')

echo "\n\n$meeting_room_response"

meeting_room_id=$(echo "$meeting_room_response" | grep -o '"id":[0-9]*,"roomName"' | awk -F'"id":|,"roomName"' '{print $2}')

echo "\n\n${GREEN}Update Meeting Room as Administrator:${NC} \n"

curl -i -X 'PUT' \
  "http://localhost:8080/meeting_room/${meeting_room_id}" \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -H "$authorization_admin" \
  -d '{
  "roomName": "TestRoom"
}'

curl -X 'GET' \
  "http://localhost:8080/meeting_room/${meeting_room_id}" \
  -H 'accept: application/json' -H "$authorization_admin"

# Delete a Meeting Room
echo "\n\n${GREEN}Delete a Meeting Room:${NC} \n"

echo "Meeting Room ID: ${meeting_room_id}"

if [ -n "${meeting_room_id}" ]; then
  curl -i -X 'DELETE' \
    "http://localhost:8080/meeting_room/${meeting_room_id}" \
    -H 'accept: */*' \
    -H "$authorization_admin"
else
  echo "Meeting Room ID is empty or not properly set."
fi

######
######
######
######

echo "\n\n${LIGHT_BLUE}NOCCO BLOCK:${NC} \n"

printf "\n\n${GREEN}Index Administrator (All) Noccos:${NC}"
echo "\n"
# Administrator
curl -X 'GET' \
  'http://localhost:8080/nocco' \
  -H 'accept: application/json' -H "$authorization_admin"

# Show an individual Nocco

echo "\n\n${GREEN}Show a single Nocco:${NC} \n"
curl -X 'GET' \
  'http://localhost:8080/nocco/1' \
  -H 'accept: application/json' -H "$authorization_admin"

echo "\n\n${GREEN}Create Nocco:${NC} \n"
nocco_response=$(curl -X 'POST' \
  'http://localhost:8080/nocco' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -H "$authorization_admin" \
  -d '{
  "name": "Peach",
  "caffeine": 180
}')

echo "\n\n$nocco_response"

nocco_id=$(echo "$nocco_response" | grep -o '"id":[0-9]*,"name"' | awk -F'"id":|,"name"' '{print $2}')

echo "\n\n${GREEN}Update Nocco as Administrator:${NC} \n"

curl -i -X 'PUT' \
  "http://localhost:8080/nocco/${nocco_id}" \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -H "$authorization_admin" \
  -d '{
  "name": "pear",
  "caffeine": 360
}'

curl -X 'GET' \
  "http://localhost:8080/nocco/${nocco_id}" \
  -H 'accept: application/json' -H "$authorization_admin"

# Delete a Nocco
echo "\n\n${GREEN}Delete a Nocco:${NC} \n"

echo "Nocco ID: ${nocco_id}"

if [ -n "${nocco_id}" ]; then
  curl -i -X 'DELETE' \
    "http://localhost:8080/nocco/${nocco_id}" \
    -H 'accept: */*' \
    -H "$authorization_admin"
else
  echo "Nocco ID is empty or not properly set."
fi

######
######
######
######

echo "\n\n${LIGHT_BLUE}TIME SLOT BLOCK:${NC} \n"

printf "\n\n${GREEN}Index Administrator (All) time slots:${NC}"
echo "\n"
# Administrator
curl -X 'GET' \
  'http://localhost:8080/time_slot' \
  -H 'accept: application/json' -H "$authorization_admin"

# Show an individual time slot

echo "\n\n${GREEN}Show a single time slot:${NC} \n"
curl -X 'GET' \
  'http://localhost:8080/time_slot/1' \
  -H 'accept: application/json' -H "$authorization_admin"

echo "\n\n${GREEN}Create time slot:${NC} \n"
time_slot_response=$(curl -X 'POST' \
  'http://localhost:8080/time_slot' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -H "$authorization_admin" \
  -d '{
  "type": "Evenings"
}')

echo "\n\n$time_slot_response"

time_slot_id=$(echo "$time_slot_response" | grep -o '"id":[0-9]*,"type"' | awk -F'"id":|,"type"' '{print $2}')

echo "\n\n${GREEN}Update time slot as Administrator:${NC} \n"

curl -i -X 'PUT' \
  "http://localhost:8080/time_slot/${time_slot_id}" \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -H "$authorization_admin" \
  -d '{
  "type": "Nights"
}'

curl -X 'GET' \
  "http://localhost:8080/time_slot/${time_slot_id}" \
  -H 'accept: application/json' -H "$authorization_admin"

# Delete a time slot
echo "\n\n${GREEN}Delete a Time Slot:${NC} \n"

echo "Time slot ID: ${time_slot_id}"

if [ -n "${time_slot_id}" ]; then
  curl -i -X 'DELETE' \
    "http://localhost:8080/time_slot/${time_slot_id}" \
    -H 'accept: */*' \
    -H "$authorization_admin"
else
  echo "Time slot ID is empty or not properly set."
fi

######
######
######
######

echo "\n\n${LIGHT_BLUE}ROLE BLOCK:${NC} \n"

printf "\n\n${GREEN}Index Administrator (All) roles:${NC}"
echo "\n"
# Administrator
curl -X 'GET' \
  'http://localhost:8080/role' \
  -H 'accept: application/json' -H "$authorization_admin"

# Show an individual role

echo "\n\n${GREEN}Show a single role:${NC} \n"
curl -X 'GET' \
  'http://localhost:8080/role/1' \
  -H 'accept: application/json' -H "$authorization_admin"

echo "\n\n${GREEN}Create role:${NC} \n"
role_response=$(curl -X 'POST' \
  'http://localhost:8080/role' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -H "$authorization_admin" \
  -d '{
  "type": "Super Admin"
}')

echo "\n\n$role_response"

role_id=$(echo "$role_response" | grep -o '"id":[0-9]*,"type"' | awk -F'"id":|,"type"' '{print $2}')

echo "\n\n${GREEN}Update role as Administrator:${NC} \n"

curl -i -X 'PUT' \
  "http://localhost:8080/role/${role_id}" \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -H "$authorization_admin" \
  -d '{
  "type": "Ultra Super Admin"
}'



# Delete a time slot
echo "\n\n${GREEN}Delete a Role:${NC} \n"

echo "Role ID: ${role_id}"

if [ -n "${role_id}" ]; then
  curl -i -X 'DELETE' \
    "http://localhost:8080/role/${role_id}" \
    -H 'accept: */*' \
    -H "$authorization_admin"
else
  echo "Role ID is empty or not properly set."
fi