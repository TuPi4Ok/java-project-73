package hexlet.code.app.controller;

import hexlet.code.app.dto.Mapper;
import hexlet.code.app.dto.UserDto;
import hexlet.code.app.errors.UserNotFoundException;
import hexlet.code.app.model.User;
import hexlet.code.app.service.User.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PutExchange;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    Mapper mapper;

    @Autowired
    UserServiceImpl userService;
    @Operation(summary = "Create new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "User is not valid")
    })
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Parameter(description = "User data to save") @Valid @RequestBody User user) {
        return mapper.toUserDto(userService.registrateUser(user));
    }

    @Operation(summary = "Get user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User with that id not found")
    })
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable(name = "id") long id) {
        try {
            return mapper.toUserDto(userService.getUser(id));
        } catch (Exception e) {
            throw new UserNotFoundException("User with this id is not found");
        }
    }

    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "Uses found")
    @GetMapping("")
    public List<UserDto> getUser() {
        List<UserDto> usersDto = new ArrayList<>();
        for (User user : userService.getUsers()) {
            usersDto.add(mapper.toUserDto(user));
        }
        return usersDto;
    }

    @Operation(summary = "Update existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "400", description = "User is not valid"),
            @ApiResponse(responseCode = "404", description = "User with that id not found")
    })
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable(name = "id") long id,
                              @Parameter(description = "User data to update") @Valid @RequestBody User user) {
        try {
            return mapper.toUserDto(userService.updateUser(id, user));
        } catch (Exception e) {
            throw new UserNotFoundException("User with this id is not found");
        }
    }

    @Operation(summary = "Delete user by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted"),
            @ApiResponse(responseCode = "404", description = "User with that id not found")
    })
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") long id) {
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            throw new UserNotFoundException("User with this id is not found");
        }
    }
}
