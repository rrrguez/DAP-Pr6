import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
// Clase Mediator
interface ChatMediator {
    void sendMessage(String message, User user);

    void addUser(User user);
}

// Clase ConcreteMediator
class ChatMediatorImpl implements ChatMediator {
    private List<User> users;

    public ChatMediatorImpl() {
        this.users = new ArrayList<>();
    }

    @Override
    public void sendMessage(String message, User user) {
        for (User u : users) {
            // Verifica que el usuario no sea el remitente
            if (u != user) {
                u.receive(message);
            }
        }
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }
}

// Clase User
abstract class User extends JFrame {
    protected ChatMediator mediator;
    protected String name;
    protected JTextArea chatArea;
    protected JTextField inputField;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;

        // Configuración de la interfaz gráfica
        setTitle(name + "'s Chat Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        inputField = new JTextField();
        add(inputField, BorderLayout.SOUTH);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = inputField.getText();
                sendMessage(message);
                inputField.setText("");
                showSentMessage(message);
            }
        });
        add(sendButton, BorderLayout.EAST);

        setVisible(false); // Inicialmente, la ventana estará oculta
    }

    public abstract void receive(String message);

    public void sendMessage(String message) {
        mediator.sendMessage(name + ": " + message, this);
    }

    public void showSentMessage(String message) {
        chatArea.append("You: " + message + "\n");
    }
}

// Clase ConcreteUser
class ConcreteUser extends User {
    public ConcreteUser(ChatMediator mediator, String name) {
        super(mediator, name);
        mediator.addUser(this);
    }

    @Override
    public void receive(String message) {
        chatArea.append(message + "\n");
    }
}

// Clase Group
class Group extends JFrame {
    private List<User> users;

    public Group() {
        users = new ArrayList<>();
        setTitle("Chat Group");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 400);
        setLayout(new GridLayout(0, 1));
        setVisible(true);
    }

    public void addUser(User user) {
        users.add(user);
        JButton button = new JButton(user.name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openChatWindow(user);
            }
        });
        add(button);
        pack();
    }

    private void openChatWindow(User user) {
        for (User u : users) {
            // Oculta todas las ventanas de chat excepto la seleccionada
            u.setVisible(u == user);
        }
    }
}

class Main {
    public static void main(String[] args) {
        ChatMediator mediator = new ChatMediatorImpl();

        ConcreteUser user1 = new ConcreteUser(mediator, "User1");
        ConcreteUser user2 = new ConcreteUser(mediator, "User2");
        ConcreteUser user3 = new ConcreteUser(mediator, "User3");

        Group group = new Group();
        group.addUser(user1);
        group.addUser(user2);
        group.addUser(user3);
    }
}
*/