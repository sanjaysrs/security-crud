package com.srsanjay.twenty.dto;

import jakarta.validation.constraints.NotBlank;

public class PasswordDto {

    @NotBlank(message = "Old password is required")
    public String oldPassword;

    @NotBlank(message = "New Password is required")
    public String newPassword;

    @NotBlank(message = "Reenter the new password")
    public String reenterNewPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getReenterNewPassword() {
        return reenterNewPassword;
    }

    public void setReenterNewPassword(String reenterNewPassword) {
        this.reenterNewPassword = reenterNewPassword;
    }
}
