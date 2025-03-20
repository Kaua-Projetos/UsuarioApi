# Deploy no Fly.io SEM Docker local

Este guia explica como fazer deploy no Fly.io sem precisar do Docker instalado no seu computador.

## Pré-requisitos

1. Conta no [Fly.io](https://fly.io)
2. Fly CLI instalado

## Instalação do Fly CLI (sem Docker)

### Windows
```
iwr https://fly.io/install.ps1 -useb | iex
```

### Mac/Linux
```
curl -L https://fly.io/install.sh | sh
```

## Login no Fly.io

```
fly auth login
```

## Deploy sem Docker local

1. Navegue até a pasta UsuarioAPI:

```
cd UsuarioAPI
```

2. Execute o comando de deploy remoto:

```
fly launch --remote-only
```

- Quando perguntar se quer criar um novo app, responda `Y`
- Quando perguntar se quer usar o nome do diretório ou outro nome, escolha o nome que preferir
- Quando perguntar sobre a região, recomendo escolher `gru` (São Paulo)
- Quando perguntar se quer configurar PostgreSQL ou Redis, responda `N` (já temos o Supabase)
- Quando perguntar sobre criar um banco de dados, responda `N`
- Quando perguntar se quer fazer deploy agora, responda `Y`

3. Configure as variáveis de ambiente para o Supabase:

```
fly secrets set DATABASE_URL="jdbc:postgresql://aws-0-sa-east-1.pooler.supabase.com:5432/postgres" DATABASE_USERNAME="postgres.azkqdfdhlzqatkakgavt" DATABASE_PASSWORD="kazito10@"
```

4. Se precisar fazer deploy novamente depois:

```
fly deploy --remote-only
```

5. Para acessar o aplicativo:

```
fly open
```

## Solução de Problemas

Se o comando falhar com erro relacionado ao Docker:
- Certifique-se de usar a flag `--remote-only` em todos os comandos de deploy
- Também pode tentar `fly deploy --local-only=false`

Se o build falhar, verifique os logs:
```
fly logs
```

## Observações

- Com a opção `--remote-only`, todo o processo de build acontece nos servidores do Fly.io
- Isso é mais lento que o build local, mas funciona sem Docker
- O Fly.io vai detectar automaticamente que é um projeto Java/Spring Boot 