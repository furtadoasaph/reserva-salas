# Tarefa 1 · Reservar uma sala
Como estudante, quero reservar uma sala em um intervalo livre para evitar que duas turmas ocupem o mesmo espaço.

## Critérios aceitos no cenário
O início deve anteceder o fim. Reservas da mesma sala e data podem se tocar nas extremidades, mas qualquer minuto em comum impede a nova reserva. Salas ou datas diferentes são independentes. Recusar um pedido preserva a agenda. O protótipo guarda a agenda em memória enquanto estiver aberto.

## Divisão de responsabilidades
| Etapa | Quem executa | Quem decide | Evidência |
|---|---|---|---|
| Definir as regras | Grupo, no papel de responsável pelo produto | Grupo | Critérios acima |
| Planejar | Agente de implementação | Grupo, no papel de revisor técnico | Plano abaixo |
| Implementar e verificar | Agente | Grupo verifica aderência às regras | Código, testes e saída registrada |
| Integrar a versão | Agente prepara a alteração | Grupo autoriza a integração no cenário | Registro de revisão e histórico Git |

## Primeiro ponto de decisão
Instrução ao agente no modelo: leia a tarefa, proponha os arquivos e exemplos de teste e aguarde a decisão antes de programar.
Plano: criar Agenda.java para armazenar reservas e verificar conflitos, Principal.java para entrada pelo terminal e dois conjuntos de testes para a primeira verificação e a conferência. Usar datas e horários do próprio Java. Manter o escopo em uma sala por pedido e um único dia por reserva.

Decisão assumida para a simulação: plano aprovado. Uma mudança de regra ou uma dúvida sobre o horário de término deve voltar ao grupo. As duas aprovações deste estudo são premissas solicitadas pelo grupo. Os commits são do agente que preparou o material.
