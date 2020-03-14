package com.test.entities;

import java.util.HashSet;
import java.util.Set;

public class RoleBasedAccessControlSystem {
    Set<User> users = new HashSet<>();
    Set<Role> roles = new HashSet<>();
    Set<Resource> resources = new HashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public void addResource(Resource resource) {
        resources.add(resource);
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }
}