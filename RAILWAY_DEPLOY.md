# Guia de Deploy no Railway

Este guia atualizado contém instruções para fazer o deploy da aplicação no Railway e resolver problemas de conexão com o banco de dados.

## Preparação para Deploy

1. **Verifique se os arquivos estão atualizados**:
   - `Dockerfile` - Configurado para usar o perfil de produção
   - `application-prod.yml` - Configurações específicas para produção
   - `.env.railway` - Variáveis de ambiente para o Railway
   - `railway.json` - Configuração do deploy

2. **Faça commit e push para seu repositório GitHub**

## Deploy no Railway

### Passo 1: Criar projeto e banco de dados

1. **Acesse o Railway**: https://railway.app/
2. **Crie um novo projeto**: "New Project" > "Deploy from GitHub repo"
3. **Adicione banco de dados PostgreSQL**:
   - Vá para "New" > "Database" > "PostgreSQL"
   - Aguarde a provisão do banco de dados

### Passo 2: Configuração das variáveis de ambiente

1. **No mesmo projeto, adicione a aplicação**:
   - Clique em "New" > "GitHub Repo"
   - Selecione seu repositório

2. **Configure as variáveis de ambiente**:
   - As variáveis para PostgreSQL (`PGHOST`, `PGPORT`, etc.) são geradas automaticamente
   - Adicione a variável `SPRING_PROFILES_ACTIVE=prod` se não estiver definida

### Passo 3: Verificação e troubleshooting

Em caso de erro "Connection to localhost:5432 refused":

1. **Verifique as variáveis de ambiente**:
   - Na sua aplicação no Railway, vá para "Variables"
   - Confirme que as variáveis `PGHOST`, `PGPORT`, `PGDATABASE`, `PGUSER` e `PGPASSWORD` estão definidas
   - Se não estiverem, você precisará associar manualmente o banco de dados à aplicação

2. **Associe o banco de dados com a aplicação**:
   - No Railway, vá para o dashboard do seu projeto
   - Arraste o serviço PostgreSQL para o serviço da aplicação para ligá-los
   - As variáveis de ambiente serão compartilhadas automaticamente

3. **Reinicie o deploy**:
   - Vá para "Deployments"
   - Clique em "Deploy" para iniciar um novo deploy

## Dicas para resolução de problemas

### Problema: Erro de conexão com o banco de dados

**Solução**:
1. Verifique se o banco de dados e a aplicação estão no mesmo projeto
2. Confirme que as variáveis de ambiente estão sendo compartilhadas
3. Verifique o valor de `SPRING_DATASOURCE_URL` para garantir que está usando as variáveis do Railway
4. Reinicie a aplicação após fazer alterações nas variáveis

### Problema: Aplicação não inicia

**Solução**:
1. Verifique os logs no Railway para identificar a causa do erro
2. Confirme que está usando o perfil correto (prod)
3. Use o script `verify-env.sh` para validar as variáveis de ambiente:
   ```
   chmod +x verify-env.sh
   ./verify-env.sh
   ```

Este guia atualizado aborda especificamente o problema de conexão com o banco de dados que você está enfrentando. 