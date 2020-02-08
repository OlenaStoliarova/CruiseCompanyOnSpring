## Система Круизная Компания. 
У <b>Компании</b> имеется несколько Кораблей</b>. У <b>Кораблей</b> есть пассажиро-вместимость, маршрут, количество посещаемых портов, длительность одного круиза. <b>Клиент</b> выбирает и оплачивает круиз. Выбирает <b>Экскурсии</b> по прибытии в порт за дополнительную плату. <b>Администратор Корабля</b> указывает бонусы для пассажиров, учитывая класс билета (бассейн, спорт зал, кинозал,
косметические салоны...).


#### To get jar:

mvn clean install -DskipTests=true

#### To run jar:
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
