package es.icane.metadatos.test;

import es.icane.metadatos.SeriesNotFoundException;
import es.icane.metadatos.MetadataClient;
import es.icane.metadatos.model.TimeSeries;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
public class NodoTest {
    public static void main(String[] args) throws SeriesNotFoundException{
        MetadataClient cliente = new MetadataClient("http://192.168.0.133:8081/metadata/api");
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, Charset.forName("UTF-8")));
        TimeSeries n = cliente.getTimeSeries(138);
        out.println(cliente.getTimeSeriesAncestors(n));
        //out.println(cliente.getAllTimeSeries());
        out.flush();
        out.close();
    }
}
