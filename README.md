# ADA LocateCar - Locadora de veículos

Criar uma aplicação que gerencie o aluguel de veículos, aplicando os conceitos vistos em aula, onde cada item abaixo seja considerado:

### Itens obrigatórios
- Cadastrar os veículos;
- Alterar um veículo cadastrado;
- Buscar um veículo por parte do nome;
- Cadastrar o cliente (pessoa física e jurídica)
- Alterar o cliente (pessoa física e jurídica)
- Alugar um veículo para pessoa física e jurídica;
- Devolver um veículo para pessoa física e jurídica;

### Regras de negócio
- RN1: Os veículos não podem ser repetidos; Pode utilizar a placa como identificador de unicidade;
- RN2: Tipos de veículos que serão considerados: PEQUENO, MEDIO e SUV;
- RN3: Os aluguéis e devoluções terão o local, data e horário;
- RN4: Considere aluguel em horas quebradas como uma diária completa. Exemplo: uma devolução de um veículo alugado no dia 25 de janeiro às 15h30 será cobrado uma (1) diária até dia 26 de janeiro às 15h30, a partir desse horário já serão cobradas duas (2) diárias e assim por diante.
- RN5: Os veículos que estiverem alugados não poderão estar disponíveis;
- RN6: Clientes não podem estar duplicados; Considerar CPF (Pessoa Física) e CNPJ (Pessoa Jurídica) como identificadores de unicidade;
- RN7: Regras de devolução:
    - Caso o cliente pessoa física tenha ficado com o carro mais que 5 diárias terá direito a 5% de desconto.
    - Caso o cliente pessoa jurídica tenha ficado com o carro mais que 3 diárias terá direito a 10% de desconto.


Valores base da diária por tipo de veículo:

| Tipo de Veículo | Valor por dia |
|-----------------|---------------|
| PEQUENO         | R$ 100,00     |
| MEDIO           | R$ 150,00     |
| SUV             | R$ 200,00     |

### Itens bônus - Opcionais
- Paginar as listagem envolvidas;
- Gravar os dados em arquivos;


### Entrega
- O projeto pode possuir um menu iterativo ou uma classe com o método main contendo a exemplificação das funcionalidades.
- Entregar o link do repositório do projeto no GITHUB. Explicar no README do projeto os conceitos do módulo que teve facilidade e dificuldade em aplicar.

### Conclusão
Esquematizar o projeto em um padrão que não estou acostumado e ainda executar o SOLID nele foi desafiador no início. Contudo, conforme foram passando algumas aulas, a ideia foi se encaixando cada vez mais.

Dentre os métodos do SOLID, os que eu tive mais certeza de como encaixá-los no programa foram:
- **Single Responsability Principle:** foi aplicado na divisão das pastas para cada destino do padrão MVC. Sua ideia já havia sido explicada pelo professor desde o começo, então aplicá-lo não foi algo tão complicado. Tentei aumentar sua utilização realizando algumas divisões de classes específicas, como um ValidadeLogin, porém não consegui executar a tempo;
- **Interface Segregation Principle:** essa ideia foi mais utilizada a partir do repository, onde todas as classes possuiam suas interfaces que podiam ser divididas em interfaces que extendem interfaces a fim de possuir uma segregação mais adequada;
- **Dependency Inversion Principle:** Esse princípio foi aplicado usando as interfaces na hora da injeção de dependência a fim de que nenhuma classe importante dependesse de outra menos importante. Tentei utilizar isso ao extremo e colocá-lo na prática na parte de repositories, onde iria criar repositories utilizando tanto array quanto arquivos, no entanto a parte utilizando arquivos não foi implementada pois tive dificuldade e não conseguiria entregar a tempo. Contudo, quando tiver mais tempo, pretendo retornar no projeto para finalizá-la.

Agora, se tratando dos últimos dois pontos, eu tive dificuldade para executá-los. Entendi a sua teoria, porém na hora da prática não consegui executar com perfeição, como pode se ver pelo fato de não ter aplicado a possibilidade dos repositories por arquivos, demonstrando um problema de Liskov Substitution Principle. Esses pontos pretendo retornar mais para frente no código após terminar o livro Clean Code e ajustá-los para que o código fique o mais fluido possível. 
