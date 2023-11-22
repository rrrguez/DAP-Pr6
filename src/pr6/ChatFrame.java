package pr6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Ventana de chat genérica
public class ChatFrame extends JFrame {
    // Área donde se muestran los mensajes enviados y recibidos
    protected JTextArea areaChat;
    // Área donde se escribirá el mensaje a enviar
    protected JTextField messageField;
    // Usuario que envía los mensajes
    protected User user;

    // Método para mostrar los mensajes recibidos en el área de mensajes
    // Simplemente se añade el nuevo mensaje al área de mensajes
    public void showReceivedMessage(String mensaje) {
        areaChat.append(mensaje + "\n");
    }

    // Método para mostrar los mensajes enviados en el área de mensajes
    // Simplemente se añade el nuevo mensaje al área de mensajes
    public void showSentMessage(String mensaje) {
        areaChat.append("Yo: " + mensaje + "\n");
    }
}