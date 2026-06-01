package com.fitness.diario.service;

import com.fitness.diario.model.PerfilUsuario;
import com.fitness.diario.model.PlanoDiario;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EmagrecimentoStrategy implements ObjetivoStrategy {

    @Override
    public String getObjetivo() {
        return "Emagrecimento";
    }

    @Override
    public void montarPlano(PerfilUsuario usuario) {
        Map<String, PlanoDiario> plano = new HashMap<>();
        int calorias = (int) (usuario.getPesoAtual() * 22); 
        
        boolean lesaoJoelho = usuario.getRestricoesMedicas().contains("Lesão no Joelho");
        boolean diabetes = usuario.getRestricoesMedicas().contains("Diabetes");

        String avisoCardio = lesaoJoelho ? "Elíptico ou Remo (Baixo Impacto)" : "Esteira (Corrida/Caminhada Rápida)";
        String cuidadoDiabetes = diabetes ? " (Atenção: Consuma um carboidrato leve antes do treino)" : "";

        // SEGUNDA-FEIRA
        PlanoDiario segunda = new PlanoDiario();
        segunda.setFocoDoDia("Cardio Contínuo e Core");
        segunda.setCaloriasMeta(calorias);
        segunda.setExercicios(List.of(
            "40 minutos de " + avisoCardio + cuidadoDiabetes, 
            "Prancha Abdominal 3x 45 seg", 
            "Abdominal Oblíquo 3x15"
        ));
        plano.put("segunda_feira", segunda);

        // TERÇA-FEIRA
        PlanoDiario terca = new PlanoDiario();
        terca.setFocoDoDia("Circuito Full Body (Superiores)");
        terca.setCaloriasMeta(calorias);
        terca.setExercicios(List.of(
            "Flexão de Braços 3x Máx", 
            "Puxada Alta na Polia 3x15", 
            "Desenvolvimento com Halteres 3x15",
            "15 minutos de " + avisoCardio
        ));
        plano.put("terca_feira", terca);

        // QUARTA-FEIRA
        PlanoDiario quarta = new PlanoDiario();
        quarta.setFocoDoDia("Treino Intervalado (HIIT)");
        quarta.setCaloriasMeta(calorias);
        if (lesaoJoelho) {
            quarta.setExercicios(List.of(
                "Natação Intensa (Tiros de 50m) 20 min" + cuidadoDiabetes, 
                "Bicicleta Ergométrica (Tiros de 1 min pesado, 1 min leve) 15 min", 
                "Fortalecimento Isométrico de Quadríceps 3x 1 min"
            ));
        } else {
            quarta.setExercicios(List.of(
                "Burpees 4x10" + cuidadoDiabetes, 
                "Polichinelos 4x30 seg", 
                "Tiros na Esteira (1 min correndo, 1 min andando) 20 min"
            ));
        }
        plano.put("quarta_feira", quarta);

        // QUINTA-FEIRA
        PlanoDiario quinta = new PlanoDiario();
        quinta.setFocoDoDia("Circuito Full Body (Inferiores)");
        quinta.setCaloriasMeta(calorias);
        if (lesaoJoelho) {
            quinta.setExercicios(List.of(
                "Elevação Pélvica com Carga 3x15", 
                "Cadeira Adutora 3x20", 
                "Alongamento Dinâmico Completo",
                "20 minutos de Caminhada Leve"
            ));
        } else {
            quinta.setExercicios(List.of(
                "Agachamento com Salto 3x15", 
                "Avanço Passada 3x12 cada perna", 
                "Pular Corda 3x 2 min",
                "15 minutos de " + avisoCardio
            ));
        }
        plano.put("quinta_feira", quinta);

        // SEXTA-FEIRA
        PlanoDiario sexta = new PlanoDiario();
        sexta.setFocoDoDia("Recuperação Ativa e Braços");
        sexta.setCaloriasMeta(calorias);
        sexta.setExercicios(List.of(
            "Tríceps no Banco 3x15", 
            "Rosca Alternada 3x15", 
            "Caminhada ao ar livre ou " + avisoCardio + " 30 min" + cuidadoDiabetes
        ));
        plano.put("sexta_feira", sexta);

        usuario.setPlanoSemanal(plano);
    }
}