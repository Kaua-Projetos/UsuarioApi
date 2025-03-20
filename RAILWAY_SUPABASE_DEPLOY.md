# Deploy no Railway com Supabase

Este guia contém instruções detalhadas para configurar e fazer deploy da aplicação Spring Boot no Railway, usando o Supabase como banco de dados.

## 1. Pré-requisitos

- Conta no [Railway](https://railway.app/)
- Conta no [Supabase](https://supabase.com/)
- Projeto Spring Boot configurado 
- Repositório Git com seu código

## 2. Configuração do Supabase

### 2.1 Criação do banco no Supabase

1. Acesse o [Supabase](https://supabase.com/) e faça login
2. Crie um novo projeto ou use um existente
3. Navegue até "Settings" > "Database"
4. Obtenha as informações de conexão:
   - **Host**: Endpoint do banco de dados
   - **Database name**: Nome do banco de dados (geralmente "postgres")
   - **Port**: Porta (geralmente 5432)
   - **User**: Nome de usuário (geralmente "postgres")
   - **Password**: Senha gerada pelo Supabase

5. Construa a URL de conexão:
   ```
   jdbc:postgresql://{host}:{port}/{database_name}
   ```

## 3. Deploy no Railway

### 3.1 Prepare seu aplicativo

1. Certifique-se de que seu código está no GitHub
2. Verifique se os arquivos de configuração foram adicionados:
   - `application-prod.yml`
   - `railway.toml`

### 3.2 Configure o Railway

1. Acesse o [Railway](https://railway.app/) e faça login
2. Crie um novo projeto: "New Project" > "Deploy from GitHub repo"
3. Selecione seu repositório
4. Configure as variáveis de ambiente (Settings > Variables):
   ```
   DATABASE_URL=jdbc:postgresql://{host}:{port}/{database_name}
   DATABASE_USERNAME={user}
   DATABASE_PASSWORD={password}
   SPRING_PROFILES_ACTIVE=prod
   ```

5. Substitua os valores entre {} pelas informações do Supabase
6. Aguarde o deploy automático ou inicie um deploy manual

## 4. Verificação do deploy

1. Após o deploy, clique em "Deployments" para verificar o status
2. Se o status for "Success", seu aplicativo está funcionando
3. Clique em "Settings" > "Domains" para obter a URL pública do seu aplicativo

## 5. Solução de problemas

### 5.1 Problemas com conexão ao Supabase

- Verifique se as credenciais estão corretas
- Confirme que o IP do Railway está na lista de permissões do Supabase
  - Vá para as configurações do Supabase > Database > Network
  - Adicione o IP do Railway ou habilite acesso público (apenas para testes)

### 5.2 Problemas com o Railway

- Verifique os logs no painel do Railway
- Certifique-se de que as variáveis de ambiente estão configuradas corretamente
- Tente reiniciar o aplicativo

### 5.3 Se tudo falhar

Teste a conexão localmente:
```bash
java -jar -Dspring.profiles.active=prod \
     -DDATABASE_URL=jdbc:postgresql://{host}:{port}/{database_name} \
     -DDATABASE_USERNAME={user} \
     -DDATABASE_PASSWORD={password} \
     target/Gerenciador-de-Tarefas-0.0.1-SNAPSHOT.jar
```

## 6. Comando para deploy manual (CLI)

Se preferir usar a linha de comando:

```bash
# Instale a CLI do Railway
npm i -g @railway/cli

# Login
railway login

# Inicialize em seu projeto
cd UsuarioAPI
railway init

# Configure variáveis
railway variables set DATABASE_URL=jdbc:postgresql://{host}:{port}/{database_name}
railway variables set DATABASE_USERNAME={user}
railway variables set DATABASE_PASSWORD={password}
railway variables set SPRING_PROFILES_ACTIVE=prod

# Deploy
railway up
``` 