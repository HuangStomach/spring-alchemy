package com.huangstomach.springalchemy.user.cli;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProvider;

class UserValueProvider implements ValueProvider {
    @Override
    public List<CompletionProposal> complete(CompletionContext completionContext) {
        return Arrays.asList("val1", "val2").stream()
            .map(CompletionProposal::new)
            .collect(Collectors.toList());
    }
}
