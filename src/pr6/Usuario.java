package pr6;

// Clase usuario
abstract class User {
    // Mediador del usuario
    protected Mediator mediator;
    // Nombre del usuario
    protected String name;
    // Chat privado del usuario
    protected PrivateChatFrame privateFrame;
    // Chat de grupo del usuario
    protected GroupChatFrame groupFrame;

    // Constructor de la clase
    public User(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
        // this.ventanaPrivado = new privateChatFrame(this, null);
        this.groupFrame = new GroupChatFrame(this);
    }

    // Getter
    public String getName() {
        return name;
    }

    // Setter
    // Se establece el usuario con el que intercambia mensajes en el chat privado
    public void setUser2(User user) {
        // this.ventanaPrivado.user2 = user;
        this.privateFrame = new PrivateChatFrame(this, user);
    }

    // Métodos abstractos
    public abstract void receiveMessage(String mensaje, ChatFrame ventana);

    public abstract void sendPrivateMessage(String mensaje, ChatFrame ventana);

    public abstract void sendGroupMessage(String mensaje, ChatFrame ventana);

}
