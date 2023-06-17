# API Energy Monitor
## Bem-vindo à API Energy Monitor! Aqui você encontrará informações sobre como utilizar os endpoints para criar e manipular dados.
### Estamos utilizando o Java na versão 17

Endpoint
POST http://localhost:8080/pessoas

Você pode utilizar o seguinte JSON como exemplo para cadastrar um novo usuario:

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
##### genero: O gênero da pessoa. Pode ser "MASCULINO", "FEMININO" ou "OUTRO".
##### parentesco: O tipo de parentesco da pessoa. Pode ser "FILHO", "FILHA", "IRMAO", "IRMA" "PAI", "MAE", "OUTRO", ou outro valor válido.

#####  Se você deseja copiar e editar o JSON de exemplo para criar uma nova pessoa, sinta-se à vontade para fazer isso! Basta copiar o JSON acima, modificá-lo conforme necessário e enviá-lo como corpo da requisição POST para o endpoint /pessoas.

Exemplo de Resposta
Após enviar a requisição, você receberá uma resposta indicando o sucesso da operação ou quaisquer erros que tenham ocorrido.
```sh
{
  "Dados recebidos com sucesso."
}
```

Endpoint
POST http://localhost:8080/enderecos

Você pode utilizar o seguinte JSON como exemplo para cadastrar um novo endereço:

```sh
{
    "rua": "EQ 5/8 Bloco B Casa",
    "numero": 2,
    "bairro": "Setor Oeste",
    "cidade": "Gama",
    "estado": "DF"
}
```

## Descrição dos Campos
##### rua: O nome da rua.
##### numero: O número da casa.
##### bairro: O nome do bairro.
##### cidade: O nome da cidade.
##### estado: A sigla do estado.
Exemplo de Resposta
Após enviar a requisição, você receberá uma resposta indicando o sucesso da operação ou quaisquer erros que tenham ocorrido.
```sh
{
  "Dados recebidos com sucesso."
}
```

Endpoint
POST http://localhost:8080/eletronicos

Você pode utilizar o seguinte JSON como exemplo para cadastrar um novo eletrônico:

```sh
{
    "nome": "Televisão",
    "potencia": 100,
    "potencia": 5
}
```

## Descrição dos Campos
##### nome: O nome do eletrônico.
##### potencia: A potência do eletrônico em Watts.
##### potencia: Quantos watts de potencia o eletrocinico tem.
Exemplo de Resposta
Após enviar a requisição, você receberá uma resposta indicando o sucesso da operação ou quaisquer erros que tenham ocorrido.
```sh
{
  "Dados recebidos com sucesso."
}
```