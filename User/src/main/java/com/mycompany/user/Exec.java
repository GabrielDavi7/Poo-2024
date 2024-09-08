package com.mycompany.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Exec {
    private static final UserDao userDao = new UserDao();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
                    case 4 -> findAllUsers();
                    case 5 -> deleteUser(scanner);
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbConnection.getInstance().disconnect();
        }
    }

    private static void printMenu() {
        System.out.println("\nMenu de Opções:");
        System.out.println("1. Criar Usuário");
        System.out.println("2. Atualizar Usuário");
        System.out.println("3. Buscar Usuário por ID");
        System.out.println("4. Listar Todos os Usuários");
        System.out.println("5. Deletar Usuário");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void createUser(Scanner scanner) {
        System.out.print("Digite o nome: ");
        String name = scanner.nextLine();
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String password = scanner.nextLine();
        System.out.print("Digite o último acesso (YYYY-MM-DD): ");
        String lastAccessString = scanner.nextLine();
        System.out.print("Usuário ativo (true/false): ");
        boolean active = scanner.nextBoolean();
        scanner.nextLine();

        LocalDateTime lastAccess = null;
        try {
            lastAccess = LocalDateTime.parse(lastAccessString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido.");
            return;
        }

        User user = new User(name, email, password, lastAccess, active);
        Long id = userDao.save(user);

        if (id != null) {
            System.out.println("Usuário criado com sucesso! ID: " + id);
        } else {
            System.out.println("Erro ao criar usuário.");
        }
    }

    private static void updateUser(Scanner scanner) {
        System.out.print("Digite o ID do usuário para atualizar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        User user = userDao.findById(id);
        if (user == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.print("Digite o novo nome (atual: " + user.getName() + "): ");
        user.setName(scanner.nextLine());
        System.out.print("Digite o novo email (atual: " + user.getEmail() + "): ");
        user.setEmail(scanner.nextLine());
        System.out.print("Digite a nova senha (atual: " + user.getPassword() + "): ");
        user.setPassword(scanner.nextLine());
        System.out.print("Digite o último acesso (YYYY-MM-DD, atual: " + user.getLastAccess() + "): ");
        String lastAccessString = scanner.nextLine();
        
        try {
            LocalDateTime lastAccess = LocalDateTime.parse(lastAccessString, formatter);
            user.setLastAccess(lastAccess);
        } catch (DateTimeParseException e) {
            System.out.println("Formato inválido.");
            return;
        }

        System.out.print("Usuário ativo (true/false, atual: " + user.isActive() + "): ");
        user.setActive(scanner.nextBoolean());
        scanner.nextLine(); 

        userDao.update(user);
        System.out.println("Usuário atualizado!");
    }

    private static void findUserById(Scanner scanner) {
        System.out.print("Digite o ID para buscar: ");
        Long id = scanner.nextLong();
        User user = userDao.findById(id);

        if (user != null) {
            System.out.println("Usuário encontrado: " + user);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    private static void findAllUsers() {
        List<User> users = userDao.findAll();

        if (users.isEmpty()) {
            System.out.println("Usuário não encontrado.");
        } else {
            users.forEach(System.out::println);
        }
    }

    private static void deleteUser(Scanner scanner) {
        System.out.print("Digite o ID para deletar: ");
        Long id = scanner.nextLong();

        userDao.delete(id);
        System.out.println("Usuário deletado com sucesso!");
    }
}
