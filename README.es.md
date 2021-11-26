Cliente de metadatos ICANE
==========================

Esta librería permite crear clientes para el [web service REST de metadatos](http://icane.es/metadata) ofrecido por el [Instituto Cántabro de Estadística](http://icane.es) (ICANE). El cliente abstrae las entidades con las que trabaja el servicio web en objetos Java para facilitar su manipulación e integración en aplicaciones.

Compilación
-----------
Este repositorio se puede abrir como projecto de NetBeans 7 (y superiores). También se puede compilar usando ```ant```:
```
ant dist
```

El JAR resultante se creará en un directorio llamado ```dist```, que también contendrá un directorio ```lib``` con librerías adicionales requeridas.

También se puede compilar la documentación en formato Javadoc.

```
ant javadoc
```

Uso
---
Las librerías ```jersey-core``` y ```jersey-client``` (versión 1.17 o superior, incluidas en el repositorio) deberán estar presentes en el classpath para que funcione el cliente.
```
String baseUrl = "http://icane.es/metadata/api";
MetadataClient metadataClient = new MetadataClient(baseUrl);
try {
    TimeSeries timeSeries = cliente.getTimeSeries("demographic-indicators");
} catch (SeriesNotFoundException ex) {
    System.err.println("Serie no encontrada");
}
```

TimeSeries
----------
TimeSeries actúa como un objeto comodín para cualquier nodo en el árbol de estadísticas/carpetas/series. Su comportamiento dependerá de su propiedad ```nodeType```, que a su vez ofrece un miembro ```uriTag``` que puede tomar los siguientes valores:

* "time-series": Una serie estadística.
* "folder": Una carpeta (nodo rama) en el árbol de series.
* "data-set": Otro tipo de nodo rama que sirve como ancestro común para un grupo de subcarpetas y series temporales que pertenecen a la misma colección estadística.
* "document": Un documento estático.
* "url": Power BI url.
* "non-olap-native": Una serie temporal automáticamente migrada de una base de datos antigua (legacy).
* "theme": Otro tipo de nodo rama que agrupa estadísticas, carpetas y series.

Cuando un objeto TimeSeries sea de tipo "folder", "data-set" o "theme", su propiedad ```children``` contendrá sus nodos hijos (descendientes directos, también de clase TimeSeries). En cualquier otro caso, ```children``` será una colección vacía o ```null```.