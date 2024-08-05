
# Franchise Management API

Este proyecto es una API de gestión de franquicias desarrollada con Java, Spring Boot, MySQL y Docker. La API permite gestionar franquicias, sucursales y productos.

# Características

Franquicias: Gestiona franquicias que consisten en un nombre y una lista de sucursales.

Sucursales: Cada sucursal tiene un nombre y una lista de productos.

Productos: Cada producto tiene un nombre y una cantidad de stock.






## Instalación del Proyecto

Descargar el proyecto, el proycto se debe llamar

```bash
  nequi-technical-test
```

Si al descargarlo directamente por github web el proyecto le aparece con otro nombre, debe modificar la carpeta de descarga de la siguiente manera

```bash
  nequi-technical-test-master --> nequi-technical-test
```

Levantar el contenedor de docker

```bash
  docker-compose up -d
```

Ubicarse en la raiz del proyecto y ejecutar el siguiente comando

```bash
  mvn clean install
```

Sobre la raiz del proyecto ejecutar el siguiente comando para arrancar el proyecto.

```bash
  mvn spring-boot:run 
```

Sobre Postman ejecutar el siguiente endpoint para verificar que el proyecto este corriendo

```bash
GET http://localhost:8080/api/ping

```

Respuesta
```bash
Pong >>> It works!
```
    
## API's

#### Agregar una nueva franquicia

Endpoint
```bash
  POST http://localhost:8080/api/franchises

```
Body
```bash
{
  "name": "Franchise A"
}

```
Respuesta
```bash
{
  "id": 1,
  "name": "Franchise A",
  "branches": []
}

```
#### Agregar una nueva sucursal a una franquicia

```bash
  POST http://localhost:8080/api/franchises/{franchiseId}/branches
```
Body
```bash
{
  "name": "Branch A"
}

```
Respuesta
```bash
{
  "id": 1,
  "name": "Branch A",
  "franchise": {
    "id": 1,
    "name": "Franchise A"
  }
}
```

#### Agregar un nuevo producto a una sucursal

```bash
  POST http://localhost:8080/api/franchises/branches/{branchId}/products
```
Body
```bash
{
  "name": "Product A",
  "stock": 100
}

```
Respuesta
```bash
{
  "id": 1,
  "name": "Product A",
  "stock": 100,
  "branch": {
    "id": 1,
    "name": "Branch A"
  }
}
```

#### Mostrar el producto con más stock por sucursal para una franquicia

```bash
  GET http://localhost:8080/api/franchises/{franchiseId}/top-stock-products
```

Respuesta
```bash
[
  {
    "branch": {
      "id": 1,
      "name": "Branch A"
    },
    "product": {
      "id": 1,
      "name": "Product A",
      "stock": 150
    }
  },
  {
    "branch": {
      "id": 2,
      "name": "Branch B"
    },
    "product": {
      "id": 2,
      "name": "Product B",
      "stock": 200
    }
  }
]
```

#### Eliminar un producto de una sucursal

```bash
  DELETE http://localhost:8080/api/franchises/products/{productId}
```

Respuesta
```bash
200
```

#### Modificar la información de un producto
```bash
  PUT http://localhost:8080/api/franchises/products/{productId}
```

Body
```bash
{
  "name": "Product A1",
  "stock": 90
}

```
Respuesta
```bash
{
    "id": 1,
    "name": Product A1,
    "stock": 90
}
```



