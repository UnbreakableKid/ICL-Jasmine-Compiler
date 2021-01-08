# Trabalho de ICL 2020

## Notas Importantes

Para correr a versão do interpretador é só correr o programa sem argumentos.

Para correr o compiler tem que se passar como argumento "-c \<Nome Ficheiro\>".

### Primeiro Handout

- Primeiro handout tem a tag Delievery1

### Segundo Handout

- No caso do compile, todas as variaveis têm de ser alocadas com new e lidas com !.

  - Exemplo:
  - `def x = 3 in print x end;;` ❌
  - `def x = new 3 in print !x end;;` ✔

- println pode ser sem argumento mas para tal precisa de acabar a expressão imediatamente a seguir, i.e seguido de um ; ou ;;.

- Está código para a criação dos ficheiros ref mas não implementado, em que se encontra comentado

- O codigo de input tem de estar single line, sem enters
