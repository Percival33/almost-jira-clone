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
Dostępna po uruchomieniu programu [dokumentacja API]

### Aktualny poziom zaawansowania rozwiazania:
W celu uruchomienia programu, konieczna jest jego kompilacja. Najpierw przy użyciu Gradle, a następnie za pomocą SpringBoot.
Następnie otworzenie programu jest możliwe po otworzeniu następującego adresu w przeglądarce: [dokumentacja API]

Na ten moment zostały zaimplementowane funkcje, które w niedalekiej przyszłości posłużą do stworzenia funkcjonującej aplikacji.
Projekt został również zintegrowany ze Swagger UI, który za sprawą naszych wysiłków umożliwia przetestowanie zaimplementowanych funkcji.
Aktualna aplikacja prezentuje się następująco:

![Current implementation](img/swagger.png)

[//]: # (link)
[dokumentacja API]: http://localhost:8080/swagger-ui/index.html