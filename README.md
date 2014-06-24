ICANE Metadata Client
=====================

This library provides a client for the [metadata REST web service](http://icane.es/metadata/) published by the [Statistics Agency of Cantabria](http://icane.es) (ICANE). The client abstracts the entities provided by the web service into Java objects for easier manipulation and integration in applications.

Building
--------
The repository can be opened using NetBeans 7 (and up). It can also be built using ```ant```:
```
ant dist
```

The resulting JAR will be created inside a ```dist``` directory.

Javadoc can also be compiled by running:

```
ant javadoc
```

Usage
-----

```
String baseUrl = "http://icane.es/metadata/api";
MetadataClient metadataClient = new MetadataClient(baseUrl);
try {
    TimeSeries timeSeries = cliente.getTimeSeries("demographic-indicators");
} catch (SeriesNotFoundException ex) {
    System.err.println("Series not found");
}
```

TimeSeries
----------
TimeSeries acts as a wildcard object for any node in the dataset/folder/series tree. It will behave according to its ```nodeType``` property, which in turn has a ```uriTag``` member that can take the following values:

* "time-series": A statistical time or cross-sectional series.
* "folder": A folder (branch node) in the series tree.
* "data-set": Another type of branch node which serves as an ancestor for a group of sub-folders and time series belonging to the same statistical collection.
* "document": A static document.
* "non-olap-native": A time series that was automatically migrated from a legacy database.
* "theme": Another type of branch node that groups data-sets or folders.

Whenever a TimeSeries object is of type "folder", "data-set" or "theme", its ```children``` property will contain the nodes (that are also TimeSeries objects) directly hanging from that branch. Otherwise, ```children``` will be an empty collection or ```null```.