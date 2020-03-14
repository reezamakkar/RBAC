# Assignment

###Problem Statement and Approach
Test require us to implement a role based auth system.

1. System should be able to assign a role to a user and remove a role from a user.
2. Access to resources for users are controlled strictly by the role.
3. One user can have multiple roles.
4. Given a user, action type and resource, the system should be able to tell whether user has access or not.**

###Relevant Files
**Entity Classes defined in com.test.entities package:**

SYSTEM: RoleBasedAccessControlSystem class represents a **role based auth system** consists of set of users, roles and resources.

USER: User class consists of name, and its assigned roles. It also has some utility methods for validation purpose.

ACTION TYPE: ActionType Enum with values READ, WRITE & DELETE defines the access level.

RESOURCE: Resource class on which we can perform some actions later on. Has name as data members.

ROLE: Role class consist of all the resource to allowed action mappings corresponding to that role.

USER TYPE: UserType enum defines type of user if its ADMIN_USER or NORMAL_USER

DataHelper class contains all initialization of some default users, roles, resources etc.

TestApplication class contains the application main where execution begins

Code written has comments included where ever required. Also tried to isolate entities or modularize code to make it
extendable.
