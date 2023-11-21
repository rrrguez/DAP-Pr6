package pr6;

import java.util.ArrayList;
import java.util.List;

// Clase concreta de Mediator
class ChatMediator implements Mediator {
    private List<Usuario> usuarios;

    public ChatMediator() {
        this.usuarios = new ArrayList<>();
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public void enviarMensajePrivado(String mensaje, Usuario usuario1, Usuario usuario2) {
        usuario2.recibirMensaje(usuario1.getNombre() + ": " + mensaje, usuario2.ventanaPrivado);
    }

    @Override
    public void enviarMensajeGrupo(String mensaje, Usuario usuario) {
        for (Usuario u : usuarios) {
            // Enviar el mensaje a todos los usuarios excepto al remitente
            if (u != usuario) {
                u.recibirMensaje(usuario.getNombre() + ": " + mensaje, u.ventanaGrupo);
            }
        }
    }

    @Override
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
