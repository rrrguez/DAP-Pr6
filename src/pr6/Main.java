package pr6;

import javax.swing.*;

// Clase principal que inicia la aplicación
class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatMediator chatMediator = new ChatMediator();

            UsuarioConcreto abigail = new UsuarioConcreto(chatMediator, "Abigail");
            UsuarioConcreto marta = new UsuarioConcreto(chatMediator, "Marta");
            UsuarioConcreto zacarias = new UsuarioConcreto(chatMediator, "Zacarías");

            chatMediator.agregarUsuario(abigail);
            chatMediator.agregarUsuario(marta);
            chatMediator.agregarUsuario(zacarias);

            VentanaGrupo ventanaGrupo = new VentanaGrupo(chatMediator);
            ventanaGrupo.setVisible(true);

            VentanaPrivada ventanaPrivada = new VentanaPrivada(chatMediator);
            ventanaPrivada.setVisible(true);
        });
    }
}
