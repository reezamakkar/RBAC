package com.test;

import com.test.entities.*;

import java.util.Optional;
import java.util.Scanner;

public class TestApplication {
    //Initialize Resources
    static RoleBasedAccessControlSystem system = DataHelper.initializeResources();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Role Based Access Control Application.\n");
        while(true){
            try {
                System.out.println("\n*********--*********");
                System.out.println("Select operation you want to perform:");
                System.out.println("1:\tPopulate All Users Info.");
                System.out.println("2:\tCreate User.");
                System.out.println("3:\tAdd role from user.");
                System.out.println("4:\tDelete role from user.");
                System.out.println("5:\tValidate a user for resource permission.");
                System.out.println("6:\tExit Application.");

                int option = scanner.nextInt();
                switch (option){
                    case 1:
                        system.getUsers().forEach(User::printInfo);
                        System.out.println("\n");
                        break;
                    case 2:
                        createUser();
                        break;
                    case 3:
                        addRole();
                        break;
                    case 4:
                        removeRole();
                        break;
                    case 5:
                        validateRolePermissionForUser();
                        break;
                    case 6: return;
                }

            } catch (Exception e) {
                System.out.println(String.format("\nNOTE: You've selected an invalid choice due to which %s.",
                        e.getMessage()));
                System.out.println("\n*********--*********\n");
            }
        }
    }

    /**
     * Given a user, action type and resource, the system should be able to tell whether user has access or not.
     */
    private static void validateRolePermissionForUser() {
        System.out.println("Available users to validate:");
        system.getUsers().forEach(u->{
            System.out.println(String.format("User - [%s]", u.getName()));
        });
        System.out.println("Enter the name of user you want to validate:");
        String userToUpdate = scanner.next();
        Optional<User> userToProcess = system.getUsers().stream()
                .filter(u -> u.getName().toLowerCase().trim().equals(userToUpdate.trim().toLowerCase()))
                .findFirst();
        if(!userToProcess.isPresent()) {
            System.out.println(String.format("Unable to find a user with Name: %s", userToUpdate));
            return;
        }
        System.out.println("Enter the name of resource you want to access:");
        system.getResources().forEach(resource -> {
            System.out.println(String.format("Resource - [%s]", resource.getName()));
        });
        String resourceToProcess = scanner.next();
        Optional<Resource> resource = system.getResources().stream()
                .filter(u -> u.getName().toLowerCase().trim().equals(resourceToProcess.trim().toLowerCase()))
                .findFirst();
        if(!resource.isPresent()) {
            System.out.println(String.format("Unable to find a resource with name: %s", resourceToProcess));
            return;
        }
        System.out.println("Enter the action type you want user to perform: \n1:\tREAD\n2:\tWRITE\n3:\tDELETE\n");
        int option = scanner.nextInt();
        ActionType action = option == 1 ? ActionType.READ : (option == 2 ? ActionType.WRITE : ActionType.DELETE);
        userToProcess.get().printInfo();
        if(userToProcess.get().validateResourceAccess(resource.get(), action)) {
            System.out.println("Yes, User has the requested access.\n");
        } else {
            System.out.println("No, User doesn't have the requested access.\n");
        }
    }

    /**
     * System should be able to remove a role from a user.
     */
    private static void removeRole() {
        System.out.println("Available users to update:");
        system.getUsers().forEach(u->{
            System.out.println(String.format("User - [%s]", u.getName()));
        });
        System.out.println("Enter the name of user you want to update:");
        String userToUpdate = scanner.next();
        Optional<User> userToProcess = system.getUsers().stream()
                .filter(u -> u.getName().toLowerCase().trim().equals(userToUpdate.trim().toLowerCase()))
                .findFirst();
        if(!userToProcess.isPresent()) {
            System.out.println(String.format("Unable to find a user with Name: %s", userToUpdate));
            return;
        }
        userToProcess.get().printInfo();
        System.out.println("Enter the name of role you want to remove:");
        String roleToAssign = scanner.next();
        Optional<Role> roleToProcess = system.getRoles().stream()
                .filter(u -> u.getName().toLowerCase().trim().equals(roleToAssign.trim().toLowerCase()))
                .findFirst();
        if(!roleToProcess.isPresent()) {
            System.out.println(String.format("Unable to find a role with name: %s", roleToAssign));
            return;
        }
        userToProcess.get().removeRole(roleToProcess.get());
        System.out.println("\nNOTE: Role removed from user successfully.\n");
        userToProcess.get().printInfo();
    }

    /**
     * System should be able to assign a role to a user.
     */
    private static void addRole() {
        //show users
        System.out.println("Available users to update:");
        system.getUsers().forEach(u->{
            System.out.println(String.format("User - [%s]", u.getName()));
        });
        System.out.println("Enter the name of user you want to update:");
        String userToUpdate = scanner.next();
        Optional<User> userToProcess = system.getUsers().stream()
                .filter(u -> u.getName().toLowerCase().trim().equals(userToUpdate.trim().toLowerCase()))
                .findFirst();
        if(!userToProcess.isPresent()) {
            System.out.println(String.format("Unable to find a user with Name: %s", userToUpdate));
            return;
        }
        userToProcess.get().printInfo();

        //show roles
        System.out.println("All available roles in system:");
        system.getRoles().forEach(r->{
            System.out.println(String.format("Role - [%s]", r.getName()));
        });
        System.out.println("Enter the name of role you want to assign to user:");
        String roleToAssign = scanner.next();
        Optional<Role> roleToProcess = system.getRoles().stream()
                .filter(u -> u.getName().toLowerCase().trim().equals(roleToAssign.trim().toLowerCase()))
                .findFirst();
        if(!roleToProcess.isPresent()) {
            System.out.println(String.format("Unable to find a role with name: %s", roleToAssign));
            return;
        }
        userToProcess.get().addRole(roleToProcess.get());
        System.out.println("\nNOTE: Role added to user successfully.\n");
        userToProcess.get().printInfo();
    }

    private static void createUser() {
        System.out.println("Enter the name of new user:");
        String name = scanner.next();
        System.out.println("Enter the type of new user: \n1:\tADMIN_TYPE\n2:\tNORMAL_TYPE");
        int option = scanner.nextInt();
        User user = new User(name, option == 1 ? UserType.ADMIN_USER : UserType.NORMAL_USER);
        system.addUser(user);
        System.out.println("User Created.");
        user.printInfo();
        System.out.println();
    }
}
