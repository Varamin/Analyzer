# Analyzer
Запуск:
* mvn clean install
* type(cat) .\access.log | java -Xmx512M -jar .\analyzer-1.0-SNAPSHOT.jar -u 86.0 -t 45  

Пример результата работы программы:  
Incidents{from = 2017-06-14T16:48:39 to = 2017-06-14T16:48:39 percent available = 85.99}  
Incidents{from = 2017-06-14T16:48:43 to = 2017-06-14T16:48:43 percent available = 85.94}  
Incidents{from = 2017-06-14T16:48:44 to = 2017-06-14T16:48:44 percent available = 85.72}  
