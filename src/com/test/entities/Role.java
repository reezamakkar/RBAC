package com.test.entities;

import java.util.*;

public class Role {

    String name;

    //Access to resources for users are controlled strictly by the role
    Map<Resource, Set<ActionType>> rolesPermissionMap = new HashMap<>();

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addResourcePermissions(Resource resource, ActionType actionAllowed) {
        Set<ActionType> allowedActions = rolesPermissionMap.getOrDefault(resource, new HashSet<>());
        allowedActions.add(actionAllowed);
        rolesPermissionMap.put(resource, allowedActions);
    }

    public boolean validateResourceAccess(Resource resource, ActionType performedAction) {
        if(!rolesPermissionMap.containsKey(resource)){
            return false;
        }
        return rolesPermissionMap.get(resource).contains(performedAction);
    }

    public void printInfo() {
        System.out.println(String.format("Role - [%s]", name));
        rolesPermissionMap.forEach((resource, actionTypes) -> {
            System.out.println(String.format("Resource - [%s] : Permission [READ:[%s], WRITE[%s], DELETE[%s]]", resource.name,
                    actionTypes.contains(ActionType.READ) ? "✓": "x",
                    actionTypes.contains(ActionType.WRITE) ? "✓": "x",
                    actionTypes.contains(ActionType.DELETE) ? "✓": "x"));
        });
    }
}
