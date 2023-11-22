package pr6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Ventana para seleccionar la ventana de grupo que se va a abrir
public class GroupFrame extends JFrame {
    private JComboBox<User> usersComboBox;

    // Constructor de la clase
    public GroupFrame(Mediator mediator) {
        // Título y tamaño de la ventana
        setTitle("Seleccionar usuario");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menú desplegable para seleccionar el usuario
        // Se incluyen en el menú todos los usuarios que tiene asignado el mediador
        usersComboBox = new JComboBox<>();
        List<User> users = mediator.getUsers();
        for (User user : users) {
            usersComboBox.addItem(user);
        }

        // Botón para abrir el chat
        JButton abrirChatButton = new JButton("Abrir chat de grupo");
        abrirChatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User selectedUser = (User) usersComboBox.getSelectedItem();
                if (selectedUser != null) {
                    // Se abre el chat del usuario seleccionado
                    selectedUser.groupFrame.setVisible(true);
                }
            }
        });

        setLayout(new FlowLayout());
        add(usersComboBox);
        add(abrirChatButton);
    }
}
