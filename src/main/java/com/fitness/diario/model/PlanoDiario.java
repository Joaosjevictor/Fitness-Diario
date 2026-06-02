package com.fitness.diario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanoDiario {

    private String focoDoDia;

    private int caloriasMeta;

    private List<String> exercicios;
}