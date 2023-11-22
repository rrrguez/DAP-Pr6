package pr6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Ventana de chat privado
public class PrivateChatFrame extends ChatFrame {
    // Se declara al usuario con el que se va a tener el chat privado
    protected User user2;

    // Constructor de la clase
    // Se especifican los dos participantes del chat
    public PrivateChatFrame(User user, User user2) {
        this.user = user;
        this.user2 = user2;

        // Título y tamaño de la ventana del chat
        setTitle(user.getName() + " hablando con " + user2.getName());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // Área donde se muestran los mensajes enviados y recibidos en el chat
        areaChat = new JTextArea();
        areaChat.setEditable(false);

        // Campo para introducir el mensaje a enviar
        // Al pulsar enter, se envía el mensaje
        messageField = new JTextField();
        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensaje = messageField.getText();
                user.sendPrivateMessage(mensaje, user.privateFrame);
                messageField.setText("");
            }
        });

        // Se actualiza el panel de mensajes
        JScrollPane scrollPane = new JScrollPane(areaChat);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(messageField, BorderLayout.SOUTH);
    }
}
