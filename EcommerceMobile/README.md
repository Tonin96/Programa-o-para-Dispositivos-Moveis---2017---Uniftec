## Gerar notificações

- Acesse https://pushtry.com/
- Vá no item "Google FCM Tester (iOS & Android)"
- Preencha o primeiro campo com o Server Token (AAAAW-CBooY:APA91bEROSlPP7zPigszRa6-jYwHGvTGT_AI-ldXIDHaZZFRPdOvPp-HBwg565f13Ycu6wNFZRc3Z8abTiNLMGQyLcu8TjZrZ-qCmzMUfm0QC-X1Ghrxf_LotzpRAnAtSew5LlI5ODft)
- O segundo campo deve ficar em branco
- Preencha o terceiro campo com a estrutura JSON abaixo. Onde na chave "to" deve ser especificado o tópico que deve receber esta notificação e na chave "data" deve ser especificada a chave "produtoId" e no valor desta chave o id do produto.

```sh
{
    "to": "/topics/todos",
    "data": {
        "produtoId" : "230"
     }
}
```

- Troque o formato da requisição para JSON
- Clique em "Send"