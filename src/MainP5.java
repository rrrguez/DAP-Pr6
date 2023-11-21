import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
// Interfaz Mediator
interface Mediator {
    void enviarMensaje(String mensaje, Usuario remitente, List<Usuario> destinatarios);

    List<Usuario> getUsuarios();

    void iniciarConversacionGrupo(String nombreConversacion, List<Usuario> participantes);

    void iniciarConversacionPrivada(String claveConversacion, Usuario usuario1, Usuario usuario2);

    List<Usuario> getConversacionPrivada(String claveConversacion);

    String obtenerClaveConversacionPrivada(Usuario usuario1, Usuario usuario2);
}

// Clase concreta de Mediator
class ChatMediator implements Mediator {
    private List<Usuario> usuarios;
    private Map<String, List<Usuario>> conversacionesGrupo;
    private Map<String, List<Usuario>> conversacionesPrivadas;

    public ChatMediator() {
        this.usuarios = new ArrayList<>();
        this.conversacionesGrupo = new HashMap<>();
        this.conversacionesPrivadas = new HashMap<>();
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public void enviarMensaje(String mensaje, Usuario remitente, List<Usuario> destinatarios) {
        for (Usuario destinatario : destinatarios) {
            destinatario.recibirMensaje(remitente.getNombre() + ": " + mensaje);
        }
    }

    @Override
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    // Método para iniciar una conversación de grupo
    public void iniciarConversacionGrupo(String nombreConversacion, List<Usuario> participantes) {
        conversacionesGrupo.put(nombreConversacion, participantes);
    }

    // En la clase ChatMediator
    @Override
    public void iniciarConversacionPrivada(String claveConversacion, Usuario usuario1, Usuario usuario2) {
        List<Usuario> participantes = Arrays.asList(usuario1, usuario2);
        conversacionesPrivadas.put(claveConversacion, participantes);
    }


    // Método para obtener la clave de una conversación privada
    @Override
    public String obtenerClaveConversacionPrivada(Usuario usuario1, Usuario usuario2) {
        return usuario1.getNombre() + "_" + usuario2.getNombre();
    }

    // Método para obtener participantes de una conversación privada
    @Override
    public List<Usuario> getConversacionPrivada(String claveConversacion) {
        return conversacionesPrivadas.get(claveConversacion);
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

    public void iniciarConversacionPrivada(Usuario usuario2) {
    }
}

// Clase concreta de Usuario
class UsuarioConcreto extends Usuario {
    private Usuario usuario2;

    public UsuarioConcreto(Mediator mediator, String nombre) {
        super(mediator, nombre);
    }

    // Método para iniciar una conversación privada con otro usuario
    public void iniciarConversacionPrivada(Usuario usuario2) {
        this.usuario2 = usuario2;
        String claveConversacion = mediator.obtenerClaveConversacionPrivada(this, usuario2);
        mediator.iniciarConversacionPrivada(claveConversacion, this, usuario2);
        ventanaChat.setTitle("Chat privado con " + usuario2.getNombre());
    }

    // Método para enviar mensajes en una conversación privada
    @Override
    public void enviarMensaje(String mensaje) {
        String claveConversacion = mediator.obtenerClaveConversacionPrivada(this, usuario2);
        List<Usuario> participantes = mediator.getConversacionPrivada(claveConversacion);
        mediator.enviarMensaje(mensaje, this, participantes);
        ventanaChat.mostrarMensajeEnviado(mensaje);
    }

    // Método para recibir mensajes en una conversación privada
    @Override
    public void recibirMensaje(String mensaje) {
        ventanaChat.mostrarMensajeRecibido(mensaje);
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
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

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

// Ventana principal para seleccionar usuarios
class VentanaPrincipal extends JFrame {
    private Mediator mediator;
    private JComboBox<Usuario> usuariosComboBox;

    public VentanaPrincipal(Mediator mediator) {
        this.mediator = mediator;

        setTitle("Seleccionar Usuario");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        usuariosComboBox = new JComboBox<>();
        List<Usuario> usuarios = mediator.getUsuarios();
        for (Usuario usuario : usuarios) {
            usuariosComboBox.addItem(usuario);
        }

        JButton abrirChatButton = new JButton("Abrir Chat");
        abrirChatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario selectedUsuario = (Usuario) usuariosComboBox.getSelectedItem();
                if (selectedUsuario != null) {
                    selectedUsuario.ventanaChat.setVisible(true);
                }
            }
        });

        setLayout(new FlowLayout());
        add(usuariosComboBox);
        add(abrirChatButton);
    }
}

// Ventana para chatear de forma privada
class VentanaPrivada extends JFrame {
    private Mediator mediator;
    private JComboBox<Usuario> usuario1ComboBox;
    private JComboBox<Usuario> usuario2ComboBox;

    public VentanaPrivada(Mediator mediator) {
        this.mediator = mediator;

        setTitle("Seleccionar Usuarios para Chat Privado");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        usuario1ComboBox = new JComboBox<>();
        usuario2ComboBox = new JComboBox<>();

        List<Usuario> usuarios = mediator.getUsuarios();
        for (Usuario usuario : usuarios) {
            usuario1ComboBox.addItem(usuario);
            usuario2ComboBox.addItem(usuario);
        }

        JButton abrirChatButton = new JButton("Abrir Chat Privado");
        abrirChatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario usuario1 = (Usuario) usuario1ComboBox.getSelectedItem();
                Usuario usuario2 = (Usuario) usuario2ComboBox.getSelectedItem();

                if (usuario1 != null && usuario2 != null && usuario1 != usuario2) {
                    usuario1.iniciarConversacionPrivada(usuario2);
                }
            }
        });

        setLayout(new FlowLayout());
        add(new JLabel("Usuario 1:"));
        add(usuario1ComboBox);
        add(new JLabel("Usuario 2:"));
        add(usuario2ComboBox);
        add(abrirChatButton);
    }
}

// Clase principal que inicia la aplicación
class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatMediator chatMediator = new ChatMediator();

            UsuarioConcreto usuario1 = new UsuarioConcreto(chatMediator, "Usuario1");
            UsuarioConcreto usuario2 = new UsuarioConcreto(chatMediator, "Usuario2");
            UsuarioConcreto usuario3 = new UsuarioConcreto(chatMediator, "Usuario3");

            chatMediator.agregarUsuario(usuario1);
            chatMediator.agregarUsuario(usuario2);
            chatMediator.agregarUsuario(usuario3);

            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(chatMediator);
            ventanaPrincipal.setVisible(true);

            VentanaPrivada ventanaPrivada = new VentanaPrivada(chatMediator);
            ventanaPrivada.setVisible(true);
        });
    }
}
 */


