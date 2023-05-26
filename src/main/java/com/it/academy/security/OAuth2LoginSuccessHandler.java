package com.it.academy.security;

import com.it.academy.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserService userService;
    private final DetailsUserService detailsUserService;
    private final JWTUtil jwtUtil;
    @Value("${oauth2-success-redirect-url}")
    private String redirectUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        CustomOAuth2User oauth2User = (CustomOAuth2User) authentication.getPrincipal();
        String registrationId = ((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId();
        String username = registrationId.equals("google")
                ? oauth2User.getEmail()
                : oauth2User.getLogin();
        userService.processOAuthPostLogin(username, oauth2User.getName(), registrationId);
        UserDetails userDetails = detailsUserService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        String token = jwtUtil.generateToken(username);

        response.addHeader("Authorization", "Bearer " + token);

//        String redirectUrl = "/user/current";
        String jsonResponse = "{\"redirectUrl\": \"" + redirectUrl + "\", \"token\": \"" + token + "\"}";
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();

//        response.sendRedirect("/auth/redirect");
    }

}
