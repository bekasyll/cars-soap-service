# Cars SOAP-service

Простой SOAP веб-сервис на Spring Boot с подключением к PostgreSQL для работы с автомобилями.

---

## Описание

Проект предоставляет SOAP веб-сервис, который позволяет запрашивать информацию о машинах по бренду и модели.  
Используется база данных PostgreSQL и Spring Data JPA для хранения данных.

Основные компоненты:
- `Car` — сущность автомобиля.
- `CarRepository` — интерфейс репозитория JPA.
- `CarEndpoint` — SOAP endpoint, который обрабатывает запросы.
- `carsWs.xsd` — XSD-схема для генерации WSDL и классов JAXB.
- `WebServiceConfig` — конфигурация SOAP сервиса.

---

## Технологии

- Java 17
- Spring Boot 4
- Spring Web Services (SOAP)
- Spring Data JPA
- PostgreSQL
- JAXB (Jakarta XML Binding)
- Maven
- Lombok

---

## Настройка базы данных

Создайте базу данных PostgreSQL, например:

```sql
CREATE DATABASE cars_db;

CREATE TABLE cars (
    id SERIAL PRIMARY KEY,
    brand VARCHAR(255),
    model VARCHAR(255),
    color VARCHAR(255),
    year VARCHAR(10),
    countryoforigin VARCHAR(255)
);
````

Добавьте несколько записей для теста:

```sql
INSERT INTO cars (brand, model, color, year, countryoforigin)
VALUES 
('Toyota', 'Camry', 'Black', '2020', 'Japan'),
('Toyota', 'Camry', 'White', '2021', 'Japan'),
('Toyota', 'Camry', 'Red', '2019', 'Japan'),
('Honda', 'Civic', 'Blue', '2021', 'Japan'),
('Ford', 'Focus', 'Grey', '2018', 'USA');
```

---

## Конфигурация

`application.yml`:

```yaml
spring:
  application:
    name: soap_service

  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/cars_db
    username: postgres
    password: password

  jpa:
    show-sql: true

  webservices:
    wsdl-locations: classpath:META-INF/schemas/
```

---

## Запуск проекта

Соберите и запустите проект через Maven:

```bash
mvn clean install
mvn spring-boot:run
```

После запуска WSDL будет доступен по адресу:

```
http://localhost:8080/services/cars.wsdl
```

---

## Пример SOAP запроса

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soap="https://bekasyl.com/soap-service">
   <soapenv:Header/>
   <soapenv:Body>
      <soap:getCarRequest>
         <soap:brand>Toyota</soap:brand>
         <soap:model>Camry</soap:model>
      </soap:getCarRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

Пример ответа:

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
   <soapenv:Body>
      <getCarResponse xmlns="https://bekasyl.com/soap-service">
         <car>
            <brand>Toyota</brand>
            <model>Camry</model>
            <color>Black</color>
            <year>2020</year>
            <countryOfOrigin>Japan</countryOfOrigin>
         </car>
         <car>
            <brand>Toyota</brand>
            <model>Camry</model>
            <color>White</color>
            <year>2021</year>
            <countryOfOrigin>Japan</countryOfOrigin>
         </car>
         ...
      </getCarResponse>
   </soapenv:Body>
</soapenv:Envelope>
```

---

## Примечания

* Используется Jakarta XML Binding (JAXB) для генерации классов из XSD.
* В сущности `Car` поле `countryOfOrigin` связано с колонкой `countryoforigin` в базе.
* Все запросы SOAP обрабатываются через `CarEndpoint`.
