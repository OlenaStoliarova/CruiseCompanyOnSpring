### Система Круизная Компания. 
У <b>Компании</b> имеется несколько Кораблей</b>. У <b>Кораблей</b> есть пассажиро-вместимость, маршрут, количество посещаемых портов, длительность одного круиза. <b>Клиент</b> выбирает и оплачивает круиз. Выбирает <b>Экскурсии</b> по прибытии в порт за дополнительную плату. <b>Администратор Корабля</b> указывает бонусы для пассажиров, учитывая класс билета (бассейн, спорт зал, кинозал,
косметические салоны...).


#### Using:
1. DB - MySql
2. Java version 8.
3. Maven

#### How to install:

1. Clone project
2. Run DBStructureAndData.sql from resources/DB folder
3. Update DB login and password in application.properties
4. Compile and run jar 
5. Go to link http://localhost:8080/
6. Just in case here are credentials of already existing users:
  - admin@a.a (password:admin, ROLE_ADMIN)
  - boss@c.ua (password:boss, ROLE_TRAVEL_AGENT)
  - misha@b.ua (password:user, ROLE_TOURIST)


#### To get jar:

mvn clean install

#### To run jar:
(from target directory)

java -Dfile.encoding=UTF-8 -jar CruiseCompanyOnSpring-0.0.1-SNAPSHOT.jar

#### Example of killing process
C:\>jps

    6464 jar
    1412
    9716 RemoteMavenServer36
    3292 Jps
    5132 Launcher

C:\>taskkill -f /PID 6464

    SUCCESS: The process with PID 6464 has been terminated.
