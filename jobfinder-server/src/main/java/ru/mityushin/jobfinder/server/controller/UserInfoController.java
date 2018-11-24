package ru.mityushin.jobfinder.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mityushin.jobfinder.server.service.http.HttpService;
import ru.mityushin.jobfinder.server.service.userinfo.UserInfoService;
import ru.mityushin.jobfinder.server.util.dto.UserInfoDTO;
import ru.mityushin.jobfinder.server.util.exception.data.DataCreateException;
import ru.mityushin.jobfinder.server.util.exception.data.DataDeleteException;
import ru.mityushin.jobfinder.server.util.exception.data.DataUpdateException;
import ru.mityushin.jobfinder.server.util.exception.data.UserAlreadyExistsException;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/users")
public class UserInfoController {

    private UserInfoService userInfoService;
    private HttpService httpService;

    public UserInfoController(UserInfoService userInfoService, HttpService httpService) {
        this.userInfoService = userInfoService;
        this.httpService = httpService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<UserInfoDTO>> getUsers() {
        return new ResponseEntity<>(userInfoService.findAll(), HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<UserInfoDTO>> getDeletedUsers() {
        return new ResponseEntity<>(userInfoService.findAllDeleted(), HttpStatus.OK);
    }

    @GetMapping("/current")
    @ResponseBody
    public ResponseEntity<UserInfoDTO> getCurrent() {
        return new ResponseEntity<>(userInfoService.getCurrentAuthenticateUserInfo(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {

        UserInfoDTO userInfo = parseUserInfoDTO(body);

        try {
            userInfo = userInfoService.create(userInfo);

        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(httpService.createMessage(e.getMessage()), HttpStatus.CONFLICT);

        } catch (DataCreateException e) {
            return new ResponseEntity<>(httpService.createMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{url}")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody Map<String, String> body, @PathVariable("url") String url) {

        UserInfoDTO userInfo = parseUserInfoDTO(body);

        try {
            userInfo = userInfoService.update(userInfo);

        } catch (DataUpdateException e) {
            return new ResponseEntity<>(httpService.createMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{url}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("url") String url) {

        UserInfoDTO userInfo = UserInfoDTO.builder()
                .url(url)
                .build();

        try {
            userInfo = userInfoService.delete(userInfo);

        } catch (DataDeleteException e) {
            return new ResponseEntity<>(httpService.createMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    private static UserInfoDTO parseUserInfoDTO(@NotNull Map<String, String> body) {
        return UserInfoDTO.builder()
                .username(body.getOrDefault("username", null))
                .password(body.getOrDefault("password", null))
                .url(body.getOrDefault("url", null))
                .firstName(body.getOrDefault("first_name", null))
                .secondName(body.getOrDefault("second_name", null))
                .lastName(body.getOrDefault("last_name", null))
                .age(body.getOrDefault("age", null))
                .sex(body.getOrDefault("sex", null))
                .build();
    }
}
