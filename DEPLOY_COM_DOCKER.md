# Deploy no Fly.io COM Docker local

Este guia explica como fazer deploy no Fly.io utilizando Docker local para um processo mais rápido e confiável.

## Pré-requisitos

1. Conta no [Fly.io](https://fly.io)
2. Fly CLI instalado
3. Docker instalado e funcionando

## Preparação

1. Navegue até a pasta UsuarioAPI:

```
cd UsuarioAPI
```

2. Certifique-se de que o Dockerfile existe e está correto:

```
type Dockerfile
```

## Deploy com Docker

1. Inicie o deploy com o Docker local:

```
fly launch
```

- Quando perguntar se quer criar um novo app, responda `Y`
- Quando perguntar se quer usar o nome do diretório ou outro nome, escolha o nome que preferir
- Quando perguntar sobre a região, recomendo escolher `gru` (São Paulo)
- Quando perguntar se quer configurar PostgreSQL ou Redis, responda `N` (já temos o Supabase)
- Quando perguntar sobre criar um banco de dados, responda `N`
- Quando perguntar se quer fazer deploy agora, responda `Y`

2. Configure as variáveis de ambiente para o Supabase:

```
fly secrets set DATABASE_URL="jdbc:postgresql://aws-0-sa-east-1.pooler.supabase.com:5432/postgres" DATABASE_USERNAME="postgres.azkqdfdhlzqatkakgavt" DATABASE_PASSWORD="kazito10@"
```

3. Para futuros deploys, use:

```
fly deploy
```

4. Para acessar o aplicativo:

```
fly open
```

## Monitoramento e Manutenção

1. Para ver logs da aplicação:

```
fly logs
```

2. Para escalar (se necessário):

```
fly scale count 1
```

3. Para reiniciar a aplicação:

```
fly apps restart
```

## Vantagens do Docker Local

- Build mais rápido, pois o Docker local otimiza o processo
- Teste do contêiner antes do deploy
- Melhor detecção de problemas antes do deploy
- Cache de camadas para builds futuros mais rápidos

## Observações

- O contêiner Docker construído localmente será enviado ao Fly.io
- Toda a infraestrutura é gerenciada pelo Fly.io
- A aplicação usará as variáveis de ambiente configuradas para acessar o Supabase 