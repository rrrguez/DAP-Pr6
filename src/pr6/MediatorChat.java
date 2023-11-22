package pr6;

import java.util.ArrayList;
import java.util.List;

// Clase concreta de mediador
public class MediatorChat implements Mediator {
    // Lista de usuarios que necesitan intervención del mediador
    // Esto sirve de cara a la interfaz, pero no es necesario
    private List<User> users;

    // Constructor
    public MediatorChat() {
        this.users = new ArrayList<>();
    }

    // Método para añadir un usuario a la lista de usuarios
    public void addUser(User user) {
        users.add(user);
    }

    // Método para enviar mensajes mediante chat privado
    // Se encarga de hacer que el otro usuario reciba el mensaje del primer usuario
    @Override
    public void sendPrivateMessage(String mensaje, User user1, User user2) {
        user2.receiveMessage(user1.getName() + ": " + mensaje, user2.privateFrame);
    }

    // Método para enviar mensajes mediante chat de grupo
    // Se encarga de hacer que el grupo reciba el mensaje enviado por el usuario que lo ha enviado
    @Override
    public void sendGroupMessage(String mensaje, User user) {
        for (User u : users) {
            // Se envía el mensaje a todos los usuarios excepto al remitente
            // Se recibe solo en el chat de grupo
            if (u != user) {
                u.receiveMessage(user.getName() + ": " + mensaje, u.groupFrame);
            }
        }
    }

    // Getter
    @Override
    public List<User> getUsers() {
        return users;
    }
}
