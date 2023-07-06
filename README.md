# PAP22-Z16

## Spis treści

1. [Członkowie zespołu](##Lista-członków-zespołu)
2. [Zadanie](#Treść-zadania)
3. [Aktualny stan rozwiązania](#Aktualny-poziom-zaawansowania-rozwiazania)
4. [Konteneryzacja i uruchomienie](#Konteneryzacja)
5. [Baza danych](#baza-danych)
6. [Style CSS](#style-css)

## Lista członków zespołu:

- [Marcin Jarczewski](https://github.com/Percival33)
- [Franciszek Malewski](https://github.com/FranciszekMalewski)
- [Jakub Jóźwiak](https://github.com/jjozwiak113)
- [Michał Ładyżyński]

## Treść zadania:

W ramach projektu z przedmiotu Programowanie Aplikacyjne planujemy stworzyć aplikację umożliwiającą bugtracking i zarządzanie projektami, podobną do programu Jira. Chcemy, aby program zawierał następujące funkcjonalności:

<ol>
    <li>Możliwość tworzenia kont użytkownika</li>
    <li>System logowania</li>
    <li>Możliwość pracy na wspólnym projekcie</li>
    <li>Możliwość dodawania nowych zadań i przypisywania do nich osób, terminów wykonania</li>
    <li>Monitorowanie postępu nad zadaniami / projektami</li>
    <li>Inne, jeszcze nie wyznaczone</li>
</ol>

## Aktualny poziom zaawansowania rozwiazania:

Wybrana przez nas technologia: Java Spring po stronie serwerowej oraz Vue.js po stronie klienckiej. Korzystamy również z nierelacyjnej bazy dancyh - MongoDb.

Na ten moment aplikacja realizuje podstawowe założenia i posiada funkcjonalności, które pozwalają na dodawanie, odczytywanie, aktualizacje oraz usuwanie zarówno użytkowników jak i projektów oraz zadań do poszczególnych projektów. Możliwa jest również manipulacja zadaniami w obrębie projektu.

<details>
  <summary markdown="span">dokumentacja API - dostępne endpointy</summary>
    <img src="img/swagger.png"/>
</details>

## Konteneryzacja

Aplikacja jest przygotowana do uruchomienia za pomocą `docker compose`, który buduje oddzielne kontenery na `API` oraz `UI`. Proces budowania pierwszy raz zależy w dużym stopniu od jakości łącza internetowego, gdyż potrzebne jest pobranie odpowiednich obrazów.

### Uruchomienie programu

Aby uruchomić aplikację z wykorzystaniem konteneryzacji należy stworzyć plik `.env` w którym, należy podmienić wartości zmiennych. Są to dane do logowania do bazy danych.

```
DB_USER=<user>
DB_PASSWORD=<passwd>
```

Aby uruchomić aplikację należy wykorzystać `docker compose`. Uruchomiona aplikacja będzie działała pod portem `5000` [aplikacja] a dostęp do api będzie możliwy pod portem `8080`

```bash
docker compose up
```

Do przebudowania od nowa potrzeba flag `--force-recreate` oraz `--build`

Aby ją zatrzymać należy użyć polecenia

```bash
docker compose down --remove-orphans
```

> w przypadku korzystania z innej instancji mongodb należy odpowiednio zmodyfikować link przy użyciu którego następuje połączenie z bazą danych. Aby to zrobić należy zmodyfikować zawartośc pliku `pap22z-z16/almost-jira/src/main/resources/application.yml`

<!-- <br/>
istnieje możliwość korzystania z lokalnej bazy dancyh aby to zrobić należy:

    1. zmodyfikować link w `pap22z-z16/almost-jira/src/main/resources/application.yml`.
    2. uruchomić bazę danych w dockerze

```bash
docker run -d --name almost-jira-mongo -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=okon mongo
``` -->

## Baza danych

Aplikacja wykorzystuje bazę danych mongodb, która jest hostowana a zewnetrznym serwisie [MongoDBCloud].

### Style CSS 
Style css pochodzą głównie z biblioteki css [Pico], niektóre z nich zostały przystosowane do naszych potrzeb.

[//]: # "/home/percival/.jdks/temurin-17.0.5/bin/java  -jar /home/percival/src/pap22z-z16/almost-jira/build/libs/almost-jira-0.0.1-SNAPSHOT-plain.jar"
[//]: # "link"
[dokumentacja api]: http://localhost:8080/swagger-ui/index.html
[aplikacja]: http://localhost:5000
[pico]: https://picocss.com/
[MongoDBCloud]: https://www.mongodb.com/atlas
