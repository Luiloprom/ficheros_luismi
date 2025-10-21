# Documentacion del Proyecto: Gestion de Ficheros de Videojuegos

---

## Introduccion

Este proyecto permite la gestion y manipulacion de un archivo binario de videojuegos usando Java. Ademas, se soporta la conversion de este archivo binario a XML y HTML, lectura con DOM y SAX, y el uso de una interfaz grafica sencilla (`JOptionPane`) para operar todas las funcionalidades. El codigo completo esta disponible en el repositorio [GitHub - Luiloprom/ficheros_luismi](https://github.com/Luiloprom/ficheros_luismi.git).

---

## Datos Guardados

- **Elemento principal:** Videojuego
- **Campos guardados:**
  - `codigo` (int): identificador del videojuego
  - `nombre` (String): nombre del videojuego
  - `plataforma` (String): plataforma principal
  - `precio` (float): precio del videojuego
  - `año` (int): año de lanzamiento

**Formatos:**
- Binario (`videojuegos.dat`)
- XML (`videojuegos.xml`)
- HTML (`videojuegos.html`) - tabla generada mediante XSLT
- Todos situados en la carpeta `src/main/resources`

---

## Programas y Su Funcionalidad


### 1. CrearFicheroLuismi.java


**Funcionalidad:**  
Crea el archivo binario `videojuegos.dat` mediante la escritura secuencial de una lista de objetos `VideojuegoLuismi`.


**Comentarios:**
-  Se crea una lista de videojuegos
-  Por cada videojuego, se escribe en el fichero con sus cinco campos
-  El fichero se guarda en resources con DataOutputStream

---


### 2. ModificarFicheroLuismi.java


**Funcionalidad:**  
Busca un videojuego por codigo y modifica sus datos.


**Comentarios:**
// Se lee todo el fichero y se almacena en una lista de objetos `VideojuegoLuismi`
// Si el codigo coincide, se modifican sus campos y se guarda en la lista.
// Se reescribe todo el fichero con los datos actualizados y escribiendo la lista completa de nuevo.



>*Nota: La reescritura completa evita problemas con los Strings de tamano variable usando writeUTF.*


---


### 3. CrearFicheroXMLLuismi.java


**Funcionalidad:**  
Convierte el archivo binario en un archivo XML usando DOM.


**Comentarios:**
// Se recorre el fichero binario y para cada videojuego se crea un nodo XML
// Se utiliza un metodo `crearElemento` para crear y anadir elementos hijos, evitando duplciar codigo




---


### 4. LeerFicheroXMLLuismi.java


**Funcionalidad:**  
Muestra el contenido del XML tanto con DOM como con SAX.


**Comentarios:**
// DOM: Recorre nodos y muestra campos por consola
// SAX: Utiliza un flag string y switch-case para identificar y mostrar cada campo dinamicamente sin booleanos repetidos


> Para el apartado de SAX me he ayudado de la IA.



---


### 5. TransformarXSLLuismi.java


**Funcionalidad:**  
Genera una tabla HTML a partir del XML utilizando una hoja de estilos XSLT (`videojuegos.xsl`).


**Comentarios:**
- Usa la API Transformer para aplicar la hoja XSLT y genera videojuegos.html
- La tabla incluye los campos nombre, plataforma y año
- El fichero xsl me ayude con IA para generarlo



---


### 6. AppLuismi.java


**Funcionalidad:**  
Menu grafico con `JOptionPane` para ejecutar todas las funcionalidades anteriores facilmente.


**Comentarios:**
// Antes de mostrar el menu borra todos los ficheros de resources excepto el .xsl
// El menu permite crear, modificar, exportar, leer (DOM/SAX), transformar y salir



---


## Ejemplo de Uso


1. El usuario selecciona "Crear fichero videojuegos.dat". Se genera un archivo dat con varios videojuegos.
2. El usuario puede modificar un videojuego introduciendo su codigo y los nuevos datos.
3. Se puede exportar el binario a XML y visualizar el resultado con DOM o SAX.
4. Puede transformar el XML a HTML mediante el XSLT.
5. La interfaz grafica permite que se vea todo mas visual y se pueda hacer todo junto por decirlo asi.


---


## Comentarios


- Todo el codigo esta debidamente comentado explicando el proposito de cada clase y metodo.
- Se emplean metodos auxiliares para evitar duplicacion (especialmente en el manejo de nodos DOM).
- La estructura del repositorio sigue las convenciones de Maven.
- Se toman precauciones para evitar corrupcion del archivo binario reescribiendo toda la lista tras modificar un registro.
- La limpieza de archivos evita borrar la hoja XSL.


---


## Requisitos y Compilacion


- Java 8+
- Maven instalado
- Extensiones Java y Lombok activas en Visual Studio Code
- Ejecutar por consola:
mvn clean compile
mvn exec:java -Dexec.mainClass="es.etg.dam.MainLuismi"



---


## Conclusion


Este proyecto demuestra una gestion completa de datos semiestructurados en Java:
- Escritura y edicion en binario,
- Exportacion y visualizacion XML (DOM/SAX),
- Transformacion XSLT a HTML
- Todo integrado con menu grafico simple.


El codigo esta preparado para ser educativo, facil de entender y ampliable para futuras mejoras.
