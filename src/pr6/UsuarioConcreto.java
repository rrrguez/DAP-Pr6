package pr6;

// Clase concreta de Usuario
class UsuarioConcreto extends Usuario {
    public UsuarioConcreto(Mediator mediator, String nombre) {
        super(mediator, nombre);
    }

    @Override
    public void recibirMensaje(String mensaje, VentanaChat ventana) {
        ventana.mostrarMensajeRecibido(mensaje);
    }

    @Override
    public void enviarMensajePrivado(String mensaje, VentanaChat ventana) {
        mediator.enviarMensajePrivado(mensaje, this, ventanaPrivado.usuario2);
        ventana.mostrarMensajeEnviado(mensaje);
    }

    @Override
    public void enviarMensajeGrupo(String mensaje, VentanaChat ventana) {
        mediator.enviarMensajeGrupo(mensaje, this);
        ventana.mostrarMensajeEnviado(mensaje);
    }
}
