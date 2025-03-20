# Guia de Deploy para Fly.io

Este guia vai ajudar você a fazer deploy do gerenciador de tarefas no Fly.io.

## Aplicação Atual

A aplicação já está implantada e disponível em:
- https://usuarioapi.fly.dev/ ou
- https://usuarioapi-4utlfg.fly.dev/

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

2. Para fazer deploy de atualizações:

```
fly deploy
```

3. Para verificar o status:

```
fly status
```

4. Para verificar os logs:

```
fly logs
```

5. Para acessar o aplicativo:

```
fly open
```

## Solução de Problemas

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