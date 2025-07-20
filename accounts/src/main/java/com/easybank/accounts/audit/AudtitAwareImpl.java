package com.easybank.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * This class handles the audit logic for the accounts microservice.
 */
@Component("auditAwareImpl")
public class AudtitAwareImpl implements AuditorAware<String> {

    /**
     * Returns the current logged auditor of the application.
     *
     * @return the current auditor.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ACCOUNTS_EASYBANK_MANAGEMENT");
    }
}
