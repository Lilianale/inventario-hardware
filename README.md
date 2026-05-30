# Inventario Hardware - Spring Boot-LILIANA LESANO

##  Descripción
Este proyecto gestiona el inventario de hardware usando Spring Boot Y PostgreSQL.

Se procesan 10.000 registros de equipos tecnológicos para generar reportes por categoría.

---

## Tecnologías usadas
- Java 21
- Spring Boot 3
- PostgreSQL
- Hibernate / JPA
- Streams API

---

## Problema resuelto

El sistema analiza 10.000 registros de hardware y debe:

- Filtrar datos
- Agrupar por categoría
- Calcular total de precios
- Calcular promedio
- Obtener el equipo más caro

---

## Implementación

### Paradigma Imperativo
- Uso de for, if, else
- Acumuladores manuales
- Control explícito del flujo

### Paradigma Funcional
- Uso de Streams API
- groupingBy, map, reduce
- Código más limpio y declarativo

---

## Comparación

| Criterio | Imperativo | Funcional |
|----------|------------|-----------|
| Código | Más largo | Más corto |
| Legibilidad | Media | Alta |
| Mantenimiento | Más difícil | Más fácil |
| Estilo | Secuencial | Declarativo |

---

## Conclusión

El paradigma imperativo permite control detallado del proceso, pero se necesita de la ejecuicòn de más código.

El paradigma funcional mejora la legibilidad y reduce la complejidad, siendo más adecuado para grandes volúmenes de datos.

---

## Repositorio- LILIANA LESANO
https://github.com/Lilianale/inventario-hardware.git