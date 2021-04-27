# fetchreward_point
# Requirement
- Java 11
- Spring Boot
- Maven


#REST API
The REST API to the app is described below.

##Request

`POST /addTransaction`<br>
Accept JSON EX: <br>`{ "payer": "DANNON", "points": 1000, "timestamp": "2020-11-02T14:00:00Z" }`<br>
Return: savedTransaction <br><br>

`POST /addListTransaction`<br>
Accept list of JSON: <br>
   `
   [
       { "payer": "DANNON", "points": 1000, "timestamp": "2020-11-02T14:00:00Z" },
       { "payer": "UNILEVER", "points": 200, "timestamp": "2020-10-31T11:00:00Z" }
       ,{ "payer": "DANNON", "points": -200, "timestamp": "2020-10-31T15:00:00Z" }
       ,{ "payer": "MILLER COORS", "points": 10000, "timestamp": "2020-11-01T14:00:00Z" }
       ,{ "payer": "DANNON", "points": 300, "timestamp": "2020-10-31T10:00:00Z" }
       ]` 
Return: List of saved Transaction<br><br>

`GET /getAllTransaction`<br>
Return list of transactions<br><br>

`GET /getBalance`<br>
Return  balance of all account