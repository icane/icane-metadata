package es.icane.metadatos.adapters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
public class DateAdapter extends XmlAdapter<String, Date> {
    
    // El formato real es 2012-12-20T22:44:55+02:00, pero SimpleDateFormat no admite TimeZones
    // con ':'
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    static {
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
    }
    

    @Override
    public Date unmarshal(String v) throws Exception {
        return dateFormat.parse(v.replaceFirst("([0-9]{2}):([0-9]{2})$", "$1$2"));
    }

    @Override
    public String marshal(Date v) throws Exception {
        return dateFormat.format(v).replaceFirst("([0-9]{2})([0-9]{2})$", "$1:$2");
    }
        
}
