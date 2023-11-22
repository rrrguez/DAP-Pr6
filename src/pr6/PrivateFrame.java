package pr6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Ventana para abrir los chats privados
public class PrivateFrame extends JFrame {
    // Menús desplegables para elegir los dos usuarios que van a chatear
    private JComboBox<User> user1ComboBox;
    private JComboBox<User> user2ComboBox;

    // Constructor de la clase
    public PrivateFrame(Mediator mediator) {
        // Se establece título y tamaño de la ventana
        setTitle("Seleccionar usuarios para chat privado");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // Menú desplegable para elegir los usuarios
        user1ComboBox = new JComboBox<>();
        user2ComboBox = new JComboBox<>();

        // Se añaden a los menús todos los usuarios
        List<User> users = mediator.getUsers();
        for (User user : users) {
            user1ComboBox.addItem(user);
            user2ComboBox.addItem(user);
        }

        // Botón para abrir los chats seleccionados
        JButton abrirChatButton = new JButton("Abrir chat privado");
        abrirChatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user1 = (User) user1ComboBox.getSelectedItem();
                User user2 = (User) user2ComboBox.getSelectedItem();

                // Se abren los chats privados de cada usuario que se ha seleccionado
                if (user1 != null && user2 != null && user1 != user2) {
                    user1.setUser2(user2);
                    user1.privateFrame.setVisible(true);
                    user2.setUser2(user1);
                    user2.privateFrame.setVisible(true);
                }
            }
        });

        // Etiquetas estéticas
        setLayout(new FlowLayout());
        add(new JLabel("User 1:"));
        add(user1ComboBox);
        add(new JLabel("User 2:"));
        add(user2ComboBox);
        add(abrirChatButton);
    }
}
