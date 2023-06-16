# API Energy Monitor
## Bem-vindo à API Energy Monitor! Aqui você encontrará informações sobre como utilizar os endpoints para criar e manipular dados.

Endpoint
POST http://localhost:8080/pessoas
Você pode utilizar o seguinte JSON como exemplo para criar uma nova pessoa:

```sh
{
  "nome": "João",
  "dataNascimento": "01-01-1990",
  "genero": "MASCULINO",
  "parentesco": "OUTRO"
}
```


## Descrição dos Campos
##### nome: O nome da pessoa. Deve ser uma string.
##### dataNascimento: A data de nascimento da pessoa no formato "dd-MM-aaaa".
##### genero: O gênero da pessoa. Pode ser "MASCULINO" ou "FEMININO".
##### parentesco: O tipo de parentesco da pessoa. Pode ser "FILHO", "PAI", "MÃE", "OUTRO", ou outro valor válido.

#####  Se você deseja copiar e editar o JSON de exemplo para criar uma nova pessoa, sinta-se à vontade para fazer isso! Basta copiar o JSON acima, modificá-lo conforme necessário e enviá-lo como corpo da requisição POST para o endpoint /pessoas.

Exemplo de Resposta
Após enviar a requisição, você receberá uma resposta indicando o sucesso da operação ou quaisquer erros que tenham ocorrido.
```sh
{
  "mensagem": "Pessoa criada com sucesso."
}
```
