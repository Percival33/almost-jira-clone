# PAP22-Z16

### Lista członków zespołu:
<ul>
    <li>Marcin Jarczewski</li>
    <li>Franciszek Malewski</li>
    <li>Jakub Jóźwiak</li>
    <li>Michał Ładyżyński</li>
</ul>

### Treść zadania:
W ramach projektu z przedmiotu Programowanie Aplikacyjne planujemy stworzyć aplikację umożliwiającą backtracking i zarządzanie projektami, podobną do programu Jira. Chcemy, aby program zawierał następujące funkcjonalności:
<ol>
    <li>Możliwość tworzenia kont użytkownika</li>
    <li>System logowania</li>
    <li>Możliwość pracy na wspólnym projekcie</li>
    <li>Możliwość dodawania nowych zadań i przypisywania do nich osób, terminów wykonania</li>
    <li>Monitorowanie postępu nad zadaniami / projektami</li>
    <li>Inne, jeszcze nie wyznaczone</li>
</ol>

### Opis rozwiązania:
Wybrana technologia: Java Spring

[dokumentacja API] (dostępna gdy aplikacja jest uruchomiona)



## Konteneryzacja

```bash
docker build -t my-spring-app .
docker run -p 8080:8080 my-spring-app
```

[//]: # (link)
[dokumentacja API]: http://localhost:8080/swagger-ui/index.html
