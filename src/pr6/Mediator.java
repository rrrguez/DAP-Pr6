package pr6;

import java.util.List;

// Interfaz mediador
interface Mediator {
    // Método para enviar un mensaje por chat privado
    void sendPrivateMessage(String mensaje, User user1, User user2);
    // Método para enviar un mensaje por chat de grupo
    void sendGroupMessage(String mensaje, User user);
    // Método para añadir un usuario a la lista de usuarios del mediador
    void addUser(User user);
    // Getter
    List<User> getUsers();
}
