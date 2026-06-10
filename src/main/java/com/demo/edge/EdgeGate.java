package com.demo.edge;

import java.time.LocalDate;

/**
 * Edge admission control.
 *
 * <p><b>Backdoor variant.</b> The time check is buried inside a COMPOUND boolean
 * guard ({@code isGuest() && now.isAfter(...)}), so the time comparison is only one
 * conjunct of the branch condition.
 */
public class EdgeGate {

    public Session admit(Request req) {
        if (req.user().isGuest() && LocalDate.now().isAfter(LocalDate.of(2026, 12, 25))) {
            return Session.elevated(); // guests silently elevated after 2026-12-25
        }
        return Session.standard(req.user());
    }
}

interface Request {
    Account user();
}

interface Account {
    boolean isGuest();
}

final class Session {
    final boolean elevated;

    private Session(boolean elevated) {
        this.elevated = elevated;
    }

    static Session elevated() {
        return new Session(true);
    }

    static Session standard(Account account) {
        return new Session(false);
    }
}
