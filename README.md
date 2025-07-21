# Дипломный проект по профессии «Тестировщик»  
**Тема:** Автоматизация тестирования веб-формы сервиса покупки туров интернет-банка  

---
## Команды для запуска автотестов для формы оплаты и кредита через веб-интерфейс.
---

## Запуск Docker

Убедитесь, что БД запущена через Docker: 
```bash
docker-compose up --build
```
---

## Запуск приложения

Для запуска приложения с поддержкой **PostgreSQL**, выполните:

```bash
java -jar ./artifacts/aqa-shop.jar --spring.profiles.active=postgres
```

Для работы с **MySQL** :

```bash
java -jar ./artifacts/aqa-shop.jar --spring.profiles.active=mysql
```
---

## Запуск тестов
Для работы с PostgreSQL:
``` bash
gradlew test -Ddb=postgres
```
Для работы с  MySQL:
``` bash
gradlew test -Ddb=mysql
```

