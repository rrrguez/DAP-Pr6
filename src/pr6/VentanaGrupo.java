package pr6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Ventana principal para seleccionar usuarios
class VentanaGrupo extends JFrame {
    private Mediator mediator;
    private JComboBox<Usuario> usuariosComboBox;

    public VentanaGrupo(Mediator mediator) {
        this.mediator = mediator;

        setTitle("Seleccionar Usuario");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        usuariosComboBox = new JComboBox<>();
        List<Usuario> usuarios = mediator.getUsuarios();
        for (Usuario usuario : usuarios) {
            usuariosComboBox.addItem(usuario);
        }

        JButton abrirChatButton = new JButton("Abrir Chat Grupo");
        abrirChatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario selectedUsuario = (Usuario) usuariosComboBox.getSelectedItem();
                if (selectedUsuario != null) {
                    selectedUsuario.ventanaGrupo.setVisible(true);
                }
            }
        });

        setLayout(new FlowLayout());
        add(usuariosComboBox);
        add(abrirChatButton);
    }
}
