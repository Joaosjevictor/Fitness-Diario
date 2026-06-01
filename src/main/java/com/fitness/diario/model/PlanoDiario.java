package com.fitness.diario.model;

import lombok.Data;
import java.util.List;

@Data
public class PlanoDiario {
    private String focoDoDia; // Ex: "Peito e Tríceps"
    private int caloriasMeta;
    private List<String> exercicios;
}
