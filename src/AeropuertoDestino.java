public class AeropuertoDestino {
    public enum UbiRelCiudad {
        NORTE, SUR, ESTE, OESTE
    }

    private int id; 
    private String nombre;
    private String ciudad;
    private String pais;
    private UbiRelCiudad ubiRelCiudad;

    public AeropuertoDestino(int id, String nombre, String ciudad, String pais, UbiRelCiudad ubiRelCiudad) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.ubiRelCiudad = ubiRelCiudad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public UbiRelCiudad getUbiRelCiudad() {
        return ubiRelCiudad;
    }

    public void setUbiRelCiudad(UbiRelCiudad ubiRelCiudad) {
        this.ubiRelCiudad = ubiRelCiudad;
    }

}
