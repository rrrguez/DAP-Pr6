package pr6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaChatPrivado extends VentanaChat {
    protected Usuario usuario2;
    public VentanaChatPrivado(Usuario usuario, Usuario usuario2) {
        this.usuario = usuario;
        this.usuario2 = usuario2;

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
                usuario.enviarMensajePrivado(mensaje, usuario.ventanaPrivado);
                campoMensaje.setText("");
            }
        });

        JScrollPane scrollPane = new JScrollPane(areaChat);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(campoMensaje, BorderLayout.SOUTH);
    }
}
