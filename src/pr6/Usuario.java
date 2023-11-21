package pr6;

// Clase Usuario
abstract class Usuario {
    protected Mediator mediator;
    protected String nombre;
    protected VentanaChatPrivado ventanaPrivado;
    protected VentanaChatGrupo ventanaGrupo;

    public Usuario(Mediator mediator, String nombre) {
        this.mediator = mediator;
        this.nombre = nombre;
        this.ventanaPrivado = new VentanaChatPrivado(this, null);
        this.ventanaGrupo = new VentanaChatGrupo(this);
    }

    public String getNombre() {
        return nombre;
    }

    public void setUsuario2(Usuario usuario) {
        this.ventanaPrivado.usuario2 = usuario;
    }

    public abstract void recibirMensaje(String mensaje, VentanaChat ventana);

    public abstract void enviarMensajePrivado(String mensaje, VentanaChat ventana);

    public abstract void enviarMensajeGrupo(String mensaje, VentanaChat ventana);

}
