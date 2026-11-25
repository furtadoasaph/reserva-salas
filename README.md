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
A unidade de estudo é uma alteração em um repositório Java. O [plano da tarefa](docs/tarefa-e-plano.md) documenta o escopo e a primeira decisão. O agente produziu a implementação e cinco testes iniciais. Uma bateria complementar de sete testes representa a etapa de revisão. O agente também preparou essa bateria. A revisão humana foi assumida para o cenário, sem coleta de observações de revisores.

| Versão executada | Bateria inicial | Bateria de revisão |
|---|---|---|
| Implementação entregue | 5 de 5 passaram | 7 de 7 passaram |
| Cópia experimental com defeito introduzido | 5 de 5 passaram | O primeiro caso falhou e interrompeu a bateria |

Na versão entregue, há conflito quando o novo início antecede o fim existente **e** o início existente antecede o novo fim, para a mesma sala e data. Isso permite que uma turma entre às 15h quando a anterior sai às 15h.

Para verificar a utilidade dos testes, alteramos uma cópia para que verificasse apenas se o início do novo pedido caía dentro da reserva anterior. Ao pedir das 13h às 16h, o início parece livre, embora a sala esteja ocupada das 14h às 15h. A primeira bateria deixou esse defeito passar. A segunda detectou exatamente a divergência esperada. Consulte as saídas da [versão entregue](docs/execucao.txt) e da [variante com defeito](docs/controle.txt).

**Interpretação.** Neste exemplo, escolher um cenário adicional mudou a capacidade de detectar um erro. O ensaio usa uma única tarefa e um defeito artificial. Não medimos produtividade, tempo de revisão ou desempenho de revisores humanos.

## Segunda decisão e histórico
Decisão final assumida no cenário: aprovar a integração da implementação em que as duas baterias passaram. A variante com defeito ficou restrita ao experimento. O histórico Git local registra plano, implementação, conferência e integração. Para inspecioná-lo, execute:

```
git log --oneline --graph --all
```

O arquivo de tarefa e a integração local representam a issue e o pull request na demonstração. Uma publicação remota e assinaturas de revisores ficam fora do registro produzido.

## Reproduzir o experimento com defeito
Em uma cópia separada da pasta extraída, execute `git apply docs/variante.patch`. Repita a compilação e os dois comandos de teste. A segunda bateria deve encerrar com “esperado CONFLITO, obtido ACEITA”. A pasta original continua pronta para apresentar o funcionamento correto.

## Roteiro para apresentar em três pessoas
1. **Pessoa 1 — problema e primeira decisão.** “Duas turmas querem usar a mesma sala. O grupo precisa decidir até uma coisa aparentemente simples: se uma aula acaba às três, a outra pode começar às três? Neste cenário, pode. Essa decisão vira uma regra escrita antes de o agente programar.” Abra a tarefa e mostre o plano.
2. **Pessoa 2 — implementação.** “O agente recebeu a tarefa de comparar sala, dia e horário. Vamos reservar das duas às três, tentar reservar da uma às quatro e, por fim, tentar das três às quatro.” Execute o exemplo e peça à turma que preveja a última resposta.
3. **Pessoa 3 — conferência e segunda decisão.** “Os cinco testes iniciais passaram até numa cópia que continha um erro. O pedido da uma às quatro revelou a falha porque envolve toda a reserva existente. É esse tipo de evidência que o revisor precisa considerar antes de aprovar a integração.” Mostre os dois registros de execução e identifique o defeito deliberado e as aprovações assumidas.

Revisão do grupo
Em 24/11/26 , Asaph ferreira testou a sala A, já reservada das 14h às 15h. Ao tentar reservar das 13h às 16h, o sistema mostrou CONFLITO. Ao tentar reservar das 15h às 16h, mostrou ACEITO. Com base nisso, o grupo decidiu aprovar a regra.

