# demojk - Aplicación Jakarta EE

Aplicación empresarial desarrollada con Jakarta EE 10, desplegada sobre WildFly y PostgreSQL en Docker. Implementa un CRUD completo de **Pedidos** mediante JSF/PrimeFaces y una API REST.

---

## Tecnologías

- Jakarta EE 10
- WildFly 39
- PostgreSQL 16
- JSF 4 + PrimeFaces 14
- Docker / Docker Compose
- Maven

---

## Estructura del Proyecto

```
src/main/
├── java/org/eclipse/jakarta/
│   ├── entities/
│   │   └── Pedido.java          # Entidad JPA
│   ├── EJB/
│   │   └── PedidoService.java   # Lógica de negocio (EJB Stateless)
│   ├── Bean/
│   │   └── PedidoBean.java      # Managed Bean para JSF
│   └── rest/
│       ├── PedidoResource.java  # API REST
│       ├── CorsFilter.java      # Filtro CORS
│       └── HelloApplication.java
├── META-INF/
│   └── persistence.xml          # Configuración JPA
└── webapp/
    ├── pedidos.xhtml             # Vista CRUD PrimeFaces
    ├── index.xhtml
    └── WEB-INF/
        └── web.xml
docker/
├── configure-wildfly.cli        # Script de configuración WildFly
└── postgresql-42.7.8.jar        # Driver JDBC
```

---

## Requisitos Previos

- Docker Desktop
- Java 21
- Maven 3.9+

---

## Configuración Docker

### Base de datos
- **Nombre:** delacruz
- **Usuario:** postgres
- **Contraseña:** postgres
- **Puerto:** 5432

### Datasource WildFly
- **JNDI:** `java:/delacruzDS`
- **Persistence Unit:** `delacruzPU`

---

## Ejecutar con Docker

```bash
# 1. Compilar el proyecto
mvn clean package -DskipTests

# 2. Levantar los contenedores
docker-compose up --build

# 3. Para detener
docker-compose down
```

---

## URLs de Acceso

| Interfaz | URL |
|---|---|
| JSF/PrimeFaces | http://localhost:8080/demojk/pedidos.xhtml |
| API REST | http://localhost:8080/demojk/api/pedidos |
| Consola WildFly | http://localhost:9990 |

---

## API REST - Endpoints

| Método | URL | Descripción |
|---|---|---|
| GET | `/api/pedidos` | Lista todos los pedidos |
| GET | `/api/pedidos/{id}` | Obtiene un pedido por ID |
| POST | `/api/pedidos` | Crea un nuevo pedido |
| PUT | `/api/pedidos/{id}` | Actualiza un pedido |
| DELETE | `/api/pedidos/{id}` | Elimina un pedido |

### Ejemplo JSON

```json
{
  "cliente": "Juan Pérez",
  "descripcion": "Pedido de prueba",
  "cantidad": 3,
  "total": 150.00,
  "fecha": "2026-02-19"
}
```

---

## Tabla Pedido

| Campo | Tipo | Descripción |
|---|---|---|
| id | Serial (PK) | Autogenerado |
| cliente | String | Nombre del cliente |
| descripcion | String | Descripción del pedido |
| cantidad | Integer | Cantidad de productos |
| total | Double | Valor total |
| fecha | LocalDate | Fecha del pedido |