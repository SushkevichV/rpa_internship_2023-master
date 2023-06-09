## Приложение для расчета среднего курса валют

Приложение работает с курсами валют, которые публикует [НБ РБ](https://www.nbrb.by/statistics/rates/ratesdaily), в диапазоне с 01.12.2022 по 31.05.2023. 
### Функциональные возможности:
1. Добавить ежедневные курсы валют за заданный период в БД. Пользователь указывает тип валюты (например USD), начальную и конечную дату периода. В качестве ответа получает массив курсов валют, которые были добавлены в БД. Формат даты: yyyy-mm-dd

  - проверка на корректность дат.
  - проверка наличия в БД курсов валют за выбранный период.
  - проверка на корректность типа валюты.

   Пример запроса: http://localhost:8087/api/currencies/load/US?start=2023-02-01&end=2023-02-10

2. Вывести все курсы по заданному типу валюты, имеющиеся в базе данных. Пользователь указывает тип валюты. В качестве ответа получает список курсов заданной валюты из базы данных.
  - проверка на корректность типа валюты.

   Пример запроса: http://localhost:8087/api/currencies/USD

3. Рассчитать средний курс за месяц. Пользователь указывает тип валюты и порядковый номер месяца. В качестве ответа получает рассчитанный средний курс за месяц.
      Средний курс = среднегеометрическое от ежедневных курсов за заданный месяц исключая выходные дни. Используются данные из БД.

  - проверка месяца для расчета курса.
  - проверка на корректность типа валюты.
  - если год не указан, используется текущий год.

   Пример запроса: http://localhost:8087/api/currencies/average/USD?month=1&year=2023

Для класса контроллера есть тестовый класс.

Java 17

Приложение использует H2 базу данных. БД сбрасывается при перезапуске приложения.
