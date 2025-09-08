# Google Sites Sync
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white)
## 📌 Descripción

Servicio desarrollado en **Spring Boot** que permite **sincronizar los sitios de Google asociados a una cuenta**.
Cada cuenta contiene **grupos**, y cada grupo a su vez tiene **sitios**. El proyecto se encarga de:

1. Conectarse a la API de Google con credenciales configuradas.
2. Recorrer los grupos (cuentas) y obtener todos los sitios disponibles.
3. Sincronizar y almacenar la información de los sitios en **MongoDB**.

De esta forma, se logra centralizar y mantener actualizada la información de Google Sites en una base de datos para posteriores usos (consultas, integraciones, dashboards, etc.).


---

## 🚀 Configuración del proyecto

### 1. Clonar el repositorio

```bash
git clone <URL_REPOSITORIO>
cd sync-googlesites
```

### 2. Variables de entorno / application.properties

En `src/main/resources/application.properties` se configuran los valores necesarios:

```properties
spring.application.name=adsitios
spring.data.mongodb.uri=mongodb://localhost:27017/ads-sync

server.servlet.context-path=/v1

google.url.auth=${google_url}
google.sites.information=${google_sites_information}
google.sites.verification=${google_sites_verification}
google.sites.account.management=${google_sites.account_management}
google.client.id=${google_client_id}
google.client.secret=${google_client_secret}
google.refresh.token=${google_refresh_token}
google.grant.type=${google_grant_type}
```

👉 Los valores `${...}` deben configurarse como **variables de entorno** antes de levantar la aplicación.

---

### 3. Compilar y ejecutar el proyecto

```bash
mvn clean install
mvn spring-boot:run
```

La API quedará disponible en:

```
http://localhost:8080/v1
```

---

## 🗄️ Base de datos

* **MongoDB** debe estar en ejecución local en el puerto `27017`.
* Se creará/actualizará la base de datos llamada `ads-sync`.
* Allí se almacenará la información de los grupos y sitios sincronizados.

---

## 📡 Endpoints (ejemplo)

Algunos endpoints esperados podrían ser:

| Método | Endpoint       | Descripción                                  |
| ------ |----------------|----------------------------------------------|
| GET    | `/sitios`      | Lista todos los sitios sincronizados         |
| GET    | `/grupos`      | Lista de todos los grupos sincronizados      |
| POST   | `/grupos/sync` | Lanza el proceso de sincronización de grupos |
| POST   | `/sitios/sync` | Lanza el proceso de sincronización de sitios |

*(ajustar según tu implementación real)*

