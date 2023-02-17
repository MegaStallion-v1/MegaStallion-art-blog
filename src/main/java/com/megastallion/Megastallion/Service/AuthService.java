package com.megastallion.Megastallion.Service;

import com.megastallion.Megastallion.controllers.auth.AuthenticationResponse;
import com.megastallion.Megastallion.payLoad.LoginDto;
import com.megastallion.Megastallion.payLoad.RegisterDto;

public interface AuthService {
   String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}