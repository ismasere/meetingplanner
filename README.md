# meetingplanner
this  is  kata  for  
# the  Application is stored in  H2  DataBase 
To  see the  APi  documentation  please  just go to   http://localhost:8080/swagger-ui/index.html

There is  sql script  witch  inject data  in H2  (Room data )

 1 To use  the  API  you must register  a new  user .
# for  example  POST
http://localhost:8080/api/auth/register

with  json payload
{
    "firstname":"Soumaila",
    "lastname":"SERE",
    "email":"sere@gmail.com",
    "password":"12345678"
 

}

2 the user must authenticate 

3 http://localhost:8080/api/auth/authenticate

# example   POST
{
    "email":"sere@gmail.com",
    "password":"12345678"
 

}
4 with  postman  you  can  test  the  api 

You can  reserve a mmeting   
# POST
http://localhost:8080/api/meetings/
{
   "name":"reunion SERE 3",
	"startTime": "2024-08-14T09:00:00", 
    "endTime": "2024-08-14T09:00:00",
    "attendees": 4,
    "meetingType": "VC"
}    

5 find all meeting  of  the  day  using  this  
http://localhost:8080/api/meetings/day




Thanks !!!!!

SERE Soumaila
