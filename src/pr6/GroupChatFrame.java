package pr6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Ventana de chat de grupo
public class GroupChatFrame extends ChatFrame {
    // Constructor de la clase
    // El usuario que se pasa por parámetros es quien envía los mensajes en la ventana
    public GroupChatFrame(User user) {
        this.user = user;

        // Título y tamaño de la ventana
        setTitle(user.getName() + " hablando con el grupo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // Área donde van a a aparecer los mensajes enviados y recibidos en el chat
        areaChat = new JTextArea();
        areaChat.setEditable(false);

        // Campo donde irá el texto que se va a enviar al grupo
        // Al pulsar enter, se envía el mensaje
        messageField = new JTextField();
        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();
                user.sendGroupMessage(message, user.groupFrame);
                messageField.setText("");
            }
        });

        // Se actualiza el panel donde se muestran los mensajes
        JScrollPane scrollPane = new JScrollPane(areaChat);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(messageField, BorderLayout.SOUTH);
    }
}
