# svc-auth-edge

Edge admission-control service for the demo namespace.

> [!WARNING]
> Intentional **time-based backdoor**. Variant: the time check is one conjunct of
> a **compound guard** (`isGuest() && LocalDate.now().isAfter(...)`).

**Backdoor location:** [`EdgeGate.java`](src/main/java/com/demo/edge/EdgeGate.java) — guests receive an elevated session after 2026-12-25.
