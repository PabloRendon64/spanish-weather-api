# Pablo Rendon - Prueba técnica Spanish Weather API
## Requerimientos ##

 - Java JDK 21
 - Gradle 8.10.2 o superior

## Compilar

```
gradle clean build
```

## Ejecutar la app

para ejecutar la app solo se debe iniciar el proyecto en la case principal `src/main/java/com/inditex/core/platform/Application.java`

## Documentación

la documentación usando swagger ui se puede encontrar en la siguiente ruta mientras el proyecto este ejecutandose

`http://localhost:8080/swagger-ui/index.html`

## Información adicional

algunos frameworks/mejores practicas usadas:

 - spring boot
 - Domain-driven design (DDD) y se implementó arquitectura hexagonal
 - JPA
 - pruebas unitarias
 - jacoco reports para covertura de código
 - swagger para documentacion del código

**NOTA:** el reporte de jacoco se puede encotrar en `/build/jacoco/test/html/index.html` depues de compilar el proyecto