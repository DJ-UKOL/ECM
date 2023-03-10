Приложение представляющее собой упрощенную систему электронного документооборота.
Приложение должно реализовывать основные CRUD-операции:
1.	Создание документа
2.	Редактирование документа
3.	Просмотр документа
4.	Просмотр списка документов с пагинацией
5.	Атрибутивный поиск документов
6.	Удаление документа
В системе должно быть реализовано управление следующими типами записей:  
1.	Организация,  содержит поля:  
a.	наименование организации;  
b.	физический адрес;  
c.	юридический адрес;  
d.	руководитель.  
2.	Подразделение,  содержит поля:  
a.	наименование подразделения;  
b.	контактные данные;  
c.	руководитель.  
3.	Сотрудник,  содержит поля: 
a.	фамилия;
b.	имя;
c.	отчество;
d.	должность.  
4.	Поручение:  
a.	предмет поручения;
b.	автор поручения;
c.	исполнители поручения;
d.	срок исполнения; 
e.	признак контрольности;
f.	признак исполнения;  
g.	текст поручения.
Серверная часть должна быть реализована в виде web-приложения с разделением на следующие слои:  
1.	Слой взаимодействия с пользовательским интерфейсом - реализовать с использованием Spring-Web в виде REST-сервисов;
2.	Слой сервисных объектов - реализуют бизнес-логику приложения.  Реализовать с использованием бинов;
3.	Слой доступа к данным - реализует логику доступа к хранилищу данных.  Реализовать с использованием JPA Spring Data;
4.	Конфигурация приложения хранится в отдельных файлах;
5.	Управление состояниями документов реализуется через машину состояний;
6.	Готовое приложение должно представлять Spring Boot приложение, собранное в jar;
7.	Зависимости должны быть также запакованы в jar-файл.

Жизненный цикл документа “Поручение”  
Жизненный цикл документа “Поручение”  рекомендуется реализоваться с использованием машины состояний.   

