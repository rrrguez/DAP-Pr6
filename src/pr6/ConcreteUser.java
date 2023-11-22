package pr6;

// Usuario concreto
public class ConcreteUser extends User {
    // Constructor de la clase
    // Se establece el mediador y el nombre del usuario
    public ConcreteUser(Mediator mediator, String name) {
        super(mediator, name);
    }

    // Para recibir el mensaje, se llama al método showReceivedMessage de la ventana del chat que corresponda
    @Override
    public void receiveMessage(String mensaje, ChatFrame frame) {
        frame.showReceivedMessage(mensaje);
    }

    // El mediador interviene para enviar los mensajes
    @Override
    public void sendPrivateMessage(String mensaje, ChatFrame frame) {
        mediator.sendPrivateMessage(mensaje, this, privateFrame.user2);
        frame.showSentMessage(mensaje);
    }

    // El mediador interviene para enviar los mensajes
    @Override
    public void sendGroupMessage(String mensaje, ChatFrame frame) {
        mediator.sendGroupMessage(mensaje, this);
        frame.showSentMessage(mensaje);
    }
}
