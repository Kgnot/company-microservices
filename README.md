# Company Microservices

Este proyecto es una simulación de una serie de microservicios diseñados para replicar la estructura de una compañía. Está enfocado en el uso de componentes de Spring Cloud para demostrar patrones y buenas prácticas en el desarrollo de microservicios.

## Tabla de Contenidos

- [Descripción](#descripción)
- [Arquitectura](#arquitectura)
- [Componentes Principales](#componentes-principales)
- [Requisitos Previos](#requisitos-previos)
- [Instalación](#instalación)
- [Patrones Implementados](#patrones-implementados)
- [Logs con ELK Stack](#logs-con-elk-stack)
- [Contribuciones](#contribuciones)

---

## Descripción

El objetivo de este proyecto es proporcionar un entorno práctico para trabajar con microservicios y explorar conceptos avanzados de Spring Cloud. Los microservicios están diseñados para interactuar entre sí utilizando herramientas modernas de balanceo de carga, descubrimiento de servicios y resiliencia.

## Arquitectura

La arquitectura incluye los siguientes componentes:

1. **API Gateway**: Gestiona las solicitudes entrantes y realiza el balanceo de carga entre los microservicios.
2. **Config Server**: Centraliza las configuraciones de todos los microservicios, permitiendo actualizaciones dinámicas.
3. **Service Registry**: Registra y descubre los microservicios dinámicamente.
4. **Microservicio de Departamentos**: Proporciona APIs para gestionar departamentos y se comunica con:
5. **Microservicio de Empleados**: API para gestionar los datos de los empleados, consumida por el microservicio de departamentos.

## Componentes Principales

### API Gateway
- Implementa balanceo de carga.
- Maneja rutas y filtros personalizados.

### Config Server
- Almacena configuraciones centralizadas.
- Compatible con Git para versionado.

### Service Registry
- Basado en **Spring Cloud Netflix Eureka**.
- Registra todos los microservicios para facilitar su descubrimiento.

### Microservicio de Departamentos
- Comunicación vía **HttpExchange** con el microservicio de empleados.
- Implementación de patrones de resiliencia como Circuit Breaker, Retry, Rate Limiter, Time Limiter y Bulkhead.

### Microservicio de Empleados
- Proporciona operaciones CRUD básicas.

## Requisitos Previos

- **Java**: JDK 17 o superior.
- **Maven**: Para construir los proyectos.
- **Docker**: Para ejecutar el ELK stack y otros servicios.

## Instalación

1. Clona este repositorio:

2. Construye los microservicios con Maven:

3. Levanta el entorno Docker para ELK:

## Patrones Implementados

### Resiliencia (Microservicio de Departamentos)

- **Circuit Breaker**: Gestiona fallos en las comunicaciones con servicios externos.
- **Retry**: Reintenta solicitudes fallidas automáticamente.
- **Rate Limiter**: Limita la cantidad de solicitudes permitidas en un periodo de tiempo.
- **Time Limiter**: Define tiempos máximos de respuesta para evitar bloqueos.
- **Bulkhead**: Asegura que los recursos estén distribuidos entre diferentes tareas para evitar saturaciones.

### Balanceo de Carga
- Gestionado por el API Gateway utilizando rutas personalizadas.

### Configuración Centralizada
- Actualización dinámica de propiedades mediante el Config Server.

## Logs con ELK Stack

Los microservicios están configurados para enviar logs al ELK stack, que incluye:

- **Elasticsearch**: Almacenamiento y búsqueda de logs.
- **Logstash**: Procesamiento de logs.
- **Kibana**: Visualización de logs y métricas.

Para acceder a Kibana:
1. Abre tu navegador y accede a `http://localhost:5601`.
2. Configura un índice para visualizar los logs enviados por los microservicios.

## Contribuciones

¡Contribuciones son bienvenidas! Si tienes ideas o encuentras problemas, abre un issue o envía un pull request.