package es.icane.metadatos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
public class DateAdapter extends XmlAdapter<String, Date> {
    
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

    static {
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
    }
    

    @Override
    public Date unmarshal(String v) throws Exception {
        return dateFormat.parse(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        return dateFormat.format(v);
    }
        
}
