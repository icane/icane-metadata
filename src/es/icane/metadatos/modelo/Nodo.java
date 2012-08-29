package es.icane.metadatos.modelo;

import es.icane.metadatos.DateAdapter;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
@XmlType
public class Nodo {

    private String id;
    private String idSemantico;
    private String codigo;
    private String titulo;
    private int lft, rgt;
    private boolean activo;
    private String url;
    private Date ultimaActualizacion;
    private String notasPie;
    private String temas;
    private AreaTematica area;
    private Estadistica estadistica;
    private Periodicidad periodicidad;
    private Periodo periodoInicial, periodoFinal;
    private List<Fuente> fuentes;
    private List<AmbitoTerritorial> ambitosTerritoriales;

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @XmlAttribute
    @XmlID
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdSemantico() {
        return idSemantico;
    }

    public void setIdSemantico(String idSemantico) {
        this.idSemantico = idSemantico;
    }

    public int getLft() {
        return lft;
    }

    public void setLft(int lft) {
        this.lft = lft;
    }

    public String getNotasPie() {
        return notasPie;
    }

    public void setNotasPie(String notasPie) {
        this.notasPie = notasPie;
    }

    public int getRgt() {
        return rgt;
    }

    public void setRgt(int rgt) {
        this.rgt = rgt;
    }

    public String getTemas() {
        return temas;
    }

    public void setTemas(String temas) {
        this.temas = temas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @XmlJavaTypeAdapter(value = DateAdapter.class)
    public Date getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(Date ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlElement(name = "areaTematica")
    public AreaTematica getArea() {
        return area;
    }

    public void setArea(AreaTematica area) {
        this.area = area;
    }

    public Estadistica getEstadistica() {
        return estadistica;
    }

    public void setEstadistica(Estadistica estadistica) {
        this.estadistica = estadistica;
    }

    public Periodicidad getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(Periodicidad periodicidad) {
        this.periodicidad = periodicidad;
    }

    public Periodo getPeriodoFinal() {
        return periodoFinal;
    }

    public void setPeriodoFinal(Periodo periodoFinal) {
        this.periodoFinal = periodoFinal;
    }

    public Periodo getPeriodoInicial() {
        return periodoInicial;
    }

    public void setPeriodoInicial(Periodo periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    @XmlElementWrapper
    @XmlElement(name = "fuente")
    public List<Fuente> getFuentes() {
        return fuentes;
    }

    public void setFuentes(List<Fuente> fuentes) {
        this.fuentes = fuentes;
    }

    @XmlElementWrapper
    @XmlElement(name = "ambitoTerritorial")
    public List<AmbitoTerritorial> getAmbitosTerritoriales() {
        return ambitosTerritoriales;
    }

    public void setAmbitosTerritoriales(List<AmbitoTerritorial> ambitosTerritoriales) {
        this.ambitosTerritoriales = ambitosTerritoriales;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Nodo - id ").append(id);
        sb.append("\n  ").append("idSemantico: ").append(idSemantico);
        sb.append("\n  ").append("codigo: ").append(codigo);
        sb.append("\n  ").append("titulo: ").append(titulo);
        sb.append("\n  ").append("lft/rgt: ").append(lft).append("/").append(rgt);
        sb.append("\n  ").append("activo: ").append(activo);
        sb.append("\n  ").append("url: ").append(url);
        sb.append("\n  ").append("ultimaActualizacion: ").append(ultimaActualizacion);
        sb.append("\n  ").append("notasPie: ").append(notasPie);
        sb.append("\n  ").append("temas: ").append(temas);
        sb.append("\n  ").append("area: ").append(area);
        sb.append("\n  ").append("estadistica: ").append(estadistica);
        sb.append("\n  ").append("periodicidad: ").append(periodicidad);
        sb.append("\n  ").append("periodoInicial: ").append(periodoInicial);
        sb.append("\n  ").append("periodoFinal: ").append(periodoFinal);
        sb.append("\n  Fuentes:");
        for (Fuente f : fuentes) {
            sb.append("\n   - ").append(f);
        }
        sb.append("\n  Ámbitos territoriales:");
        for (AmbitoTerritorial a : ambitosTerritoriales) {
            sb.append("\n   - ").append(a);
        }
        return sb.toString();
    }
}
