# university-academic-eureka

**Microservicio responsable del registro y descubrimiento de servicios dentro de la arquitectura distribuida del sistema académico universitario.**  
El **Eureka Server** actúa como punto central donde los demás microservicios (`AuthService`, `Student`, `Teacher`, `University`, `Gateway`, etc.) se **registran dinámicamente** y pueden **descubrirse entre sí**, permitiendo una comunicación sin depender de direcciones o puertos fijos.

---

## Descripción general

Este repositorio contiene la implementación del **Eureka Server**, componente esencial en la arquitectura de microservicios del sistema académico.

### Responsabilidades principales

- Servir como **Service Registry** para todos los microservicios.  
- Facilitar la **descubribilidad dinámica** en tiempo de ejecución.  
- Monitorear el estado de los servicios mediante *heartbeats*.  
- Permitir **escalabilidad horizontal**, registrando múltiples instancias.  
- Integrarse con los módulos `AuthService`, `Student`, `Teacher`, `University` y el `API Gateway`.  

---

## Tecnologías principales

| Tecnología | Versión / Descripción |
|-------------|------------------------|
| **Java** | 21 |
| **Spring Boot** | 3.5.5 |
| **Spring Cloud Netflix Eureka Server** | Última versión estable |
| **Build Tool** | Maven |
| **Tests** | JUnit 5 |
| **Contenedores** | Docker |

---

## Estructura del repositorio

```bash
/
├─ src/
│  ├─ main/
│  │  ├─ java/         # Código fuente principal (configuración de Eureka)
│  │  └─ resources/    # Archivos application.yml / properties
│  └─ test/            # Pruebas unitarias
├─ Dockerfile
├─ pom.xml
├─ .gitignore
└─ README.md
```

---

## Flujo de trabajo y políticas de ramas

### Ramas principales

- **main** → Código listo para despliegue.  
- **develop** → Rama de integración.  
- **qa** → Rama de pruebas funcionales.  
- **feature/** → Nuevas funcionalidades.  
- **release/** → Estabilización previa al paso a producción.  

**Flujo recomendado:**  
`feature/*` → PR → `develop` → `qa` → `release/*` → `main`

---

## Configuración básica

### Archivo `application.yml` (modo local)

```yaml
server:
  port: 8761

spring:
  application:
    name: eureka-server

eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
  server:
    enable-self-preservation: false
  instance:
    hostname: localhost
```

### Configuración para un cliente (ejemplo: `auth-service`)

```yaml
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

---

## Ejecución local

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/<ORG>/eureka-server.git
   ```
2. Compilar el proyecto:
   ```bash
   mvn clean install
   ```
3. Ejecutar:
   ```bash
   mvn spring-boot:run
   ```
4. Acceder a la consola de Eureka:
   ```
   http://localhost:8761
   ```

---

## Dockerización

### Ejemplo de `Dockerfile`

```dockerfile
FROM openjdk:21-jdk
WORKDIR /app
COPY target/eureka-server.jar eureka-server.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/app/eureka-server.jar"]
```

### Construcción y ejecución

```bash
docker build -t eureka-server .
docker run -p 8761:8761 eureka-server
```

---

##  Integración con los módulos del sistema

| Módulo | Descripción |
|--------|--------------|
| **AuthService** | Registro y descubrimiento para autenticación centralizada. |
| **Student-Service** | Gestión de estudiantes y consulta de rendimiento. |
| **Teacher-Service** | Gestión de docentes y materias. |
| **University-Service** | Parametrización de universidades y programas. |
| **API Gateway** | Redirección inteligente de peticiones según el servicio registrado. |

---

## Monitoreo y despliegue

El servicio expone un endpoint de salud en:

```bash
/actuator/health
```

Puede integrarse con herramientas como **Prometheus** o **Grafana** para monitorear disponibilidad, latencia y métricas de los servicios registrados.

---

## Enlaces relevantes

-  **Repositorio base:** `university-academic-tracker`  
-  **Documentación oficial:** [Spring Cloud Netflix Eureka](https://cloud.spring.io/spring-cloud-netflix/)  
-  **Guía de implementación:** [Documentación oficial de Spring](https://spring.io/projects/spring-cloud-netflix)
