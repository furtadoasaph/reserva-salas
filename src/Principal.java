import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        System.out.println("Digite sala;data;início;fim (exemplo: A;2026-11-24;14:00;15:00).");
        System.out.println("Digite sair para encerrar.");
        try (Scanner teclado = new Scanner(System.in)) {
            while (teclado.hasNextLine()) {
                String linha = teclado.nextLine().trim();
                if (linha.equalsIgnoreCase("sair")) break;
                String[] campos = linha.split(";", -1);
                if (campos.length != 4) {
                    System.out.println("Informe quatro campos separados por ponto e vírgula.");
                    continue;
                }
                try {
                    System.out.println(agenda.reservar(campos[0], LocalDate.parse(campos[1].trim()),
                            LocalTime.parse(campos[2].trim()), LocalTime.parse(campos[3].trim())));
                } catch (DateTimeParseException erro) {
                    System.out.println("Confira a data (AAAA-MM-DD) e os horários (HH:MM).");
                }
            }
        }
    }
}
