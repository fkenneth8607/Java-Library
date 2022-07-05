# Java-Library

Simple Crud application to manage books using:
- Java EE 8
- Spring boot
- Rest APIs
- vuejs 2
- Mysql Database
- Clean Architecture

![image](https://user-images.githubusercontent.com/33763007/177363572-ad9bb8da-b905-4eb4-95db-218a89059c89.png)

Instructions
______________________________________________

Via Node js
with this command install vue if is necessary with next command:

  npm install -g @vue/cli

with this command we install all modules node for the proyect

  npm install

create bd in mysql called library

import la bd library.sql in the repo

change application properties with your configuration

spring.datasource.url=jdbc:mysql://localhost:3306/library 
spring.datasource.username=root
spring.datasource.password=yourPass
server.error.whitelabel.enabled=false



Run Java app as Spring boot app and the front end execute the comand  run npm serve
