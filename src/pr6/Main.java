/**
 * Universidad de La Laguna
 * Escuela Superior de Ingeniería y Tecnología
 * Grado en Ingeniería Informática
 * Diseño Arquitectónico y Patrones
 * Práctica 6: El patrón Mediador
 */

package pr6;

import javax.swing.*;

// Clase principal que inicia la aplicación
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Se crea el mediador
            Mediator mediator = new MediatorChat();

            // Se crean los usuarios concretos del programa
            User abigail = new ConcreteUser(mediator, "Abigail");
            User marta = new ConcreteUser(mediator, "Marta");
            User zacarias = new ConcreteUser(mediator, "Zacarías");

            // Se establece que el mediador gestione a los usuarios creados
            mediator.addUser(abigail);
            mediator.addUser(marta);
            mediator.addUser(zacarias);

            // Se crea la ventana para abrir los chats de grupo
            GroupFrame groupFrame = new GroupFrame(mediator);
            groupFrame.setVisible(true);

            // Se crea la ventana para abrir los chats privados
            PrivateFrame privateFrame = new PrivateFrame(mediator);
            privateFrame.setVisible(true);
        });
    }
}
