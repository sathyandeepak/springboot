## 🔁 **Big Picture: Application Flow**

Here’s a clear breakdown of the **flow**:

### ✅ Step 1: **User Registers**

* API: `POST /api/auth/register`
* Data: username, password, roles, etc.
* Handled by: `EmployeeService.addEmployee()`

🟢 Password is **encoded** using `PasswordEncoder`.

🟢 Roles are validated and added.

🟢 User is saved to DB (`RegisterDetailsRepository`).

---

### ✅ Step 2: **User Logs In**

* API: `POST /api/auth/login`
* Input: username, password
* Handled by: your custom **auth method** (maybe in `AuthController` or a service)

🟢 Inside login method:

```java
authenticationManager.authenticate(
    new UsernamePasswordAuthenticationToken(username, password)
);
```

📌 This **triggers Spring Security**:

* Calls your `CustomUserDetailsService.loadUserByUsername()`
* Loads user from DB using `RegisterDetailsRepository`
* Compares encoded password
* If successful → returns an `Authentication` object

🟢 Then `JwtTokenProvider.generateToken()` is called:

* Generates a signed token with user info
* Returns token in a `JwtResponse`

---

### ✅ Step 3: **Client Sends Requests with JWT**

Now the user includes the JWT in the **Authorization Header**:

```
Authorization: Bearer <token>
```

---

### ✅ Step 4: **JWT Filter Intercepts Requests**

* Class: `JwtAuthenticationFilter`
* It runs **before any controller** method is called

### Inside `JwtAuthenticationFilter`:

1. Extracts token from header
2. Calls `JwtTokenProvider.getUsernameFromToken()`
3. Calls `CustomUserDetailsService.loadUserByUsername(username)`
4. Calls `JwtTokenProvider.validateToken(token)`
5. If valid, sets up Spring Security context (`SecurityContextHolder`)

📌 Now Spring thinks the user is "logged in" and authenticated.

---

### ✅ Step 5: **Authorized Access to Protected APIs**

Any endpoint that is **not `/api/auth/**`** is protected:

```java
http
  .authorizeHttpRequests(auth -> {
      auth.requestMatchers("/api/auth/**").permitAll();
      auth.anyRequest().authenticated();
  })
```

Spring Security checks:

* Is the token present and valid?
* Is the user authorized?

You can now use annotations like:

```java
@PreAuthorize("hasRole('ADMIN')")
```

---

## 🔐 JWT Package Roles

| File                      | Role                                              |
| ------------------------- | ------------------------------------------------- |
| `JwtTokenProvider`        | Generates, validates, parses JWTs                 |
| `JwtAuthenticationFilter` | Intercepts requests, authenticates using JWT      |
| `JwtResponse`             | Holds token + user info to send in login response |

---

## ⚙️ Config Package Roles

| File                    | Role                                                                                 |
| ----------------------- | ------------------------------------------------------------------------------------ |
| `SpringConfiguration`   | Configures security: disables CSRF, adds JWT filter, creates `AuthenticationManager` |
| `@EnableMethodSecurity` | Enables `@PreAuthorize` on methods                                                   |

---

## 👤 `CustomUserDetailsService`

Implements:

```java
UserDetailsService
```

Used by:

* `AuthenticationManager` during login
* `JwtAuthenticationFilter` to fetch user details from DB

---

## 🧭 Flow Summary

```
Register → Save encoded user + roles
        ↓
Login → AuthManager authenticates using CustomUserDetailsService
        ↓
        If valid → JwtTokenProvider generates token
        ↓
Client stores token → Sends it with every request
        ↓
JwtAuthenticationFilter intercepts every request
        ↓
        If token is valid → sets up SecurityContextHolder
        ↓
        Then controller/service runs
```

---
