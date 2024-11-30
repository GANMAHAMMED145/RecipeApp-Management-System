package com.Receipe.Api.Model;

import com.Receipe.Api.Entity.Receipe;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalApiResponse {
    private List<Receipe> recipes;
}
