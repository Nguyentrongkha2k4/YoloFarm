package com.web.yolofarm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    USERNAME_INVALID(400, "Username must be at least 8 characters long."),
    PASSWORD_INVALID(400, "Password must be at least 5 characters long."),
    INVALID_KEY(400, "Invalid key."),
    USER_EXISTED(400, "Username has already existed."),
    WRONG_PASSWORD(400, "Password is not correct"),
    USER_NOT_EXISTED(400, "User have not existed"),
    FAILED_GENERATE_TOKEN(400, "failed when generating the token"),
    VERIFY_TOKEN_EXCEPTION(500, "failed when verify token"),
    TOKEN_INVALID(400, "Token is invalid."),
    ACCESS_DENY(900, "You don't have permission to access this thing."),
    UNCATEGORIED_EXCEPTION(999, "Uncategoried exception"),
    ORGANIZATION_EXIST,
    DELETE_FAILED(500, "Delete failed."),
    ORGANIZATION_NOT_EXIST(404, "Organization does not exist."),
    YOU_ARE_NOT_PERMITTED(403, "You are not permitted to do this."),
    ROLE_INVALID(400, "Role is invalid."),
    ;
    private int code;
    private String message;

}
