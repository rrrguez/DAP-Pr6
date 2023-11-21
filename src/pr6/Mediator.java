package pr6;

import java.util.List;

interface Mediator {
    void enviarMensajePrivado(String mensaje, Usuario usuario1, Usuario usuario2);
    void enviarMensajeGrupo(String mensaje, Usuario usuario);


    List<Usuario> getUsuarios();
}
