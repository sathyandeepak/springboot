----- AuthService.java --------

// response like JwtResponse
public JwtResponse authenticate(RegisterDetails login) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(login.getUserName(), login.getPassword())
    );

    String token = jwtTokenProvider.generateToken(authentication);

    // Extract username
    String username = login.getUserName();

    // Extract roles
    List<String> roles = authentication.getAuthorities().stream()
            .map(role -> role.getAuthority())
            .collect(Collectors.toList());

    String joinedRoles = String.join(",", roles);

    return new JwtResponse(token, username, joinedRoles);
}


------------- 

//    Generates a signed JWT token for an authenticated user.
    public String generateToken(Authentication authenticate) {
//        Retrieves the logged-in user's details.
        UserDetails userPrincipal = (UserDetails) authenticate.getPrincipal();

//        Then builds a JWT:
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername()) // who the token is about
                .setIssuedAt(new Date()) // who the token is about
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMilliseconds)) // expiry
                .signWith(secretKey() , SignatureAlgorithm.HS256) // signing
                .compact();
   }


----------------------
