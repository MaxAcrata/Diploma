# Отчёт о тестировании 

## Запуск с БД MySQL
<img width="1429" height="940" alt="image" src="https://github.com/user-attachments/assets/2ce2e31e-1480-42f8-8ca0-f5051fda7591" />


## Общая информация
- **Время выполнения:** 5м 20с  
- **Всего тестов:** 56  
- **Успешно:** 38  
- **Неуспешно:** 18  
- **Процент успешных тестов:** 67.85 %  

---

## Результаты по тестовым наборам

| Тестовый набор (Suite)                                   | Всего тестов | Успешно  | Неуспешно  |
|----------------------------------------------------------|--------------|------------|--------------|
| `ru.netology.tests.common.CardholderFieldValidationTest` | 12           | 2          | 10           |
| `ru.netology.tests.common.DateFieldValidationTest`       | 16           | 14         | 2            |
| `ru.netology.tests.common.CvvFieldValidationTest`        | 8            | 6          | 2            |
| `ru.netology.tests.sql.CreditPaySqlRequestTest`          | 4            | 2          | 2            |
| `ru.netology.tests.sql.PaySqlRequestTest`                | 4            | 2          | 2            |
| `ru.netology.tests.common.CardNumberFieldValidationTest` | 6            | 6          | 0            |
| `ru.netology.tests.api.PaymentSystemAPITest`             | 4            | 4          | 0            |
| `ru.netology.tests.common.EmptyAllFieldsValidationTest`  | 2            | 2          | 0            |

---

## Основные наблюдения
1. **Критическая зона** — `CardholderFieldValidationTest`: 10 ошибок → серьёзные дефекты валидации поля «Владелец карты».  
2. `DateFieldValidationTest` и `CvvFieldValidationTest` содержат по 2 ошибки → отдельные проблемы валидации даты и CVV.  
3. SQL-наборы (`CreditPaySqlRequestTest`, `PaySqlRequestTest`) упали наполовину (по 2 из 4 тестов) → нестабильность работы с БД.  
4. `CardNumberFieldValidationTest`, `PaymentSystemAPITest` и `EmptyAllFieldsValidationTest` — тесты пройдены успешно.  

---

## Выводы
- Общая стабильность тестов ниже 70% (67.85%), требуется доработка ключевых модулей.  
- **Наибольший риск для релиза** несут:  
  - валидация владельца карты (CardholderFieldValidationTest);  
  - SQL-тесты (рассогласование UI ↔ БД).  
- API-тесты и проверки пустых полей работают корректно и рисков не несут.  

Запуск с БД PostgreSQL

<img width="1153" height="753" alt="image" src="https://github.com/user-attachments/assets/cf5de958-e1fe-4f1a-a801-97fad54070d0" />


## Общая информация
- **Время выполнения:** 5м 13с  
- **Всего тестов:** 56  
- **Успешно:** 36  
- **Неуспешно:** 20  
- **Процент успешных тестов:** 64.28 %  

---

## Результаты по тестовым наборам

| Тестовый набор (Suite)                                   | Всего тестов | Успешно  | Неуспешно  |
|----------------------------------------------------------|--------------|------------|--------------|
| `ru.netology.tests.common.CardholderFieldValidationTest` | 12           | 2          | 10           |
| `ru.netology.tests.common.DateFieldValidationTest`       | 16           | 14         | 2            |
| `ru.netology.tests.common.CvvFieldValidationTest`        | 8            | 6          | 2            |
| `ru.netology.tests.sql.CreditPaySqlRequestTest`          | 4            | 1          | 3            |
| `ru.netology.tests.sql.PaySqlRequestTest`                | 4            | 1          | 3            |
| `ru.netology.tests.common.CardNumberFieldValidationTest` | 6            | 6          | 0            |
| `ru.netology.tests.api.PaymentSystemAPITest`             | 4            | 4          | 0            |
| `ru.netology.tests.common.EmptyAllFieldsValidationTest`  | 2            | 2          | 0            |

---

## Основные наблюдения
1. **Критическая зона** — `CardholderFieldValidationTest`: 10 ошибок → серьёзные дефекты валидации поля «Владелец карты».  
2. `DateFieldValidationTest` и `CvvFieldValidationTest` содержат по 2 ошибки → отдельные проблемы валидации даты и CVV.  
3. SQL-наборы (`CreditPaySqlRequestTest`, `PaySqlRequestTest`) упали наполовину (по 3 из 4 тестов) → нестабильность работы с БД.  
4. `CardNumberFieldValidationTest`, `PaymentSystemAPITest` и `EmptyAllFieldsValidationTest` — тесты пройдены успешно.  

---

## Выводы
- Общая стабильность тестов ниже 70% ( 64.28%), требуется доработка ключевых модулей.  
- **Наибольший риск для релиза** несут:  
  - валидация владельца карты (CardholderFieldValidationTest);  
  - SQL-тесты (рассогласование UI ↔ БД).  
- API-тесты и проверки пустых полей работают корректно и рисков не несут.  


## Общие рекомендации
- Исправить визуальные и орфографические ошибки 
-  Cделать одинаковыми кнопки "Купить" и "Купить в кредит", чтобы при нажатии они меняли цвет (активная вкладка,неактивная)
- Исправить ошибки связаные с полями «Владелец карты», «ДМесяц» «CVV». 
- Решить проблему с подключением БД PostgreSQL, так как аналогичные тесты проходят на MySQL, а на PostgreSQL не проходят 
 -  Для поля "Владелец" ввести ограничение на вводимые символы - только английские буквы, нечувствительные к регистру
 - Сделать кнопку "Продолжить" неактивной, если есть пустые и/или неправильно заполненные поля
- Полный список найденных дефектов находится в [Issues](https://github.com/MaxAcrata/Diploma/issues)
