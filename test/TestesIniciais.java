import java.time.LocalDate;
import java.time.LocalTime;

public class TestesIniciais {
    static final LocalDate DIA = LocalDate.of(2026, 11, 24);

    static void verificar(Agenda agenda, String sala, LocalDate dia, String inicio, String fim,
                          Agenda.Resultado esperado, String caso) {
        Agenda.Resultado obtido = agenda.reservar(sala, dia, LocalTime.parse(inicio), LocalTime.parse(fim));
        if (obtido != esperado) {
            throw new AssertionError(caso + " | esperado " + esperado + ", obtido " + obtido);
        }
        System.out.println("OK | " + caso + " | " + obtido);
    }

    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        verificar(agenda, "A", DIA, "14:00", "15:00", Agenda.Resultado.ACEITA, "Primeira reserva");
        verificar(agenda, "A", DIA, "14:30", "15:30", Agenda.Resultado.CONFLITO, "Sobreposição parcial");
        verificar(agenda, "A", DIA, "15:00", "16:00", Agenda.Resultado.ACEITA, "Começa no fim da anterior");
        verificar(agenda, "B", DIA, "14:00", "15:00", Agenda.Resultado.ACEITA, "Outra sala");
        verificar(agenda, "A", DIA.plusDays(1), "14:00", "15:00", Agenda.Resultado.ACEITA, "Outro dia");
    }
}
