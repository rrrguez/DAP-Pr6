package pr6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Ventana de chat
class VentanaChat extends JFrame {
    protected JTextArea areaChat;
    protected JTextField campoMensaje;
    protected Usuario usuario;


    public void mostrarMensajeRecibido(String mensaje) {
        areaChat.append(mensaje + "\n");
    }

    public void mostrarMensajeEnviado(String mensaje) {
        areaChat.append("Yo: " + mensaje + "\n");
    }
}