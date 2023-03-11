package datnnhom12api.controller.admin;

import datnnhom12api.request.UserRoleRequest;
import datnnhom12api.response.UserRoleResponse;
import datnnhom12api.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class UserRoleController {

    @Autowired
    UserRoleService userRoleService;

    @PostMapping("/api/updateRole")
    public UserRoleResponse updateRole(@RequestBody UserRoleRequest userRoleRequest) {
        return new UserRoleResponse(userRoleService.updateRole(userRoleRequest));
    }
}