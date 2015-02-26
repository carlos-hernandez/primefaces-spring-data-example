Primefaces + Spring Data + QueryDSL + LazyDataModel
===================================================

Description
--------------------------------------

This project is an example about how to use a multi-tier architecture to build a Primefaces application.

How to build
--------------------------------------

You can import this Maven project in your favourite IDE. If you find some compilation errors when importing it run the following command.

```bash
mvn clean compile
```

Modules
--------------------------------------

### Model Entities

Used to define the model entities used within the project.

This module generates some metadata classes based on the JPA entities so we can use [QueryDSL](http://www.querydsl.com) to have a more fluent API to create HQL/JQL queries.

This metadata classes are created after compiling this module, and you can found them within the `target/generated-sources/java` folder. When Maven is packaging this module in a JAR all of this metadata classes are included too.

QueryDSL allows us to explicitly define HQL/JQL queries within our code, so we can detect errors at compile-time if a query doesn't match the current mappings that are defined in JPA.

**Dependencies:**
- JPA 2.1 Annotations

**References:**
- [QueryDSL JPA Integration](http://www.querydsl.com/static/querydsl/3.6.1/reference/html/ch02.html#jpa_integration)

### Repositories

This module is using [Spring Data JPA](http://projects.spring.io/spring-data-jpa/) to define our data-access layer. Spring Data JPA provides us with a good abstraction of repositories so we can have at hand all the methods to support CRUD and search operations over our model entities.

One of the most important features about Spring Data JPA is that provides us with the necessary tools to define paginated queries.

Spring Data JPA supports some QueryDSL functionalitites so we can easily define search methods based on the metadata classes that are created in the **Model Entities** module.

**Dependencies:**
- [Spring Data JPA](http://docs.spring.io/spring-data/jpa/docs/current/reference/html/)

**References:**
- [Advanced Spring Data JPA - Specifications and Querydsl](http://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl/)

## Business

The intention of this module is to concentrate all the business logic of the project. It makes use of the data-access layer to retrieve the information used by the business operations that we want to expose.

This layer prohibits the exposure of model entities outside it. That's the reason why we need another layer like the one defined in the **SPI** module.

To map the entities and the DTOs defined in the SPI layer we need a bean mapper framework like [Orika](https://code.google.com/p/orika/) to abstract and concentrate this operations outside the **Business Object (BO)**.

To have a better integration between Orika and Spring this module defines a `SpringConfigurableMapper` that searches bean mappers and converters created with Orika that are registered as Spring `@Component`s.

**Dependencies:*
- Orika

**References:**
-- [Orika Documentation](http://orika-mapper.github.io/orika-docs/)

### SPI (Service Provider Interface)

Defines DTOs and interfaces that external projects can use to communicate with our business layer.

### JSF Client

This is an example module that shows how to use a Business Object injected via Spring within a JSF Controller.

Also, it makes use of some of the capabilities mentioned before to have a paginated datatable and make it easier to use custom filters within a datatable.
