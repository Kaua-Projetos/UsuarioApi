# Guia de Deploy para Fly.io

Este guia vai ajudar você a fazer deploy do gerenciador de tarefas no Fly.io.

## Pré-requisitos

1. Conta no [Fly.io](https://fly.io)
2. Fly CLI instalado

## Instalação do Fly CLI

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

## Deploy

1. Navegue até a pasta UsuarioAPI onde está o arquivo `fly.toml`:

```
cd UsuarioAPI
```

2. Inicie o deploy:

```
fly launch --dockerfile Dockerfile
```

- Quando perguntar se quer criar um novo app, responda `Y`
- Quando perguntar se quer usar o nome do diretório ou outro nome, escolha o nome que preferir
- Quando perguntar sobre a região, recomendo escolher `gru` (São Paulo)
- Quando perguntar se quer configurar PostgreSQL ou Redis, responda `N` (já temos o Supabase)
- Quando perguntar sobre criar um banco de dados, responda `N`
- Quando perguntar se quer fazer deploy agora, responda `Y`

3. Se preferir fazer o deploy depois, configure as variáveis de ambiente para o Supabase:

```
fly secrets set DATABASE_URL="jdbc:postgresql://aws-0-sa-east-1.pooler.supabase.com:5432/postgres" DATABASE_USERNAME="postgres.azkqdfdhlzqatkakgavt" DATABASE_PASSWORD="kazito10@"
```

4. E então faça o deploy:

```
fly deploy
```

5. Após o deploy, confira o status:

```
fly status
```

6. Para verificar os logs:

```
fly logs
```

7. Para acessar o aplicativo:

```
fly open
```

## Solução de Problemas

Se encontrar o erro "Could not find a Dockerfile", verifique se está no diretório correto que contém o Dockerfile. Use o comando `fly launch --dockerfile Dockerfile` para especificar explicitamente.

Se o deploy falhar com erros de build, verifique os logs:

```
fly logs
```

Para reiniciar o aplicativo:

```
fly apps restart
```

Para verificar as configurações:

```
fly config show
```

## Comandos Úteis

- `fly ips list`: Lista os IPs da aplicação
- `fly scale show`: Mostra a configuração atual de escala
- `fly scale count 1`: Define o número de instâncias
- `fly scale vm shared-cpu-1x`: Define o tipo de VM 