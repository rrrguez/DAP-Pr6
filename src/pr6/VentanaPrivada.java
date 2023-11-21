package pr6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Ventana para chatear de forma privada
class VentanaPrivada extends JFrame {
    private JComboBox<Usuario> usuario1ComboBox;
    private JComboBox<Usuario> usuario2ComboBox;

    public VentanaPrivada(Mediator mediator) {
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
                    usuario1.ventanaPrivado.setVisible(true);
                    usuario1.setUsuario2(usuario2);
                    usuario2.ventanaPrivado.setVisible(true);
                    usuario2.setUsuario2(usuario1);

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
