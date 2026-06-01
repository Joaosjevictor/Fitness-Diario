package com.fitness.diario.service;

import com.fitness.diario.model.PerfilUsuario;
import com.fitness.diario.model.PlanoDiario;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HipertrofiaStrategy implements ObjetivoStrategy {

    @Override
    public String getObjetivo() {
        return "Hipertrofia";
    }

    @Override
    public void montarPlano(PerfilUsuario usuario) {
        Map<String, PlanoDiario> plano = new HashMap<>();
        int calorias = (int) (usuario.getPesoAtual() * 35); 
        
        boolean lesaoJoelho = usuario.getRestricoesMedicas().contains("Lesão no Joelho");
        boolean diabetes = usuario.getRestricoesMedicas().contains("Diabetes");

        String avisoDiabetes = diabetes ? " + 15 min de bicicleta (Controle Glicêmico)" : "";

        // SEGUNDA-FEIRA
        PlanoDiario segunda = new PlanoDiario();
        segunda.setFocoDoDia("Peito e Ombros Anterior");
        segunda.setCaloriasMeta(calorias);
        segunda.setExercicios(List.of(
            "Supino Reto com Barra 4x10", 
            "Crucifixo Inclinado com Halteres 3x12", 
            "Desenvolvimento Militar 4x10",
            "Elevação Frontal 3x12" + avisoDiabetes
        ));
        plano.put("segunda_feira", segunda);

        // TERÇA-FEIRA
        PlanoDiario terca = new PlanoDiario();
        terca.setFocoDoDia("Costas e Posterior de Ombro");
        terca.setCaloriasMeta(calorias);
        terca.setExercicios(List.of(
            "Puxada Frontal na Polia 4x12", 
            "Remada Curvada com Barra 4x10", 
            "Crucifixo Inverso 3x15",
            "Levantamento Terra 3x8" + avisoDiabetes
        ));
        plano.put("terca_feira", terca);

        // QUARTA-FEIRA (A Mágica da Adaptação de Lesão)
        PlanoDiario quarta = new PlanoDiario();
        quarta.setFocoDoDia("Membros Inferiores");
        quarta.setCaloriasMeta(calorias);
        if (lesaoJoelho) {
            quarta.setExercicios(List.of(
                "Elevação Pélvica (Sem peso nos joelhos) 4x15", 
                "Cadeira Adutora 4x15", 
                "Cadeira Abdutora 4x15",
                "Natação ou Bicicleta Ergométrica Leve 30 min"
            ));
        } else {
            quarta.setExercicios(List.of(
                "Agachamento Livre 4x10", 
                "Leg Press 45º 4x12", 
                "Cadeira Extensora 3x15",
                "Panturrilha no Smith 4x15"
            ));
        }
        plano.put("quarta_feira", quarta);

        // QUINTA-FEIRA
        PlanoDiario quinta = new PlanoDiario();
        quinta.setFocoDoDia("Braços (Bíceps e Tríceps)");
        quinta.setCaloriasMeta(calorias);
        quinta.setExercicios(List.of(
            "Rosca Direta com Barra 4x10", 
            "Rosca Martelo com Halteres 3x12", 
            "Tríceps Testa 4x10",
            "Tríceps na Polia com Corda 3x15" + avisoDiabetes
        ));
        plano.put("quinta_feira", quinta);

        // SEXTA-FEIRA
        PlanoDiario sexta = new PlanoDiario();
        sexta.setFocoDoDia("Core e Funcional");
        sexta.setCaloriasMeta(calorias);
        sexta.setExercicios(List.of(
            "Prancha Isométrica 4x 1 minuto", 
            "Abdominal Supra com Carga 4x15", 
            "Elevação de Pernas Suspenso 3x12",
            "Lombar no Banco 3x15" + avisoDiabetes
        ));
        plano.put("sexta_feira", sexta);

        usuario.setPlanoSemanal(plano);
    }
}