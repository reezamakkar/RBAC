package com.test;

import com.test.entities.*;

public class DataHelper {

    /**
     * init system with few Resources, Roles(which have resources with different permission set),
     * Admin User(admin) and Normal User(User1)
     * @return
     */
    public static RoleBasedAccessControlSystem initializeResources(){
        RoleBasedAccessControlSystem sys = new RoleBasedAccessControlSystem();

        // Defining resources
        Resource resource1 = new Resource("EC2");
        Resource resource2 = new Resource("VPC");
        Resource resource3 = new Resource("S3");
        Resource resource4 = new Resource("CloudFormation");
        sys.addResource(resource1);
        sys.addResource(resource2);
        sys.addResource(resource3);
        sys.addResource(resource4);

        // Defining roles
        Role role1 = new Role("Role-Read-Only");
        role1.addResourcePermissions(resource1, ActionType.READ);
        role1.addResourcePermissions(resource2, ActionType.READ);
        role1.addResourcePermissions(resource3, ActionType.READ);
        role1.addResourcePermissions(resource4, ActionType.READ);
        Role role2 = new Role("Role-Read-Write-Delete");
        role2.addResourcePermissions(resource1, ActionType.READ);
        role2.addResourcePermissions(resource1, ActionType.WRITE);
        role2.addResourcePermissions(resource1, ActionType.DELETE);
        role2.addResourcePermissions(resource2, ActionType.READ);
        role2.addResourcePermissions(resource2, ActionType.WRITE);
        role2.addResourcePermissions(resource2, ActionType.DELETE);
        role2.addResourcePermissions(resource3, ActionType.READ);
        role2.addResourcePermissions(resource3, ActionType.WRITE);
        role2.addResourcePermissions(resource3, ActionType.DELETE);
        role2.addResourcePermissions(resource4, ActionType.READ);
        role2.addResourcePermissions(resource4, ActionType.WRITE);
        role2.addResourcePermissions(resource4, ActionType.DELETE);
        Role role3 = new Role("Role-Read-Write");
        role3.addResourcePermissions(resource1, ActionType.READ);
        role3.addResourcePermissions(resource1, ActionType.WRITE);
        role3.addResourcePermissions(resource3, ActionType.READ);
        role3.addResourcePermissions(resource3, ActionType.WRITE);
        role3.addResourcePermissions(resource2, ActionType.READ);
        role3.addResourcePermissions(resource2, ActionType.WRITE);
        role3.addResourcePermissions(resource4, ActionType.READ);
        role3.addResourcePermissions(resource4, ActionType.WRITE);
        Role role4 = new Role("Role-Delete-Only");
        role4.addResourcePermissions(resource1, ActionType.DELETE);
        role4.addResourcePermissions(resource2, ActionType.DELETE);
        role4.addResourcePermissions(resource3, ActionType.DELETE);
        role4.addResourcePermissions(resource4, ActionType.DELETE);
        sys.addRole(role1);
        sys.addRole(role2);
        sys.addRole(role3);
        sys.addRole(role4);

        // Assigning roles to user
        User user1 = new User("Test Normal User 1", UserType.NORMAL_USER);
        user1.addRole(role1);
        user1.addRole(role4);
        User user2 = new User("Test Normal User 2", UserType.NORMAL_USER);
        user2.addRole(role3);
        User user3 = new User("Test Normal User 3", UserType.NORMAL_USER);
        user3.addRole(role4);
        User user4 = new User("Test Admin User 1", UserType.ADMIN_USER);

        // return users
        sys.addUser(user1);
        sys.addUser(user2);
        sys.addUser(user3);
        sys.addUser(user4);



        return sys;
    }
}
