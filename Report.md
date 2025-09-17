# Отчёт о проведённом тестировании
Краткое описание
Автоматизировно тестирование комплексного сервиса покупки тура, взаимодействующего с СУБД и API Банка.

## Статистика успешных/неуспешных кейсов

### При подключении к БД MySQL

<img width="1150" height="759" alt="image" src="https://github.com/user-attachments/assets/e1b5e672-c533-4062-b8a1-7d76812f456d" />


## Общая информация

- **Время выполнения:** 3м 52с  
- **Всего тестов:** 52  
- **Успешно:** 36  
- **Неуспешные:** 16  
- **Процент успешных тестов:** 69.23 %  

---

## Результаты по тестовым наборам

| Тестовый набор (Suite)                                   | Всего тестов | Успешно  | Неуспешные  |
|----------------------------------------------------------|--------------|------------|--------------|
| `ru.netology.tests.common.CardholderFieldValidationTest` | 12           | 2          | 10           |
| `ru.netology.tests.common.DateFieldValidationTest`       | 16           | 14         | 2            |
| `ru.netology.tests.common.CvvFieldValidationTest`        | 8            | 6          | 2            |
| `ru.netology.tests.sql.CreditPaySqlRequestTest`          | 2            | 1          | 1            |
| `ru.netology.tests.sql.PaySqlRequestTest`                | 2            | 1          | 1            |
| `ru.netology.tests.common.CardNumberFieldValidationTest` | 6            | 6          | 0            |
| `ru.netology.tests.api.PaymentSystemAPITest`             | 4            | 4          | 0            |
| `ru.netology.tests.common.EmptyAllFieldsValidationTest`  | 2            | 2          | 0            |

---

## Основные наблюдения
1. **Наибольшее количество ошибок** в `CardholderFieldValidationTest` (10 падений из 12) → высокая концентрация дефектов валидации поля «Владелец карты».  
2. **DateFieldValidationTest** и **CvvFieldValidationTest** содержат по 2 ошибки → локальные баги валидации даты и CVC-кода.  
3. **SQL-тесты (CreditPaySqlRequestTest, PaySqlRequestTest)** падают на половине проверок (по 1 багу в каждом наборе). Это указыват на несоответствие UI ↔ БД.  
4. **CardNumber, PaymentSystemAPI и EmptyFieldsValidation** полностью зелёные — тесты пройдены успешно, багов не выявлено.  

---

## Выводы
- Серьёзные проблемы с обработкой и валидацией **поля держателя карты**.  
- Присутствуют отдельные баги в логике обработки **даты** и **CVV**.  
- Взаимодействие с БД (SQL-тесты) нестабильно: результаты в UI не всегда совпадают с записями в базе.  
- Позитивные сценарии (корректный ввод номера карты, API-проверки, пустые поля) работают корректно.

  ## Статистика успешных/неуспешных кейсов
  
### При подключении к БД PostgreSQL

<img width="1154" height="759" alt="image" src="https://github.com/user-attachments/assets/3d06694a-b9cc-4bc0-b8cb-ccf0150bf2e8" />



## Общая информация
- **Время выполнения:** 4м 08с  
- **Всего тестов:** 52  
- **Успешно:** 35  
- **Неуспешно:** 17  
- **Процент успешных тестов:** 67.3 %  

---

## Результаты по тестовым наборам

| Тестовый набор (Suite)                                   | Всего тестов | Успешно    | Неуспешно    |
|----------------------------------------------------------|--------------|------------|--------------|
| `ru.netology.tests.common.CardholderFieldValidationTest` | 12           | 2          | 10           |
| `ru.netology.tests.common.DateFieldValidationTest`       | 16           | 14         | 2            |
| `ru.netology.tests.common.CvvFieldValidationTest`        | 8            | 6          | 2            |
| `ru.netology.tests.common.CardNumberFieldValidationTest` | 6            | 5          | 1            |
| `ru.netology.tests.sql.CreditPaySqlRequestTest`          | 2            | 1          | 1            |
| `ru.netology.tests.sql.PaySqlRequestTest`                | 2            | 1          | 1            |
| `ru.netology.tests.api.PaymentSystemAPITest`             | 4            | 4          | 0            |
| `ru.netology.tests.common.EmptyAllFieldsValidationTest`  | 2            | 2          | 0            |

---

## Основные наблюдения
1. **Критическая зона** — `CardholderFieldValidationTest`: 10 ошибок из 12 тестов (83% провалов) → серьёзные дефекты валидации поля «Владелец карты».  
2. `DateFieldValidationTest` и `CvvFieldValidationTest` показывают по 2 ошибки → отдельные проблемы валидации даты и CVV.  
3. `CardNumberFieldValidationTest` потерял устойчивость — 1 неуспешный тест.  
4. SQL-наборы (`CreditPaySqlRequestTest`, `PaySqlRequestTest`) нестабильны: по одному тесту завершилось с ошибкой в каждом.  
5. `PaymentSystemAPITest` и `EmptyAllFieldsValidationTest` прошли полностью успешно.  

---

## Выводы
- Общая стабильность ниже 70% (67.3%), что говорит о необходимости исправлений в ключевых модулях.  
- Наибольший риск для релиза несут **валидация владельца карты** и **SQL-тесты** (рассогласование между UI и БД).  
- API-тесты и проверки пустых полей демонстрируют стабильность.

## Общие выводы
1. **Критическая проблема** одинакова в обоих окружениях: валидация поля «Владелец карты» работает некорректно (83% тестов падает).  
2. **MySQL** показал немного лучшее покрытие (69.23% успешных против 67.3% у PostgreSQL).  
3. При работе с **Postgres** выявился дополнительный сбой в `CardNumberFieldValidationTest`, что говорит о возможных различиях в обработке данных между СУБД.  
4. **SQL-тесты** нестабильны в обоих вариантах, что указывает на несоответствие логики UI и фактических записей в базе.  
5. **API-тесты и проверки пустых полей** — полностью стабильны в обоих окружениях, рисков не несут.  

---

## Рекомендации
- Приоритетное исправление — логика валидации **CardholderFieldValidationTest**.  
- Проверить различия в схемах и данных **MySQL и Postgres**, особенно для `CardNumberFieldValidationTest`.  
- Усилить отладку и логи в SQL-тестах (фиксация запросов/ответов и записей в таблицах).  
- Сконцентрироваться на приведении функционала UI и БД к единому поведению.
- Исправить визуальные и орфографические ошибки.
- Cделать одинаковыми кнопки "Купить" и "Купить в кредит", чтобы при нажатии они меняли цвет.
- Полный список найденных дефектов находится в [Issues](https://github.com/MaxAcrata/Diploma/issues)



