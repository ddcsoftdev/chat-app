package com.chatapp.user.infrastructure.dto;

//import com.chatapp.user.infrastructure.dto.AuthorityDtoBuilder;
import com.chatapp.user.domain.aggregate.Authority;
import org.jilt.Builder;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record AuthorityDto(String name) {



}
