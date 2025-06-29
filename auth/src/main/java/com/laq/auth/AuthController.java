package com.laq.auth;

import java.util.Map;

import com.laq.libJwtShare.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final JwtUtil jwtUtil; // final field needed here

  @PostMapping("/login")
  public Map<String, String> login(@RequestBody AuthRequest req) {
    if (!"password123".equals(req.getPassword()))
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

    return Map.of("token", jwtUtil.generateToken(req.getUsername()));
  }
}
