package es.icane.metadatos.modelo;

import es.icane.metadatos.DateAdapter;
import es.icane.metadatos.NullableIntAdapter;
import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
@XmlType
public class Periodo {

    private String id;
    private String mesInicio, mesFinal, annoInicio, annoFinal;
    private Integer numeroTrimestre, numeroSemestre;
    private Date created;
    private Date lastUpdated;

    @XmlAttribute
    @XmlID
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnnoFinal() {
        return annoFinal;
    }

    public void setAnnoFinal(String annoFinal) {
        this.annoFinal = annoFinal;
    }

    public String getAnnoInicio() {
        return annoInicio;
    }

    public void setAnnoInicio(String annoInicio) {
        this.annoInicio = annoInicio;
    }

    @XmlElement(name = "nombreMesFinal")
    public String getMesFinal() {
        return mesFinal;
    }

    public void setMesFinal(String mesFinal) {
        this.mesFinal = mesFinal;
    }

    @XmlElement(name = "nombreMesInicio")
    public String getMesInicio() {
        return mesInicio;
    }

    public void setMesInicio(String mesInicio) {
        this.mesInicio = mesInicio;
    }

    @XmlJavaTypeAdapter(value = NullableIntAdapter.class)
    public Integer getNumeroSemestre() {
        return numeroSemestre;
    }

    public void setNumeroSemestre(Integer numeroSemestre) {
        this.numeroSemestre = numeroSemestre;
    }

    @XmlJavaTypeAdapter(value = NullableIntAdapter.class)
    public Integer getNumeroTrimestre() {
        return numeroTrimestre;
    }

    public void setNumeroTrimestre(Integer numeroTrimestre) {
        this.numeroTrimestre = numeroTrimestre;
    }

    @XmlElement(name = "dateCreated")
    @XmlJavaTypeAdapter(value = DateAdapter.class)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @XmlJavaTypeAdapter(value = DateAdapter.class)
    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "Periodo{" + "id=" + id + ", mesInicio=" + mesInicio + ", mesFinal=" + mesFinal + ", annoInicio=" + annoInicio + ", annoFinal=" + annoFinal + ", numeroTrimestre=" + numeroTrimestre + ", numeroSemestre=" + numeroSemestre + ", created=" + created + ", lastUpdated=" + lastUpdated + '}';
    }
}
