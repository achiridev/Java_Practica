package com.proyecto01;

public class ServiceLogin {
    private UsuariosDAO usuariosDAO;

    public ServiceLogin(UsuariosDAO usuariosDAO) {
        this.usuariosDAO = usuariosDAO;
    }

    public void loginInseguro(String username, String pass) {
        usuariosDAO.getUserByUsernameInseguro(username, pass)
                .ifPresentOrElse(
                    usuario -> mostrarMenuLogin(usuario),
                    () -> System.out.println("No se encontro tal usuario.")
                );
    }
    
    public void loginSeguro(String username, String pass) {
        usuariosDAO.getUserByUsername(username)
                .ifPresentOrElse(
                    usuario -> {
                        if (usuario.getPassword().equals(pass)) mostrarMenuLogin(usuario);
                        else System.out.println("Contraseña incorrecta.");
                    },
                    () -> System.out.println("No se encontro tal usuario.")
                );
    }
    public void mostrarMenuLogin(Usuario user) {
        System.out.println("Inicio de sesion existoso.");
        System.out.println("Bienvenido "+user.getUsername()+" !");
        System.out.println("Su gmail es: "+user.getEmail());
    }
}
