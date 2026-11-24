import java.time.LocalTime;

public class TestesRevisao extends TestesIniciais {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        if (agenda.reservar("A", DIA, LocalTime.of(14, 0), LocalTime.of(15, 0)) != Agenda.Resultado.ACEITA) {
            throw new AssertionError("Falha ao preparar a reserva de referência");
        }
        verificar(agenda, "A", DIA, "13:00", "16:00", Agenda.Resultado.CONFLITO, "Envolve a reserva inteira");
        verificar(agenda, "A", DIA, "14:10", "14:20", Agenda.Resultado.CONFLITO, "Dentro da reserva anterior");
        verificar(agenda, " a ", DIA, "14:00", "15:00", Agenda.Resultado.CONFLITO, "Duplicada com espaços e minúscula");
        verificar(agenda, "A", DIA, "13:00", "14:00", Agenda.Resultado.ACEITA, "Termina no início da anterior após recusa");
        verificar(agenda, "A", DIA, "16:00", "16:00", Agenda.Resultado.INVALIDA, "Duração zero");
        verificar(agenda, "A", DIA, "17:00", "16:00", Agenda.Resultado.INVALIDA, "Horário invertido");
        verificar(agenda, " ", DIA, "17:00", "18:00", Agenda.Resultado.INVALIDA, "Sala vazia");
    }
}
