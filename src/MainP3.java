import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
// Interfaz Mediator
interface Mediator {
    void enviarMensaje(String mensaje, Usuario usuario);
}

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
    public void enviarMensaje(String mensaje, Usuario usuario) {
        for (Usuario u : usuarios) {
            // Enviar el mensaje a todos los usuarios excepto al remitente
            if (u != usuario) {
                u.recibirMensaje(usuario.getNombre() + ": " + mensaje);
            }
        }
    }
}

// Clase Usuario
abstract class Usuario {
    protected Mediator mediator;
    protected String nombre;
    protected VentanaChat ventanaChat;

    public Usuario(Mediator mediator, String nombre) {
        this.mediator = mediator;
        this.nombre = nombre;
        this.ventanaChat = new VentanaChat(this);
    }

    public String getNombre() {
        return nombre;
    }

    public abstract void recibirMensaje(String mensaje);

    public abstract void enviarMensaje(String mensaje);
}

// Clase concreta de Usuario
class UsuarioConcreto extends Usuario {
    public UsuarioConcreto(Mediator mediator, String nombre) {
        super(mediator, nombre);
    }

    @Override
    public void recibirMensaje(String mensaje) {
        ventanaChat.mostrarMensajeRecibido(mensaje);
    }

    @Override
    public void enviarMensaje(String mensaje) {
        mediator.enviarMensaje(mensaje, this);
        ventanaChat.mostrarMensajeEnviado(mensaje);
    }
}

// Ventana de chat
class VentanaChat extends JFrame {
    private JTextArea areaChat;
    private JTextField campoMensaje;
    private Usuario usuario;

    public VentanaChat(Usuario usuario) {
        this.usuario = usuario;

        setTitle(usuario.getNombre());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        areaChat = new JTextArea();
        areaChat.setEditable(false);

        campoMensaje = new JTextField();
        campoMensaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensaje = campoMensaje.getText();
                usuario.enviarMensaje(mensaje);
                campoMensaje.setText("");
            }
        });

        JScrollPane scrollPane = new JScrollPane(areaChat);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(campoMensaje, BorderLayout.SOUTH);
    }

    public void mostrarMensajeRecibido(String mensaje) {
        areaChat.append(mensaje + "\n");
    }

    public void mostrarMensajeEnviado(String mensaje) {
        areaChat.append("Yo: " + mensaje + "\n");
    }
}

// Clase principal que inicia la aplicación
class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatMediator chatMediator = new ChatMediator();

            UsuarioConcreto usuario1 = new UsuarioConcreto(chatMediator, "Usuario1");
            UsuarioConcreto usuario2 = new UsuarioConcreto(chatMediator, "Usuario2");

            chatMediator.agregarUsuario(usuario1);
            chatMediator.agregarUsuario(usuario2);

            usuario1.ventanaChat.setVisible(true);
            usuario2.ventanaChat.setVisible(true);
        });
    }
}
*/

