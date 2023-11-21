package pr6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaChatGrupo extends VentanaChat {
    public VentanaChatGrupo(Usuario usuario) {
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
                usuario.enviarMensajeGrupo(mensaje, usuario.ventanaGrupo);
                campoMensaje.setText("");
            }
        });

        JScrollPane scrollPane = new JScrollPane(areaChat);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(campoMensaje, BorderLayout.SOUTH);
    }
}
