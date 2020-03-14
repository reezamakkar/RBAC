package com.test.entities;

import java.util.HashSet;
import java.util.Set;

public class User {
    //One user can have multiple roles.
    Set<Role> roles = new HashSet<>();
    UserType type;
    String name;

    public User(String name, UserType type) {
        this.name = name;
        this.type = type;
    }

    //System should be able to assign a role to a user.
    public void addRole(Role role) {
        roles.add(role);
    }

    //System should be able to assign a role to a user.
    public String getName() {
        return name;
    }

    //System should be able to remove a role from a user.
    public void removeRole(Role role) {
        if(roles.contains(role))
            roles.remove(role);
    }

    /**
     * Given a user, action type and resource, the system should be able to tell whether user has access or not.
     * @param resource
     * @param performedAction
     * @return
     */
    public boolean validateResourceAccess(Resource resource, ActionType performedAction) {
        if(type.equals(UserType.ADMIN_USER)) {
            System.out.println("User has Admin role hence access to all resouces despite of roles.");
            return true;
        }

        return roles.stream().anyMatch(role -> role.validateResourceAccess(resource, performedAction));
    }

    public void printInfo() {
        System.out.println(String.format("\n*********- %s [%s] -*********", name, type.toString()));
        if(!roles.isEmpty()) {
            System.out.println("ROLES:");
            roles.forEach(Role::printInfo);
        } else {
            System.out.println("<<<<No assigned roles so far>>>>");
        }
    }
}
