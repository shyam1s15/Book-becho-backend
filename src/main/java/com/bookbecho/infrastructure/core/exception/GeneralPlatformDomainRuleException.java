package com.bookbecho.infrastructure.core.exception;


/**
 * A {@link RuntimeException} thrown when valid api request end up violating some domain rule.
 */
public final class GeneralPlatformDomainRuleException extends AbstractPlatformDomainRuleException {

    public GeneralPlatformDomainRuleException(String globalisationMessageCode, String defaultUserMessage,
                                              Object... defaultUserMessageArgs) {
        super(globalisationMessageCode, defaultUserMessage, defaultUserMessageArgs);
    }
}
