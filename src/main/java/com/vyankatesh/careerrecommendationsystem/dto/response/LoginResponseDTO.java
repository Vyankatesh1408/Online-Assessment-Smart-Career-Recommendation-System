package com.vyankatesh.careerrecommendationsystem.dto.response;

public class LoginResponseDTO {

    private String message;
    private Long userId;
    private String fullName;
    private String email;
    private String roleName;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String message, Long userId, String fullName, String email, String roleName) {
        this.message = message;
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.roleName = roleName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}