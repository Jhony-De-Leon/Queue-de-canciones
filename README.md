# Queue-de-canciones

Proyecto académico desarrollado en Programación III.

Es un simulador de reproductor musical utilizando estructuras de datos (Queue)
con manejo de prioridad y simulación de duración en tiempo real.

---

# 1. Cómo compilar la librería

Si el proyecto contiene una librería separada (por ejemplo la estructura LinkedQueue),
debe compilarse primero. Desde la carpeta raíz de la librería ejecutar: mvn clean package

Esto generará el archivo `.jar` dentro de la carpeta: target/

---

# 2. Cómo instalar la librería en local

Para instalar la librería en el repositorio local de Maven ejecutar: mvn install

Con esto el `.jar` quedará disponible en el repositorio local:

C:\Users\TU_USUARIO\.m2\repository

Luego puede ser utilizado como dependencia en otros proyectos.

---

# ⚙ 3. Cómo compilar el handler "MusicPlayer"

Ubicarse en la carpeta raíz del proyecto principal y ejecutar: mvn clean package

Esto generará el archivo ejecutable dentro de: target/

Ejemplo:

target/queueHandler-1.0-SNAPSHOT

---

# ▶ 4. Cómo ejecutar desde consola

Desde la carpeta `target` ejecutar:

java -cp "queueHandler-1.0-SNAPSHOT.jar;data-structure-queue-1.0-SNAPSHOT.jar" umg.edu.gt.MusicPlayer

El sistema iniciará la simulación del reproductor.

Para saltar una canción durante reproducción:

Escribir: "s" y presionar ENTER.

---

# 🏗 Explicación del diseño

El sistema fue diseñado siguiendo los siguientes principios:

- Uso de estructura de datos tipo Queue (FIFO).
- Separación de responsabilidades:
  - Clase `Song` que contiene el modelo de datos.
  - Clase `MusicPlayer` donde se encuentra la lógica principal.
  - `LinkedQueue` la cual es la estructura de almacenamiento.
- Simulación controlada mediante la cual nos permite ver como avanzan las canciones `Thread.sleep()`.

Se implementaron dos colas:

- Cola de prioridad alta
- Cola de prioridad normal

Además se creó:

- Cola de historial de canciones reproducidas

---

# Decisiones técnicas

1. Se utilizó una estructura `LinkedQueue` para cumplir el principio FIFO.
2. No se utilizó ordenamiento para respetar el modelo de colas.
3. Se implementó un Thread adicional para permitir el método `skip()` interactivo.
4. Se utilizó variable compartida `skipRequested` para controlar interrupción.
5. Se marcó el hilo como `daemon` para que finalice junto al programa.

---

# Cómo se implementó la prioridad

Se manejaron dos colas independientes:

- `highPriority`
- `normalPriority`

Al agregar una canción:

Si prioridad == 1 Se encola en `highPriority`
Si prioridad == 2 Se encola en `normalPriority`

Durante la reproducción:

1. Siempre se verifica primero si hay canciones en `highPriority`.
2. Solo si está vacía se atiende `normalPriority`.

Esto garantiza prioridad sin necesidad de ordenamiento.

---

# Cómo se manejó la simulación de duración

La duración se simula usando:

Thread.sleep(1000);

Por cada segundo definido en la canción.

Ejemplo:

for (int i = 1; i <= duration; i++) {
    Thread.sleep(1000);
}

Esto nos permitio:

- Mostrar progreso segundo a segundo
- Permitir interrupción mediante `skip()`
- Simular un reproductor en tiempo real

---


