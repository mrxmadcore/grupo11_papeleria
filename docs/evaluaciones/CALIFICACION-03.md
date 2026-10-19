# Retroalimentación — Pilas y Colas en el proyecto de aula

**Grupo:** Grupo11 · **Proyecto:** Papelería

## Nota

| Criterio | Puntos máx. | Puntos |
|---|---|---|
| **Pilas** — `Pila<T>` correcta | 30 | 30 |
| Pilas — Casos límite | 10 | 10 |
| Pilas — Integración en `Service` | 30 | 17 |
| Pilas — Menú en `view/` | 15 | 10 |
| Pilas — Buenas prácticas | 15 | 8 |
| **Total Pilas** | 100 | **75** |
| **Colas** — `Cola<T>` correcta | 30 | 20 |
| Colas — Casos límite | 10 | 3 |
| Colas — Entidad dueña (`PedidosPendientes`) | 15 | 7 |
| Colas — Integración en `Service` | 25 | 3 |
| Colas — Menú en `view/` | 10 | 0 |
| Colas — Buenas prácticas | 10 | 4 |
| **Total Colas** | 100 | **37** |
| **Nota del laboratorio** | | **2.80** |

La nota se calcula así: (total de Pilas + total de Colas) / 2, dividido entre 20.

## 1. Pilas
**Lo que hicieron bien:**
- `Pila<T>` está muy bien hecha: `push`, `pop`, `peek`, `isEmpty` y `size` funcionan, sin ciclos y sin usar `java.util`.
- `pop` y `peek` en pila vacía lanzan el error correcto, y `push(null)` no daña la pila.
- La pila de ventas vive en `VentaService`, como pide la tabla de la Papelería, y anular una venta devuelve el stock.
- `MenuPilaView` usa `ConsoleUtils`, no tiene `try/catch` y revisa si hay ventas antes de anular o consultar.

**Lo que pueden mejorar:**
- Falta `listarOperaciones` (listar con la pila auxiliar) en el `Service`, y la opción 6 del menú solo dice "más adelante".
- El menú de pilas no está conectado en `App`: no se puede abrir al correr el programa.
- Al registrar una venta desde el menú se crea una venta vacía, sin productos; así anular no tiene efecto real sobre el stock.
- Los métodos del `Service` imprimen mensajes y devuelven `null`; la impresión es mejor dejarla en la `View`.
- La rama `feature/pila-en-el-proyecto` no existe: trabajaron en `development_sebastian`. Hay commits frecuentes y claros, y esa rama sí se fusionó a `main`.

## 2. Colas
**Lo que hicieron bien:**
- `Cola<T>` funciona: mantiene `frente`, `fin` y `tamano`, y volver a encolar tras vaciarla funciona bien.
- `PedidosPendientes` guarda la cola como atributo privado creado en el constructor y no tiene `setFila`.

**Lo que pueden mejorar:**
- En `Cola<T>` los métodos se llaman `estaVacia` y `peek`; el laboratorio pide `isEmpty` y `front`.
- `dequeue` y `peek` en una cola vacía devuelven `null`; deben lanzar `NoSuchElementException` con mensaje.
- `PedidosPendientes` no tiene el nombre/descripción ni `getFila()` del diagrama, y tiene lógica de atención y mensajes que no le corresponde (eso es del `Service`).
- Los cinco métodos del TAD y `listarEnEspera` no existen en el `Service`: `PedidoService` no usa la cola.
- No hay `MenuColaView` ni conexión en `App`, así que no se puede probar la fila desde la consola.
- La rama `feature/cola-en-el-proyecto` no existe y los commits de la cola fueron pocos y directos a `main`.
