package com.mycompany.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Exec {
    private static final UserDao userDao = new UserDao();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int option = -1;

            while (option != 0) {
                printMenu();
                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                    scanner.nextLine();
                    continue;
                }

                switch (option) {
                    case 1 -> createUser(scanner);
                    case 2 -> updateUser(scanner);
                    case 3 -> findUserById(scanner);
                    case 4 -> listAllUsers();
                    case 5 -> deleteUser(scanner);
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida.");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("Menu:");
        System.out.println("1 - Criar usuário");
        System.out.println("2 - Atualizar usuário");
        System.out.println("3 - Buscar usuário por ID");
        System.out.println("4 - Listar todos os usuários");
        System.out.println("5 - Deletar usuário");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void createUser(Scanner scanner) {
        System.out.println("Criação de usuário:");
        System.out.print("Nome: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();
        System.out.print("Último acesso (yyyy-MM-dd HH:mm:ss): ");
        String lastAccessStr = scanner.nextLine();
        LocalDateTime lastAccess = parseDate(lastAccessStr);
        System.out.print("Ativo (true/false): ");
        Boolean active = Boolean.parseBoolean(scanner.nextLine());

        User user = new User(name, email, password, lastAccess, active);
        userDao.save(user);
        System.out.println("Usuário criado com sucesso.");
    }

    private static void updateUser(Scanner scanner) {
        System.out.print("ID do usuário a atualizar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        User user = userDao.findById(id);
        if (user == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.print("Novo nome (" + user.getName() + "): ");
        String name = scanner.nextLine();
        user.setName(name.isEmpty() ? user.getName() : name);
        System.out.print("Novo email (" + user.getEmail() + "): ");
        String email = scanner.nextLine();
        user.setEmail(email.isEmpty() ? user.getEmail() : email);
        System.out.print("Nova senha: ");
        String password = scanner.nextLine();
        user.setPassword(password.isEmpty() ? user.getPassword() : password);
        System.out.print("Novo último acesso (" + user.getLastAccess() + "): ");
        String lastAccessStr = scanner.nextLine();
        if (!lastAccessStr.isEmpty()) {
            user.setLastAccess(parseDate(lastAccessStr));
        }
        System.out.print("Ativo (true/false) (" + user.isActive() + "): ");
        String activeStr = scanner.nextLine();
        user.setActive(activeStr.isEmpty() ? user.isActive() : Boolean.parseBoolean(activeStr));

        userDao.update(user);
        System.out.println("Usuário atualizado com sucesso.");
    }

    private static void findUserById(Scanner scanner) {
        System.out.print("ID do usuário: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        User user = userDao.findById(id);
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    private static void listAllUsers() {
        List<User> users = userDao.findAll();
        if (users.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
        } else {
            users.forEach(System.out::println);
        }
    }

    private static void deleteUser(Scanner scanner) {
        System.out.print("ID do usuário a deletar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        userDao.delete(id);
        System.out.println("Usuário deletado com sucesso.");
    }

    private static LocalDateTime parseDate(String dateStr) {
        try {
            return LocalDateTime.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Erro ao parsear data: " + e.getMessage());
            return null;
        }
    }
}