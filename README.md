# TestDB

Создание БД, которая содержит следующие таблицы:
- developers (хранит данные о разработчиках)
- skills (навыки разработчиков – Java, C++, etc.)
- projects (проекты, на которых работают разработчики)
- companies (IT компании, в которых работают разработчики)
- customers (клиенты, которые являются заказчиками проектов в IT компаниях)
При этом:
- разработчики могут иметь много навыков
- каждый проект имеет много разработчиков, которые над ним работают
- компании выполняют много проектов одновременно - заказчики имеют много проектов

sql ресурсы:

initDB.sql (создание таблиц и связей между ними)

populateDB.sql (заполнение таблиц данными)