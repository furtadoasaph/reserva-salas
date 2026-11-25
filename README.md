# Reserva de salas com Human-in-the-Loop
UFPA · Sistemas de Informação · Engenharia de Software II  
Grupo: Asaph Ferreira Furtado, Jamilly Corrêa e Renata Silva

## Pergunta do estudo
Como verificar se uma regra definida pelas pessoas foi respeitada pelo agente que implementou o sistema? A tarefa escolhida foi registrar uma reserva e impedir conflitos entre turmas. O HITL organiza o desenvolvimento. Quem usa o programa recebe a resposta da agenda imediatamente.

## Como executar
Extraia o ZIP e abra um terminal na pasta que contém este README. Use um JDK (o projeto foi executado em Java 21). Os comandos abaixo servem no Windows, no Linux e no macOS. Na primeira execução, crie a pasta de compilação.

```
mkdir out
javac -encoding UTF-8 -d out src/Agenda.java src/Principal.java test/TestesIniciais.java test/TestesRevisao.java
java -cp out TestesIniciais
java -cp out TestesRevisao
java -cp out Principal
```

No programa, digite uma linha por vez. No exemplo, as respostas esperadas são ACEITA, CONFLITO e ACEITA. A agenda é reiniciada ao fechar o programa.

```
A;2026-11-24;14:00;15:00
A;2026-11-24;13:00;16:00
A;2026-11-24;15:00;16:00
sair
```

## Método e resultados
O [plano da tarefa](docs/tarefa-e-plano.md) descreve o escopo e a etapa de planejamento do fluxo HITL. O agente implementou a regra e preparou duas baterias automatizadas, com cinco e sete testes. Na revisão da versão entregue, Asaph testou a regra em nome da equipe e registrou a decisão no repositório.

| Versão executada | Bateria inicial | Bateria de revisão |
|---|---|---|
| Implementação entregue | 5 de 5 passaram | 7 de 7 passaram |
| Cópia experimental com defeito introduzido | 5 de 5 passaram | O primeiro caso falhou e interrompeu a bateria |

Na versão entregue, há conflito quando o novo início antecede o fim existente **e** o início existente antecede o novo fim, para a mesma sala e data. Isso permite que uma turma entre às 15h quando a anterior sai às 15h.

Para verificar a utilidade dos testes, alteramos uma cópia para que verificasse apenas se o início do novo pedido caía dentro da reserva anterior. Ao pedir das 13h às 16h, o início parece livre, embora a sala esteja ocupada das 14h às 15h. A primeira bateria deixou esse defeito passar. A segunda detectou exatamente a divergência esperada. Consulte as saídas da [versão entregue](docs/execucao.txt) e da [variante com defeito](docs/controle.txt).

## Revisão feita pela equipe

Em 24/11/2026, Asaph Ferreira Furtado fez a conferência manual da versão final. Com a sala A já reservada das 14h às 15h no mesmo dia, ele verificou estes pedidos:

| Novo pedido | Resultado esperado pela regra | Resposta do sistema |
|---|---|---|
| Das 13h às 16h | Recusar, pois o pedido inclui todo o período ocupado | `CONFLITO` |
| Das 15h às 16h | Aceitar, pois a reserva anterior termina às 15h | `ACEITA` |

Os dois resultados corresponderam ao esperado. Com base nessa conferência, a equipe aprovou a regra de conflito de horários. Asaph registrou a revisão da versão já integrada no commit `b22cca0`. O planejamento inicial integra a demonstração do fluxo HITL.

Para consultar o registro da revisão:

```
git show b22cca0 -- README.md
```

## Métricas reais do experimento

| Indicador | Resultado observado | Evidência |
|---|---|---|
| Testes automatizados aprovados na versão entregue | 12 de 12 (100%) | [Registro de execução](docs/execucao.txt) |
| Pedidos com resposta esperada na conferência manual | 2 de 2 (100%) | Revisão de Asaph, commit `b22cca0` |
| Detecção do defeito introduzido na cópia experimental | Os cinco testes iniciais passaram. O primeiro teste complementar detectou o conflito e interrompeu a execução. | [Registro da cópia experimental](docs/controle.txt) |

Os percentuais descrevem os casos executados. A equipe realizou a revisão sem cronometrar as etapas.

### Tempo de execução e rendimento dos testes

Foram cronometradas cinco rodadas em Linux com OpenJDK 21.0.13. Cada rodada começou com uma compilação em pasta vazia e executou as duas baterias em sequência. O cronômetro incluiu a inicialização de cada processo Java. Os 12 testes passaram em todas as rodadas.

| Rodada | Compilação (ms) | 5 testes iniciais (ms) | 7 testes de revisão (ms) |
|---|---:|---:|---:|
| 1 | 460,1 | 37,9 | 45,6 |
| 2 | 352,3 | 35,5 | 46,6 |
| 3 | 342,7 | 33,3 | 36,2 |
| 4 | 392,2 | 32,5 | 37,6 |
| 5 | 350,5 | 38,1 | 37,5 |

Em média, a compilação levou **379,6 ms** e a execução dos 12 testes levou **76,2 ms**. A taxa observada foi de **157,6 testes por segundo**, calculada dividindo 60 execuções de teste pelo tempo acumulado das duas baterias nas cinco rodadas. O cálculo exclui a compilação.

Essa taxa descreve a velocidade de verificação automática neste ambiente. O tempo de implementação e o da revisão humana ficaram sem registro, por isso a produtividade da equipe em entregas por hora permanece desconhecida.

## Reproduzir o experimento com defeito
Em uma cópia separada da pasta extraída, execute `git apply docs/variante.patch`. Repita a compilação e os dois comandos de teste. A segunda bateria deve encerrar com “esperado CONFLITO, obtido ACEITA”. A pasta original continua pronta para apresentar o funcionamento correto.

## Roteiro para apresentar em três pessoas
1. **Pessoa 1 — problema e primeira decisão.** “Duas turmas querem usar a mesma sala. O grupo precisa decidir até uma coisa aparentemente simples: se uma aula acaba às três, a outra pode começar às três? Neste cenário, pode. Essa decisão vira uma regra escrita antes de o agente programar.” Abra a tarefa e mostre o plano.
2. **Pessoa 2 — implementação.** “O agente recebeu a tarefa de comparar sala, dia e horário. Vamos reservar das duas às três, tentar reservar da uma às quatro e, por fim, tentar das três às quatro.” Execute o exemplo e peça à turma que preveja a última resposta.
3. **Pessoa 3 — conferência e segunda decisão.** “Os cinco testes iniciais passaram até numa cópia que continha um erro. O pedido da uma às quatro revelou a falha porque envolve toda a reserva existente. É esse tipo de evidência que o revisor precisa considerar antes de aprovar a integração.” Mostre as saídas das duas baterias e o commit da revisão feita por Asaph.
