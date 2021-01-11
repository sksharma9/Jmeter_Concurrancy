# 



# Jmeter_Concurrancy test


here I have tried to simulate following **problem statement.**

The task is to consistently increment a number in a database when parallel threads are racing to increment the number.
 a table Number in MySQL database with one integer type field.
 a RESTful API using Spring MVC architecture that increments this number.
Used Jmeter  to call this API with 5000 users so that a lot of parallel requests are sent to server to increment the number.
the initial value of Number is 0.
After the execution of Jmeter, the value of the number in the database is 5000 (as expected).


**tech and tools used:**

* SPRING 
* HIBERNATE
* JAVA 1.8
* JMETER
* MYSQL
* MAVEN
* ECLIPSE








**SPIRNG REST APIs**

**GET** : http://localhost:8080/taskApp/user/getUser?id=7   

**Response** : {"user":{"id":7,"count":9,"name":"shail"},"message":"user found"}

**POST** : http://localhost:8080/taskApp/user/addUser?name=shelendra

**Response** : {"user":{"id":11,"count":0,"name":"shelendra"},"message":"user created successfully"}

**PUT** : http://localhost:8080/taskApp/user/updateCount?id=7

**Response** : {"user":{"id":7,"count":10,"name":"shail"},"message":"count has been incremented by 1"}


**Pre-data before running Jmeter API requests. initial count set to 0 as shown below.**




<p align="center">
  <img src="/taskApp/pre-data.JPG" alt="Size Limit CLI" width="500">
</p>


 **using Jmeter for bulk API requsts.**
 
 

<p align="center">
  <img src="/taskApp/Jmeter testing image.JPG" alt="Size Limit CLI" width="738">
</p>



**post-data after running Jmeter API requests.  count is 5000 now.**

 
 

<p align="center">
  <img src="/taskApp/post-data.JPG" alt="Size Limit CLI" width="738">
</p>



**Concurrancy control mechanism used**

  Pessimistic_write locking via Hibernate

