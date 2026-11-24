import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Agenda {
    public enum Resultado { ACEITA, CONFLITO, INVALIDA }

    private static class Reserva {
        final String sala;
        final LocalDate data;
        final LocalTime inicio;
        final LocalTime fim;

        Reserva(String sala, LocalDate data, LocalTime inicio, LocalTime fim) {
            this.sala = sala;
            this.data = data;
            this.inicio = inicio;
            this.fim = fim;
        }
    }

    private final List<Reserva> reservas = new ArrayList<>();

    public Resultado reservar(String sala, LocalDate data, LocalTime inicio, LocalTime fim) {
        if (sala == null || sala.trim().isEmpty() || data == null || inicio == null
                || fim == null || !inicio.isBefore(fim)) {
            return Resultado.INVALIDA;
        }
        String nome = sala.trim().toUpperCase(Locale.ROOT);
        for (Reserva anterior : reservas) {
            if (nome.equals(anterior.sala) && data.equals(anterior.data)
                    && inicio.isBefore(anterior.fim) && anterior.inicio.isBefore(fim)) {
                return Resultado.CONFLITO;
            }
        }
        reservas.add(new Reserva(nome, data, inicio, fim));
        return Resultado.ACEITA;
    }
}
